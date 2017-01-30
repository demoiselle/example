/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.entity.LogLogradouro;
import io.swagger.annotations.Api;
import javax.ejb.Asynchronous;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.crud.Search;
import org.demoiselle.jee.rest.annotation.CacheControl;
import org.demoiselle.jee.rest.exception.DemoiselleRestException;

/**
 *
 * @author gladson
 */
@Api("Cep")
@Path("cep")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class CepFacadeREST extends AbstractREST<LogLogradouro, Integer> {

    @Inject
    private BaseInMemory dao;

    @GET
    @Asynchronous
    @Path(value = "{cep}")
    @CacheControl(value = "max-age=259200000")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public void findCep(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "cep") final String id) {
        asyncResponse.resume(doFindCep(id));
    }

    private Response doFindCep(@PathParam("cep") String id) {
        return Response.ok().entity(dao.getCep(id)).build();
    }

    @GET
    @Transactional
    @CacheControl(value = "max-age=259200000")
    public void find(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(doFind());
    }

    private Response doFind() {
        return Response.ok().entity(bc.find()).build();
    }
}
