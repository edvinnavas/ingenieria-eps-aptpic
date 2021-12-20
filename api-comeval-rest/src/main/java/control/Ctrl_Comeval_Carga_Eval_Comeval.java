package control;

import java.io.Serializable;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entidad.Comeval_Carga_Eval_Comeval;
import entidad.Solicitud_Workflow_Historial;
import java.lang.reflect.Type;

public class Ctrl_Comeval_Carga_Eval_Comeval implements Serializable {

    private static final long serialVersionUID = 1L;
    private String jndi_nombre;
    private Connection conn;

    public Ctrl_Comeval_Carga_Eval_Comeval() {
    }
    
    public Ctrl_Comeval_Carga_Eval_Comeval(String jndi_nombre) {
        try {
            this.jndi_nombre = jndi_nombre;
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - Ctrl_Comeval_Carga_Eval_Comeval):" + ex.toString());
        }
    }

    private void Abrir_Conexion() {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(this.jndi_nombre);
            this.conn = ds.getConnection();
        } catch (Exception ex) {
            this.conn = null;
            System.out.println("1,ERROR (" + this.getClass().getName() + " - Abrir_Conexion):" + ex.toString());
        }
    }

    private void Cerrar_Conexion() {
        try {
            if (this.conn != null) {
                this.conn.close();
                this.conn = null;
            }
        } catch (Exception ex) {
            this.conn = null;
            System.out.println("1,ERROR (" + this.getClass().getName() + " - Cerrar_Conexion):" + ex.toString());
        }
    }
    
    public String crear_carga_eval_comeval(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Carga_Eval_Comeval>>() {
            }.getType();
            List<Comeval_Carga_Eval_Comeval> lst_comeval_carga_eval_comeval = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_carga_eval_comeval.size(); i++) {
                Long id_comeval_carga_eval_comeval = Long.parseLong("0");
                String cadenasql = "select coalesce(max(t.id_comeval_carga_eval_comeval) + 1, 1) max_id_comeval_carga_eval_comeval from comeval_carga_eval_comeval t";
                Statement stmt = this.conn.createStatement();
                ResultSet rs = stmt.executeQuery(cadenasql);
                while (rs.next()) {
                    id_comeval_carga_eval_comeval = rs.getLong(1);
                }
                rs.close();
                stmt.close();
                
                cadenasql = "insert into comeval_carga_eval_comeval ("
                        + "id_comeval_carga_eval_comeval, "
                        + "descripcion_solicitud, "
                        + "path_archivo, "
                        + "id_solicitud_acta, "
                        + "no_acta, "
                        + "anio_acta, "
                        + "punto_acta, "
                        + "inciso_acta, "
                        + "fecha_acta, "
                        + "resolucion_acta, "
                        + "fecha_ingreso, "
                        + "id_estado_solicitud, "
                        + "id_tipo_solicitud, "
                        + "rechazado) values ("
                        + id_comeval_carga_eval_comeval + ",'"
                        + lst_comeval_carga_eval_comeval.get(i).getDescripcion_solicitud() + "','"
                        + lst_comeval_carga_eval_comeval.get(i).getPath_archivo() + "',"
                        + lst_comeval_carga_eval_comeval.get(i).getId_solicitud_acta() + ",'"
                        + lst_comeval_carga_eval_comeval.get(i).getNo_acta() + "','"
                        + lst_comeval_carga_eval_comeval.get(i).getAnio_acta() + "','"
                        + lst_comeval_carga_eval_comeval.get(i).getPunto_acta() + "','"
                        + lst_comeval_carga_eval_comeval.get(i).getInciso_acta() + "','"
                        + lst_comeval_carga_eval_comeval.get(i).getFecha_acta() + "','"
                        + lst_comeval_carga_eval_comeval.get(i).getResolucion_acta() + "','"
                        + lst_comeval_carga_eval_comeval.get(i).getFecha_ingreso() + "',"
                        + lst_comeval_carga_eval_comeval.get(i).getId_estado_solicitud() + ","
                        + lst_comeval_carga_eval_comeval.get(i).getId_tipo_solicitud() + ","
                        + lst_comeval_carga_eval_comeval.get(i).getRechazado() + ")";
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();
                
                Solicitud_Workflow_Historial solicitud_workflow_historial = new Solicitud_Workflow_Historial();
                solicitud_workflow_historial.setId_solicitud(id_comeval_carga_eval_comeval);
                solicitud_workflow_historial.setId_estado_solicitud(lst_comeval_carga_eval_comeval.get(i).getId_estado_solicitud());
                solicitud_workflow_historial.setId_tipo_solicitud(lst_comeval_carga_eval_comeval.get(i).getId_tipo_solicitud());
                solicitud_workflow_historial.setFecha(lst_comeval_carga_eval_comeval.get(i).getFecha_ingreso());
                solicitud_workflow_historial.setRechazado(lst_comeval_carga_eval_comeval.get(i).getRechazado());
                solicitud_workflow_historial.setFecha_rechazado(lst_comeval_carga_eval_comeval.get(i).getFecha_ingreso());

                cadenasql = "insert into solicitud_workflow_historial ("
                        + "id_solicitud,"
                        + "id_estado_solicitud,"
                        + "id_tipo_solicitud,"
                        + "fecha,"
                        + "rechazado,"
                        + "fecha_rechazado) values ("
                        + solicitud_workflow_historial.getId_solicitud() + ","
                        + solicitud_workflow_historial.getId_estado_solicitud() + ","
                        + solicitud_workflow_historial.getId_tipo_solicitud() + ",'"
                        + solicitud_workflow_historial.getFecha() + "',"
                        + solicitud_workflow_historial.getRechazado() + ","
                        + "null" + ")";
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();
            }

            this.conn.commit();
            this.conn.setAutoCommit(true);

            resultado = "0,Solicitud COMEVAL carga evaluación docente ingresada.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - crear_carga_eval_comeval):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - crear_carga_eval_comeval):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - crear_carga_eval_comeval-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - crear_carga_eval_comeval-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }
    
    public String modificar_carga_eval_comeval(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Carga_Eval_Comeval>>() {
            }.getType();
            List<Comeval_Carga_Eval_Comeval> lst_comeval_carga_eval_comeval = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_carga_eval_comeval.size(); i++) {
                String cadenasql = "update comeval_carga_eval_comeval set "
                        + "descripcion_solicitud='" + lst_comeval_carga_eval_comeval.get(i).getDescripcion_solicitud() + "', "
                        + "path_archivo='" + lst_comeval_carga_eval_comeval.get(i).getPath_archivo() + "', "
                        + "id_solicitud_acta=" + lst_comeval_carga_eval_comeval.get(i).getId_solicitud_acta() + ", "
                        + "no_acta='" + lst_comeval_carga_eval_comeval.get(i).getNo_acta() + "', "
                        + "anio_acta='" + lst_comeval_carga_eval_comeval.get(i).getAnio_acta() + "', "
                        + "punto_acta='" + lst_comeval_carga_eval_comeval.get(i).getPunto_acta() + "', "
                        + "inciso_acta='" + lst_comeval_carga_eval_comeval.get(i).getInciso_acta() + "', "
                        + "fecha_acta='" + lst_comeval_carga_eval_comeval.get(i).getFecha_acta() + "', "
                        + "resolucion_acta='" + lst_comeval_carga_eval_comeval.get(i).getResolucion_acta() + "', "
                        + "fecha_ingreso='" + lst_comeval_carga_eval_comeval.get(i).getFecha_ingreso() + "', "
                        + "id_estado_solicitud=" + lst_comeval_carga_eval_comeval.get(i).getId_estado_solicitud() + ", "
                        + "id_tipo_solicitud=" + lst_comeval_carga_eval_comeval.get(i).getId_tipo_solicitud() + ", "
                        + "rechazado=" + lst_comeval_carga_eval_comeval.get(i).getRechazado() + " "
                        + "where "
                        + "id_comeval_carga_eval_comeval=" + lst_comeval_carga_eval_comeval.get(i).getId_comeval_carga_eval_comeval();
                Statement stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();
            }

            this.conn.commit();
            this.conn.setAutoCommit(true);

            resultado = "0,Solicitud COMEVAL carga evaluación docente modificada.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_carga_eval_comeval):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - modificar_carga_eval_comeval):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_carga_eval_comeval-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - modificar_carga_eval_comeval-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }
    
}
