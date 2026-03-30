package app.service;

import app.constants.Perfil;
import app.constants.Status;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import jakarta.ws.rs.core.Response;
import static jakarta.ws.rs.core.Response.ok;
import org.demoiselle.jee.rest.annotation.CacheControl;

@Path("v1/constants")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ConstantsREST {

    @GET
    @Path("perfil")
    @CacheControl(value = "max-age=86400")
    public Response getPerfil() {
        return ok().entity(Perfil.getMap()).build();
    }

    @GET
    @Path("status")
    @CacheControl(value = "max-age=86400")
    public Response getStatus() {
        return ok().entity(Status.getMap()).build();
    }
}
