package app.service;

import app.dao.UserDAO;
import app.security.Credentials;
import io.swagger.annotations.Api;
import javax.ejb.Asynchronous;
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
@Api("v1/Auth")
@Path("v1/auth")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class AuthREST {

    @Inject
    private UserDAO dao;

    @POST
    public Response login(Credentials credentials) {
        return ok().entity(dao.login(credentials).toString()).build();
    }

    @GET
    @Asynchronous
    @Authenticated
    public void retoken(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(doRetoken());
    }

    private Response doRetoken() {
        return ok().entity(dao.retoken().toString()).build();
    }

}
