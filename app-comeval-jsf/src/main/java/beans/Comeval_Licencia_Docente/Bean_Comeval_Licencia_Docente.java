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
    private String descripcion_solicitud;
    private Long id_solicitud_acta;
    private String no_acta;
    private String anio_acta;
    private String punto_acta;
    private String inciso_acta;
    private Date fecha_acta;
    private String resolucion_acta;
    private Date fecha_ingreso;
    private Long id_estado_solicitud;
    private Long id_tipo_solicitud;
    private Long rechazado;
    private Boolean rechazado_form;
    private Long visto_bueno_director;
    private Boolean visto_bueno_director_form;
    private Long ingreso_siif_traslado;
    private Boolean ingreso_siif_traslado_form;
    private Long confirmar_traslado;
    private Boolean confirmar_traslado_form;
    private Long asignar_tipo_licencia;
    private Boolean asignar_tipo_licencia_form;
    private Long aprobacion_decanatura;
    private Boolean aprobacion_decanatura_form;
    private Long notificacion_tesoreria;
    private Boolean notificacion_tesoreria_form;
    
    private List<SelectItem> lst_motivo_licencia;
    private List<SelectItem> lst_tipo_licencia;
    private List<SelectItem> lst_goce_sueldo;
    private List<SelectItem> lst_estado_solicitud;
    private List<SelectItem> lst_tipo_solicitud;
    
    private String opcion;
    private DualListModel<String> plazas;
   
    private Boolean cbxTipoSolicitud;
    private Boolean cbxEstadoSolicitud;
    private Boolean txtCodigoDocente;
    private Boolean txtNombreDocente;
    private Boolean cbxMotivoLicencia;
    private Boolean cbxTipoLicencia;
    private Boolean cbxGoceSueldo;
    private Boolean calFechaInicioLicencia;
    private Boolean calFechaFinalLicencia;
    private Boolean areObservacionDocente;
    private Boolean chxRechazado;
    private Boolean chxVistoBuenoDirector;
    private Boolean chxIngresoSiifTraslado;
    private Boolean chxConfirmarTraslado;
    private Boolean chxAsignarTipoLicencia;
    private Boolean chxAprobacionDecanatura;
    private Boolean chxNotificacionTesoreria;
    private Boolean pklPlazas;
    private Boolean btnAceptar;
    private Boolean btnCancelar;
    
    public Bean_Comeval_Licencia_Docente() {
        try {
            this.lst_motivo_licencia = new ArrayList<>();
            this.lst_tipo_licencia = new ArrayList<>();
            this.lst_goce_sueldo = new ArrayList<>();
            this.lst_estado_solicitud = new ArrayList<>();
            this.lst_tipo_solicitud = new ArrayList<>();
            
            List<String> plazas_fuente = new ArrayList<>();
            List<String> plazas_destino = new ArrayList<>();
            this.plazas = new DualListModel<>(plazas_fuente, plazas_destino);
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
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // Valores iniciales del formulario.
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
            this.descripcion_solicitud = "-";
            this.id_solicitud_acta = Long.parseLong("0");
            this.no_acta = "-";
            this.anio_acta = "-";
            this.punto_acta = "-";
            this.inciso_acta = "-";
            this.fecha_acta = dateFormat.parse("1900-01-01 00:00:00");
            this.resolucion_acta = "-";
            this.fecha_ingreso = dateFormat.parse("1900-01-01 00:00:00");
            this.rechazado = Long.parseLong("0");
            this.visto_bueno_director = Long.parseLong("0");
            this.ingreso_siif_traslado = Long.parseLong("0");
            this.confirmar_traslado = Long.parseLong("0");
            this.asignar_tipo_licencia = Long.parseLong("0");
            this.aprobacion_decanatura = Long.parseLong("0");
            this.notificacion_tesoreria = Long.parseLong("0");
            this.rechazado_form = false;
            this.visto_bueno_director_form = false;
            this.ingreso_siif_traslado_form = false;
            this.confirmar_traslado_form = false;
            this.asignar_tipo_licencia_form = false;
            this.aprobacion_decanatura_form = false;
            this.notificacion_tesoreria_form = false;
            
            this.cbxTipoSolicitud = true;
            this.cbxEstadoSolicitud = true;
            this.txtCodigoDocente = false;
            this.txtNombreDocente = true;
            this.cbxMotivoLicencia = false;
            this.cbxTipoLicencia = false;
            this.cbxGoceSueldo = false;
            this.calFechaInicioLicencia = false;
            this.calFechaFinalLicencia = false;
            this.areObservacionDocente = false;
            this.chxRechazado = true;
            this.chxVistoBuenoDirector = true;
            this.chxIngresoSiifTraslado = true;
            this.chxConfirmarTraslado = true;
            this.chxAsignarTipoLicencia = true;
            this.chxAprobacionDecanatura = true;
            this.chxNotificacionTesoreria = true;
            this.pklPlazas = false;
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
            
            cadenasql = "select "
                    + "t.id_comeval_licencia_docente, "
                    + "t.personal, "
                    + "t.id_motivo_licencia, "
                    + "t.id_tipo_licencia, "
                    + "t.goce_sueldo, "
                    + "t.fecha_inicio_licencia, "
                    + "t.fecha_final_licencia, "
                    + "t.descripcion_solicitud, "
                    + "t.id_solicitud_acta, "
                    + "t.no_acta, "
                    + "t.anio_acta, "
                    + "t.punto_acta, "
                    + "t.inciso_acta, "
                    + "t.fecha_acta, "
                    + "t.resolucion_acta, "
                    + "t.fecha_ingreso, "
                    + "t.id_estado_solicitud, "
                    + "t.id_tipo_solicitud, "
                    + "t.rechazado, "
                    + "t.visto_bueno_director, "
                    + "t.ingreso_siif_traslado, "
                    + "t.confirmar_traslado, "
                    + "t.asignar_tipo_licencia, "
                    + "t.aprobacion_decanatura, "
                    + "t.notificacion_tesoreria "
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
                this.descripcion_solicitud =col[7];
                this.id_solicitud_acta = Long.parseLong(col[8]);
                this.no_acta = col[9];
                this.anio_acta = col[10];
                this.punto_acta = col[11];
                this.inciso_acta = col[12];
                this.fecha_acta = dateFormat.parse(col[13]);
                this.resolucion_acta = col[14];
                this.fecha_ingreso = dateFormat.parse(col[15]);
                this.id_estado_solicitud = Long.parseLong(col[16]);
                this.id_tipo_solicitud = Long.parseLong(col[17]);
                this.rechazado = Long.parseLong(col[18]);
                if (this.rechazado == Long.parseLong("0")) {
                    this.rechazado_form = false;
                } else {
                    this.rechazado_form = true;
                }
                this.visto_bueno_director = Long.parseLong(col[19]);
                if (this.visto_bueno_director == Long.parseLong("0")) {
                    this.visto_bueno_director_form = false;
                } else {
                    this.visto_bueno_director_form = true;
                }
                this.ingreso_siif_traslado = Long.parseLong(col[20]);
                if (this.ingreso_siif_traslado == Long.parseLong("0")) {
                    this.ingreso_siif_traslado_form = false;
                } else {
                    this.ingreso_siif_traslado_form = true;
                }
                this.confirmar_traslado = Long.parseLong(col[21]);
                if (this.confirmar_traslado == Long.parseLong("0")) {
                    this.confirmar_traslado_form = false;
                } else {
                    this.confirmar_traslado_form = true;
                }
                this.asignar_tipo_licencia = Long.parseLong(col[22]);
                if (this.asignar_tipo_licencia == Long.parseLong("0")) {
                    this.asignar_tipo_licencia_form = false;
                } else {
                    this.asignar_tipo_licencia_form = true;
                }
                this.aprobacion_decanatura = Long.parseLong(col[23]);
                if (this.aprobacion_decanatura == Long.parseLong("0")) {
                    this.aprobacion_decanatura_form = false;
                } else {
                    this.aprobacion_decanatura_form = true;
                }
                this.notificacion_tesoreria = Long.parseLong(col[24]);
                if (this.notificacion_tesoreria == Long.parseLong("0")) {
                    this.notificacion_tesoreria_form = false;
                } else {
                    this.notificacion_tesoreria_form = true;
                }
            }
            
            if (this.id_estado_solicitud == Long.parseLong("1")) {
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.cbxMotivoLicencia = false;
                this.cbxTipoLicencia = false;
                this.cbxGoceSueldo = false;
                this.calFechaInicioLicencia = false;
                this.calFechaFinalLicencia = false;
                this.areObservacionDocente = false;
                this.chxRechazado = true;
                this.chxVistoBuenoDirector = true;
                this.chxIngresoSiifTraslado = true;
                this.chxConfirmarTraslado = true;
                this.chxAsignarTipoLicencia = true;
                this.chxAprobacionDecanatura = true;
                this.chxNotificacionTesoreria = true;
                this.pklPlazas = false;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("2")) {
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.cbxMotivoLicencia = true;
                this.cbxTipoLicencia = true;
                this.cbxGoceSueldo = true;
                this.calFechaInicioLicencia = true;
                this.calFechaFinalLicencia = true;
                this.areObservacionDocente = false;
                this.chxRechazado = true;
                this.chxVistoBuenoDirector = false;
                this.chxIngresoSiifTraslado = true;
                this.chxConfirmarTraslado = true;
                this.chxAsignarTipoLicencia = true;
                this.chxAprobacionDecanatura = true;
                this.chxNotificacionTesoreria = true;
                this.pklPlazas = true;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("3")) {
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.cbxMotivoLicencia = true;
                this.cbxTipoLicencia = true;
                this.cbxGoceSueldo = true;
                this.calFechaInicioLicencia = true;
                this.calFechaFinalLicencia = true;
                this.areObservacionDocente = false;
                this.chxRechazado = true;
                this.chxVistoBuenoDirector = true;
                this.chxIngresoSiifTraslado = false;
                this.chxConfirmarTraslado = true;
                this.chxAsignarTipoLicencia = true;
                this.chxAprobacionDecanatura = true;
                this.chxNotificacionTesoreria = true;
                this.pklPlazas = true;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("4")) {
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.cbxMotivoLicencia = true;
                this.cbxTipoLicencia = true;
                this.cbxGoceSueldo = true;
                this.calFechaInicioLicencia = true;
                this.calFechaFinalLicencia = true;
                this.areObservacionDocente = false;
                this.chxRechazado = true;
                this.chxVistoBuenoDirector = true;
                this.chxIngresoSiifTraslado = true;
                this.chxConfirmarTraslado = false;
                this.chxAsignarTipoLicencia = true;
                this.chxAprobacionDecanatura = true;
                this.chxNotificacionTesoreria = true;
                this.pklPlazas = true;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("5")) {
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.cbxMotivoLicencia = true;
                this.cbxTipoLicencia = false;
                this.cbxGoceSueldo = true;
                this.calFechaInicioLicencia = true;
                this.calFechaFinalLicencia = true;
                this.areObservacionDocente = false;
                this.chxRechazado = true;
                this.chxVistoBuenoDirector = true;
                this.chxIngresoSiifTraslado = true;
                this.chxConfirmarTraslado = true;
                this.chxAsignarTipoLicencia = false;
                this.chxAprobacionDecanatura = true;
                this.chxNotificacionTesoreria = true;
                this.pklPlazas = true;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("6")) {
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.cbxMotivoLicencia = true;
                this.cbxTipoLicencia = true;
                this.cbxGoceSueldo = true;
                this.calFechaInicioLicencia = true;
                this.calFechaFinalLicencia = true;
                this.areObservacionDocente = false;
                this.chxRechazado = true;
                this.chxVistoBuenoDirector = true;
                this.chxIngresoSiifTraslado = true;
                this.chxConfirmarTraslado = true;
                this.chxAsignarTipoLicencia = true;
                this.chxAprobacionDecanatura = false;
                this.chxNotificacionTesoreria = true;
                this.pklPlazas = true;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("8")) {
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.cbxMotivoLicencia = true;
                this.cbxTipoLicencia = true;
                this.cbxGoceSueldo = true;
                this.calFechaInicioLicencia = true;
                this.calFechaFinalLicencia = true;
                this.areObservacionDocente = false;
                this.chxRechazado = true;
                this.chxVistoBuenoDirector = true;
                this.chxIngresoSiifTraslado = true;
                this.chxConfirmarTraslado = true;
                this.chxAsignarTipoLicencia = true;
                this.chxAprobacionDecanatura = true;
                this.chxNotificacionTesoreria = false;
                this.pklPlazas = true;
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

                    if (this.visto_bueno_director_form) {
                        this.visto_bueno_director = Long.parseLong("1");
                    } else {
                        this.visto_bueno_director = Long.parseLong("0");
                    }

                    if (this.ingreso_siif_traslado_form) {
                        this.ingreso_siif_traslado = Long.parseLong("1");
                    } else {
                        this.ingreso_siif_traslado = Long.parseLong("0");
                    }

                    if (this.confirmar_traslado_form) {
                        this.confirmar_traslado = Long.parseLong("1");
                    } else {
                        this.confirmar_traslado = Long.parseLong("0");
                    }

                    if (this.asignar_tipo_licencia_form) {
                        this.asignar_tipo_licencia = Long.parseLong("1");
                    } else {
                        this.asignar_tipo_licencia = Long.parseLong("0");
                    }

                    if (this.aprobacion_decanatura_form) {
                        this.aprobacion_decanatura = Long.parseLong("1");
                    } else {
                        this.aprobacion_decanatura = Long.parseLong("0");
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

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    entidad.Comeval_Licencia_Docente comeval_licencia_docente = new entidad.Comeval_Licencia_Docente(
                            this.id_comeval_licencia_docente,
                            this.personal,
                            this.id_motivo_licencia,
                            this.id_tipo_licencia,
                            this.goce_sueldo,
                            dateFormat.format(this.fecha_inicio_licencia),
                            dateFormat.format(this.fecha_final_licencia),
                            this.descripcion_solicitud,
                            this.id_solicitud_acta,
                            this.no_acta,
                            this.anio_acta,
                            this.punto_acta,
                            this.inciso_acta,
                            dateFormat.format(this.fecha_acta), // FECHA ACTA.
                            this.resolucion_acta,
                            dateFormat.format(new Date()), // FECHA INGRESO.
                            this.id_estado_solicitud,
                            this.id_tipo_solicitud,
                            this.rechazado,
                            this.visto_bueno_director,
                            this.ingreso_siif_traslado,
                            this.confirmar_traslado,
                            this.asignar_tipo_licencia,
                            this.aprobacion_decanatura,
                            this.notificacion_tesoreria,
                            lst_plazas);

                    List<entidad.Comeval_Licencia_Docente> lst_comeval_licencia_docente = new ArrayList<>();
                    lst_comeval_licencia_docente.add(comeval_licencia_docente);

                    servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                    String resultado = cliente_api_comeval_rest.crear_licencia_docente(lst_comeval_licencia_docente);

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

                    if (this.visto_bueno_director_form) {
                        this.visto_bueno_director = Long.parseLong("1");
                    } else {
                        this.visto_bueno_director = Long.parseLong("0");
                    }

                    if (this.ingreso_siif_traslado_form) {
                        this.ingreso_siif_traslado = Long.parseLong("1");
                    } else {
                        this.ingreso_siif_traslado = Long.parseLong("0");
                    }

                    if (this.confirmar_traslado_form) {
                        this.confirmar_traslado = Long.parseLong("1");
                    } else {
                        this.confirmar_traslado = Long.parseLong("0");
                    }

                    if (this.asignar_tipo_licencia_form) {
                        this.asignar_tipo_licencia = Long.parseLong("1");
                    } else {
                        this.asignar_tipo_licencia = Long.parseLong("0");
                    }

                    if (this.aprobacion_decanatura_form) {
                        this.aprobacion_decanatura = Long.parseLong("1");
                    } else {
                        this.aprobacion_decanatura = Long.parseLong("0");
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

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    entidad.Comeval_Licencia_Docente comeval_licencia_docente = new entidad.Comeval_Licencia_Docente(
                            this.id_comeval_licencia_docente,
                            this.personal,
                            this.id_motivo_licencia,
                            this.id_tipo_licencia,
                            this.goce_sueldo,
                            dateFormat.format(this.fecha_inicio_licencia),
                            dateFormat.format(this.fecha_final_licencia),
                            this.descripcion_solicitud,
                            this.id_solicitud_acta,
                            this.no_acta,
                            this.anio_acta,
                            this.punto_acta,
                            this.inciso_acta,
                            dateFormat.format(this.fecha_acta), // FECHA ACTA.
                            this.resolucion_acta,
                            dateFormat.format(this.fecha_ingreso), // FECHA INGRESO.
                            this.id_estado_solicitud,
                            this.id_tipo_solicitud,
                            this.rechazado,
                            this.visto_bueno_director,
                            this.ingreso_siif_traslado,
                            this.confirmar_traslado,
                            this.asignar_tipo_licencia,
                            this.aprobacion_decanatura,
                            this.notificacion_tesoreria,
                            lst_plazas);

                    List<entidad.Comeval_Licencia_Docente> lst_comeval_licencia_docente = new ArrayList<>();
                    lst_comeval_licencia_docente.add(comeval_licencia_docente);

                    servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                    String resultado = cliente_api_comeval_rest.modificar_licencia_docente(lst_comeval_licencia_docente);

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

    public String getDescripcion_solicitud() {
        return descripcion_solicitud;
    }

    public void setDescripcion_solicitud(String descripcion_solicitud) {
        this.descripcion_solicitud = descripcion_solicitud;
    }

    public Long getId_solicitud_acta() {
        return id_solicitud_acta;
    }

    public void setId_solicitud_acta(Long id_solicitud_acta) {
        this.id_solicitud_acta = id_solicitud_acta;
    }

    public String getNo_acta() {
        return no_acta;
    }

    public void setNo_acta(String no_acta) {
        this.no_acta = no_acta;
    }

    public String getAnio_acta() {
        return anio_acta;
    }

    public void setAnio_acta(String anio_acta) {
        this.anio_acta = anio_acta;
    }

    public String getPunto_acta() {
        return punto_acta;
    }

    public void setPunto_acta(String punto_acta) {
        this.punto_acta = punto_acta;
    }

    public String getInciso_acta() {
        return inciso_acta;
    }

    public void setInciso_acta(String inciso_acta) {
        this.inciso_acta = inciso_acta;
    }

    public Date getFecha_acta() {
        return fecha_acta;
    }

    public void setFecha_acta(Date fecha_acta) {
        this.fecha_acta = fecha_acta;
    }

    public String getResolucion_acta() {
        return resolucion_acta;
    }

    public void setResolucion_acta(String resolucion_acta) {
        this.resolucion_acta = resolucion_acta;
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

    public Long getIngreso_siif_traslado() {
        return ingreso_siif_traslado;
    }

    public void setIngreso_siif_traslado(Long ingreso_siif_traslado) {
        this.ingreso_siif_traslado = ingreso_siif_traslado;
    }

    public Boolean getIngreso_siif_traslado_form() {
        return ingreso_siif_traslado_form;
    }

    public void setIngreso_siif_traslado_form(Boolean ingreso_siif_traslado_form) {
        this.ingreso_siif_traslado_form = ingreso_siif_traslado_form;
    }

    public Long getConfirmar_traslado() {
        return confirmar_traslado;
    }

    public void setConfirmar_traslado(Long confirmar_traslado) {
        this.confirmar_traslado = confirmar_traslado;
    }

    public Boolean getConfirmar_traslado_form() {
        return confirmar_traslado_form;
    }

    public void setConfirmar_traslado_form(Boolean confirmar_traslado_form) {
        this.confirmar_traslado_form = confirmar_traslado_form;
    }

    public Long getAsignar_tipo_licencia() {
        return asignar_tipo_licencia;
    }

    public void setAsignar_tipo_licencia(Long asignar_tipo_licencia) {
        this.asignar_tipo_licencia = asignar_tipo_licencia;
    }

    public Boolean getAsignar_tipo_licencia_form() {
        return asignar_tipo_licencia_form;
    }

    public void setAsignar_tipo_licencia_form(Boolean asignar_tipo_licencia_form) {
        this.asignar_tipo_licencia_form = asignar_tipo_licencia_form;
    }

    public Long getAprobacion_decanatura() {
        return aprobacion_decanatura;
    }

    public void setAprobacion_decanatura(Long aprobacion_decanatura) {
        this.aprobacion_decanatura = aprobacion_decanatura;
    }

    public Boolean getAprobacion_decanatura_form() {
        return aprobacion_decanatura_form;
    }

    public void setAprobacion_decanatura_form(Boolean aprobacion_decanatura_form) {
        this.aprobacion_decanatura_form = aprobacion_decanatura_form;
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

    public Boolean getAreObservacionDocente() {
        return areObservacionDocente;
    }

    public void setAreObservacionDocente(Boolean areObservacionDocente) {
        this.areObservacionDocente = areObservacionDocente;
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

    public Boolean getChxIngresoSiifTraslado() {
        return chxIngresoSiifTraslado;
    }

    public void setChxIngresoSiifTraslado(Boolean chxIngresoSiifTraslado) {
        this.chxIngresoSiifTraslado = chxIngresoSiifTraslado;
    }

    public Boolean getChxConfirmarTraslado() {
        return chxConfirmarTraslado;
    }

    public void setChxConfirmarTraslado(Boolean chxConfirmarTraslado) {
        this.chxConfirmarTraslado = chxConfirmarTraslado;
    }

    public Boolean getChxAsignarTipoLicencia() {
        return chxAsignarTipoLicencia;
    }

    public void setChxAsignarTipoLicencia(Boolean chxAsignarTipoLicencia) {
        this.chxAsignarTipoLicencia = chxAsignarTipoLicencia;
    }

    public Boolean getChxAprobacionDecanatura() {
        return chxAprobacionDecanatura;
    }

    public void setChxAprobacionDecanatura(Boolean chxAprobacionDecanatura) {
        this.chxAprobacionDecanatura = chxAprobacionDecanatura;
    }

    public Boolean getChxNotificacionTesoreria() {
        return chxNotificacionTesoreria;
    }

    public void setChxNotificacionTesoreria(Boolean chxNotificacionTesoreria) {
        this.chxNotificacionTesoreria = chxNotificacionTesoreria;
    }

    public Boolean getPklPlazas() {
        return pklPlazas;
    }

    public void setPklPlazas(Boolean pklPlazas) {
        this.pklPlazas = pklPlazas;
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
