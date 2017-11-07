package org.demoiselle.forum.service;

import io.swagger.annotations.Api;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.ok;
import org.demoiselle.forum.dao.UserDAO;
import org.demoiselle.forum.security.Credentials;
import org.demoiselle.jee.security.annotation.Authenticated;

/**
 *
 * @author PauloGladson
 */
@Api("Auth")
@Path("auth")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class AuthREST {

    @Inject
    private UserDAO dao;

    /**
     *
     * @param credentials
     * @return
     */
    @POST
    public Response login(Credentials credentials) {
        return ok().entity(dao.login(credentials).toString()).build();
    }

    /**
     *
     * @return
     */
    @GET
    @Authenticated
    public Response retoken() {
        return ok().entity(dao.retoken().toString()).build();
    }

    /**
     *
     * @param credentials
     */
    @POST
    @Path("register")
    public void register(Credentials credentials) {
        dao.register(credentials);
    }

    /**
     *
     * @param credentials
     */
    @POST
    @Path("amnesia")
    public void amnesia(Credentials credentials) {
        dao.amnesia(credentials);
    }

    private static final Logger LOG = Logger.getLogger(AuthREST.class.getName());
}
