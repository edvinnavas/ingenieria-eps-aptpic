package beans.Comeval_Notas_Evaluacion_Docente;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.InputStream;
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
import org.apache.commons.io.FileUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;

@Named(value = "Bean_Comeval_Notas_Evaluacion_Docente")
@ViewScoped
public class Bean_Comeval_Notas_Evaluacion_Docente implements Serializable {

    private static final long serialVersionUID = 1L;

    private entidad.Usuario usuario;

    private Long id_comeval_notas_evaluacion_docente;
    private String nota_ref;
    private Date fecha_nota_ref;
    private Long anio_notas;
    private String path_archivo;
    private Date fecha_ingreso;
    private Long id_estado_solicitud;
    private Long id_tipo_solicitud;
    private Long rechazado;
    private Long id_estado_solicitud_rechazado;
    private Long id_tipo_solicitud_rechazado;

    private List<SelectItem> lst_estado_solicitud;
    private List<SelectItem> lst_tipo_solicitud;

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
    private Boolean txtNotaRef;
    private Boolean calFechaNotaRef;
    private Boolean spnAnioNotas;
    private Boolean txtPathArchivo;
    private Boolean fupExcel;
    private Boolean btnEliminarArchivo;
    private Boolean btnAgregarObservacion;
    private Boolean btnEliminarObservacion;
    private Boolean btnAceptar;
    private Boolean btnCancelar;

    public Bean_Comeval_Notas_Evaluacion_Docente() {
        try {
            this.lst_estado_solicitud = new ArrayList<>();
            this.lst_tipo_solicitud = new ArrayList<>();
            this.lst_archivo_rest = new ArrayList<>();
            this.lst_observaciones = new ArrayList<>();
            this.lst_tipo_adjunto = new ArrayList<>();
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Bean_Comeval_Notas_Evaluacion_Docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void mostrar_crear_notas_evaluacion_docente(entidad.Usuario usuario, Long id_estado_solicitud, Long id_tipo_solicitud) {
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

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            this.id_comeval_notas_evaluacion_docente = Long.parseLong("1");
            this.nota_ref = "-";
            this.fecha_nota_ref = new Date();
            Integer l_anio = Calendar.getInstance().get(Calendar.YEAR);
            this.anio_notas = Long.parseLong(l_anio.toString());
            this.path_archivo = "";
            this.fecha_ingreso = new Date();
            this.rechazado = Long.parseLong("0");
            this.id_estado_solicitud_rechazado = Long.parseLong("0");
            this.id_tipo_solicitud_rechazado = Long.parseLong("0");
            
            this.lst_archivo_rest = new ArrayList<>();

            this.dependencia_observaciones = "SECRETARIO ACADÉMICO";
            this.lst_tipo_adjunto.add(new SelectItem("eval", "Notas Evaluación Docente"));
            this.tipo_adjunto = "eval";
            this.txtNotaRef = true;
            this.calFechaNotaRef = true;
            
            this.lst_archivo_rest = new ArrayList<>();
            this.lst_observaciones = new ArrayList<>();
            
            this.cbxTipoSolicitud = true;
            this.cbxEstadoSolicitud = true;
            this.txtCodigoDocente = false;
            this.txtNombreDocente = true;
            this.txtNotaRef = false;
            this.calFechaNotaRef = false;
            this.spnAnioNotas = false;
            this.txtPathArchivo = true;
            this.fupExcel = false;
            this.btnEliminarArchivo = false;
            this.btnAgregarObservacion = false;
            this.btnEliminarObservacion = false;
            this.btnAceptar = false;
            this.btnCancelar = false;

            PrimeFaces.current().executeScript("PF('ComevalCargaEvalComevalDialogVar').show();");
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: mostrar_crear_notas_evaluacion_docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void mostrar_modificar_notas_evaluacion_docente(entidad.Usuario usuario, Long id_comeval_notas_evaluacion_docente, Long id_estado_solicitud, Long id_tipo_solicitud) {
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

            cadenasql = "select "
                    + "t.id_comeval_notas_evaluacion_docente, "
                    + "t.nota_ref, "
                    + "t.fecha_nota_ref, "
                    + "t.anio_notas, "
                    + "t.path_archivo, "
                    + "t.fecha_ingreso, "
                    + "t.id_estado_solicitud, "
                    + "t.id_tipo_solicitud, "
                    + "t.rechazado, "
                    + "t.id_estado_solicitud_rechazado, "
                    + "t.id_tipo_solicitud_rechazado "
                    + "from "
                    + "comeval_notas_evaluacion_docente t "
                    + "where "
                    + "t.id_comeval_notas_evaluacion_docente=" + id_comeval_notas_evaluacion_docente;
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] col = lista_drive.get(i).split("♣");
                this.id_comeval_notas_evaluacion_docente = Long.parseLong(col[0]);
                this.nota_ref = col[1];
                this.fecha_nota_ref = dateFormat.parse(col[2]);
                this.anio_notas = Long.parseLong(col[3]);
                this.path_archivo = col[4];
                this.fecha_ingreso = dateFormat.parse(col[5]);
                this.id_estado_solicitud = Long.parseLong(col[6]);
                this.id_tipo_solicitud = Long.parseLong(col[7]);
                this.rechazado = Long.parseLong(col[8]);
                this.id_estado_solicitud_rechazado = Long.parseLong("0");
                this.id_tipo_solicitud_rechazado = Long.parseLong("0");
            }
            
            this.lst_archivo_rest = new ArrayList<>();
            File archivo_rest = File.createTempFile("archivo_temporal", "tmp");
            archivo_rest.deleteOnExit();
            
            lista_archivos archivo_adjunto = new lista_archivos(Long.parseLong("1"), this.path_archivo, "Notas_Evaluacion_Docente_" + this.anio_notas, archivo_rest);
            this.lst_archivo_rest.add(archivo_adjunto);
            
            this.lst_observaciones = new ArrayList<>();
            cadenasql = "select "
                    + "t.id_observacion, "
                    + "t.dependencia, "
                    + "t.fecha_hora, "
                    + "t.observacion "
                    + "from "
                    + "comeval_solicitud_observacion t "
                    + "where "
                    + "t.id_solicitud=" + this.id_comeval_notas_evaluacion_docente + " and "
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
            this.dependencia_observaciones = "SECRETARIO ACADÉMICO";
            this.lst_tipo_adjunto.add(new SelectItem("eval", "Notas Evaluación Docente"));
            this.tipo_adjunto = "eval";
                
            this.cbxTipoSolicitud = true;
            this.cbxEstadoSolicitud = true;
            this.txtCodigoDocente = false;
            this.txtNombreDocente = true;
            this.txtNotaRef = false;
            this.calFechaNotaRef = false;
            this.spnAnioNotas = false;
            this.txtPathArchivo = true;
            this.fupExcel = false;
            this.btnEliminarArchivo = false;
            this.btnAgregarObservacion = false;
            this.btnEliminarObservacion = false;
            this.btnAceptar = false;
            this.btnCancelar = false;

            PrimeFaces.current().executeScript("PF('ComevalCargaEvalComevalDialogVar').show();");
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: mostrar_modificar_notas_evaluacion_docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void aceptar() {
        try {
            switch (this.opcion) {
                case "CREAR": {
                    this.Crear_Notas_Evaluacion_Docente();
                    break;
                }
                case "MODIFICAR": {
                    this.Modificar_Notas_Evaluacion_Docente();
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: aceptar ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void Crear_Notas_Evaluacion_Docente() {
        try {
            if (this.lst_archivo_rest.size() > 0) {
                for (Integer i = 0; i < this.lst_archivo_rest.size(); i++) {
                    servicio.cliente.Cliente_RestHeart_API cliente_restheart_api = new servicio.cliente.Cliente_RestHeart_API("admin", "changeit");
                    cliente_restheart_api.cargar_archivo(this.lst_archivo_rest.get(i).getArchivo(), this.lst_archivo_rest.get(i).getNombre_archivo());
                }

                entidad.Comeval_Acta_Solicitud comeval_acta_solicitud = null;
                
                List<entidad.Comeval_Solicitud_Observacion> lst_observaciones_rest = new ArrayList<>();
                for (Integer i = 0; i < this.lst_observaciones.size(); i++) {
                    entidad.Comeval_Solicitud_Observacion observacion_temp = new entidad.Comeval_Solicitud_Observacion();
                    observacion_temp.setId_solicitud(this.id_comeval_notas_evaluacion_docente);
                    observacion_temp.setId_tipo_solicitud(this.id_tipo_solicitud);
                    observacion_temp.setId_observacion(this.lst_observaciones.get(i).getId_observacion());
                    observacion_temp.setDependencia(this.lst_observaciones.get(i).getDependencia());
                    observacion_temp.setFecha_hora(this.lst_observaciones.get(i).getFecha_hora());
                    observacion_temp.setObservacion(this.lst_observaciones.get(i).getObservacion());
                    lst_observaciones_rest.add(observacion_temp);
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                entidad.Comeval_Notas_Evaluacion_Docente comeval_notas_evaluacion_docente = new entidad.Comeval_Notas_Evaluacion_Docente(
                        this.id_comeval_notas_evaluacion_docente,
                        this.nota_ref,
                        dateFormat.format(this.fecha_nota_ref),
                        this.anio_notas,
                        this.path_archivo,
                        this.usuario.getUsuario(),
                        dateFormat.format(new Date()),
                        this.id_estado_solicitud,
                        this.id_tipo_solicitud,
                        this.rechazado,
                        this.id_estado_solicitud_rechazado,
                        this.id_tipo_solicitud_rechazado,
                        comeval_acta_solicitud,
                        lst_observaciones_rest);

                List<entidad.Comeval_Notas_Evaluacion_Docente> lst_comeval_notas_evaluacion_docente = new ArrayList<>();
                lst_comeval_notas_evaluacion_docente.add(comeval_notas_evaluacion_docente);

                servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                String resultado = cliente_api_comeval_rest.notas_evaluacion_docente_ingresar(lst_comeval_notas_evaluacion_docente);

                PrimeFaces.current().executeScript("PF('ComevalCargaEvalComevalDialogVar').hide();");

                String[] mensaje = resultado.split(",");
                if (mensaje[0].equals("0")) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", mensaje[1]));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", mensaje[1]));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", "Debe adjuntar el archivo de las notas de evaluación COMEVAL."));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Crear_Notas_Evaluacion_Docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void Modificar_Notas_Evaluacion_Docente() {
        try {
            if (this.lst_archivo_rest.size() > 0) {
                for (Integer i = 0; i < this.lst_archivo_rest.size(); i++) {
                    servicio.cliente.Cliente_RestHeart_API_1 cliente_restheart_api_1 = new servicio.cliente.Cliente_RestHeart_API_1("admin", "changeit");
                    String resultado = cliente_restheart_api_1.buscar_adjunto(this.lst_archivo_rest.get(i).getNombre_archivo());

                    if (resultado.equals("NO_ENCONTRADO")) {
                        servicio.cliente.Cliente_RestHeart_API cliente_restheart_api = new servicio.cliente.Cliente_RestHeart_API("admin", "changeit");
                        cliente_restheart_api.cargar_archivo(this.lst_archivo_rest.get(i).getArchivo(), this.lst_archivo_rest.get(i).getNombre_archivo());
                    }
                }

                entidad.Comeval_Acta_Solicitud comeval_acta_solicitud = null;
                
                List<entidad.Comeval_Solicitud_Observacion> lst_observaciones_rest = new ArrayList<>();
                for (Integer i = 0; i < this.lst_observaciones.size(); i++) {
                    entidad.Comeval_Solicitud_Observacion observacion_temp = new entidad.Comeval_Solicitud_Observacion();
                    observacion_temp.setId_solicitud(this.id_comeval_notas_evaluacion_docente);
                    observacion_temp.setId_tipo_solicitud(this.id_tipo_solicitud);
                    observacion_temp.setId_observacion(this.lst_observaciones.get(i).getId_observacion());
                    observacion_temp.setDependencia(this.lst_observaciones.get(i).getDependencia());
                    observacion_temp.setFecha_hora(this.lst_observaciones.get(i).getFecha_hora());
                    observacion_temp.setObservacion(this.lst_observaciones.get(i).getObservacion());
                    lst_observaciones_rest.add(observacion_temp);
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                entidad.Comeval_Notas_Evaluacion_Docente comeval_notas_evaluacion_docente = new entidad.Comeval_Notas_Evaluacion_Docente(
                        this.id_comeval_notas_evaluacion_docente,
                        this.nota_ref,
                        dateFormat.format(this.fecha_nota_ref),
                        this.anio_notas,
                        this.path_archivo,
                        this.usuario.getUsuario(),
                        dateFormat.format(new Date()),
                        this.id_estado_solicitud,
                        this.id_tipo_solicitud,
                        this.rechazado,
                        this.id_estado_solicitud_rechazado,
                        this.id_tipo_solicitud_rechazado,
                        comeval_acta_solicitud,
                        lst_observaciones_rest);

                List<entidad.Comeval_Notas_Evaluacion_Docente> lst_comeval_notas_evaluacion_docente = new ArrayList<>();
                lst_comeval_notas_evaluacion_docente.add(comeval_notas_evaluacion_docente);

                servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                String resultado = cliente_api_comeval_rest.notas_evaluacion_docente_modificar(lst_comeval_notas_evaluacion_docente);

                PrimeFaces.current().executeScript("PF('ComevalCargaEvalComevalDialogVar').hide();");

                String[] mensaje = resultado.split(",");
                if (mensaje[0].equals("0")) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", mensaje[1]));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", mensaje[1]));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", "Debe adjuntar el archivo de las notas de evaluación COMEVAL."));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Modificar_Notas_Evaluacion_Docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            Boolean validado = true;
            String mensaje_agregar = "";
            String prefijo_adjunto = "ned_" + this.tipo_adjunto + "_";
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
                this.lst_archivo_rest.add(new lista_archivos(Long.parseLong("1"), prefijo_adjunto + "_" + dateFormat.format(fecha_actual), event.getFile().getFileName(), archivo_rest));

                this.path_archivo = prefijo_adjunto + "_" + dateFormat.format(fecha_actual);
                
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
                servicio.cliente.Cliente_RestHeart_API_1 cliente_restheart_api_1 = new servicio.cliente.Cliente_RestHeart_API_1("admin", "changeit");
                cliente_restheart_api_1.eliminar_adjunto(this.sel_archivo_rest.getNombre_archivo());
                this.lst_archivo_rest.remove(this.sel_archivo_rest);
                
                for (Integer i = 0; i < this.lst_archivo_rest.size(); i++) {
                    Integer j = i + 1;
                    this.lst_archivo_rest.get(i).setId_archivo(Long.parseLong(j.toString()));
                }
                
                this.path_archivo = "";

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", "Archivo eliminado."));
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

                File archivo_temporal = new File("/home/edvin/comeval/archivo_temporal.pdf");
                archivo_temporal.deleteOnExit();
                FileUtils.copyInputStreamToFile(inputstream, archivo_temporal);

                PrimeFaces.current().executeScript("PF('ArchivoAdjuntoDialogVarA').show();");
                // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", "Archivo descargado."));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: mostrar_adjunto ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }
    
    public void agregar_observacion() {
        try {
            this.observacion = "";
            PrimeFaces.current().executeScript("PF('NotasObservacionesDialogVar').show();");
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

            PrimeFaces.current().executeScript("PF('NotasObservacionesDialogVar').hide();");
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
    
    public entidad.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(entidad.Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId_comeval_notas_evaluacion_docente() {
        return id_comeval_notas_evaluacion_docente;
    }

    public void setId_comeval_notas_evaluacion_docente(Long id_comeval_notas_evaluacion_docente) {
        this.id_comeval_notas_evaluacion_docente = id_comeval_notas_evaluacion_docente;
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

    public Long getAnio_notas() {
        return anio_notas;
    }

    public void setAnio_notas(Long anio_notas) {
        this.anio_notas = anio_notas;
    }

    public String getPath_archivo() {
        return path_archivo;
    }

    public void setPath_archivo(String path_archivo) {
        this.path_archivo = path_archivo;
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

    public Boolean getSpnAnioNotas() {
        return spnAnioNotas;
    }

    public void setSpnAnioNotas(Boolean spnAnioNotas) {
        this.spnAnioNotas = spnAnioNotas;
    }

    public Boolean getFupExcel() {
        return fupExcel;
    }

    public void setFupExcel(Boolean fupExcel) {
        this.fupExcel = fupExcel;
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

    public Boolean getTxtPathArchivo() {
        return txtPathArchivo;
    }

    public void setTxtPathArchivo(Boolean txtPathArchivo) {
        this.txtPathArchivo = txtPathArchivo;
    }
    
}
