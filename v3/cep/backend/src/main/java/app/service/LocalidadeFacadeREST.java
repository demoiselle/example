/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.bc.LocalidadeBC;
import app.entity.LogLocalidade;
import io.swagger.annotations.Api;
import javax.ejb.Asynchronous;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.crud.Search;
import org.demoiselle.jee.rest.annotation.CacheControl;

/**
 *
 * @author gladson
 */
@Api("Localidade")
@Path("localidade")
public class LocalidadeFacadeREST extends AbstractREST<LogLocalidade, Integer> {

    @GET
    @Asynchronous
    @Search(withPagination = false, fields = {"locNuSequencial", "ufeSg", "locNo", "cep"})
    @CacheControl(value = "max-age=259200000")
    public void listLocalidade(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(doListLocalidade());
    }

    private Response doListLocalidade() {
        return Response.ok().entity(bc.find()).build();
    }

    @GET
    @Path("uf/{uf}")
    @Asynchronous
    @CacheControl(value = "max-age=259200000")
    public void listLocalidadePorUf(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "uf") final String uf) {
        asyncResponse.resume(doListLocalidadePorUf(uf));
    }

    private Response doListLocalidadePorUf(String uf) {
        return Response.ok().entity(((LocalidadeBC) bc).findByUf(uf)).build();
    }
}
