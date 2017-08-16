package app.service;

import app.dao.CepDAO;
import io.swagger.annotations.Api;
import javax.ejb.Asynchronous;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
@Api("UF")
@Path("v1/ufs")
@Produces(value = {MediaType.APPLICATION_JSON})
public class UfREST {

    @Inject
    private CepDAO dao;

    @GET
    @Asynchronous
    @CacheControl(value = "max-age=3600, must-revalidate, public")
    public void findCep(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(doListaUF());
    }

    private Response doListaUF() {
        return Response.ok().entity(dao.getListaUF()).build();
    }

}
