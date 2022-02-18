package beans.Comeval_Ampliacion_Horario;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.inject.Named;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;

@Named(value = "Bean_Comeval_Ampliacion_Horario")
@ViewScoped
public class Bean_Comeval_Ampliacion_Horario implements Serializable {

    private static final long serialVersionUID = 1L;

    private entidad.Usuario usuario;

    private Long id_estado_solicitud;
    private Long id_tipo_solicitud;
    private Long id_estado_solicitud_rechazado;
    private Long id_tipo_solicitud_rechazado;
    private Long id_comeval_ampliacion_horario;
    private String personal;
    private String codigo_docente;
    private String nombre_docente;
    private Long id_tipo_ampliacion_horario;
    private Long id_plaza_temporal;
    private Long id_plaza_indefinido;        
    private String nota_ref;
    private Date fecha_nota_ref;
    private Date fecha_ingreso;
    private Long rechazado;
    private Boolean rechazado_form;
    private Long visto_bueno_director;
    private Boolean visto_bueno_director_form;
    private Long visto_bueno_secretario_academico;
    private Boolean visto_bueno_secretario_academico_form;
    private Long notificacion_tesoreria;
    private Boolean notificacion_tesoreria_form;

    private Boolean lunes;
    private Boolean martes;
    private Boolean miercoles;
    private Boolean jueves;
    private Boolean viernes;
    private Boolean sabado;
    private Boolean domingo;
    private Date hora_inicio;
    private Date hora_fin;
    
    private List<SelectItem> lst_estado_solicitud;
    private List<SelectItem> lst_tipo_solicitud;
    private List<SelectItem> lst_estado_solicitud_rechazado;
    private List<SelectItem> lst_tipo_solicitud_rechazado;
    private List<SelectItem> lst_tipo_ampliacion_horario;
    private List<SelectItem> lst_plaza_temporal;
    private List<SelectItem> lst_plaza_indefinido;

    private List<Lista_Horarios> lst_horarios;
    private Lista_Horarios sel_horarios;

    private String opcion;

    private String dependencia_observaciones;
    private String observacion;

    private List<lista_observaciones> lst_observaciones;
    private lista_observaciones sel_observaciones;

    private Boolean cbxTipoSolicitud;
    private Boolean cbxEstadoSolicitud;
    private Boolean cbxTipoSolicitudRechazado;
    private Boolean cbxEstadoSolicitudRechazado;
    private Boolean txtCodigoDocente;
    private Boolean txtNombreDocente;
    private Boolean txtNotaRef;
    private Boolean calFechaNotaRef;
    private Boolean cbxTipoAmpliacionHorario;
    private Boolean cbxPlazaTemporal;
    private Boolean cbxPlazaIndefinido;
    private Boolean chxRechazado;
    private Boolean chxVistoBuenoDirector;
    private Boolean chxVistoSecretarioAcademico;
    private Boolean chxNotificacionTesoreria;
    private Boolean chxLunes;
    private Boolean chxMartes;
    private Boolean chxMiercoles;
    private Boolean chxJueves;
    private Boolean chxViernes;
    private Boolean chxSabado;
    private Boolean chxDomingo;
    private Boolean calHoraInicio;
    private Boolean calHoraFin;
    private Boolean btnAgregarHorario;
    private Boolean btnEliminarHorario;
    private Boolean btnAgregarObservacion;
    private Boolean btnEliminarObservacion;
    private Boolean btnAceptar;
    private Boolean btnCancelar;

    public Bean_Comeval_Ampliacion_Horario() {
        try {
            this.lst_estado_solicitud = new ArrayList<>();
            this.lst_tipo_solicitud = new ArrayList<>();
            this.lst_estado_solicitud_rechazado = new ArrayList<>();
            this.lst_tipo_solicitud_rechazado = new ArrayList<>();
            this.lst_tipo_ampliacion_horario = new ArrayList<>();
            this.lst_plaza_temporal = new ArrayList<>();
            this.lst_plaza_indefinido = new ArrayList<>();
            this.lst_horarios = new ArrayList<>();
            this.lst_observaciones = new ArrayList<>();
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Bean_Comeval_Ampliacion_Horario ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void mostrar_crear_ampliacion_horario(entidad.Usuario usuario, Long id_estado_solicitud, Long id_tipo_solicitud) {
        try {
            this.usuario = usuario;
            this.opcion = "CREAR";

            this.lst_tipo_solicitud = new ArrayList<>();
            String cadenasql = "select f.id_tipo_solicitud, f.nombre from tipo_solicitud_comeval f order by f.nombre";
            servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            String jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            Type listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            List<String> lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] rol = lista_drive.get(i).split("♣");
                this.lst_tipo_solicitud.add(new SelectItem(Long.parseLong(rol[0]), rol[1]));
            }
            this.id_tipo_solicitud = id_tipo_solicitud;

            this.lst_estado_solicitud = new ArrayList<>();
            cadenasql = "select f.id_estado_solicitud, f.nombre from estado_solicitud_comeval f where f.id_tipo_solicitud = " + this.id_tipo_solicitud + " order by f.nombre";
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] rol = lista_drive.get(i).split("♣");
                this.lst_estado_solicitud.add(new SelectItem(Long.parseLong(rol[0]), rol[1]));
            }
            this.id_estado_solicitud = id_estado_solicitud;

            this.lst_tipo_solicitud_rechazado = this.lst_tipo_solicitud;
            this.id_tipo_solicitud_rechazado = Long.parseLong("1");

            this.lst_tipo_ampliacion_horario = new ArrayList<>();
            this.lst_tipo_ampliacion_horario.add(new SelectItem(Long.parseLong("1"), "Temporal"));
            this.lst_tipo_ampliacion_horario.add(new SelectItem(Long.parseLong("2"), "Permanente"));
            
            this.lst_plaza_temporal = new ArrayList<>();
            this.lst_plaza_indefinido = new ArrayList<>();
            
            cadenasql = "select p.plaza, p.numero, pp.subpartida, count(*) numero "
                    + "from plaza p left join plazapartida pp on (p.plaza = pp.plaza) "
                    + "where pp.plaza not in (select d.plaza from plazapersonal d) and pp.estado = 0 "
                    + "group by p.plaza, p.numero, pp.subpartida "
                    + "order by p.numero, pp.subpartida";
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] plaza = lista_drive.get(i).split("♣");
                this.lst_plaza_temporal.add(new SelectItem(Long.parseLong(plaza[0]), plaza[1] + "-" + plaza[2]));
                this.lst_plaza_indefinido.add(new SelectItem(Long.parseLong(plaza[0]), plaza[1] + "-" + plaza[2]));
            }
            
            this.lst_estado_solicitud_rechazado = this.lst_estado_solicitud;
            this.id_estado_solicitud_rechazado = Long.parseLong("1");
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            this.id_comeval_ampliacion_horario = Long.parseLong("1");
            this.personal = "0";
            this.codigo_docente = this.usuario.getUsuario();
            this.nombre_docente = "-";
            this.datos_docente();
            this.id_tipo_ampliacion_horario = Long.parseLong("1");
            this.id_plaza_temporal = Long.parseLong("0");
            this.id_plaza_indefinido = Long.parseLong("0");
            this.nota_ref = "";
            this.fecha_nota_ref = new Date();
            this.fecha_ingreso = dateFormat.parse("1900-01-01 00:00:00");

            this.rechazado = Long.parseLong("0");
            this.rechazado_form = false;
            this.visto_bueno_director = Long.parseLong("0");
            this.visto_bueno_director_form = false;
            this.visto_bueno_secretario_academico = Long.parseLong("0");
            this.visto_bueno_secretario_academico_form = false;
            this.notificacion_tesoreria = Long.parseLong("0");
            this.notificacion_tesoreria_form = false;
            
            this.lunes = false;
            this.martes = false;
            this.miercoles = false;
            this.jueves = false;
            this.viernes = false;
            this.sabado = false;
            this.domingo = false;
            this.hora_inicio = new Date();
            this.hora_fin = new Date();

            this.lst_horarios = new ArrayList<>();

            if (this.id_estado_solicitud.equals(Long.parseLong("1"))) {
                this.dependencia_observaciones = "DOCENTE";
            }
            if (this.id_estado_solicitud.equals(Long.parseLong("2"))) {
                this.dependencia_observaciones = "ESCUELA";
            }
            if (this.id_estado_solicitud.equals(Long.parseLong("3"))) {
                this.dependencia_observaciones = "SECRETARIO ACADÉMICO";
            }
            if (this.id_estado_solicitud.equals(Long.parseLong("5"))) {
                this.dependencia_observaciones = "TESORERÍA";
            }

            this.lst_observaciones = new ArrayList<>();

            this.cbxTipoSolicitud = true;
            this.cbxEstadoSolicitud = true;
            this.cbxTipoSolicitudRechazado = true;
            this.cbxEstadoSolicitudRechazado = true;
            this.txtCodigoDocente = false;
            this.txtNombreDocente = true;
            this.txtNotaRef = true;
            this.calFechaNotaRef = true;
            this.cbxTipoAmpliacionHorario = false;
            this.cbxPlazaTemporal = true;
            this.cbxPlazaIndefinido = true;
            this.chxRechazado = true;
            this.chxVistoBuenoDirector = true;
            this.chxVistoSecretarioAcademico = true;
            this.chxNotificacionTesoreria = true;
            this.chxLunes = false;
            this.chxMartes = false;
            this.chxMiercoles = false;
            this.chxJueves = false;
            this.chxViernes = false;
            this.chxSabado = false;
            this.chxDomingo = false;
            this.calHoraInicio = false;
            this.calHoraFin = false;
            this.btnAgregarHorario = false;
            this.btnEliminarHorario = false;
            this.btnAgregarObservacion = false;
            this.btnEliminarObservacion = false;
            this.btnAceptar = false;
            this.btnCancelar = false;
            
            PrimeFaces.current().executeScript("PF('ComevalAmpliacionHorarioDialogVar').show();");
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: mostrar_crear_ampliacion_horario ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void mostrar_modificar_ampliacion_horario(entidad.Usuario usuario, Long id_comeval_ampliacion_horario, Long id_estado_solicitud, Long id_tipo_solicitud) {
        try {
            this.usuario = usuario;
            this.opcion = "MODIFICAR";

            this.lst_tipo_solicitud = new ArrayList<>();
            String cadenasql = "select f.id_tipo_solicitud, f.nombre from tipo_solicitud_comeval f order by f.nombre";
            servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            String jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            Type listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            List<String> lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] rol = lista_drive.get(i).split("♣");
                this.lst_tipo_solicitud.add(new SelectItem(Long.parseLong(rol[0]), rol[1]));
            }

            this.lst_estado_solicitud = new ArrayList<>();
            cadenasql = "select f.id_estado_solicitud, f.nombre from estado_solicitud_comeval f where f.id_tipo_solicitud = " + id_tipo_solicitud + " order by f.nombre";
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] rol = lista_drive.get(i).split("♣");
                this.lst_estado_solicitud.add(new SelectItem(Long.parseLong(rol[0]), rol[1]));
            }
            this.lst_tipo_solicitud_rechazado = this.lst_tipo_solicitud;

            this.lst_estado_solicitud_rechazado = new ArrayList<>();
            cadenasql = "select distinct swh.id_estado_solicitud, esc.nombre "
                    + "from solicitud_workflow_historial swh "
                    + "left join estado_solicitud_comeval esc on (swh.id_tipo_solicitud=esc.id_tipo_solicitud and swh.id_estado_solicitud=esc.id_estado_solicitud) "
                    + "where swh.id_tipo_solicitud=" + id_tipo_solicitud + " and swh.id_solicitud=" + id_comeval_ampliacion_horario + " and swh.id_estado_solicitud < " + id_estado_solicitud;
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] rol = lista_drive.get(i).split("♣");
                this.lst_estado_solicitud_rechazado.add(new SelectItem(Long.parseLong(rol[0]), rol[1]));
            }

            this.lst_tipo_ampliacion_horario = new ArrayList<>();
            this.lst_tipo_ampliacion_horario.add(new SelectItem(Long.parseLong("1"), "Temporal"));
            this.lst_tipo_ampliacion_horario.add(new SelectItem(Long.parseLong("2"), "Permanente"));
            
            this.lst_plaza_temporal = new ArrayList<>();
            this.lst_plaza_indefinido = new ArrayList<>();
            
            cadenasql = "select p.plaza, p.numero, pp.subpartida, count(*) numero "
                    + "from plaza p left join plazapartida pp on (p.plaza = pp.plaza) "
                    + "where pp.plaza not in (select d.plaza from plazapersonal d) and pp.estado = 0 "
                    + "group by p.plaza, p.numero, pp.subpartida "
                    + "order by p.numero, pp.subpartida";
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] plaza = lista_drive.get(i).split("♣");
                this.lst_plaza_temporal.add(new SelectItem(Long.parseLong(plaza[0]), plaza[1] + "-" + plaza[2]));
                this.lst_plaza_indefinido.add(new SelectItem(Long.parseLong(plaza[0]), plaza[1] + "-" + plaza[2]));
            }

            cadenasql = "select "
                    + "t.id_comeval_ampliacion_horario, "
                    + "t.personal, "
                    + "t.id_tipo_ampliacion_horario, "
                    + "t.id_plaza_temporal, "
                    + "t.id_plaza_indefinido, "
                    + "t.nota_ref, "
                    + "t.fecha_nota_ref, "
                    + "t.fecha_ingreso, "
                    + "t.id_estado_solicitud, "
                    + "t.id_tipo_solicitud, "
                    + "t.rechazado, "
                    + "t.visto_bueno_director_escuela, "
                    + "t.visto_bueno_secretario_academico, "
                    + "t.notificacion_tesoreria, "
                    + "coalesce(t.id_estado_solicitud_rechazado, 1) id_estado_solicitud_rechazado, "
                    + "coalesce(t.id_tipo_solicitud_rechazado, " + id_tipo_solicitud + ") id_tipo_solicitud_rechazado "
                    + "from "
                    + "comeval_ampliacion_horario t "
                    + "where "
                    + "t.id_comeval_ampliacion_horario=" + id_comeval_ampliacion_horario;
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] col = lista_drive.get(i).split("♣");
                this.id_comeval_ampliacion_horario = Long.parseLong(col[0]);
                this.personal = col[1];
                this.codigo_docente = col[1];
                this.datos_docente();
                this.id_tipo_ampliacion_horario = Long.parseLong(col[2]);
                this.id_plaza_temporal = Long.parseLong(col[3]);
                this.id_plaza_indefinido = Long.parseLong(col[4]);
                this.nota_ref = col[5];
                this.fecha_nota_ref = dateFormat.parse(col[6]);
                this.fecha_ingreso = dateFormat.parse(col[7]);
                this.id_estado_solicitud = Long.parseLong(col[8]);
                this.id_tipo_solicitud = Long.parseLong(col[9]);
                this.rechazado = Long.parseLong(col[10]);
                if (this.rechazado == Long.parseLong("0")) {
                    this.rechazado_form = false;
                } else {
                    this.rechazado_form = true;
                }
                this.visto_bueno_director = Long.parseLong(col[11]);
                if (this.visto_bueno_director == Long.parseLong("0")) {
                    this.visto_bueno_director_form = false;
                } else {
                    this.visto_bueno_director_form = true;
                }
                this.visto_bueno_secretario_academico = Long.parseLong(col[12]);
                if (this.visto_bueno_secretario_academico == Long.parseLong("0")) {
                    this.visto_bueno_secretario_academico_form = false;
                } else {
                    this.visto_bueno_secretario_academico_form = true;
                }
                this.notificacion_tesoreria = Long.parseLong(col[13]);
                if (this.notificacion_tesoreria == Long.parseLong("0")) {
                    this.notificacion_tesoreria_form = false;
                } else {
                    this.notificacion_tesoreria_form = true;
                }
                this.id_estado_solicitud_rechazado = Long.parseLong(col[14]);
                this.id_tipo_solicitud_rechazado = Long.parseLong(col[15]);
            }
            
            this.lst_horarios = new ArrayList<>();
            cadenasql = "select "
                    + "t.id_comeval_ampliacion_horario, "
                    + "t.horainicio, "
                    + "t.horafin, "
                    + "t.dias "
                    + "from "
                    + "comeval_ampliacion_horario_plaza t "
                    + "where "
                    + "t.id_comeval_ampliacion_horario = " + id_comeval_ampliacion_horario;
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] col = lista_drive.get(i).split("♣");
                Lista_Horarios lista_horarios = new Lista_Horarios();
                lista_horarios.setId_horario(Long.parseLong(col[0]));
                lista_horarios.setHora_inicio(col[1]);
                lista_horarios.setHora_final(col[2]);
                lista_horarios.setDias(col[3]);
                this.lst_horarios.add(lista_horarios);
            }
            
            this.lst_observaciones = new ArrayList<>();
            cadenasql = "select "
                    + "t.id_observacion, "
                    + "t.dependencia, "
                    + "t.fecha_hora, "
                    + "t.observacion "
                    + "from "
                    + "comeval_solicitud_observacion t "
                    + "where "
                    + "t.id_solicitud=" + this.id_comeval_ampliacion_horario + " and "
                    + "t.id_tipo_solicitud=" + this.id_tipo_solicitud + " "
                    + "order by "
                    + "t.id_observacion";
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] col = lista_drive.get(i).split("♣");
                lista_observaciones observacion_temp = new lista_observaciones(Long.parseLong(col[0]), col[1], col[2], col[3]);
                this.lst_observaciones.add(observacion_temp);
            }

            if (this.id_estado_solicitud == Long.parseLong("1")) {
                this.dependencia_observaciones = "DOCENTE";
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.cbxTipoSolicitudRechazado = true;
                if(this.rechazado_form) {
                    this.cbxEstadoSolicitudRechazado = false;
                } else {
                    this.cbxEstadoSolicitudRechazado = true;
                }
                this.txtCodigoDocente = false;
                this.txtNombreDocente = true;
                this.txtNotaRef = true;
                this.calFechaNotaRef = true;
                this.cbxTipoAmpliacionHorario = false;
                this.cbxPlazaTemporal = true;
                this.cbxPlazaIndefinido = true;
                this.chxRechazado = true;
                this.chxVistoBuenoDirector = true;
                this.chxVistoSecretarioAcademico = true;
                this.chxNotificacionTesoreria = true;
                this.chxLunes = false;
                this.chxMartes = false;
                this.chxMiercoles = false;
                this.chxJueves = false;
                this.chxViernes = false;
                this.chxSabado = false;
                this.chxDomingo = false;
                this.calHoraInicio = false;
                this.calHoraFin = false;
                this.btnAgregarHorario = false;
                this.btnEliminarHorario = false;
                this.btnAgregarObservacion = false;
                this.btnEliminarObservacion = false;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("2")) {
                this.dependencia_observaciones = "ESCUELA";
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.cbxTipoSolicitudRechazado = true;
                if(this.rechazado_form) {
                    this.cbxEstadoSolicitudRechazado = false;
                } else {
                    this.cbxEstadoSolicitudRechazado = true;
                }
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.txtNotaRef = false;
                this.calFechaNotaRef = false;
                this.cbxTipoAmpliacionHorario = false;
                this.cbxPlazaTemporal = true;
                this.cbxPlazaIndefinido = true;
                this.chxRechazado = false;
                this.chxVistoBuenoDirector = false;
                this.chxVistoSecretarioAcademico = true;
                this.chxNotificacionTesoreria = true;
                this.chxLunes = true;
                this.chxMartes = true;
                this.chxMiercoles = true;
                this.chxJueves = true;
                this.chxViernes = true;
                this.chxSabado = true;
                this.chxDomingo = true;
                this.calHoraInicio = true;
                this.calHoraFin = true;
                this.btnAgregarHorario = true;
                this.btnEliminarHorario = true;
                this.btnAgregarObservacion = false;
                this.btnEliminarObservacion = false;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("3")) {
                this.dependencia_observaciones = "SECRETARIO ACADÉMICO";
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.cbxTipoSolicitudRechazado = true;
                if(this.rechazado_form) {
                    this.cbxEstadoSolicitudRechazado = false;
                } else {
                    this.cbxEstadoSolicitudRechazado = true;
                }
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.txtNotaRef = false;
                this.calFechaNotaRef = false;
                this.cbxTipoAmpliacionHorario = false;
                this.cbxPlazaTemporal = false;
                this.cbxPlazaIndefinido = false;
                this.chxRechazado = false;
                this.chxVistoBuenoDirector = true;
                this.chxVistoSecretarioAcademico = false;
                this.chxNotificacionTesoreria = true;
                this.chxLunes = true;
                this.chxMartes = true;
                this.chxMiercoles = true;
                this.chxJueves = true;
                this.chxViernes = true;
                this.chxSabado = true;
                this.chxDomingo = true;
                this.calHoraInicio = true;
                this.calHoraFin = true;
                this.btnAgregarHorario = true;
                this.btnEliminarHorario = true;
                this.btnAgregarObservacion = false;
                this.btnEliminarObservacion = false;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("5")) {
                this.dependencia_observaciones = "TESORERÍA";
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.cbxTipoSolicitudRechazado = true;
                if(this.rechazado_form) {
                    this.cbxEstadoSolicitudRechazado = false;
                } else {
                    this.cbxEstadoSolicitudRechazado = true;
                }
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.txtNotaRef = true;
                this.calFechaNotaRef = true;
                this.cbxTipoAmpliacionHorario = true;
                this.cbxPlazaTemporal = true;
                this.cbxPlazaIndefinido = true;
                this.chxRechazado = false;
                this.chxVistoBuenoDirector = true;
                this.chxVistoSecretarioAcademico = true;
                this.chxNotificacionTesoreria = false;
                this.chxLunes = true;
                this.chxMartes = true;
                this.chxMiercoles = true;
                this.chxJueves = true;
                this.chxViernes = true;
                this.chxSabado = true;
                this.chxDomingo = true;
                this.calHoraInicio = true;
                this.calHoraFin = true;
                this.btnAgregarHorario = true;
                this.btnEliminarHorario = true;
                this.btnAgregarObservacion = false;
                this.btnEliminarObservacion = false;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }

            PrimeFaces.current().executeScript("PF('ComevalAmpliacionHorarioDialogVar').show();");
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: mostrar_modificar_ampliacion_horario ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void aceptar() {
        try {
            switch (this.opcion) {
                case "CREAR": {
                    this.Crear_Ampliacion_Horario();
                    break;
                }
                case "MODIFICAR": {
                    this.Modificar_Ampliacion_Horario();
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: aceptar ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void Crear_Ampliacion_Horario() {
        try {
            if (!this.personal.equals("0")) {
                if (this.lst_horarios.size() > 0) {
                    if (this.rechazado_form) {
                        this.rechazado = Long.parseLong("1");
                    } else {
                        this.rechazado = Long.parseLong("0");
                    }
                    if (this.visto_bueno_director_form) {
                        this.visto_bueno_director = Long.parseLong("1");
                    } else {
                        this.visto_bueno_director = Long.parseLong("0");
                    }
                    if (this.visto_bueno_secretario_academico_form) {
                        this.visto_bueno_secretario_academico = Long.parseLong("1");
                    } else {
                        this.visto_bueno_secretario_academico = Long.parseLong("0");
                    }
                    if (this.notificacion_tesoreria_form) {
                        this.notificacion_tesoreria = Long.parseLong("1");
                    } else {
                        this.notificacion_tesoreria = Long.parseLong("0");
                    }
                    
                    entidad.Comeval_Acta_Solicitud comeval_acta_solicitud = null;

                    List<entidad.Comeval_Solicitud_Observacion> lst_observaciones_rest = new ArrayList<>();
                    for (Integer i = 0; i < this.lst_observaciones.size(); i++) {
                        entidad.Comeval_Solicitud_Observacion observacion_temp = new entidad.Comeval_Solicitud_Observacion();
                        observacion_temp.setId_solicitud(this.id_comeval_ampliacion_horario);
                        observacion_temp.setId_tipo_solicitud(this.id_tipo_solicitud);
                        observacion_temp.setId_observacion(this.lst_observaciones.get(i).getId_observacion());
                        observacion_temp.setDependencia(this.lst_observaciones.get(i).getDependencia());
                        observacion_temp.setFecha_hora(this.lst_observaciones.get(i).getFecha_hora());
                        observacion_temp.setObservacion(this.lst_observaciones.get(i).getObservacion());
                        lst_observaciones_rest.add(observacion_temp);
                    }

                    List<entidad.Comeval_Ampliacion_Horario_Plaza> lst_horario = new ArrayList<>();
                    for (Integer i = 0; i < this.lst_horarios .size(); i++) {
                        entidad.Comeval_Ampliacion_Horario_Plaza comeval_ampliacion_horario_plaza = new entidad.Comeval_Ampliacion_Horario_Plaza();
                        comeval_ampliacion_horario_plaza.setId_comeval_ampliacion_horario(this.id_comeval_ampliacion_horario);
                        comeval_ampliacion_horario_plaza.setId_horario(Long.parseLong("1"));
                        comeval_ampliacion_horario_plaza.setHorainicio(this.lst_horarios.get(i).getHora_inicio());
                        comeval_ampliacion_horario_plaza.setHorafin(this.lst_horarios.get(i).getHora_final());
                        comeval_ampliacion_horario_plaza.setDias(this.lst_horarios.get(i).getDias());
                        lst_horario.add(comeval_ampliacion_horario_plaza);
                    }

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    entidad.Comeval_Ampliacion_Horario comeval_ampliacion_horario = new entidad.Comeval_Ampliacion_Horario(
                            this.id_comeval_ampliacion_horario,
                            this.personal,
                            this.id_tipo_ampliacion_horario,
                            this.id_plaza_temporal,
                            this.id_plaza_indefinido,
                            this.nota_ref,
                            dateFormat.format(this.fecha_nota_ref),
                            this.usuario.getUsuario(),
                            dateFormat.format(new Date()),
                            this.id_estado_solicitud,
                            this.id_tipo_solicitud,
                            this.rechazado,
                            this.id_estado_solicitud_rechazado,
                            this.id_tipo_solicitud_rechazado,
                            this.visto_bueno_director,
                            this.visto_bueno_secretario_academico,
                            this.notificacion_tesoreria,
                            lst_horario,
                            null,
                            null);

                    List<entidad.Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario = new ArrayList<>();
                    lst_comeval_ampliacion_horario.add(comeval_ampliacion_horario);

                    servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                    String resultado = cliente_api_comeval_rest.ampliacion_horario_ingresar(lst_comeval_ampliacion_horario);

                    PrimeFaces.current().executeScript("PF('ComevalAmpliacionHorarioDialogVar').hide();");
                    // PrimeFaces.current().executeScript("PF('varTblComevalAmpliacionHorario').clearFilters();");

                    String[] mensaje = resultado.split(",");
                    if (mensaje[0].equals("0")) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", mensaje[1]));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", mensaje[1]));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", "Debe registrar almenos un horario aplicado a una plaza."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", "Debe ingresar un docente registrado en el sistema."));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Crear_Ampliacion_Horario ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void Modificar_Ampliacion_Horario() {
        try {
            if (!this.personal.equals("0")) {
                if (this.lst_horarios.size() > 0) {
                    if (this.rechazado_form) {
                        this.rechazado = Long.parseLong("1");
                    } else {
                        this.rechazado = Long.parseLong("0");
                    }
                    if (this.visto_bueno_director_form) {
                        this.visto_bueno_director = Long.parseLong("1");
                    } else {
                        this.visto_bueno_director = Long.parseLong("0");
                    }
                    if (this.visto_bueno_secretario_academico_form) {
                        this.visto_bueno_secretario_academico = Long.parseLong("1");
                    } else {
                        this.visto_bueno_secretario_academico = Long.parseLong("0");
                    }
                    if (this.notificacion_tesoreria_form) {
                        this.notificacion_tesoreria = Long.parseLong("1");
                    } else {
                        this.notificacion_tesoreria = Long.parseLong("0");
                    }
                    
                    entidad.Comeval_Acta_Solicitud comeval_acta_solicitud = null;

                    List<entidad.Comeval_Solicitud_Observacion> lst_observaciones_rest = new ArrayList<>();
                    for (Integer i = 0; i < this.lst_observaciones.size(); i++) {
                        entidad.Comeval_Solicitud_Observacion observacion_temp = new entidad.Comeval_Solicitud_Observacion();
                        observacion_temp.setId_solicitud(this.id_comeval_ampliacion_horario);
                        observacion_temp.setId_tipo_solicitud(this.id_tipo_solicitud);
                        observacion_temp.setId_observacion(this.lst_observaciones.get(i).getId_observacion());
                        observacion_temp.setDependencia(this.lst_observaciones.get(i).getDependencia());
                        observacion_temp.setFecha_hora(this.lst_observaciones.get(i).getFecha_hora());
                        observacion_temp.setObservacion(this.lst_observaciones.get(i).getObservacion());
                        lst_observaciones_rest.add(observacion_temp);
                    }

                    List<entidad.Comeval_Ampliacion_Horario_Plaza> lst_horario = new ArrayList<>();
                    for (Integer i = 0; i < this.lst_horarios .size(); i++) {
                        entidad.Comeval_Ampliacion_Horario_Plaza comeval_ampliacion_horario_plaza = new entidad.Comeval_Ampliacion_Horario_Plaza();
                        comeval_ampliacion_horario_plaza.setId_comeval_ampliacion_horario(this.id_comeval_ampliacion_horario);
                        comeval_ampliacion_horario_plaza.setId_horario(Long.parseLong("1"));
                        comeval_ampliacion_horario_plaza.setHorainicio(this.lst_horarios.get(i).getHora_inicio());
                        comeval_ampliacion_horario_plaza.setHorafin(this.lst_horarios.get(i).getHora_final());
                        comeval_ampliacion_horario_plaza.setDias(this.lst_horarios.get(i).getDias());
                        lst_horario.add(comeval_ampliacion_horario_plaza);
                    }

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    entidad.Comeval_Ampliacion_Horario comeval_ampliacion_horario = new entidad.Comeval_Ampliacion_Horario(
                            this.id_comeval_ampliacion_horario,
                            this.personal,
                            this.id_tipo_ampliacion_horario,
                            this.id_plaza_temporal,
                            this.id_plaza_indefinido,
                            this.nota_ref,
                            dateFormat.format(this.fecha_nota_ref),
                            this.usuario.getUsuario(),
                            dateFormat.format(new Date()),
                            this.id_estado_solicitud,
                            this.id_tipo_solicitud,
                            this.rechazado,
                            this.id_estado_solicitud_rechazado,
                            this.id_tipo_solicitud_rechazado,
                            this.visto_bueno_director,
                            this.visto_bueno_secretario_academico,
                            this.notificacion_tesoreria,
                            lst_horario,
                            null,
                            null);

                    List<entidad.Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario = new ArrayList<>();
                    lst_comeval_ampliacion_horario.add(comeval_ampliacion_horario);

                    servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                    String resultado = cliente_api_comeval_rest.ampliacion_horario_modificar(lst_comeval_ampliacion_horario);

                    PrimeFaces.current().executeScript("PF('ComevalAmpliacionHorarioDialogVar').hide();");
                    // PrimeFaces.current().executeScript("PF('varTblComevalAmpliacionHorario').clearFilters();");

                    String[] mensaje = resultado.split(",");
                    if (mensaje[0].equals("0")) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", mensaje[1]));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", mensaje[1]));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", "Debe registrar almenos un horario aplicado a una plaza."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", "Debe ingresar un docente registrado en el sistema."));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Modificar_Ampliacion_Horario ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void datos_docente() {
        try {
            this.personal = "0";
            this.nombre_docente = "-";

            String cadenasql = "select p.personal, trim(p.nombre) || ' ' || trim(p.apellido), 'PENDIENTE' titularidad_actual "
                    + "from personal p "
                    + "where p.personal='" + this.codigo_docente + "'";
            servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            String jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            Type listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            List<String> lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] docente = lista_drive.get(i).split("♣");
                this.personal = docente[0];
                this.nombre_docente = docente[1];
            }
            
            // INICIALIZAR CONTROLES DE HORA EN 00:00
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
            String fecha_letras = "";

            Integer dia = Calendar.getInstance().get(Calendar.DATE);
            if (dia < 10) {
                fecha_letras = "0" + dia + "/";
            } else {
                fecha_letras = dia + "/";
            }

            Integer mes = Calendar.getInstance().get(Calendar.MONTH);
            if (mes < 10) {
                fecha_letras = fecha_letras + "0" + mes + "/";
            } else {
                fecha_letras = fecha_letras + mes + "/";
            }

            Integer anio = Calendar.getInstance().get(Calendar.YEAR);
            fecha_letras = fecha_letras + anio;

            this.hora_inicio = dateFormat1.parse(fecha_letras + " 00:00:00");
            this.hora_fin = dateFormat1.parse(fecha_letras + " 00:00:00");
            
            // INICIALIZAR CONTROL DE DIAS DEL HORARIO.
            this.lunes = false;
            this.martes = false;
            this.miercoles = false;
            this.jueves = false;
            this.viernes = false;
            this.sabado = false;
            this.domingo = false;

            this.lst_horarios = new ArrayList<>();
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: datos_docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void agregar_horario() {
        try {
            Integer ContadorDias = 0;
            String dias = "";
            if (this.lunes) {
                dias = "X";
                ContadorDias++;
            } else {
                dias = "-";
            }
            if (this.martes) {
                dias = dias + "X";
                ContadorDias++;
            } else {
                dias = dias + "-";
            }
            if (this.miercoles) {
                dias = dias + "X";
                ContadorDias++;
            } else {
                dias = dias + "-";
            }
            if (this.jueves) {
                dias = dias + "X";
                ContadorDias++;
            } else {
                dias = dias + "-";
            }
            if (this.viernes) {
                dias = dias + "X";
                ContadorDias++;
            } else {
                dias = dias + "-";
            }
            if (this.sabado) {
                dias = dias + "X";
                ContadorDias++;
            } else {
                dias = dias + "-";
            }
            if (this.domingo) {
                dias = dias + "X";
                ContadorDias++;
            } else {
                dias = dias + "-";
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");

            Boolean ListaConHorario = false;
            for (Integer i = 0; i < this.lst_horarios.size(); i++) {
                if (dateFormat.format(this.hora_inicio).equals(lst_horarios.get(i).getHora_inicio()) && dateFormat.format(this.hora_fin).equals(lst_horarios.get(i).getHora_final()) && dias.equals(this.lst_horarios.get(i).getDias())) {
                    ListaConHorario = true;
                }
            }

            if (ContadorDias > 0) {
                if (!ListaConHorario) {
                    Lista_Horarios lista_horarios = new Lista_Horarios(Long.parseLong("1"), dateFormat.format(this.hora_inicio), dateFormat.format(this.hora_fin), dias);
                    this.lst_horarios.add(lista_horarios);

                    for (Integer i = 0; i < this.lst_horarios.size(); i++) {
                        Integer j = i + 1;
                        this.lst_horarios.get(i).setId_horario(Long.parseLong(j.toString()));
                    }

                    this.lunes = false;
                    this.martes = false;
                    this.miercoles = false;
                    this.jueves = false;
                    this.viernes = false;
                    this.sabado = false;
                    this.domingo = false;

                    // INICIALIZAR CONTROLES DE HORA EN 00:00
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
                    String fecha_letras = "";

                    Integer dia = Calendar.getInstance().get(Calendar.DATE);
                    if (dia < 10) {
                        fecha_letras = "0" + dia + "/";
                    } else {
                        fecha_letras = dia + "/";
                    }

                    Integer mes = Calendar.getInstance().get(Calendar.MONTH);
                    if (mes < 10) {
                        fecha_letras = fecha_letras + "0" + mes + "/";
                    } else {
                        fecha_letras = fecha_letras + mes + "/";
                    }

                    Integer anio = Calendar.getInstance().get(Calendar.YEAR);
                    fecha_letras = fecha_letras + anio;

                    this.hora_inicio = dateFormat1.parse(fecha_letras + " 00:00:00");
                    this.hora_fin = dateFormat1.parse(fecha_letras + " 00:00:00");

                    // INICIALIZAR CONTROL DE DIAS DEL HORARIO.
                    this.lunes = false;
                    this.martes = false;
                    this.miercoles = false;
                    this.jueves = false;
                    this.viernes = false;
                    this.sabado = false;
                    this.domingo = false;

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", "Horario cargado con éxito."));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje del sistema...", "El horario que desea agregar ya esta registrado en la solicitud."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje del sistema...", "Debe seleccionar al menos un día en el horario."));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: agregar_horario ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void eliminar_horario() {
        try {
            if(this.sel_horarios != null) {
                this.lst_horarios.remove(this.sel_horarios);
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: eliminar_horario ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }
    
    public void agregar_observacion() {
        try {
            this.observacion = "";
            PrimeFaces.current().executeScript("PF('AmpliacionHorarioObservacionesDialogVar').show();");
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: agregar_observacion ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void eliminar_observacion() {
        try {
            if (this.sel_observaciones != null) {
                this.lst_observaciones.remove(this.sel_observaciones);

                for (Integer i = 0; i < this.lst_observaciones.size(); i++) {
                    Integer j = i + 1;
                    this.lst_observaciones.get(i).setId_observacion(Long.parseLong(j.toString()));
                }
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: eliminar_observacion ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void agregar_observacion_dialog() {
        try {
            Integer id_observacion = lst_observaciones.size();
            id_observacion++;
            beans.Comeval_Ampliacion_Horario.lista_observaciones observacion_dialog = new beans.Comeval_Ampliacion_Horario.lista_observaciones();
            observacion_dialog.setId_observacion(Long.parseLong(id_observacion.toString()));
            observacion_dialog.setDependencia(this.dependencia_observaciones);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            observacion_dialog.setFecha_hora(dateFormat.format(new Date()));
            observacion_dialog.setObservacion(this.observacion);
            this.lst_observaciones.add(observacion_dialog);

            for (Integer i = 0; i < this.lst_observaciones.size(); i++) {
                Integer j = i + 1;
                this.lst_observaciones.get(i).setId_observacion(Long.parseLong(j.toString()));
            }

            PrimeFaces.current().executeScript("PF('AmpliacionHorarioObservacionesDialogVar').hide();");
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: agregar_observacion_dialog ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void check_cambio_valor() {
        try {
            if (this.rechazado_form) {
                this.cbxEstadoSolicitudRechazado = false;
            } else {
                this.cbxEstadoSolicitudRechazado = true;
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: check_cambio_valor ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public entidad.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(entidad.Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId_estado_solicitud() {
        return id_estado_solicitud;
    }

    public void setId_estado_solicitud(Long id_estado_solicitud) {
        this.id_estado_solicitud = id_estado_solicitud;
    }

    public Long getId_tipo_solicitud() {
        return id_tipo_solicitud;
    }

    public void setId_tipo_solicitud(Long id_tipo_solicitud) {
        this.id_tipo_solicitud = id_tipo_solicitud;
    }

    public Long getId_estado_solicitud_rechazado() {
        return id_estado_solicitud_rechazado;
    }

    public void setId_estado_solicitud_rechazado(Long id_estado_solicitud_rechazado) {
        this.id_estado_solicitud_rechazado = id_estado_solicitud_rechazado;
    }

    public Long getId_tipo_solicitud_rechazado() {
        return id_tipo_solicitud_rechazado;
    }

    public void setId_tipo_solicitud_rechazado(Long id_tipo_solicitud_rechazado) {
        this.id_tipo_solicitud_rechazado = id_tipo_solicitud_rechazado;
    }

    public Long getId_comeval_ampliacion_horario() {
        return id_comeval_ampliacion_horario;
    }

    public void setId_comeval_ampliacion_horario(Long id_comeval_ampliacion_horario) {
        this.id_comeval_ampliacion_horario = id_comeval_ampliacion_horario;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getCodigo_docente() {
        return codigo_docente;
    }

    public void setCodigo_docente(String codigo_docente) {
        this.codigo_docente = codigo_docente;
    }

    public String getNombre_docente() {
        return nombre_docente;
    }

    public void setNombre_docente(String nombre_docente) {
        this.nombre_docente = nombre_docente;
    }

    public Long getId_tipo_ampliacion_horario() {
        return id_tipo_ampliacion_horario;
    }

    public void setId_tipo_ampliacion_horario(Long id_tipo_ampliacion_horario) {
        this.id_tipo_ampliacion_horario = id_tipo_ampliacion_horario;
    }

    public Long getId_plaza_temporal() {
        return id_plaza_temporal;
    }

    public void setId_plaza_temporal(Long id_plaza_temporal) {
        this.id_plaza_temporal = id_plaza_temporal;
    }

    public Long getId_plaza_indefinido() {
        return id_plaza_indefinido;
    }

    public void setId_plaza_indefinido(Long id_plaza_indefinido) {
        this.id_plaza_indefinido = id_plaza_indefinido;
    }

    public String getNota_ref() {
        return nota_ref;
    }

    public void setNota_ref(String nota_ref) {
        this.nota_ref = nota_ref;
    }

    public Date getFecha_nota_ref() {
        return fecha_nota_ref;
    }

    public void setFecha_nota_ref(Date fecha_nota_ref) {
        this.fecha_nota_ref = fecha_nota_ref;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Long getRechazado() {
        return rechazado;
    }

    public void setRechazado(Long rechazado) {
        this.rechazado = rechazado;
    }

    public Boolean getRechazado_form() {
        return rechazado_form;
    }

    public void setRechazado_form(Boolean rechazado_form) {
        this.rechazado_form = rechazado_form;
    }

    public Long getVisto_bueno_director() {
        return visto_bueno_director;
    }

    public void setVisto_bueno_director(Long visto_bueno_director) {
        this.visto_bueno_director = visto_bueno_director;
    }

    public Boolean getVisto_bueno_director_form() {
        return visto_bueno_director_form;
    }

    public void setVisto_bueno_director_form(Boolean visto_bueno_director_form) {
        this.visto_bueno_director_form = visto_bueno_director_form;
    }

    public Long getVisto_bueno_secretario_academico() {
        return visto_bueno_secretario_academico;
    }

    public void setVisto_bueno_secretario_academico(Long visto_bueno_secretario_academico) {
        this.visto_bueno_secretario_academico = visto_bueno_secretario_academico;
    }

    public Boolean getVisto_bueno_secretario_academico_form() {
        return visto_bueno_secretario_academico_form;
    }

    public void setVisto_bueno_secretario_academico_form(Boolean visto_bueno_secretario_academico_form) {
        this.visto_bueno_secretario_academico_form = visto_bueno_secretario_academico_form;
    }

    public Long getNotificacion_tesoreria() {
        return notificacion_tesoreria;
    }

    public void setNotificacion_tesoreria(Long notificacion_tesoreria) {
        this.notificacion_tesoreria = notificacion_tesoreria;
    }

    public Boolean getNotificacion_tesoreria_form() {
        return notificacion_tesoreria_form;
    }

    public void setNotificacion_tesoreria_form(Boolean notificacion_tesoreria_form) {
        this.notificacion_tesoreria_form = notificacion_tesoreria_form;
    }

    public Boolean getLunes() {
        return lunes;
    }

    public void setLunes(Boolean lunes) {
        this.lunes = lunes;
    }

    public Boolean getMartes() {
        return martes;
    }

    public void setMartes(Boolean martes) {
        this.martes = martes;
    }

    public Boolean getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(Boolean miercoles) {
        this.miercoles = miercoles;
    }

    public Boolean getJueves() {
        return jueves;
    }

    public void setJueves(Boolean jueves) {
        this.jueves = jueves;
    }

    public Boolean getViernes() {
        return viernes;
    }

    public void setViernes(Boolean viernes) {
        this.viernes = viernes;
    }

    public Boolean getSabado() {
        return sabado;
    }

    public void setSabado(Boolean sabado) {
        this.sabado = sabado;
    }

    public Boolean getDomingo() {
        return domingo;
    }

    public void setDomingo(Boolean domingo) {
        this.domingo = domingo;
    }

    public Date getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Date hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Date getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Date hora_fin) {
        this.hora_fin = hora_fin;
    }

    public List<SelectItem> getLst_estado_solicitud() {
        return lst_estado_solicitud;
    }

    public void setLst_estado_solicitud(List<SelectItem> lst_estado_solicitud) {
        this.lst_estado_solicitud = lst_estado_solicitud;
    }

    public List<SelectItem> getLst_tipo_solicitud() {
        return lst_tipo_solicitud;
    }

    public void setLst_tipo_solicitud(List<SelectItem> lst_tipo_solicitud) {
        this.lst_tipo_solicitud = lst_tipo_solicitud;
    }

    public List<SelectItem> getLst_estado_solicitud_rechazado() {
        return lst_estado_solicitud_rechazado;
    }

    public void setLst_estado_solicitud_rechazado(List<SelectItem> lst_estado_solicitud_rechazado) {
        this.lst_estado_solicitud_rechazado = lst_estado_solicitud_rechazado;
    }

    public List<SelectItem> getLst_tipo_solicitud_rechazado() {
        return lst_tipo_solicitud_rechazado;
    }

    public void setLst_tipo_solicitud_rechazado(List<SelectItem> lst_tipo_solicitud_rechazado) {
        this.lst_tipo_solicitud_rechazado = lst_tipo_solicitud_rechazado;
    }

    public List<SelectItem> getLst_tipo_ampliacion_horario() {
        return lst_tipo_ampliacion_horario;
    }

    public void setLst_tipo_ampliacion_horario(List<SelectItem> lst_tipo_ampliacion_horario) {
        this.lst_tipo_ampliacion_horario = lst_tipo_ampliacion_horario;
    }

    public List<SelectItem> getLst_plaza_temporal() {
        return lst_plaza_temporal;
    }

    public void setLst_plaza_temporal(List<SelectItem> lst_plaza_temporal) {
        this.lst_plaza_temporal = lst_plaza_temporal;
    }

    public List<SelectItem> getLst_plaza_indefinido() {
        return lst_plaza_indefinido;
    }

    public void setLst_plaza_indefinido(List<SelectItem> lst_plaza_indefinido) {
        this.lst_plaza_indefinido = lst_plaza_indefinido;
    }

    public List<Lista_Horarios> getLst_horarios() {
        return lst_horarios;
    }

    public void setLst_horarios(List<Lista_Horarios> lst_horarios) {
        this.lst_horarios = lst_horarios;
    }

    public Lista_Horarios getSel_horarios() {
        return sel_horarios;
    }

    public void setSel_horarios(Lista_Horarios sel_horarios) {
        this.sel_horarios = sel_horarios;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getDependencia_observaciones() {
        return dependencia_observaciones;
    }

    public void setDependencia_observaciones(String dependencia_observaciones) {
        this.dependencia_observaciones = dependencia_observaciones;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<lista_observaciones> getLst_observaciones() {
        return lst_observaciones;
    }

    public void setLst_observaciones(List<lista_observaciones> lst_observaciones) {
        this.lst_observaciones = lst_observaciones;
    }

    public lista_observaciones getSel_observaciones() {
        return sel_observaciones;
    }

    public void setSel_observaciones(lista_observaciones sel_observaciones) {
        this.sel_observaciones = sel_observaciones;
    }

    public Boolean getCbxTipoSolicitud() {
        return cbxTipoSolicitud;
    }

    public void setCbxTipoSolicitud(Boolean cbxTipoSolicitud) {
        this.cbxTipoSolicitud = cbxTipoSolicitud;
    }

    public Boolean getCbxEstadoSolicitud() {
        return cbxEstadoSolicitud;
    }

    public void setCbxEstadoSolicitud(Boolean cbxEstadoSolicitud) {
        this.cbxEstadoSolicitud = cbxEstadoSolicitud;
    }

    public Boolean getCbxTipoSolicitudRechazado() {
        return cbxTipoSolicitudRechazado;
    }

    public void setCbxTipoSolicitudRechazado(Boolean cbxTipoSolicitudRechazado) {
        this.cbxTipoSolicitudRechazado = cbxTipoSolicitudRechazado;
    }

    public Boolean getCbxEstadoSolicitudRechazado() {
        return cbxEstadoSolicitudRechazado;
    }

    public void setCbxEstadoSolicitudRechazado(Boolean cbxEstadoSolicitudRechazado) {
        this.cbxEstadoSolicitudRechazado = cbxEstadoSolicitudRechazado;
    }

    public Boolean getTxtCodigoDocente() {
        return txtCodigoDocente;
    }

    public void setTxtCodigoDocente(Boolean txtCodigoDocente) {
        this.txtCodigoDocente = txtCodigoDocente;
    }

    public Boolean getTxtNombreDocente() {
        return txtNombreDocente;
    }

    public void setTxtNombreDocente(Boolean txtNombreDocente) {
        this.txtNombreDocente = txtNombreDocente;
    }

    public Boolean getTxtNotaRef() {
        return txtNotaRef;
    }

    public void setTxtNotaRef(Boolean txtNotaRef) {
        this.txtNotaRef = txtNotaRef;
    }

    public Boolean getCalFechaNotaRef() {
        return calFechaNotaRef;
    }

    public void setCalFechaNotaRef(Boolean calFechaNotaRef) {
        this.calFechaNotaRef = calFechaNotaRef;
    }

    public Boolean getCbxTipoAmpliacionHorario() {
        return cbxTipoAmpliacionHorario;
    }

    public void setCbxTipoAmpliacionHorario(Boolean cbxTipoAmpliacionHorario) {
        this.cbxTipoAmpliacionHorario = cbxTipoAmpliacionHorario;
    }

    public Boolean getCbxPlazaTemporal() {
        return cbxPlazaTemporal;
    }

    public void setCbxPlazaTemporal(Boolean cbxPlazaTemporal) {
        this.cbxPlazaTemporal = cbxPlazaTemporal;
    }

    public Boolean getCbxPlazaIndefinido() {
        return cbxPlazaIndefinido;
    }

    public void setCbxPlazaIndefinido(Boolean cbxPlazaIndefinido) {
        this.cbxPlazaIndefinido = cbxPlazaIndefinido;
    }

    public Boolean getChxRechazado() {
        return chxRechazado;
    }

    public void setChxRechazado(Boolean chxRechazado) {
        this.chxRechazado = chxRechazado;
    }

    public Boolean getChxVistoBuenoDirector() {
        return chxVistoBuenoDirector;
    }

    public void setChxVistoBuenoDirector(Boolean chxVistoBuenoDirector) {
        this.chxVistoBuenoDirector = chxVistoBuenoDirector;
    }

    public Boolean getChxVistoSecretarioAcademico() {
        return chxVistoSecretarioAcademico;
    }

    public void setChxVistoSecretarioAcademico(Boolean chxVistoSecretarioAcademico) {
        this.chxVistoSecretarioAcademico = chxVistoSecretarioAcademico;
    }

    public Boolean getChxNotificacionTesoreria() {
        return chxNotificacionTesoreria;
    }

    public void setChxNotificacionTesoreria(Boolean chxNotificacionTesoreria) {
        this.chxNotificacionTesoreria = chxNotificacionTesoreria;
    }

    public Boolean getChxLunes() {
        return chxLunes;
    }

    public void setChxLunes(Boolean chxLunes) {
        this.chxLunes = chxLunes;
    }

    public Boolean getChxMartes() {
        return chxMartes;
    }

    public void setChxMartes(Boolean chxMartes) {
        this.chxMartes = chxMartes;
    }

    public Boolean getChxMiercoles() {
        return chxMiercoles;
    }

    public void setChxMiercoles(Boolean chxMiercoles) {
        this.chxMiercoles = chxMiercoles;
    }

    public Boolean getChxJueves() {
        return chxJueves;
    }

    public void setChxJueves(Boolean chxJueves) {
        this.chxJueves = chxJueves;
    }

    public Boolean getChxViernes() {
        return chxViernes;
    }

    public void setChxViernes(Boolean chxViernes) {
        this.chxViernes = chxViernes;
    }

    public Boolean getChxSabado() {
        return chxSabado;
    }

    public void setChxSabado(Boolean chxSabado) {
        this.chxSabado = chxSabado;
    }

    public Boolean getChxDomingo() {
        return chxDomingo;
    }

    public void setChxDomingo(Boolean chxDomingo) {
        this.chxDomingo = chxDomingo;
    }

    public Boolean getCalHoraInicio() {
        return calHoraInicio;
    }

    public void setCalHoraInicio(Boolean calHoraInicio) {
        this.calHoraInicio = calHoraInicio;
    }

    public Boolean getCalHoraFin() {
        return calHoraFin;
    }

    public void setCalHoraFin(Boolean calHoraFin) {
        this.calHoraFin = calHoraFin;
    }

    public Boolean getBtnAgregarHorario() {
        return btnAgregarHorario;
    }

    public void setBtnAgregarHorario(Boolean btnAgregarHorario) {
        this.btnAgregarHorario = btnAgregarHorario;
    }

    public Boolean getBtnEliminarHorario() {
        return btnEliminarHorario;
    }

    public void setBtnEliminarHorario(Boolean btnEliminarHorario) {
        this.btnEliminarHorario = btnEliminarHorario;
    }

    public Boolean getBtnAgregarObservacion() {
        return btnAgregarObservacion;
    }

    public void setBtnAgregarObservacion(Boolean btnAgregarObservacion) {
        this.btnAgregarObservacion = btnAgregarObservacion;
    }

    public Boolean getBtnEliminarObservacion() {
        return btnEliminarObservacion;
    }

    public void setBtnEliminarObservacion(Boolean btnEliminarObservacion) {
        this.btnEliminarObservacion = btnEliminarObservacion;
    }

    public Boolean getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(Boolean btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    public Boolean getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(Boolean btnCancelar) {
        this.btnCancelar = btnCancelar;
    }
    
}
