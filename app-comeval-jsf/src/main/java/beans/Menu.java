package beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entidad.Usuario;
import javax.inject.Named;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;

@Named(value = "Menu")
@ViewScoped
public class Menu implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private entidad.Usuario usuario;
    private String pagina_seleccionada;
    private String id_usuario;
    private String nombre_usuario;
    private String rol_usuario;
    private String unidad_usuario;
    
    public Menu() {
        try {
            this.pagina_seleccionada = "Inicio/inicio";
        } catch(Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Menu ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }
    
    public void onload(entidad.Usuario usuario) {
        try {
            if (this.usuario == null) {
                this.usuario = usuario;

                String nombre_rol = "";
                String cadenasql = "select r.nombre from rol r where r.rol=" + this.usuario.getId_rol();
                servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                String jsonString = cliente_api_comeval_rest.driver_comeval_gestionautenticacion2(cadenasql);
                Type listType = new TypeToken<ArrayList<String>>() {
                }.getType();
                List<String> lista_drive = new Gson().fromJson(jsonString, listType);
                for (Integer i = 1; i < lista_drive.size(); i++) {
                    String[] nombres = lista_drive.get(i).split("♣");
                    nombre_rol = nombres[0];
                }
                
                String nombre_unidad = "";
                cadenasql = "select u.nombre from unidad u where u.unidad=" + this.usuario.getId_unidad();
                cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                jsonString = cliente_api_comeval_rest.driver_comeval_gestionautenticacion2(cadenasql);
                listType = new TypeToken<ArrayList<String>>() {
                }.getType();
                lista_drive = new Gson().fromJson(jsonString, listType);
                for (Integer i = 1; i < lista_drive.size(); i++) {
                    String[] nombres = lista_drive.get(i).split("♣");
                    nombre_unidad = nombres[0];
                }

                this.id_usuario = this.usuario.getUsuario();
                this.nombre_usuario = this.usuario.getNombre();
                this.rol_usuario = nombre_rol;
                this.unidad_usuario = nombre_unidad;
            }
        } catch (Exception ex) {
            try {
                System.out.println("CLASE: " + this.getClass().getName() + " METODO: onload ERROR: " + ex.toString());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));

                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (Exception ex1) {
                System.out.println("CLASE: " + this.getClass().getName() + " METODO: onload Exception ex1 ERROR: " + ex1.toString());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
            }
        }
    }
    
    public Boolean mostrar_menu(String menu) {
        Boolean resultado = false;
        
        try {
//            String cadenasql = "select t.id_estado_solicitud from estado_solicitud_comeval t where t.nombre='" + menu + "' and t.id_rol=" + this.usuario.getId_rol();
//            servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
//            String jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
//            Type listType = new TypeToken<ArrayList<String>>() {
//            }.getType();
//            List<String> lista_drive = new Gson().fromJson(jsonString, listType);
//            for (Integer i = 1; i < lista_drive.size(); i++) {
//                String[] rol = lista_drive.get(i).split("♣");
//                resultado = true;
//            }
            resultado = true;
        } catch(Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: mostrar_menu ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
        
        return resultado;
    }
    
    public void Salir() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch(Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Salir ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public String getPagina_seleccionada() {
        return pagina_seleccionada + ".xhtml";
    }

    public void setPagina_seleccionada(String pagina_seleccionada) {
        this.pagina_seleccionada = pagina_seleccionada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getRol_usuario() {
        return rol_usuario;
    }

    public void setRol_usuario(String rol_usuario) {
        this.rol_usuario = rol_usuario;
    }

    public String getUnidad_usuario() {
        return unidad_usuario;
    }

    public void setUnidad_usuario(String unidad_usuario) {
        this.unidad_usuario = unidad_usuario;
    }
    
}
