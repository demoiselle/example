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
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import org.demoiselle.jee.rest.annotation.CacheControl;
import org.demoiselle.jee.security.annotation.Authenticated;

/**
 *
 * @author gladson
 */
@Api("Uf")
@Path("v1/ufs")
public class UFREST {

    @Inject
    private CepDAO dao;

    @GET
    @Asynchronous
//    @CacheControl(value = "max-age=8640")
    public void findCep(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(doFindUfs());
    }

    private Response doFindUfs() {
        return Response.ok().entity(dao.getUfs()).build();
    }

}
