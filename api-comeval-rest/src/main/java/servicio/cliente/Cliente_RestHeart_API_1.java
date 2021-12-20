package servicio.cliente;

import java.io.InputStream;
import java.io.Serializable;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class Cliente_RestHeart_API_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String BASE_URI = "http://172.16.72.12:8484/procesos_titulares";
    private HttpAuthenticationFeature feature;
    private ClientConfig clientConfig;
    private Client client;

    public Cliente_RestHeart_API_1(String usuario, String pass) {
        try {
            this.feature = HttpAuthenticationFeature.basic(usuario, pass);
            this.clientConfig = new ClientConfig();
            this.clientConfig.register(this.feature);
            this.clientConfig.register(String.class);
            this.client = ClientBuilder.newClient(this.clientConfig);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - Cliente_Api_Comeval_Rest_1):" + ex.toString());
        }
    }
    
    public InputStream descargar_adjunto_binary(String id_adjunto) {
        InputStream resultado;
        
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("procesos_titulares.files/" + id_adjunto + "/binary");
            // MediaType.APPLICATION_OCTET_STREAM_TYPE
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
            Response response = invocationBuilder.get();
            if(response.getStatus() == 200) {
                resultado = response.readEntity(InputStream.class);
            } else {
                resultado = null;
            }
        } catch(Exception ex) {
            resultado = null;
            System.out.println("1,ERROR (" + this.getClass().getName() + " - login):" + ex.toString());
        }
        
        return resultado;
    }
    
}
