package org.demoiselle.livraria.service;

import org.demoiselle.livraria.dao.UserDAO;
import org.demoiselle.livraria.security.Credentials;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import jakarta.ws.rs.core.Response;
import static jakarta.ws.rs.core.Response.ok;
import org.demoiselle.jee.security.annotation.Authenticated;
import org.demoiselle.livraria.security.UserRegister;
import org.demoiselle.livraria.entity.User;

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
