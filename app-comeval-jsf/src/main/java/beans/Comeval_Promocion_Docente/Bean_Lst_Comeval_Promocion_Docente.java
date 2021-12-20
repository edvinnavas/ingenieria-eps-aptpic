package beans.Comeval_Promocion_Docente;

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

@Named(value = "Bean_Lst_Comeval_Promocion_Docente")
@ViewScoped
public class Bean_Lst_Comeval_Promocion_Docente implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private entidad.Usuario usuario;
    private List<Tbl_Comeval_Promocion_Docente> lst_comeval_promocion_docente;
    private Tbl_Comeval_Promocion_Docente sel_comeval_promocion_docente;
    private Long id_estado_solicitud;
    private Long id_tipo_solicitud;
    
    private Boolean btnCrear;
    
    public Bean_Lst_Comeval_Promocion_Docente() {
        try {
            this.lst_comeval_promocion_docente = new ArrayList<>();
        } catch(Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Bean_Lst_Comeval_Promocion_Docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }
    
    public void cargar_datos(entidad.Usuario usuario, Long id_estado_solicitud, Long id_tipo_solicitud) {
        try {
            this.usuario = usuario;
            this.id_estado_solicitud = id_estado_solicitud;
            this.id_tipo_solicitud = id_tipo_solicitud;
            
            String cadenasql = "select "
                    + "cpd.id_comeval_promocion_docente id_solicitud, "
                    + "cpd.personal id_personal, "
                    + "cpd.fecha_ingreso fecha_ingreso, "
                    + "es.nombre estado_solicitud, "
                    + "ts.nombre tipo_solicitud "
                    + "from "
                    + "comeval_promocion_docente cpd "
                    + "left join estado_solicitud_comeval es on (cpd.id_estado_solicitud = es.id_estado_solicitud and cpd.id_tipo_solicitud = es.id_tipo_solicitud) "
                    + "left join tipo_solicitud_comeval ts on (es.id_tipo_solicitud = ts.id_tipo_solicitud) "
                    + "where "
                    + "cpd.rechazado=0 and "
                    + "cpd.id_estado_solicitud=" + this.id_estado_solicitud + " and "
                    + "cpd.id_tipo_solicitud=" + this.id_tipo_solicitud + " "
                    + "order by "
                    + "cpd.id_comeval_promocion_docente";
            
            this.lst_comeval_promocion_docente = new ArrayList<>();
            servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            String jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            Type listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            List<String> lista_drive = new Gson().fromJson(jsonString, listType);
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] col = lista_drive.get(i).split("♣");
                Tbl_Comeval_Promocion_Docente tbl_comeval_promocion_docente = new Tbl_Comeval_Promocion_Docente(Long.parseLong(col[0]), col[1], dateFormat1.parse(col[2]), col[3], col[4]);
                this.lst_comeval_promocion_docente.add(tbl_comeval_promocion_docente);
            }
            
            if(this.id_estado_solicitud == Long.parseLong("1") || this.id_estado_solicitud == Long.parseLong("2") || this.id_estado_solicitud == Long.parseLong("3")) {
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
            if (this.sel_comeval_promocion_docente != null) {
                List<entidad.Comeval_Promocion_Docente> lst_comeval_promocion_docente_temp = new ArrayList<>();
                
                String cadenasql = "select "
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
                        + "t.revision_secretario_academico "
                        + "from "
                        + "comeval_promocion_docente t "
                        + "where "
                        + "t.id_comeval_promocion_docente=" + this.sel_comeval_promocion_docente.getId_comeval_promocion_docente();
                servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                String jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
                Type listType = new TypeToken<ArrayList<String>>() {
                }.getType();
                List<String> lista_drive = new Gson().fromJson(jsonString, listType);
                for (Integer i = 1; i < lista_drive.size(); i++) {
                    String[] col = lista_drive.get(i).split("♣");
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                    entidad.Comeval_Promocion_Docente comeval_promocion_docente = new entidad.Comeval_Promocion_Docente(
                            Long.parseLong(col[0]),
                            col[1],
                            Long.parseLong(col[2]),
                            col[3],
                            dateFormat2.format(dateFormat1.parse(col[4])),
                            col[5],
                            dateFormat2.format(dateFormat1.parse(col[6])),
                            this.usuario.getUsuario(),
                            col[7],
                            Long.parseLong(col[8]),
                            Long.parseLong(col[9]),
                            Long.parseLong(col[10]),
                            Long.parseLong(col[8]), // id_estado_solicitud_rechazado
                            Long.parseLong(col[9]), // id_tipo_solicitud_rechazado
                            Long.parseLong(col[11]),
                            Long.parseLong(col[12]),
                            null,
                            null,
                            null);
                    lst_comeval_promocion_docente_temp.add(comeval_promocion_docente);
                }

                cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                String resultado = "";
                if (this.id_estado_solicitud == Long.parseLong("1")) {
                    resultado = cliente_api_comeval_rest.promocion_docente_enviar_ingreso_docente(lst_comeval_promocion_docente_temp);
                }
                if (this.id_estado_solicitud == Long.parseLong("2")) {
                    resultado = cliente_api_comeval_rest.promocion_docente_enviar_ingreso_comeval(lst_comeval_promocion_docente_temp);
                }
                if (this.id_estado_solicitud == Long.parseLong("3")) {
                    resultado = cliente_api_comeval_rest.promocion_docente_enviar_ingreso_secretario_academico(lst_comeval_promocion_docente_temp);
                }
                if (this.id_estado_solicitud == Long.parseLong("4")) {
                    resultado = cliente_api_comeval_rest.promocion_docente_enviar_revision_comeval(lst_comeval_promocion_docente_temp);
                }
                if (this.id_estado_solicitud == Long.parseLong("5")) {
                    resultado = cliente_api_comeval_rest.promocion_docente_enviar_revision_secretario_academico(lst_comeval_promocion_docente_temp);
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

    public List<Tbl_Comeval_Promocion_Docente> getLst_comeval_promocion_docente() {
        return lst_comeval_promocion_docente;
    }

    public void setLst_comeval_promocion_docente(List<Tbl_Comeval_Promocion_Docente> lst_comeval_promocion_docente) {
        this.lst_comeval_promocion_docente = lst_comeval_promocion_docente;
    }

    public Tbl_Comeval_Promocion_Docente getSel_comeval_promocion_docente() {
        return sel_comeval_promocion_docente;
    }

    public void setSel_comeval_promocion_docente(Tbl_Comeval_Promocion_Docente sel_comeval_promocion_docente) {
        this.sel_comeval_promocion_docente = sel_comeval_promocion_docente;
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
