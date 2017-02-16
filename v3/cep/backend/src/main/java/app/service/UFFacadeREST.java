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
@Api("UF")
@Path("uf")
public class UFFacadeREST extends AbstractREST<LogFaixaUf, String> {

    @GET
    @Asynchronous
    @Search(withPagination = false, fields = {"ufeSg", "ufeNo"})
    @CacheControl(value = "max-age=86400")
    public void findUf(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(doFindUf());
    }

    private Response doFindUf() {
        return Response.ok().entity(bc.find()).build();
    }

}
