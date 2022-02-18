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
    private String nota_ref;
    private Date fecha_nota_ref;
    private Date fecha_ingreso;
    private Long id_estado_solicitud;
    private Long id_tipo_solicitud;
    private Long id_estado_solicitud_rechazado;
    private Long id_tipo_solicitud_rechazado;
    private Long rechazado;
    private Boolean rechazado_form;
    private Long visto_bueno_secretario_academico;
    private Boolean visto_bueno_secretario_academico_form;

    private List<SelectItem> lst_estado_solicitud;
    private List<SelectItem> lst_tipo_solicitud;
    private List<SelectItem> lst_estado_solicitud_rechazado;
    private List<SelectItem> lst_tipo_solicitud_rechazado;

    private String opcion;

    private String dependencia_observaciones;
    private String observacion;

    private List<lista_observaciones> lst_observaciones;
    private lista_observaciones sel_observaciones;

    private Boolean cbxTipoSolicitud;
    private Boolean cbxEstadoSolicitud;
    private Boolean txtCodigoDocente;
    private Boolean txtNombreDocente;
    private Boolean txtNotaRef;
    private Boolean calFechaNotaRef;
    private Boolean chxRechazado;
    private Boolean chxVistoBuenoSecretario;
    private Boolean cbxTipoSolicitudRechazado;
    private Boolean cbxEstadoSolicitudRechazado;
    private Boolean btnAgregarObservacion;
    private Boolean btnEliminarObservacion;
    private Boolean btnAceptar;
    private Boolean btnCancelar;

    public Bean_Comeval_Amonestacion_Docente() {
        try {
            this.lst_estado_solicitud = new ArrayList<>();
            this.lst_tipo_solicitud = new ArrayList<>();
            this.lst_estado_solicitud_rechazado = new ArrayList<>();
            this.lst_tipo_solicitud_rechazado = new ArrayList<>();
            this.lst_observaciones = new ArrayList<>();
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

            this.lst_tipo_solicitud_rechazado = this.lst_tipo_solicitud;
            this.id_tipo_solicitud_rechazado = Long.parseLong("1");

            this.lst_estado_solicitud_rechazado = this.lst_estado_solicitud;
            this.id_estado_solicitud_rechazado = Long.parseLong("1");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            this.id_comeval_amonestacion_docente = Long.parseLong("0");
            this.personal = "0";
            this.codigo_docente = this.usuario.getUsuario();
            this.nombre_docente = "-";
            this.datos_docente();
            this.nota_ref = "";
            this.fecha_nota_ref = new Date();

            this.fecha_ingreso = dateFormat.parse("1900-01-01 00:00:00");

            this.rechazado = Long.parseLong("0");
            this.rechazado_form = false;
            this.visto_bueno_secretario_academico = Long.parseLong("0");
            this.visto_bueno_secretario_academico_form = false;

            if (this.id_estado_solicitud.equals(Long.parseLong("1"))) {
                this.dependencia_observaciones = "ESCUELA";
            }
            if (this.id_estado_solicitud.equals(Long.parseLong("2"))) {
                this.dependencia_observaciones = "SECRETARIO ACADÉMICO";
            }

            this.lst_observaciones = new ArrayList<>();

            this.cbxTipoSolicitud = true;
            this.cbxEstadoSolicitud = true;
            this.txtCodigoDocente = false;
            this.txtNombreDocente = true;
            this.txtNotaRef = false;
            this.calFechaNotaRef = false;
            this.chxRechazado = true;
            this.chxVistoBuenoSecretario = true;
            this.cbxTipoSolicitudRechazado = true;
            this.cbxEstadoSolicitudRechazado = true;
            this.btnAgregarObservacion = false;
            this.btnEliminarObservacion = false;
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
                    + "where swh.id_tipo_solicitud=" + id_tipo_solicitud + " and swh.id_solicitud=" + id_comeval_amonestacion_docente + " and swh.id_estado_solicitud < " + id_estado_solicitud;
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
                    + "t.id_comeval_amonestacion_docente, "
                    + "t.personal, "
                    + "t.nota_ref, "
                    + "t.fecha_nota_ref, "
                    + "t.fecha_ingreso, "
                    + "t.id_estado_solicitud, "
                    + "t.id_tipo_solicitud, "
                    + "t.rechazado, "
                    + "t.visto_bueno_secretario_academico, "
                    + "coalesce(t.id_estado_solicitud_rechazado, 1) id_estado_solicitud_rechazado, "
                    + "coalesce(t.id_tipo_solicitud_rechazado, " + id_tipo_solicitud + ") id_tipo_solicitud_rechazado "
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
                this.nota_ref = col[2];
                this.fecha_nota_ref = dateFormat.parse(col[3]);
                this.fecha_ingreso = dateFormat.parse(col[4]);
                this.id_estado_solicitud = Long.parseLong(col[5]);
                this.id_tipo_solicitud = Long.parseLong(col[6]);
                this.rechazado = Long.parseLong(col[7]);
                if (this.rechazado == Long.parseLong("0")) {
                    this.rechazado_form = false;
                } else {
                    this.rechazado_form = true;
                }
                this.visto_bueno_secretario_academico = Long.parseLong(col[8]);
                if (this.visto_bueno_secretario_academico == Long.parseLong("0")) {
                    this.visto_bueno_secretario_academico_form = false;
                } else {
                    this.visto_bueno_secretario_academico_form = true;
                }
                this.id_estado_solicitud_rechazado = Long.parseLong(col[9]);
                this.id_tipo_solicitud_rechazado = Long.parseLong(col[10]);
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
                    + "t.id_solicitud=" + this.id_comeval_amonestacion_docente + " and "
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
                this.dependencia_observaciones = "ESCUELA";
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = false;
                this.txtNombreDocente = true;
                this.txtNotaRef = false;
                this.calFechaNotaRef = false;
                this.chxRechazado = true;
                this.chxVistoBuenoSecretario = true;
                this.cbxTipoSolicitudRechazado = true;
                if(this.rechazado_form) {
                    this.cbxEstadoSolicitudRechazado = false;
                } else {
                    this.cbxEstadoSolicitudRechazado = true;
                }
                this.btnAgregarObservacion = false;
                this.btnEliminarObservacion = false;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("2")) {
                this.dependencia_observaciones = "SECRETARÍA ACADÉMICA";
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.txtNotaRef = false;
                this.calFechaNotaRef = false;
                this.chxRechazado = false;
                this.chxVistoBuenoSecretario = false;
                this.cbxTipoSolicitudRechazado = true;
                if (this.rechazado_form) {
                    this.cbxEstadoSolicitudRechazado = false;
                } else {
                    this.cbxEstadoSolicitudRechazado = true;
                }
                this.btnAgregarObservacion = false;
                this.btnEliminarObservacion = false;
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

                if (this.visto_bueno_secretario_academico_form) {
                    this.visto_bueno_secretario_academico = Long.parseLong("1");
                } else {
                    this.visto_bueno_secretario_academico = Long.parseLong("0");
                }

                entidad.Comeval_Acta_Solicitud comeval_acta_solicitud = null;

                List<entidad.Comeval_Solicitud_Observacion> lst_observaciones_rest = new ArrayList<>();
                for (Integer i = 0; i < this.lst_observaciones.size(); i++) {
                    entidad.Comeval_Solicitud_Observacion observacion_temp = new entidad.Comeval_Solicitud_Observacion();
                    observacion_temp.setId_solicitud(this.id_comeval_amonestacion_docente);
                    observacion_temp.setId_tipo_solicitud(this.id_tipo_solicitud);
                    observacion_temp.setId_observacion(this.lst_observaciones.get(i).getId_observacion());
                    observacion_temp.setDependencia(this.lst_observaciones.get(i).getDependencia());
                    observacion_temp.setFecha_hora(this.lst_observaciones.get(i).getFecha_hora());
                    observacion_temp.setObservacion(this.lst_observaciones.get(i).getObservacion());
                    lst_observaciones_rest.add(observacion_temp);
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                entidad.Comeval_Amonestacion_Docente comeval_amonestacion_docente = new entidad.Comeval_Amonestacion_Docente(
                        this.id_comeval_amonestacion_docente,
                        this.personal,
                        this.nota_ref,
                        dateFormat.format(this.fecha_nota_ref),
                        this.usuario.getUsuario(),
                        dateFormat.format(new Date()),
                        this.id_estado_solicitud,
                        this.id_tipo_solicitud,
                        this.rechazado,
                        this.id_estado_solicitud_rechazado,
                        this.id_tipo_solicitud_rechazado,
                        this.visto_bueno_secretario_academico,
                        comeval_acta_solicitud,
                        lst_observaciones_rest);

                List<entidad.Comeval_Amonestacion_Docente> lst_comeval_amonestacion_docente = new ArrayList<>();
                lst_comeval_amonestacion_docente.add(comeval_amonestacion_docente);

                servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                String resultado = cliente_api_comeval_rest.amonestacion_docente_ingresar(lst_comeval_amonestacion_docente);

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

                if (this.visto_bueno_secretario_academico_form) {
                    this.visto_bueno_secretario_academico = Long.parseLong("1");
                } else {
                    this.visto_bueno_secretario_academico = Long.parseLong("0");
                }

                entidad.Comeval_Acta_Solicitud comeval_acta_solicitud = null;

                List<entidad.Comeval_Solicitud_Observacion> lst_observaciones_rest = new ArrayList<>();
                for (Integer i = 0; i < this.lst_observaciones.size(); i++) {
                    entidad.Comeval_Solicitud_Observacion observacion_temp = new entidad.Comeval_Solicitud_Observacion();
                    observacion_temp.setId_solicitud(this.id_comeval_amonestacion_docente);
                    observacion_temp.setId_tipo_solicitud(this.id_tipo_solicitud);
                    observacion_temp.setId_observacion(this.lst_observaciones.get(i).getId_observacion());
                    observacion_temp.setDependencia(this.lst_observaciones.get(i).getDependencia());
                    observacion_temp.setFecha_hora(this.lst_observaciones.get(i).getFecha_hora());
                    observacion_temp.setObservacion(this.lst_observaciones.get(i).getObservacion());
                    lst_observaciones_rest.add(observacion_temp);
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                entidad.Comeval_Amonestacion_Docente comeval_amonestacion_docente = new entidad.Comeval_Amonestacion_Docente(
                        this.id_comeval_amonestacion_docente,
                        this.personal,
                        this.nota_ref,
                        dateFormat.format(this.fecha_nota_ref),
                        this.usuario.getUsuario(),
                        dateFormat.format(new Date()),
                        this.id_estado_solicitud,
                        this.id_tipo_solicitud,
                        this.rechazado,
                        this.id_estado_solicitud_rechazado,
                        this.id_tipo_solicitud_rechazado,
                        this.visto_bueno_secretario_academico,
                        comeval_acta_solicitud,
                        lst_observaciones_rest);

                List<entidad.Comeval_Amonestacion_Docente> lst_comeval_amonestacion_docente = new ArrayList<>();
                lst_comeval_amonestacion_docente.add(comeval_amonestacion_docente);

                servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                String resultado = cliente_api_comeval_rest.amonestacion_docente_modificar(lst_comeval_amonestacion_docente);

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

    public void agregar_observacion() {
        try {
            this.observacion = "";
            PrimeFaces.current().executeScript("PF('AmonestacionObservacionesDialogVar').show();");
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
            beans.Comeval_Amonestacion_Docente.lista_observaciones observacion_dialog = new beans.Comeval_Amonestacion_Docente.lista_observaciones();
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

            PrimeFaces.current().executeScript("PF('AmonestacionObservacionesDialogVar').hide();");
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

    public Boolean getChxRechazado() {
        return chxRechazado;
    }

    public void setChxRechazado(Boolean chxRechazado) {
        this.chxRechazado = chxRechazado;
    }

    public Boolean getChxVistoBuenoSecretario() {
        return chxVistoBuenoSecretario;
    }

    public void setChxVistoBuenoSecretario(Boolean chxVistoBuenoSecretario) {
        this.chxVistoBuenoSecretario = chxVistoBuenoSecretario;
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
