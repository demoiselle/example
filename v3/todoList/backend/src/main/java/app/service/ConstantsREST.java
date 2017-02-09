package app.service;

import app.constants.Perfil;
import io.swagger.annotations.Api;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.ok;
import org.demoiselle.jee.rest.annotation.CacheControl;

@Api("Constants")
@Path("constants")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ConstantsREST {

    @GET
    @Path("perfil")
    @CacheControl(value = "max-age=86400")
    public Response getPerfil() {
        return ok().entity(Perfil.values()).build();
    }

}
