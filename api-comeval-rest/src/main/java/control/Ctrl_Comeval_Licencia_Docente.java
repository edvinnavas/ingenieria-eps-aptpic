package control;

import entidad.Comeval_Licencia_Docente;
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
import entidad.Solicitud_Workflow_Historial;
import java.lang.reflect.Type;

public class Ctrl_Comeval_Licencia_Docente implements Serializable {

    private static final long serialVersionUID = 1L;
    private String jndi_nombre;
    private Connection conn;

    public Ctrl_Comeval_Licencia_Docente() {
    }
    
    public Ctrl_Comeval_Licencia_Docente(String jndi_nombre) {
        try {
            this.jndi_nombre = jndi_nombre;
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - Ctrl_Comeval_Licencia_Docente):" + ex.toString());
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

    public String crear_licencia_docente(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Licencia_Docente>>() {
            }.getType();
            List<Comeval_Licencia_Docente> lst_comeval_licencia_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_licencia_docente.size(); i++) {
                Long id_comeval_licencia_docente = Long.parseLong("0");
                String cadenasql = "select coalesce(max(t.id_comeval_licencia_docente) + 1, 1) max_id_comeval_licencia_docente from comeval_licencia_docente t";
                Statement stmt = this.conn.createStatement();
                ResultSet rs = stmt.executeQuery(cadenasql);
                while (rs.next()) {
                    id_comeval_licencia_docente = rs.getLong(1);
                }
                rs.close();
                stmt.close();
                
                cadenasql = "insert into comeval_licencia_docente ("
                        + "id_comeval_licencia_docente, "
                        + "personal, "
                        + "id_motivo_licencia, "
                        + "id_tipo_licencia, "
                        + "goce_sueldo, "
                        + "fecha_inicio_licencia, "
                        + "fecha_final_licencia, "
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
                        + "id_tipo_solicitud,"
                        + "rechazado, "
                        + "visto_bueno_director, "
                        + "ingreso_siif_traslado, "
                        + "confirmar_traslado, "
                        + "asignar_tipo_licencia, "
                        + "aprobacion_decanatura, "
                        + "notificacion_tesoreria) values ("
                        + id_comeval_licencia_docente + ",'"
                        + lst_comeval_licencia_docente.get(i).getPersonal() + "',"
                        + lst_comeval_licencia_docente.get(i).getId_motivo_licencia() + ","
                        + lst_comeval_licencia_docente.get(i).getId_tipo_licencia() + ",'"
                        + lst_comeval_licencia_docente.get(i).getGoce_sueldo() + "','"
                        + lst_comeval_licencia_docente.get(i).getFecha_inicio_licencia() + "','"
                        + lst_comeval_licencia_docente.get(i).getFecha_final_licencia() + "','"
                        + lst_comeval_licencia_docente.get(i).getDescripcion_solicitud() + "',"
                        + lst_comeval_licencia_docente.get(i).getId_solicitud_acta()+ ",'"
                        + lst_comeval_licencia_docente.get(i).getNo_acta() + "','"
                        + lst_comeval_licencia_docente.get(i).getAnio_acta() + "','"
                        + lst_comeval_licencia_docente.get(i).getPunto_acta() + "','"
                        + lst_comeval_licencia_docente.get(i).getInciso_acta() + "','"
                        + lst_comeval_licencia_docente.get(i).getFecha_acta() + "','"
                        + lst_comeval_licencia_docente.get(i).getResolucion_acta() + "','"
                        + lst_comeval_licencia_docente.get(i).getFecha_ingreso() + "',"
                        + lst_comeval_licencia_docente.get(i).getId_estado_solicitud() + ","
                        + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud() + ","
                        + lst_comeval_licencia_docente.get(i).getRechazado() + ","
                        + lst_comeval_licencia_docente.get(i).getVisto_bueno_director() + ","
                        + lst_comeval_licencia_docente.get(i).getIngreso_siif_traslado() + ","
                        + lst_comeval_licencia_docente.get(i).getConfirmar_traslado() + ","
                        + lst_comeval_licencia_docente.get(i).getAsignar_tipo_licencia() + ","
                        + lst_comeval_licencia_docente.get(i).getAprobacion_decanatura() + ","
                        + lst_comeval_licencia_docente.get(i).getNotificacion_tesoreria() + ")";
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();
                
                for(Integer j=0; j < lst_comeval_licencia_docente.get(i).getLst_plazas().size(); j++) {
                    cadenasql = "insert into comeval_licencia_docente_plazas ("
                            + "id_comeval_licencia_docente,"
                            + "personal,"
                            + "plaza,"
                            + "periodo,"
                            + "anio,"
                            + "subpartida,"
                            + "renglon,"
                            + "numero_plaza) values ("
                            + id_comeval_licencia_docente + ",'"
                            + lst_comeval_licencia_docente.get(i).getLst_plazas().get(j).getPersonal() + "'," 
                            + lst_comeval_licencia_docente.get(i).getLst_plazas().get(j).getPlaza() + "," 
                            + lst_comeval_licencia_docente.get(i).getLst_plazas().get(j).getPeriodo() + "," 
                            + lst_comeval_licencia_docente.get(i).getLst_plazas().get(j).getAnio() + ",'" 
                            + lst_comeval_licencia_docente.get(i).getLst_plazas().get(j).getSubpartida() + "','" 
                            + lst_comeval_licencia_docente.get(i).getLst_plazas().get(j).getRenglon() + "',"
                            + lst_comeval_licencia_docente.get(i).getLst_plazas().get(j).getNumero_plaza() + ")";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }
                
                Solicitud_Workflow_Historial solicitud_workflow_historial = new Solicitud_Workflow_Historial();
                solicitud_workflow_historial.setId_solicitud(id_comeval_licencia_docente);
                solicitud_workflow_historial.setId_estado_solicitud(lst_comeval_licencia_docente.get(i).getId_estado_solicitud());
                solicitud_workflow_historial.setId_tipo_solicitud(lst_comeval_licencia_docente.get(i).getId_tipo_solicitud());
                solicitud_workflow_historial.setFecha(lst_comeval_licencia_docente.get(i).getFecha_ingreso());
                solicitud_workflow_historial.setRechazado(lst_comeval_licencia_docente.get(i).getRechazado());
                solicitud_workflow_historial.setFecha_rechazado(lst_comeval_licencia_docente.get(i).getFecha_ingreso());
                
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

            resultado = "0,Solicitud COMEVAL licencia docente ingresada.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - crear_licencia_docente):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - crear_licencia_docente):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - crear_licencia_docente-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - crear_licencia_docente-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }
    
    public String modificar_licencia_docente(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Licencia_Docente>>() {
            }.getType();
            List<Comeval_Licencia_Docente> lst_comeval_licencia_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_licencia_docente.size(); i++) {
                String cadenasql = "update comeval_licencia_docente set "
                        + "personal='" + lst_comeval_licencia_docente.get(i).getPersonal() + "', "
                        + "id_motivo_licencia=" + lst_comeval_licencia_docente.get(i).getId_motivo_licencia() + ", "
                        + "id_tipo_licencia='" + lst_comeval_licencia_docente.get(i).getId_tipo_licencia() + "', "
                        + "goce_sueldo='" + lst_comeval_licencia_docente.get(i).getGoce_sueldo() + "', "
                        + "fecha_inicio_licencia='" + lst_comeval_licencia_docente.get(i).getFecha_inicio_licencia() + "', "
                        + "fecha_final_licencia='" + lst_comeval_licencia_docente.get(i).getFecha_final_licencia()  + "', "
                        + "descripcion_solicitud='" + lst_comeval_licencia_docente.get(i).getDescripcion_solicitud() + "', "
                        + "id_solicitud_acta=" + lst_comeval_licencia_docente.get(i).getId_solicitud_acta() + ", "
                        + "no_acta='" + lst_comeval_licencia_docente.get(i).getNo_acta() + "', "
                        + "anio_acta='" + lst_comeval_licencia_docente.get(i).getAnio_acta() + "', "
                        + "punto_acta='" + lst_comeval_licencia_docente.get(i).getPunto_acta() + "', "
                        + "inciso_acta='" + lst_comeval_licencia_docente.get(i).getInciso_acta() + "', "
                        + "fecha_acta='" + lst_comeval_licencia_docente.get(i).getFecha_acta() + "', "
                        + "resolucion_acta='" + lst_comeval_licencia_docente.get(i).getResolucion_acta() + "', "
                        + "fecha_ingreso='" + lst_comeval_licencia_docente.get(i).getFecha_ingreso() + "', "
                        + "id_estado_solicitud=" + lst_comeval_licencia_docente.get(i).getId_estado_solicitud() + ", "
                        + "id_tipo_solicitud=" + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud() + ", "
                        + "rechazado=" + lst_comeval_licencia_docente.get(i).getRechazado() + ", "
                        + "visto_bueno_director=" + lst_comeval_licencia_docente.get(i).getVisto_bueno_director() + ", "
                        + "ingreso_siif_traslado=" + lst_comeval_licencia_docente.get(i).getIngreso_siif_traslado() + ", "
                        + "confirmar_traslado=" + lst_comeval_licencia_docente.get(i).getConfirmar_traslado() + ", "
                        + "asignar_tipo_licencia=" + lst_comeval_licencia_docente.get(i).getAsignar_tipo_licencia() + ", "
                        + "aprobacion_decanatura=" + lst_comeval_licencia_docente.get(i).getAprobacion_decanatura() + ", "
                        + "notificacion_tesoreria=" + lst_comeval_licencia_docente.get(i).getNotificacion_tesoreria() + " "
                        + "where "
                        + "id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                Statement stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();
                
                cadenasql = "delete from comeval_licencia_docente_plazas where id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();
                
                for(Integer j=0; j < lst_comeval_licencia_docente.get(i).getLst_plazas().size(); j++) {
                    cadenasql = "insert into comeval_licencia_docente_plazas ("
                            + "id_comeval_licencia_docente,"
                            + "personal,"
                            + "plaza,"
                            + "periodo,"
                            + "anio,"
                            + "subpartida,"
                            + "renglon,"
                            + "numero_plaza) values ("
                            + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente() + ",'"
                            + lst_comeval_licencia_docente.get(i).getLst_plazas().get(j).getPersonal() + "'," 
                            + lst_comeval_licencia_docente.get(i).getLst_plazas().get(j).getPlaza() + "," 
                            + lst_comeval_licencia_docente.get(i).getLst_plazas().get(j).getPeriodo() + "," 
                            + lst_comeval_licencia_docente.get(i).getLst_plazas().get(j).getAnio() + ",'" 
                            + lst_comeval_licencia_docente.get(i).getLst_plazas().get(j).getSubpartida() + "','" 
                            + lst_comeval_licencia_docente.get(i).getLst_plazas().get(j).getRenglon() + "',"
                            + lst_comeval_licencia_docente.get(i).getLst_plazas().get(j).getNumero_plaza() + ")";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }
            }

            this.conn.commit();
            this.conn.setAutoCommit(true);

            resultado = "0,Solicitud COMEVAL licencia docente modificada.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_licencia_docente):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - modificar_licencia_docente):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_licencia_docente-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - modificar_licencia_docente-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }
    
}
