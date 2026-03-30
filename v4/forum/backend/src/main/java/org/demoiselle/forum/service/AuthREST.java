package org.demoiselle.forum.service;

import org.demoiselle.forum.dao.UserDAO;
import org.demoiselle.forum.security.Credentials;
import org.demoiselle.forum.security.Social;
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

/**
 *
 * @author 70744416353
 */
@Path("auth")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class AuthREST {

    @Inject
    private UserDAO dao;

    @POST
    @Transactional
    public Response login(Credentials credentials) {
        return ok().entity(dao.login(credentials).toString()).build();
    }

    @GET
    @Transactional
    @Authenticated
    public Response retoken() {
        return ok().entity(dao.retoken().toString()).build();
    }

    @POST
    @Transactional
    @Path("register")
    public void register(Credentials credentials) {
        dao.register(credentials);
    }

    @POST
    @Transactional
    @Path("amnesia")
    public void amnesia(Credentials credentials) {
        dao.amnesia(credentials);
    }

    @POST
    @Transactional
    @Path("social")
    public Response social(Social social) {
        return ok().entity(dao.social(social).toString()).build();
    }

    @POST
    @Transactional
    @Path("fingerprint")
    public Response fingerprint(String fingerprint) {
        dao.setFingerprint(fingerprint);
        return ok().entity("").build();
    }
}
