package beans.Comeval_Licencia_Docente;

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

@Named(value = "Bean_Lst_Comeval_Licencia_Docente")
@ViewScoped
public class Bean_Lst_Comeval_Licencia_Docente implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private entidad.Usuario usuario;
    private List<Tbl_Comeval_Licencia_Docente> lst_comeval_licencia_docente;
    private Tbl_Comeval_Licencia_Docente sel_comeval_licencia_docente;
    private Long id_estado_solicitud;
    private Long id_tipo_solicitud;
    
    private Boolean btnCrear;
    
    public Bean_Lst_Comeval_Licencia_Docente() {
        try {
            this.lst_comeval_licencia_docente = new ArrayList<>();
        } catch(Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Bean_Lst_Comeval_Licencia_Docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }
    
    public void cargar_datos(entidad.Usuario usuario, Long id_estado_solicitud, Long id_tipo_solicitud) {
        try {
            this.usuario = usuario;
            this.id_estado_solicitud = id_estado_solicitud;
            this.id_tipo_solicitud = id_tipo_solicitud;
            
            String cadenasql = "select "
                    + "cld.id_comeval_licencia_docente id_solicitud, "
                    + "cld.personal id_personal, "
                    + "cld.fecha_ingreso fecha_ingreso, "
                    + "es.nombre estado_solicitud, "
                    + "ts.nombre tipo_solicitud "
                    + "from "
                    + "comeval_licencia_docente cld "
                    + "left join estado_solicitud_comeval es on (cld.id_estado_solicitud = es.id_estado_solicitud and cld.id_tipo_solicitud = es.id_tipo_solicitud) "
                    + "left join tipo_solicitud_comeval ts on (es.id_tipo_solicitud = ts.id_tipo_solicitud) "
                    + "where "
                    + "cld.rechazado=0 and "
                    + "cld.id_estado_solicitud=" + this.id_estado_solicitud + " and "
                    + "cld.id_tipo_solicitud=" + this.id_tipo_solicitud + " "
                    + "order by "
                    + "cld.id_comeval_licencia_docente";
            
            this.lst_comeval_licencia_docente = new ArrayList<>();
            servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            String jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
            Type listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            List<String> lista_drive = new Gson().fromJson(jsonString, listType);
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] col = lista_drive.get(i).split("♣");
                Tbl_Comeval_Licencia_Docente tbl_comeval_licencia_docente = new Tbl_Comeval_Licencia_Docente(Long.parseLong(col[0]), col[1], dateFormat1.parse(col[2]), col[3], col[4]);
                this.lst_comeval_licencia_docente.add(tbl_comeval_licencia_docente);
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
        /* try {
            if (this.sel_comeval_licencia_docente != null) {
                servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                String resultado = cliente_api_comeval_rest.siguiente_estado_solicitud(this.sel_comeval_licencia_docente.getId_comeval_licencia_docente(), this.id_estado_solicitud, this.id_tipo_solicitud);
                this.cargar_datos(this.usuario, this.id_estado_solicitud, this.id_tipo_solicitud);

                String[] mensaje = resultado.split(",");
                if (mensaje[0].equals("0")) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", mensaje[1]));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", mensaje[1]));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje del sistema...", "Debe seleccionar una solicitud."));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: siguiente_estado_solicitud ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        } */
    }

    public entidad.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(entidad.Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Tbl_Comeval_Licencia_Docente> getLst_comeval_licencia_docente() {
        return lst_comeval_licencia_docente;
    }

    public void setLst_comeval_licencia_docente(List<Tbl_Comeval_Licencia_Docente> lst_comeval_licencia_docente) {
        this.lst_comeval_licencia_docente = lst_comeval_licencia_docente;
    }

    public Tbl_Comeval_Licencia_Docente getSel_comeval_licencia_docente() {
        return sel_comeval_licencia_docente;
    }

    public void setSel_comeval_licencia_docente(Tbl_Comeval_Licencia_Docente sel_comeval_licencia_docente) {
        this.sel_comeval_licencia_docente = sel_comeval_licencia_docente;
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
