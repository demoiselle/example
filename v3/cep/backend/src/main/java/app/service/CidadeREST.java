/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.dao.CepDAO;
import io.swagger.annotations.Api;
import javax.ejb.Asynchronous;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.demoiselle.jee.rest.annotation.CacheControl;
import org.demoiselle.jee.security.annotation.Authenticated;

/**
 *
 * @author gladson
 */
@Api("Cidade")
@Path("v1/cidades")
@Produces(value = {MediaType.APPLICATION_JSON})
public class CidadeREST {

    @Inject
    private CepDAO dao;

    @GET
    @Asynchronous
    @Path(value = "{uf}")
    @CacheControl(value = "max-age=3600, must-revalidate")
    public void findCep(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "uf") final String id) {
        asyncResponse.resume(doFindCep(id));
    }

    private Response doFindCep(String id) {
        return Response.ok().entity(dao.getListaCidade(id)).build();
    }

}
