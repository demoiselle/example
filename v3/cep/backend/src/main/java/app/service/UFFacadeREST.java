/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import io.swagger.annotations.Api;
import javax.ejb.Asynchronous;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.demoiselle.jee.rest.annotation.CacheControl;

/**
 *
 * @author gladson
 */
@Api("UF")
@Path("uf")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class UFFacadeREST {

    @Inject
    private BaseInMemory dao;

    @GET
    @Asynchronous
    @CacheControl(value = "max-age=259200000")
    public void findUf(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(doFindUf());
    }

    private Response doFindUf() {
        return Response.ok().entity(dao.getRepoUf()).build();
    }

    @GET
    @Asynchronous
    @Path(value = "{uf}")
    @CacheControl(value = "max-age=259200000")
    public void findUf(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "uf") final String uf) {
        asyncResponse.resume(doFindUf(uf));
    }

    private Response doFindUf(@PathParam("uf") String uf) {
        return Response.ok().entity(dao.getUf(uf)).build();
    }
}
