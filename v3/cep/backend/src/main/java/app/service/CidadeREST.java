package app.service;

import app.dao.CepDAO;
import io.swagger.annotations.Api;
import javax.ejb.Asynchronous;
import javax.inject.Inject;
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
 * @author PauloGladson
 */
@Api("Cidade")
@Path("v1/cidades")
@Produces(value = {MediaType.APPLICATION_JSON})
public class CidadeREST {

    @Inject
    private CepDAO dao;

    @GET
    @Asynchronous
    @Path(value = "{uf}")
    @CacheControl(value = "max-age=36000, must-revalidate, public")
    public void findCidade(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "uf") final String uf) {
        asyncResponse.resume(doFindCidade(uf));
    }

    private Response doFindCidade(String id) {
        return Response.ok().entity(dao.getListaCidade(id)).build();
    }

}
