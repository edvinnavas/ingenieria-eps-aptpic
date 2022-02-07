package beans.Comeval_Promocion_Docente;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.InputStream;
import javax.inject.Named;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import org.apache.commons.io.FileUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named(value = "Bean_Comeval_Promocion_Docente")
@ViewScoped
public class Bean_Comeval_Promocion_Docente implements Serializable {

    private static final long serialVersionUID = 1L;

    private entidad.Usuario usuario;

    private Long id_comeval_promocion_docente;
    private String personal;
    private String codigo_docente;
    private String nombre_docente;
    private String titularidad_actual;
    private Long tipoascenso;
    private String puesto;
    private Date fecha_promueve;
    private String nota_ref_comeval;
    private Date fecha_nota_ref_comeval;
    private Date fecha_ingreso;
    private Long id_estado_solicitud;
    private Long id_tipo_solicitud;
    private Long rechazado;
    private Long id_estado_solicitud_rechazado;
    private Long id_tipo_solicitud_rechazado;
    private Boolean rechazado_form;
    private Long revision_comeval;
    private Boolean revision_comeval_form;
    private Long revision_secretario_academico;
    private Boolean revision_secretario_academico_form;

    private List<SelectItem> lst_tipoascenso;
    private List<SelectItem> lst_puesto;
    private List<SelectItem> lst_estado_solicitud;
    private List<SelectItem> lst_tipo_solicitud;
    private List<SelectItem> lst_estado_solicitud_rechazado;
    private List<SelectItem> lst_tipo_solicitud_rechazado;

    private String opcion;

    private List<lista_archivos> lst_archivo_rest;
    private lista_archivos sel_archivo_rest;

    private String dependencia_observaciones;
    private String observacion;

    private List<lista_observaciones> lst_observaciones;
    private lista_observaciones sel_observaciones;

    private StreamedContent file;

    private String tipo_adjunto;
    private List<SelectItem> lst_tipo_adjunto;

    private Boolean cbxTipoSolicitud;
    private Boolean cbxEstadoSolicitud;
    private Boolean txtCodigoDocente;
    private Boolean txtNombreDocente;
    private Boolean txtTitularidadActual;
    private Boolean cbxRazonTitularidad;
    private Boolean cbxTipoTitularidad;
    private Boolean calFechaPromueve;
    private Boolean txtNotaRefComeval;
    private Boolean calFechaNotaRefComeval;
    private Boolean fupExcel;
    private Boolean chxRechazado;
    private Boolean chxRevisionComeval;
    private Boolean chxRevisionSecretarioAcademico;
    private Boolean cbxTipoSolicitudRechazado;
    private Boolean cbxEstadoSolicitudRechazado;
    private Boolean btnEliminarArchivo;
    private Boolean btnAgregarObservacion;
    private Boolean btnEliminarObservacion;
    private Boolean btnAceptar;
    private Boolean btnCancelar;

    public Bean_Comeval_Promocion_Docente() {
        try {
            this.lst_tipoascenso = new ArrayList<>();
            this.lst_puesto = new ArrayList<>();
            this.lst_estado_solicitud = new ArrayList<>();
            this.lst_tipo_solicitud = new ArrayList<>();
            this.lst_archivo_rest = new ArrayList<>();
            this.lst_observaciones = new ArrayList<>();
            this.lst_tipo_adjunto = new ArrayList<>();
            this.lst_estado_solicitud_rechazado = new ArrayList<>();
            this.lst_tipo_solicitud_rechazado = new ArrayList<>();
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Bean_Comeval_Promocion_Docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void mostrar_crear_promocion_docente(entidad.Usuario usuario, Long id_estado_solicitud, Long id_tipo_solicitud) {
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

            this.lst_tipoascenso = new ArrayList<>();
            cadenasql = "select t.tipoascenso, t.nombre "
                    + "from promocion_docente_tipoascenso pdt left join tipoascenso t on (pdt.tipoascenso=t.tipoascenso) "
                    + "where pdt.id_tipo_solicitud=" + this.id_tipo_solicitud + " and pdt.id_estado_solicitud=" + this.id_estado_solicitud + " "
                    + "order by t.nombre";
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] razon_titularidad = lista_drive.get(i).split("♣");
                this.lst_tipoascenso.add(new SelectItem(Long.parseLong(razon_titularidad[0]), razon_titularidad[1]));
            }

            this.lst_tipo_solicitud_rechazado = this.lst_tipo_solicitud;
            this.id_tipo_solicitud_rechazado = Long.parseLong("1");

            this.lst_estado_solicitud_rechazado = this.lst_estado_solicitud;
            this.id_estado_solicitud_rechazado = Long.parseLong("1");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            this.id_comeval_promocion_docente = Long.parseLong("1");
            this.personal = "0";
            this.codigo_docente = this.usuario.getUsuario();
            this.nombre_docente = "-";
            this.titularidad_actual = "-";
            this.datos_docente();
            this.tipoascenso = Long.parseLong("1");
            this.puesto = "0";
            this.fecha_promueve = new Date();
            this.nota_ref_comeval = "-";
            this.fecha_nota_ref_comeval = new Date();
            this.fecha_ingreso = dateFormat.parse("1900-01-01 00:00:00");
            this.rechazado = Long.parseLong("0");
            this.revision_comeval = Long.parseLong("0");
            this.revision_secretario_academico = Long.parseLong("0");
            this.rechazado_form = false;
            this.revision_comeval_form = false;
            this.revision_secretario_academico_form = false;

            this.lst_tipo_adjunto = new ArrayList<>();
            if (this.id_estado_solicitud.equals(Long.parseLong("1"))) {
                this.dependencia_observaciones = "DOCENTE";
                this.lst_tipo_adjunto.add(new SelectItem("exp", "Expediente Docente"));
                this.tipo_adjunto = "exp";
                this.calFechaPromueve = true;
                this.txtNotaRefComeval = true;
                this.calFechaNotaRefComeval = true;
            }
            if (this.id_estado_solicitud.equals(Long.parseLong("2"))) {
                this.dependencia_observaciones = "COMEVAL";
                this.lst_tipo_adjunto.add(new SelectItem("exp", "Expediente Docente"));
                this.lst_tipo_adjunto.add(new SelectItem("hrl", "Hoja Relación Laboral"));
                this.lst_tipo_adjunto.add(new SelectItem("cpd", "Cuadro Promoción Docente"));
                this.tipo_adjunto = "hrl";
                this.calFechaPromueve = false;
                this.txtNotaRefComeval = false;
                this.calFechaNotaRefComeval = false;
            }
            if (this.id_estado_solicitud.equals(Long.parseLong("3"))) {
                this.dependencia_observaciones = "SECRETARIO ACADÉMICO";
                this.lst_tipo_adjunto.add(new SelectItem("exp", "Expediente Docente"));
                this.lst_tipo_adjunto.add(new SelectItem("hrl", "Hoja Relación Laboral"));
                this.lst_tipo_adjunto.add(new SelectItem("cpd", "Cuadro Promoción Docente"));
                this.tipo_adjunto = "cpd";
                this.calFechaPromueve = false;
                this.txtNotaRefComeval = false;
                this.calFechaNotaRefComeval = false;
            }
            if (this.id_estado_solicitud.equals(Long.parseLong("4"))) {
                this.dependencia_observaciones = "COMEVAL";
                this.calFechaPromueve = false;
                this.txtNotaRefComeval = false;
                this.calFechaNotaRefComeval = false;
            }
            if (this.id_estado_solicitud.equals(Long.parseLong("5"))) {
                this.dependencia_observaciones = "SECRETARIO ACADÉMICO";
                this.calFechaPromueve = false;
                this.txtNotaRefComeval = false;
                this.calFechaNotaRefComeval = false;
            }

            this.lst_archivo_rest = new ArrayList<>();
            this.lst_observaciones = new ArrayList<>();

            this.cbxTipoSolicitud = true;
            this.cbxEstadoSolicitud = true;
            this.txtCodigoDocente = false;
            this.txtNombreDocente = true;
            this.txtTitularidadActual = true;
            this.calFechaPromueve = true;
            this.cbxRazonTitularidad = false;
            this.cbxTipoTitularidad = false;
            this.fupExcel = false;
            this.chxRechazado = true;
            this.cbxTipoSolicitudRechazado = true;
            this.cbxEstadoSolicitudRechazado = true;
            this.chxRevisionComeval = true;
            this.chxRevisionSecretarioAcademico = true;
            this.btnEliminarArchivo = false;
            this.btnAgregarObservacion = false;
            this.btnEliminarObservacion = false;
            this.btnAceptar = false;
            this.btnCancelar = false;

            PrimeFaces.current().executeScript("PF('ComevalPromocionDocenteDialogVar').show();");
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: mostrar_crear_promocion_docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void mostrar_modificar_promocion_docente(entidad.Usuario usuario, Long id_comeval_promocion_docente, Long id_estado_solicitud, Long id_tipo_solicitud) {
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

            this.lst_tipoascenso = new ArrayList<>();
            if (id_estado_solicitud == Long.parseLong("1") || id_estado_solicitud == Long.parseLong("2") || id_estado_solicitud == Long.parseLong("3")) {
                cadenasql = "select t.tipoascenso, t.nombre "
                        + "from promocion_docente_tipoascenso pdt left join tipoascenso t on (pdt.tipoascenso=t.tipoascenso) "
                        + "where pdt.id_tipo_solicitud=" + id_tipo_solicitud + " and pdt.id_estado_solicitud=" + id_estado_solicitud + " "
                        + "order by t.nombre";
            } else {
                cadenasql = "select t.tipoascenso, t.nombre "
                        + "from promocion_docente_tipoascenso pdt left join tipoascenso t on (pdt.tipoascenso=t.tipoascenso) "
                        + "where pdt.id_tipo_solicitud=" + id_tipo_solicitud + " "
                        + "order by t.nombre";
            }
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] razon_titularidad = lista_drive.get(i).split("♣");
                this.lst_tipoascenso.add(new SelectItem(Long.parseLong(razon_titularidad[0]), razon_titularidad[1]));
            }

            this.lst_tipo_solicitud_rechazado = this.lst_tipo_solicitud;

            this.lst_estado_solicitud_rechazado = new ArrayList<>();
            cadenasql = "select distinct swh.id_estado_solicitud, esc.nombre "
                    + "from solicitud_workflow_historial swh "
                    + "left join estado_solicitud_comeval esc on (swh.id_tipo_solicitud=esc.id_tipo_solicitud and swh.id_estado_solicitud=esc.id_estado_solicitud) "
                    + "where swh.id_solicitud=" + id_comeval_promocion_docente + " and swh.id_estado_solicitud < " + id_estado_solicitud;
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
                    + "t.id_comeval_promocion_docente, "
                    + "t.personal, "
                    + "t.tipoascenso, "
                    + "t.puesto, "
                    + "coalesce(t.fecha_promueve, now()) fecha_promueve, "
                    + "coalesce(t.nota_ref_comeval, '-') nota_ref_comeval, "
                    + "coalesce(t.fecha_nota_ref_comeval, now()) fecha_nota_ref_comeval, "
                    + "t.fecha_ingreso, "
                    + "t.id_estado_solicitud, "
                    + "t.id_tipo_solicitud, "
                    + "t.rechazado, "
                    + "t.revision_comeval, "
                    + "t.revision_secretario_academico, "
                    + "coalesce(t.id_estado_solicitud_rechazado, 1) id_estado_solicitud_rechazado, "
                    + "coalesce(t.id_tipo_solicitud_rechazado, " + id_tipo_solicitud + ") id_tipo_solicitud_rechazado "
                    + "from "
                    + "comeval_promocion_docente t "
                    + "where "
                    + "t.id_comeval_promocion_docente=" + id_comeval_promocion_docente;
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] col = lista_drive.get(i).split("♣");
                this.id_comeval_promocion_docente = Long.parseLong(col[0]);
                this.personal = col[1];
                this.codigo_docente = col[1];
                this.datos_docente();
                this.tipoascenso = Long.parseLong(col[2]);
                this.puesto = col[3];
                this.fecha_promueve = dateFormat.parse(col[4]);
                this.nota_ref_comeval = col[5];
                this.fecha_nota_ref_comeval = dateFormat.parse(col[6]);
                this.fecha_ingreso = dateFormat.parse(col[7]);
                this.id_estado_solicitud = Long.parseLong(col[8]);
                this.id_tipo_solicitud = Long.parseLong(col[9]);
                this.rechazado = Long.parseLong(col[10]);
                if (this.rechazado == Long.parseLong("0")) {
                    this.rechazado_form = false;
                } else {
                    this.rechazado_form = true;
                }
                this.revision_comeval = Long.parseLong(col[11]);
                if (this.revision_comeval == Long.parseLong("0")) {
                    this.revision_comeval_form = false;
                } else {
                    this.revision_comeval_form = true;
                }
                this.revision_secretario_academico = Long.parseLong(col[12]);
                if (this.revision_secretario_academico == Long.parseLong("0")) {
                    this.revision_secretario_academico_form = false;
                } else {
                    this.revision_secretario_academico_form = true;
                }
                this.id_estado_solicitud_rechazado = Long.parseLong(col[13]);
                this.id_tipo_solicitud_rechazado = Long.parseLong(col[14]);
            }

            this.lst_archivo_rest = new ArrayList<>();
            File archivo_rest = File.createTempFile("archivo_temporal", "tmp");
            archivo_rest.deleteOnExit();

            cadenasql = "select "
                    + "t.id_archivo, "
                    + "t.nombre_archivo, "
                    + "t.nombre_archivo_real "
                    + "from "
                    + "comeval_promocion_docente_archivos t "
                    + "where "
                    + "t.id_comeval_promocion_docente=" + this.id_comeval_promocion_docente + " "
                    + "order by "
                    + "t.id_archivo";
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] col = lista_drive.get(i).split("♣");
                lista_archivos archivo_adjunto = new lista_archivos(Long.parseLong(col[0]), col[1], col[2], archivo_rest);
                this.lst_archivo_rest.add(archivo_adjunto);
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
                    + "t.id_solicitud=" + this.id_comeval_promocion_docente + " and "
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

            this.lst_tipo_adjunto = new ArrayList<>();
            if (this.id_estado_solicitud.equals(Long.parseLong("1"))) {
                this.dependencia_observaciones = "DOCENTE";
                this.lst_tipo_adjunto.add(new SelectItem("exp", "Expediente Docente"));
                this.tipo_adjunto = "exp";
                this.calFechaPromueve = true;
            }
            if (this.id_estado_solicitud.equals(Long.parseLong("2"))) {
                this.dependencia_observaciones = "COMEVAL";
                this.lst_tipo_adjunto.add(new SelectItem("exp", "Expediente Docente"));
                this.lst_tipo_adjunto.add(new SelectItem("hrl", "Hoja Relación Laboral"));
                this.lst_tipo_adjunto.add(new SelectItem("cpd", "Cuadro Promoción Docente"));
                this.tipo_adjunto = "hrl";
                this.calFechaPromueve = false;
            }
            if (this.id_estado_solicitud.equals(Long.parseLong("3"))) {
                this.dependencia_observaciones = "SECRETARIO ACADÉMICO";
                this.lst_tipo_adjunto.add(new SelectItem("exp", "Expediente Docente"));
                this.lst_tipo_adjunto.add(new SelectItem("hrl", "Hoja Relación Laboral"));
                this.lst_tipo_adjunto.add(new SelectItem("cpd", "Cuadro Promoción Docente"));
                this.tipo_adjunto = "cpd";
            }
            if (this.id_estado_solicitud.equals(Long.parseLong("4"))) {
                this.dependencia_observaciones = "COMEVAL";
                this.lst_tipo_adjunto.add(new SelectItem("exp", "Expediente Docente"));
                this.lst_tipo_adjunto.add(new SelectItem("hrl", "Hoja Relación Laboral"));
                this.lst_tipo_adjunto.add(new SelectItem("cpd", "Cuadro Promoción Docente"));
                this.tipo_adjunto = "hrl";
                this.calFechaPromueve = false;
            }
            if (this.id_estado_solicitud.equals(Long.parseLong("5"))) {
                this.dependencia_observaciones = "SECRETARIO ACADÉMICO";
                this.lst_tipo_adjunto.add(new SelectItem("exp", "Expediente Docente"));
                this.lst_tipo_adjunto.add(new SelectItem("hrl", "Hoja Relación Laboral"));
                this.lst_tipo_adjunto.add(new SelectItem("cpd", "Cuadro Promoción Docente"));
                this.tipo_adjunto = "cpd";
                this.calFechaPromueve = false;
            }

            if (this.id_estado_solicitud == Long.parseLong("1")) {
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.txtTitularidadActual = true;
                this.cbxRazonTitularidad = false;
                this.cbxTipoTitularidad = false;
                this.calFechaPromueve = true;
                this.txtNotaRefComeval = true;
                this.calFechaNotaRefComeval = true;
                this.fupExcel = false;
                this.chxRechazado = true;
                this.chxRevisionComeval = true;
                this.chxRevisionSecretarioAcademico = true;
                this.cbxTipoSolicitudRechazado = true;
                this.cbxEstadoSolicitudRechazado = true;
                this.btnEliminarArchivo = false;
                this.btnAgregarObservacion = false;
                this.btnEliminarObservacion = false;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("2") || this.id_estado_solicitud == Long.parseLong("3")) {
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.txtTitularidadActual = true;
                this.cbxRazonTitularidad = false;
                this.cbxTipoTitularidad = false;
                this.calFechaPromueve = false;
                this.txtNotaRefComeval = false;
                this.calFechaNotaRefComeval = false;
                this.fupExcel = false;
                this.chxRechazado = true;
                this.chxRevisionComeval = true;
                this.chxRevisionSecretarioAcademico = true;
                this.cbxTipoSolicitudRechazado = true;
                this.cbxEstadoSolicitudRechazado = true;
                this.btnEliminarArchivo = false;
                this.btnAgregarObservacion = false;
                this.btnEliminarObservacion = false;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("4")) {
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.txtTitularidadActual = true;
                this.cbxRazonTitularidad = true;
                this.cbxTipoTitularidad = true;
                this.calFechaPromueve = false;
                this.txtNotaRefComeval = false;
                this.calFechaNotaRefComeval = false;
                this.fupExcel = false;
                this.chxRechazado = false;
                this.chxRevisionComeval = false;
                this.chxRevisionSecretarioAcademico = true;
                this.cbxTipoSolicitudRechazado = true;
                if(this.rechazado_form) {
                    this.cbxEstadoSolicitudRechazado = false;
                } else {
                    this.cbxEstadoSolicitudRechazado = true;
                }
                this.btnEliminarArchivo = false;
                this.btnAgregarObservacion = false;
                this.btnEliminarObservacion = false;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("5")) {
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.txtTitularidadActual = true;
                this.cbxRazonTitularidad = true;
                this.cbxTipoTitularidad = true;
                this.calFechaPromueve = false;
                this.txtNotaRefComeval = false;
                this.calFechaNotaRefComeval = false;
                this.fupExcel = false;
                this.chxRechazado = false;
                this.chxRevisionComeval = true;
                this.chxRevisionSecretarioAcademico = false;
                this.cbxTipoSolicitudRechazado = true;
                if(this.rechazado_form) {
                    this.cbxEstadoSolicitudRechazado = false;
                } else {
                    this.cbxEstadoSolicitudRechazado = true;
                }
                this.btnEliminarArchivo = false;
                this.btnAgregarObservacion = false;
                this.btnEliminarObservacion = false;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }

            PrimeFaces.current().executeScript("PF('ComevalPromocionDocenteDialogVar').show();");
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: mostrar_modificar_promocion_docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void aceptar() {
        try {
            switch (this.opcion) {
                case "CREAR": {
                    this.Crear_Promocion_Docente();
                    break;
                }
                case "MODIFICAR": {
                    this.Modificar_Promocion_Docente();
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: aceptar ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void Crear_Promocion_Docente() {
        try {
            if (!this.personal.equals("0")) {
                if (this.lst_puesto.size() > 0) {
                    if (this.rechazado_form) {
                        this.rechazado = Long.parseLong("1");
                    } else {
                        this.rechazado = Long.parseLong("0");
                    }

                    if (this.revision_comeval_form) {
                        this.revision_comeval = Long.parseLong("1");
                    } else {
                        this.revision_comeval = Long.parseLong("0");
                    }

                    if (this.revision_secretario_academico_form) {
                        this.revision_secretario_academico = Long.parseLong("1");
                    } else {
                        this.revision_secretario_academico = Long.parseLong("0");
                    }

                    List<entidad.Comeval_Promocion_Docente_Archivos> lst_archivos = new ArrayList<>();
                    for (Integer i = 0; i < this.lst_archivo_rest.size(); i++) {
                        servicio.cliente.Cliente_RestHeart_API cliente_restheart_api = new servicio.cliente.Cliente_RestHeart_API("admin", "changeit");
                        cliente_restheart_api.cargar_archivo(this.lst_archivo_rest.get(i).getArchivo(), this.lst_archivo_rest.get(i).getNombre_archivo());

                        entidad.Comeval_Promocion_Docente_Archivos comeval_promocion_docente_archivos = new entidad.Comeval_Promocion_Docente_Archivos();
                        comeval_promocion_docente_archivos.setId_comeval_promocion_docente(this.id_comeval_promocion_docente);
                        comeval_promocion_docente_archivos.setId_archivo(Long.parseLong("0"));
                        comeval_promocion_docente_archivos.setNombre_archivo(this.lst_archivo_rest.get(i).getNombre_archivo());
                        comeval_promocion_docente_archivos.setNombre_archivo_real(this.lst_archivo_rest.get(i).getNombre_archivo_real());
                        lst_archivos.add(comeval_promocion_docente_archivos);
                    }

                    entidad.Comeval_Acta_Solicitud comeval_acta_solicitud = null;

                    List<entidad.Comeval_Solicitud_Observacion> lst_observaciones_rest = new ArrayList<>();
                    for (Integer i = 0; i < this.lst_observaciones.size(); i++) {
                        entidad.Comeval_Solicitud_Observacion observacion_temp = new entidad.Comeval_Solicitud_Observacion();
                        observacion_temp.setId_solicitud(this.id_comeval_promocion_docente);
                        observacion_temp.setId_tipo_solicitud(this.id_tipo_solicitud);
                        observacion_temp.setId_observacion(this.lst_observaciones.get(i).getId_observacion());
                        observacion_temp.setDependencia(this.lst_observaciones.get(i).getDependencia());
                        observacion_temp.setFecha_hora(this.lst_observaciones.get(i).getFecha_hora());
                        observacion_temp.setObservacion(this.lst_observaciones.get(i).getObservacion());
                        lst_observaciones_rest.add(observacion_temp);
                    }

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    entidad.Comeval_Promocion_Docente comeval_promocion_docente = new entidad.Comeval_Promocion_Docente(
                            this.id_comeval_promocion_docente,
                            this.personal,
                            this.tipoascenso,
                            this.puesto,
                            dateFormat.format(this.fecha_promueve),
                            this.nota_ref_comeval.trim(),
                            dateFormat.format(this.fecha_nota_ref_comeval),
                            this.usuario.getUsuario(),
                            dateFormat.format(new Date()),
                            this.id_estado_solicitud,
                            this.id_tipo_solicitud,
                            this.rechazado,
                            this.id_estado_solicitud_rechazado,
                            this.id_tipo_solicitud_rechazado,
                            this.revision_comeval,
                            this.revision_secretario_academico,
                            lst_archivos,
                            comeval_acta_solicitud,
                            lst_observaciones_rest);

                    List<entidad.Comeval_Promocion_Docente> lst_comeval_promocion_docente = new ArrayList<>();
                    lst_comeval_promocion_docente.add(comeval_promocion_docente);

                    servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                    String resultado = cliente_api_comeval_rest.promocion_docente_ingresar(lst_comeval_promocion_docente);

                    PrimeFaces.current().executeScript("PF('ComevalPromocionDocenteDialogVar').hide();");

                    String[] mensaje = resultado.split(",");
                    if (mensaje[0].equals("0")) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", mensaje[1]));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje del sistema...", mensaje[1]));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje del sistema...", "Debe seleccionar la nueva titularidad del docente."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje del sistema...", "Debe ingresar un docente registrado en el sistema."));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Crear_Promocion_Docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void Modificar_Promocion_Docente() {
        try {
            if (!this.personal.equals("0")) {
                if (this.lst_puesto.size() > 0) {
                    if (this.rechazado_form) {
                        this.rechazado = Long.parseLong("1");
                    } else {
                        this.rechazado = Long.parseLong("0");
                    }

                    if (this.revision_comeval_form) {
                        this.revision_comeval = Long.parseLong("1");
                    } else {
                        this.revision_comeval = Long.parseLong("0");
                    }

                    if (this.revision_secretario_academico_form) {
                        this.revision_secretario_academico = Long.parseLong("1");
                    } else {
                        this.revision_secretario_academico = Long.parseLong("0");
                    }

                    List<entidad.Comeval_Promocion_Docente_Archivos> lst_archivos = new ArrayList<>();
                    for (Integer i = 0; i < this.lst_archivo_rest.size(); i++) {
                        servicio.cliente.Cliente_RestHeart_API_1 cliente_restheart_api_1 = new servicio.cliente.Cliente_RestHeart_API_1("admin", "changeit");
                        String resultado = cliente_restheart_api_1.buscar_adjunto(this.lst_archivo_rest.get(i).getNombre_archivo());

                        if (resultado.equals("NO_ENCONTRADO")) {
                            servicio.cliente.Cliente_RestHeart_API cliente_restheart_api = new servicio.cliente.Cliente_RestHeart_API("admin", "changeit");
                            cliente_restheart_api.cargar_archivo(this.lst_archivo_rest.get(i).getArchivo(), this.lst_archivo_rest.get(i).getNombre_archivo());
                        }

                        entidad.Comeval_Promocion_Docente_Archivos comeval_promocion_docente_archivos = new entidad.Comeval_Promocion_Docente_Archivos();
                        comeval_promocion_docente_archivos.setId_comeval_promocion_docente(this.id_comeval_promocion_docente);
                        comeval_promocion_docente_archivos.setId_archivo(Long.parseLong("0"));
                        comeval_promocion_docente_archivos.setNombre_archivo(this.lst_archivo_rest.get(i).getNombre_archivo());
                        comeval_promocion_docente_archivos.setNombre_archivo_real(this.lst_archivo_rest.get(i).getNombre_archivo_real());
                        lst_archivos.add(comeval_promocion_docente_archivos);
                    }

                    entidad.Comeval_Acta_Solicitud comeval_acta_solicitud = null;

                    List<entidad.Comeval_Solicitud_Observacion> lst_observaciones_rest = new ArrayList<>();
                    for (Integer i = 0; i < this.lst_observaciones.size(); i++) {
                        entidad.Comeval_Solicitud_Observacion observacion_temp = new entidad.Comeval_Solicitud_Observacion();
                        observacion_temp.setId_solicitud(this.id_comeval_promocion_docente);
                        observacion_temp.setId_tipo_solicitud(this.id_tipo_solicitud);
                        observacion_temp.setId_observacion(this.lst_observaciones.get(i).getId_observacion());
                        observacion_temp.setDependencia(this.lst_observaciones.get(i).getDependencia());
                        observacion_temp.setFecha_hora(this.lst_observaciones.get(i).getFecha_hora());
                        observacion_temp.setObservacion(this.lst_observaciones.get(i).getObservacion());
                        lst_observaciones_rest.add(observacion_temp);
                    }

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String fecha_promueve_temp;
                    String fecha_nota_ref_comeval_temp;
                    if (this.id_estado_solicitud == Long.parseLong("1")) {
                        fecha_promueve_temp = "-";
                        fecha_nota_ref_comeval_temp = "-";
                    } else {
                        fecha_promueve_temp = dateFormat.format(this.fecha_promueve);
                        fecha_nota_ref_comeval_temp = dateFormat.format(this.fecha_nota_ref_comeval);
                    }

                    entidad.Comeval_Promocion_Docente comeval_promocion_docente = new entidad.Comeval_Promocion_Docente(
                            this.id_comeval_promocion_docente,
                            this.personal,
                            this.tipoascenso,
                            this.puesto,
                            fecha_promueve_temp,
                            this.nota_ref_comeval.trim(),
                            fecha_nota_ref_comeval_temp,
                            this.usuario.getUsuario(),
                            dateFormat.format(new Date()),
                            this.id_estado_solicitud,
                            this.id_tipo_solicitud,
                            this.rechazado,
                            this.id_estado_solicitud_rechazado,
                            this.id_tipo_solicitud_rechazado,
                            this.revision_comeval,
                            this.revision_secretario_academico,
                            lst_archivos,
                            comeval_acta_solicitud,
                            lst_observaciones_rest);

                    List<entidad.Comeval_Promocion_Docente> lst_comeval_promocion_docente = new ArrayList<>();
                    lst_comeval_promocion_docente.add(comeval_promocion_docente);

                    servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                    String resultado = cliente_api_comeval_rest.promocion_docente_modificar(lst_comeval_promocion_docente);

                    PrimeFaces.current().executeScript("PF('ComevalPromocionDocenteDialogVar').hide();");

                    String[] mensaje = resultado.split(",");
                    if (mensaje[0].equals("0")) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", mensaje[1]));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje del sistema...", mensaje[1]));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje del sistema...", "Debe seleccionar la nueva titularidad del docente."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje del sistema...", "Debe ingresar un docente registrado en el sistema."));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Crear_Promocion_Docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void datos_docente() {
        try {
            this.personal = "0";
            this.nombre_docente = "-";
            this.titularidad_actual = "-";
            this.fupExcel = false;
            this.btnEliminarArchivo = false;
            this.btnAgregarObservacion = false;
            this.btnEliminarObservacion = false;

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

            String puesto_actual = "0";
            cadenasql = "select p.puesto, p.nombre from puesto p where p.puesto in (select t.puesto from titularidad t where t.personal='" + this.codigo_docente + "')";
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] puestos = lista_drive.get(i).split("♣");
                puesto_actual = puestos[0];
                this.titularidad_actual = puestos[1];
            }

            Long id_puesto_orden = Long.parseLong("0");
            cadenasql = "select ppc.id_puesto_orden from puesto_plaza_comeval ppc where ppc.puesto='" + puesto_actual + "'";
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] puesto_plaza = lista_drive.get(i).split("♣");
                id_puesto_orden = Long.parseLong(puesto_plaza[0]);
            }

            this.lst_puesto = new ArrayList<>();
            cadenasql = "select p.puesto, p.nombre "
                    + "from puesto p "
                    + "where p.activo=true and p.nombre ilike 'TITULAR%' and p.puesto in (select ppc.puesto from puesto_plaza_comeval ppc where ppc.id_puesto_orden > " + id_puesto_orden + ") "
                    + "order by p.puesto";
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] titularidad = lista_drive.get(i).split("♣");
                this.lst_puesto.add(new SelectItem(titularidad[0], titularidad[1]));
            }

            if (this.personal.equals("0")) {
                this.titularidad_actual = "-";
                this.lst_puesto = new ArrayList<>();
                this.puesto = "0";
                this.fupExcel = true;
                this.btnEliminarArchivo = true;
                this.btnAgregarObservacion = true;
                this.btnEliminarObservacion = true;
                this.lst_archivo_rest = new ArrayList();
                this.lst_observaciones = new ArrayList();
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: datos_docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            Boolean validado = true;
            String mensaje_agregar = "";
            String prefijo_adjunto = "pd_" + this.tipo_adjunto + "_";
            for (Integer i = 0; i < this.lst_archivo_rest.size(); i++) {
                if (this.lst_archivo_rest.get(i).getNombre_archivo().contains(prefijo_adjunto)) {
                    validado = false;
                    mensaje_agregar = "Solo puede agregar un tipo de adjunto: " + this.tipo_adjunto;
                }
            }

            if (validado) {
                File archivo_rest = File.createTempFile("archivo_temporal", "tmp");
                archivo_rest.deleteOnExit();
                FileUtils.copyInputStreamToFile(event.getFile().getInputStream(), archivo_rest);

                Date fecha_actual = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                this.lst_archivo_rest.add(new lista_archivos(Long.parseLong("1"), prefijo_adjunto + this.personal + "_" + dateFormat.format(fecha_actual), event.getFile().getFileName(), archivo_rest));

                for (Integer i = 0; i < this.lst_archivo_rest.size(); i++) {
                    Integer j = i + 1;
                    this.lst_archivo_rest.get(i).setId_archivo(Long.parseLong(j.toString()));
                }

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", "Adjunto " + event.getFile().getFileName() + " cagado."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje del sistema...", mensaje_agregar));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: handleFileUpload ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void eliminar_archivo() {
        try {
            if (this.sel_archivo_rest != null) {
                Boolean validado = false;
                for (Integer i = 0; i < this.lst_tipo_adjunto.size(); i++) {
                    System.out.println(this.sel_archivo_rest.getNombre_archivo() + " <---> " + this.lst_tipo_adjunto.get(i).getValue().toString());
                    if (this.sel_archivo_rest.getNombre_archivo().contains(this.lst_tipo_adjunto.get(i).getValue().toString())) {
                        validado = true;
                    }
                }

                if (validado) {
                    servicio.cliente.Cliente_RestHeart_API_1 cliente_restheart_api_1 = new servicio.cliente.Cliente_RestHeart_API_1("admin", "changeit");
                    cliente_restheart_api_1.eliminar_adjunto(this.sel_archivo_rest.getNombre_archivo());
                    this.lst_archivo_rest.remove(this.sel_archivo_rest);

                    for (Integer i = 0; i < this.lst_archivo_rest.size(); i++) {
                        Integer j = i + 1;
                        this.lst_archivo_rest.get(i).setId_archivo(Long.parseLong(j.toString()));
                    }

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", "Archivo eliminado."));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje del sistema...", "No puede eliminar el archivo seleccionado."));
                }
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: eliminar_archivo ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void mostrar_adjunto() {
        try {
            if (this.sel_archivo_rest != null) {
                servicio.cliente.Cliente_RestHeart_API_1 cliente_restheart_api_1 = new servicio.cliente.Cliente_RestHeart_API_1("admin", "changeit");
                InputStream inputstream = cliente_restheart_api_1.descargar_adjunto_binary(this.sel_archivo_rest.getNombre_archivo());
                this.file = DefaultStreamedContent.builder()
                        .name("Copy_" + this.sel_archivo_rest.getNombre_archivo_real())
                        .contentType("Word/docx")
                        .stream(() -> inputstream)
                        .build();
                PrimeFaces.current().executeScript("PF('ArchivoDialogVar').show();");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje del sistema...", "Debe seleccionar un registro de la tabla."));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: mostrar_adjunto ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void agregar_observacion() {
        try {
            this.observacion = "";
            PrimeFaces.current().executeScript("PF('PromocionObservacionesDialogVar').show();");
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
            lista_observaciones observacion_dialog = new lista_observaciones();
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

            PrimeFaces.current().executeScript("PF('PromocionObservacionesDialogVar').hide();");
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: agregar_observacion_dialog ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void cambiar_tipo_archivo(SelectEvent event) {
        try {
            this.tipo_adjunto = event.getObject().toString();
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: cambiar_tipo_archivo ERROR: " + ex.toString());
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

    public Long getId_comeval_promocion_docente() {
        return id_comeval_promocion_docente;
    }

    public void setId_comeval_promocion_docente(Long id_comeval_promocion_docente) {
        this.id_comeval_promocion_docente = id_comeval_promocion_docente;
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

    public String getTitularidad_actual() {
        return titularidad_actual;
    }

    public void setTitularidad_actual(String titularidad_actual) {
        this.titularidad_actual = titularidad_actual;
    }

    public Long getTipoascenso() {
        return tipoascenso;
    }

    public void setTipoascenso(Long tipoascenso) {
        this.tipoascenso = tipoascenso;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Date getFecha_promueve() {
        return fecha_promueve;
    }

    public void setFecha_promueve(Date fecha_promueve) {
        this.fecha_promueve = fecha_promueve;
    }

    public String getNota_ref_comeval() {
        return nota_ref_comeval;
    }

    public void setNota_ref_comeval(String nota_ref_comeval) {
        this.nota_ref_comeval = nota_ref_comeval;
    }

    public Date getFecha_nota_ref_comeval() {
        return fecha_nota_ref_comeval;
    }

    public void setFecha_nota_ref_comeval(Date fecha_nota_ref_comeval) {
        this.fecha_nota_ref_comeval = fecha_nota_ref_comeval;
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

    public Long getRevision_comeval() {
        return revision_comeval;
    }

    public void setRevision_comeval(Long revision_comeval) {
        this.revision_comeval = revision_comeval;
    }

    public Boolean getRevision_comeval_form() {
        return revision_comeval_form;
    }

    public void setRevision_comeval_form(Boolean revision_comeval_form) {
        this.revision_comeval_form = revision_comeval_form;
    }

    public Long getRevision_secretario_academico() {
        return revision_secretario_academico;
    }

    public void setRevision_secretario_academico(Long revision_secretario_academico) {
        this.revision_secretario_academico = revision_secretario_academico;
    }

    public Boolean getRevision_secretario_academico_form() {
        return revision_secretario_academico_form;
    }

    public void setRevision_secretario_academico_form(Boolean revision_secretario_academico_form) {
        this.revision_secretario_academico_form = revision_secretario_academico_form;
    }

    public List<SelectItem> getLst_tipoascenso() {
        return lst_tipoascenso;
    }

    public void setLst_tipoascenso(List<SelectItem> lst_tipoascenso) {
        this.lst_tipoascenso = lst_tipoascenso;
    }

    public List<SelectItem> getLst_puesto() {
        return lst_puesto;
    }

    public void setLst_puesto(List<SelectItem> lst_puesto) {
        this.lst_puesto = lst_puesto;
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

    public List<lista_archivos> getLst_archivo_rest() {
        return lst_archivo_rest;
    }

    public void setLst_archivo_rest(List<lista_archivos> lst_archivo_rest) {
        this.lst_archivo_rest = lst_archivo_rest;
    }

    public lista_archivos getSel_archivo_rest() {
        return sel_archivo_rest;
    }

    public void setSel_archivo_rest(lista_archivos sel_archivo_rest) {
        this.sel_archivo_rest = sel_archivo_rest;
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

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public String getTipo_adjunto() {
        return tipo_adjunto;
    }

    public void setTipo_adjunto(String tipo_adjunto) {
        this.tipo_adjunto = tipo_adjunto;
    }

    public List<SelectItem> getLst_tipo_adjunto() {
        return lst_tipo_adjunto;
    }

    public void setLst_tipo_adjunto(List<SelectItem> lst_tipo_adjunto) {
        this.lst_tipo_adjunto = lst_tipo_adjunto;
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

    public Boolean getTxtTitularidadActual() {
        return txtTitularidadActual;
    }

    public void setTxtTitularidadActual(Boolean txtTitularidadActual) {
        this.txtTitularidadActual = txtTitularidadActual;
    }

    public Boolean getCbxRazonTitularidad() {
        return cbxRazonTitularidad;
    }

    public void setCbxRazonTitularidad(Boolean cbxRazonTitularidad) {
        this.cbxRazonTitularidad = cbxRazonTitularidad;
    }

    public Boolean getCbxTipoTitularidad() {
        return cbxTipoTitularidad;
    }

    public void setCbxTipoTitularidad(Boolean cbxTipoTitularidad) {
        this.cbxTipoTitularidad = cbxTipoTitularidad;
    }

    public Boolean getCalFechaPromueve() {
        return calFechaPromueve;
    }

    public void setCalFechaPromueve(Boolean calFechaPromueve) {
        this.calFechaPromueve = calFechaPromueve;
    }

    public Boolean getTxtNotaRefComeval() {
        return txtNotaRefComeval;
    }

    public void setTxtNotaRefComeval(Boolean txtNotaRefComeval) {
        this.txtNotaRefComeval = txtNotaRefComeval;
    }

    public Boolean getCalFechaNotaRefComeval() {
        return calFechaNotaRefComeval;
    }

    public void setCalFechaNotaRefComeval(Boolean calFechaNotaRefComeval) {
        this.calFechaNotaRefComeval = calFechaNotaRefComeval;
    }

    public Boolean getFupExcel() {
        return fupExcel;
    }

    public void setFupExcel(Boolean fupExcel) {
        this.fupExcel = fupExcel;
    }

    public Boolean getChxRechazado() {
        return chxRechazado;
    }

    public void setChxRechazado(Boolean chxRechazado) {
        this.chxRechazado = chxRechazado;
    }

    public Boolean getChxRevisionComeval() {
        return chxRevisionComeval;
    }

    public void setChxRevisionComeval(Boolean chxRevisionComeval) {
        this.chxRevisionComeval = chxRevisionComeval;
    }

    public Boolean getChxRevisionSecretarioAcademico() {
        return chxRevisionSecretarioAcademico;
    }

    public void setChxRevisionSecretarioAcademico(Boolean chxRevisionSecretarioAcademico) {
        this.chxRevisionSecretarioAcademico = chxRevisionSecretarioAcademico;
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

    public Boolean getBtnEliminarArchivo() {
        return btnEliminarArchivo;
    }

    public void setBtnEliminarArchivo(Boolean btnEliminarArchivo) {
        this.btnEliminarArchivo = btnEliminarArchivo;
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
