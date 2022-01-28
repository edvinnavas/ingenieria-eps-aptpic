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
import java.io.File;
import java.io.InputStream;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.io.FileUtils;

public class Ctrl_Driver implements Serializable {

    private static final long serialVersionUID = 1L;
    private String jndi_nombre;
    private Connection conn;

    public Ctrl_Driver(String jndi_nombre) {
        try {
            this.jndi_nombre = jndi_nombre;
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - Ctrl_Driver):" + ex.toString());
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

    public String driver_comeval(String cadenasql) {
        String resultado = "";

        List<String> lista_drive = new ArrayList<>();
        String linea_drive = "";
        Integer columnas = 0;
        Integer filas = 0;

        try {
            this.Abrir_Conexion();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(cadenasql);
            ResultSetMetaData metadatos = rs.getMetaData();
            columnas = metadatos.getColumnCount();
            while (rs.next()) {
                filas++;
            }

            //Agrega los nombre de las columnas a la tabla.
            Integer i = 0;
            for (Integer j = 0; j < columnas; j++) {
                if (j == 0) {
                    linea_drive = metadatos.getColumnLabel(j + 1);
                } else {
                    linea_drive = linea_drive + "♣" + metadatos.getColumnLabel(j + 1);
                }
            }
            rs.close();
            stmt.close();
            lista_drive.add(linea_drive);

            //Llena tabla con la informacion de la consulta.
            i = 1;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(cadenasql);
            while (rs.next()) {
                for (Integer j = 0; j < columnas; j++) {
                    if (rs.getString(j + 1) == null) {
                        if (j == 0) {
                            linea_drive = "-";
                        } else {
                            linea_drive = linea_drive + "♣" + "-";
                        }
                    } else {
                        char caracter_old = (char) 31;
                        char caracter_new = (char) 32;
                        if (j == 0) {
                            linea_drive = rs.getString(j + 1).replace(caracter_old, caracter_new);
                        } else {
                            linea_drive = linea_drive + "♣" + rs.getString(j + 1).replace(caracter_old, caracter_new);
                        }
                    }
                }
                lista_drive.add(linea_drive);
                i++;
            }
            rs.close();
            stmt.close();

            resultado = new Gson().toJson(lista_drive);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - driver_comeval):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - driver_comeval):" + ex.toString();
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String autenticar(String usuario, String contrasena, Long id_rol, Long id_unidad) {
        String resultado = "2,Usuario " + usuario + " no autenticado.";

        try {
            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            String cadenasql = "select "
                    + "u.usuario usuario, "
                    + "trim(u.nombre) || ' ' || trim(u.apellido) nombre, "
                    + "u.contrasenia contrasena, "
                    + "a.rol id_rol, "
                    + "a.unidad id_unidad "
                    + "from "
                    + "usuario u "
                    + "left join acceso a on (u.usuario = a.usuario) "
                    + "where "
                    + "u.usuario='" + usuario + "' and "
                    + "u.contrasenia=MD5('" + contrasena + "') and "
                    + "a.rol=" + id_rol + " and "
                    + "a.unidad=" + id_unidad;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(cadenasql);
            while (rs.next()) {
                entidad.Usuario entidad_usuario = new entidad.Usuario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getLong(4), rs.getLong(5));
                resultado = new Gson().toJson(entidad_usuario);
            }
            rs.close();
            stmt.close();

            this.conn.commit();
            this.conn.setAutoCommit(true);
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - login):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - login):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - login-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - login-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String siguiente_estado_solicitud(Long id_solicitud, Long id_estado_solicitud_actual, Long id_tipo_solicitud_actual) {
        String resultado = "";

        try {
            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            Boolean validado = false;
            String mensaje_no_valido = "";
            
            // WORKFLOW AMONESTACION DOCENTE.
            if (id_tipo_solicitud_actual == Long.parseLong("3")) {
                if (id_estado_solicitud_actual == Long.parseLong("1")) {
                    validado = true;
                }
            }

            // WORKFLOW CAMBIO DE HORARIO.
            if (id_tipo_solicitud_actual == Long.parseLong("4")) {
                if (id_estado_solicitud_actual == Long.parseLong("1")) {
                    validado = true;
                }

                if (id_estado_solicitud_actual == Long.parseLong("2")) {
                    String cadenasql = "select cch.visto_bueno_director "
                            + "from comeval_cambio_horario cch "
                            + "where "
                            + "cch.id_comeval_cambio_horario=" + id_solicitud;
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    Integer visto_bueno_director = 0;
                    while (rs.next()) {
                        visto_bueno_director = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (visto_bueno_director == 0) {
                        validado = false;
                        mensaje_no_valido = "Debe marcar la casilla Visto bueno Director en el formulario.";
                    } else {
                        validado = true;
                    }
                }

                if (id_estado_solicitud_actual == Long.parseLong("3")) {
                    String cadenasql = "select cch.visto_bueno_secretario_academico "
                            + "from comeval_cambio_horario cch "
                            + "where "
                            + "cch.id_comeval_cambio_horario=" + id_solicitud;
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    Integer visto_bueno_secretario_academico = 0;
                    while (rs.next()) {
                        visto_bueno_secretario_academico = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (visto_bueno_secretario_academico == 0) {
                        validado = false;
                        mensaje_no_valido = "Debe marcar la casilla Visto bueno Secretario Académico en el formulario.";
                    } else {
                        validado = true;
                    }
                }
            }

            // WORKFLOW AMPLIACION DE HORARIO.
            if (id_tipo_solicitud_actual == Long.parseLong("5")) {
                if (id_estado_solicitud_actual == Long.parseLong("1")) {
                    validado = true;
                }

                if (id_estado_solicitud_actual == Long.parseLong("2")) {
                    String cadenasql = "select cah.visto_bueno_director "
                            + "from comeval_ampliacion_horario cah "
                            + "where "
                            + "cah.id_comeval_ampliacion_horario=" + id_solicitud;
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    Integer visto_bueno_director = 0;
                    while (rs.next()) {
                        visto_bueno_director = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (visto_bueno_director == 0) {
                        validado = false;
                        mensaje_no_valido = "Debe marcar la casilla Visto bueno Director en el formulario.";
                    } else {
                        validado = true;
                    }
                }

                if (id_estado_solicitud_actual == Long.parseLong("3")) {
                    String cadenasql = "select cah.visto_bueno_secretario_academico "
                            + "from comeval_ampliacion_horario cah "
                            + "where "
                            + "cah.id_comeval_ampliacion_horario=" + id_solicitud;
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    Integer visto_bueno_secretario_academico = 0;
                    while (rs.next()) {
                        visto_bueno_secretario_academico = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (visto_bueno_secretario_academico == 0) {
                        validado = false;
                        mensaje_no_valido = "Debe marcar la casilla Visto bueno Secretario Académico en el formulario.";
                    } else {
                        validado = true;
                    }
                }

                if (id_estado_solicitud_actual == Long.parseLong("5")) {
                    String cadenasql = "select cah.notificacion_tesoreria "
                            + "from comeval_ampliacion_horario cah "
                            + "where "
                            + "cah.id_comeval_ampliacion_horario=" + id_solicitud;
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(cadenasql);
                    Integer notificacion_tesoreria = 0;
                    while (rs.next()) {
                        notificacion_tesoreria = rs.getInt(1);
                    }
                    rs.close();
                    stmt.close();

                    if (notificacion_tesoreria == 0) {
                        validado = false;
                        mensaje_no_valido = "Debe marcar la casilla Notificación Tesorería en el formulario.";
                    } else {
                        validado = true;
                    }
                }
            }

            // CARGA EVALUACION DOCENTE.
            if (id_tipo_solicitud_actual == Long.parseLong("6")) {
                if (id_estado_solicitud_actual == Long.parseLong("1")) {
                    validado = true;
                }
            }
            
            this.conn.commit();
            this.conn.setAutoCommit(true);
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - siguiente_estado_solicitud):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - siguiente_estado_solicitud):" + ex.toString();
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - siguiente_estado_solicitud-1):" + ex.toString());
                resultado = "1,ERROR (" + this.getClass().getName() + " - siguiente_estado_solicitud-1):" + ex.toString();
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public String nombre_usuario(String usuario) {
        String resultado = "";

        try {
            this.Abrir_Conexion();

            String cadenasql = "select trim(u.nombre) || ' ' || trim(u.apellido) nombre "
                    + "from usuario u "
                    + "where u.usuario='" + usuario + "'";
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(cadenasql);
            String nombre_solicitante = "";
            while (rs.next()) {
                nombre_solicitante = rs.getString(1);
            }
            rs.close();
            stmt.close();

            resultado = nombre_solicitante;
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - nombre_usuario):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - nombre_usuario):" + ex.toString();
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public Long insertar_solictid_db_actas(entidad.Solicitud_Acta solicitud_acta, String personal, String nombre_solicitante, String id_archivo_personal2, Long id_asunto) {
        Long resultado = Long.parseLong("0");

        try {
            this.Abrir_Conexion();
            this.conn.setAutoCommit(false);

            // String cadenasql = "select nextval('seq_solicitud')";
            String cadenasql = "select coalesce(max(s.solicitud) + 1, 1) max_id_solicitud from solicitud s";
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(cadenasql);
            Long max_id_solicitud = Long.parseLong("0");
            while (rs.next()) {
                max_id_solicitud = rs.getLong(1);
            }
            rs.close();
            stmt.close();
            solicitud_acta.setId_solicitud(max_id_solicitud);
            solicitud_acta.setEstado(Long.parseLong("0"));
            solicitud_acta.setAsunto(id_asunto);
            solicitud_acta.setFacultadorigen("FACULTAD DE INGENIERÍA");
            solicitud_acta.setFacultaddestino("FACULTAD DE INGENIERÍA");
            solicitud_acta.setUniversidadorigen("USAC");
            solicitud_acta.setUniversidaddestino("USAC");
            Integer anio_actual_temp = Calendar.getInstance().get(Calendar.YEAR);
            Integer mes_actual = Calendar.getInstance().get(Calendar.MONTH);
            Integer periodo_actual_temp = 0;
            if (mes_actual <= 6) {
                periodo_actual_temp = 1;
            } else {
                periodo_actual_temp = 2;
            }

            String id_archivo = "";
            if(id_asunto.equals(Long.parseLong("43")) || id_asunto.equals(Long.parseLong("37"))) {
                servicio.cliente.Cliente_RestHeart_API_1 cliente_restheart_api_1 = new servicio.cliente.Cliente_RestHeart_API_1("admin", "changeit");
                InputStream inputstream = cliente_restheart_api_1.descargar_adjunto_binary(id_archivo_personal2);

                File archivo_rest = File.createTempFile("archivo_temporal", "tmp");
                archivo_rest.deleteOnExit();
                FileUtils.copyInputStreamToFile(inputstream, archivo_rest);

                String prefijo_archivo = "";
                if(id_asunto.equals(Long.parseLong("43"))) {
                    prefijo_archivo = "spd_";
                }
                if(id_asunto.equals(Long.parseLong("37"))) {
                    prefijo_archivo = "ned_";
                }
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddkkmmss");
                id_archivo = prefijo_archivo + dateFormat.format(new Date());
                servicio.cliente.Cliente_RestHeart_API cliente_restheart_api = new servicio.cliente.Cliente_RestHeart_API("admin", "changeit");
                cliente_restheart_api.cargar_archivo(archivo_rest, id_archivo);
            }
            
            solicitud_acta.setId_archivo(id_archivo);
            solicitud_acta.setAnio(Long.parseLong(anio_actual_temp.toString()));
            solicitud_acta.setPeriodo(Long.parseLong(periodo_actual_temp.toString()));
            solicitud_acta.setImpresa("false");
            solicitud_acta.setCon_asignacion("false");

            cadenasql = "insert into solicitud ("
                    + "solicitud, "
                    + "estado, "
                    + "asunto, "
                    + "carreraorigen, "
                    + "carreradestino, "
                    + "solicitanteexterno, "
                    + "descripcion, "
                    + "fechaaprobacion, "
                    + "fechaingreso, "
                    + "facultadorigen, "
                    + "facultaddestino, "
                    + "universidadorigen, "
                    + "universidaddestino, "
                    + "scarreraorigen, "
                    + "id_archivo, "
                    + "estado_validacion, "
                    + "anio, "
                    + "periodo, "
                    + "transaccion, "
                    + "impresa, "
                    + "tipoaprobacion,"
                    + "con_asignacion) values ("
                    + solicitud_acta.getId_solicitud() + ",'"
                    + solicitud_acta.getEstado() + "',"
                    + solicitud_acta.getAsunto() + ","
                    + "null" + ","
                    + "null" + ",'"
                    + solicitud_acta.getSolicitanteexterno() + "','"
                    + solicitud_acta.getDescripcion() + "',"
                    + "null" + ","
                    + "now()" + ",'"
                    + solicitud_acta.getFacultadorigen() + "','"
                    + solicitud_acta.getFacultaddestino() + "','"
                    + solicitud_acta.getUniversidadorigen() + "','"
                    + solicitud_acta.getUniversidaddestino() + "',"
                    + "null" + ",'"
                    + solicitud_acta.getId_archivo() + "',"
                    + "null" + ","
                    + solicitud_acta.getAnio() + ","
                    + solicitud_acta.getPeriodo() + ","
                    + "null" + ","
                    + solicitud_acta.getImpresa() + ","
                    + "null" + ","
                    + solicitud_acta.getCon_asignacion() + ")";
            stmt = this.conn.createStatement();
            stmt.executeUpdate(cadenasql);
            stmt.close();

            cadenasql = "insert into solicitud_solicitante ("
                    + "personal,"
                    + "seq_solicitante,"
                    + "solicitud,"
                    + "titulo,"
                    + "tiposolicitante,"
                    + "nombre,"
                    + "empresa,"
                    + "subpartida,"
                    + "carnet) values ('"
                    + personal + "',"
                    + "1" + ","
                    + solicitud_acta.getId_solicitud() + ","
                    + "1" + ","
                    + "1" + ",'"
                    + nombre_solicitante + "','"
                    + "Aplicación Procesos Titulares" + "',"
                    + "null" + ","
                    + "null" + ")";
            stmt = this.conn.createStatement();
            stmt.executeUpdate(cadenasql);
            stmt.close();

            this.conn.commit();
            this.conn.setAutoCommit(true);

            resultado = solicitud_acta.getId_solicitud();
        } catch (Exception ex) {
            try {
                this.conn.rollback();

                System.out.println("1,ERROR (" + this.getClass().getName() + " - insertar_solictid_db_actas):" + ex.toString());
                resultado = Long.parseLong("0");;
            } catch (Exception ex1) {
                System.out.println("1,ERROR (" + this.getClass().getName() + " - insertar_solictid_db_actas-1):" + ex.toString());
                resultado = Long.parseLong("0");;
            }
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

    public entidad.Acuerdo_Acta obtener_acta_resolucion(Long id_solicitud_acta) {
        entidad.Acuerdo_Acta resultado = new entidad.Acuerdo_Acta();
        resultado.setTiene_contenido(false);

        try {
            this.Abrir_Conexion();

            String cadenasql = "select sc.contenido from solicitud_contenido sc where sc.solicitud=" + id_solicitud_acta;
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(cadenasql);
            while (rs.next()) {
                resultado.setContenido(rs.getLong(1));
                resultado.setTiene_contenido(true);
            }
            rs.close();
            stmt.close();

            if (resultado.getTiene_contenido()) {
                cadenasql = "select "
                        + "coalesce(contenido_hijo.numeroacta,0) no_acta, "
                        + "coalesce(contenido_hijo.anioacta,0) anio_acta, "
                        + "coalesce(contenido_padre.numero,0) punto, "
                        + "coalesce(contenido_hijo.numero,0) inciso, "
                        + "coalesce(contenido_hijo.tipoacta ,0) tipoacta "
                        + "from "
                        + "contenido contenido_hijo "
                        + "inner join contenido contenido_padre on (contenido_padre.contenido = contenido_hijo.contenidopadre) "
                        + "inner join solicitud_contenido sc on (sc.contenido = contenido_hijo.contenido) "
                        + "where "
                        + "sc.solicitud =" + id_solicitud_acta;
                stmt = this.conn.createStatement();
                rs = stmt.executeQuery(cadenasql);
                Long tipoacta = Long.parseLong("0");
                while (rs.next()) {
                    resultado.setNo_acta(rs.getLong(1));
                    resultado.setAnio_acta(rs.getLong(2));
                    resultado.setPunto_acta(rs.getLong(3));
                    resultado.setInciso_acta(rs.getLong(4));
                    tipoacta = rs.getLong(5);
                }
                rs.close();
                stmt.close();
                
                cadenasql = "select r.acuerdo, r.aprobado from resolucion r where r.contenido=" + resultado.getContenido();
                stmt = this.conn.createStatement();
                rs = stmt.executeQuery(cadenasql);
                while (rs.next()) {
                    resultado.setAcuerdo(rs.getString(1));
                    resultado.setAprobado(rs.getBoolean(2));
                }
                rs.close();
                stmt.close();

                cadenasql = "select coalesce(s.fecha, current_date) fecha_acta "
                        + "from acta a "
                        + "left join sesion s on (a.sesion=s.sesion) "
                        + "where a.numero=" + resultado.getNo_acta() + " and a.anio=" + resultado.getAnio_acta() + " and a.tipoacta=" + tipoacta;
                stmt = this.conn.createStatement();
                rs = stmt.executeQuery(cadenasql);
                while (rs.next()) {
                    resultado.setFecha_acta(rs.getDate(1));
                }
                rs.close();
                stmt.close();
            }
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - insertar_solictid_db_actas):" + ex.toString());
            resultado.setTiene_contenido(false);
        } finally {
            this.Cerrar_Conexion();
        }

        return resultado;
    }

}
