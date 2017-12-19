package org.demoiselle.cep.service;

import org.demoiselle.cep.dao.CepDAO;
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
@Api("Cidade")
@Path("v1/cidades")
@Produces(value = {APPLICATION_JSON})
public class CidadeREST {

    private static final Logger LOG = getLogger(CidadeREST.class.getName());

    @Inject
    private CepDAO dao;

    /**
     *
     * @param asyncResponse
     * @param uf
     */
    @GET
    @Transactional
    @Path(value = "{uf}")
    @CacheControl(value = "max-age=36000, must-revalidate, public")
    public void findCidade(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "uf") final String uf) {
        asyncResponse.resume(doFindCidade(uf));
    }

    private Response doFindCidade(String id) {
        return ok().entity(dao.getListaCidade(id)).build();
    }

}
