package servicio.cliente;

import java.io.File;
import java.io.Serializable;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

public class Cliente_RestHeart_API implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String BASE_URI = "http://172.16.72.12:8484/expedientes_docs";
    private HttpAuthenticationFeature feature;
    private ClientConfig clientConfig;
    private Client client;

    public Cliente_RestHeart_API(String usuario, String pass) {
        try {
            this.feature = HttpAuthenticationFeature.basic(usuario, pass);
            this.clientConfig = new ClientConfig();
            this.clientConfig.register(this.feature);
            this.clientConfig.register(MultiPartFeature.class);
            this.client = ClientBuilder.newClient(this.clientConfig);
        } catch (Exception ex) {
            System.out.println("1,ERROR (" + this.getClass().getName() + " - Cliente_Api_Comeval_Rest):" + ex.toString());
        }
    }
    
    public String cargar_archivo(File archivo, String nombre_archivo) {
        String resultado = "";
        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("docx.files/" + nombre_archivo);

            MultiPart multiPart = new MultiPart();
            multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
            FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file", archivo, MediaType.APPLICATION_OCTET_STREAM_TYPE);
            multiPart.bodyPart(fileDataBodyPart);
            Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.entity(multiPart, multiPart.getMediaType()));

            System.out.println("***** RESPONSE STATUS: " + response.getStatus());
            if (response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch (Exception ex) {
            resultado = ex.toString();
            System.out.println("1,ERROR (" + this.getClass().getName() + " - driver_comeval):" + ex.toString());
        }

        return resultado;
    }
    
}
