package beans.Comeval_Amonestacion_Docente;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import org.primefaces.PrimeFaces;

@Named(value = "Bean_Comeval_Amonestacion_Docente")
@ViewScoped
public class Bean_Comeval_Amonestacion_Docente implements Serializable {

    private static final long serialVersionUID = 1L;

    private entidad.Usuario usuario;

    private Long id_comeval_amonestacion_docente;
    private String personal;
    private String codigo_docente;
    private String nombre_docente;
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

    private List<SelectItem> lst_estado_solicitud;
    private List<SelectItem> lst_tipo_solicitud;

    private String opcion;

    private Boolean cbxTipoSolicitud;
    private Boolean cbxEstadoSolicitud;
    private Boolean txtCodigoDocente;
    private Boolean txtNombreDocente;
    private Boolean areObservacionDocente;
    private Boolean chxRechazado;
    private Boolean btnAceptar;
    private Boolean btnCancelar;

    public Bean_Comeval_Amonestacion_Docente() {
        try {
            this.lst_estado_solicitud = new ArrayList<>();
            this.lst_tipo_solicitud = new ArrayList<>();
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Bean_Comeval_Amonestacion_Docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void mostrar_crear_amonestacion_docente(entidad.Usuario usuario, Long id_estado_solicitud, Long id_tipo_solicitud) {
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
            // Valores iniciales del formulario.
            this.id_comeval_amonestacion_docente = Long.parseLong("0");
            this.personal = "0";
            this.codigo_docente = this.usuario.getUsuario();
            this.nombre_docente = "-";
            this.datos_docente();
            this.descripcion_solicitud = "-";
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
            this.chxRechazado = true;
            this.btnAceptar = false;
            this.btnCancelar = false;

            PrimeFaces.current().executeScript("PF('ComevalAmonestacionDocenteDialogVar').show();");
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: mostrar_crear_amonestacion_docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void mostrar_modificar_amonestacion_docente(entidad.Usuario usuario, Long id_comeval_amonestacion_docente, Long id_estado_solicitud, Long id_tipo_solicitud) {
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
                    + "t.id_comeval_amonestacion_docente, "
                    + "t.personal, "
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
                    + "t.rechazado "
                    + "from "
                    + "comeval_amonestacion_docente t "
                    + "where "
                    + "t.id_comeval_amonestacion_docente=" + id_comeval_amonestacion_docente;
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] col = lista_drive.get(i).split("♣");
                this.id_comeval_amonestacion_docente = Long.parseLong(col[0]);
                this.personal = col[1];
                this.codigo_docente = col[1];
                this.datos_docente();
                this.descripcion_solicitud = col[2];
                this.id_solicitud_acta = Long.parseLong(col[3]);
                this.no_acta = col[4];
                this.anio_acta = col[5];
                this.punto_acta = col[6];
                this.inciso_acta = col[7];
                this.fecha_acta = dateFormat.parse(col[8]);
                this.resolucion_acta = col[9];
                this.fecha_ingreso = dateFormat.parse(col[10]);
                this.id_estado_solicitud = Long.parseLong(col[11]);
                this.id_tipo_solicitud = Long.parseLong(col[12]);
                this.rechazado = Long.parseLong(col[13]);
                if (this.rechazado == Long.parseLong("0")) {
                    this.rechazado_form = false;
                } else {
                    this.rechazado_form = true;
                }
            }

            if (this.id_estado_solicitud == Long.parseLong("1")) {
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.areObservacionDocente = false;
                this.chxRechazado = true;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }

            PrimeFaces.current().executeScript("PF('ComevalAmonestacionDocenteDialogVar').show();");
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: mostrar_modificar_amonestacion_docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void aceptar() {
        try {
            switch (this.opcion) {
                case "CREAR": {
                    this.Crear_Amonestacion_Docente();
                    break;
                }
                case "MODIFICAR": {
                    this.Modificar_Amonestacion_Docente();
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: aceptar ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void Crear_Amonestacion_Docente() {
        try {
            if (!this.personal.equals("0")) {
                if (this.rechazado_form) {
                    this.rechazado = Long.parseLong("1");
                } else {
                    this.rechazado = Long.parseLong("0");
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                entidad.Comeval_Amonestacion_Docente comeval_amonestacion_docente = new entidad.Comeval_Amonestacion_Docente(
                        this.id_comeval_amonestacion_docente,
                        this.personal,
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
                        this.rechazado);

                List<entidad.Comeval_Amonestacion_Docente> lst_comeval_amonestacion_docente = new ArrayList<>();
                lst_comeval_amonestacion_docente.add(comeval_amonestacion_docente);

                servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                String resultado = cliente_api_comeval_rest.crear_amonestacion_docente(lst_comeval_amonestacion_docente);

                PrimeFaces.current().executeScript("PF('ComevalAmonestacionDocenteDialogVar').hide();");
                // PrimeFaces.current().executeScript("PF('varTblComevalAmonestacionDocente').clearFilters();");

                String[] mensaje = resultado.split(",");
                if (mensaje[0].equals("0")) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", mensaje[1]));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", mensaje[1]));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", "Debe ingresar un docente registrado en el sistema."));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Crear_Amonestacion_Docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void Modificar_Amonestacion_Docente() {
        try {
            if (!this.personal.equals("0")) {
                if (this.rechazado_form) {
                    this.rechazado = Long.parseLong("1");
                } else {
                    this.rechazado = Long.parseLong("0");
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                entidad.Comeval_Amonestacion_Docente comeval_amonestacion_docente = new entidad.Comeval_Amonestacion_Docente(
                        this.id_comeval_amonestacion_docente,
                        this.personal,
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
                        this.rechazado);

                List<entidad.Comeval_Amonestacion_Docente> lst_comeval_amonestacion_docente = new ArrayList<>();
                lst_comeval_amonestacion_docente.add(comeval_amonestacion_docente);

                servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                String resultado = cliente_api_comeval_rest.modificar_amonestacion_docente(lst_comeval_amonestacion_docente);

                PrimeFaces.current().executeScript("PF('ComevalAmonestacionDocenteDialogVar').hide();");
                // PrimeFaces.current().executeScript("PF('varTblComevalAmonestacionDocente').clearFilters();");

                String[] mensaje = resultado.split(",");
                if (mensaje[0].equals("0")) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", mensaje[1]));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", mensaje[1]));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", "Debe ingresar un docente registrado en el sistema."));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Modificar_Amonestacion_Docente ERROR: " + ex.toString());
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

    public Long getId_comeval_amonestacion_docente() {
        return id_comeval_amonestacion_docente;
    }

    public void setId_comeval_amonestacion_docente(Long id_comeval_amonestacion_docente) {
        this.id_comeval_amonestacion_docente = id_comeval_amonestacion_docente;
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

    public Boolean getChxRechazado() {
        return chxRechazado;
    }

    public void setChxRechazado(Boolean chxRechazado) {
        this.chxRechazado = chxRechazado;
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
