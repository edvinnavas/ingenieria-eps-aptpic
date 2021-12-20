package servicio;

import control.Ctrl_Comeval_Amonestacion_Docente;
import control.Ctrl_Comeval_Ampliacion_Horario;
import control.Ctrl_Comeval_Cambio_Horario;
import control.Ctrl_Comeval_Carga_Eval_Comeval;
import control.Ctrl_Comeval_Promocion_Docente;
import control.Ctrl_Comeval_Licencia_Docente;
import control.Ctrl_Driver;
import java.io.Serializable;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("servicio")
public class Servicios implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String jndi_gestionautenticacion2 = "jndi_gestionautenticacion2";
    private final String jndi_asuntosestudiantiles = "jndi_asuntosestudiantiles";
    private final String jndi_personal2 = "jndi_personal2";

    @GET
    @Path("ping")
    public Response ping() {
        return Response.ok("ping").build();
    }

    @POST
    @Path("driver-comeval-gestionautenticacion2")
    public String driver_comeval_gestionautenticacion2(String cadenasql) {
        String resultado;

        try {
            Ctrl_Driver ctrl_driver = new Ctrl_Driver(this.jndi_gestionautenticacion2);
            resultado = ctrl_driver.driver_comeval(cadenasql);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - driver_comeval_gestionautenticacion2):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - driver_comeval_gestionautenticacion2):" + ex.toString();
        }

        return resultado;
    }

    @POST
    @Path("driver-comeval-personal2")
    public String driver_comeval_personal2(String cadenasql) {
        String resultado;

        try {
            Ctrl_Driver ctrl_driver = new Ctrl_Driver(this.jndi_personal2);
            resultado = ctrl_driver.driver_comeval(cadenasql);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - driver_comeval_personal2):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - driver_comeval_personal2):" + ex.toString();
        }

        return resultado;
    }

    @GET
    @Path("autenticar/{usuario}/{contrasena}/{id_rol}/{id_unidad}")
    public String autenticar(
            @PathParam("usuario") String usuario,
            @PathParam("contrasena") String contrasena,
            @PathParam("id_rol") Long id_rol,
            @PathParam("id_unidad") Long id_unidad) {

        String resultado;

        try {
            Ctrl_Driver ctrl_driver = new Ctrl_Driver(this.jndi_gestionautenticacion2);
            resultado = ctrl_driver.autenticar(usuario, contrasena, id_rol, id_unidad);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - autenticar):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - autenticar):" + ex.toString();
        }

        return resultado;
    }

    @POST
    @Path("promocion_docente_ingresar")
    public String promocion_docente_ingresar(String jsonString) {
        String resultado;

        try {
            Ctrl_Comeval_Promocion_Docente ctrl_comeval_promocion_docente = new Ctrl_Comeval_Promocion_Docente(this.jndi_personal2);
            resultado = ctrl_comeval_promocion_docente.promocion_docente_ingresar(jsonString);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_ingresar):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_ingresar):" + ex.toString();
        }

        return resultado;
    }

    @PUT
    @Path("promocion_docente_modificar")
    public String promocion_docente_modificar(String jsonString) {
        String resultado;

        try {
            Ctrl_Comeval_Promocion_Docente ctrl_comeval_promocion_docente = new Ctrl_Comeval_Promocion_Docente(this.jndi_personal2);
            resultado = ctrl_comeval_promocion_docente.promocion_docente_modificar(jsonString);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_modificar):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_modificar):" + ex.toString();
        }

        return resultado;
    }
    
    @PUT
    @Path("promocion_docente_enviar_ingreso_docente")
    public String promocion_docente_enviar_ingreso_docente(String jsonString) {
        String resultado;
        
        try {
            Ctrl_Comeval_Promocion_Docente ctrl_comeval_promocion_docente = new Ctrl_Comeval_Promocion_Docente(this.jndi_personal2);
            resultado = ctrl_comeval_promocion_docente.promocion_docente_enviar_ingreso_docente(jsonString);
        } catch(Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_docente):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_docente):" + ex.toString();
        }
        
        return resultado;
    }
    
    @PUT
    @Path("promocion_docente_enviar_ingreso_comeval")
    public String promocion_docente_enviar_ingreso_comeval(String jsonString) {
        String resultado;
        
        try {
            Ctrl_Comeval_Promocion_Docente ctrl_comeval_promocion_docente = new Ctrl_Comeval_Promocion_Docente(this.jndi_personal2);
            resultado = ctrl_comeval_promocion_docente.promocion_docente_enviar_ingreso_comeval(jsonString);
        } catch(Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_docente):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_docente):" + ex.toString();
        }
        
        return resultado;
    }
    
    @PUT
    @Path("promocion_docente_enviar_ingreso_secretario_academico")
    public String promocion_docente_enviar_ingreso_secretario_academico(String jsonString) {
        String resultado;
        
        try {
            Ctrl_Comeval_Promocion_Docente ctrl_comeval_promocion_docente = new Ctrl_Comeval_Promocion_Docente(this.jndi_personal2);
            resultado = ctrl_comeval_promocion_docente.promocion_docente_enviar_ingreso_secretario_academico(jsonString);
        } catch(Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_docente):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_docente):" + ex.toString();
        }
        
        return resultado;
    }
    
    @PUT
    @Path("promocion_docente_enviar_revision_comeval")
    public String promocion_docente_enviar_revision_comeval(String jsonString) {
        String resultado;
        
        try {
            Ctrl_Comeval_Promocion_Docente ctrl_comeval_promocion_docente = new Ctrl_Comeval_Promocion_Docente(this.jndi_personal2);
            resultado = ctrl_comeval_promocion_docente.promocion_docente_enviar_revision_comeval(jsonString);
        } catch(Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_docente):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_docente):" + ex.toString();
        }
        
        return resultado;
    }
    
    @PUT
    @Path("promocion_docente_enviar_revision_secretario_academico")
    public String promocion_docente_enviar_revision_secretario_academico(String jsonString) {
        String resultado;
        
        try {
            Ctrl_Comeval_Promocion_Docente ctrl_comeval_promocion_docente = new Ctrl_Comeval_Promocion_Docente(this.jndi_personal2);
            resultado = ctrl_comeval_promocion_docente.promocion_docente_enviar_revision_secretario_academico(jsonString);
        } catch(Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_docente):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_docente):" + ex.toString();
        }
        
        return resultado;
    }
    
    @PUT
    @Path("promocion_docente_acta_junta_directiva")
    public String promocion_docente_acta_junta_directiva(String jsonString) {
        String resultado;
        
        try {
            Ctrl_Comeval_Promocion_Docente ctrl_comeval_promocion_docente = new Ctrl_Comeval_Promocion_Docente(this.jndi_personal2);
            resultado = ctrl_comeval_promocion_docente.promocion_docente_acta_junta_directiva();
        } catch(Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_docente):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - promocion_docente_enviar_ingreso_docente):" + ex.toString();
        }
        
        return resultado;
    }

    @POST
    @Path("crear_licencia_docente")
    public String crear_licencia_docente(String jsonString) {
        String resultado;

        try {
            Ctrl_Comeval_Licencia_Docente ctrl_comeval_licencia_docente = new Ctrl_Comeval_Licencia_Docente(this.jndi_personal2);
            resultado = ctrl_comeval_licencia_docente.crear_licencia_docente(jsonString);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - crear_licencia_docente):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - crear_licencia_docente):" + ex.toString();
        }

        return resultado;
    }

    @PUT
    @Path("modificar_licencia_docente")
    public String modificar_licencia_docente(String jsonString) {
        String resultado;

        try {
            Ctrl_Comeval_Licencia_Docente ctrl_comeval_licencia_docente = new Ctrl_Comeval_Licencia_Docente(this.jndi_personal2);
            resultado = ctrl_comeval_licencia_docente.modificar_licencia_docente(jsonString);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_licencia_docente):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - modificar_licencia_docente):" + ex.toString();
        }

        return resultado;
    }

    @POST
    @Path("crear_amonestacion_docente")
    public String crear_amonestacion_docente(String jsonString) {
        String resultado;

        try {
            Ctrl_Comeval_Amonestacion_Docente ctrl_comeval_amonestacion_docente = new Ctrl_Comeval_Amonestacion_Docente(this.jndi_personal2);
            resultado = ctrl_comeval_amonestacion_docente.crear_amonestacion_docente(jsonString);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - crear_amonestacion_docente):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - crear_amonestacion_docente):" + ex.toString();
        }

        return resultado;
    }

    @PUT
    @Path("modificar_amonestacion_docente")
    public String modificar_amonestacion_docente(String jsonString) {
        String resultado;

        try {
            Ctrl_Comeval_Amonestacion_Docente ctrl_comeval_amonestacion_docente = new Ctrl_Comeval_Amonestacion_Docente(this.jndi_personal2);
            resultado = ctrl_comeval_amonestacion_docente.modificar_amonestacion_docente(jsonString);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_amonestacion_docente):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - modificar_amonestacion_docente):" + ex.toString();
        }

        return resultado;
    }

    @POST
    @Path("crear_cambio_horario")
    public String crear_cambio_horario(String jsonString) {
        String resultado;

        try {
            Ctrl_Comeval_Cambio_Horario ctrl_comeval_cambio_horario = new Ctrl_Comeval_Cambio_Horario(this.jndi_personal2);
            resultado = ctrl_comeval_cambio_horario.crear_cambio_horario(jsonString);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - crear_cambio_horario):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - crear_cambio_horario):" + ex.toString();
        }

        return resultado;
    }

    @PUT
    @Path("modificar_cambio_horario")
    public String modificar_cambio_horario(String jsonString) {
        String resultado;

        try {
            Ctrl_Comeval_Cambio_Horario ctrl_comeval_cambio_horario = new Ctrl_Comeval_Cambio_Horario(this.jndi_personal2);
            resultado = ctrl_comeval_cambio_horario.modificar_cambio_horario(jsonString);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_cambio_horario):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - modificar_cambio_horario):" + ex.toString();
        }

        return resultado;
    }

    @POST
    @Path("crear_ampliacion_horario")
    public String crear_ampliacion_horario(String jsonString) {
        String resultado;

        try {
            Ctrl_Comeval_Ampliacion_Horario ctrl_comeval_ampliacion_horario = new Ctrl_Comeval_Ampliacion_Horario(this.jndi_personal2);
            resultado = ctrl_comeval_ampliacion_horario.crear_ampliacion_horario(jsonString);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - crear_ampliacion_horario):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - crear_ampliacion_horario):" + ex.toString();
        }

        return resultado;
    }

    @PUT
    @Path("modificar_ampliacion_horario")
    public String modificar_ampliacion_horario(String jsonString) {
        String resultado;

        try {
            Ctrl_Comeval_Ampliacion_Horario ctrl_comeval_ampliacion_horario = new Ctrl_Comeval_Ampliacion_Horario(this.jndi_personal2);
            resultado = ctrl_comeval_ampliacion_horario.modificar_ampliacion_horario(jsonString);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_ampliacion_horario):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - modificar_ampliacion_horario):" + ex.toString();
        }

        return resultado;
    }

    @POST
    @Path("crear_carga_eval_comeval")
    public String crear_carga_eval_comeval(String jsonString) {
        String resultado;

        try {
            Ctrl_Comeval_Carga_Eval_Comeval ctrl_comeval_carga_eval_comeval = new Ctrl_Comeval_Carga_Eval_Comeval(this.jndi_personal2);
            resultado = ctrl_comeval_carga_eval_comeval.crear_carga_eval_comeval(jsonString);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - crear_carga_eval_comeval):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - crear_carga_eval_comeval):" + ex.toString();
        }

        return resultado;
    }

    @PUT
    @Path("modificar_carga_eval_comeval")
    public String modificar_carga_eval_comeval(String jsonString) {
        String resultado;

        try {
            Ctrl_Comeval_Carga_Eval_Comeval ctrl_comeval_carga_eval_comeval = new Ctrl_Comeval_Carga_Eval_Comeval(this.jndi_personal2);
            resultado = ctrl_comeval_carga_eval_comeval.modificar_carga_eval_comeval(jsonString);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - modificar_carga_eval_comeval):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - modificar_carga_eval_comeval):" + ex.toString();
        }

        return resultado;
    }
    
    @GET
    @Path("flujo_proceso/{id_solicitud}/{id_tipo_solicitud}/{id_estado_solicitud}")
    public String flujo_proceso(
            @PathParam("id_solicitud") Long id_solicitud,
            @PathParam("id_tipo_solicitud") Long id_tipo_solicitud,
            @PathParam("id_estado_solicitud") Long id_estado_solicitud) {

        String resultado;

        try {
            Ctrl_Driver ctrl_driver = new Ctrl_Driver(this.jndi_personal2);
            resultado = ctrl_driver.flujo_proceso(id_solicitud, id_tipo_solicitud, id_estado_solicitud);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - flujo_proceso):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - flujo_proceso):" + ex.toString();
        }

        return resultado;
    }

    @GET
    @Path("siguiente_estado_solicitud/{id_solicitud}/{id_estado_solicitud_actual}/{id_tipo_solicitud_actual}")
    public String siguiente_estado_solicitud(
            @PathParam("id_solicitud") Long id_solicitud,
            @PathParam("id_estado_solicitud_actual") Long id_estado_solicitud_actual,
            @PathParam("id_tipo_solicitud_actual") Long id_tipo_solicitud_actual) {

        String resultado;

        try {
            Ctrl_Driver ctrl_driver = new Ctrl_Driver(this.jndi_personal2);
            resultado = ctrl_driver.siguiente_estado_solicitud(id_solicitud, id_estado_solicitud_actual, id_tipo_solicitud_actual);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - siguiente_estado_solicitud):" + ex.toString());
            resultado = "1,ERROR (" + this.getClass().getName() + " - siguiente_estado_solicitud):" + ex.toString();
        }

        return resultado;
    }

}
