package org.demoiselle.livraria.service;

import io.swagger.annotations.Api;
import org.demoiselle.livraria.dao.UserDAO;
import org.demoiselle.livraria.security.Credentials;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.ok;
import org.demoiselle.jee.security.annotation.Authenticated;
import org.demoiselle.livraria.security.UserRegister;
import org.demoiselle.livraria.entity.User;

@Api("Auth")
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

    @POST
    @Transactional
    @Path("register")
    public Response register(UserRegister user) {
        return ok().entity(dao.register(user).toString()).build();
    }

    @GET
    @Authenticated
    public Response retoken() {
        return ok().entity(dao.retoken().toString()).build();
    }

}
