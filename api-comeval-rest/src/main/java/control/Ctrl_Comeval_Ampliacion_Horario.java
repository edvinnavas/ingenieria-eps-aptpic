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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public String ampliacion_horario_ingresar(String jsonString) {
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

                String nota_ref_temp;
                if (lst_comeval_ampliacion_horario.get(i).getNota_ref().equals("-")) {
                    nota_ref_temp = "null";
                } else {
                    nota_ref_temp = "'" + lst_comeval_ampliacion_horario.get(i).getNota_ref() + "'";
                }

                String fecha_nota_ref_temp;
                if (lst_comeval_ampliacion_horario.get(i).getFecha_nota_ref().equals("-")) {
                    fecha_nota_ref_temp = "null";
                } else {
                    fecha_nota_ref_temp = "'" + lst_comeval_ampliacion_horario.get(i).getFecha_nota_ref() + "'";
                }

                cadenasql = "insert into comeval_ampliacion_horario ("
                        + "id_comeval_ampliacion_horario, "
                        + "personal, "
                        + "id_tipo_ampliacion_horario, "
                        + "id_plaza_temporal, " 
                        + "id_plaza_indefinido, "
                        + "nota_ref, "
                        + "fecha_nota_ref, "
                        + "usuario, "
                        + "fecha_ingreso, "
                        + "id_estado_solicitud, "
                        + "id_tipo_solicitud, "
                        + "rechazado, "
                        + "id_estado_solicitud_rechazado, "
                        + "id_tipo_solicitud_rechazado, "
                        + "visto_bueno_director_escuela, "
                        + "visto_bueno_secretario_academico) values ("
                        + id_comeval_ampliacion_horario + ",'"
                        + lst_comeval_ampliacion_horario.get(i).getPersonal() + "',"
                        + lst_comeval_ampliacion_horario.get(i).getId_tipo_ampliacion_horario() + ","
                        + lst_comeval_ampliacion_horario.get(i).getId_plaza_temporal() + ","
                        + lst_comeval_ampliacion_horario.get(i).getId_plaza_indefinido() + ","
                        + nota_ref_temp + ","
                        + fecha_nota_ref_temp + ",'"
                        + lst_comeval_ampliacion_horario.get(i).getUsuario() + "','"
                        + lst_comeval_ampliacion_horario.get(i).getFecha_ingreso() + "',"
                        + lst_comeval_ampliacion_horario.get(i).getId_estado_solicitud() + ","
                        + lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud() + "',"
                        + lst_comeval_ampliacion_horario.get(i).getRechazado() + ","
                        + "null" + ","
                        + "null" + ","
                        + lst_comeval_ampliacion_horario.get(i).getVisto_bueno_director_escuela() + ","
                        + lst_comeval_ampliacion_horario.get(i).getVisto_bueno_secretario_academico() + ")";
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
                            + (j + 1) + ",'"
                            + lst_comeval_ampliacion_horario.get(i).getLst_horario_plaza().get(j).getHorainicio() + "','"
                            + lst_comeval_ampliacion_horario.get(i).getLst_horario_plaza().get(j).getHorafin() + "','"
                            + lst_comeval_ampliacion_horario.get(i).getLst_horario_plaza().get(j).getDias() + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }

                for (Integer j = 0; j < lst_comeval_ampliacion_horario.get(i).getLst_observaciones().size(); j++) {
                    cadenasql = "insert into comeval_solicitud_observacion ("
                            + "id_solicitud, "
                            + "id_tipo_solicitud, "
                            + "id_observacion, "
                            + "dependencia, "
                            + "fecha_hora, "
                            + "observacion) values ("
                            + id_comeval_ampliacion_horario + ","
                            + lst_comeval_ampliacion_horario.get(i).getLst_observaciones().get(j).getId_tipo_solicitud() + ","
                            + lst_comeval_ampliacion_horario.get(i).getLst_observaciones().get(j).getId_observacion() + ",'"
                            + lst_comeval_ampliacion_horario.get(i).getLst_observaciones().get(j).getDependencia() + "','"
                            + lst_comeval_ampliacion_horario.get(i).getLst_observaciones().get(j).getFecha_hora() + "','"
                            + lst_comeval_ampliacion_horario.get(i).getLst_observaciones().get(j).getObservacion() + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }

                Long max_id_workflow = Long.parseLong("0");
                cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + id_comeval_ampliacion_horario;
                stmt = this.conn.createStatement();
                rs = stmt.executeQuery(cadenasql);
                while (rs.next()) {
                    max_id_workflow = rs.getLong(1);
                }
                rs.close();
                stmt.close();

                Solicitud_Workflow_Historial solicitud_workflow_historial = new Solicitud_Workflow_Historial();
                solicitud_workflow_historial.setId_solicitud(id_comeval_ampliacion_horario);
                solicitud_workflow_historial.setId_estado_solicitud(lst_comeval_ampliacion_horario.get(i).getId_estado_solicitud());
                solicitud_workflow_historial.setId_tipo_solicitud(lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud());
                solicitud_workflow_historial.setId_workflow(max_id_workflow);
                solicitud_workflow_historial.setUsuario(lst_comeval_ampliacion_horario.get(i).getUsuario());
                solicitud_workflow_historial.setFecha(lst_comeval_ampliacion_horario.get(i).getFecha_ingreso());
                solicitud_workflow_historial.setRechazado(lst_comeval_ampliacion_horario.get(i).getRechazado());
                solicitud_workflow_historial.setFecha_rechazado(lst_comeval_ampliacion_horario.get(i).getFecha_ingreso());

                cadenasql = "insert into solicitud_workflow_historial ("
                        + "id_solicitud, "
                        + "id_estado_solicitud, "
                        + "id_tipo_solicitud, "
                        + "id_workflow, "
                        + "usuario, "
                        + "fecha, "
                        + "rechazado, "
                        + "fecha_rechazado) values ("
                        + solicitud_workflow_historial.getId_solicitud() + ","
                        + solicitud_workflow_historial.getId_estado_solicitud() + ","
                        + solicitud_workflow_historial.getId_tipo_solicitud() + ","
                        + solicitud_workflow_historial.getId_workflow() + ",'"
                        + solicitud_workflow_historial.getUsuario() + "','"
                        + solicitud_workflow_historial.getFecha() + "',"
                        + solicitud_workflow_historial.getRechazado() + ","
                        + "null" + ")";
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();
            }

            this.conn.commit();
            this.conn.setAutoCommit(true);

            resultado = "0,Solicitud Ampliación de Horario ingresada correctamente.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_ingresar):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_ingresar):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_ingresar-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_ingresar-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String ampliacion_horario_modificar(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Ampliacion_Horario>>() {
            }.getType();
            List<Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_ampliacion_horario.size(); i++) {
                String nota_ref_temp;
                if (lst_comeval_ampliacion_horario.get(i).getNota_ref().equals("-")) {
                    nota_ref_temp = "null";
                } else {
                    nota_ref_temp = "'" + lst_comeval_ampliacion_horario.get(i).getNota_ref() + "'";
                }

                String fecha_nota_ref_temp;
                if (lst_comeval_ampliacion_horario.get(i).getFecha_nota_ref().equals("-")) {
                    fecha_nota_ref_temp = "null";
                } else {
                    fecha_nota_ref_temp = "'" + lst_comeval_ampliacion_horario.get(i).getFecha_nota_ref() + "'";
                }

                String id_estado_solicitud_rechazado_temp;
                String id_tipo_solicitud_rechazado_temp;
                if (lst_comeval_ampliacion_horario.get(i).getRechazado() == Long.parseLong("1")) {
                    id_estado_solicitud_rechazado_temp = lst_comeval_ampliacion_horario.get(i).getId_estado_solicitud_rechazado().toString();
                    id_tipo_solicitud_rechazado_temp = lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud_rechazado().toString();
                } else {
                    id_estado_solicitud_rechazado_temp = "null";
                    id_tipo_solicitud_rechazado_temp = "null";
                }

                String cadenasql = "update comeval_ampliacion_horario set "
                        + "id_tipo_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_tipo_ampliacion_horario() + ", "
                        + "id_plaza_temporal=" + lst_comeval_ampliacion_horario.get(i).getId_plaza_temporal() + ", "
                        + "id_plaza_indefinido=" + lst_comeval_ampliacion_horario.get(i).getId_plaza_indefinido() + ", "
                        + "nota_ref=" + nota_ref_temp + ", "
                        + "fecha_nota_ref=" + fecha_nota_ref_temp + ", "
                        + "usuario='" + lst_comeval_ampliacion_horario.get(i).getUsuario() + "', "
                        + "rechazado=" + lst_comeval_ampliacion_horario.get(i).getRechazado() + ", "
                        + "id_estado_solicitud_rechazado=" + id_estado_solicitud_rechazado_temp + ", "
                        + "id_tipo_solicitud_rechazado=" + id_tipo_solicitud_rechazado_temp + ", "
                        + "visto_bueno_director_escuela=" + lst_comeval_ampliacion_horario.get(i).getVisto_bueno_director_escuela() + ", "
                        + "visto_bueno_secretario_academico=" + lst_comeval_ampliacion_horario.get(i).getVisto_bueno_secretario_academico() + " "
                        + "where "
                        + "id_comeval_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                Statement stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                cadenasql = "delete from comeval_ampliacion_horario_plaza where id_comeval_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                for (Integer j = 0; j < lst_comeval_ampliacion_horario.get(i).getLst_observaciones().size(); j++) {
                    cadenasql = "insert into comeval_solicitud_observacion ("
                            + "id_solicitud, "
                            + "id_tipo_solicitud, "
                            + "id_observacion, "
                            + "dependencia, "
                            + "fecha_hora, "
                            + "observacion) values ("
                            + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario() + ","
                            + lst_comeval_ampliacion_horario.get(i).getLst_observaciones().get(j).getId_tipo_solicitud() + ","
                            + lst_comeval_ampliacion_horario.get(i).getLst_observaciones().get(j).getId_observacion() + ",'"
                            + lst_comeval_ampliacion_horario.get(i).getLst_observaciones().get(j).getDependencia() + "','"
                            + lst_comeval_ampliacion_horario.get(i).getLst_observaciones().get(j).getFecha_hora() + "','"
                            + lst_comeval_ampliacion_horario.get(i).getLst_observaciones().get(j).getObservacion() + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }

                cadenasql = "delete from comeval_solicitud_observacion "
                        + "where id_solicitud=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario() + " and "
                        + "id_tipo_solicitud=" + lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud();
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                for (Integer j = 0; j < lst_comeval_ampliacion_horario.get(i).getLst_observaciones().size(); j++) {
                    cadenasql = "insert into comeval_solicitud_observacion ("
                            + "id_solicitud, "
                            + "id_tipo_solicitud, "
                            + "id_observacion, "
                            + "dependencia, "
                            + "fecha_hora, "
                            + "observacion) values ("
                            + lst_comeval_ampliacion_horario.get(i).getLst_observaciones().get(j).getId_solicitud() + ","
                            + lst_comeval_ampliacion_horario.get(i).getLst_observaciones().get(j).getId_tipo_solicitud() + ","
                            + lst_comeval_ampliacion_horario.get(i).getLst_observaciones().get(j).getId_observacion() + ",'"
                            + lst_comeval_ampliacion_horario.get(i).getLst_observaciones().get(j).getDependencia() + "','"
                            + lst_comeval_ampliacion_horario.get(i).getLst_observaciones().get(j).getFecha_hora() + "','"
                            + lst_comeval_ampliacion_horario.get(i).getLst_observaciones().get(j).getObservacion() + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }
            }

            this.conn.commit();
            this.conn.setAutoCommit(true);

            resultado = "0,Solicitud Ampliación de Horario modificada correctamente.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_modificar):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_modificar):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_modificar-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_modificar-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String ampliacion_horario_enviar_ingreso_docente(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Ampliacion_Horario>>() {
            }.getType();
            List<Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_ampliacion_horario.size(); i++) {
                Boolean validado = true;
                String mensaje_no_valido = "";

                // CAMBIA LA SOLICITUD AL SIGUIENTE ESTADO.
                if (validado) {
                    Long id_estado_solicitud_siguiente = Long.parseLong("0");
                    Long id_tipo_solicitud_siguiente = Long.parseLong("0");

                    String cadenasql = "select "
                            + "ws.id_estado_solicitud_siguiente, "
                            + "ws.id_tipo_solicitud_siguiente "
                            + "from "
                            + "workflow_solicitud ws "
                            + "where "
                            + "ws.id_estado_solicitud_actual=" + lst_comeval_ampliacion_horario.get(i).getId_estado_solicitud() + " and "
                            + "ws.id_tipo_solicitud_actual=" + lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud();
                    Statement stmt = this.conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    while (rs.next()) {
                        id_estado_solicitud_siguiente = rs.getLong(1);
                        id_tipo_solicitud_siguiente = rs.getLong(2);
                    }
                    rs.close();
                    stmt.close();

                    if (!id_estado_solicitud_siguiente.equals(Long.parseLong("0")) && !id_tipo_solicitud_siguiente.equals(Long.parseLong("0"))) {
                        cadenasql = "update comeval_ampliacion_horario set "
                                + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                + "usuario='" + lst_comeval_ampliacion_horario.get(i).getUsuario() + "' "
                                + "where id_comeval_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                        stmt = this.conn.createStatement();
                        stmt.executeUpdate(cadenasql);
                        stmt.close();

                        Long max_id_workflow = Long.parseLong("0");
                        cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        while (rs.next()) {
                            max_id_workflow = rs.getLong(1);
                        }
                        rs.close();
                        stmt.close();

                        Date fecha_actual = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        cadenasql = "insert into solicitud_workflow_historial ("
                                + "id_solicitud, "
                                + "id_estado_solicitud, "
                                + "id_tipo_solicitud, "
                                + "id_workflow, "
                                + "usuario, "
                                + "fecha, "
                                + "rechazado, "
                                + "fecha_rechazado) values ("
                                + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario() + ", "
                                + id_estado_solicitud_siguiente + ","
                                + id_tipo_solicitud_siguiente + ","
                                + max_id_workflow + ",'"
                                + lst_comeval_ampliacion_horario.get(i).getUsuario() + "','"
                                + dateFormat.format(fecha_actual) + "',"
                                + "0" + ","
                                + "null" + ")";
                        stmt = this.conn.createStatement();
                        stmt.executeUpdate(cadenasql);
                        stmt.close();

                        resultado = "0,Solicitud Ampliación de Horario enviada al siguiente estado correctamente.";
                    } else {
                        resultado = "1,No existe estado siguiente para la solicitud Ampliación de Horario.";
                    }
                } else {
                    resultado = "1," + mensaje_no_valido;
                }
            }

            this.conn.commit();
            this.conn.setAutoCommit(true);
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_ingreso_docente):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_ingreso_docente):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_ingreso_docente-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_ingreso_docente-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String ampliacion_horario_enviar_visto_bueno_escuela(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Ampliacion_Horario>>() {
            }.getType();
            List<Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_ampliacion_horario.size(); i++) {
                if (lst_comeval_ampliacion_horario.get(i).getRechazado() == Long.parseLong("1")) {
                    String cadenasql = "update comeval_ampliacion_horario set "
                            + "id_estado_solicitud=" + lst_comeval_ampliacion_horario.get(i).getId_estado_solicitud_rechazado() + ", "
                            + "id_tipo_solicitud=" + lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud_rechazado() + ", "
                            + "usuario='" + lst_comeval_ampliacion_horario.get(i).getUsuario() + "', "
                            + "rechazado=0, "
                            + "id_estado_solicitud_rechazado=null, "
                            + "id_tipo_solicitud_rechazado=null "
                            + "where id_comeval_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    Long max_id_workflow = Long.parseLong("0");
                    cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                    stmt = this.conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    while (rs.next()) {
                        max_id_workflow = rs.getLong(1);
                    }
                    rs.close();
                    stmt.close();

                    Date fecha_actual = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    cadenasql = "insert into solicitud_workflow_historial ("
                            + "id_solicitud, "
                            + "id_estado_solicitud, "
                            + "id_tipo_solicitud, "
                            + "id_workflow, "
                            + "usuario, "
                            + "fecha, "
                            + "rechazado, "
                            + "fecha_rechazado) values ("
                            + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario() + ","
                            + lst_comeval_ampliacion_horario.get(i).getId_estado_solicitud_rechazado() + ","
                            + lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud_rechazado() + ","
                            + max_id_workflow + ",'"
                            + lst_comeval_ampliacion_horario.get(i).getUsuario() + "','"
                            + dateFormat.format(fecha_actual) + "',"
                            + lst_comeval_ampliacion_horario.get(i).getRechazado() + ",'"
                            + dateFormat.format(fecha_actual) + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    resultado = "0,Solicitud Ampliación Horario rechazada correctamente.";
                } else {
                    Boolean validado = false;
                    String mensaje_no_valido = "";

                    // VALIDA QUE SE HAYA MARCADO LA CASILLA VISTO BUENO ESCUELA.
                    String cadenasql = "select cch.visto_bueno_director_escuela "
                            + "from comeval_ampliacion_horario cch "
                            + "where "
                            + "cch.id_comeval_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    Integer visto_bueno_director_escuela = 0;
                    while (rs.next()) {
                        visto_bueno_director_escuela = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (visto_bueno_director_escuela == 0) {
                        validado = false;
                        mensaje_no_valido = "Debe marcar la casilla Visto_Bueno (Escuela) en el formulario de la solicitud Ampliación Horario.";
                    } else {
                        validado = true;
                    }

                    // CAMBIA LA SOLICITUD AL SIGUIENTE ESTADO.
                    if (validado) {
                        Long id_estado_solicitud_siguiente = Long.parseLong("0");
                        Long id_tipo_solicitud_siguiente = Long.parseLong("0");

                        cadenasql = "select "
                                + "ws.id_estado_solicitud_siguiente, "
                                + "ws.id_tipo_solicitud_siguiente "
                                + "from "
                                + "workflow_solicitud ws "
                                + "where "
                                + "ws.id_estado_solicitud_actual=" + lst_comeval_ampliacion_horario.get(i).getId_estado_solicitud() + " and "
                                + "ws.id_tipo_solicitud_actual=" + lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud();
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        while (rs.next()) {
                            id_estado_solicitud_siguiente = rs.getLong(1);
                            id_tipo_solicitud_siguiente = rs.getLong(2);
                        }
                        rs.close();
                        stmt.close();

                        if (!id_estado_solicitud_siguiente.equals(Long.parseLong("0")) && !id_tipo_solicitud_siguiente.equals(Long.parseLong("0"))) {
                            cadenasql = "update comeval_ampliacion_horario set "
                                    + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                    + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                    + "usuario='" + lst_comeval_ampliacion_horario.get(i).getUsuario() + "' "
                                    + "where id_comeval_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            Long max_id_workflow = Long.parseLong("0");
                            cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                            stmt = this.conn.createStatement();
                            rs = stmt.executeQuery(cadenasql);
                            while (rs.next()) {
                                max_id_workflow = rs.getLong(1);
                            }
                            rs.close();
                            stmt.close();

                            Date fecha_actual = new Date();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            cadenasql = "insert into solicitud_workflow_historial ("
                                    + "id_solicitud, "
                                    + "id_estado_solicitud, "
                                    + "id_tipo_solicitud, "
                                    + "id_workflow, "
                                    + "usuario, "
                                    + "fecha, "
                                    + "rechazado, "
                                    + "fecha_rechazado) values ("
                                    + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario() + ", "
                                    + id_estado_solicitud_siguiente + ","
                                    + id_tipo_solicitud_siguiente + ","
                                    + max_id_workflow + ",'"
                                    + lst_comeval_ampliacion_horario.get(i).getUsuario() + "','"
                                    + dateFormat.format(fecha_actual) + "',"
                                    + "0" + ","
                                    + "null" + ")";
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            resultado = "0,Solicitud Ampliación Horario enviada al siguiente estado correctamente.";
                        } else {
                            resultado = "1,No existe estado siguiente para la solicitud Ampliación Horario.";
                        }
                    } else {
                        resultado = "1," + mensaje_no_valido;
                    }
                }
            }

            this.conn.commit();
            this.conn.setAutoCommit(true);
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_visto_bueno_escuela):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_visto_bueno_escuela):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_visto_bueno_escuela-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_visto_bueno_escuela-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String ampliacion_horario_enviar_visto_bueno_secretario(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Ampliacion_Horario>>() {
            }.getType();
            List<Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_ampliacion_horario.size(); i++) {
                if (lst_comeval_ampliacion_horario.get(i).getRechazado() == Long.parseLong("1")) {
                    String cadenasql = "update comeval_ampliacion_horario set "
                            + "id_estado_solicitud=" + lst_comeval_ampliacion_horario.get(i).getId_estado_solicitud_rechazado() + ", "
                            + "id_tipo_solicitud=" + lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud_rechazado() + ", "
                            + "usuario='" + lst_comeval_ampliacion_horario.get(i).getUsuario() + "', "
                            + "rechazado=0, "
                            + "id_estado_solicitud_rechazado=null, "
                            + "id_tipo_solicitud_rechazado=null "
                            + "where id_comeval_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    Long max_id_workflow = Long.parseLong("0");
                    cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                    stmt = this.conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    while (rs.next()) {
                        max_id_workflow = rs.getLong(1);
                    }
                    rs.close();
                    stmt.close();

                    Date fecha_actual = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    cadenasql = "insert into solicitud_workflow_historial ("
                            + "id_solicitud, "
                            + "id_estado_solicitud, "
                            + "id_tipo_solicitud, "
                            + "id_workflow, "
                            + "usuario, "
                            + "fecha, "
                            + "rechazado, "
                            + "fecha_rechazado) values ("
                            + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario() + ","
                            + lst_comeval_ampliacion_horario.get(i).getId_estado_solicitud_rechazado() + ","
                            + lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud_rechazado() + ","
                            + max_id_workflow + ",'"
                            + lst_comeval_ampliacion_horario.get(i).getUsuario() + "','"
                            + dateFormat.format(fecha_actual) + "',"
                            + lst_comeval_ampliacion_horario.get(i).getRechazado() + ",'"
                            + dateFormat.format(fecha_actual) + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    resultado = "0,Solicitud Ampliación Horario rechazada correctamente.";
                } else {
                    Boolean validado = false;
                    String mensaje_no_valido = "";

                    // VALIDA QUE SE HAYA MARCADO LA CASILLA VISTO BUENO ESCUELA.
                    String cadenasql = "select cch.visto_bueno_director_escuela "
                            + "from comeval_ampliacion_horario cch "
                            + "where "
                            + "cch.id_comeval_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    Integer visto_bueno_director_escuela = 0;
                    while (rs.next()) {
                        visto_bueno_director_escuela = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (visto_bueno_director_escuela == 0) {
                        validado = false;
                        mensaje_no_valido = "Debe marcar la casilla Visto_Bueno (Escuela) en el formulario de la solicitud Ampliación Horario.";
                    } else {
                        validado = true;
                    }

                    // VALIDA QUE SE HAYA MARCADO LA CASILLA VISTO BUENO SECRETARIO.
                    if (validado) {
                        cadenasql = "select cch.visto_bueno_secretario_academico "
                                + "from comeval_ampliacion_horario cch "
                                + "where "
                                + "cch.id_comeval_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        Integer visto_bueno_secretario_academico = 0;
                        while (rs.next()) {
                            visto_bueno_secretario_academico = rs.getInt(1);
                        }
                        rs.close();
                        stmt.close();

                        if (visto_bueno_secretario_academico == 0) {
                            validado = false;
                            mensaje_no_valido = "Debe marcar la casilla Visto_Bueno (Escuela) en el formulario de la solicitud Ampliación Horario.";
                        } else {
                            validado = true;
                        }
                    }

                    // INSERTAR SOLICITUD EN LA APLICACIÓN DE ACTAS DE JUNTA DIRECTIVA.
                    if (validado) {
                        Ctrl_Driver ctrl_driver1 = new Ctrl_Driver("jndi_gestionautenticacion2");

                        String nombre_solicitante = ctrl_driver1.nombre_usuario(lst_comeval_ampliacion_horario.get(i).getUsuario());

                        cadenasql = "select p.nombre "
                                + "from puesto p "
                                + "where p.puesto in (select t.puesto from titularidad t where t.personal='" + lst_comeval_ampliacion_horario.get(i).getUsuario() + "')";
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        String puesto_solicitante = "";
                        while (rs.next()) {
                            puesto_solicitante = rs.getString(1);
                        }
                        rs.close();
                        stmt.close();

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                        Calendar fecha_nota_ref = Calendar.getInstance();
                        fecha_nota_ref.setTime(dateFormat.parse(lst_comeval_ampliacion_horario.get(i).getFecha_nota_ref()));

                        Integer dia_fecha_nota_ref = fecha_nota_ref.get(Calendar.DATE);
                        Integer mes_fecha_nota_ref = fecha_nota_ref.get(Calendar.MONTH) + 1;
                        Integer anio_fecha_nota_ref = fecha_nota_ref.get(Calendar.YEAR);

                        String l_dia_fecha_nota_ref = "";
                        if (dia_fecha_nota_ref < 10) {
                            l_dia_fecha_nota_ref = "0" + dia_fecha_nota_ref.toString();
                        } else {
                            l_dia_fecha_nota_ref = dia_fecha_nota_ref.toString();
                        }

                        String l_mes_fecha_nota_ref = "";
                        switch (mes_fecha_nota_ref) {
                            case 1: {
                                l_mes_fecha_nota_ref = "enero";
                                break;
                            }
                            case 2: {
                                l_mes_fecha_nota_ref = "febrero";
                                break;
                            }
                            case 3: {
                                l_mes_fecha_nota_ref = "marzo";
                                break;
                            }
                            case 4: {
                                l_mes_fecha_nota_ref = "abril";
                                break;
                            }
                            case 5: {
                                l_mes_fecha_nota_ref = "mayo";
                                break;
                            }
                            case 6: {
                                l_mes_fecha_nota_ref = "junio";
                                break;
                            }
                            case 7: {
                                l_mes_fecha_nota_ref = "julio";
                                break;
                            }
                            case 8: {
                                l_mes_fecha_nota_ref = "agosto";
                                break;
                            }
                            case 9: {
                                l_mes_fecha_nota_ref = "septiembre";
                                break;
                            }
                            case 10: {
                                l_mes_fecha_nota_ref = "octubre";
                                break;
                            }
                            case 11: {
                                l_mes_fecha_nota_ref = "noviembre";
                                break;
                            }
                            case 12: {
                                l_mes_fecha_nota_ref = "diciembre";
                                break;
                            }
                        }

                        cadenasql = "select p.personal, trim(p.nombre) || ' ' || trim(p.apellido) nombre_solicitante "
                                + "from personal p "
                                + "where p.personal='" + lst_comeval_ampliacion_horario.get(i).getPersonal() + "'";
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        String nombre_docente = "";
                        while (rs.next()) {
                            nombre_docente = rs.getString(2);
                        }
                        rs.close();
                        stmt.close();

                        entidad.Solicitud_Acta solicitud_acta = new entidad.Solicitud_Acta();
                        solicitud_acta.setSolicitanteexterno("NOMBRE-SOLICITANTE: " + nombre_solicitante + ", CÓDIGO-PERSONAL: " + lst_comeval_ampliacion_horario.get(i).getUsuario());
                        solicitud_acta.setDescripcion("Se recibió nota de Ref. " + lst_comeval_ampliacion_horario.get(i).getNota_ref().trim() + " de fecha " + l_dia_fecha_nota_ref + " de " + l_mes_fecha_nota_ref + " de " + anio_fecha_nota_ref + ", "
                                + "enviada por el Ing(a). " + nombre_solicitante.trim() + ", " + puesto_solicitante.trim() + " de la Facultad de Ingeniería, "
                                + "quien presenta Solicitud Ampliación de Horario del docente del ingeniero/a " + nombre_docente + " con registro de personal No. " + lst_comeval_ampliacion_horario.get(i).getPersonal() + ", ");

                        String id_archivo_personal2 = "";
                        Ctrl_Driver ctrl_driver2 = new Ctrl_Driver("jndi_asuntosestudiantiles");
                        Long id_solicitud = ctrl_driver2.insertar_solictid_db_actas(solicitud_acta, lst_comeval_ampliacion_horario.get(i).getUsuario(), nombre_solicitante, id_archivo_personal2, Long.parseLong("31"));

                        if (id_solicitud > Long.parseLong("0")) {
                            cadenasql = "insert into comeval_acta_solicitud ("
                                    + "id_solicitud,"
                                    + "id_tipo_solicitud,"
                                    + "id_solicitud_acta,"
                                    + "no_acta,"
                                    + "anio_acta,"
                                    + "punto_acta,"
                                    + "inciso_acta,"
                                    + "fecha_acta,"
                                    + "resolucion_acta,"
                                    + "aprobado) values ("
                                    + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario() + ","
                                    + lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud() + ","
                                    + solicitud_acta.getId_solicitud() + ","
                                    + "null" + ","
                                    + "null" + ","
                                    + "null" + ","
                                    + "null" + ","
                                    + "null" + ","
                                    + "null" + ","
                                    + "null" + ")";
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();
                        } else {
                            mensaje_no_valido = "No se inserto la Solictud en la aplicación de Actas de Junta Directiva.";
                        }
                    }

                    // CAMBIA LA SOLICITUD AL SIGUIENTE ESTADO.
                    if (validado) {
                        Long id_estado_solicitud_siguiente = Long.parseLong("0");
                        Long id_tipo_solicitud_siguiente = Long.parseLong("0");

                        cadenasql = "select "
                                + "ws.id_estado_solicitud_siguiente, "
                                + "ws.id_tipo_solicitud_siguiente "
                                + "from "
                                + "workflow_solicitud ws "
                                + "where "
                                + "ws.id_estado_solicitud_actual=" + lst_comeval_ampliacion_horario.get(i).getId_estado_solicitud() + " and "
                                + "ws.id_tipo_solicitud_actual=" + lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud();
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        while (rs.next()) {
                            id_estado_solicitud_siguiente = rs.getLong(1);
                            id_tipo_solicitud_siguiente = rs.getLong(2);
                        }
                        rs.close();
                        stmt.close();

                        if (!id_estado_solicitud_siguiente.equals(Long.parseLong("0")) && !id_tipo_solicitud_siguiente.equals(Long.parseLong("0"))) {
                            cadenasql = "update comeval_ampliacion_horario set "
                                    + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                    + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                    + "usuario='" + lst_comeval_ampliacion_horario.get(i).getUsuario() + "' "
                                    + "where id_comeval_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            Long max_id_workflow = Long.parseLong("0");
                            cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                            stmt = this.conn.createStatement();
                            rs = stmt.executeQuery(cadenasql);
                            while (rs.next()) {
                                max_id_workflow = rs.getLong(1);
                            }
                            rs.close();
                            stmt.close();

                            Date fecha_actual = new Date();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            cadenasql = "insert into solicitud_workflow_historial ("
                                    + "id_solicitud, "
                                    + "id_estado_solicitud, "
                                    + "id_tipo_solicitud, "
                                    + "id_workflow, "
                                    + "usuario, "
                                    + "fecha, "
                                    + "rechazado, "
                                    + "fecha_rechazado) values ("
                                    + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario() + ", "
                                    + id_estado_solicitud_siguiente + ","
                                    + id_tipo_solicitud_siguiente + ","
                                    + max_id_workflow + ",'"
                                    + lst_comeval_ampliacion_horario.get(i).getUsuario() + "','"
                                    + dateFormat.format(fecha_actual) + "',"
                                    + "0" + ","
                                    + "null" + ")";
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            resultado = "0,Solicitud Ampliación Horario enviada al siguiente estado correctamente.";
                        } else {
                            resultado = "1,No existe estado siguiente para la solicitud Ampliación Horario.";
                        }
                    } else {
                        resultado = "1," + mensaje_no_valido;
                    }
                }
            }

            this.conn.commit();
            this.conn.setAutoCommit(true);
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_visto_bueno_secretario):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_visto_bueno_secretario):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_visto_bueno_secretario-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_visto_bueno_secretario-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }
    
    public String ampliacion_horario_enviar_notificacion_secretaria(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Ampliacion_Horario>>() {
            }.getType();
            List<Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_ampliacion_horario.size(); i++) {
                if (lst_comeval_ampliacion_horario.get(i).getRechazado() == Long.parseLong("1")) {
                    String cadenasql = "update comeval_ampliacion_horario set "
                            + "id_estado_solicitud=" + lst_comeval_ampliacion_horario.get(i).getId_estado_solicitud_rechazado() + ", "
                            + "id_tipo_solicitud=" + lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud_rechazado() + ", "
                            + "usuario='" + lst_comeval_ampliacion_horario.get(i).getUsuario() + "', "
                            + "rechazado=0, "
                            + "id_estado_solicitud_rechazado=null, "
                            + "id_tipo_solicitud_rechazado=null "
                            + "where id_comeval_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    Long max_id_workflow = Long.parseLong("0");
                    cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                    stmt = this.conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    while (rs.next()) {
                        max_id_workflow = rs.getLong(1);
                    }
                    rs.close();
                    stmt.close();

                    Date fecha_actual = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    cadenasql = "insert into solicitud_workflow_historial ("
                            + "id_solicitud, "
                            + "id_estado_solicitud, "
                            + "id_tipo_solicitud, "
                            + "id_workflow, "
                            + "usuario, "
                            + "fecha, "
                            + "rechazado, "
                            + "fecha_rechazado) values ("
                            + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario() + ","
                            + lst_comeval_ampliacion_horario.get(i).getId_estado_solicitud_rechazado() + ","
                            + lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud_rechazado() + ","
                            + max_id_workflow + ",'"
                            + lst_comeval_ampliacion_horario.get(i).getUsuario() + "','"
                            + dateFormat.format(fecha_actual) + "',"
                            + lst_comeval_ampliacion_horario.get(i).getRechazado() + ",'"
                            + dateFormat.format(fecha_actual) + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    resultado = "0,Solicitud Ampliación Horario rechazada correctamente.";
                } else {
                    Boolean validado = false;
                    String mensaje_no_valido = "";

                    // VALIDA QUE SE HAYA MARCADO LA CASILLA VISTO BUENO ESCUELA.
                    String cadenasql = "select cch.visto_bueno_director_escuela "
                            + "from comeval_ampliacion_horario cch "
                            + "where "
                            + "cch.id_comeval_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    Integer visto_bueno_director_escuela = 0;
                    while (rs.next()) {
                        visto_bueno_director_escuela = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (visto_bueno_director_escuela == 0) {
                        validado = false;
                        mensaje_no_valido = "Debe marcar la casilla Visto_Bueno (Escuela) en el formulario de la solicitud Ampliación Horario.";
                    } else {
                        validado = true;
                    }

                    // VALIDA QUE SE HAYA MARCADO LA CASILLA VISTO BUENO SECRETARIO.
                    if (validado) {
                        cadenasql = "select cch.visto_bueno_secretario_academico "
                                + "from comeval_ampliacion_horario cch "
                                + "where "
                                + "cch.id_comeval_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        Integer visto_bueno_secretario_academico = 0;
                        while (rs.next()) {
                            visto_bueno_secretario_academico = rs.getInt(1);
                        }
                        rs.close();
                        stmt.close();

                        if (visto_bueno_secretario_academico == 0) {
                            validado = false;
                            mensaje_no_valido = "Debe marcar la casilla Visto_Bueno (Escuela) en el formulario de la solicitud Ampliación Horario.";
                        } else {
                            validado = true;
                        }
                    }
                    
                    // VALIDA QUE SE HAYA MARCADO LA CASILLA NOTIFICACION TESORERIA.
                    if (validado) {
                        cadenasql = "select cch.notificacion_tesoreria "
                                + "from comeval_ampliacion_horario cch "
                                + "where "
                                + "cch.id_comeval_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        Integer notificacion_tesoreria = 0;
                        while (rs.next()) {
                            notificacion_tesoreria = rs.getInt(1);
                        }
                        rs.close();
                        stmt.close();

                        if (notificacion_tesoreria == 0) {
                            validado = false;
                            mensaje_no_valido = "Debe marcar la casilla Notificación Tesoreria en el formulario de la solicitud Ampliación Horario.";
                        } else {
                            validado = true;
                        }
                    }
                    
                    // CAMBIA LA SOLICITUD AL SIGUIENTE ESTADO.
                    if (validado) {
                        Long id_estado_solicitud_siguiente = Long.parseLong("0");
                        Long id_tipo_solicitud_siguiente = Long.parseLong("0");

                        cadenasql = "select "
                                + "ws.id_estado_solicitud_siguiente, "
                                + "ws.id_tipo_solicitud_siguiente "
                                + "from "
                                + "workflow_solicitud ws "
                                + "where "
                                + "ws.id_estado_solicitud_actual=" + lst_comeval_ampliacion_horario.get(i).getId_estado_solicitud() + " and "
                                + "ws.id_tipo_solicitud_actual=" + lst_comeval_ampliacion_horario.get(i).getId_tipo_solicitud();
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        while (rs.next()) {
                            id_estado_solicitud_siguiente = rs.getLong(1);
                            id_tipo_solicitud_siguiente = rs.getLong(2);
                        }
                        rs.close();
                        stmt.close();

                        if (!id_estado_solicitud_siguiente.equals(Long.parseLong("0")) && !id_tipo_solicitud_siguiente.equals(Long.parseLong("0"))) {
                            cadenasql = "update comeval_ampliacion_horario set "
                                    + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                    + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                    + "usuario='" + lst_comeval_ampliacion_horario.get(i).getUsuario() + "' "
                                    + "where id_comeval_ampliacion_horario=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            Long max_id_workflow = Long.parseLong("0");
                            cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario();
                            stmt = this.conn.createStatement();
                            rs = stmt.executeQuery(cadenasql);
                            while (rs.next()) {
                                max_id_workflow = rs.getLong(1);
                            }
                            rs.close();
                            stmt.close();

                            Date fecha_actual = new Date();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            cadenasql = "insert into solicitud_workflow_historial ("
                                    + "id_solicitud, "
                                    + "id_estado_solicitud, "
                                    + "id_tipo_solicitud, "
                                    + "id_workflow, "
                                    + "usuario, "
                                    + "fecha, "
                                    + "rechazado, "
                                    + "fecha_rechazado) values ("
                                    + lst_comeval_ampliacion_horario.get(i).getId_comeval_ampliacion_horario() + ", "
                                    + id_estado_solicitud_siguiente + ","
                                    + id_tipo_solicitud_siguiente + ","
                                    + max_id_workflow + ",'"
                                    + lst_comeval_ampliacion_horario.get(i).getUsuario() + "','"
                                    + dateFormat.format(fecha_actual) + "',"
                                    + "0" + ","
                                    + "null" + ")";
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            resultado = "0,Solicitud Ampliación Horario enviada al siguiente estado correctamente.";
                        } else {
                            resultado = "1,No existe estado siguiente para la solicitud Ampliación Horario.";
                        }
                    } else {
                        resultado = "1," + mensaje_no_valido;
                    }
                }
            }

            this.conn.commit();
            this.conn.setAutoCommit(true);
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_notificacion_secretaria):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_notificacion_secretaria):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_notificacion_secretaria-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_notificacion_secretaria-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String ampliacion_horario_acta_junta_directiva() {
        String resultado = "1,No se proceso ninguna solicitud.";

        try {
            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            List<entidad.Comeval_Acta_Solicitud> lst_comeval_acta_solicitud = new ArrayList<>();
            String cadenasql = "select "
                    + "cas.id_solicitud, "
                    + "cas.id_tipo_solicitud, "
                    + "cas.id_solicitud_acta "
                    + "from "
                    + "comeval_acta_solicitud cas "
                    + "where "
                    + "cas.id_tipo_solicitud=5 and "
                    + "cas.no_acta is null and "
                    + "cas.anio_acta is null and "
                    + "cas.punto_acta is null and "
                    + "cas.inciso_acta is null and "
                    + "cas.fecha_acta is null and "
                    + "cas.resolucion_acta is null";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(cadenasql);
            while (rs.next()) {
                entidad.Comeval_Acta_Solicitud comeval_acta_solicitud = new entidad.Comeval_Acta_Solicitud();
                comeval_acta_solicitud.setId_solicitud(rs.getLong(1));
                comeval_acta_solicitud.setId_tipo_solicitud(rs.getLong(2));
                comeval_acta_solicitud.setId_solicitud_acta(rs.getLong(3));
                lst_comeval_acta_solicitud.add(comeval_acta_solicitud);
            }
            rs.close();
            stmt.close();

            for (Integer i = 0; i < lst_comeval_acta_solicitud.size(); i++) {
                Ctrl_Driver ctrl_driver = new Ctrl_Driver("jndi_asuntosestudiantiles");
                entidad.Acuerdo_Acta acuerdo_acta = ctrl_driver.obtener_acta_resolucion(lst_comeval_acta_solicitud.get(i).getId_solicitud_acta());

                if (acuerdo_acta.getTiene_contenido()) { // SI NO TINE CONTENIDO NO SE REALIZA NINGUNA ACCIÓN.
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                    Integer aprobado_acuerdo = 0;
                    if (acuerdo_acta.getAprobado()) {
                        aprobado_acuerdo = 1;
                    } else {
                        aprobado_acuerdo = 0;
                        acuerdo_acta.setAcuerdo("SOLICITUD NO APROBADA!!!" + acuerdo_acta.getAcuerdo());
                    }

                    cadenasql = "update comeval_acta_solicitud set "
                            + "no_acta=" + acuerdo_acta.getNo_acta() + ", "
                            + "anio_acta=" + acuerdo_acta.getAnio_acta() + ", "
                            + "punto_acta=" + acuerdo_acta.getPunto_acta() + ", "
                            + "inciso_acta=" + acuerdo_acta.getInciso_acta() + ", "
                            + "fecha_acta='" + dateFormat.format(acuerdo_acta.getFecha_acta()) + "', "
                            + "resolucion_acta='" + acuerdo_acta.getAcuerdo() + "', "
                            + "aprobado=" + aprobado_acuerdo + " "
                            + "where "
                            + "id_solicitud=" + lst_comeval_acta_solicitud.get(i).getId_solicitud() + " and "
                            + "id_tipo_solicitud=" + lst_comeval_acta_solicitud.get(i).getId_tipo_solicitud() + " and "
                            + "id_solicitud_acta=" + lst_comeval_acta_solicitud.get(i).getId_solicitud_acta();
                    stmt = conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    // CAMBIO DE ESTADO DE LA SOLICITUD AMPLIACION HORARIO.
                    Long id_estado_solicitud_actual = Long.parseLong("0");
                    Long id_tipo_solicitud_actual = Long.parseLong("0");
                    String usuario = "";
                    cadenasql = "select "
                            + "cad.id_estado_solicitud, "
                            + "cad.id_tipo_solicitud, "
                            + "cad.usuario "
                            + "from "
                            + "comeval_ampliacion_horario cad "
                            + "where "
                            + "cad.id_comeval_ampliacion_horario=" + lst_comeval_acta_solicitud.get(i).getId_solicitud();
                    stmt = this.conn.createStatement();
                    rs = stmt.executeQuery(cadenasql);
                    while (rs.next()) {
                        id_estado_solicitud_actual = rs.getLong(1);
                        id_tipo_solicitud_actual = rs.getLong(2);
                        usuario = rs.getString(3);
                    }
                    rs.close();
                    stmt.close();

                    Long id_estado_solicitud_siguiente = Long.parseLong("0");
                    Long id_tipo_solicitud_siguiente = Long.parseLong("0");
                    cadenasql = "select "
                            + "ws.id_estado_solicitud_siguiente, "
                            + "ws.id_tipo_solicitud_siguiente "
                            + "from "
                            + "workflow_solicitud ws "
                            + "where "
                            + "ws.id_estado_solicitud_actual=" + id_estado_solicitud_actual + " and "
                            + "ws.id_tipo_solicitud_actual=" + id_tipo_solicitud_actual;
                    stmt = this.conn.createStatement();
                    rs = stmt.executeQuery(cadenasql);
                    while (rs.next()) {
                        id_estado_solicitud_siguiente = rs.getLong(1);
                        id_tipo_solicitud_siguiente = rs.getLong(2);
                    }
                    rs.close();
                    stmt.close();

                    if (!id_estado_solicitud_siguiente.equals(Long.parseLong("0")) && !id_tipo_solicitud_siguiente.equals(Long.parseLong("0"))) {
                        cadenasql = "update comeval_ampliacion_horario set "
                                + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                + "usuario='" + usuario + "' "
                                + "where id_comeval_ampliacion_horario=" + lst_comeval_acta_solicitud.get(i).getId_solicitud();
                        stmt = this.conn.createStatement();
                        stmt.executeUpdate(cadenasql);
                        stmt.close();

                        Long max_id_workflow = Long.parseLong("0");
                        cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_acta_solicitud.get(i).getId_solicitud();
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        while (rs.next()) {
                            max_id_workflow = rs.getLong(1);
                        }
                        rs.close();
                        stmt.close();

                        Date fecha_actual = new Date();

                        cadenasql = "insert into solicitud_workflow_historial ("
                                + "id_solicitud, "
                                + "id_estado_solicitud, "
                                + "id_tipo_solicitud, "
                                + "id_workflow, "
                                + "usuario, "
                                + "fecha, "
                                + "rechazado, "
                                + "fecha_rechazado) values ("
                                + lst_comeval_acta_solicitud.get(i).getId_solicitud() + ", "
                                + id_estado_solicitud_siguiente + ","
                                + id_tipo_solicitud_siguiente + ","
                                + max_id_workflow + ",'"
                                + usuario + "','"
                                + dateFormat.format(fecha_actual) + "',"
                                + "0" + ","
                                + "null" + ")";
                        stmt = this.conn.createStatement();
                        stmt.executeUpdate(cadenasql);
                        stmt.close();

                        resultado = "0,Solicitud Ampliación Horario enviada al siguiente estado correctamente.";
                    } else {
                        resultado = "1,No existe estado siguiente para la solicitud Ampliación Horario.";
                    }
                }
            }

            resultado = "0,Solicitudes procesadas.";

            this.conn.commit();
            this.conn.setAutoCommit(true);
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_acta_junta_directiva):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_acta_junta_directiva):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_acta_junta_directiva-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_acta_junta_directiva-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

}
