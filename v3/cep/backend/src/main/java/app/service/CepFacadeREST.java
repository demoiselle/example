/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.bc.LogradouroBC;
import app.entity.LogLogradouro;
import io.swagger.annotations.Api;
import javax.ejb.Asynchronous;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.rest.annotation.CacheControl;
import org.demoiselle.jee.security.annotation.Authenticated;

/**
 *
 * @author gladson
 */
@Api("Cep")
@Path("cep")
@Authenticated
public class CepFacadeREST extends AbstractREST<LogLogradouro, Integer> {

    @GET
    @Asynchronous
    @Path(value = "{cep}")
    @Authenticated(enable = false)
    @CacheControl(value = "max-age=864000")
    public void findCep(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "cep") final String id) {
        asyncResponse.resume(doFindCep(id));
    }

    private Response doFindCep(@PathParam("cep") String id) {
        return Response.ok().entity(((LogradouroBC) bc).getLogradouroCep(id)).build();
    }

    @GET
    @Asynchronous
    @Transactional
    @Authenticated(enable = false)
    @CacheControl(value = "max-age=864000")
    public void find(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(doFind());
    }

    private Response doFind() {
        return Response.ok().entity(bc.find()).build();
    }
}
