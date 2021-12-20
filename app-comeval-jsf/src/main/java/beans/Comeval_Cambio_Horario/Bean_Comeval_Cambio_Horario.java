package beans.Comeval_Cambio_Horario;

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

@Named(value = "Bean_Comeval_Cambio_Horario")
@ViewScoped
public class Bean_Comeval_Cambio_Horario implements Serializable {

    private static final long serialVersionUID = 1L;

    private entidad.Usuario usuario;

    private Long id_comeval_cambio_horario;
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

    private Date hora_inicio;
    private Date hora_fin;
    private String plaza;
    private Boolean lunes;
    private Boolean martes;
    private Boolean miercoles;
    private Boolean jueves;
    private Boolean viernes;
    private Boolean sabado;
    private Boolean domingo;

    private Long rechazado;
    private Boolean rechazado_form;
    private Long visto_bueno_director;
    private Boolean visto_bueno_director_form;
    private Long visto_bueno_secretario_academico;
    private Boolean visto_bueno_secretario_academico_form;

    private List<SelectItem> lst_estado_solicitud;
    private List<SelectItem> lst_tipo_solicitud;
    private List<SelectItem> lst_plazas;
    private List<Lista_Horarios> lst_horarios_plaza;
    private Lista_Horarios sel_horarios_plaza;

    private String opcion;

    private Boolean cbxTipoSolicitud;
    private Boolean cbxEstadoSolicitud;
    private Boolean txtCodigoDocente;
    private Boolean txtNombreDocente;
    private Boolean areObservacionDocente;
    private Boolean calHoraInicio;
    private Boolean calHoraFin;
    private Boolean cbxPlaza;
    private Boolean chxLunes;
    private Boolean chxMartes;
    private Boolean chxMiercoles;
    private Boolean chxJueves;
    private Boolean chxViernes;
    private Boolean chxSabado;
    private Boolean chxDomingo;
    private Boolean chxRechazado;
    private Boolean chxVistoBuenoDirector;
    private Boolean chxVistoSecretarioAcademico;
    private Boolean btnAgregarHorario;
    private Boolean btnEliminarHorario;
    private Boolean btnAceptar;
    private Boolean btnCancelar;

    public Bean_Comeval_Cambio_Horario() {
        try {
            this.lst_estado_solicitud = new ArrayList<>();
            this.lst_tipo_solicitud = new ArrayList<>();
            this.lst_horarios_plaza = new ArrayList<>();
            this.lst_plazas = new ArrayList<>();
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Bean_Comeval_Cambio_Horario ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void mostrar_crear_cambio_horario(entidad.Usuario usuario, Long id_estado_solicitud, Long id_tipo_solicitud) {
        try {
            this.usuario = usuario;
            this.opcion = "CREAR";

            // INICIALIZAR CATALOGO TIPO_SOLICITUD Y ASIGNAR EL VALOR POR PARAMETRO.
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

            // INICIALIZAR CATALOGO ESTADO_SOLICITUD Y ASIGNAR EL VALOR POR PARAMETRO.
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

            // INICIALIZAR LOS CAMPOS DE FORMULARIO.
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // Valores iniciales del formulario.
            this.id_comeval_cambio_horario = Long.parseLong("0");
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
            this.visto_bueno_director = Long.parseLong("0");
            this.visto_bueno_director_form = false;
            this.visto_bueno_secretario_academico = Long.parseLong("0");
            this.visto_bueno_secretario_academico_form = false;

            this.cbxTipoSolicitud = true;
            this.cbxEstadoSolicitud = true;
            this.txtCodigoDocente = false;
            this.txtNombreDocente = true;
            this.areObservacionDocente = false;
            this.chxRechazado = true;
            this.chxVistoBuenoDirector = true;
            this.chxVistoSecretarioAcademico = true;
            this.btnAgregarHorario = false;
            this.btnEliminarHorario = false;
            this.btnAceptar = false;
            this.btnCancelar = false;

            PrimeFaces.current().executeScript("PF('ComevalCambioHorarioDialogVar').show();");
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: mostrar_crear_cambio_horario ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void mostrar_modificar_cambio_horario(entidad.Usuario usuario, Long id_comeval_cambio_horario, Long id_estado_solicitud, Long id_tipo_solicitud) {
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
                    + "t.id_comeval_cambio_horario, "
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
                    + "t.rechazado, "
                    + "t.visto_bueno_director, "
                    + "t.visto_bueno_secretario_academico "
                    + "from "
                    + "comeval_cambio_horario t "
                    + "where "
                    + "t.id_comeval_cambio_horario=" + id_comeval_cambio_horario;
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] col = lista_drive.get(i).split("♣");
                this.id_comeval_cambio_horario = Long.parseLong(col[0]);
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
                this.visto_bueno_director = Long.parseLong(col[14]);
                if (this.visto_bueno_director == Long.parseLong("0")) {
                    this.visto_bueno_director_form = false;
                } else {
                    this.visto_bueno_director_form = true;
                }
                this.visto_bueno_secretario_academico = Long.parseLong(col[15]);
                if (this.visto_bueno_secretario_academico == Long.parseLong("0")) {
                    this.visto_bueno_secretario_academico_form = false;
                } else {
                    this.visto_bueno_secretario_academico_form = true;
                }
            }
            
            this.lst_horarios_plaza = new ArrayList<>();
            cadenasql = "select "
                    + "t.id_comeval_cambio_horario, "
                    + "t.id_horario, "
                    + "t.horainicio, "
                    + "t.horafin, "
                    + "t.dias, "
                    + "(t.anio || '-' || t.periodo || '-' || p.numero || '-' || t.subpartida) id_plaza "
                    + "from "
                    + "comeval_cambio_horario_plaza t "
                    + "left join plaza p on (t.plaza = p.plaza) "
                    + "where "
                    + "t.id_comeval_cambio_horario = " + id_comeval_cambio_horario;
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] col = lista_drive.get(i).split("♣");
                Lista_Horarios lista_horarios = new Lista_Horarios();
                lista_horarios.setId_horario(Long.parseLong(col[1]));
                lista_horarios.setHora_inicio(col[2]);
                lista_horarios.setHora_final(col[3]);
                lista_horarios.setDias(col[4]);
                lista_horarios.setPlaza(col[5]);
                this.lst_horarios_plaza.add(lista_horarios);
            }

            if (this.id_estado_solicitud == Long.parseLong("1")) {
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.areObservacionDocente = false;
                this.chxRechazado = true;
                this.chxVistoBuenoDirector = true;
                this.chxVistoSecretarioAcademico = true;
                this.btnAgregarHorario = false;
                this.btnEliminarHorario = false;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("2")) {
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.areObservacionDocente = false;
                this.chxRechazado = true;
                this.chxVistoBuenoDirector = false;
                this.chxVistoSecretarioAcademico = true;
                this.btnAgregarHorario = true;
                this.btnEliminarHorario = true;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }
            if (this.id_estado_solicitud == Long.parseLong("3")) {
                this.cbxTipoSolicitud = true;
                this.cbxEstadoSolicitud = true;
                this.txtCodigoDocente = true;
                this.txtNombreDocente = true;
                this.areObservacionDocente = false;
                this.chxRechazado = true;
                this.chxVistoBuenoDirector = true;
                this.chxVistoSecretarioAcademico = false;
                this.btnAgregarHorario = true;
                this.btnEliminarHorario = true;
                this.btnAceptar = false;
                this.btnCancelar = false;
            }

            PrimeFaces.current().executeScript("PF('ComevalCambioHorarioDialogVar').show();");
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: mostrar_modificar_cambio_horario ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void aceptar() {
        try {
            switch (this.opcion) {
                case "CREAR": {
                    this.Crear_Cambio_Horario();
                    break;
                }
                case "MODIFICAR": {
                    this.Modificar_Cambio_Horario();
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: aceptar ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void Crear_Cambio_Horario() {
        try {
            if (!this.personal.equals("0")) {
                if (this.lst_horarios_plaza.size() > 0) {
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

                    List<entidad.Comeval_Cambio_Horario_Plaza> lst_horario_plaza = new ArrayList<>();
                    for (Integer i = 0; i < this.lst_horarios_plaza.size(); i++) {
                        entidad.Comeval_Cambio_Horario_Plaza comeval_cambio_horario_plaza = new entidad.Comeval_Cambio_Horario_Plaza();
                        comeval_cambio_horario_plaza.setId_comeval_cambio_horario(this.id_comeval_cambio_horario);
                        comeval_cambio_horario_plaza.setHorainicio(this.lst_horarios_plaza.get(i).getHora_inicio());
                        comeval_cambio_horario_plaza.setHorafin(this.lst_horarios_plaza.get(i).getHora_final());
                        comeval_cambio_horario_plaza.setDias(this.lst_horarios_plaza.get(i).getDias());

                        String cadenasql = "select pp.personal, pp.plaza, pp.periodo, pp.anio , pp.subpartida , pp.renglon "
                                + "from "
                                + "plazapersonal pp "
                                + "left join plaza p on (pp.plaza = p.plaza) "
                                + "where (pp.anio || '-' || pp.periodo || '-' || p.numero || '-' || pp.subpartida) = '" + this.lst_horarios_plaza.get(i).getPlaza() + "'";
                        servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                        String jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
                        Type listType = new TypeToken<ArrayList<String>>() {
                        }.getType();
                        List<String> lista_drive = new Gson().fromJson(jsonString, listType);
                        for (Integer j = 1; j < lista_drive.size(); j++) {
                            String[] reg_plaza = lista_drive.get(j).split("♣");

                            comeval_cambio_horario_plaza.setId_horario(Long.parseLong("1"));
                            comeval_cambio_horario_plaza.setPersonal(reg_plaza[0]);
                            comeval_cambio_horario_plaza.setPlaza(Long.parseLong(reg_plaza[1]));
                            comeval_cambio_horario_plaza.setPeriodo(Long.parseLong(reg_plaza[2]));
                            comeval_cambio_horario_plaza.setAnio(Long.parseLong(reg_plaza[3]));
                            comeval_cambio_horario_plaza.setSubpartida(reg_plaza[4]);
                            comeval_cambio_horario_plaza.setRenglon(reg_plaza[5]);
                        }
                        lst_horario_plaza.add(comeval_cambio_horario_plaza);
                    }

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    entidad.Comeval_Cambio_Horario comeval_cambio_horario = new entidad.Comeval_Cambio_Horario(
                            this.id_comeval_cambio_horario,
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
                            this.rechazado,
                            this.visto_bueno_director,
                            this.visto_bueno_secretario_academico,
                            lst_horario_plaza);

                    List<entidad.Comeval_Cambio_Horario> lst_comeval_cambio_horario = new ArrayList<>();
                    lst_comeval_cambio_horario.add(comeval_cambio_horario);

                    servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                    String resultado = cliente_api_comeval_rest.crear_cambio_horario(lst_comeval_cambio_horario);

                    PrimeFaces.current().executeScript("PF('ComevalCambioHorarioDialogVar').hide();");
                    // PrimeFaces.current().executeScript("PF('varTblComevalCambioHorario').clearFilters();");

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
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Crear_Cambio_Horario ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void Modificar_Cambio_Horario() {
        try {
            if (!this.personal.equals("0")) {
                if (this.lst_horarios_plaza.size() > 0) {
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

                    List<entidad.Comeval_Cambio_Horario_Plaza> lst_horario_plaza = new ArrayList<>();
                    for (Integer i = 0; i < this.lst_horarios_plaza.size(); i++) {
                        entidad.Comeval_Cambio_Horario_Plaza comeval_cambio_horario_plaza = new entidad.Comeval_Cambio_Horario_Plaza();
                        comeval_cambio_horario_plaza.setId_comeval_cambio_horario(this.id_comeval_cambio_horario);
                        comeval_cambio_horario_plaza.setHorainicio(this.lst_horarios_plaza.get(i).getHora_inicio());
                        comeval_cambio_horario_plaza.setHorafin(this.lst_horarios_plaza.get(i).getHora_final());
                        comeval_cambio_horario_plaza.setDias(this.lst_horarios_plaza.get(i).getDias());

                        String cadenasql = "select pp.personal, pp.plaza, pp.periodo, pp.anio , pp.subpartida , pp.renglon "
                                + "from "
                                + "plazapersonal pp "
                                + "left join plaza p on (pp.plaza = p.plaza) "
                                + "where (pp.anio || '-' || pp.periodo || '-' || p.numero || '-' || pp.subpartida) = '" + this.lst_horarios_plaza.get(i).getPlaza() + "'";
                        servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                        String jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
                        Type listType = new TypeToken<ArrayList<String>>() {
                        }.getType();
                        List<String> lista_drive = new Gson().fromJson(jsonString, listType);
                        for (Integer j = 1; j < lista_drive.size(); j++) {
                            String[] reg_plaza = lista_drive.get(j).split("♣");

                            comeval_cambio_horario_plaza.setId_horario(Long.parseLong("1"));
                            comeval_cambio_horario_plaza.setPersonal(reg_plaza[0]);
                            comeval_cambio_horario_plaza.setPlaza(Long.parseLong(reg_plaza[1]));
                            comeval_cambio_horario_plaza.setPeriodo(Long.parseLong(reg_plaza[2]));
                            comeval_cambio_horario_plaza.setAnio(Long.parseLong(reg_plaza[3]));
                            comeval_cambio_horario_plaza.setSubpartida(reg_plaza[4]);
                            comeval_cambio_horario_plaza.setRenglon(reg_plaza[5]);
                        }
                        lst_horario_plaza.add(comeval_cambio_horario_plaza);
                    }

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    entidad.Comeval_Cambio_Horario comeval_cambio_horario = new entidad.Comeval_Cambio_Horario(
                            this.id_comeval_cambio_horario,
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
                            this.rechazado,
                            this.visto_bueno_director,
                            this.visto_bueno_secretario_academico,
                            lst_horario_plaza);

                    List<entidad.Comeval_Cambio_Horario> lst_comeval_cambio_horario = new ArrayList<>();
                    lst_comeval_cambio_horario.add(comeval_cambio_horario);

                    servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                    String resultado = cliente_api_comeval_rest.modificar_cambio_horario(lst_comeval_cambio_horario);

                    PrimeFaces.current().executeScript("PF('ComevalCambioHorarioDialogVar').hide();");
                    // PrimeFaces.current().executeScript("PF('varTblComevalCambioHorario').clearFilters();");

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
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Modificar_Cambio_Horario ERROR: " + ex.toString());
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

            // INICIALICAR LISTA DE PLAZAS ASIGNADAS AL DOCENTE.
            Integer anio_actual = Calendar.getInstance().get(Calendar.YEAR);
            Integer mes_actual = Calendar.getInstance().get(Calendar.MONTH);
            Integer periodo_actual = 0;
            if (mes_actual <= 6) {
                periodo_actual = 1;
            } else {
                periodo_actual = 2;
            }

            this.lst_plazas = new ArrayList<>();
            cadenasql = "select (pp.anio || '-' || pp.periodo || '-' || p.numero || '-' || pp.subpartida) id_plaza "
                    + "from "
                    + "plazapersonal pp "
                    + "left join plaza p on (pp.plaza = p.plaza) "
                    + "where "
                    + "pp.personal = '" + this.codigo_docente + "' and "
                    + "pp.anio = " + anio_actual + " and "
                    + "pp.periodo = " + periodo_actual;
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] rol = lista_drive.get(i).split("♣");
                this.lst_plazas.add(new SelectItem(rol[0], rol[0]));
            }

            // INICIALIZAR CONTROL DE DIAS DEL HORARIO.
            this.lunes = false;
            this.martes = false;
            this.miercoles = false;
            this.jueves = false;
            this.viernes = false;
            this.sabado = false;
            this.domingo = false;

            this.lst_horarios_plaza = new ArrayList<>();
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

            Boolean PlazaConHorario = false;
            for (Integer i = 0; i < this.lst_horarios_plaza.size(); i++) {
                if (dateFormat.format(this.hora_inicio).equals(lst_horarios_plaza.get(i).getHora_inicio()) && dateFormat.format(this.hora_fin).equals(lst_horarios_plaza.get(i).getHora_final()) && dias.equals(this.lst_horarios_plaza.get(i).getDias()) && this.plaza.equals(this.lst_horarios_plaza.get(i).getPlaza())) {
                    PlazaConHorario = true;
                }
            }

            if (this.lst_plazas.size() > 0) {
                if (ContadorDias > 0) {
                    if (!PlazaConHorario) {
                        Lista_Horarios lista_horarios = new Lista_Horarios(Long.parseLong("1"), dateFormat.format(this.hora_inicio), dateFormat.format(this.hora_fin), dias, this.plaza);
                        this.lst_horarios_plaza.add(lista_horarios);

                        for (Integer i = 0; i < this.lst_horarios_plaza.size(); i++) {
                            Integer j = i + 1;
                            this.lst_horarios_plaza.get(i).setId_horario(Long.parseLong(j.toString()));
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
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje del sistema...", "El docente no tiene plaza vigente asignada."));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: agregar_horario ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void eliminar_horario() {
        try {
            if(this.sel_horarios_plaza != null) {
                this.lst_horarios_plaza.remove(this.sel_horarios_plaza);
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: eliminar_horario ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public entidad.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(entidad.Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId_comeval_cambio_horario() {
        return id_comeval_cambio_horario;
    }

    public void setId_comeval_cambio_horario(Long id_comeval_cambio_horario) {
        this.id_comeval_cambio_horario = id_comeval_cambio_horario;
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

    public String getPlaza() {
        return plaza;
    }

    public void setPlaza(String plaza) {
        this.plaza = plaza;
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

    public List<SelectItem> getLst_plazas() {
        return lst_plazas;
    }

    public void setLst_plazas(List<SelectItem> lst_plazas) {
        this.lst_plazas = lst_plazas;
    }

    public List<Lista_Horarios> getLst_horarios_plaza() {
        return lst_horarios_plaza;
    }

    public void setLst_horarios_plaza(List<Lista_Horarios> lst_horarios_plaza) {
        this.lst_horarios_plaza = lst_horarios_plaza;
    }

    public Lista_Horarios getSel_horarios_plaza() {
        return sel_horarios_plaza;
    }

    public void setSel_horarios_plaza(Lista_Horarios sel_horarios_plaza) {
        this.sel_horarios_plaza = sel_horarios_plaza;
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

    public Boolean getCbxPlaza() {
        return cbxPlaza;
    }

    public void setCbxPlaza(Boolean cbxPlaza) {
        this.cbxPlaza = cbxPlaza;
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

}
