package control;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entidad.Comeval_Cambio_Horario;
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

public class Ctrl_Comeval_Cambio_Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    private String jndi_nombre;
    private Connection conn;
    
    public Ctrl_Comeval_Cambio_Horario() {
    }

    public Ctrl_Comeval_Cambio_Horario(String jndi_nombre) {
        try {
            this.jndi_nombre = jndi_nombre;
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - Ctrl_Comeval_Cambio_Horario):" + ex.toString());
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

    public String crear_cambio_horario(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Cambio_Horario>>() {
            }.getType();
            List<Comeval_Cambio_Horario> lst_comeval_cambio_horario = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_cambio_horario.size(); i++) {
                Long id_comeval_cambio_horario = Long.parseLong("0");
                String cadenasql = "select coalesce(max(t.id_comeval_cambio_horario) + 1, 1) max_id_comeval_cambio_horario from comeval_cambio_horario t";
                Statement stmt = this.conn.createStatement();
                ResultSet rs = stmt.executeQuery(cadenasql);
                while (rs.next()) {
                    id_comeval_cambio_horario = rs.getLong(1);
                }
                rs.close();
                stmt.close();

                cadenasql = "insert into comeval_cambio_horario ("
                        + "id_comeval_cambio_horario, "
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
                        + "id_tipo_solicitud,"
                        + "rechazado, "
                        + "visto_bueno_director, "
                        + "visto_bueno_secretario_academico) values ("
                        + id_comeval_cambio_horario + ",'"
                        + lst_comeval_cambio_horario.get(i).getPersonal() + "','"
                        + lst_comeval_cambio_horario.get(i).getDescripcion_solicitud() + "',"
                        + lst_comeval_cambio_horario.get(i).getId_solicitud_acta() + ",'"
                        + lst_comeval_cambio_horario.get(i).getNo_acta() + "','"
                        + lst_comeval_cambio_horario.get(i).getAnio_acta() + "','"
                        + lst_comeval_cambio_horario.get(i).getPunto_acta() + "','"
                        + lst_comeval_cambio_horario.get(i).getInciso_acta() + "','"
                        + lst_comeval_cambio_horario.get(i).getFecha_acta() + "','"
                        + lst_comeval_cambio_horario.get(i).getResolucion_acta() + "','"
                        + lst_comeval_cambio_horario.get(i).getFecha_ingreso() + "',"
                        + lst_comeval_cambio_horario.get(i).getId_estado_solicitud() + ","
                        + lst_comeval_cambio_horario.get(i).getId_tipo_solicitud() + ","
                        + lst_comeval_cambio_horario.get(i).getRechazado() + ","
                        + lst_comeval_cambio_horario.get(i).getVisto_bueno_director() + ","
                        + lst_comeval_cambio_horario.get(i).getVisto_bueno_secretario_academico() + ")";
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                for (Integer j = 0; j < lst_comeval_cambio_horario.get(i).getLst_horario_plaza().size(); j++) {
                    cadenasql = "insert into comeval_cambio_horario_plaza ("
                            + "id_comeval_cambio_horario,"
                            + "id_horario,"
                            + "horainicio,"
                            + "horafin,"
                            + "dias,"
                            + "personal,"
                            + "plaza,"
                            + "periodo,"
                            + "anio,"
                            + "subpartida,"
                            + "renglon) values ("
                            + id_comeval_cambio_horario + ","
                            + (j+1) + ",'"
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getHorainicio() + "','"
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getHorafin() + "','"
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getDias() + "','"
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getPersonal() + "',"
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getPlaza() + ","
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getPeriodo() + ","
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getAnio() + ",'"
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getSubpartida() + "','"
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getRenglon() + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }

                Solicitud_Workflow_Historial solicitud_workflow_historial = new Solicitud_Workflow_Historial();
                solicitud_workflow_historial.setId_solicitud(id_comeval_cambio_horario);
                solicitud_workflow_historial.setId_estado_solicitud(lst_comeval_cambio_horario.get(i).getId_estado_solicitud());
                solicitud_workflow_historial.setId_tipo_solicitud(lst_comeval_cambio_horario.get(i).getId_tipo_solicitud());
                solicitud_workflow_historial.setFecha(lst_comeval_cambio_horario.get(i).getFecha_ingreso());
                solicitud_workflow_historial.setRechazado(lst_comeval_cambio_horario.get(i).getRechazado());
                solicitud_workflow_historial.setFecha_rechazado(lst_comeval_cambio_horario.get(i).getFecha_ingreso());

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

            resultado = "0,Solicitud COMEVAL cambio de horario ingresado.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - crear_cambio_horario):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - crear_cambio_horario):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - crear_cambio_horario-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - crear_cambio_horario-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String modificar_cambio_horario(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Cambio_Horario>>() {
            }.getType();
            List<Comeval_Cambio_Horario> lst_comeval_cambio_horario = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_cambio_horario.size(); i++) {
                String cadenasql = "update comeval_cambio_horario set "
                        + "personal='" + lst_comeval_cambio_horario.get(i).getPersonal() + "', "
                        + "descripcion_solicitud='" + lst_comeval_cambio_horario.get(i).getDescripcion_solicitud() + "', "
                        + "id_solicitud_acta=" + lst_comeval_cambio_horario.get(i).getId_solicitud_acta() + ", "
                        + "no_acta='" + lst_comeval_cambio_horario.get(i).getNo_acta() + "', "
                        + "anio_acta='" + lst_comeval_cambio_horario.get(i).getAnio_acta() + "', "
                        + "punto_acta='" + lst_comeval_cambio_horario.get(i).getPunto_acta() + "', "
                        + "inciso_acta='" + lst_comeval_cambio_horario.get(i).getInciso_acta() + "', "
                        + "fecha_acta='" + lst_comeval_cambio_horario.get(i).getFecha_acta() + "', "
                        + "resolucion_acta='" + lst_comeval_cambio_horario.get(i).getResolucion_acta() + "', "
                        + "fecha_ingreso='" + lst_comeval_cambio_horario.get(i).getFecha_ingreso() + "', "
                        + "id_estado_solicitud=" + lst_comeval_cambio_horario.get(i).getId_estado_solicitud() + ", "
                        + "id_tipo_solicitud=" + lst_comeval_cambio_horario.get(i).getId_tipo_solicitud() + ", "
                        + "rechazado=" + lst_comeval_cambio_horario.get(i).getRechazado() + ", "
                        + "visto_bueno_director=" + lst_comeval_cambio_horario.get(i).getVisto_bueno_director() + ", "
                        + "visto_bueno_secretario_academico=" + lst_comeval_cambio_horario.get(i).getVisto_bueno_secretario_academico() + " "
                        + "where "
                        + "id_comeval_cambio_horario=" + lst_comeval_cambio_horario.get(i).getId_comeval_cambio_horario();
                Statement stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                cadenasql = "delete from comeval_cambio_horario_plaza where id_comeval_cambio_horario=" + lst_comeval_cambio_horario.get(i).getId_comeval_cambio_horario();
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                for (Integer j = 0; j < lst_comeval_cambio_horario.get(i).getLst_horario_plaza().size(); j++) {
                    cadenasql = "insert into comeval_cambio_horario_plaza ("
                            + "id_comeval_cambio_horario,"
                            + "id_horario,"
                            + "horainicio,"
                            + "horafin,"
                            + "dias,"
                            + "personal,"
                            + "plaza,"
                            + "periodo,"
                            + "anio,"
                            + "subpartida,"
                            + "renglon) values ("
                            + lst_comeval_cambio_horario.get(i).getId_comeval_cambio_horario() + ","
                            + (j+1) + ",'"
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getHorainicio() + "','"
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getHorafin() + "','"
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getDias() + "','"
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getPersonal() + "',"
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getPlaza() + ","
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getPeriodo() + ","
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getAnio() + ",'"
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getSubpartida() + "','"
                            + lst_comeval_cambio_horario.get(i).getLst_horario_plaza().get(j).getRenglon() + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }
            }

            this.conn.commit();
            this.conn.setAutoCommit(true);

            resultado = "0,Solicitud COMEVAL cambio de horario modificado.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_cambio_horario):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - modificar_cambio_horario):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_cambio_horario-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - modificar_cambio_horario-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

}
