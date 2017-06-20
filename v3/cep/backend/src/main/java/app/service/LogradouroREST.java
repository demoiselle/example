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
@Api("Logradouro")
@Path("v1/logradouro")
@Produces(value = {MediaType.APPLICATION_JSON})
public class LogradouroREST {

    @Inject
    private CepDAO dao;

    @GET
    @Asynchronous
    @Path(value = "{nome}")
    @CacheControl(value = "max-age=36000, must-revalidate, public")
    public void findLogradouro(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "nome") final String nome) {
        asyncResponse.resume(doFindLogradouro(nome));
    }

    private Response doFindLogradouro(String nome) {
        return Response.ok().entity(dao.getListaCidade(nome)).build();
    }

}
