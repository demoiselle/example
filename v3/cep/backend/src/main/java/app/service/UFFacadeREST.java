/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.entity.LogFaixaUf;
import io.swagger.annotations.Api;
import javax.ejb.Asynchronous;
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
@Api("UF")
@Path("uf")
@Authenticated
public class UFFacadeREST extends AbstractREST<LogFaixaUf, String> {

    @GET
    @Asynchronous
    @Authenticated(enable = false)
    @CacheControl(value = "max-age=259200000")
    public void findUf(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(doFindUf());
    }

    private Response doFindUf() {
        return Response.ok().entity(bc.find()).build();
    }

    @GET
    @Asynchronous
    @Authenticated(enable = false)
    @Path(value = "{uf}")
    @CacheControl(value = "max-age=259200000")
    public void findUf(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "uf") final String uf) {
        asyncResponse.resume(doFindUf(uf));
    }

    private Response doFindUf(@PathParam("uf") String uf) {
        return Response.ok().entity(bc.find(uf)).build();
    }

}
