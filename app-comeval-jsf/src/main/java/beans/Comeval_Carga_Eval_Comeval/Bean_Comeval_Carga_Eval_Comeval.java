package beans.Comeval_Carga_Eval_Comeval;

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

@Named(value = "Bean_Comeval_Carga_Eval_Comeval")
@ViewScoped
public class Bean_Comeval_Carga_Eval_Comeval implements Serializable {

    private static final long serialVersionUID = 1L;

    private entidad.Usuario usuario;

    private Long id_comeval_carga_eval_comeval;
    private String descripcion_solicitud;
    private String path_archivo;
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

    private List<SelectItem> lst_estado_solicitud;
    private List<SelectItem> lst_tipo_solicitud;

    private String opcion;
    private List<lista_archivos> lst_archivo_rest;
    lista_archivos sel_archivo_rest;

    private Boolean cbxTipoSolicitud;
    private Boolean cbxEstadoSolicitud;
    private Boolean txtCodigoDocente;
    private Boolean txtNombreDocente;
    private Boolean areObservacionDocente;
    private Boolean fupExcel;
    private Boolean chxRechazado;
    private Boolean btnEliminarArchivo;
    private Boolean btnAceptar;
    private Boolean btnCancelar;

    public Bean_Comeval_Carga_Eval_Comeval() {
        try {
            this.lst_estado_solicitud = new ArrayList<>();
            this.lst_tipo_solicitud = new ArrayList<>();
            this.lst_archivo_rest = new ArrayList<>();
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Bean_Comeval_Carga_Eval_Comeval ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void mostrar_crear_carga_eval_comeval(entidad.Usuario usuario, Long id_estado_solicitud, Long id_tipo_solicitud) {
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

            this.lst_archivo_rest = new ArrayList<>();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // Valores iniciales del formulario.
            this.id_comeval_carga_eval_comeval = Long.parseLong("0");
            this.descripcion_solicitud = "-";
            this.path_archivo = "-";
            this.id_solicitud_acta = Long.parseLong("0");
            this.no_acta = "-";
            this.anio_acta = "-";
            this.punto_acta = "-";
            this.inciso_acta = "-";
            this.fecha_acta = dateFormat.parse("1900-01-01 00:00:00");
            this.resolucion_acta = "-";
            this.fecha_ingreso = new Date();
            this.rechazado = Long.parseLong("0");
            this.rechazado_form = false;

            this.cbxTipoSolicitud = true;
            this.cbxEstadoSolicitud = true;
            this.txtCodigoDocente = false;
            this.txtNombreDocente = true;
            this.areObservacionDocente = false;
            this.fupExcel = false;
            this.chxRechazado = true;
            this.btnEliminarArchivo = false;
            this.btnAceptar = false;
            this.btnCancelar = false;

            PrimeFaces.current().executeScript("PF('ComevalCargaEvalComevalDialogVar').show();");
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: mostrar_crear_carga_eval_comeval ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void mostrar_modificar_carga_eval_comeval(entidad.Usuario usuario, Long id_comeval_carga_eval_comeval, Long id_estado_solicitud, Long id_tipo_solicitud) {
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

            cadenasql = "select "
                    + "t.id_comeval_carga_eval_comeval, "
                    + "t.descripcion_solicitud, "
                    + "t.path_archivo, "
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
                    + "t.rechazado "
                    + "from "
                    + "comeval_carga_eval_comeval t "
                    + "where "
                    + "t.id_comeval_carga_eval_comeval=" + id_comeval_carga_eval_comeval;
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMddHHmmss");
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] col = lista_drive.get(i).split("♣");
                this.id_comeval_carga_eval_comeval = Long.parseLong(col[0]);
                this.descripcion_solicitud = col[1];
                this.path_archivo = col[2];
                this.id_solicitud_acta = Long.parseLong(col[3]);
                this.no_acta = col[4];
                this.anio_acta = col[5];
                this.punto_acta = col[6];
                this.inciso_acta = col[7];
                this.fecha_acta = dateFormat1.parse(col[8]);
                this.resolucion_acta = col[9];
                this.fecha_ingreso = dateFormat1.parse(col[10]);
                this.id_estado_solicitud = Long.parseLong(col[11]);
                this.id_tipo_solicitud = Long.parseLong(col[12]);
                this.rechazado = Long.parseLong(col[13]);
                if (this.rechazado == Long.parseLong("0")) {
                    this.rechazado_form = false;
                } else {
                    this.rechazado_form = true;
                }
            }

            this.lst_archivo_rest = new ArrayList<>();
            File archivo_rest = File.createTempFile("archivo_temporal", "tmp");
            archivo_rest.deleteOnExit();
            lista_archivos archivo_adjunto = new lista_archivos(Long.parseLong("1"), this.path_archivo, archivo_rest);
            this.lst_archivo_rest.add(archivo_adjunto);

            if (this.id_estado_solicitud == Long.parseLong("1")) {
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.areObservacionDocente = false;
                this.fupExcel = false;
                this.chxRechazado = true;
                this.btnEliminarArchivo = false;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }

            PrimeFaces.current().executeScript("PF('ComevalCargaEvalComevalDialogVar').show();");
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: mostrar_modificar_carga_eval_comeval ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void aceptar() {
        try {
            switch (this.opcion) {
                case "CREAR": {
                    this.Crear_Carga_Eval_Comeval();
                    break;
                }
                case "MODIFICAR": {
                    this.Modificar_Carga_Eval_Comeval();
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: aceptar ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void Crear_Carga_Eval_Comeval() {
        try {
            if (this.lst_archivo_rest.size() > 0) {
                if (this.rechazado_form) {
                    this.rechazado = Long.parseLong("1");
                } else {
                    this.rechazado = Long.parseLong("0");
                }

                for (Integer i = 0; i < this.lst_archivo_rest.size(); i++) {
                    servicio.cliente.Cliente_RestHeart_API cliente_restheart_api = new servicio.cliente.Cliente_RestHeart_API("admin", "changeit");
                    cliente_restheart_api.cargar_archivo(this.lst_archivo_rest.get(i).getArchivo(), this.lst_archivo_rest.get(i).getNombre_archivo());
                    this.path_archivo = this.lst_archivo_rest.get(i).getNombre_archivo();
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                entidad.Comeval_Carga_Eval_Comeval comeval_carga_eval_comeval = new entidad.Comeval_Carga_Eval_Comeval(
                        this.id_comeval_carga_eval_comeval,
                        this.descripcion_solicitud,
                        this.path_archivo,
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
                        this.rechazado);

                List<entidad.Comeval_Carga_Eval_Comeval> lst_comeval_carga_eval_comeval = new ArrayList<>();
                lst_comeval_carga_eval_comeval.add(comeval_carga_eval_comeval);

                servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                String resultado = cliente_api_comeval_rest.crear_carga_eval_comeval(lst_comeval_carga_eval_comeval);

                PrimeFaces.current().executeScript("PF('ComevalCargaEvalComevalDialogVar').hide();");
                // PrimeFaces.current().executeScript("PF('varTblComevalCargaEvalComeval').clearFilters();");

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
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Crear_Carga_Eval_Comeval ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void Modificar_Carga_Eval_Comeval() {
        try {
            if (this.lst_archivo_rest.size() > 0) {
                if (this.rechazado_form) {
                    this.rechazado = Long.parseLong("1");
                } else {
                    this.rechazado = Long.parseLong("0");
                }

                for (Integer i = 0; i < this.lst_archivo_rest.size(); i++) {
                    servicio.cliente.Cliente_RestHeart_API_1 cliente_restheart_api_1 = new servicio.cliente.Cliente_RestHeart_API_1("admin", "changeit");
                    String resultado = cliente_restheart_api_1.buscar_adjunto(this.lst_archivo_rest.get(i).getNombre_archivo());

                    if (resultado.equals("NO_ENCONTRADO")) {
                        servicio.cliente.Cliente_RestHeart_API cliente_restheart_api = new servicio.cliente.Cliente_RestHeart_API("admin", "changeit");
                        cliente_restheart_api.cargar_archivo(this.lst_archivo_rest.get(i).getArchivo(), this.lst_archivo_rest.get(i).getNombre_archivo());
                    }

                    this.path_archivo = this.lst_archivo_rest.get(i).getNombre_archivo();
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                entidad.Comeval_Carga_Eval_Comeval comeval_carga_eval_comeval = new entidad.Comeval_Carga_Eval_Comeval(
                        this.id_comeval_carga_eval_comeval,
                        this.descripcion_solicitud,
                        this.path_archivo,
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
                        this.rechazado);

                List<entidad.Comeval_Carga_Eval_Comeval> lst_comeval_carga_eval_comeval = new ArrayList<>();
                lst_comeval_carga_eval_comeval.add(comeval_carga_eval_comeval);

                servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                String resultado = cliente_api_comeval_rest.modificar_carga_eval_comeval(lst_comeval_carga_eval_comeval);

                PrimeFaces.current().executeScript("PF('ComevalCargaEvalComevalDialogVar').hide();");
                // PrimeFaces.current().executeScript("PF('varTblComevalCargaEvalComeval').clearFilters();");

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
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Modificar_Carga_Eval_Comeval ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            Boolean validado = false;
            String mensaje_agregar = "";
            String prefijo_adjunto = "";
            if (this.id_estado_solicitud == Long.parseLong("1")) {
                prefijo_adjunto = "eval_docente";
                validado = true;
                for (Integer i = 0; i < this.lst_archivo_rest.size(); i++) {
                    if (this.lst_archivo_rest.get(i).getNombre_archivo().contains(prefijo_adjunto)) {
                        validado = false;
                        mensaje_agregar = "Solo puede adjuntar un archivo de Notas Evaluación Docente.";
                    }
                }
            }

            if (validado) {
                File archivo_rest = File.createTempFile("archivo_temporal", "tmp");
                archivo_rest.deleteOnExit();
                FileUtils.copyInputStreamToFile(event.getFile().getInputStream(), archivo_rest);

                Date fecha_actual = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                this.lst_archivo_rest.add(new lista_archivos(Long.parseLong("1"), prefijo_adjunto + "_" + dateFormat.format(fecha_actual), archivo_rest));

                for (Integer i = 0; i < this.lst_archivo_rest.size(); i++) {
                    Integer j = i + 1;
                    this.lst_archivo_rest.get(i).setId_archivo(Long.parseLong(j.toString()));
                }

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", "Adjunto " + event.getFile().getFileName() + " cagado."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", mensaje_agregar));
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
                String mensaje_eliminado = "";
                if (this.id_estado_solicitud == Long.parseLong("4")) {
                    if (this.sel_archivo_rest.getNombre_archivo().contains("eval_docente_")) {
                        validado = true;
                    } else {
                        validado = false;
                        mensaje_eliminado = "En este estado solo puede eliminar adjuntos iniciados en 'pd_hrl_'.";
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
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", mensaje_eliminado));
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

    public entidad.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(entidad.Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId_comeval_carga_eval_comeval() {
        return id_comeval_carga_eval_comeval;
    }

    public void setId_comeval_carga_eval_comeval(Long id_comeval_carga_eval_comeval) {
        this.id_comeval_carga_eval_comeval = id_comeval_carga_eval_comeval;
    }

    public String getDescripcion_solicitud() {
        return descripcion_solicitud;
    }

    public void setDescripcion_solicitud(String descripcion_solicitud) {
        this.descripcion_solicitud = descripcion_solicitud;
    }

    public String getPath_archivo() {
        return path_archivo;
    }

    public void setPath_archivo(String path_archivo) {
        this.path_archivo = path_archivo;
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

    public Boolean getAreObservacionDocente() {
        return areObservacionDocente;
    }

    public void setAreObservacionDocente(Boolean areObservacionDocente) {
        this.areObservacionDocente = areObservacionDocente;
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

    public Boolean getBtnEliminarArchivo() {
        return btnEliminarArchivo;
    }

    public void setBtnEliminarArchivo(Boolean btnEliminarArchivo) {
        this.btnEliminarArchivo = btnEliminarArchivo;
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
