package control;

import entidad.Comeval_Promocion_Docente;
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

public class Ctrl_Comeval_Promocion_Docente implements Serializable {

    private static final long serialVersionUID = 1L;
    private String jndi_nombre;
    private Connection conn;

    public Ctrl_Comeval_Promocion_Docente() {
    }

    public Ctrl_Comeval_Promocion_Docente(String jndi_nombre) {
        try {
            this.jndi_nombre = jndi_nombre;
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - Ctrl_Comeval_Promocion_Docente):" + ex.toString());
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

    public String promocion_docente_ingresar(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Promocion_Docente>>() {
            }.getType();
            List<Comeval_Promocion_Docente> lst_comeval_promocion_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_promocion_docente.size(); i++) {
                Long id_comeval_promocion_docente = Long.parseLong("0");
                String cadenasql = "select coalesce(max(t.id_comeval_promocion_docente) + 1, 1) max_id_comeval_promocion_docente from comeval_promocion_docente t";
                Statement stmt = this.conn.createStatement();
                ResultSet rs = stmt.executeQuery(cadenasql);
                while (rs.next()) {
                    id_comeval_promocion_docente = rs.getLong(1);
                }
                rs.close();
                stmt.close();

                String fecha_promueve_temp;
                if (lst_comeval_promocion_docente.get(i).getFecha_promueve().equals("-")) {
                    fecha_promueve_temp = "null";
                } else {
                    fecha_promueve_temp = "'" + lst_comeval_promocion_docente.get(i).getFecha_promueve() + "'";
                }

                String nota_ref_comeval_temp;
                if (lst_comeval_promocion_docente.get(i).getNota_ref_comeval().equals("-")) {
                    nota_ref_comeval_temp = "null";
                } else {
                    nota_ref_comeval_temp = "'" + lst_comeval_promocion_docente.get(i).getNota_ref_comeval() + "'";
                }

                String fecha_nota_ref_comeval_temp;
                if (lst_comeval_promocion_docente.get(i).getFecha_nota_ref_comeval().equals("-")) {
                    fecha_nota_ref_comeval_temp = "null";
                } else {
                    fecha_nota_ref_comeval_temp = "'" + lst_comeval_promocion_docente.get(i).getFecha_nota_ref_comeval() + "'";
                }

                cadenasql = "insert into comeval_promocion_docente ("
                        + "id_comeval_promocion_docente, "
                        + "personal, "
                        + "tipoascenso, "
                        + "puesto, "
                        + "fecha_promueve, "
                        + "nota_ref_comeval, "
                        + "fecha_nota_ref_comeval, "
                        + "usuario, "
                        + "fecha_ingreso, "
                        + "id_estado_solicitud, "
                        + "id_tipo_solicitud, "
                        + "rechazado, "
                        + "id_estado_solicitud_rechazado, "
                        + "id_tipo_solicitud_rechazado, "
                        + "revision_comeval, "
                        + "revision_secretario_academico) values ("
                        + id_comeval_promocion_docente + ",'"
                        + lst_comeval_promocion_docente.get(i).getPersonal() + "',"
                        + lst_comeval_promocion_docente.get(i).getTipoascenso() + ",'"
                        + lst_comeval_promocion_docente.get(i).getPuesto() + "',"
                        + fecha_promueve_temp + ","
                        + nota_ref_comeval_temp + ","
                        + fecha_nota_ref_comeval_temp + ",'"
                        + lst_comeval_promocion_docente.get(i).getUsuario() + "','"
                        + lst_comeval_promocion_docente.get(i).getFecha_ingreso() + "',"
                        + lst_comeval_promocion_docente.get(i).getId_estado_solicitud() + ","
                        + lst_comeval_promocion_docente.get(i).getId_tipo_solicitud() + ","
                        + lst_comeval_promocion_docente.get(i).getRechazado() + ","
                        + "null" + ","
                        + "null" + ","
                        + lst_comeval_promocion_docente.get(i).getRevision_comeval() + ","
                        + lst_comeval_promocion_docente.get(i).getRevision_secretario_academico() + ")";
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                for (Integer j = 0; j < lst_comeval_promocion_docente.get(i).getLst_archivos().size(); j++) {
                    cadenasql = "insert into comeval_promocion_docente_archivos ("
                            + "id_comeval_promocion_docente, "
                            + "id_archivo, "
                            + "nombre_archivo, "
                            + "nombre_archivo_real) values ("
                            + id_comeval_promocion_docente + ","
                            + (j + 1) + ",'"
                            + lst_comeval_promocion_docente.get(i).getLst_archivos().get(j).getNombre_archivo() + "','"
                            + lst_comeval_promocion_docente.get(i).getLst_archivos().get(j).getNombre_archivo_real() + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }

                for (Integer j = 0; j < lst_comeval_promocion_docente.get(i).getLst_observaciones().size(); j++) {
                    cadenasql = "insert into comeval_solicitud_observacion ("
                            + "id_solicitud, "
                            + "id_tipo_solicitud, "
                            + "id_observacion, "
                            + "dependencia, "
                            + "fecha_hora, "
                            + "observacion) values ("
                            + id_comeval_promocion_docente + ","
                            + lst_comeval_promocion_docente.get(i).getLst_observaciones().get(j).getId_tipo_solicitud() + ","
                            + lst_comeval_promocion_docente.get(i).getLst_observaciones().get(j).getId_observacion() + ",'"
                            + lst_comeval_promocion_docente.get(i).getLst_observaciones().get(j).getDependencia() + "','"
                            + lst_comeval_promocion_docente.get(i).getLst_observaciones().get(j).getFecha_hora() + "','"
                            + lst_comeval_promocion_docente.get(i).getLst_observaciones().get(j).getObservacion() + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }

                Long max_id_workflow = Long.parseLong("0");
                cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + id_comeval_promocion_docente;
                stmt = this.conn.createStatement();
                rs = stmt.executeQuery(cadenasql);
                while (rs.next()) {
                    max_id_workflow = rs.getLong(1);
                }
                rs.close();
                stmt.close();

                Solicitud_Workflow_Historial solicitud_workflow_historial = new Solicitud_Workflow_Historial();
                solicitud_workflow_historial.setId_solicitud(id_comeval_promocion_docente);
                solicitud_workflow_historial.setId_estado_solicitud(lst_comeval_promocion_docente.get(i).getId_estado_solicitud());
                solicitud_workflow_historial.setId_tipo_solicitud(lst_comeval_promocion_docente.get(i).getId_tipo_solicitud());
                solicitud_workflow_historial.setId_workflow(max_id_workflow);
                solicitud_workflow_historial.setUsuario(lst_comeval_promocion_docente.get(i).getUsuario());
                solicitud_workflow_historial.setFecha(lst_comeval_promocion_docente.get(i).getFecha_ingreso());
                solicitud_workflow_historial.setRechazado(lst_comeval_promocion_docente.get(i).getRechazado());
                solicitud_workflow_historial.setFecha_rechazado(lst_comeval_promocion_docente.get(i).getFecha_ingreso());

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

            resultado = "0,Solicitud Promoción Docente ingresada correctamente.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_ingresar):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_ingresar):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_ingresar-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_ingresar-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String promocion_docente_modificar(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Promocion_Docente>>() {
            }.getType();
            List<Comeval_Promocion_Docente> lst_comeval_promocion_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_promocion_docente.size(); i++) {
                String fecha_promueve_temp = "";
                if (lst_comeval_promocion_docente.get(i).getFecha_promueve().trim().equals("-")) {
                    fecha_promueve_temp = "null";
                } else {
                    fecha_promueve_temp = "'" + lst_comeval_promocion_docente.get(i).getFecha_promueve().trim() + "'";
                }

                String nota_ref_comeval_temp;
                if (lst_comeval_promocion_docente.get(i).getNota_ref_comeval().equals("-")) {
                    nota_ref_comeval_temp = "null";
                } else {
                    nota_ref_comeval_temp = "'" + lst_comeval_promocion_docente.get(i).getNota_ref_comeval() + "'";
                }

                String fecha_nota_ref_comeval_temp;
                if (lst_comeval_promocion_docente.get(i).getFecha_nota_ref_comeval().equals("-")) {
                    fecha_nota_ref_comeval_temp = "null";
                } else {
                    fecha_nota_ref_comeval_temp = "'" + lst_comeval_promocion_docente.get(i).getFecha_nota_ref_comeval() + "'";
                }

                String id_estado_solicitud_rechazado_temp;
                String id_tipo_solicitud_rechazado_temp;
                if(lst_comeval_promocion_docente.get(i).getRechazado() == Long.parseLong("1")) {
                    id_estado_solicitud_rechazado_temp = lst_comeval_promocion_docente.get(i).getId_estado_solicitud_rechazado().toString();
                    id_tipo_solicitud_rechazado_temp = lst_comeval_promocion_docente.get(i).getId_tipo_solicitud_rechazado().toString();
                } else {
                    id_estado_solicitud_rechazado_temp = "null";
                    id_tipo_solicitud_rechazado_temp = "null";
                }

                String cadenasql = "update comeval_promocion_docente set "
                        + "tipoascenso=" + lst_comeval_promocion_docente.get(i).getTipoascenso() + ", "
                        + "puesto='" + lst_comeval_promocion_docente.get(i).getPuesto() + "', "
                        + "fecha_promueve=" + fecha_promueve_temp + ", "
                        + "nota_ref_comeval=" + nota_ref_comeval_temp + ", "
                        + "fecha_nota_ref_comeval=" + fecha_nota_ref_comeval_temp + ", "
                        + "usuario='" + lst_comeval_promocion_docente.get(i).getUsuario() + "', "
                        + "rechazado=" + lst_comeval_promocion_docente.get(i).getRechazado() + ", "
                        + "id_estado_solicitud_rechazado=" + id_estado_solicitud_rechazado_temp + ", "
                        + "id_tipo_solicitud_rechazado=" + id_tipo_solicitud_rechazado_temp + ", "
                        + "revision_comeval=" + lst_comeval_promocion_docente.get(i).getRevision_comeval() + ", "
                        + "revision_secretario_academico=" + lst_comeval_promocion_docente.get(i).getRevision_secretario_academico() + " "
                        + "where "
                        + "id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                Statement stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                cadenasql = "delete from comeval_promocion_docente_archivos "
                        + "where id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                for (Integer j = 0; j < lst_comeval_promocion_docente.get(i).getLst_archivos().size(); j++) {
                    cadenasql = "insert into comeval_promocion_docente_archivos ("
                            + "id_comeval_promocion_docente, "
                            + "id_archivo, "
                            + "nombre_archivo, "
                            + "nombre_archivo_real) values ("
                            + lst_comeval_promocion_docente.get(i).getLst_archivos().get(j).getId_comeval_promocion_docente() + ","
                            + (j + 1) + ",'"
                            + lst_comeval_promocion_docente.get(i).getLst_archivos().get(j).getNombre_archivo() + "','"
                            + lst_comeval_promocion_docente.get(i).getLst_archivos().get(j).getNombre_archivo_real() + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }

                cadenasql = "delete from comeval_solicitud_observacion "
                        + "where id_solicitud=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + " and "
                        + "id_tipo_solicitud=" + lst_comeval_promocion_docente.get(i).getId_tipo_solicitud();
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                for (Integer j = 0; j < lst_comeval_promocion_docente.get(i).getLst_observaciones().size(); j++) {
                    cadenasql = "insert into comeval_solicitud_observacion ("
                            + "id_solicitud, "
                            + "id_tipo_solicitud, "
                            + "id_observacion, "
                            + "dependencia, "
                            + "fecha_hora, "
                            + "observacion) values ("
                            + lst_comeval_promocion_docente.get(i).getLst_observaciones().get(j).getId_solicitud() + ","
                            + lst_comeval_promocion_docente.get(i).getLst_observaciones().get(j).getId_tipo_solicitud() + ","
                            + lst_comeval_promocion_docente.get(i).getLst_observaciones().get(j).getId_observacion() + ",'"
                            + lst_comeval_promocion_docente.get(i).getLst_observaciones().get(j).getDependencia() + "','"
                            + lst_comeval_promocion_docente.get(i).getLst_observaciones().get(j).getFecha_hora() + "','"
                            + lst_comeval_promocion_docente.get(i).getLst_observaciones().get(j).getObservacion() + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }
            }

            this.conn.commit();
            this.conn.setAutoCommit(true);

            resultado = "0,Solicitud Promoción Docente modificada correctamente.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_modificar):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_modificar):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_modificar-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_modificar-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String promocion_docente_enviar_ingreso_docente(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Promocion_Docente>>() {
            }.getType();
            List<Comeval_Promocion_Docente> lst_comeval_promocion_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_promocion_docente.size(); i++) {
                Boolean validado = false;
                String mensaje_no_valido = "";

                String cadenasql = "select trim(cpd.puesto) puesto "
                        + "from comeval_promocion_docente cpd "
                        + "where cpd.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                Statement stmt = this.conn.createStatement();
                ResultSet rs = stmt.executeQuery(cadenasql);
                String puesto = "";
                while (rs.next()) {
                    puesto = rs.getString(1);
                }
                rs.close();
                stmt.close();

                // SI EL PUESTO ES TITULARIDAD I O TITULARIDAD II SE VALIDA QUE LA SOLICITUD TENGA ADJUNTO EL EXPEDIENTE DEL DOCENTE.
                if (puesto.equals("210111") || puesto.equals("210121")) {
                    cadenasql = "select count(*) numero "
                            + "from comeval_promocion_docente_archivos cpda "
                            + "where "
                            + "cpda.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + " and "
                            + "nombre_archivo ilike '%pd_exp%'";
                    stmt = this.conn.createStatement();
                    rs = stmt.executeQuery(cadenasql);
                    Integer numero_adjuntos = 0;
                    while (rs.next()) {
                        numero_adjuntos = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (numero_adjuntos == 0) {
                        validado = false;
                        mensaje_no_valido = "La Promoción Docente con Titularidad I o II requiere adjuntar actas de promoción docente anteriores.";
                    } else {
                        validado = true;
                    }
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
                            + "ws.id_estado_solicitud_actual=" + lst_comeval_promocion_docente.get(i).getId_estado_solicitud() + " and "
                            + "ws.id_tipo_solicitud_actual=" + lst_comeval_promocion_docente.get(i).getId_tipo_solicitud();
                    stmt = this.conn.createStatement();
                    rs = stmt.executeQuery(cadenasql);
                    while (rs.next()) {
                        id_estado_solicitud_siguiente = rs.getLong(1);
                        id_tipo_solicitud_siguiente = rs.getLong(2);
                    }
                    rs.close();
                    stmt.close();

                    if (!id_estado_solicitud_siguiente.equals(Long.parseLong("0")) && !id_tipo_solicitud_siguiente.equals(Long.parseLong("0"))) {
                        cadenasql = "update comeval_promocion_docente set "
                                + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                + "usuario='" + lst_comeval_promocion_docente.get(i).getUsuario() + "' "
                                + "where id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                        stmt = this.conn.createStatement();
                        stmt.executeUpdate(cadenasql);
                        stmt.close();

                        Long max_id_workflow = Long.parseLong("0");
                        cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
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
                                + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + ", "
                                + id_estado_solicitud_siguiente + ","
                                + id_tipo_solicitud_siguiente + ","
                                + max_id_workflow + ",'"
                                + lst_comeval_promocion_docente.get(i).getUsuario() + "','"
                                + dateFormat.format(fecha_actual) + "',"
                                + "0" + ","
                                + "null" + ")";
                        stmt = this.conn.createStatement();
                        stmt.executeUpdate(cadenasql);
                        stmt.close();

                        resultado = "0,Solicitud Promoción Docente enviada al siguiente estado correctamente.";
                    } else {
                        resultado = "1,No existe estado siguiente para la solicitud Promoción Docente.";
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

                System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_docente):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_docente):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_docente-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_docente-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String promocion_docente_enviar_ingreso_comeval(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Promocion_Docente>>() {
            }.getType();
            List<Comeval_Promocion_Docente> lst_comeval_promocion_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_promocion_docente.size(); i++) {
                Boolean validado = false;
                String mensaje_no_valido = "";

                String cadenasql = "select trim(cpd.puesto) puesto "
                        + "from comeval_promocion_docente cpd "
                        + "where cpd.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                Statement stmt = this.conn.createStatement();
                ResultSet rs = stmt.executeQuery(cadenasql);
                String puesto = "";
                while (rs.next()) {
                    puesto = rs.getString(1);
                }
                rs.close();
                stmt.close();

                // SI EL PUESTO ES TITULARIDAD I O TITULARIDAD II SE VALIDA QUE LA SOLICITUD TENGA ADJUNTO EL EXPEDIENTE DEL DOCENTE.
                if (puesto.equals("210111") || puesto.equals("210121")) {
                    cadenasql = "select count(*) numero "
                            + "from comeval_promocion_docente_archivos cpda "
                            + "where "
                            + "cpda.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + " and "
                            + "nombre_archivo ilike '%pd_exp%'";
                    stmt = this.conn.createStatement();
                    rs = stmt.executeQuery(cadenasql);
                    Integer numero_adjuntos = 0;
                    while (rs.next()) {
                        numero_adjuntos = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (numero_adjuntos == 0) {
                        validado = false;
                        mensaje_no_valido = "La Promoción Docente con Titularidad I o II requiere adjuntar actas de promoción docente anteriores.";
                    } else {
                        validado = true;
                    }
                } else {
                    validado = true;
                }

                // SE VALIDA QUE SE HAYA ADJUNTADO LA HOJA DE RELACIÓN LABORAL.
                if (validado) {
                    cadenasql = "select count(*) numero "
                            + "from comeval_promocion_docente_archivos cpda "
                            + "where "
                            + "cpda.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + " and "
                            + "nombre_archivo ilike '%pd_hrl%'";
                    stmt = this.conn.createStatement();
                    rs = stmt.executeQuery(cadenasql);
                    Integer numero_adjuntos = 0;
                    while (rs.next()) {
                        numero_adjuntos = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (numero_adjuntos == 0) {
                        validado = false;
                        mensaje_no_valido = "Debe adjuntar la Hoja de Relación Laboral del docente.";
                    } else {
                        validado = true;
                    }
                }

                // SE VALIDA QUE SE HAYA ADJUNTADO EL CUADRO DE PROMOCIÓN DOCENTE.
                if (validado) {
                    cadenasql = "select count(*) numero "
                            + "from comeval_promocion_docente_archivos cpda "
                            + "where "
                            + "cpda.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + " and "
                            + "nombre_archivo ilike '%pd_cpd%'";
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(cadenasql);
                    Integer numero_adjuntos = 0;
                    while (rs.next()) {
                        numero_adjuntos = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (numero_adjuntos == 0) {
                        validado = false;
                        mensaje_no_valido = "Debe adjuntar el Cuadro de Promoción Docente.";
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
                            + "ws.id_estado_solicitud_actual=" + lst_comeval_promocion_docente.get(i).getId_estado_solicitud() + " and "
                            + "ws.id_tipo_solicitud_actual=" + lst_comeval_promocion_docente.get(i).getId_tipo_solicitud();
                    stmt = this.conn.createStatement();
                    rs = stmt.executeQuery(cadenasql);
                    while (rs.next()) {
                        id_estado_solicitud_siguiente = rs.getLong(1);
                        id_tipo_solicitud_siguiente = rs.getLong(2);
                    }
                    rs.close();
                    stmt.close();

                    if (!id_estado_solicitud_siguiente.equals(Long.parseLong("0")) && !id_tipo_solicitud_siguiente.equals(Long.parseLong("0"))) {
                        cadenasql = "update comeval_promocion_docente set "
                                + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                + "usuario='" + lst_comeval_promocion_docente.get(i).getUsuario() + "' "
                                + "where id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                        stmt = this.conn.createStatement();
                        stmt.executeUpdate(cadenasql);
                        stmt.close();

                        Long max_id_workflow = Long.parseLong("0");
                        cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
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
                                + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + ", "
                                + id_estado_solicitud_siguiente + ","
                                + id_tipo_solicitud_siguiente + ","
                                + max_id_workflow + ",'"
                                + lst_comeval_promocion_docente.get(i).getUsuario() + "','"
                                + dateFormat.format(fecha_actual) + "',"
                                + "0" + ","
                                + "null" + ")";
                        stmt = this.conn.createStatement();
                        stmt.executeUpdate(cadenasql);
                        stmt.close();

                        resultado = "0,Solicitud Promoción Docente enviada al siguiente estado correctamente.";
                    } else {
                        resultado = "1,No existe estado siguiente para la solicitud Promoción Docente.";
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

                System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_comeval):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_comeval):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_comeval-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_comeval-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String promocion_docente_enviar_ingreso_secretario_academico(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Promocion_Docente>>() {
            }.getType();
            List<Comeval_Promocion_Docente> lst_comeval_promocion_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_promocion_docente.size(); i++) {
                Boolean validado = false;
                String mensaje_no_valido = "";

                String cadenasql = "select trim(cpd.puesto) puesto "
                        + "from comeval_promocion_docente cpd "
                        + "where cpd.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                Statement stmt = this.conn.createStatement();
                ResultSet rs = stmt.executeQuery(cadenasql);
                String puesto = "";
                while (rs.next()) {
                    puesto = rs.getString(1);
                }
                rs.close();
                stmt.close();

                // SI EL PUESTO ES TITULARIDAD I O TITULARIDAD II SE VALIDA QUE LA SOLICITUD TENGA ADJUNTO EL EXPEDIENTE DEL DOCENTE.
                if (puesto.equals("210111") || puesto.equals("210121")) {
                    cadenasql = "select count(*) numero "
                            + "from comeval_promocion_docente_archivos cpda "
                            + "where "
                            + "cpda.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + " and "
                            + "nombre_archivo ilike '%pd_exp%'";
                    stmt = this.conn.createStatement();
                    rs = stmt.executeQuery(cadenasql);
                    Integer numero_adjuntos = 0;
                    while (rs.next()) {
                        numero_adjuntos = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (numero_adjuntos == 0) {
                        validado = false;
                        mensaje_no_valido = "La Promoción Docente con Titularidad I o II requiere adjuntar actas de promoción docente anteriores.";
                    } else {
                        validado = true;
                    }
                } else {
                    validado = true;
                }

                // SE VALIDA QUE SE HAYA ADJUNTADO LA HOJA DE RELACIÓN LABORAL.
                if (validado) {
                    cadenasql = "select count(*) numero "
                            + "from comeval_promocion_docente_archivos cpda "
                            + "where "
                            + "cpda.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + " and "
                            + "nombre_archivo ilike '%pd_hrl%'";
                    stmt = this.conn.createStatement();
                    rs = stmt.executeQuery(cadenasql);
                    Integer numero_adjuntos = 0;
                    while (rs.next()) {
                        numero_adjuntos = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (numero_adjuntos == 0) {
                        validado = false;
                        mensaje_no_valido = "Debe adjuntar la Hoja de Relación Laboral del docente.";
                    } else {
                        validado = true;
                    }
                }

                // SE VALIDA QUE SE HAYA ADJUNTADO EL CUADRO DE PROMOCIÓN DOCENTE.
                if (validado) {
                    cadenasql = "select count(*) numero "
                            + "from comeval_promocion_docente_archivos cpda "
                            + "where "
                            + "cpda.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + " and "
                            + "nombre_archivo ilike '%pd_cpd%'";
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(cadenasql);
                    Integer numero_adjuntos = 0;
                    while (rs.next()) {
                        numero_adjuntos = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (numero_adjuntos == 0) {
                        validado = false;
                        mensaje_no_valido = "Debe adjuntar el Cuadro de Promoción Docente.";
                    } else {
                        validado = true;
                    }
                }

                // INSERTAR SOLICITUD EN LA APLICACIÓN DE ACTAS DE JUNTA DIRECTIVA.
                if (validado) {
                    Ctrl_Driver ctrl_driver1 = new Ctrl_Driver("jndi_gestionautenticacion2");
                    String nombre_solicitante = ctrl_driver1.nombre_usuario(lst_comeval_promocion_docente.get(i).getUsuario());

                    cadenasql = "select p.personal, trim(p.nombre) || ' ' || trim(p.apellido) nombre_solicitante "
                            + "from personal p "
                            + "where p.personal='" + lst_comeval_promocion_docente.get(i).getPersonal() + "'";
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
                            + "where p.puesto in (select t.puesto from titularidad t where t.personal='" + lst_comeval_promocion_docente.get(i).getPersonal() + "')";
                    stmt = this.conn.createStatement();
                    rs = stmt.executeQuery(cadenasql);
                    String puesto_actual = "";
                    while (rs.next()) {
                        puesto_actual = rs.getString(1);
                    }
                    rs.close();
                    stmt.close();

                    cadenasql = "select p.nombre "
                            + "from puesto p "
                            + "where p.puesto='" + lst_comeval_promocion_docente.get(i).getPuesto() + "'";
                    stmt = this.conn.createStatement();
                    rs = stmt.executeQuery(cadenasql);
                    String nuevo_puesto = "";
                    while (rs.next()) {
                        nuevo_puesto = rs.getString(1);
                    }
                    rs.close();
                    stmt.close();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                    Calendar fecha_promueve = Calendar.getInstance();
                    fecha_promueve.setTime(dateFormat.parse(lst_comeval_promocion_docente.get(i).getFecha_promueve()));

                    Integer dia_fecha_promueve = fecha_promueve.get(Calendar.DATE);
                    Integer mes_fecha_promueve = fecha_promueve.get(Calendar.MONTH) + 1;
                    Integer anio_fecha_promueve = fecha_promueve.get(Calendar.YEAR);

                    String l_dia_fecha_promueve = "";
                    if (dia_fecha_promueve < 10) {
                        l_dia_fecha_promueve = "0" + dia_fecha_promueve.toString();
                    } else {
                        l_dia_fecha_promueve = dia_fecha_promueve.toString();
                    }

                    String l_mes_fecha_promueve = "";
                    switch (mes_fecha_promueve) {
                        case 1: {
                            l_mes_fecha_promueve = "enero";
                            break;
                        }
                        case 2: {
                            l_mes_fecha_promueve = "febrero";
                            break;
                        }
                        case 3: {
                            l_mes_fecha_promueve = "marzo";
                            break;
                        }
                        case 4: {
                            l_mes_fecha_promueve = "abril";
                            break;
                        }
                        case 5: {
                            l_mes_fecha_promueve = "mayo";
                            break;
                        }
                        case 6: {
                            l_mes_fecha_promueve = "junio";
                            break;
                        }
                        case 7: {
                            l_mes_fecha_promueve = "julio";
                            break;
                        }
                        case 8: {
                            l_mes_fecha_promueve = "agosto";
                            break;
                        }
                        case 9: {
                            l_mes_fecha_promueve = "septiembre";
                            break;
                        }
                        case 10: {
                            l_mes_fecha_promueve = "octubre";
                            break;
                        }
                        case 11: {
                            l_mes_fecha_promueve = "noviembre";
                            break;
                        }
                        case 12: {
                            l_mes_fecha_promueve = "diciembre";
                            break;
                        }
                    }

                    Calendar fecha_nota_ref_comeval = Calendar.getInstance();
                    fecha_nota_ref_comeval.setTime(dateFormat.parse(lst_comeval_promocion_docente.get(i).getFecha_nota_ref_comeval()));

                    Integer dia_fecha_nota_ref_comeval = fecha_nota_ref_comeval.get(Calendar.DATE);
                    Integer mes_fecha_nota_ref_comeval = fecha_nota_ref_comeval.get(Calendar.MONTH) + 1;
                    Integer anio_fecha_nota_ref_comeval = fecha_nota_ref_comeval.get(Calendar.YEAR);

                    String l_dia_fecha_nota_ref_comeval = "";
                    if (dia_fecha_nota_ref_comeval < 10) {
                        l_dia_fecha_nota_ref_comeval = "0" + dia_fecha_nota_ref_comeval.toString();
                    } else {
                        l_dia_fecha_nota_ref_comeval = dia_fecha_nota_ref_comeval.toString();
                    }

                    String l_mes_fecha_nota_ref_comeval = "";
                    switch (mes_fecha_nota_ref_comeval) {
                        case 1: {
                            l_mes_fecha_nota_ref_comeval = "enero";
                            break;
                        }
                        case 2: {
                            l_mes_fecha_nota_ref_comeval = "febrero";
                            break;
                        }
                        case 3: {
                            l_mes_fecha_nota_ref_comeval = "marzo";
                            break;
                        }
                        case 4: {
                            l_mes_fecha_nota_ref_comeval = "abril";
                            break;
                        }
                        case 5: {
                            l_mes_fecha_nota_ref_comeval = "mayo";
                            break;
                        }
                        case 6: {
                            l_mes_fecha_nota_ref_comeval = "junio";
                            break;
                        }
                        case 7: {
                            l_mes_fecha_nota_ref_comeval = "julio";
                            break;
                        }
                        case 8: {
                            l_mes_fecha_nota_ref_comeval = "agosto";
                            break;
                        }
                        case 9: {
                            l_mes_fecha_nota_ref_comeval = "septiembre";
                            break;
                        }
                        case 10: {
                            l_mes_fecha_nota_ref_comeval = "octubre";
                            break;
                        }
                        case 11: {
                            l_mes_fecha_nota_ref_comeval = "noviembre";
                            break;
                        }
                        case 12: {
                            l_mes_fecha_nota_ref_comeval = "diciembre";
                            break;
                        }
                    }

                    entidad.Solicitud_Acta solicitud_acta = new entidad.Solicitud_Acta();
                    solicitud_acta.setSolicitanteexterno("NOMBRE-SOLICITANTE: " + nombre_solicitante + ", CÓDIGO-PERSONAL: " + lst_comeval_promocion_docente.get(i).getUsuario());
                    solicitud_acta.setDescripcion("Se recibió nota de Ref. COMEVAL " + lst_comeval_promocion_docente.get(i).getNota_ref_comeval().trim() + " de fecha " + l_dia_fecha_nota_ref_comeval + " de " + l_mes_fecha_nota_ref_comeval + " de " + anio_fecha_nota_ref_comeval + ", "
                            + "presentada por el/la Ing/Inga. " + nombre_solicitante + ", usuario de la aplicación Procesos Titulares de la Facultad de Ingenieria, "
                            + "quien presenta el cuadro de promoción docente del ingeniero/a " + nombre_docente + " con registro de personal No. " + lst_comeval_promocion_docente.get(i).getPersonal() + ", "
                            + "quien promueve de " + puesto_actual + " a " + nuevo_puesto + " a partir del " + l_dia_fecha_promueve + " de " + l_mes_fecha_promueve + " de " + anio_fecha_promueve + ".");

                    cadenasql = "select cpda.nombre_archivo "
                            + "from comeval_promocion_docente_archivos cpda "
                            + "where cpda.nombre_archivo ilike '%pd_cpd%' and cpda.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                    stmt = this.conn.createStatement();
                    rs = stmt.executeQuery(cadenasql);
                    String id_archivo_personal2 = "";
                    while (rs.next()) {
                        id_archivo_personal2 = rs.getString(1);
                    }
                    rs.close();
                    stmt.close();

                    Ctrl_Driver ctrl_driver2 = new Ctrl_Driver("jndi_asuntosestudiantiles");
                    Long id_solicitud = ctrl_driver2.insertar_solictid_db_actas(solicitud_acta, lst_comeval_promocion_docente.get(i).getUsuario(), nombre_solicitante, id_archivo_personal2, Long.parseLong("43"));

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
                                + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + ","
                                + lst_comeval_promocion_docente.get(i).getId_tipo_solicitud() + ","
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
                            + "ws.id_estado_solicitud_actual=" + lst_comeval_promocion_docente.get(i).getId_estado_solicitud() + " and "
                            + "ws.id_tipo_solicitud_actual=" + lst_comeval_promocion_docente.get(i).getId_tipo_solicitud();
                    stmt = this.conn.createStatement();
                    rs = stmt.executeQuery(cadenasql);
                    while (rs.next()) {
                        id_estado_solicitud_siguiente = rs.getLong(1);
                        id_tipo_solicitud_siguiente = rs.getLong(2);
                    }
                    rs.close();
                    stmt.close();

                    if (!id_estado_solicitud_siguiente.equals(Long.parseLong("0")) && !id_tipo_solicitud_siguiente.equals(Long.parseLong("0"))) {
                        cadenasql = "update comeval_promocion_docente set "
                                + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                + "usuario='" + lst_comeval_promocion_docente.get(i).getUsuario() + "' "
                                + "where id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                        stmt = this.conn.createStatement();
                        stmt.executeUpdate(cadenasql);
                        stmt.close();

                        Long max_id_workflow = Long.parseLong("0");
                        cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
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
                                + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + ", "
                                + id_estado_solicitud_siguiente + ","
                                + id_tipo_solicitud_siguiente + ","
                                + max_id_workflow + ",'"
                                + lst_comeval_promocion_docente.get(i).getUsuario() + "','"
                                + dateFormat.format(fecha_actual) + "',"
                                + "0" + ","
                                + "null" + ")";
                        stmt = this.conn.createStatement();
                        stmt.executeUpdate(cadenasql);
                        stmt.close();

                        resultado = "0,Solicitud Promoción Docente enviada al siguiente estado correctamente.";
                    } else {
                        resultado = "1,No existe estado siguiente para la solicitud Promoción Docente.";
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

                System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_secretario_academico):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_secretario_academico):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_secretario_academico-1):" + ex1.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_secretario_academico-1):" + ex1.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String promocion_docente_enviar_revision_comeval(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Promocion_Docente>>() {
            }.getType();
            List<Comeval_Promocion_Docente> lst_comeval_promocion_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_promocion_docente.size(); i++) {
                if (lst_comeval_promocion_docente.get(i).getRechazado() == Long.parseLong("1")) {
                    String cadenasql = "update comeval_promocion_docente set "
                            + "id_estado_solicitud=" + lst_comeval_promocion_docente.get(i).getId_estado_solicitud_rechazado() + ", "
                            + "id_tipo_solicitud=" + lst_comeval_promocion_docente.get(i).getId_tipo_solicitud_rechazado() + ", "
                            + "usuario='" + lst_comeval_promocion_docente.get(i).getUsuario() + "', "
                            + "rechazado=0, "
                            + "id_estado_solicitud_rechazado=null, "
                            + "id_tipo_solicitud_rechazado=null "
                            + "where id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    Long max_id_workflow = Long.parseLong("0");
                    cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
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
                            + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + ","
                            + lst_comeval_promocion_docente.get(i).getId_estado_solicitud_rechazado() + ","
                            + lst_comeval_promocion_docente.get(i).getId_tipo_solicitud_rechazado() + ","
                            + max_id_workflow + ",'"
                            + lst_comeval_promocion_docente.get(i).getUsuario() + "','"
                            + dateFormat.format(fecha_actual) + "',"
                            + lst_comeval_promocion_docente.get(i).getRechazado() + ",'"
                            + dateFormat.format(fecha_actual) + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    resultado = "0,Solicitud Promoción Docente rechazada correctamente.";
                } else {
                    Boolean validado = false;
                    String mensaje_no_valido = "";

                    String cadenasql = "select trim(cpd.puesto) puesto "
                            + "from comeval_promocion_docente cpd "
                            + "where cpd.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                    Statement stmt = this.conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    String puesto = "";
                    while (rs.next()) {
                        puesto = rs.getString(1);
                    }
                    rs.close();
                    stmt.close();

                    // SI EL PUESTO ES TITULARIDAD I O TITULARIDAD II SE VALIDA QUE LA SOLICITUD TENGA ADJUNTO EL EXPEDIENTE DEL DOCENTE.
                    if (puesto.equals("210111") || puesto.equals("210121")) {
                        cadenasql = "select count(*) numero "
                                + "from comeval_promocion_docente_archivos cpda "
                                + "where "
                                + "cpda.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + " and "
                                + "nombre_archivo ilike '%pd_exp%'";
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        Integer numero_adjuntos = 0;
                        while (rs.next()) {
                            numero_adjuntos = rs.getInt(1);
                        }
                        rs.close();
                        stmt.close();

                        if (numero_adjuntos == 0) {
                            validado = false;
                            mensaje_no_valido = "La Promoción Docente con Titularidad I o II requiere adjuntar actas de promoción docente anteriores.";
                        } else {
                            validado = true;
                        }
                    } else {
                        validado = true;
                    }

                    // SE VALIDA QUE SE HAYA ADJUNTADO LA HOJA DE RELACIÓN LABORAL.
                    if (validado) {
                        cadenasql = "select count(*) numero "
                                + "from comeval_promocion_docente_archivos cpda "
                                + "where "
                                + "cpda.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + " and "
                                + "nombre_archivo ilike '%pd_hrl%'";
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        Integer numero_adjuntos = 0;
                        while (rs.next()) {
                            numero_adjuntos = rs.getInt(1);
                        }
                        rs.close();
                        stmt.close();

                        if (numero_adjuntos == 0) {
                            validado = false;
                            mensaje_no_valido = "Debe adjuntar la Hoja de Relación Laboral del docente.";
                        } else {
                            validado = true;
                        }
                    }

                    // SE VALIDA QUE SE HAYA ADJUNTADO EL CUADRO DE PROMOCIÓN DOCENTE.
                    if (validado) {
                        cadenasql = "select count(*) numero "
                                + "from comeval_promocion_docente_archivos cpda "
                                + "where "
                                + "cpda.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + " and "
                                + "nombre_archivo ilike '%pd_cpd%'";
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        Integer numero_adjuntos = 0;
                        while (rs.next()) {
                            numero_adjuntos = rs.getInt(1);
                        }
                        rs.close();
                        stmt.close();

                        if (numero_adjuntos == 0) {
                            validado = false;
                            mensaje_no_valido = "Debe adjuntar el Cuadro de Promoción Docente.";
                        } else {
                            validado = true;
                        }
                    }

                    // VALIDA QUE SE HAYA MARCADO LA CASILLA REVISIÓN COMEVAL.
                    if (validado) {
                        cadenasql = "select cpd.revision_comeval "
                                + "from comeval_promocion_docente cpd "
                                + "where "
                                + "cpd.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        Integer revision_comeval = 0;
                        while (rs.next()) {
                            revision_comeval = rs.getInt(1);
                        }
                        rs.close();
                        stmt.close();

                        if (revision_comeval == 0) {
                            validado = false;
                            mensaje_no_valido = "Debe marcar la casilla Revisión Comeval en el formulario de la solicitud Promoción Docente.";
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
                                + "ws.id_estado_solicitud_actual=" + lst_comeval_promocion_docente.get(i).getId_estado_solicitud() + " and "
                                + "ws.id_tipo_solicitud_actual=" + lst_comeval_promocion_docente.get(i).getId_tipo_solicitud();
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        while (rs.next()) {
                            id_estado_solicitud_siguiente = rs.getLong(1);
                            id_tipo_solicitud_siguiente = rs.getLong(2);
                        }
                        rs.close();
                        stmt.close();

                        if (!id_estado_solicitud_siguiente.equals(Long.parseLong("0")) && !id_tipo_solicitud_siguiente.equals(Long.parseLong("0"))) {
                            cadenasql = "update comeval_promocion_docente set "
                                    + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                    + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                    + "usuario='" + lst_comeval_promocion_docente.get(i).getUsuario() + "' "
                                    + "where id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            Long max_id_workflow = Long.parseLong("0");
                            cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
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
                                    + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + ", "
                                    + id_estado_solicitud_siguiente + ","
                                    + id_tipo_solicitud_siguiente + ","
                                    + max_id_workflow + ",'"
                                    + lst_comeval_promocion_docente.get(i).getUsuario() + "','"
                                    + dateFormat.format(fecha_actual) + "',"
                                    + "0" + ","
                                    + "null" + ")";
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            resultado = "0,Solicitud Promoción Docente enviada al siguiente estado correctamente.";
                        } else {
                            resultado = "1,No existe estado siguiente para la solicitud Promoción Docente.";
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

                System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_revision_comeval):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_revision_comeval):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_revision_comeval-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_revision_comeval-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String promocion_docente_enviar_revision_secretario_academico(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Promocion_Docente>>() {
            }.getType();
            List<Comeval_Promocion_Docente> lst_comeval_promocion_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_promocion_docente.size(); i++) {
                if (lst_comeval_promocion_docente.get(i).getRechazado() == Long.parseLong("1")) {
                    String cadenasql = "update comeval_promocion_docente set "
                            + "id_estado_solicitud=" + lst_comeval_promocion_docente.get(i).getId_estado_solicitud_rechazado() + ", "
                            + "id_tipo_solicitud=" + lst_comeval_promocion_docente.get(i).getId_tipo_solicitud_rechazado() + ", "
                            + "usuario='" + lst_comeval_promocion_docente.get(i).getUsuario() + "', "
                            + "rechazado=0, "
                            + "id_estado_solicitud_rechazado=null, "
                            + "id_tipo_solicitud_rechazado=null "
                            + "where id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                    
                    Long max_id_workflow = Long.parseLong("0");
                    cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
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
                            + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + ","
                            + lst_comeval_promocion_docente.get(i).getId_estado_solicitud_rechazado() + ","
                            + lst_comeval_promocion_docente.get(i).getId_tipo_solicitud_rechazado() + ","
                            + max_id_workflow + ",'"
                            + lst_comeval_promocion_docente.get(i).getUsuario() + "','"
                            + dateFormat.format(fecha_actual) + "',"
                            + lst_comeval_promocion_docente.get(i).getRechazado() + ",'"
                            + dateFormat.format(fecha_actual) + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();

                    resultado = "0,Solicitud Promoción Docente rechazada correctamente.";
                } else {
                    Boolean validado = false;
                    String mensaje_no_valido = "";

                    String cadenasql = "select trim(cpd.puesto) puesto "
                            + "from comeval_promocion_docente cpd "
                            + "where cpd.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                    Statement stmt = this.conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    String puesto = "";
                    while (rs.next()) {
                        puesto = rs.getString(1);
                    }
                    rs.close();
                    stmt.close();

                    // SI EL PUESTO ES TITULARIDAD I O TITULARIDAD II SE VALIDA QUE LA SOLICITUD TENGA ADJUNTO EL EXPEDIENTE DEL DOCENTE.
                    if (puesto.equals("210111") || puesto.equals("210121")) {
                        cadenasql = "select count(*) numero "
                                + "from comeval_promocion_docente_archivos cpda "
                                + "where "
                                + "cpda.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + " and "
                                + "nombre_archivo ilike '%pd_exp%'";
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        Integer numero_adjuntos = 0;
                        while (rs.next()) {
                            numero_adjuntos = rs.getInt(1);
                        }
                        rs.close();
                        stmt.close();

                        if (numero_adjuntos == 0) {
                            validado = false;
                            mensaje_no_valido = "La Promoción Docente con Titularidad I o II requiere adjuntar actas de promoción docente anteriores.";
                        } else {
                            validado = true;
                        }
                    } else {
                        validado = true;
                    }

                    // SE VALIDA QUE SE HAYA ADJUNTADO LA HOJA DE RELACIÓN LABORAL.
                    if (validado) {
                        cadenasql = "select count(*) numero "
                                + "from comeval_promocion_docente_archivos cpda "
                                + "where "
                                + "cpda.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + " and "
                                + "nombre_archivo ilike '%pd_hrl%'";
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        Integer numero_adjuntos = 0;
                        while (rs.next()) {
                            numero_adjuntos = rs.getInt(1);
                        }
                        rs.close();
                        stmt.close();

                        if (numero_adjuntos == 0) {
                            validado = false;
                            mensaje_no_valido = "Debe adjuntar la Hoja de Relación Laboral del docente.";
                        } else {
                            validado = true;
                        }
                    }

                    // SE VALIDA QUE SE HAYA ADJUNTADO EL CUADRO DE PROMOCIÓN DOCENTE.
                    if (validado) {
                        cadenasql = "select count(*) numero "
                                + "from comeval_promocion_docente_archivos cpda "
                                + "where "
                                + "cpda.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + " and "
                                + "nombre_archivo ilike '%pd_cpd%'";
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        Integer numero_adjuntos = 0;
                        while (rs.next()) {
                            numero_adjuntos = rs.getInt(1);
                        }
                        rs.close();
                        stmt.close();

                        if (numero_adjuntos == 0) {
                            validado = false;
                            mensaje_no_valido = "Debe adjuntar el Cuadro de Promoción Docente.";
                        } else {
                            validado = true;
                        }
                    }

                    // VALIDA QUE SE HAYA MARCADO LA CASILLA REVISIÓN SECRETARIO ACADÉMICO.
                    if (validado) {
                        cadenasql = "select cpd.revision_secretario_academico "
                                + "from comeval_promocion_docente cpd "
                                + "where "
                                + "cpd.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        Integer revision_comeval = 0;
                        while (rs.next()) {
                            revision_comeval = rs.getInt(1);
                        }
                        rs.close();
                        stmt.close();

                        if (revision_comeval == 0) {
                            validado = false;
                            mensaje_no_valido = "Debe marcar la casilla Revisión Secretario Académico en el formulario de la solicitud Promoción Docente.";
                        } else {
                            validado = true;
                        }
                    }

                    // INSERTAR SOLICITUD EN LA APLICACIÓN DE ACTAS DE JUNTA DIRECTIVA.
                    if (validado) {
                        Ctrl_Driver ctrl_driver1 = new Ctrl_Driver("jndi_gestionautenticacion2");
                        String nombre_solicitante = ctrl_driver1.nombre_usuario(lst_comeval_promocion_docente.get(i).getUsuario());

                        cadenasql = "select p.personal, trim(p.nombre) || ' ' || trim(p.apellido) nombre_solicitante "
                                + "from personal p "
                                + "where p.personal='" + lst_comeval_promocion_docente.get(i).getPersonal() + "'";
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
                                + "where p.puesto in (select t.puesto from titularidad t where t.personal='" + lst_comeval_promocion_docente.get(i).getPersonal() + "')";
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        String puesto_actual = "";
                        while (rs.next()) {
                            puesto_actual = rs.getString(1);
                        }
                        rs.close();
                        stmt.close();

                        cadenasql = "select p.nombre "
                                + "from puesto p "
                                + "where p.puesto='" + lst_comeval_promocion_docente.get(i).getPuesto() + "'";
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        String nuevo_puesto = "";
                        while (rs.next()) {
                            nuevo_puesto = rs.getString(1);
                        }
                        rs.close();
                        stmt.close();

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                        Calendar fecha_promueve = Calendar.getInstance();
                        fecha_promueve.setTime(dateFormat.parse(lst_comeval_promocion_docente.get(i).getFecha_promueve()));

                        Integer dia_fecha_promueve = fecha_promueve.get(Calendar.DATE);
                        Integer mes_fecha_promueve = fecha_promueve.get(Calendar.MONTH) + 1;
                        Integer anio_fecha_promueve = fecha_promueve.get(Calendar.YEAR);

                        String l_dia_fecha_promueve = "";
                        if (dia_fecha_promueve < 10) {
                            l_dia_fecha_promueve = "0" + dia_fecha_promueve.toString();
                        } else {
                            l_dia_fecha_promueve = dia_fecha_promueve.toString();
                        }

                        String l_mes_fecha_promueve = "";
                        switch (mes_fecha_promueve) {
                            case 1: {
                                l_mes_fecha_promueve = "enero";
                                break;
                            }
                            case 2: {
                                l_mes_fecha_promueve = "febrero";
                                break;
                            }
                            case 3: {
                                l_mes_fecha_promueve = "marzo";
                                break;
                            }
                            case 4: {
                                l_mes_fecha_promueve = "abril";
                                break;
                            }
                            case 5: {
                                l_mes_fecha_promueve = "mayo";
                                break;
                            }
                            case 6: {
                                l_mes_fecha_promueve = "junio";
                                break;
                            }
                            case 7: {
                                l_mes_fecha_promueve = "julio";
                                break;
                            }
                            case 8: {
                                l_mes_fecha_promueve = "agosto";
                                break;
                            }
                            case 9: {
                                l_mes_fecha_promueve = "septiembre";
                                break;
                            }
                            case 10: {
                                l_mes_fecha_promueve = "octubre";
                                break;
                            }
                            case 11: {
                                l_mes_fecha_promueve = "noviembre";
                                break;
                            }
                            case 12: {
                                l_mes_fecha_promueve = "diciembre";
                                break;
                            }
                        }

                        Calendar fecha_nota_ref_comeval = Calendar.getInstance();
                        fecha_nota_ref_comeval.setTime(dateFormat.parse(lst_comeval_promocion_docente.get(i).getFecha_nota_ref_comeval()));

                        Integer dia_fecha_nota_ref_comeval = fecha_nota_ref_comeval.get(Calendar.DATE);
                        Integer mes_fecha_nota_ref_comeval = fecha_nota_ref_comeval.get(Calendar.MONTH) + 1;
                        Integer anio_fecha_nota_ref_comeval = fecha_nota_ref_comeval.get(Calendar.YEAR);

                        String l_dia_fecha_nota_ref_comeval = "";
                        if (dia_fecha_nota_ref_comeval < 10) {
                            l_dia_fecha_nota_ref_comeval = "0" + dia_fecha_nota_ref_comeval.toString();
                        } else {
                            l_dia_fecha_nota_ref_comeval = dia_fecha_nota_ref_comeval.toString();
                        }

                        String l_mes_fecha_nota_ref_comeval = "";
                        switch (mes_fecha_nota_ref_comeval) {
                            case 1: {
                                l_mes_fecha_nota_ref_comeval = "enero";
                                break;
                            }
                            case 2: {
                                l_mes_fecha_nota_ref_comeval = "febrero";
                                break;
                            }
                            case 3: {
                                l_mes_fecha_nota_ref_comeval = "marzo";
                                break;
                            }
                            case 4: {
                                l_mes_fecha_nota_ref_comeval = "abril";
                                break;
                            }
                            case 5: {
                                l_mes_fecha_nota_ref_comeval = "mayo";
                                break;
                            }
                            case 6: {
                                l_mes_fecha_nota_ref_comeval = "junio";
                                break;
                            }
                            case 7: {
                                l_mes_fecha_nota_ref_comeval = "julio";
                                break;
                            }
                            case 8: {
                                l_mes_fecha_nota_ref_comeval = "agosto";
                                break;
                            }
                            case 9: {
                                l_mes_fecha_nota_ref_comeval = "septiembre";
                                break;
                            }
                            case 10: {
                                l_mes_fecha_nota_ref_comeval = "octubre";
                                break;
                            }
                            case 11: {
                                l_mes_fecha_nota_ref_comeval = "noviembre";
                                break;
                            }
                            case 12: {
                                l_mes_fecha_nota_ref_comeval = "diciembre";
                                break;
                            }
                        }

                        entidad.Solicitud_Acta solicitud_acta = new entidad.Solicitud_Acta();
                        solicitud_acta.setSolicitanteexterno("NOMBRE-SOLICITANTE: " + nombre_solicitante + ", CÓDIGO-PERSONAL: " + lst_comeval_promocion_docente.get(i).getUsuario());
                        solicitud_acta.setDescripcion("Se recibió nota de Ref. COMEVAL " + lst_comeval_promocion_docente.get(i).getNota_ref_comeval().trim() + " de fecha " + l_dia_fecha_nota_ref_comeval + " de " + l_mes_fecha_nota_ref_comeval + " de " + anio_fecha_nota_ref_comeval + ", "
                                + "presentada por el/la Ing/Inga. " + nombre_solicitante + ", usuario de la aplicación Procesos Titulares de la Facultad de Ingenieria, "
                                + "quien presenta el cuadro de promoción docente del ingeniero/a " + nombre_docente + " con registro de personal No. " + lst_comeval_promocion_docente.get(i).getPersonal() + ", "
                                + "quien promueve de " + puesto_actual + " a " + nuevo_puesto + " a partir del " + l_dia_fecha_promueve + " de " + l_mes_fecha_promueve + " de " + anio_fecha_promueve + ".");

                        cadenasql = "select cpda.nombre_archivo "
                                + "from comeval_promocion_docente_archivos cpda "
                                + "where cpda.nombre_archivo ilike '%pd_cpd%' and cpda.id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        String id_archivo_personal2 = "";
                        while (rs.next()) {
                            id_archivo_personal2 = rs.getString(1);
                        }
                        rs.close();
                        stmt.close();

                        Ctrl_Driver ctrl_driver2 = new Ctrl_Driver("jndi_asuntosestudiantiles");
                        Long id_solicitud = ctrl_driver2.insertar_solictid_db_actas(solicitud_acta, lst_comeval_promocion_docente.get(i).getUsuario(), nombre_solicitante, id_archivo_personal2, Long.parseLong("43"));

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
                                    + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + ","
                                    + lst_comeval_promocion_docente.get(i).getId_tipo_solicitud() + ","
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
                                + "ws.id_estado_solicitud_actual=" + lst_comeval_promocion_docente.get(i).getId_estado_solicitud() + " and "
                                + "ws.id_tipo_solicitud_actual=" + lst_comeval_promocion_docente.get(i).getId_tipo_solicitud();
                        stmt = this.conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        while (rs.next()) {
                            id_estado_solicitud_siguiente = rs.getLong(1);
                            id_tipo_solicitud_siguiente = rs.getLong(2);
                        }
                        rs.close();
                        stmt.close();

                        if (!id_estado_solicitud_siguiente.equals(Long.parseLong("0")) && !id_tipo_solicitud_siguiente.equals(Long.parseLong("0"))) {
                            cadenasql = "update comeval_promocion_docente set "
                                    + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                    + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                    + "usuario='" + lst_comeval_promocion_docente.get(i).getUsuario() + "' "
                                    + "where id_comeval_promocion_docente=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            Long max_id_workflow = Long.parseLong("0");
                            cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente();
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
                                    + lst_comeval_promocion_docente.get(i).getId_comeval_promocion_docente() + ", "
                                    + id_estado_solicitud_siguiente + ","
                                    + id_tipo_solicitud_siguiente + ","
                                    + max_id_workflow + ",'"
                                    + lst_comeval_promocion_docente.get(i).getUsuario() + "','"
                                    + dateFormat.format(fecha_actual) + "',"
                                    + "0" + ","
                                    + "null" + ")";
                            stmt = this.conn.createStatement();
                            stmt.executeUpdate(cadenasql);
                            stmt.close();

                            resultado = "0,Solicitud Promoción Docente enviada al siguiente estado correctamente.";
                        } else {
                            resultado = "1,No existe estado siguiente para la solicitud Promoción Docente.";
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

                System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_revision_secretario_academico):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_revision_secretario_academico):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_revision_secretario_academico-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_revision_secretario_academico-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String promocion_docente_acta_junta_directiva() {
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
                    + "cas.id_tipo_solicitud=1 and "
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
                        String personal = "";
                        String puesto = "";
                        Long tipoascenso = Long.parseLong("0");
                        cadenasql = "select cpd.personal, cpd.puesto, cpd.tipoascenso "
                                + "from comeval_promocion_docente cpd "
                                + "where cpd.id_comeval_promocion_docente=" + lst_comeval_acta_solicitud.get(i).getId_solicitud();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        while (rs.next()) {
                            personal = rs.getString(1);
                            puesto = rs.getString(2);
                            tipoascenso = rs.getLong(3);
                        }
                        rs.close();
                        stmt.close();

                        String puestoorigen = "";
                        cadenasql = "select p.puesto "
                                + "from puesto p "
                                + "where p.puesto in (select t.puesto from titularidad t where t.personal='" + personal + "')";
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(cadenasql);
                        while (rs.next()) {
                            puestoorigen = rs.getString(1);
                        }
                        rs.close();
                        stmt.close();

                        cadenasql = "insert into titularidadhistorial ("
                                + "personal,"
                                + "puesto,"
                                + "acta,"
                                + "fechasistema,"
                                + "titular,"
                                + "dictamen,"
                                + "puestoorigen,"
                                + "tipoascenso,"
                                + "fechaascenso) values ('"
                                + personal + "','"
                                + puesto + "',"
                                + acuerdo_acta.getNo_acta() + ","
                                + "now()" + ","
                                + "TRUE" + ","
                                + "null" + ",'"
                                + puestoorigen + "',"
                                + tipoascenso + ",'"
                                + dateFormat.format(acuerdo_acta.getFecha_acta()) + "')";
                        stmt = conn.createStatement();
                        stmt.executeUpdate(cadenasql);
                        stmt.close();

                        cadenasql = "delete from titularidad where personal='" + personal + "'";
                        stmt = conn.createStatement();
                        stmt.executeUpdate(cadenasql);
                        stmt.close();

                        cadenasql = "insert into titularidad ("
                                + "personal,"
                                + "puesto,"
                                + "acta,"
                                + "fechasistema,"
                                + "titular,"
                                + "dictamen,"
                                + "dictamenemisor) values ('"
                                + personal + "','"
                                + puesto + "',"
                                + acuerdo_acta.getNo_acta() + ","
                                + "now()" + ","
                                + "TRUE" + ","
                                + "null" + ","
                                + "1)";
                        stmt = conn.createStatement();
                        stmt.executeUpdate(cadenasql);
                        stmt.close();
                    } else {
                        aprobado_acuerdo = 0;
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
                }
            }

            resultado = "0,Solicitudes procesadas.";

            this.conn.commit();
            this.conn.setAutoCommit(true);
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_acta_junta_directiva):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_acta_junta_directiva):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_acta_junta_directiva-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_acta_junta_directiva-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

}
