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
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import org.demoiselle.jee.rest.annotation.CacheControl;

/**
 *
 * @author gladson
 */
@Api("Cidade")
@Path("v1/cidades")
public class CidadeREST {

    @Inject
    private CepDAO dao;

    @GET
    @Asynchronous
    @Path(value = "{uf}")
    @CacheControl(value = "max-age=3600, must-revalidate, public")
    public void findCidade(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "uf") final String uf) {
        asyncResponse.resume(doFindCidade(uf));
    }

    private Response doFindCidade(String uf) {
        return Response.ok().entity(dao.getCidades(uf)).build();
    }

}
