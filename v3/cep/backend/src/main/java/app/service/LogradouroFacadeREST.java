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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.rest.annotation.CacheControl;
import org.demoiselle.jee.security.annotation.Authenticated;

/**
 *
 * @author gladson
 */
@Api("Logradouro")
@Path("logradouro")
@Authenticated
public class LogradouroFacadeREST extends AbstractREST<LogLogradouro, Integer> {

    @GET
    @Asynchronous
    @Authenticated(enable = false)
    @CacheControl(value = "max-age=86400")
    public void findLogradouro(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(doFindLogradouro());
    }

    private Response doFindLogradouro() {
        return Response.ok().entity(bc.find()).build();
    }

}
