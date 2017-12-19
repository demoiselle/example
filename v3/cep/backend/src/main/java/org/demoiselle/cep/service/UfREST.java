package org.demoiselle.cep.service;

import org.demoiselle.cep.dao.CepDAO;
import io.swagger.annotations.Api;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
@Api("UF")
@Path("v1/ufs")
@Produces(value = {APPLICATION_JSON})
public class UfREST {

    private static final Logger LOG = getLogger(UfREST.class.getName());

    @Inject
    private CepDAO dao;

    /**
     *
     * @param asyncResponse
     */
    @GET
    @Transactional
    @CacheControl(value = "max-age=3600, must-revalidate, public")
    public void findCep(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(doListaUF());
    }

    private Response doListaUF() {
        return ok().entity(dao.getListaUF()).build();
    }

}
