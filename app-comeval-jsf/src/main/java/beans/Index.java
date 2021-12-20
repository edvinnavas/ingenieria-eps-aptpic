package beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entidad.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;

@Named(value = "Index")
@SessionScoped
public class Index implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String txt_usuario;
    private String txt_contrasena;
    private Long id_rol;
    private Long id_unidad;
    private List<SelectItem> lst_rol;
    private List<SelectItem> lst_unidad;
    private entidad.Usuario usuario; 
    
    public Index() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            
            this.txt_usuario = "";
            this.txt_contrasena = "";
            
            this.lst_rol = new ArrayList<>();
            String cadenasql = "select r.rol, r.nombre from rol r order by r.nombre";
            servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            String jsonString = cliente_api_comeval_rest.driver_comeval_gestionautenticacion2(cadenasql);
            Type listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            List<String> lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] rol = lista_drive.get(i).split("♣");
                this.lst_rol.add(new SelectItem(Long.parseLong(rol[0]), rol[1]));
            }
            this.id_rol = Long.parseLong("3");
            
            this.lst_unidad = new ArrayList<>();
            cadenasql = "select u.unidad, u.nombre from unidad u order by u.nombre";
            cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            jsonString = cliente_api_comeval_rest.driver_comeval_gestionautenticacion2(cadenasql);
            listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            lista_drive = new Gson().fromJson(jsonString, listType);
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] rol = lista_drive.get(i).split("♣");
                this.lst_unidad.add(new SelectItem(Long.parseLong(rol[0]), rol[1]));
            }
            this.id_unidad = Long.parseLong("46");
        } catch(Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Index ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }
    
    public String login() {
        String resultado = "index";

        try {
            servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            String ws_respuesta = cliente_api_comeval_rest.autenticar(this.txt_usuario, this.txt_contrasena, this.id_rol, this.id_unidad);

            String[] mensaje = ws_respuesta.split(",");
            if (mensaje[0].equals("1")) {
                resultado = "index";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", mensaje[1]));
            } else {
                if (mensaje[0].equals("2")) {
                    resultado = "index";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", mensaje[1]));
                } else {
                    Type listType = new TypeToken<entidad.Usuario>() {
                    }.getType();
                    this.usuario = new Gson().fromJson(ws_respuesta, listType);

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", ws_respuesta));
                    resultado = "menu";
                }
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: login ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
            resultado = "index";
        }

        return resultado;
    }

    public String getTxt_usuario() {
        return txt_usuario;
    }

    public void setTxt_usuario(String txt_usuario) {
        this.txt_usuario = txt_usuario;
    }

    public String getTxt_contrasena() {
        return txt_contrasena;
    }

    public void setTxt_contrasena(String txt_contrasena) {
        this.txt_contrasena = txt_contrasena;
    }

    public Long getId_rol() {
        return id_rol;
    }

    public void setId_rol(Long id_rol) {
        this.id_rol = id_rol;
    }

    public Long getId_unidad() {
        return id_unidad;
    }

    public void setId_unidad(Long id_unidad) {
        this.id_unidad = id_unidad;
    }

    public List<SelectItem> getLst_rol() {
        return lst_rol;
    }

    public void setLst_rol(List<SelectItem> lst_rol) {
        this.lst_rol = lst_rol;
    }

    public List<SelectItem> getLst_unidad() {
        return lst_unidad;
    }

    public void setLst_unidad(List<SelectItem> lst_unidad) {
        this.lst_unidad = lst_unidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
