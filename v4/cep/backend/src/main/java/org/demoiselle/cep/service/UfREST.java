package org.demoiselle.cep.service;

import org.demoiselle.cep.dao.CepDAO;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import jakarta.ws.rs.core.Response;
import static jakarta.ws.rs.core.Response.ok;
import org.demoiselle.jee.rest.annotation.CacheControl;

/**
 *
 * @author PauloGladson
 */
@Path("v1/ufs")
@Produces(value = {APPLICATION_JSON})
public class UfREST {

    private static final Logger LOG = getLogger(UfREST.class.getName());

    @Inject
    private CepDAO dao;

    /**
     *
     * @return
     */
    @GET
    @Transactional
    @CacheControl(value = "max-age=3600, must-revalidate, public")
    public Response findCep() {
        return ok().entity(dao.getListaUF()).build();
    }

}
