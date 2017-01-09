/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.dao.UserDAO;
import app.security.Credentials;
import io.swagger.annotations.Api;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
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
 * @author 70744416353
 */
@Api("Auth")
@Path("auth")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class AuthREST {

    private static final Logger LOG = getLogger(AuthREST.class.getName());

    @Inject
    private UserDAO dao;

    @POST
    @Asynchronous
    public void login(@Suspended final AsyncResponse asyncResponse, Credentials credentials) {
        asyncResponse.resume(doLogin(credentials));
    }

    private Response doLogin(Credentials credentials) {
        return ok().entity("{\"token\":\"" + dao.login(credentials) + "\"}").build();
    }

    @GET
    @Authenticated
    @Asynchronous
    public void retoken(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(doRetoken());
    }

    private Response doRetoken() {
        return ok().entity("{\"token\":\"" + dao.retoken() + "\"}").build();
    }

}
