package servicio;

import java.io.Serializable;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("comeval")
public class Servicios implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
}
