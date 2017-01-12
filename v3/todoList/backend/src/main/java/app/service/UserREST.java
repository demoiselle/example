/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.entity.User;
import app.message.TodoMessage;
import io.swagger.annotations.Api;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.core.api.security.DemoiselleUser;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.rest.exception.DemoiselleRestException;
import org.demoiselle.jee.security.annotation.Authenticated;
import org.demoiselle.jee.security.annotation.RequiredRole;

/**
 *
 * @author gladson
 */
@Authenticated
@Api("User")
@Path("user")
public class UserREST extends AbstractREST<User, String> {

    private static final Logger LOG = getLogger(UserREST.class.getName());

    @Inject
    private DemoiselleUser dml;

    private TodoMessage message;

    @POST
    @Override
    @Transactional
    @Authenticated(enable = false)
    public User persist(User entity) {
        return bc.persist(entity);
    }

    @PUT
    @Override
    @Transactional
    public User merge(User entity) {
        if (entity.getId().equalsIgnoreCase(dml.getIdentity())) {
            return bc.merge(entity);
        } else {
            throw new DemoiselleRestException(message.onlyOwner(), 401);
        }
    }

    @GET
    @Override
    @Path("{id}")
//    @OnlyOwner(field = "id")
    @Transactional
    public User find(@PathParam("id") final String id) {
        if (id.equalsIgnoreCase(dml.getIdentity())) {
            return bc.find(id);
        } else {
            throw new DemoiselleRestException(message.onlyOwner(), 401);
        }
    }

    @GET
    @Override
    @Transactional
    @RequiredRole("Administrador")
    public Result find() {
        return bc.find();
    }

    @DELETE
    @Override
    @Path("{id}")
    @Transactional
    public void remove(@PathParam("id") final String id) {
        if (id.equalsIgnoreCase(dml.getIdentity())) {
            bc.remove(id);
        } else {
            throw new DemoiselleRestException(message.onlyOwner(), 401);
        }
    }

}
//import org.demoiselle.jee.security.annotation.OnlyOwner;
