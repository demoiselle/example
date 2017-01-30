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
@Api("Localidade")
@Path("localidade")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class LocalidadeFacadeREST {

    @Inject
    private BaseInMemory dao;

    @GET
    @Asynchronous
    @CacheControl(value = "max-age=259200000")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public void listLocalidade(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(doListLocalidade());
    }

    private Response doListLocalidade() {
        return Response.ok().entity(dao.getRepoLoca()).build();
    }

    @GET
    @Asynchronous
    @Path(value = "{nome}")
    @CacheControl(value = "max-age=259200000")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public void listLocalidade(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "nome") final String nome) {
        asyncResponse.resume(doListLocalidade(nome));
    }

    private Response doListLocalidade(String nome) {
        return Response.ok().entity(dao.getLocalidade(nome)).build();
    }

    @GET
    @Asynchronous
    @Path(value = "uf/{uf}")
    @CacheControl(value = "max-age=259200000")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public void listLocalidadeporuf(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "uf") final String nome) {
        asyncResponse.resume(doListLocalidadeporuf(nome));
    }

    private Response doListLocalidadeporuf(String nome) {
        return Response.ok().entity(dao.getLocalidadesFromUF(nome)).build();
    }

}
