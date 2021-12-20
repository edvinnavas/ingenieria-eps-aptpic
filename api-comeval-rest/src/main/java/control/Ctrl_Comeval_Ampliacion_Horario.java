package control;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entidad.Comeval_Ampliacion_Horario;
import entidad.Solicitud_Workflow_Historial;
import java.io.Serializable;
import java.lang.reflect.Type;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Ctrl_Comeval_Ampliacion_Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    private String jndi_nombre;
    private Connection conn;
    
    public Ctrl_Comeval_Ampliacion_Horario() {
    }
    
    public Ctrl_Comeval_Ampliacion_Horario(String jndi_nombre) {
        try {
            this.jndi_nombre = jndi_nombre;
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - Ctrl_Comeval_Ampliacion_Horario):" + ex.toString());
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

    public String crear_ampliacion_horario(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Ampliacion_Horario>>() {
            }.getType();
            List<Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_ampliacion_horario.size(); i++) {
                Long id_comeval_ampliacion_horario = Long.parseLong("0");
                String cadenasql = "select coalesce(max(t.id_comeval_ampliacion_horario) + 1, 1) max_id_comeval_ampliacion_horario from comeval_ampliacion_horario t";
                Statement stmt = this.conn.createStatement();
                ResultSet rs = stmt.executeQuery(cadenasql);
                while (rs.next()) {
                    id_comeval_ampliacion_horario = rs.getLong(1);
                }
                rs.close();
                stmt.close();
                
                String id_plaza_temporal = "";
                if(lst_comeval_ampliacion_horario.get(i).getId_plaza_temporal() == Long.parseLong("0")) {
                    id_plaza_temporal = "null";
                } else {
                    id_plaza_temporal = lst_comeval_ampliacion_horario.get(i).getId_plaza_temporal().toString();
                }
                
                String id_plaza_indefinido = "";
                if(lst_comeval_ampliacion_horario.get(i).getId_plaza_indefinido() == Long.parseLong("0")) {
                    id_plaza_indefinido = "null";
                } else {
                    id_plaza_indefinido = lst_comeval_ampliacion_horario.get(i).getId_plaza_indefinido().toString();
                }
                
                cadenasql = "insert into comeval_ampliacion_horario ("
                        + "id_comeval_ampliacion_horario, "
                        + "personal, "
                        + "id_plaza_temporal, " 
                        + "id_plaza_indefinido, "
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
                        + "visto_bueno_secretario_academico, "
                        + "notificacion_tesoreria) values ("
                        + id_comeval_ampliacion_horario + ",'"
                        + lst_comeval_ampliacion_horario.get(i).getPersonal() + "',"
                        + id_plaza_temporal + ","
                        + id_plaza_indefinido + ",'"
                        + lst_comeval_ampliacion_horario.get(i).getDescripcion_solicitud() + "',"
                        + lst_comeval_ampliacion_horario.get(i).getId_solicitud_acta() + ",'"
                        + lst_comeval_ampliacion_horario.get(i).getNo_acta() + "','"
                        + lst_comeval_ampliacion_horario.get(i).getAnio_acta() + "','"
                        + lst_comeval_ampliacion_horario.get(i).getPunto_acta() + "','"
                        + lst_comeval_ampliacion_horario.get(i).getInciso_acta() + "','"
                        + lst_comeval_ampliacion_horario.get(i).getFecha_acta() + "','"
                        + lst_comeval_ampliacion_horario.get(i).getResolucion_acta() + "','"
                        + lst_comeval_ampliacion_horario.get(i).getFecha_ingreso() + "',"
                        + lst_comeval_ampliacion_horario.get(i).getId_estado_solicitud() + ","
                        + lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud() + ","
                        + lst_comeval_ampliacion_horario.get(i).getRechazado() + ","
                        + lst_comeval_ampliacion_horario.get(i).getVisto_bueno_director() + ","
                        + lst_comeval_ampliacion_horario.get(i).getVisto_bueno_secretario_academico() + ","
                        + lst_comeval_ampliacion_horario.get(i).getNotificacion_tesoreria() + ")";
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                for (Integer j = 0; j < lst_comeval_ampliacion_horario.get(i).getLst_horario_plaza().size(); j++) {
                    cadenasql = "insert into comeval_ampliacion_horario_plaza ("
                            + "id_comeval_ampliacion_horario,"
                            + "id_horario,"
                            + "horainicio,"
                            + "horafin,"
                            + "dias) values ("
                            + id_comeval_ampliacion_horario + ","
                            + (j+1) + ",'"
                            + lst_comeval_ampliacion_horario.get(i).getLst_horario_plaza().get(j).getHorainicio() + "','"
                            + lst_comeval_ampliacion_horario.get(i).getLst_horario_plaza().get(j).getHorafin() + "','"
                            + lst_comeval_ampliacion_horario.get(i).getLst_horario_plaza().get(j).getDias() + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }

                Solicitud_Workflow_Historial solicitud_workflow_historial = new Solicitud_Workflow_Historial();
                solicitud_workflow_historial.setId_solicitud(id_comeval_ampliacion_horario);
                solicitud_workflow_historial.setId_estado_solicitud(lst_comeval_ampliacion_horario.get(i).getId_estado_solicitud());
                solicitud_workflow_historial.setId_tipo_solicitud(lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud());
                solicitud_workflow_historial.setFecha(lst_comeval_ampliacion_horario.get(i).getFecha_ingreso());
                solicitud_workflow_historial.setRechazado(lst_comeval_ampliacion_horario.get(i).getRechazado());
                solicitud_workflow_historial.setFecha_rechazado(lst_comeval_ampliacion_horario.get(i).getFecha_ingreso());

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

            resultado = "0,Solicitud COMEVAL ampliación de horario ingresado.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - crear_ampliacion_horario):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - crear_ampliacion_horario):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - crear_ampliacion_horario-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - crear_ampliacion_horario-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String modificar_ampliacion_horario(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Ampliacion_Horario>>() {
            }.getType();
            List<Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_ampliacion_horario.size(); i++) {
                
                String id_plaza_temporal = "";
                if(lst_comeval_ampliacion_horario.get(i).getId_plaza_temporal() == Long.parseLong("0")) {
                    id_plaza_temporal = "null";
                } else {
                    id_plaza_temporal = lst_comeval_ampliacion_horario.get(i).getId_plaza_temporal().toString();
                }
                
                String id_plaza_indefinido = "";
                if(lst_comeval_ampliacion_horario.get(i).getId_plaza_indefinido() == Long.parseLong("0")) {
                    id_plaza_indefinido = "null";
                } else {
                    id_plaza_indefinido = lst_comeval_ampliacion_horario.get(i).getId_plaza_indefinido().toString();
                }
                
                String cadenasql = "update comeval_ampliacion_horario set "
                        + "personal='" + lst_comeval_ampliacion_horario.get(i).getPersonal() + "', "
                        + "id_plaza_temporal=" + id_plaza_temporal + ", "
                        + "id_plaza_indefinido=" + id_plaza_indefinido + ", "
                        + "descripcion_solicitud='" + lst_comeval_ampliacion_horario.get(i).getDescripcion_solicitud() + "', "
                        + "id_solicitud_acta=" + lst_comeval_ampliacion_horario.get(i).getId_solicitud_acta() + ", "
                        + "no_acta='" + lst_comeval_ampliacion_horario.get(i).getNo_acta() + "', "
                        + "anio_acta='" + lst_comeval_ampliacion_horario.get(i).getAnio_acta() + "', "
                        + "punto_acta='" + lst_comeval_ampliacion_horario.get(i).getPunto_acta() + "', "
                        + "inciso_acta='" + lst_comeval_ampliacion_horario.get(i).getInciso_acta() + "', "
                        + "fecha_acta='" + lst_comeval_ampliacion_horario.get(i).getFecha_acta() + "', "
                        + "resolucion_acta='" + lst_comeval_ampliacion_horario.get(i).getResolucion_acta() + "', "
                        + "fecha_ingreso='" + lst_comeval_ampliacion_horario.get(i).getFecha_ingreso() + "', "
                        + "id_estado_solicitud=" + lst_comeval_ampliacion_horario.get(i).getId_estado_solicitud() + ", "
                        + "id_tipo_solicitud=" + lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud() + ", "
                        + "rechazado=" + lst_comeval_ampliacion_horario.get(i).getRechazado() + ", "
                        + "visto_bueno_director=" + lst_comeval_ampliacion_horario.get(i).getVisto_bueno_director() + ", "
                        + "visto_bueno_secretario_academico=" + lst_comeval_ampliacion_horario.get(i).getVisto_bueno_secretario_academico() + ", "
                        + "notificacion_tesoreria=" + lst_comeval_ampliacion_horario.get(i).getNotificacion_tesoreria() + " "
                        + "where "
                        + "id_comeval_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                Statement stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                cadenasql = "delete from comeval_ampliacion_horario_plaza where id_comeval_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                for (Integer j = 0; j < lst_comeval_ampliacion_horario.get(i).getLst_horario_plaza().size(); j++) {
                    cadenasql = "insert into comeval_ampliacion_horario_plaza ("
                            + "id_comeval_ampliacion_horario,"
                            + "id_horario,"
                            + "horainicio,"
                            + "horafin,"
                            + "dias) values ("
                            + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario() + ","
                            + (j+1) + ",'"
                            + lst_comeval_ampliacion_horario.get(i).getLst_horario_plaza().get(j).getHorainicio() + "','"
                            + lst_comeval_ampliacion_horario.get(i).getLst_horario_plaza().get(j).getHorafin() + "','"
                            + lst_comeval_ampliacion_horario.get(i).getLst_horario_plaza().get(j).getDias() + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }
            }

            this.conn.commit();
            this.conn.setAutoCommit(true);

            resultado = "0,Solicitud COMEVAL ampliación de horario modificado.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_ampliacion_horario):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - modificar_ampliacion_horario):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_ampliacion_horario-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - modificar_ampliacion_horario-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

}
