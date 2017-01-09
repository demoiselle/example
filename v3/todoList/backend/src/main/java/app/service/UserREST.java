/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import org.demoiselle.jee.persistence.crud.AbstractREST;
//import org.demoiselle.jee.security.annotation.OnlyOwner;
import org.demoiselle.jee.rest.annotation.ValidatePayload;
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

    @Inject
    private DemoiselleUser dml;

    @POST
    @Override
    @Transactional
    @ValidatePayload
    @Authenticated(enable = false)
    @ApiOperation(value = "persist entity")
    public User persist(User entity) {
        return bc.persist(entity);
    }

    @PUT
    @Override
    @Transactional
    @ValidatePayload
    @ApiOperation(value = "full update entity")
    public User merge(User entity) {
        if (entity.getId().equalsIgnoreCase(dml.getIdentity())) {
            return bc.merge(entity);
        } else {
            throw new DemoiselleRestException("Você só pode alterar seu próprio dado", 401);
        }
    }

    @GET
    @Override
    @Path("{id}")
//    @OnlyOwner(field = "id")
    @Transactional
    @ApiOperation(value = "find by ID")
    public User find(@PathParam("id") final String id) {
        if (id.equalsIgnoreCase(dml.getIdentity())) {
            return bc.find(id);
        } else {
            throw new DemoiselleRestException("Você só pode buscar seu próprio dado", 401);
        }
    }

    @GET
    @Override
    @Transactional
    @RequiredRole("Administrador")
    @ApiOperation(value = "list all entities with pagination filter and query")
    public Result find() {
        return bc.find();
    }

    @DELETE
    @Override
    @Path("{id}")
    @Transactional
    @ApiOperation(value = "remove entity")
    public void remove(@PathParam("id") final String id) {
        if (id.equalsIgnoreCase(dml.getIdentity())) {
            bc.remove(id);
        } else {
            throw new DemoiselleRestException("Você só pode apagar seu próprio dado", 401);
        }
    }

}
