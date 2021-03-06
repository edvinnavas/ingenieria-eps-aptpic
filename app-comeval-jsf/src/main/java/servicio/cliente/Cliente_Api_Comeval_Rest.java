package servicio.cliente;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class Cliente_Api_Comeval_Rest implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String BASE_URI = "http://localhost:8080/api-comeval-rest/comeval/servicio";
    private HttpAuthenticationFeature feature;
    private ClientConfig clientConfig;
    private Client client;

    public Cliente_Api_Comeval_Rest(String usuario, String pass) {
        try {
            this.feature = HttpAuthenticationFeature.basic(usuario, pass);
            this.clientConfig = new ClientConfig();
            this.clientConfig.register(this.feature);
            this.clientConfig.register(String.class);
            this.client = ClientBuilder.newClient(this.clientConfig);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - Cliente_Api_Comeval_Rest):" + ex.toString());
        }
    }
    
    public String driver_comeval_gestionautenticacion2(String cadenasql) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("driver-comeval-gestionautenticacion2");
            String data = cadenasql;
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - driver_comeval_gestionautenticacion2):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String driver_comeval_personal2(String cadenasql) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("driver-comeval-personal2");
            String data = cadenasql;
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - driver_comeval_personal2):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String autenticar(String usuario, String contrasena, Long id_rol, Long id_unidad) {
        String resultado = "";
        
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("autenticar/" + usuario + "/" + contrasena + "/" + id_rol + "/" + id_unidad);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - autenticar):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String promocion_docente_ingresar(List<entidad.Comeval_Promocion_Docente> lst_comeval_promocion_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("promocion_docente_ingresar");
            String data = new Gson().toJson(lst_comeval_promocion_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_ingresar):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String promocion_docente_modificar(List<entidad.Comeval_Promocion_Docente> lst_comeval_promocion_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("promocion_docente_modificar");
            String data = new Gson().toJson(lst_comeval_promocion_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_modificar):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String promocion_docente_enviar_ingreso_docente(List<entidad.Comeval_Promocion_Docente> lst_comeval_promocion_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("promocion_docente_enviar_ingreso_docente");
            String data = new Gson().toJson(lst_comeval_promocion_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_docente):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String promocion_docente_enviar_ingreso_comeval(List<entidad.Comeval_Promocion_Docente> lst_comeval_promocion_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("promocion_docente_enviar_ingreso_comeval");
            String data = new Gson().toJson(lst_comeval_promocion_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_comeval):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String promocion_docente_enviar_ingreso_secretario_academico(List<entidad.Comeval_Promocion_Docente> lst_comeval_promocion_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("promocion_docente_enviar_ingreso_secretario_academico");
            String data = new Gson().toJson(lst_comeval_promocion_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_secretario_academico):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String promocion_docente_enviar_revision_comeval(List<entidad.Comeval_Promocion_Docente> lst_comeval_promocion_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("promocion_docente_enviar_revision_comeval");
            String data = new Gson().toJson(lst_comeval_promocion_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_revision_comeval):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String promocion_docente_enviar_revision_secretario_academico(List<entidad.Comeval_Promocion_Docente> lst_comeval_promocion_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("promocion_docente_enviar_revision_secretario_academico");
            String data = new Gson().toJson(lst_comeval_promocion_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_revision_secretario_academico):" + ex.toString());
        }
        
        return resultado;
    }
        
    public String licencia_docente_ingresar(List<entidad.Comeval_Licencia_Docente> lst_comeval_licencia_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("licencia_docente_ingresar");
            String data = new Gson().toJson(lst_comeval_licencia_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_ingresar):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String licencia_docente_modificar(List<entidad.Comeval_Licencia_Docente> lst_comeval_licencia_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("licencia_docente_modificar");
            String data = new Gson().toJson(lst_comeval_licencia_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_modificar):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String licencia_docente_enviar_ingreso_docente(List<entidad.Comeval_Licencia_Docente> lst_comeval_licencia_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("licencia_docente_enviar_ingreso_docente");
            String data = new Gson().toJson(lst_comeval_licencia_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_ingreso_docente):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String licencia_docente_enviar_ingreso_siif_visto_bueno_escuela(List<entidad.Comeval_Licencia_Docente> lst_comeval_licencia_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("licencia_docente_enviar_ingreso_siif_visto_bueno_escuela");
            String data = new Gson().toJson(lst_comeval_licencia_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_ingreso_siif_visto_bueno_escuela):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String licencia_docente_enviar_tipo_licencia_secretario_academico(List<entidad.Comeval_Licencia_Docente> lst_comeval_licencia_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("licencia_docente_enviar_tipo_licencia_secretario_academico");
            String data = new Gson().toJson(lst_comeval_licencia_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_tipo_licencia_secretario_academico):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String licencia_docente_enviar_acuerdo_decanatura(List<entidad.Comeval_Licencia_Docente> lst_comeval_licencia_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("licencia_docente_enviar_acuerdo_decanatura");
            String data = new Gson().toJson(lst_comeval_licencia_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_acuerdo_decanatura):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String licencia_docente_enviar_notificacion_tesoreria(List<entidad.Comeval_Licencia_Docente> lst_comeval_licencia_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("licencia_docente_enviar_notificacion_tesoreria");
            String data = new Gson().toJson(lst_comeval_licencia_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - licencia_docente_enviar_notificacion_tesoreria):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String amonestacion_docente_ingresar(List<entidad.Comeval_Amonestacion_Docente> lst_comeval_amonestacion_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("amonestacion_docente_ingresar");
            String data = new Gson().toJson(lst_comeval_amonestacion_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - amonestacion_docente_ingresar):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String amonestacion_docente_modificar(List<entidad.Comeval_Amonestacion_Docente> lst_comeval_amonestacion_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("amonestacion_docente_modificar");
            String data = new Gson().toJson(lst_comeval_amonestacion_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - amonestacion_docente_modificar):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String amonestacion_docente_enviar_ingreso_escuela(List<entidad.Comeval_Amonestacion_Docente> lst_comeval_amonestacion_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("amonestacion_docente_enviar_ingreso_escuela");
            String data = new Gson().toJson(lst_comeval_amonestacion_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - amonestacion_docente_enviar_ingreso_escuela):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String amonestacion_docente_enviar_visto_bueno_secretario_academico(List<entidad.Comeval_Amonestacion_Docente> lst_comeval_amonestacion_docente) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("amonestacion_docente_enviar_visto_bueno_secretario_academico");
            String data = new Gson().toJson(lst_comeval_amonestacion_docente);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - amonestacion_docente_enviar_visto_bueno_secretario_academico):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String cambio_horario_ingresar(List<entidad.Comeval_Cambio_Horario> lst_comeval_cambio_horario) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("cambio_horario_ingresar");
            String data = new Gson().toJson(lst_comeval_cambio_horario);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - crear_cambio_horario):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String cambio_horario_modificar(List<entidad.Comeval_Cambio_Horario> lst_comeval_cambio_horario) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("cambio_horario_modificar");
            String data = new Gson().toJson(lst_comeval_cambio_horario);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_cambio_horario):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String cambio_horario_enviar_ingreso_docente(List<entidad.Comeval_Cambio_Horario> lst_comeval_cambio_horario) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("cambio_horario_enviar_ingreso_docente");
            String data = new Gson().toJson(lst_comeval_cambio_horario);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_cambio_horario):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String cambio_horario_enviar_visto_bueno_escuela(List<entidad.Comeval_Cambio_Horario> lst_comeval_cambio_horario) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("cambio_horario_enviar_visto_bueno_escuela");
            String data = new Gson().toJson(lst_comeval_cambio_horario);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_cambio_horario):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String cambio_horario_enviar_visto_bueno_secretario(List<entidad.Comeval_Cambio_Horario> lst_comeval_cambio_horario) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("cambio_horario_enviar_visto_bueno_secretario");
            String data = new Gson().toJson(lst_comeval_cambio_horario);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_cambio_horario):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String ampliacion_horario_ingresar(List<entidad.Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("ampliacion_horario_ingresar");
            String data = new Gson().toJson(lst_comeval_ampliacion_horario);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_ingresar):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String ampliacion_horario_modificar(List<entidad.Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("ampliacion_horario_modificar");
            String data = new Gson().toJson(lst_comeval_ampliacion_horario);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_modificar):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String ampliacion_horario_enviar_ingreso_docente(List<entidad.Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("ampliacion_horario_enviar_ingreso_docente");
            String data = new Gson().toJson(lst_comeval_ampliacion_horario);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_ingreso_docente):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String ampliacion_horario_enviar_visto_bueno_escuela(List<entidad.Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("ampliacion_horario_enviar_visto_bueno_escuela");
            String data = new Gson().toJson(lst_comeval_ampliacion_horario);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_visto_bueno_escuela):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String ampliacion_horario_enviar_visto_bueno_secretario(List<entidad.Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("ampliacion_horario_enviar_visto_bueno_secretario");
            String data = new Gson().toJson(lst_comeval_ampliacion_horario);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_visto_bueno_secretario):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String ampliacion_horario_enviar_notificacion_secretaria(List<entidad.Comeval_Ampliacion_Horario> lst_comeval_ampliacion_horario) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("ampliacion_horario_enviar_notificacion_secretaria");
            String data = new Gson().toJson(lst_comeval_ampliacion_horario);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - ampliacion_horario_enviar_notificacion_secretaria):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String notas_evaluacion_docente_ingresar(List<entidad.Comeval_Notas_Evaluacion_Docente> lst_comeval_carga_eval_comeval) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("notas_evaluacion_docente_ingresar");
            String data = new Gson().toJson(lst_comeval_carga_eval_comeval);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - crear_carga_eval_comeval):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String notas_evaluacion_docente_modificar(List<entidad.Comeval_Notas_Evaluacion_Docente> lst_comeval_carga_eval_comeval) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("notas_evaluacion_docente_modificar");
            String data = new Gson().toJson(lst_comeval_carga_eval_comeval);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_carga_eval_comeval):" + ex.toString());
        }
        
        return resultado;
    }
    
    public String notas_evaluacion_enviar_ingreso_secretario(List<entidad.Comeval_Notas_Evaluacion_Docente> lst_comeval_carga_eval_comeval) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("notas_evaluacion_enviar_ingreso_secretario");
            String data = new Gson().toJson(lst_comeval_carga_eval_comeval);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.put(Entity.text(data));
            if(response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch(Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_carga_eval_comeval):" + ex.toString());
        }
        
        return resultado;
    }
    
}
