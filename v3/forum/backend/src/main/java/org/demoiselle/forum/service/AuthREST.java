package org.demoiselle.forum.service;


import io.swagger.annotations.Api;
import org.demoiselle.forum.dao.UserDAO;
import org.demoiselle.forum.security.Credentials;
import org.demoiselle.forum.security.Social;
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

/**
 *
 * @author 70744416353
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
    @Transactional
    public Response login(Credentials credentials) {
        return ok().entity(dao.login(credentials).toString()).build();
    }

    /**
     *
     * @return
     */
    @GET
    @Transactional
    @Authenticated
    public Response retoken() {
        return ok().entity(dao.retoken().toString()).build();
    }

    /**
     *
     * @param credentials
     */
    @POST
    @Transactional
    @Path("register")
    public void register(Credentials credentials) {
        dao.register(credentials);
    }

    /**
     *
     * @param credentials
     */
    @POST
    @Transactional
    @Path("amnesia")
    public void amnesia(Credentials credentials) {
        dao.amnesia(credentials);
    }
    
    /**
     *
     * @param social
     * @return
     */
    @POST
    @Transactional
    @Path("social")
    public Response social(Social social) {
        return ok().entity(dao.social(social).toString()).build();
    }

    /**
     *
     * @param fingerprint
     * @return
     */
    @POST
    @Transactional
    @Path("fingerprint")
    public Response fingerprint(String fingerprint) {
        dao.setFingerprint(fingerprint);
        return ok().entity("").build();
    }
}
