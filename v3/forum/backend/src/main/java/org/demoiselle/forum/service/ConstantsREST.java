package org.demoiselle.forum.service;

import io.swagger.annotations.Api;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.ok;
import org.demoiselle.forum.constants.Perfil;
import static org.demoiselle.forum.constants.Perfil.getMap;
import org.demoiselle.jee.rest.annotation.CacheControl;

/**
 *
 * @author PauloGladson
 */
@Api("v1/Constants")
@Path("v1/constants")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ConstantsREST {

    /**
     *
     * @return
     */
    @GET
    @Path("perfil")
    @CacheControl(value = "max-age=86400")
    public Response getPerfil() {
        return ok().entity(getMap()).build();
    }
    private static final Logger LOG = Logger.getLogger(ConstantsREST.class.getName());

}
