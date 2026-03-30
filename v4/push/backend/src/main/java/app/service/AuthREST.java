package app.service;

import app.dao.UserDAO;
import app.security.Credentials;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import jakarta.ws.rs.core.Response;
import static jakarta.ws.rs.core.Response.ok;
import org.demoiselle.jee.security.annotation.Authenticated;

/**
 *
 * @author SERPRO
 */
@Path("auth")
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
    @Authenticated
    public Response retoken() {
        return ok().entity(dao.retoken().toString()).build();
    }

}
