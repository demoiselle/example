/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.cep.service;

import io.swagger.annotations.Api;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.ok;
import org.demoiselle.jee.rest.annotation.CacheControl;

/**
 *
 * @author PauloGladson
 */
@Api("CNPJ")
@Path("v1/cnpjs")
@Produces(value = {APPLICATION_JSON})
public class CnpjREST {

    private static final Logger LOG = getLogger(CnpjREST.class.getName());

//    @Inject
//    private CnpjDAO dao;

    /**
     *
     * @param asyncResponse
     * @param cnpj
     */
//    @GET
//    @Transactional
//    @Path(value = "{cnpj}")
//    @CacheControl(value = "max-age=36000, must-revalidate, public")
//    public void findCnpj(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "cnpj") final String cnpj) {
//        asyncResponse.resume(doFindCnpj(cnpj));
//    }

//    private Response doFindCnpj(String cnpj) {
//        return ok().entity(dao.getListaCnpj(cnpj)).build();
//    }
}
