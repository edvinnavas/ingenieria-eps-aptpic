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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public String licencia_docente_ingresar(String jsonString) {
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
                        + "usuario, "
                        + "fecha_ingreso, "
                        + "id_estado_solicitud, "
                        + "id_tipo_solicitud, "
                        + "rechazado, "
                        + "id_estado_solicitud_rechazado, "
                        + "id_tipo_solicitud_rechazado, "
                        + "ingreso_siif_visto_bueno_escuela, "
                        + "tipo_licencia_secretario_academico, "
                        + "acuerdo_decanatura, "
                        + "notificacion_tesoreria) values ("
                        + id_comeval_licencia_docente + ",'"
                        + lst_comeval_licencia_docente.get(i).getPersonal() + "',"
                        + lst_comeval_licencia_docente.get(i).getId_motivo_licencia() + ","
                        + lst_comeval_licencia_docente.get(i).getId_tipo_licencia() + ",'"
                        + lst_comeval_licencia_docente.get(i).getGoce_sueldo() + "','"
                        + lst_comeval_licencia_docente.get(i).getFecha_inicio_licencia() + "','"
                        + lst_comeval_licencia_docente.get(i).getFecha_final_licencia() + "','"
                        + lst_comeval_licencia_docente.get(i).getUsuario() + "','"
                        + lst_comeval_licencia_docente.get(i).getFecha_ingreso() + "',"
                        + lst_comeval_licencia_docente.get(i).getId_estado_solicitud() + ","
                        + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud() + ","
                        + lst_comeval_licencia_docente.get(i).getRechazado() + ","
                        + "null" + ","
                        + "null" + ","
                        + lst_comeval_licencia_docente.get(i).getIngreso_siif_visto_bueno_escuela() + ","
                        + lst_comeval_licencia_docente.get(i).getTipo_licencia_secretario_academico() + ","
                        + lst_comeval_licencia_docente.get(i).getAcuerdo_decanatura() + ","
                        + lst_comeval_licencia_docente.get(i).getNotificacion_tesoreria() + ")";
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                for (Integer j = 0; j < lst_comeval_licencia_docente.get(i).getLst_plazas().size(); j++) {
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

                for (Integer j = 0; j < lst_comeval_licencia_docente.get(i).getLst_observaciones().size(); j++) {
                    cadenasql = "insert into comeval_solicitud_observacion ("
                            + "id_solicitud, "
                            + "id_tipo_solicitud, "
                            + "id_observacion, "
                            + "dependencia, "
                            + "fecha_hora, "
                            + "observacion) values ("
                            + id_comeval_licencia_docente + ","
                            + lst_comeval_licencia_docente.get(i).getLst_observaciones().get(j).getId_tipo_solicitud() + ","
                            + lst_comeval_licencia_docente.get(i).getLst_observaciones().get(j).getId_observacion() + ",'"
                            + lst_comeval_licencia_docente.get(i).getLst_observaciones().get(j).getDependencia() + "','"
                            + lst_comeval_licencia_docente.get(i).getLst_observaciones().get(j).getFecha_hora() + "','"
                            + lst_comeval_licencia_docente.get(i).getLst_observaciones().get(j).getObservacion() + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }

                Long max_id_workflow = Long.parseLong("0");
                cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + id_comeval_licencia_docente;
                stmt = this.conn.createStatement();
                rs = stmt.executeQuery(cadenasql);
                while (rs.next()) {
                    max_id_workflow = rs.getLong(1);
                }
                rs.close();
                stmt.close();

                Solicitud_Workflow_Historial solicitud_workflow_historial = new Solicitud_Workflow_Historial();
                solicitud_workflow_historial.setId_solicitud(id_comeval_licencia_docente);
                solicitud_workflow_historial.setId_estado_solicitud(lst_comeval_licencia_docente.get(i).getId_estado_solicitud());
                solicitud_workflow_historial.setId_tipo_solicitud(lst_comeval_licencia_docente.get(i).getId_tipo_solicitud());
                solicitud_workflow_historial.setId_workflow(max_id_workflow);
                solicitud_workflow_historial.setUsuario(lst_comeval_licencia_docente.get(i).getUsuario());
                solicitud_workflow_historial.setFecha(lst_comeval_licencia_docente.get(i).getFecha_ingreso());
                solicitud_workflow_historial.setRechazado(lst_comeval_licencia_docente.get(i).getRechazado());
                solicitud_workflow_historial.setFecha_rechazado(lst_comeval_licencia_docente.get(i).getFecha_ingreso());

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

            resultado = "0,Solicitud Licencia Docente ingresada correctamente.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_ingresar):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - licencia_docente_ingresar):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_ingresar-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - licencia_docente_ingresar-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String licencia_docente_modificar(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Licencia_Docente>>() {
            }.getType();
            List<Comeval_Licencia_Docente> lst_comeval_licencia_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_licencia_docente.size(); i++) {
                String id_estado_solicitud_rechazado_temp;
                String id_tipo_solicitud_rechazado_temp;
                if (lst_comeval_licencia_docente.get(i).getRechazado() == Long.parseLong("1")) {
                    id_estado_solicitud_rechazado_temp = lst_comeval_licencia_docente.get(i).getId_estado_solicitud_rechazado().toString();
                    id_tipo_solicitud_rechazado_temp = lst_comeval_licencia_docente.get(i).getId_tipo_solicitud_rechazado().toString();
                } else {
                    id_estado_solicitud_rechazado_temp = "null";
                    id_tipo_solicitud_rechazado_temp = "null";
                }
                
                String cadenasql = "update comeval_licencia_docente set "
                        + "personal='" + lst_comeval_licencia_docente.get(i).getPersonal() + "', "
                        + "id_motivo_licencia=" + lst_comeval_licencia_docente.get(i).getId_motivo_licencia() + ", "
                        + "id_tipo_licencia='" + lst_comeval_licencia_docente.get(i).getId_tipo_licencia() + "', "
                        + "goce_sueldo='" + lst_comeval_licencia_docente.get(i).getGoce_sueldo() + "', "
                        + "fecha_inicio_licencia='" + lst_comeval_licencia_docente.get(i).getFecha_inicio_licencia() + "', "
                        + "fecha_final_licencia='" + lst_comeval_licencia_docente.get(i).getFecha_final_licencia() + "', "
                        + "usuario='" + lst_comeval_licencia_docente.get(i).getUsuario() + "', "
                        + "rechazado=" + lst_comeval_licencia_docente.get(i).getRechazado() + ", "
                        + "id_estado_solicitud_rechazado=" + id_estado_solicitud_rechazado_temp + ", "
                        + "id_tipo_solicitud_rechazado=" + id_tipo_solicitud_rechazado_temp + ", "
                        + "ingreso_siif_visto_bueno_escuela=" + lst_comeval_licencia_docente.get(i).getIngreso_siif_visto_bueno_escuela() + ", "
                        + "tipo_licencia_secretario_academico=" + lst_comeval_licencia_docente.get(i).getTipo_licencia_secretario_academico() + ", "
                        + "acuerdo_decanatura=" + lst_comeval_licencia_docente.get(i).getAcuerdo_decanatura() + ", "
                        + "notificacion_tesoreria=" + lst_comeval_licencia_docente.get(i).getNotificacion_tesoreria() + " "
                        + "where "
                        + "id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                Statement stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                cadenasql = "delete from comeval_licencia_docente_plazas "
                        + "where id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                for (Integer j = 0; j < lst_comeval_licencia_docente.get(i).getLst_plazas().size(); j++) {
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

                cadenasql = "delete from comeval_solicitud_observacion "
                        + "where id_solicitud=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente() + " and "
                        + "id_tipo_solicitud=" + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud();
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                for (Integer j = 0; j < lst_comeval_licencia_docente.get(i).getLst_observaciones().size(); j++) {
                    cadenasql = "insert into comeval_solicitud_observacion ("
                            + "id_solicitud, "
                            + "id_tipo_solicitud, "
                            + "id_observacion, "
                            + "dependencia, "
                            + "fecha_hora, "
                            + "observacion) values ("
                            + lst_comeval_licencia_docente.get(i).getLst_observaciones().get(j).getId_solicitud() + ","
                            + lst_comeval_licencia_docente.get(i).getLst_observaciones().get(j).getId_tipo_solicitud() + ","
                            + lst_comeval_licencia_docente.get(i).getLst_observaciones().get(j).getId_observacion() + ",'"
                            + lst_comeval_licencia_docente.get(i).getLst_observaciones().get(j).getDependencia() + "','"
                            + lst_comeval_licencia_docente.get(i).getLst_observaciones().get(j).getFecha_hora() + "','"
                            + lst_comeval_licencia_docente.get(i).getLst_observaciones().get(j).getObservacion() + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }
            }

            this.conn.commit();
            this.conn.setAutoCommit(true);

            resultado = "0,Solicitud Licencia Docente modificada correctamente.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_modificar):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - licencia_docente_modificar):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_modificar-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - licencia_docente_modificar-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String licencia_docente_enviar_ingreso_docente(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Licencia_Docente>>() {
            }.getType();
            List<Comeval_Licencia_Docente> lst_comeval_licencia_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_licencia_docente.size(); i++) {
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
                            + "ws.id_estado_solicitud_actual=" + lst_comeval_licencia_docente.get(i).getId_estado_solicitud() + " and "
                            + "ws.id_tipo_solicitud_actual=" + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud();
                    Statement stmt = this.conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    while (rs.next()) {
                        id_estado_solicitud_siguiente = rs.getLong(1);
                        id_tipo_solicitud_siguiente = rs.getLong(2);
                    }
                    rs.close();
                    stmt.close();

                    if (!id_estado_solicitud_siguiente.equals(Long.parseLong("0")) && !id_tipo_solicitud_siguiente.equals(Long.parseLong("0"))) {
                        cadenasql = "update comeval_licencia_docente set "
                                + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                + "usuario='" + lst_comeval_licencia_docente.get(i).getUsuario() + "' "
                                + "where id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                        stmt = this.conn.createStatement();
                        stmt.executeUpdate(cadenasql);
                        stmt.close();

                        Long max_id_workflow = Long.parseLong("0");
                        cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
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
                                + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente() + ", "
                                + id_estado_solicitud_siguiente + ","
                                + id_tipo_solicitud_siguiente + ","
                                + max_id_workflow + ",'"
                                + lst_comeval_licencia_docente.get(i).getUsuario() + "','"
                                + dateFormat.format(fecha_actual) + "',"
                                + "0" + ","
                                + "null" + ")";
                        stmt = this.conn.createStatement();
                        stmt.executeUpdate(cadenasql);
                        stmt.close();

                        resultado = "0,Solicitud Licencia Docente enviada al siguiente estado correctamente.";
                    } else {
                        resultado = "1,No existe estado siguiente para la solicitud Licencia Docente.";
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

                System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_ingreso_docente):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_ingreso_docente):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_ingreso_docente-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_ingreso_docente-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String licencia_docente_enviar_ingreso_siif_visto_bueno_escuela(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Licencia_Docente>>() {
            }.getType();
            List<Comeval_Licencia_Docente> lst_comeval_licencia_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_licencia_docente.size(); i++) {
                if (lst_comeval_licencia_docente.get(i).getRechazado() == Long.parseLong("1")) {
                    String cadenasql = "update comeval_licencia_docente set "
                            + "id_estado_solicitud=" + lst_comeval_licencia_docente.get(i).getId_estado_solicitud_rechazado() + ", "
                            + "id_tipo_solicitud=" + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud_rechazado() + ", "
                            + "usuario='" + lst_comeval_licencia_docente.get(i).getUsuario() + "', "
                            + "rechazado=0, "
                            + "id_estado_solicitud_rechazado=null, "
                            + "id_tipo_solicitud_rechazado=null "
                            + "where id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    Long max_id_workflow = Long.parseLong("0");
                    cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
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
                            + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente() + ","
                            + lst_comeval_licencia_docente.get(i).getId_estado_solicitud_rechazado() + ","
                            + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud_rechazado() + ","
                            + max_id_workflow + ",'"
                            + lst_comeval_licencia_docente.get(i).getUsuario() + "','"
                            + dateFormat.format(fecha_actual) + "',"
                            + lst_comeval_licencia_docente.get(i).getRechazado() + ",'"
                            + dateFormat.format(fecha_actual) + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    resultado = "0,Solicitud Licencia Docente rechazada correctamente.";
                } else {
                    Boolean validado = false;
                    String mensaje_no_valido = "";

                    // VALIDA QUE SE HAYA MARCADO LA CASILLA REVISIÓN COMEVAL.
                    String cadenasql = "select cld.ingreso_siif_visto_bueno_escuela "
                            + "from comeval_licencia_docente cld "
                            + "where "
                            + "cld.id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    Integer ingreso_siif_visto_bueno_escuela = 0;
                    while (rs.next()) {
                        ingreso_siif_visto_bueno_escuela = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (ingreso_siif_visto_bueno_escuela == 0) {
                        validado = false;
                        mensaje_no_valido = "Debe marcar la casilla Ingreso_SIIF/Visto_Bueno (Escuela) en el formulario de la solicitud Licencia Docente.";
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
                                + "ws.id_estado_solicitud_actual=" + lst_comeval_licencia_docente.get(i).getId_estado_solicitud() + " and "
                                + "ws.id_tipo_solicitud_actual=" + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud();
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        while (rs.next()) {
                            id_estado_solicitud_siguiente = rs.getLong(1);
                            id_tipo_solicitud_siguiente = rs.getLong(2);
                        }
                        rs.close();
                        stmt.close();

                        if (!id_estado_solicitud_siguiente.equals(Long.parseLong("0")) && !id_tipo_solicitud_siguiente.equals(Long.parseLong("0"))) {
                            cadenasql = "update comeval_licencia_docente set "
                                    + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                    + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                    + "usuario='" + lst_comeval_licencia_docente.get(i).getUsuario() + "' "
                                    + "where id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            Long max_id_workflow = Long.parseLong("0");
                            cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
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
                                    + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente() + ", "
                                    + id_estado_solicitud_siguiente + ","
                                    + id_tipo_solicitud_siguiente + ","
                                    + max_id_workflow + ",'"
                                    + lst_comeval_licencia_docente.get(i).getUsuario() + "','"
                                    + dateFormat.format(fecha_actual) + "',"
                                    + "0" + ","
                                    + "null" + ")";
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            resultado = "0,Solicitud Licencia Docente enviada al siguiente estado correctamente.";
                        } else {
                            resultado = "1,No existe estado siguiente para la solicitud Licencia Docente.";
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

                System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_ingreso_siif_visto_bueno_escuela):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_ingreso_siif_visto_bueno_escuela):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_ingreso_siif_visto_bueno_escuela-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_ingreso_siif_visto_bueno_escuela-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String licencia_docente_enviar_tipo_licencia_secretario_academico(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Licencia_Docente>>() {
            }.getType();
            List<Comeval_Licencia_Docente> lst_comeval_licencia_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_licencia_docente.size(); i++) {
                if (lst_comeval_licencia_docente.get(i).getRechazado() == Long.parseLong("1")) {
                    String cadenasql = "update comeval_licencia_docente set "
                            + "id_estado_solicitud=" + lst_comeval_licencia_docente.get(i).getId_estado_solicitud_rechazado() + ", "
                            + "id_tipo_solicitud=" + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud_rechazado() + ", "
                            + "usuario='" + lst_comeval_licencia_docente.get(i).getUsuario() + "', "
                            + "rechazado=0, "
                            + "id_estado_solicitud_rechazado=null, "
                            + "id_tipo_solicitud_rechazado=null "
                            + "where id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    Long max_id_workflow = Long.parseLong("0");
                    cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
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
                            + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente() + ","
                            + lst_comeval_licencia_docente.get(i).getId_estado_solicitud_rechazado() + ","
                            + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud_rechazado() + ","
                            + max_id_workflow + ",'"
                            + lst_comeval_licencia_docente.get(i).getUsuario() + "','"
                            + dateFormat.format(fecha_actual) + "',"
                            + lst_comeval_licencia_docente.get(i).getRechazado() + ",'"
                            + dateFormat.format(fecha_actual) + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    resultado = "0,Solicitud Licencia Docente rechazada correctamente.";
                } else {
                    Boolean validado = false;
                    String mensaje_no_valido = "";

                    // VALIDA QUE SE HAYA MARCADO LA CASILLA INGRESO SIIF/VISTO BUENO (ESCUELA).
                    String cadenasql = "select cld.ingreso_siif_visto_bueno_escuela "
                            + "from comeval_licencia_docente cld "
                            + "where "
                            + "cld.id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    Integer ingreso_siif_visto_bueno_escuela = 0;
                    while (rs.next()) {
                        ingreso_siif_visto_bueno_escuela = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (ingreso_siif_visto_bueno_escuela == 0) {
                        validado = false;
                        mensaje_no_valido = "Debe marcar la casilla Ingreso_SIIF/Visto_Bueno (Escuela) en el formulario de la solicitud Licencia Docente.";
                    } else {
                        validado = true;
                    }

                    // VALIDA QUE SE HAYA MARCADO LA CASILLA TIPO LICENCIA (SECRETARIO ACADEMICO).
                    if (validado) {
                        cadenasql = "select cld.tipo_licencia_secretario_academico "
                                + "from comeval_licencia_docente cld "
                                + "where "
                                + "cld.id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        Integer tipo_licencia_secretario_academico = 0;
                        while (rs.next()) {
                            tipo_licencia_secretario_academico = rs.getInt(1);
                        }
                        rs.close();
                        stmt.close();

                        if (tipo_licencia_secretario_academico == 0) {
                            validado = false;
                            mensaje_no_valido = "Debe marcar la casilla Tipo Licencia (Secretario Académico) en el formulario de la solicitud Licencia Docente.";
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
                                + "ws.id_estado_solicitud_actual=" + lst_comeval_licencia_docente.get(i).getId_estado_solicitud() + " and "
                                + "ws.id_tipo_solicitud_actual=" + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud();
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        while (rs.next()) {
                            id_estado_solicitud_siguiente = rs.getLong(1);
                            id_tipo_solicitud_siguiente = rs.getLong(2);
                        }
                        rs.close();
                        stmt.close();

                        // SOLICITUD LICENCIA-DOCENTE -> ESTADO: {CALCULO DE DIAS DE LA SOLICITUD DE LICENCIA}.
                        // ESTADO-DECANATURA <= 60 DIAS, ESTADO-JUNTA-DIRECTIVA > 60 DIAS.
                        if (lst_comeval_licencia_docente.get(i).getId_estado_solicitud().equals(Long.parseLong("3"))) {
                            Integer dias_diferencia = 0;

                            cadenasql = "select date_part('day',t.fecha_final_licencia::timestamp - t.fecha_inicio_licencia::timestamp) "
                                    + "from comeval_licencia_docente t "
                                    + "where id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                            stmt = conn.createStatement();
                            rs = stmt.executeQuery(cadenasql);
                            while (rs.next()) {
                                dias_diferencia = rs.getInt(1);
                            }
                            rs.close();
                            stmt.close();

                            if (dias_diferencia < 61) {
                                id_estado_solicitud_siguiente = Long.parseLong("4");
                            } else {
                                id_estado_solicitud_siguiente = Long.parseLong("5");
                            }
                        }

                        // INSERTAR SOLICITUD EN LA APLICACIÓN DE ACTAS DE JUNTA DIRECTIVA.
                        if (id_estado_solicitud_siguiente.equals(Long.parseLong("5"))) {
                            Ctrl_Driver ctrl_driver1 = new Ctrl_Driver("jndi_gestionautenticacion2");
                            String nombre_solicitante = ctrl_driver1.nombre_usuario(lst_comeval_licencia_docente.get(i).getUsuario());

                            String goce_sueldo = "";
                            if (lst_comeval_licencia_docente.get(i).getGoce_sueldo().trim().equals("SI")) {
                                goce_sueldo = "con";
                            } else {
                                goce_sueldo = "sin";
                            }

                            cadenasql = "select p.personal, trim(p.nombre) || ' ' || trim(p.apellido) nombre_solicitante "
                                    + "from personal p "
                                    + "where p.personal='" + lst_comeval_licencia_docente.get(i).getPersonal() + "'";
                            stmt = this.conn.createStatement();
                            rs = stmt.executeQuery(cadenasql);
                            String nombre_docente = "";
                            while (rs.next()) {
                                nombre_docente = rs.getString(2);
                            }
                            rs.close();
                            stmt.close();

                            cadenasql = "select p.nombre "
                                    + "from puesto p "
                                    + "where p.puesto in (select t.puesto from titularidad t where t.personal='" + lst_comeval_licencia_docente.get(i).getPersonal() + "')";
                            stmt = this.conn.createStatement();
                            rs = stmt.executeQuery(cadenasql);
                            String puesto_actual = "";
                            while (rs.next()) {
                                puesto_actual = rs.getString(1);
                            }
                            rs.close();
                            stmt.close();

                            cadenasql = "select s.nombre "
                                    + "from subpartida s "
                                    + "where s.subpartida in (select p2.subpartida from plazapersonal p2 where p2.personal = '" + lst_comeval_licencia_docente.get(i).getPersonal() + "')";
                            stmt = this.conn.createStatement();
                            rs = stmt.executeQuery(cadenasql);
                            String nombre_escuela = "";
                            while (rs.next()) {
                                nombre_escuela = rs.getString(1);
                            }
                            rs.close();
                            stmt.close();

                            cadenasql = "select cldp.numero_plaza "
                                    + "from comeval_licencia_docente_plazas cldp "
                                    + "where cldp.id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                            stmt = this.conn.createStatement();
                            rs = stmt.executeQuery(cadenasql);
                            String numero_plaza = "";
                            while (rs.next()) {
                                numero_plaza = rs.getString(1);
                            }
                            rs.close();
                            stmt.close();

                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                            Calendar fecha_inicio_licencia = Calendar.getInstance();
                            fecha_inicio_licencia.setTime(dateFormat.parse(lst_comeval_licencia_docente.get(i).getFecha_inicio_licencia()));

                            Integer dia_fecha_inicio_licencia = fecha_inicio_licencia.get(Calendar.DATE);
                            Integer mes_fecha_inicio_licencia = fecha_inicio_licencia.get(Calendar.MONTH) + 1;
                            Integer anio_fecha_inicio_licencia = fecha_inicio_licencia.get(Calendar.YEAR);

                            String l_dia_fecha_inicio_licencia = "";
                            if (dia_fecha_inicio_licencia < 10) {
                                l_dia_fecha_inicio_licencia = "0" + dia_fecha_inicio_licencia.toString();
                            } else {
                                l_dia_fecha_inicio_licencia = dia_fecha_inicio_licencia.toString();
                            }

                            String l_mes_fecha_inicio_licencia = "";
                            switch (mes_fecha_inicio_licencia) {
                                case 1: {
                                    l_mes_fecha_inicio_licencia = "enero";
                                    break;
                                }
                                case 2: {
                                    l_mes_fecha_inicio_licencia = "febrero";
                                    break;
                                }
                                case 3: {
                                    l_mes_fecha_inicio_licencia = "marzo";
                                    break;
                                }
                                case 4: {
                                    l_mes_fecha_inicio_licencia = "abril";
                                    break;
                                }
                                case 5: {
                                    l_mes_fecha_inicio_licencia = "mayo";
                                    break;
                                }
                                case 6: {
                                    l_mes_fecha_inicio_licencia = "junio";
                                    break;
                                }
                                case 7: {
                                    l_mes_fecha_inicio_licencia = "julio";
                                    break;
                                }
                                case 8: {
                                    l_mes_fecha_inicio_licencia = "agosto";
                                    break;
                                }
                                case 9: {
                                    l_mes_fecha_inicio_licencia = "septiembre";
                                    break;
                                }
                                case 10: {
                                    l_mes_fecha_inicio_licencia = "octubre";
                                    break;
                                }
                                case 11: {
                                    l_mes_fecha_inicio_licencia = "noviembre";
                                    break;
                                }
                                case 12: {
                                    l_mes_fecha_inicio_licencia = "diciembre";
                                    break;
                                }
                            }

                            Calendar fecha_final_licencia = Calendar.getInstance();
                            fecha_final_licencia.setTime(dateFormat.parse(lst_comeval_licencia_docente.get(i).getFecha_final_licencia()));

                            Integer dia_fecha_final_licencia = fecha_final_licencia.get(Calendar.DATE);
                            Integer mes_fecha_final_licencia = fecha_final_licencia.get(Calendar.MONTH) + 1;
                            Integer anio_fecha_final_licencia = fecha_final_licencia.get(Calendar.YEAR);

                            String l_dia_fecha_final_licencia = "";
                            if (dia_fecha_final_licencia < 10) {
                                l_dia_fecha_final_licencia = "0" + dia_fecha_final_licencia.toString();
                            } else {
                                l_dia_fecha_final_licencia = dia_fecha_final_licencia.toString();
                            }

                            String l_mes_fecha_final_licencia = "";
                            switch (mes_fecha_final_licencia) {
                                case 1: {
                                    l_mes_fecha_final_licencia = "enero";
                                    break;
                                }
                                case 2: {
                                    l_mes_fecha_final_licencia = "febrero";
                                    break;
                                }
                                case 3: {
                                    l_mes_fecha_final_licencia = "marzo";
                                    break;
                                }
                                case 4: {
                                    l_mes_fecha_final_licencia = "abril";
                                    break;
                                }
                                case 5: {
                                    l_mes_fecha_final_licencia = "mayo";
                                    break;
                                }
                                case 6: {
                                    l_mes_fecha_final_licencia = "junio";
                                    break;
                                }
                                case 7: {
                                    l_mes_fecha_final_licencia = "julio";
                                    break;
                                }
                                case 8: {
                                    l_mes_fecha_final_licencia = "agosto";
                                    break;
                                }
                                case 9: {
                                    l_mes_fecha_final_licencia = "septiembre";
                                    break;
                                }
                                case 10: {
                                    l_mes_fecha_final_licencia = "octubre";
                                    break;
                                }
                                case 11: {
                                    l_mes_fecha_final_licencia = "noviembre";
                                    break;
                                }
                                case 12: {
                                    l_mes_fecha_final_licencia = "diciembre";
                                    break;
                                }
                            }

                            entidad.Solicitud_Acta solicitud_acta = new entidad.Solicitud_Acta();
                            solicitud_acta.setSolicitanteexterno("NOMBRE-SOLICITANTE: " + nombre_solicitante + ", CÓDIGO-PERSONAL: " + lst_comeval_licencia_docente.get(i).getUsuario());
                            solicitud_acta.setDescripcion("Se recibió solicitud de licencia " + goce_sueldo + " goce de sueldo del Ing(a). " + nombre_docente + ", "
                                    + "Profesor(a) " + puesto_actual + " de la " + nombre_escuela + ", registro de personal No. " + lst_comeval_licencia_docente.get(i).getPersonal()
                                    + ", plaza No. " + numero_plaza + ". Dicha licencia la solicita por tener asuntos personales que no le permiten atender su curso durante el presente semestre. "
                                    + "Dicha licencia tiene vigencia del " + l_dia_fecha_inicio_licencia + " de " + l_mes_fecha_inicio_licencia + " al "
                                    + l_dia_fecha_final_licencia + " de " + l_mes_fecha_final_licencia + " de " + anio_fecha_final_licencia + ".");

                            String id_archivo_personal2 = "";
                            Ctrl_Driver ctrl_driver2 = new Ctrl_Driver("jndi_asuntosestudiantiles");
                            Long id_solicitud = ctrl_driver2.insertar_solictid_db_actas(solicitud_acta, lst_comeval_licencia_docente.get(i).getUsuario(), nombre_solicitante, id_archivo_personal2, Long.parseLong("42"));

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
                                        + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente() + ","
                                        + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud() + ","
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

                        if (!id_estado_solicitud_siguiente.equals(Long.parseLong("0")) && !id_tipo_solicitud_siguiente.equals(Long.parseLong("0"))) {
                            cadenasql = "update comeval_licencia_docente set "
                                    + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                    + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                    + "usuario='" + lst_comeval_licencia_docente.get(i).getUsuario() + "' "
                                    + "where id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            Long max_id_workflow = Long.parseLong("0");
                            cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
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
                                    + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente() + ", "
                                    + id_estado_solicitud_siguiente + ","
                                    + id_tipo_solicitud_siguiente + ","
                                    + max_id_workflow + ",'"
                                    + lst_comeval_licencia_docente.get(i).getUsuario() + "','"
                                    + dateFormat.format(fecha_actual) + "',"
                                    + "0" + ","
                                    + "null" + ")";
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            resultado = "0,Solicitud Licencia Docente enviada al siguiente estado correctamente.";
                        } else {
                            resultado = "1,No existe estado siguiente para la solicitud Licencia Docente.";
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

                System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_tipo_licencia_secretario_academico):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_tipo_licencia_secretario_academico):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_tipo_licencia_secretario_academico-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_tipo_licencia_secretario_academico-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String licencia_docente_enviar_acuerdo_decanatura(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Licencia_Docente>>() {
            }.getType();
            List<Comeval_Licencia_Docente> lst_comeval_licencia_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_licencia_docente.size(); i++) {
                if (lst_comeval_licencia_docente.get(i).getRechazado() == Long.parseLong("1")) {
                    String cadenasql = "update comeval_licencia_docente set "
                            + "id_estado_solicitud=" + lst_comeval_licencia_docente.get(i).getId_estado_solicitud_rechazado() + ", "
                            + "id_tipo_solicitud=" + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud_rechazado() + ", "
                            + "usuario='" + lst_comeval_licencia_docente.get(i).getUsuario() + "', "
                            + "rechazado=0, "
                            + "id_estado_solicitud_rechazado=null, "
                            + "id_tipo_solicitud_rechazado=null "
                            + "where id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    Long max_id_workflow = Long.parseLong("0");
                    cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
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
                            + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente() + ","
                            + lst_comeval_licencia_docente.get(i).getId_estado_solicitud_rechazado() + ","
                            + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud_rechazado() + ","
                            + max_id_workflow + ",'"
                            + lst_comeval_licencia_docente.get(i).getUsuario() + "','"
                            + dateFormat.format(fecha_actual) + "',"
                            + lst_comeval_licencia_docente.get(i).getRechazado() + ",'"
                            + dateFormat.format(fecha_actual) + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    resultado = "0,Solicitud Licencia Docente rechazada correctamente.";
                } else {
                    Boolean validado = false;
                    String mensaje_no_valido = "";

                    // VALIDA QUE SE HAYA MARCADO LA CASILLA INGRESO SIIF/VISTO BUENO (ESCUELA).
                    String cadenasql = "select cld.ingreso_siif_visto_bueno_escuela "
                            + "from comeval_licencia_docente cld "
                            + "where "
                            + "cld.id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    Integer ingreso_siif_visto_bueno_escuela = 0;
                    while (rs.next()) {
                        ingreso_siif_visto_bueno_escuela = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (ingreso_siif_visto_bueno_escuela == 0) {
                        validado = false;
                        mensaje_no_valido = "Debe marcar la casilla Ingreso_SIIF/Visto_Bueno (Escuela) en el formulario de la solicitud Licencia Docente.";
                    } else {
                        validado = true;
                    }

                    // VALIDA QUE SE HAYA MARCADO LA CASILLA TIPO LICENCIA (SECRETARIO ACADEMICO).
                    if (validado) {
                        cadenasql = "select cld.tipo_licencia_secretario_academico "
                                + "from comeval_licencia_docente cld "
                                + "where "
                                + "cld.id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        Integer tipo_licencia_secretario_academico = 0;
                        while (rs.next()) {
                            tipo_licencia_secretario_academico = rs.getInt(1);
                        }
                        rs.close();
                        stmt.close();

                        if (tipo_licencia_secretario_academico == 0) {
                            validado = false;
                            mensaje_no_valido = "Debe marcar la casilla Tipo Licencia (Secretario Académico) en el formulario de la solicitud Licencia Docente.";
                        } else {
                            validado = true;
                        }
                    }

                    // VALIDA QUE SE HAYA MARCADO LA CASILLA ACUERDO DECANATURA.
                    if (validado) {
                        cadenasql = "select cld.acuerdo_decanatura "
                                + "from comeval_licencia_docente cld "
                                + "where "
                                + "cld.id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        Integer acuerdo_decanatura = 0;
                        while (rs.next()) {
                            acuerdo_decanatura = rs.getInt(1);
                        }
                        rs.close();
                        stmt.close();

                        if (acuerdo_decanatura == 0) {
                            validado = false;
                            mensaje_no_valido = "Debe marcar la casilla Acuerdo Decanatura en el formulario de la solicitud Licencia Docente.";
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
                                + "ws.id_estado_solicitud_actual=" + lst_comeval_licencia_docente.get(i).getId_estado_solicitud() + " and "
                                + "ws.id_tipo_solicitud_actual=" + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud();
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        while (rs.next()) {
                            id_estado_solicitud_siguiente = rs.getLong(1);
                            id_tipo_solicitud_siguiente = rs.getLong(2);
                        }
                        rs.close();
                        stmt.close();

                        if (!id_estado_solicitud_siguiente.equals(Long.parseLong("0")) && !id_tipo_solicitud_siguiente.equals(Long.parseLong("0"))) {
                            cadenasql = "update comeval_licencia_docente set "
                                    + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                    + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                    + "usuario='" + lst_comeval_licencia_docente.get(i).getUsuario() + "' "
                                    + "where id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            Long max_id_workflow = Long.parseLong("0");
                            cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
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
                                    + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente() + ", "
                                    + id_estado_solicitud_siguiente + ","
                                    + id_tipo_solicitud_siguiente + ","
                                    + max_id_workflow + ",'"
                                    + lst_comeval_licencia_docente.get(i).getUsuario() + "','"
                                    + dateFormat.format(fecha_actual) + "',"
                                    + "0" + ","
                                    + "null" + ")";
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            resultado = "0,Solicitud Licencia Docente enviada al siguiente estado correctamente.";
                        } else {
                            resultado = "1,No existe estado siguiente para la solicitud Licencia Docente.";
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

                System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_acuerdo_decanatura):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_acuerdo_decanatura):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_acuerdo_decanatura-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_acuerdo_decanatura-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String licencia_docente_enviar_notificacion_tesoreria(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Licencia_Docente>>() {
            }.getType();
            List<Comeval_Licencia_Docente> lst_comeval_licencia_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_licencia_docente.size(); i++) {
                if (lst_comeval_licencia_docente.get(i).getRechazado() == Long.parseLong("1")) {
                    String cadenasql = "update comeval_licencia_docente set "
                            + "id_estado_solicitud=" + lst_comeval_licencia_docente.get(i).getId_estado_solicitud_rechazado() + ", "
                            + "id_tipo_solicitud=" + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud_rechazado() + ", "
                            + "usuario='" + lst_comeval_licencia_docente.get(i).getUsuario() + "', "
                            + "rechazado=0, "
                            + "id_estado_solicitud_rechazado=null, "
                            + "id_tipo_solicitud_rechazado=null "
                            + "where id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    Long max_id_workflow = Long.parseLong("0");
                    cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
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
                            + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente() + ","
                            + lst_comeval_licencia_docente.get(i).getId_estado_solicitud_rechazado() + ","
                            + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud_rechazado() + ","
                            + max_id_workflow + ",'"
                            + lst_comeval_licencia_docente.get(i).getUsuario() + "','"
                            + dateFormat.format(fecha_actual) + "',"
                            + lst_comeval_licencia_docente.get(i).getRechazado() + ",'"
                            + dateFormat.format(fecha_actual) + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    resultado = "0,Solicitud Licencia Docente rechazada correctamente.";
                } else {
                    Boolean validado = false;
                    String mensaje_no_valido = "";

                    // VALIDA QUE SE HAYA MARCADO LA CASILLA INGRESO SIIF/VISTO BUENO (ESCUELA).
                    String cadenasql = "select cld.ingreso_siif_visto_bueno_escuela "
                            + "from comeval_licencia_docente cld "
                            + "where "
                            + "cld.id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    Integer ingreso_siif_visto_bueno_escuela = 0;
                    while (rs.next()) {
                        ingreso_siif_visto_bueno_escuela = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (ingreso_siif_visto_bueno_escuela == 0) {
                        validado = false;
                        mensaje_no_valido = "Debe marcar la casilla Ingreso_SIIF/Visto_Bueno (Escuela) en el formulario de la solicitud Licencia Docente.";
                    } else {
                        validado = true;
                    }

                    // VALIDA QUE SE HAYA MARCADO LA CASILLA TIPO LICENCIA (SECRETARIO ACADEMICO).
                    if (validado) {
                        cadenasql = "select cld.tipo_licencia_secretario_academico "
                                + "from comeval_licencia_docente cld "
                                + "where "
                                + "cld.id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        Integer tipo_licencia_secretario_academico = 0;
                        while (rs.next()) {
                            tipo_licencia_secretario_academico = rs.getInt(1);
                        }
                        rs.close();
                        stmt.close();

                        if (tipo_licencia_secretario_academico == 0) {
                            validado = false;
                            mensaje_no_valido = "Debe marcar la casilla Tipo Licencia (Secretario Académico) en el formulario de la solicitud Licencia Docente.";
                        } else {
                            validado = true;
                        }
                    }

                    // VALIDA QUE SE HAYA MARCADO LA CASILLA ACUERDO DECANATURA.
                    if (validado) {
                        cadenasql = "select cld.acuerdo_decanatura "
                                + "from comeval_licencia_docente cld "
                                + "where "
                                + "cld.id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        Integer acuerdo_decanatura = 0;
                        while (rs.next()) {
                            acuerdo_decanatura = rs.getInt(1);
                        }
                        rs.close();
                        stmt.close();

                        if (acuerdo_decanatura == 0) {
                            validado = false;
                            mensaje_no_valido = "Debe marcar la casilla Acuerdo Decanatura en el formulario de la solicitud Licencia Docente.";
                        } else {
                            validado = true;
                        }
                    }

                    // VALIDA QUE SE HAYA MARCADO LA CASILLA NOTIFICACION TESORERIA.
                    if (validado) {
                        cadenasql = "select cld.notificacion_tesoreria "
                                + "from comeval_licencia_docente cld "
                                + "where "
                                + "cld.id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
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
                            mensaje_no_valido = "Debe marcar la casilla Notificación Tesorería en el formulario de la solicitud Licencia Docente.";
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
                                + "ws.id_estado_solicitud_actual=" + lst_comeval_licencia_docente.get(i).getId_estado_solicitud() + " and "
                                + "ws.id_tipo_solicitud_actual=" + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud();
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        while (rs.next()) {
                            id_estado_solicitud_siguiente = rs.getLong(1);
                            id_tipo_solicitud_siguiente = rs.getLong(2);
                        }
                        rs.close();
                        stmt.close();

                        if (!id_estado_solicitud_siguiente.equals(Long.parseLong("0")) && !id_tipo_solicitud_siguiente.equals(Long.parseLong("0"))) {
                            cadenasql = "update comeval_licencia_docente set "
                                    + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                    + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                    + "usuario='" + lst_comeval_licencia_docente.get(i).getUsuario() + "' "
                                    + "where id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            Long max_id_workflow = Long.parseLong("0");
                            cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
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
                                    + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente() + ", "
                                    + id_estado_solicitud_siguiente + ","
                                    + id_tipo_solicitud_siguiente + ","
                                    + max_id_workflow + ",'"
                                    + lst_comeval_licencia_docente.get(i).getUsuario() + "','"
                                    + dateFormat.format(fecha_actual) + "',"
                                    + "0" + ","
                                    + "null" + ")";
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            // BUSCA NUMERO DE ACTA ASIGNADO EN JUNTA DIRECTIVA.
                            cadenasql = "select cas.no_acta, cas.aprobado "
                                    + "from comeval_acta_solicitud cas "
                                    + "where cas.id_tipo_solicitud=" + lst_comeval_licencia_docente.get(i).getId_tipo_solicitud() + " and "
                                    + "id_solicitud=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                            Long no_acta = Long.parseLong("0");
                            Long aprobado = Long.parseLong("0");
                            stmt = this.conn.createStatement();
                            rs = stmt.executeQuery(cadenasql);
                            while (rs.next()) {
                                no_acta = rs.getLong(1);
                                aprobado = rs.getLong(2);
                            }
                            rs.close();
                            stmt.close();

                            // SI LA LICENCIA FUE APROBADA VIA ACTA; ENTONCES, SE REGISTRA EN LA TABLA LICENCIA DB-PERSONAL2.
                            if (no_acta > Long.parseLong("0") && aprobado.equals(Long.parseLong("1"))) {
                                cadenasql = "select "
                                        + "cldp.plaza, "
                                        + "cldp.periodo, "
                                        + "cldp.anio, "
                                        + "cldp.subpartida, "
                                        + "cldp.renglon "
                                        + "from comeval_licencia_docente_plazas cldp "
                                        + "where cldp.id_comeval_licencia_docente=" + lst_comeval_licencia_docente.get(i).getId_comeval_licencia_docente();
                                Long plaza = Long.parseLong("0");
                                Long periodo = Long.parseLong("0");
                                Long anio = Long.parseLong("0");
                                String subpartida = "";
                                String renglon = "";
                                stmt = this.conn.createStatement();
                                rs = stmt.executeQuery(cadenasql);
                                while (rs.next()) {
                                    plaza = rs.getLong(1);
                                    periodo = rs.getLong(2);
                                    anio = rs.getLong(3);
                                    subpartida = rs.getString(4);
                                    renglon = rs.getString(5);
                                }
                                rs.close();
                                stmt.close();

                                Boolean bool_goce_sueldo = false;
                                if (lst_comeval_licencia_docente.get(i).getGoce_sueldo().trim().equals("SI")) {
                                    bool_goce_sueldo = true;
                                } else {
                                    bool_goce_sueldo = false;
                                }

                                cadenasql = "select s.nombre "
                                        + "from subpartida s "
                                        + "where s.subpartida in (select p2.subpartida from plazapersonal p2 where p2.personal = '" + lst_comeval_licencia_docente.get(i).getPersonal() + "')";
                                stmt = this.conn.createStatement();
                                rs = stmt.executeQuery(cadenasql);
                                String nombre_escuela = "";
                                while (rs.next()) {
                                    nombre_escuela = rs.getString(1);
                                }
                                rs.close();
                                stmt.close();

                                Long max_licencia = Long.parseLong("0");
                                cadenasql = "select coalesce(max(t.licencia) + 1, 1) max_id from licencia t";
                                stmt = this.conn.createStatement();
                                rs = stmt.executeQuery(cadenasql);
                                while (rs.next()) {
                                    max_licencia = rs.getLong(1);
                                }
                                rs.close();
                                stmt.close();

                                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                                cadenasql = "insert into licencia ("
                                        + "fechafin, "
                                        + "tipolicencia, "
                                        + "personal, "
                                        + "fechainicio, "
                                        + "acta, "
                                        + "periodo, "
                                        + "anio, "
                                        + "congocedesueldo, "
                                        + "plaza, "
                                        + "subpartida, "
                                        + "renglon, "
                                        + "motivo, "
                                        + "cargo, "
                                        + "ubicacion, "
                                        + "licencia) values ('"
                                        + dateFormat1.format(dateFormat.parse(lst_comeval_licencia_docente.get(i).getFecha_final_licencia())) + "',"
                                        + lst_comeval_licencia_docente.get(i).getId_tipo_licencia() + ",'"
                                        + lst_comeval_licencia_docente.get(i).getPersonal().trim() + "','"
                                        + dateFormat1.format(dateFormat.parse(lst_comeval_licencia_docente.get(i).getFecha_inicio_licencia())) + "',"
                                        + no_acta + ","
                                        + periodo + ","
                                        + anio + ","
                                        + bool_goce_sueldo + ","
                                        + plaza + ",'"
                                        + subpartida + "','"
                                        + renglon + "',"
                                        + lst_comeval_licencia_docente.get(i).getId_motivo_licencia() + ","
                                        + "null" + ",'"
                                        + nombre_escuela + "',"
                                        + max_licencia + ")";
                                stmt = this.conn.createStatement();
                                stmt.executeUpdate(cadenasql);
                                stmt.close();
                            }

                            resultado = "0,Solicitud Licencia Docente enviada al siguiente estado correctamente.";
                        } else {
                            resultado = "1,No existe estado siguiente para la solicitud Licencia Docente.";
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

                System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_acuerdo_decanatura):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_acuerdo_decanatura):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_acuerdo_decanatura-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_acuerdo_decanatura-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String licencia_docente_acta_junta_directiva() {
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
                    + "cas.id_tipo_solicitud=2 and "
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

                    // CAMBIO DE ESTADO DE LA SOLICITUD LICENCIA DOCENTE.
                    Long id_estado_solicitud_actual = Long.parseLong("0");
                    Long id_tipo_solicitud_actual = Long.parseLong("0");
                    String usuario = "";
                    cadenasql = "select "
                            + "cld.id_estado_solicitud, "
                            + "cld.id_tipo_solicitud, "
                            + "cld.usuario "
                            + "from "
                            + "comeval_licencia_docente cld "
                            + "where "
                            + "cld.id_comeval_licencia_docente=" + lst_comeval_acta_solicitud.get(i).getId_solicitud();
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
                        cadenasql = "update comeval_licencia_docente set "
                                + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                + "acuerdo_decanatura=" + "1" + ", "
                                + "usuario='" + usuario + "' "
                                + "where id_comeval_licencia_docente=" + lst_comeval_acta_solicitud.get(i).getId_solicitud();
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

                        resultado = "0,Solicitud Licencia Docente enviada al siguiente estado correctamente.";
                    } else {
                        resultado = "1,No existe estado siguiente para la solicitud Licencia Docente.";
                    }
                }
            }

            resultado = "0,Solicitudes procesadas.";

            this.conn.commit();
            this.conn.setAutoCommit(true);
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_acta_junta_directiva):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - licencia_docente_acta_junta_directiva):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_acta_junta_directiva-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - licencia_docente_acta_junta_directiva-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

}
