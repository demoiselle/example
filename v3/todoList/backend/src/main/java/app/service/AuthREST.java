package app.service;

import app.dao.UserDAO;
import app.security.Credentials;
import io.swagger.annotations.Api;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.ok;
import org.demoiselle.jee.security.annotation.Authenticated;

/**
 *
 * @author SERPRO
 */
@Api("Auth")
@Path("auth")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class AuthREST {

    @Inject
    private UserDAO dao;

    @POST
    public Response login(Credentials credentials) {
        return ok().entity("{\"token\":\"" + dao.login(credentials) + "\"}").build();
    }

    @GET
    @Authenticated
    public Response retoken(@Suspended final AsyncResponse asyncResponse) {
        return ok().entity("{\"token\":\"" + dao.retoken() + "\"}").build();
    }
    private static final Logger LOG = Logger.getLogger(AuthREST.class.getName());

}
