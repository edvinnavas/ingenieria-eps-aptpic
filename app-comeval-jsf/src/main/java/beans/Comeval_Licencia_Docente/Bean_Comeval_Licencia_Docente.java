package beans.Comeval_Licencia_Docente;

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
import org.primefaces.model.DualListModel;

@Named(value = "Bean_Comeval_Licencia_Docente")
@ViewScoped
public class Bean_Comeval_Licencia_Docente implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private entidad.Usuario usuario;
    
    private Long id_comeval_licencia_docente;
    private String personal;
    private String codigo_docente;
    private String nombre_docente;
    private Long id_motivo_licencia;
    private Long id_tipo_licencia;
    private String goce_sueldo;
    private Date fecha_inicio_licencia;
    private Date fecha_final_licencia;
    private Date fecha_ingreso;
    private Long id_estado_solicitud;
    private Long id_tipo_solicitud;
    private Long rechazado;
    private Long id_estado_solicitud_rechazado;
    private Long id_tipo_solicitud_rechazado;
    private Boolean rechazado_form;
    private Long ingreso_siif_visto_bueno_escuela;
    private Boolean ingreso_siif_visto_bueno_escuela_form;
    private Long tipo_licencia_secretario_academico;
    private Boolean tipo_licencia_secretario_academico_form;
    private Long acuerdo_decanatura;
    private Boolean acuerdo_decanatura_form;
    private Long notificacion_tesoreria;
    private Boolean notificacion_tesoreria_form;
    
    private List<SelectItem> lst_motivo_licencia;
    private List<SelectItem> lst_tipo_licencia;
    private List<SelectItem> lst_goce_sueldo;
    private List<SelectItem> lst_estado_solicitud;
    private List<SelectItem> lst_tipo_solicitud;
    private List<SelectItem> lst_estado_solicitud_rechazado;
    private List<SelectItem> lst_tipo_solicitud_rechazado;
    
    private String opcion;
    private DualListModel<String> plazas;
    
    private String dependencia_observaciones;
    private String observacion;
    
    private List<lista_observaciones> lst_observaciones;
    private lista_observaciones sel_observaciones;
    
    private Boolean cbxTipoSolicitud;
    private Boolean cbxEstadoSolicitud;
    private Boolean txtCodigoDocente;
    private Boolean txtNombreDocente;
    private Boolean cbxMotivoLicencia;
    private Boolean cbxTipoLicencia;
    private Boolean cbxGoceSueldo;
    private Boolean calFechaInicioLicencia;
    private Boolean calFechaFinalLicencia;
    private Boolean chxRechazado;
    private Boolean chxIngresoSiif_VistoBuenoEscuela;
    private Boolean chxTipoLicencia;
    private Boolean chxAcuerdoDecanatura;
    private Boolean chxNotificacionTesoreria;
    private Boolean cbxTipoSolicitudRechazado;
    private Boolean cbxEstadoSolicitudRechazado;
    private Boolean pklPlazas;
    private Boolean btnAgregarObservacion;
    private Boolean btnEliminarObservacion;
    private Boolean btnAceptar;
    private Boolean btnCancelar;
    
    public Bean_Comeval_Licencia_Docente() {
        try {
            this.lst_motivo_licencia = new ArrayList<>();
            this.lst_tipo_licencia = new ArrayList<>();
            this.lst_goce_sueldo = new ArrayList<>();
            this.lst_estado_solicitud = new ArrayList<>();
            this.lst_tipo_solicitud = new ArrayList<>();
            this.lst_estado_solicitud_rechazado = new ArrayList<>();
            this.lst_tipo_solicitud_rechazado = new ArrayList<>();
            
            List<String> plazas_fuente = new ArrayList<>();
            List<String> plazas_destino = new ArrayList<>();
            this.plazas = new DualListModel<>(plazas_fuente, plazas_destino);
            
            this.lst_observaciones = new ArrayList<>();
        } catch(Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Bean_Comeval_Licencia_Docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }
    
    public void mostrar_crear_licencia_docente(entidad.Usuario usuario, Long id_estado_solicitud, Long id_tipo_solicitud) {
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
            
            this.lst_goce_sueldo = new ArrayList<>();
            this.lst_goce_sueldo.add(new SelectItem("SI", "SI"));
            this.lst_goce_sueldo.add(new SelectItem("NO", "NO"));
            
            this.lst_motivo_licencia = new ArrayList<>();
            cadenasql = "select f.id, f.descripcion from licencia_motivo f order by f.descripcion";
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] rol = lista_drive.get(i).split("♣");
                this.lst_motivo_licencia.add(new SelectItem(Long.parseLong(rol[0]), rol[1]));
            }
            
            this.lst_tipo_licencia = new ArrayList<>();
            cadenasql = "select f.tipolicencia, f.descripcion from tipolicencia f order by f.descripcion";
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] rol = lista_drive.get(i).split("♣");
                this.lst_tipo_licencia.add(new SelectItem(Long.parseLong(rol[0]), rol[1]));
            }
            
            this.lst_tipo_solicitud_rechazado = this.lst_tipo_solicitud;
            this.id_tipo_solicitud_rechazado = Long.parseLong("1");

            this.lst_estado_solicitud_rechazado = this.lst_estado_solicitud;
            this.id_estado_solicitud_rechazado = Long.parseLong("1");
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            this.id_comeval_licencia_docente = Long.parseLong("0");
            this.personal = "0";
            this.codigo_docente = this.usuario.getUsuario();
            this.nombre_docente = "-";
            this.datos_docente();
            
            this.id_motivo_licencia = Long.parseLong("1");
            this.id_tipo_licencia = Long.parseLong("1");
            this.goce_sueldo = "SI";
            this.fecha_inicio_licencia = new Date();
            this.fecha_final_licencia = new Date();
            this.fecha_ingreso = dateFormat.parse("1900-01-01 00:00:00");
            
            this.rechazado = Long.parseLong("0");
            
            this.ingreso_siif_visto_bueno_escuela = Long.parseLong("0");
            this.tipo_licencia_secretario_academico = Long.parseLong("0");
            this.acuerdo_decanatura = Long.parseLong("0");
            this.notificacion_tesoreria = Long.parseLong("0");
            
            this.rechazado_form = false;
            this.ingreso_siif_visto_bueno_escuela_form = false;
            this.tipo_licencia_secretario_academico_form = false;
            this.acuerdo_decanatura_form = false;
            this.notificacion_tesoreria_form = false;
            
            if (this.id_estado_solicitud.equals(Long.parseLong("1"))) {
                this.dependencia_observaciones = "DOCENTE";
            }
            if (this.id_estado_solicitud.equals(Long.parseLong("2"))) {
                this.dependencia_observaciones = "ESCUELA";
            }
            if (this.id_estado_solicitud.equals(Long.parseLong("3"))) {
                this.dependencia_observaciones = "SECRETARIO ACADÉMICO";
            }
            if (this.id_estado_solicitud.equals(Long.parseLong("4"))) {
                this.dependencia_observaciones = "DECANATURA";
            }
            if (this.id_estado_solicitud.equals(Long.parseLong("6"))) {
                this.dependencia_observaciones = "TESORERÍA";
            }
            
            this.lst_observaciones = new ArrayList<>();
            
            this.cbxTipoSolicitud = true;
            this.cbxEstadoSolicitud = true;
            this.txtCodigoDocente = false;
            this.txtNombreDocente = true;
            this.cbxMotivoLicencia = false;
            this.cbxTipoLicencia = true;
            this.cbxGoceSueldo = false;
            this.calFechaInicioLicencia = false;
            this.calFechaFinalLicencia = false;
            this.chxRechazado = true;
            this.chxIngresoSiif_VistoBuenoEscuela = true;
            this.chxTipoLicencia = true;
            this.chxAcuerdoDecanatura = true;
            this.chxNotificacionTesoreria = true;
            this.cbxTipoSolicitudRechazado = true;
            this.cbxEstadoSolicitudRechazado = true;
            this.pklPlazas = false;
            this.btnAgregarObservacion = false;
            this.btnEliminarObservacion = false;
            this.btnAceptar = false;
            this.btnCancelar = false;
            
            PrimeFaces.current().executeScript("PF('ComevalLicenciaDocenteDialogVar').show();");
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: mostrar_crear_licencia_docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }
    
    public void mostrar_modificar_licencia_docente(entidad.Usuario usuario, Long id_comeval_licencia_docente, Long id_estado_solicitud, Long id_tipo_solicitud) {
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
            
            this.lst_goce_sueldo = new ArrayList<>();
            this.lst_goce_sueldo.add(new SelectItem("SI", "SI"));
            this.lst_goce_sueldo.add(new SelectItem("NO", "NO"));
            
            this.lst_motivo_licencia = new ArrayList<>();
            cadenasql = "select f.id, f.descripcion from licencia_motivo f order by f.descripcion";
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] rol = lista_drive.get(i).split("♣");
                this.lst_motivo_licencia.add(new SelectItem(Long.parseLong(rol[0]), rol[1]));
            }
            
            this.lst_tipo_licencia = new ArrayList<>();
            cadenasql = "select f.tipolicencia, f.descripcion from tipolicencia f order by f.descripcion";
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] rol = lista_drive.get(i).split("♣");
                this.lst_tipo_licencia.add(new SelectItem(Long.parseLong(rol[0]), rol[1]));
            }
            
            this.lst_tipo_solicitud_rechazado = this.lst_tipo_solicitud;

            this.lst_estado_solicitud_rechazado = new ArrayList<>();
            cadenasql = "select distinct swh.id_estado_solicitud, esc.nombre "
                    + "from solicitud_workflow_historial swh "
                    + "left join estado_solicitud_comeval esc on (swh.id_tipo_solicitud=esc.id_tipo_solicitud and swh.id_estado_solicitud=esc.id_estado_solicitud) "
                    + "where swh.id_solicitud=" + id_comeval_licencia_docente + " and swh.id_estado_solicitud < " + id_estado_solicitud;
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] rol = lista_drive.get(i).split("♣");
                this.lst_estado_solicitud_rechazado.add(new SelectItem(Long.parseLong(rol[0]), rol[1]));
            }
            
            cadenasql = "select "
                    + "t.id_comeval_licencia_docente, "
                    + "t.personal, "
                    + "t.id_motivo_licencia, "
                    + "t.id_tipo_licencia, "
                    + "t.goce_sueldo, "
                    + "t.fecha_inicio_licencia, "
                    + "t.fecha_final_licencia, "
                    + "t.fecha_ingreso, "
                    + "t.id_estado_solicitud, "
                    + "t.id_tipo_solicitud, "
                    + "t.rechazado, "
                    + "t.ingreso_siif_visto_bueno_escuela, "
                    + "t.tipo_licencia_secretario_academico, "
                    + "t.acuerdo_decanatura, "
                    + "t.notificacion_tesoreria, "
                    + "coalesce(t.id_estado_solicitud_rechazado, 1) id_estado_solicitud_rechazado, "
                    + "coalesce(t.id_tipo_solicitud_rechazado, 1) id_tipo_solicitud_rechazado "
                    + "from "
                    + "comeval_licencia_docente t "
                    + "where "
                    + "t.id_comeval_licencia_docente=" + id_comeval_licencia_docente;
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] col = lista_drive.get(i).split("♣");
                this.id_comeval_licencia_docente = Long.parseLong(col[0]);
                this.personal = col[1];
                this.codigo_docente = col[1];
                this.datos_docente();
                this.id_motivo_licencia = Long.parseLong(col[2]);
                this.id_tipo_licencia = Long.parseLong(col[3]);
                this.goce_sueldo = col[4];
                this.fecha_inicio_licencia = dateFormat.parse(col[5]);
                this.fecha_final_licencia = dateFormat.parse(col[6]);
                this.fecha_ingreso = dateFormat.parse(col[7]);
                this.id_estado_solicitud = Long.parseLong(col[8]);
                this.id_tipo_solicitud = Long.parseLong(col[9]);
                this.rechazado = Long.parseLong(col[10]);
                if (this.rechazado == Long.parseLong("0")) {
                    this.rechazado_form = false;
                } else {
                    this.rechazado_form = true;
                }
                this.ingreso_siif_visto_bueno_escuela = Long.parseLong(col[11]);
                if (this.ingreso_siif_visto_bueno_escuela == Long.parseLong("0")) {
                    this.ingreso_siif_visto_bueno_escuela_form = false;
                } else {
                    this.ingreso_siif_visto_bueno_escuela_form = true;
                }
                this.tipo_licencia_secretario_academico = Long.parseLong(col[12]);
                if (this.tipo_licencia_secretario_academico == Long.parseLong("0")) {
                    this.tipo_licencia_secretario_academico_form = false;
                } else {
                    this.tipo_licencia_secretario_academico_form = true;
                }
                this.acuerdo_decanatura = Long.parseLong(col[13]);
                if (this.acuerdo_decanatura == Long.parseLong("0")) {
                    this.acuerdo_decanatura_form = false;
                } else {
                    this.acuerdo_decanatura_form = true;
                }
                this.notificacion_tesoreria = Long.parseLong(col[14]);
                if (this.notificacion_tesoreria == Long.parseLong("0")) {
                    this.notificacion_tesoreria_form = false;
                } else {
                    this.notificacion_tesoreria_form = true;
                }
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
                    + "t.id_solicitud=" + this.id_comeval_licencia_docente + " and "
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
                this.txtCodigoDocente = false;
                this.txtNombreDocente = true;
                this.cbxMotivoLicencia = false;
                this.cbxTipoLicencia = true;
                this.cbxGoceSueldo = false;
                this.calFechaInicioLicencia = false;
                this.calFechaFinalLicencia = false;
                this.chxRechazado = true;
                this.chxIngresoSiif_VistoBuenoEscuela = true;
                this.chxTipoLicencia = true;
                this.chxAcuerdoDecanatura = true;
                this.chxNotificacionTesoreria = true;
                this.cbxTipoSolicitudRechazado = true;
                this.cbxEstadoSolicitudRechazado = true;
                this.pklPlazas = false;
                this.btnAgregarObservacion = false;
                this.btnEliminarObservacion = false;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("2")) {
                this.dependencia_observaciones = "ESCUELA";
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.cbxMotivoLicencia = true;
                this.cbxTipoLicencia = true;
                this.cbxGoceSueldo = true;
                this.calFechaInicioLicencia = true;
                this.calFechaFinalLicencia = true;
                this.chxRechazado = false;
                this.chxIngresoSiif_VistoBuenoEscuela = false;
                this.chxTipoLicencia = true;
                this.chxAcuerdoDecanatura = true;
                this.chxNotificacionTesoreria = true;
                this.cbxTipoSolicitudRechazado = true;
                if(this.rechazado_form) {
                    this.cbxEstadoSolicitudRechazado = false;
                } else {
                    this.cbxEstadoSolicitudRechazado = true;
                }
                this.pklPlazas = true;
                this.btnAgregarObservacion = false;
                this.btnEliminarObservacion = false;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("3")) {
                this.dependencia_observaciones = "SECRETARIO ACADÉMICO";
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.cbxMotivoLicencia = true;
                this.cbxTipoLicencia = false;
                this.cbxGoceSueldo = false;
                this.calFechaInicioLicencia = true;
                this.calFechaFinalLicencia = true;
                this.chxRechazado = false;
                this.chxIngresoSiif_VistoBuenoEscuela = true;
                this.chxTipoLicencia = false;
                this.chxAcuerdoDecanatura = true;
                this.chxNotificacionTesoreria = true;
                this.cbxTipoSolicitudRechazado = true;
                if(this.rechazado_form) {
                    this.cbxEstadoSolicitudRechazado = false;
                } else {
                    this.cbxEstadoSolicitudRechazado = true;
                }
                this.pklPlazas = true;
                this.btnAgregarObservacion = false;
                this.btnEliminarObservacion = false;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("4")) {
                this.dependencia_observaciones = "DECANATURA";
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.cbxMotivoLicencia = true;
                this.cbxTipoLicencia = true;
                this.cbxGoceSueldo = true;
                this.calFechaInicioLicencia = true;
                this.calFechaFinalLicencia = true;
                this.chxRechazado = false;
                this.chxIngresoSiif_VistoBuenoEscuela = true;
                this.chxTipoLicencia = true;
                this.chxAcuerdoDecanatura = false;
                this.chxNotificacionTesoreria = true;
                this.cbxTipoSolicitudRechazado = true;
                if(this.rechazado_form) {
                    this.cbxEstadoSolicitudRechazado = false;
                } else {
                    this.cbxEstadoSolicitudRechazado = true;
                }
                this.pklPlazas = true;
                this.btnAgregarObservacion = false;
                this.btnEliminarObservacion = false;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("6")) {
                this.dependencia_observaciones = "TESORERÍA";
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.cbxMotivoLicencia = true;
                this.cbxTipoLicencia = true;
                this.cbxGoceSueldo = true;
                this.calFechaInicioLicencia = true;
                this.calFechaFinalLicencia = true;
                this.chxRechazado = false;
                this.chxIngresoSiif_VistoBuenoEscuela = true;
                this.chxTipoLicencia = true;
                this.chxAcuerdoDecanatura = true;
                this.chxNotificacionTesoreria = false;
                this.cbxTipoSolicitudRechazado = true;
                if(this.rechazado_form) {
                    this.cbxEstadoSolicitudRechazado = false;
                } else {
                    this.cbxEstadoSolicitudRechazado = true;
                }
                this.pklPlazas = true;
                this.btnAgregarObservacion = false;
                this.btnEliminarObservacion = false;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            
            PrimeFaces.current().executeScript("PF('ComevalLicenciaDocenteDialogVar').show();");
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: mostrar_modificar_licencia_docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }
    
    public void aceptar() {
        try {
            switch (this.opcion) {
                case "CREAR": {
                    this.Crear_Licencia_Docente();
                    break;
                }
                case "MODIFICAR": {
                    this.Modificar_Licencia_Docente();
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: aceptar ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }
    
    public void Crear_Licencia_Docente() {
        try {
            if (!this.personal.equals("0")) {
                if (this.plazas.getTarget().size() > 0) {
                    if (this.rechazado_form) {
                        this.rechazado = Long.parseLong("1");
                    } else {
                        this.rechazado = Long.parseLong("0");
                    }

                    if (this.ingreso_siif_visto_bueno_escuela_form) {
                        this.ingreso_siif_visto_bueno_escuela = Long.parseLong("1");
                    } else {
                        this.ingreso_siif_visto_bueno_escuela = Long.parseLong("0");
                    }

                    if (this.tipo_licencia_secretario_academico_form) {
                        this.tipo_licencia_secretario_academico = Long.parseLong("1");
                    } else {
                        this.tipo_licencia_secretario_academico = Long.parseLong("0");
                    }

                    if (this.acuerdo_decanatura_form) {
                        this.acuerdo_decanatura = Long.parseLong("1");
                    } else {
                        this.acuerdo_decanatura = Long.parseLong("0");
                    }

                    if (this.notificacion_tesoreria_form) {
                        this.notificacion_tesoreria = Long.parseLong("1");
                    } else {
                        this.notificacion_tesoreria = Long.parseLong("0");
                    }

                    List<entidad.Comeval_Licencia_Docente_Plaza> lst_plazas = new ArrayList<>();
                    for (Integer i = 0; i < this.plazas.getTarget().size(); i++) {
                        String cadenasql = "select pp.personal, pp.plaza, pp.periodo, pp.anio, pp.subpartida, pp.renglon, p.numero "
                                + "from plazapersonal pp left join plaza p on (pp.plaza = p.plaza) "
                                + "where (pp.anio || '-' || pp.periodo || '-' || p.numero || '-' || pp.subpartida) = '" + this.plazas.getTarget().get(i) + "'";
                        servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                        String jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
                        Type listType = new TypeToken<ArrayList<String>>() {
                        }.getType();
                        List<String> lista_drive = new Gson().fromJson(jsonString, listType);
                        for (Integer j = 1; j < lista_drive.size(); j++) {
                            String[] col = lista_drive.get(j).split("♣");
                            entidad.Comeval_Licencia_Docente_Plaza comeval_licencia_docente_plaza = new entidad.Comeval_Licencia_Docente_Plaza(
                                    this.id_comeval_licencia_docente,
                                    col[0],
                                    Long.parseLong(col[1]),
                                    Long.parseLong(col[2]),
                                    Long.parseLong(col[3]),
                                    col[4],
                                    col[5],
                                    Long.parseLong(col[6])
                            );
                            lst_plazas.add(comeval_licencia_docente_plaza);
                        }
                    }
                    
                    entidad.Comeval_Acta_Solicitud comeval_acta_solicitud = null;

                    List<entidad.Comeval_Solicitud_Observacion> lst_observaciones_rest = new ArrayList<>();
                    for (Integer i = 0; i < this.lst_observaciones.size(); i++) {
                        entidad.Comeval_Solicitud_Observacion observacion_temp = new entidad.Comeval_Solicitud_Observacion();
                        observacion_temp.setId_solicitud(this.id_comeval_licencia_docente);
                        observacion_temp.setId_tipo_solicitud(this.id_tipo_solicitud);
                        observacion_temp.setId_observacion(this.lst_observaciones.get(i).getId_observacion());
                        observacion_temp.setDependencia(this.lst_observaciones.get(i).getDependencia());
                        observacion_temp.setFecha_hora(this.lst_observaciones.get(i).getFecha_hora());
                        observacion_temp.setObservacion(this.lst_observaciones.get(i).getObservacion());
                        lst_observaciones_rest.add(observacion_temp);
                    }

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    entidad.Comeval_Licencia_Docente comeval_licencia_docente = new entidad.Comeval_Licencia_Docente(
                            this.id_comeval_licencia_docente,
                            this.personal,
                            this.id_motivo_licencia,
                            this.id_tipo_licencia,
                            this.goce_sueldo,
                            dateFormat.format(this.fecha_inicio_licencia),
                            dateFormat.format(this.fecha_final_licencia),
                            this.usuario.getUsuario(),
                            dateFormat.format(new Date()),
                            this.id_estado_solicitud,
                            this.id_tipo_solicitud,
                            this.rechazado,
                            this.id_estado_solicitud_rechazado,
                            this.id_tipo_solicitud_rechazado,
                            this.ingreso_siif_visto_bueno_escuela,
                            this.tipo_licencia_secretario_academico,
                            this.acuerdo_decanatura,
                            this.notificacion_tesoreria,
                            lst_plazas,
                            comeval_acta_solicitud,
                            lst_observaciones_rest);

                    List<entidad.Comeval_Licencia_Docente> lst_comeval_licencia_docente = new ArrayList<>();
                    lst_comeval_licencia_docente.add(comeval_licencia_docente);

                    servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                    String resultado = cliente_api_comeval_rest.licencia_docente_ingresar(lst_comeval_licencia_docente);

                    PrimeFaces.current().executeScript("PF('ComevalLicenciaDocenteDialogVar').hide();");
                    // PrimeFaces.current().executeScript("PF('varTblComevalLicenciaDocente').clearFilters();");

                    String[] mensaje = resultado.split(",");
                    if (mensaje[0].equals("0")) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", mensaje[1]));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", mensaje[1]));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", "Debe selecionar al menos una plaza para aplicar la licencia."));
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", "Debe ingresar un docente registrado en el sistema."));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Crear_Licencia_Docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }
    
    public void Modificar_Licencia_Docente() {
        try {
            if (!this.personal.equals("0")) {
                if (this.plazas.getTarget().size() > 0) {
                    if (this.rechazado_form) {
                        this.rechazado = Long.parseLong("1");
                    } else {
                        this.rechazado = Long.parseLong("0");
                    }

                    if (this.ingreso_siif_visto_bueno_escuela_form) {
                        this.ingreso_siif_visto_bueno_escuela = Long.parseLong("1");
                    } else {
                        this.ingreso_siif_visto_bueno_escuela = Long.parseLong("0");
                    }

                    if (this.tipo_licencia_secretario_academico_form) {
                        this.tipo_licencia_secretario_academico = Long.parseLong("1");
                    } else {
                        this.tipo_licencia_secretario_academico = Long.parseLong("0");
                    }

                    if (this.acuerdo_decanatura_form) {
                        this.acuerdo_decanatura = Long.parseLong("1");
                    } else {
                        this.acuerdo_decanatura = Long.parseLong("0");
                    }

                    if (this.notificacion_tesoreria_form) {
                        this.notificacion_tesoreria = Long.parseLong("1");
                    } else {
                        this.notificacion_tesoreria = Long.parseLong("0");
                    }

                    List<entidad.Comeval_Licencia_Docente_Plaza> lst_plazas = new ArrayList<>();
                    for (Integer i = 0; i < this.plazas.getTarget().size(); i++) {
                        String cadenasql = "select pp.personal, pp.plaza, pp.periodo, pp.anio, pp.subpartida, pp.renglon, p.numero "
                                + "from plazapersonal pp left join plaza p on (pp.plaza = p.plaza) "
                                + "where (pp.anio || '-' || pp.periodo || '-' || p.numero || '-' || pp.subpartida) = '" + this.plazas.getTarget().get(i) + "'";
                        servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                        String jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
                        Type listType = new TypeToken<ArrayList<String>>() {
                        }.getType();
                        List<String> lista_drive = new Gson().fromJson(jsonString, listType);
                        for (Integer j = 1; j < lista_drive.size(); j++) {
                            String[] col = lista_drive.get(j).split("♣");
                            entidad.Comeval_Licencia_Docente_Plaza comeval_licencia_docente_plaza = new entidad.Comeval_Licencia_Docente_Plaza(
                                    this.id_comeval_licencia_docente,
                                    col[0],
                                    Long.parseLong(col[1]),
                                    Long.parseLong(col[2]),
                                    Long.parseLong(col[3]),
                                    col[4],
                                    col[5],
                                    Long.parseLong(col[6])
                            );
                            lst_plazas.add(comeval_licencia_docente_plaza);
                        }
                    }
                    
                    entidad.Comeval_Acta_Solicitud comeval_acta_solicitud = null;

                    List<entidad.Comeval_Solicitud_Observacion> lst_observaciones_rest = new ArrayList<>();
                    for (Integer i = 0; i < this.lst_observaciones.size(); i++) {
                        entidad.Comeval_Solicitud_Observacion observacion_temp = new entidad.Comeval_Solicitud_Observacion();
                        observacion_temp.setId_solicitud(this.id_comeval_licencia_docente);
                        observacion_temp.setId_tipo_solicitud(this.id_tipo_solicitud);
                        observacion_temp.setId_observacion(this.lst_observaciones.get(i).getId_observacion());
                        observacion_temp.setDependencia(this.lst_observaciones.get(i).getDependencia());
                        observacion_temp.setFecha_hora(this.lst_observaciones.get(i).getFecha_hora());
                        observacion_temp.setObservacion(this.lst_observaciones.get(i).getObservacion());
                        lst_observaciones_rest.add(observacion_temp);
                    }

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    entidad.Comeval_Licencia_Docente comeval_licencia_docente = new entidad.Comeval_Licencia_Docente(
                            this.id_comeval_licencia_docente,
                            this.personal,
                            this.id_motivo_licencia,
                            this.id_tipo_licencia,
                            this.goce_sueldo,
                            dateFormat.format(this.fecha_inicio_licencia),
                            dateFormat.format(this.fecha_final_licencia),
                            this.usuario.getUsuario(),
                            dateFormat.format(new Date()),
                            this.id_estado_solicitud,
                            this.id_tipo_solicitud,
                            this.rechazado,
                            this.id_estado_solicitud_rechazado,
                            this.id_tipo_solicitud_rechazado,
                            this.ingreso_siif_visto_bueno_escuela,
                            this.tipo_licencia_secretario_academico,
                            this.acuerdo_decanatura,
                            this.notificacion_tesoreria,
                            lst_plazas,
                            comeval_acta_solicitud,
                            lst_observaciones_rest);

                    List<entidad.Comeval_Licencia_Docente> lst_comeval_licencia_docente = new ArrayList<>();
                    lst_comeval_licencia_docente.add(comeval_licencia_docente);

                    servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                    String resultado = cliente_api_comeval_rest.licencia_docente_modificar(lst_comeval_licencia_docente);

                    PrimeFaces.current().executeScript("PF('ComevalLicenciaDocenteDialogVar').hide();");
                    // PrimeFaces.current().executeScript("PF('varTblComevalLicenciaDocente').clearFilters();");

                    String[] mensaje = resultado.split(",");
                    if (mensaje[0].equals("0")) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", mensaje[1]));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", mensaje[1]));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", "Debe selecionar al menos una plaza para aplicar la licencia."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", "Debe ingresar un docente registrado en el sistema."));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Modificar_Licencia_Docente ERROR: " + ex.toString());
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
             
            List<String> plazas_destino = new ArrayList<>();
            cadenasql = "select (cldp.anio || '-' || cldp.periodo || '-' || cldp.numero_plaza || '-' || cldp.subpartida) id_plaza "
                    + "from comeval_licencia_docente_plazas cldp "
                    + "where cldp.id_comeval_licencia_docente=" + this.id_comeval_licencia_docente;
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] rol = lista_drive.get(i).split("♣");
                plazas_destino.add(rol[0]);
            }
            
            Integer anio_actual = Calendar.getInstance().get(Calendar.YEAR);
            Integer mes_actual = Calendar.getInstance().get(Calendar.MONTH);
            Integer periodo_actual = 0;
            if(mes_actual <= 6) {
                periodo_actual = 1;
            } else {
                periodo_actual = 2;
            }
            
            // BORRAR ESTA LINEA EN PRODUCCION, SOLO FUNCIONA PARA PRUEBAS.
            anio_actual = 2021; 
            
            List<String> plazas_fuente = new ArrayList<>();
            cadenasql = "select (pp.anio || '-' || pp.periodo || '-' || p.numero || '-' || pp.subpartida) id_plaza "
                    + "from "
                    + "plazapersonal pp "
                    + "left join plaza p on (pp.plaza = p.plaza) "
                    + "where "
                    + "pp.personal = '" + this.codigo_docente + "' and "
                    + "pp.anio = " + anio_actual + " and "
                    + "pp.periodo = " + periodo_actual + " and "
                    + "(pp.anio || '-' || pp.periodo || '-' || p.numero || '-' || pp.subpartida) not in ("
                    + "select (cldp.anio || '-' || cldp.periodo || '-' || cldp.numero_plaza || '-' || cldp.subpartida) "
                    + "from comeval_licencia_docente_plazas cldp "
                    + "where cldp.id_comeval_licencia_docente=" + this.id_comeval_licencia_docente + ")";
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] rol = lista_drive.get(i).split("♣");
                plazas_fuente.add(rol[0]);
            }
            
            this.plazas = new DualListModel<>(plazas_fuente, plazas_destino);
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: datos_docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }
    
    public void agregar_observacion() {
        try {
            this.observacion = "";
            PrimeFaces.current().executeScript("PF('LicenciaObservacionesDialogVar').show();");
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
            beans.Comeval_Licencia_Docente.lista_observaciones observacion_dialog = new beans.Comeval_Licencia_Docente.lista_observaciones();
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

            PrimeFaces.current().executeScript("PF('LicenciaObservacionesDialogVar').hide();");
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

    public Long getId_comeval_licencia_docente() {
        return id_comeval_licencia_docente;
    }

    public void setId_comeval_licencia_docente(Long id_comeval_licencia_docente) {
        this.id_comeval_licencia_docente = id_comeval_licencia_docente;
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

    public Long getId_motivo_licencia() {
        return id_motivo_licencia;
    }

    public void setId_motivo_licencia(Long id_motivo_licencia) {
        this.id_motivo_licencia = id_motivo_licencia;
    }

    public Long getId_tipo_licencia() {
        return id_tipo_licencia;
    }

    public void setId_tipo_licencia(Long id_tipo_licencia) {
        this.id_tipo_licencia = id_tipo_licencia;
    }

    public String getGoce_sueldo() {
        return goce_sueldo;
    }

    public void setGoce_sueldo(String goce_sueldo) {
        this.goce_sueldo = goce_sueldo;
    }

    public Date getFecha_inicio_licencia() {
        return fecha_inicio_licencia;
    }

    public void setFecha_inicio_licencia(Date fecha_inicio_licencia) {
        this.fecha_inicio_licencia = fecha_inicio_licencia;
    }

    public Date getFecha_final_licencia() {
        return fecha_final_licencia;
    }

    public void setFecha_final_licencia(Date fecha_final_licencia) {
        this.fecha_final_licencia = fecha_final_licencia;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
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

    public Long getRechazado() {
        return rechazado;
    }

    public void setRechazado(Long rechazado) {
        this.rechazado = rechazado;
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

    public Boolean getRechazado_form() {
        return rechazado_form;
    }

    public void setRechazado_form(Boolean rechazado_form) {
        this.rechazado_form = rechazado_form;
    }

    public Long getIngreso_siif_visto_bueno_escuela() {
        return ingreso_siif_visto_bueno_escuela;
    }

    public void setIngreso_siif_visto_bueno_escuela(Long ingreso_siif_visto_bueno_escuela) {
        this.ingreso_siif_visto_bueno_escuela = ingreso_siif_visto_bueno_escuela;
    }

    public Boolean getIngreso_siif_visto_bueno_escuela_form() {
        return ingreso_siif_visto_bueno_escuela_form;
    }

    public void setIngreso_siif_visto_bueno_escuela_form(Boolean ingreso_siif_visto_bueno_escuela_form) {
        this.ingreso_siif_visto_bueno_escuela_form = ingreso_siif_visto_bueno_escuela_form;
    }

    public Long getTipo_licencia_secretario_academico() {
        return tipo_licencia_secretario_academico;
    }

    public void setTipo_licencia_secretario_academico(Long tipo_licencia_secretario_academico) {
        this.tipo_licencia_secretario_academico = tipo_licencia_secretario_academico;
    }

    public Boolean getTipo_licencia_secretario_academico_form() {
        return tipo_licencia_secretario_academico_form;
    }

    public void setTipo_licencia_secretario_academico_form(Boolean tipo_licencia_secretario_academico_form) {
        this.tipo_licencia_secretario_academico_form = tipo_licencia_secretario_academico_form;
    }

    public Long getAcuerdo_decanatura() {
        return acuerdo_decanatura;
    }

    public void setAcuerdo_decanatura(Long acuerdo_decanatura) {
        this.acuerdo_decanatura = acuerdo_decanatura;
    }

    public Boolean getAcuerdo_decanatura_form() {
        return acuerdo_decanatura_form;
    }

    public void setAcuerdo_decanatura_form(Boolean acuerdo_decanatura_form) {
        this.acuerdo_decanatura_form = acuerdo_decanatura_form;
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

    public List<SelectItem> getLst_motivo_licencia() {
        return lst_motivo_licencia;
    }

    public void setLst_motivo_licencia(List<SelectItem> lst_motivo_licencia) {
        this.lst_motivo_licencia = lst_motivo_licencia;
    }

    public List<SelectItem> getLst_tipo_licencia() {
        return lst_tipo_licencia;
    }

    public void setLst_tipo_licencia(List<SelectItem> lst_tipo_licencia) {
        this.lst_tipo_licencia = lst_tipo_licencia;
    }

    public List<SelectItem> getLst_goce_sueldo() {
        return lst_goce_sueldo;
    }

    public void setLst_goce_sueldo(List<SelectItem> lst_goce_sueldo) {
        this.lst_goce_sueldo = lst_goce_sueldo;
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

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public DualListModel<String> getPlazas() {
        return plazas;
    }

    public void setPlazas(DualListModel<String> plazas) {
        this.plazas = plazas;
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

    public Boolean getCbxMotivoLicencia() {
        return cbxMotivoLicencia;
    }

    public void setCbxMotivoLicencia(Boolean cbxMotivoLicencia) {
        this.cbxMotivoLicencia = cbxMotivoLicencia;
    }

    public Boolean getCbxTipoLicencia() {
        return cbxTipoLicencia;
    }

    public void setCbxTipoLicencia(Boolean cbxTipoLicencia) {
        this.cbxTipoLicencia = cbxTipoLicencia;
    }

    public Boolean getCbxGoceSueldo() {
        return cbxGoceSueldo;
    }

    public void setCbxGoceSueldo(Boolean cbxGoceSueldo) {
        this.cbxGoceSueldo = cbxGoceSueldo;
    }

    public Boolean getCalFechaInicioLicencia() {
        return calFechaInicioLicencia;
    }

    public void setCalFechaInicioLicencia(Boolean calFechaInicioLicencia) {
        this.calFechaInicioLicencia = calFechaInicioLicencia;
    }

    public Boolean getCalFechaFinalLicencia() {
        return calFechaFinalLicencia;
    }

    public void setCalFechaFinalLicencia(Boolean calFechaFinalLicencia) {
        this.calFechaFinalLicencia = calFechaFinalLicencia;
    }

    public Boolean getChxRechazado() {
        return chxRechazado;
    }

    public void setChxRechazado(Boolean chxRechazado) {
        this.chxRechazado = chxRechazado;
    }

    public Boolean getChxIngresoSiif_VistoBuenoEscuela() {
        return chxIngresoSiif_VistoBuenoEscuela;
    }

    public void setChxIngresoSiif_VistoBuenoEscuela(Boolean chxIngresoSiif_VistoBuenoEscuela) {
        this.chxIngresoSiif_VistoBuenoEscuela = chxIngresoSiif_VistoBuenoEscuela;
    }

    public Boolean getChxTipoLicencia() {
        return chxTipoLicencia;
    }

    public void setChxTipoLicencia(Boolean chxTipoLicencia) {
        this.chxTipoLicencia = chxTipoLicencia;
    }

    public Boolean getChxAcuerdoDecanatura() {
        return chxAcuerdoDecanatura;
    }

    public void setChxAcuerdoDecanatura(Boolean chxAcuerdoDecanatura) {
        this.chxAcuerdoDecanatura = chxAcuerdoDecanatura;
    }

    public Boolean getChxNotificacionTesoreria() {
        return chxNotificacionTesoreria;
    }

    public void setChxNotificacionTesoreria(Boolean chxNotificacionTesoreria) {
        this.chxNotificacionTesoreria = chxNotificacionTesoreria;
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

    public Boolean getPklPlazas() {
        return pklPlazas;
    }

    public void setPklPlazas(Boolean pklPlazas) {
        this.pklPlazas = pklPlazas;
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
