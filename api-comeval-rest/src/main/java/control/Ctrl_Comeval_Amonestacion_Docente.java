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
import entidad.Comeval_Amonestacion_Docente;
import entidad.Solicitud_Workflow_Historial;
import java.lang.reflect.Type;

public class Ctrl_Comeval_Amonestacion_Docente implements Serializable {

    private static final long serialVersionUID = 1L;
    private String jndi_nombre;
    private Connection conn;

    public Ctrl_Comeval_Amonestacion_Docente() {
    }
    
    public Ctrl_Comeval_Amonestacion_Docente(String jndi_nombre) {
        try {
            this.jndi_nombre = jndi_nombre;
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - Ctrl_Comeval_Amonestacion_Docente):" + ex.toString());
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
    
    public String crear_amonestacion_docente(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Amonestacion_Docente>>() {
            }.getType();
            List<Comeval_Amonestacion_Docente> lst_comeval_amonestacion_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_amonestacion_docente.size(); i++) {
                Long id_comeval_amonestacion_docente = Long.parseLong("0");
                String cadenasql = "select coalesce(max(t.id_comeval_amonestacion_docente) + 1, 1) max_id_comeval_amonestacion_docente from comeval_amonestacion_docente t";
                Statement stmt = this.conn.createStatement();
                ResultSet rs = stmt.executeQuery(cadenasql);
                while (rs.next()) {
                    id_comeval_amonestacion_docente = rs.getLong(1);
                }
                rs.close();
                stmt.close();
                
                cadenasql = "insert into comeval_amonestacion_docente ("
                        + "id_comeval_amonestacion_docente, "
                        + "personal, "
                        + "descripcion_solicitud, "
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
                        + id_comeval_amonestacion_docente + ",'"
                        + lst_comeval_amonestacion_docente.get(i).getPersonal() + "','"
                        + lst_comeval_amonestacion_docente.get(i).getDescripcion_solicitud() + "',"
                        + lst_comeval_amonestacion_docente.get(i).getId_solicitud_acta() + ",'"
                        + lst_comeval_amonestacion_docente.get(i).getNo_acta() + "','"
                        + lst_comeval_amonestacion_docente.get(i).getAnio_acta() + "','"
                        + lst_comeval_amonestacion_docente.get(i).getPunto_acta() + "','"
                        + lst_comeval_amonestacion_docente.get(i).getInciso_acta() + "','"
                        + lst_comeval_amonestacion_docente.get(i).getFecha_acta() + "','"
                        + lst_comeval_amonestacion_docente.get(i).getResolucion_acta() + "','"
                        + lst_comeval_amonestacion_docente.get(i).getFecha_ingreso() + "',"
                        + lst_comeval_amonestacion_docente.get(i).getId_estado_solicitud() + ","
                        + lst_comeval_amonestacion_docente.get(i).getId_tipo_solicitud() + ","
                        + lst_comeval_amonestacion_docente.get(i).getRechazado() + ")";
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();
                
                Solicitud_Workflow_Historial solicitud_workflow_historial = new Solicitud_Workflow_Historial();
                solicitud_workflow_historial.setId_solicitud(id_comeval_amonestacion_docente);
                solicitud_workflow_historial.setId_estado_solicitud(lst_comeval_amonestacion_docente.get(i).getId_estado_solicitud());
                solicitud_workflow_historial.setId_tipo_solicitud(lst_comeval_amonestacion_docente.get(i).getId_tipo_solicitud());
                solicitud_workflow_historial.setFecha(lst_comeval_amonestacion_docente.get(i).getFecha_ingreso());
                solicitud_workflow_historial.setRechazado(lst_comeval_amonestacion_docente.get(i).getRechazado());
                solicitud_workflow_historial.setFecha_rechazado(lst_comeval_amonestacion_docente.get(i).getFecha_ingreso());
                
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

            resultado = "0,Solicitud COMEVAL amonestación docente ingresada.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - crear_amonestacion_docente):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - crear_amonestacion_docente):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - crear_amonestacion_docente-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - crear_amonestacion_docente-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }
    
    public String modificar_amonestacion_docente(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Amonestacion_Docente>>() {
            }.getType();
            List<Comeval_Amonestacion_Docente> lst_comeval_amonestacion_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_amonestacion_docente.size(); i++) {
                String cadenasql = "update comeval_amonestacion_docente set "
                        + "personal='" + lst_comeval_amonestacion_docente.get(i).getPersonal() + "', "
                        + "descripcion_solicitud='" + lst_comeval_amonestacion_docente.get(i).getDescripcion_solicitud() + "', "
                        + "id_solicitud_acta=" + lst_comeval_amonestacion_docente.get(i).getId_solicitud_acta() + ", "
                        + "no_acta='" + lst_comeval_amonestacion_docente.get(i).getNo_acta() + "', "
                        + "anio_acta='" + lst_comeval_amonestacion_docente.get(i).getAnio_acta() + "', "
                        + "punto_acta='" + lst_comeval_amonestacion_docente.get(i).getPunto_acta() + "', "
                        + "inciso_acta='" + lst_comeval_amonestacion_docente.get(i).getInciso_acta() + "', "
                        + "fecha_acta='" + lst_comeval_amonestacion_docente.get(i).getFecha_acta() + "', "
                        + "resolucion_acta='" + lst_comeval_amonestacion_docente.get(i).getResolucion_acta() + "', "
                        + "fecha_ingreso='" + lst_comeval_amonestacion_docente.get(i).getFecha_ingreso() + "', "
                        + "id_estado_solicitud=" + lst_comeval_amonestacion_docente.get(i).getId_estado_solicitud() + ", "
                        + "id_tipo_solicitud=" + lst_comeval_amonestacion_docente.get(i).getId_tipo_solicitud() + ", "
                        + "rechazado=" + lst_comeval_amonestacion_docente.get(i).getRechazado() + " "
                        + "where "
                        + "id_comeval_amonestacion_docente=" + lst_comeval_amonestacion_docente.get(i).getId_comeval_amonestacion_docente();
                Statement stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();
            }

            this.conn.commit();
            this.conn.setAutoCommit(true);

            resultado = "0,Solicitud COMEVAL amonestación docente modificada.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_amonestacion_docente):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - modificar_amonestacion_docente):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_amonestacion_docente-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - modificar_amonestacion_docente-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }
    
}
