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
import entidad.Comeval_Notas_Evaluacion_Docente;
import entidad.Solicitud_Workflow_Historial;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ctrl_Comeval_Notas_Evaluacion_Docente implements Serializable {

    private static final long serialVersionUID = 1L;
    private String jndi_nombre;
    private Connection conn;

    public Ctrl_Comeval_Notas_Evaluacion_Docente() {
    }

    public Ctrl_Comeval_Notas_Evaluacion_Docente(String jndi_nombre) {
        try {
            this.jndi_nombre = jndi_nombre;
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - Ctrl_Comeval_Notas_Evaluacion_Docente):" + ex.toString());
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

    public String notas_evaluacion_docente_ingresar(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Notas_Evaluacion_Docente>>() {
            }.getType();
            List<Comeval_Notas_Evaluacion_Docente> lst_comeval_notas_evaluacion_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_notas_evaluacion_docente.size(); i++) {
                Long id_comeval_notas_evaluacion_docente = Long.parseLong("0");
                String cadenasql = "select coalesce(max(t.id_comeval_notas_evaluacion_docente) + 1, 1) max_id_comeval_notas_evaluacion_docente from comeval_notas_evaluacion_docente t";
                Statement stmt = this.conn.createStatement();
                ResultSet rs = stmt.executeQuery(cadenasql);
                while (rs.next()) {
                    id_comeval_notas_evaluacion_docente = rs.getLong(1);
                }
                rs.close();
                stmt.close();

                cadenasql = "insert into comeval_notas_evaluacion_docente ("
                        + "id_comeval_notas_evaluacion_docente, "
                        + "nota_ref, "
                        + "fecha_nota_ref, "
                        + "anio_notas, "
                        + "path_archivo, "
                        + "usuario, "
                        + "fecha_ingreso, "
                        + "id_estado_solicitud, "
                        + "id_tipo_solicitud, "
                        + "rechazado, "
                        + "id_estado_solicitud_rechazado, "
                        + "id_tipo_solicitud_rechazado) values ("
                        + id_comeval_notas_evaluacion_docente + ",'"
                        + lst_comeval_notas_evaluacion_docente.get(i).getNota_ref() + "','"
                        + lst_comeval_notas_evaluacion_docente.get(i).getFecha_nota_ref() + "',"
                        + lst_comeval_notas_evaluacion_docente.get(i).getAnio_notas() + ",'"
                        + lst_comeval_notas_evaluacion_docente.get(i).getPath_archivo() + "','"
                        + lst_comeval_notas_evaluacion_docente.get(i).getUsuario() + "','"
                        + lst_comeval_notas_evaluacion_docente.get(i).getFecha_ingreso() + "',"
                        + lst_comeval_notas_evaluacion_docente.get(i).getId_estado_solicitud() + ","
                        + lst_comeval_notas_evaluacion_docente.get(i).getId_tipo_solicitud() + ","
                        + lst_comeval_notas_evaluacion_docente.get(i).getRechazado() + ","
                        + "null" + ","
                        + "null" + ")";
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                for (Integer j = 0; j < lst_comeval_notas_evaluacion_docente.get(i).getLst_observaciones().size(); j++) {
                    cadenasql = "insert into comeval_solicitud_observacion ("
                            + "id_solicitud, "
                            + "id_tipo_solicitud, "
                            + "id_observacion, "
                            + "dependencia, "
                            + "fecha_hora, "
                            + "observacion) values ("
                            + id_comeval_notas_evaluacion_docente + ","
                            + lst_comeval_notas_evaluacion_docente.get(i).getLst_observaciones().get(j).getId_tipo_solicitud() + ","
                            + lst_comeval_notas_evaluacion_docente.get(i).getLst_observaciones().get(j).getId_observacion() + ",'"
                            + lst_comeval_notas_evaluacion_docente.get(i).getLst_observaciones().get(j).getDependencia() + "','"
                            + lst_comeval_notas_evaluacion_docente.get(i).getLst_observaciones().get(j).getFecha_hora() + "','"
                            + lst_comeval_notas_evaluacion_docente.get(i).getLst_observaciones().get(j).getObservacion() + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }

                Long max_id_workflow = Long.parseLong("0");
                cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + id_comeval_notas_evaluacion_docente;
                stmt = this.conn.createStatement();
                rs = stmt.executeQuery(cadenasql);
                while (rs.next()) {
                    max_id_workflow = rs.getLong(1);
                }
                rs.close();
                stmt.close();

                Solicitud_Workflow_Historial solicitud_workflow_historial = new Solicitud_Workflow_Historial();
                solicitud_workflow_historial.setId_solicitud(id_comeval_notas_evaluacion_docente);
                solicitud_workflow_historial.setId_estado_solicitud(lst_comeval_notas_evaluacion_docente.get(i).getId_estado_solicitud());
                solicitud_workflow_historial.setId_tipo_solicitud(lst_comeval_notas_evaluacion_docente.get(i).getId_tipo_solicitud());
                solicitud_workflow_historial.setId_workflow(max_id_workflow);
                solicitud_workflow_historial.setUsuario(lst_comeval_notas_evaluacion_docente.get(i).getUsuario());
                solicitud_workflow_historial.setFecha(lst_comeval_notas_evaluacion_docente.get(i).getFecha_ingreso());
                solicitud_workflow_historial.setRechazado(lst_comeval_notas_evaluacion_docente.get(i).getRechazado());
                solicitud_workflow_historial.setFecha_rechazado(lst_comeval_notas_evaluacion_docente.get(i).getFecha_ingreso());

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

            resultado = "0,Solicitud carga notas evaluación docente ingresada correctamente.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - notas_evaluacion_docente_ingresar):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - notas_evaluacion_docente_ingresar):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - notas_evaluacion_docente_ingresar-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - notas_evaluacion_docente_ingresar-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String notas_evaluacion_docente_modificar(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Notas_Evaluacion_Docente>>() {
            }.getType();
            List<Comeval_Notas_Evaluacion_Docente> lst_comeval_notas_evaluacion_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_notas_evaluacion_docente.size(); i++) {
                String id_estado_solicitud_rechazado_temp;
                String id_tipo_solicitud_rechazado_temp;
                if (lst_comeval_notas_evaluacion_docente.get(i).getRechazado() == Long.parseLong("1")) {
                    id_estado_solicitud_rechazado_temp = lst_comeval_notas_evaluacion_docente.get(i).getId_estado_solicitud_rechazado().toString();
                    id_tipo_solicitud_rechazado_temp = lst_comeval_notas_evaluacion_docente.get(i).getId_tipo_solicitud_rechazado().toString();
                } else {
                    id_estado_solicitud_rechazado_temp = "null";
                    id_tipo_solicitud_rechazado_temp = "null";
                }

                String cadenasql = "update comeval_notas_evaluacion_docente set "
                        + "nota_ref='" + lst_comeval_notas_evaluacion_docente.get(i).getNota_ref() + "', "
                        + "fecha_nota_ref='" + lst_comeval_notas_evaluacion_docente.get(i).getFecha_nota_ref() + "', "
                        + "anio_notas=" + lst_comeval_notas_evaluacion_docente.get(i).getAnio_notas() + ", "
                        + "path_archivo='" + lst_comeval_notas_evaluacion_docente.get(i).getPath_archivo() + "', "
                        + "usuario='" + lst_comeval_notas_evaluacion_docente.get(i).getUsuario() + "', "
                        + "rechazado=" + lst_comeval_notas_evaluacion_docente.get(i).getRechazado() + ", "
                        + "id_estado_solicitud_rechazado=" + id_estado_solicitud_rechazado_temp + ", "
                        + "id_tipo_solicitud_rechazado=" + id_tipo_solicitud_rechazado_temp + " "
                        + "where "
                        + "id_comeval_notas_evaluacion_docente=" + lst_comeval_notas_evaluacion_docente.get(i).getId_comeval_notas_evaluacion_docente();
                Statement stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                cadenasql = "delete from comeval_solicitud_observacion "
                        + "where id_solicitud=" + lst_comeval_notas_evaluacion_docente.get(i).getId_comeval_notas_evaluacion_docente() + " and "
                        + "id_tipo_solicitud=" + lst_comeval_notas_evaluacion_docente.get(i).getId_tipo_solicitud();
                stmt = this.conn.createStatement();
                stmt.executeUpdate(cadenasql);
                stmt.close();

                for (Integer j = 0; j < lst_comeval_notas_evaluacion_docente.get(i).getLst_observaciones().size(); j++) {
                    cadenasql = "insert into comeval_solicitud_observacion ("
                            + "id_solicitud, "
                            + "id_tipo_solicitud, "
                            + "id_observacion, "
                            + "dependencia, "
                            + "fecha_hora, "
                            + "observacion) values ("
                            + lst_comeval_notas_evaluacion_docente.get(i).getLst_observaciones().get(j).getId_solicitud() + ","
                            + lst_comeval_notas_evaluacion_docente.get(i).getLst_observaciones().get(j).getId_tipo_solicitud() + ","
                            + lst_comeval_notas_evaluacion_docente.get(i).getLst_observaciones().get(j).getId_observacion() + ",'"
                            + lst_comeval_notas_evaluacion_docente.get(i).getLst_observaciones().get(j).getDependencia() + "','"
                            + lst_comeval_notas_evaluacion_docente.get(i).getLst_observaciones().get(j).getFecha_hora() + "','"
                            + lst_comeval_notas_evaluacion_docente.get(i).getLst_observaciones().get(j).getObservacion() + "')";
                    stmt = this.conn.createStatement();
                    stmt.executeUpdate(cadenasql);
                    stmt.close();
                }
            }

            this.conn.commit();
            this.conn.setAutoCommit(true);

            resultado = "0,Solicitud carga notas evaluación docente modificada correctamente.";
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - notas_evaluacion_docente_modificar):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - notas_evaluacion_docente_modificar):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - notas_evaluacion_docente_modificar-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - notas_evaluacion_docente_modificar-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String notas_evaluacion_enviar_ingreso_secretario(String jsonString) {
        String resultado = "";

        try {
            Type listType = new TypeToken<ArrayList<Comeval_Notas_Evaluacion_Docente>>() {
            }.getType();
            List<Comeval_Notas_Evaluacion_Docente> lst_comeval_notas_evaluacion_docente = new Gson().fromJson(jsonString, listType);

            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            for (Integer i = 0; i < lst_comeval_notas_evaluacion_docente.size(); i++) {
                Boolean validado = true;
                String mensaje_no_valido = "";

                // INSERTAR SOLICITUD EN LA APLICACIÓN DE ACTAS DE JUNTA DIRECTIVA.
                if (validado) {
                    Ctrl_Driver ctrl_driver1 = new Ctrl_Driver("jndi_gestionautenticacion2");
                    
                    String nombre_solicitante = ctrl_driver1.nombre_usuario(lst_comeval_notas_evaluacion_docente.get(i).getUsuario());

                    String cadenasql = "select p.nombre "
                            + "from puesto p "
                            + "where p.puesto in (select t.puesto from titularidad t where t.personal='" + lst_comeval_notas_evaluacion_docente.get(i).getUsuario() + "')";
                    Statement stmt = this.conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    String puesto_solicitante = "";
                    while (rs.next()) {
                        puesto_solicitante = rs.getString(1);
                    }
                    rs.close();
                    stmt.close();
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                    Calendar fecha_nota_ref = Calendar.getInstance();
                    fecha_nota_ref.setTime(dateFormat.parse(lst_comeval_notas_evaluacion_docente.get(i).getFecha_nota_ref()));

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

                    entidad.Solicitud_Acta solicitud_acta = new entidad.Solicitud_Acta();
                    solicitud_acta.setSolicitanteexterno("NOMBRE-SOLICITANTE: " + nombre_solicitante + ", CÓDIGO-PERSONAL: " + lst_comeval_notas_evaluacion_docente.get(i).getUsuario());
                    solicitud_acta.setDescripcion("Se recibió nota de Ref. " + lst_comeval_notas_evaluacion_docente.get(i).getNota_ref().trim() + " de fecha " + l_dia_fecha_nota_ref + " de " + l_mes_fecha_nota_ref + " de " + anio_fecha_nota_ref + ", "
                            + "enviada por el Ing(a). " + nombre_solicitante.trim() + ", " + puesto_solicitante.trim() + " de la Facultad de Ingeniería, "
                            + "quien presenta reporte de EVALUACIONES DOCENTES DEL AÑO " + lst_comeval_notas_evaluacion_docente.get(i).getAnio_notas() + " de los profesionales.");

                    String id_archivo_personal2 = lst_comeval_notas_evaluacion_docente.get(i).getPath_archivo();
                    Ctrl_Driver ctrl_driver2 = new Ctrl_Driver("jndi_asuntosestudiantiles");
                    Long id_solicitud = ctrl_driver2.insertar_solictid_db_actas(solicitud_acta, lst_comeval_notas_evaluacion_docente.get(i).getUsuario(), nombre_solicitante, id_archivo_personal2, Long.parseLong("37"));

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
                                + lst_comeval_notas_evaluacion_docente.get(i).getId_comeval_notas_evaluacion_docente() + ","
                                + lst_comeval_notas_evaluacion_docente.get(i).getId_tipo_solicitud() + ","
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

                    String cadenasql = "select "
                            + "ws.id_estado_solicitud_siguiente, "
                            + "ws.id_tipo_solicitud_siguiente "
                            + "from "
                            + "workflow_solicitud ws "
                            + "where "
                            + "ws.id_estado_solicitud_actual=" + lst_comeval_notas_evaluacion_docente.get(i).getId_estado_solicitud() + " and "
                            + "ws.id_tipo_solicitud_actual=" + lst_comeval_notas_evaluacion_docente.get(i).getId_tipo_solicitud();
                    Statement stmt = this.conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    while (rs.next()) {
                        id_estado_solicitud_siguiente = rs.getLong(1);
                        id_tipo_solicitud_siguiente = rs.getLong(2);
                    }
                    rs.close();
                    stmt.close();

                    if (!id_estado_solicitud_siguiente.equals(Long.parseLong("0")) && !id_tipo_solicitud_siguiente.equals(Long.parseLong("0"))) {
                        cadenasql = "update comeval_notas_evaluacion_docente set "
                                + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                + "usuario='" + lst_comeval_notas_evaluacion_docente.get(i).getUsuario() + "' "
                                + "where id_comeval_notas_evaluacion_docente=" + lst_comeval_notas_evaluacion_docente.get(i).getId_comeval_notas_evaluacion_docente();
                        stmt = this.conn.createStatement();
                        stmt.executeUpdate(cadenasql);
                        stmt.close();

                        Long max_id_workflow = Long.parseLong("0");
                        cadenasql = "select coalesce(max(t.id_workflow) + 1, 1) max_id from solicitud_workflow_historial t where t.id_solicitud=" + lst_comeval_notas_evaluacion_docente.get(i).getId_comeval_notas_evaluacion_docente();
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
                                + lst_comeval_notas_evaluacion_docente.get(i).getId_comeval_notas_evaluacion_docente() + ", "
                                + id_estado_solicitud_siguiente + ","
                                + id_tipo_solicitud_siguiente + ","
                                + max_id_workflow + ",'"
                                + lst_comeval_notas_evaluacion_docente.get(i).getUsuario() + "','"
                                + dateFormat.format(fecha_actual) + "',"
                                + "0" + ","
                                + "null" + ")";
                        stmt = this.conn.createStatement();
                        stmt.executeUpdate(cadenasql);
                        stmt.close();

                        resultado = "0,Solicitud carga notas de evauación docente enviada al siguiente estado correctamente.";
                    } else {
                        resultado = "1,No existe estado siguiente para la solicitud carga notas de evauación docente.";
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

                System.out.println("1,ERROR (" + this.getClass().getName() + " - notas_evaluacion_enviar_ingreso_secretario):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - notas_evaluacion_enviar_ingreso_secretario):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - notas_evaluacion_enviar_ingreso_secretario-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - notas_evaluacion_enviar_ingreso_secretario-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }
    
    public String notas_evaluacion_acta_junta_directiva() {
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
                    + "cas.id_tipo_solicitud=6 and "
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

                    // CAMBIO DE ESTADO DE LA SOLICITUD PROMOCION DOCENTE.
                    Long id_estado_solicitud_actual = Long.parseLong("0");
                    Long id_tipo_solicitud_actual = Long.parseLong("0");
                    String usuario = "";
                    cadenasql = "select "
                            + "cld.id_estado_solicitud, "
                            + "cld.id_tipo_solicitud, "
                            + "cld.usuario "
                            + "from "
                            + "comeval_notas_evaluacion_docente cld "
                            + "where "
                            + "cld.id_comeval_notas_evaluacion_docente=" + lst_comeval_acta_solicitud.get(i).getId_solicitud();
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
                        cadenasql = "update comeval_notas_evaluacion_docente set "
                                + "id_estado_solicitud=" + id_estado_solicitud_siguiente + ", "
                                + "id_tipo_solicitud=" + id_tipo_solicitud_siguiente + ", "
                                + "usuario='" + usuario + "' "
                                + "where id_comeval_notas_evaluacion_docente=" + lst_comeval_acta_solicitud.get(i).getId_solicitud();
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

                        resultado = "0,Solicitud Notas Evaluación Docente enviada al siguiente estado correctamente.";
                    } else {
                        resultado = "1,No existe estado siguiente para la solicitud Notas Evaluación Docente.";
                    }
                }
            }

            resultado = "0,Solicitudes procesadas.";

            this.conn.commit();
            this.conn.setAutoCommit(true);
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - notas_evaluacion_acta_junta_directiva):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - notas_evaluacion_acta_junta_directiva):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - notas_evaluacion_acta_junta_directiva-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - notas_evaluacion_acta_junta_directiva-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

}
