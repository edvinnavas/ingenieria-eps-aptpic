package beans.Comeval_Ampliacion_Horario;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.inject.Named;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;

@Named(value = "Bean_Lst_Comeval_Ampliacion_Horario")
@ViewScoped
public class Bean_Lst_Comeval_Ampliacion_Horario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private entidad.Usuario usuario;
    private List<Tbl_Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario;
    private Tbl_Comeval_Ampliacion_Horario sel_comeval_ampliacion_horario;
    private Long id_estado_solicitud;
    private Long id_tipo_solicitud;
    
    private Boolean btnCrear;
    
    public Bean_Lst_Comeval_Ampliacion_Horario() {
        try {
            this.lst_comeval_ampliacion_horario = new ArrayList<>();
        } catch(Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Bean_Lst_Comeval_Ampliacion_Horario ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }
    
    public void cargar_datos(entidad.Usuario usuario, Long id_estado_solicitud, Long id_tipo_solicitud) {
        try {
            this.usuario = usuario;
            this.id_estado_solicitud = id_estado_solicitud;
            this.id_tipo_solicitud = id_tipo_solicitud;
            
            String cadenasql = "select "
                    + "cah.id_comeval_ampliacion_horario id_solicitud, "
                    + "cah.personal id_personal, "
                    + "cah.fecha_ingreso fecha_ingreso, "
                    + "es.nombre estado_solicitud, "
                    + "ts.nombre tipo_solicitud "
                    + "from "
                    + "comeval_ampliacion_horario cah "
                    + "left join estado_solicitud_comeval es on (cah.id_estado_solicitud = es.id_estado_solicitud and cah.id_tipo_solicitud = es.id_tipo_solicitud) "
                    + "left join tipo_solicitud_comeval ts on (es.id_tipo_solicitud = ts.id_tipo_solicitud) "
                    + "where "
                    + "cah.id_estado_solicitud=" + this.id_estado_solicitud + " and "
                    + "cah.id_tipo_solicitud=" + this.id_tipo_solicitud + " "
                    + "order by "
                    + "cah.id_comeval_ampliacion_horario";
            
            this.lst_comeval_ampliacion_horario = new ArrayList<>();
            servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            String jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            Type listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            List<String> lista_drive = new Gson().fromJson(jsonString, listType);
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] col = lista_drive.get(i).split("♣");
                Tbl_Comeval_Ampliacion_Horario tbl_comeval_ampliacion_horario = new Tbl_Comeval_Ampliacion_Horario(Long.parseLong(col[0]), col[1], dateFormat1.parse(col[2]), col[3], col[4]);
                this.lst_comeval_ampliacion_horario.add(tbl_comeval_ampliacion_horario);
            }
            
            if(this.id_estado_solicitud == Long.parseLong("1")) {
                this.btnCrear = false;
            } else {
                this.btnCrear = true;
            }
            
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: cargar_datos ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }
    
    public void siguiente_estado_solicitud() {
        try {
            if (this.sel_comeval_ampliacion_horario != null) {
                List<entidad.Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario_temp = new ArrayList<>();

                String cadenasql = "select "
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
                        + "coalesce(t.id_estado_solicitud_rechazado,1) id_estado_solicitud_rechazado, "
                        + "coalesce(t.id_tipo_solicitud_rechazado,1) id_tipo_solicitud_rechazado, "
                        + "t.visto_bueno_director_escuela, "
                        + "t.visto_bueno_secretario_academico. "
                        + "t.notificacion_tesoreria "
                        + "from "
                        + "comeval_ampliacion_horario t "
                        + "where "
                        + "t.id_comeval_ampliacion_horario=" + this.sel_comeval_ampliacion_horario.getId_comeval_ampliacion_horario();
                servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                String jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
                Type listType = new TypeToken<ArrayList<String>>() {
                }.getType();
                List<String> lista_drive = new Gson().fromJson(jsonString, listType);
                for (Integer i = 1; i < lista_drive.size(); i++) {
                    String[] col = lista_drive.get(i).split("♣");
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                    entidad.Comeval_Ampliacion_Horario comeval_ampliacion_horario = new entidad.Comeval_Ampliacion_Horario(
                            Long.parseLong(col[0]),
                            col[1],
                            Long.parseLong(col[2]),
                            Long.parseLong(col[3]),
                            Long.parseLong(col[4]),
                            col[5],
                            dateFormat2.format(dateFormat1.parse(col[6])),
                            this.usuario.getUsuario(),
                            dateFormat2.format(dateFormat1.parse(col[7])),
                            Long.parseLong(col[8]),
                            Long.parseLong(col[9]),
                            Long.parseLong(col[10]),
                            Long.parseLong(col[11]),
                            Long.parseLong(col[12]),
                            Long.parseLong(col[13]),
                            Long.parseLong(col[14]),
                            Long.parseLong(col[15]),
                            null,
                            null,
                            null);
                    lst_comeval_ampliacion_horario_temp.add(comeval_ampliacion_horario);
                }

                cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                String resultado = "";
                if (this.id_estado_solicitud == Long.parseLong("1")) {
                    resultado = cliente_api_comeval_rest.ampliacion_horario_enviar_ingreso_docente(lst_comeval_ampliacion_horario_temp);
                }
                if (this.id_estado_solicitud == Long.parseLong("2")) {
                    resultado = cliente_api_comeval_rest.ampliacion_horario_enviar_visto_bueno_escuela(lst_comeval_ampliacion_horario_temp);
                }
                if (this.id_estado_solicitud == Long.parseLong("3")) {
                    resultado = cliente_api_comeval_rest.ampliacion_horario_enviar_visto_bueno_secretario(lst_comeval_ampliacion_horario_temp);
                }
                if (this.id_estado_solicitud == Long.parseLong("5")) {
                    resultado = cliente_api_comeval_rest.ampliacion_horario_enviar_notificacion_secretaria(lst_comeval_ampliacion_horario_temp);
                }

                this.cargar_datos(this.usuario, this.id_estado_solicitud, this.id_tipo_solicitud);

                String[] mensaje = resultado.split(",");
                if (mensaje[0].equals("0")) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", mensaje[1]));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje del sistema...", mensaje[1]));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje del sistema...", "Debe seleccionar una solicitud."));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: siguiente_estado_solicitud ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public entidad.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(entidad.Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Tbl_Comeval_Ampliacion_Horario> getLst_comeval_ampliacion_horario() {
        return lst_comeval_ampliacion_horario;
    }

    public void setLst_comeval_ampliacion_horario(List<Tbl_Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario) {
        this.lst_comeval_ampliacion_horario = lst_comeval_ampliacion_horario;
    }

    public Tbl_Comeval_Ampliacion_Horario getSel_comeval_ampliacion_horario() {
        return sel_comeval_ampliacion_horario;
    }

    public void setSel_comeval_ampliacion_horario(Tbl_Comeval_Ampliacion_Horario sel_comeval_ampliacion_horario) {
        this.sel_comeval_ampliacion_horario = sel_comeval_ampliacion_horario;
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

    public Boolean getBtnCrear() {
        return btnCrear;
    }

    public void setBtnCrear(Boolean btnCrear) {
        this.btnCrear = btnCrear;
    }
    
}
