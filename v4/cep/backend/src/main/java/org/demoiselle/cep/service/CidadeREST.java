package org.demoiselle.cep.service;

import org.demoiselle.cep.dao.CepDAO;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import jakarta.ws.rs.core.Response;
import static jakarta.ws.rs.core.Response.ok;
import org.demoiselle.jee.rest.annotation.CacheControl;

/**
 *
 * @author PauloGladson
 */
@Path("v1/cidades")
@Produces(value = {APPLICATION_JSON})
public class CidadeREST {

    private static final Logger LOG = getLogger(CidadeREST.class.getName());

    @Inject
    private CepDAO dao;

    /**
     *
     * @param uf
     * @return
     */
    @GET
    @Transactional
    @Path(value = "{uf}")
    @CacheControl(value = "max-age=36000, must-revalidate, public")
    public Response findCidade(@PathParam(value = "uf") final String uf) {
        return ok().entity(dao.getListaCidade(uf)).build();
    }

}
