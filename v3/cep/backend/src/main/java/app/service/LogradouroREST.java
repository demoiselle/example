package app.service;

import app.dao.CepDAO;
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
@Api("Logradouro")
@Path("v1/logradouros")
@Produces(value = {APPLICATION_JSON})
public class LogradouroREST {

    private static final Logger LOG = getLogger(LogradouroREST.class.getName());

    @Inject
    private CepDAO dao;

    /**
     *
     * @param asyncResponse
     * @param uf
     * @param nome
     */
    @GET
    @Transactional
    @Path(value = "{uf}/{nome}")
    @CacheControl(value = "max-age=36000, must-revalidate, public")
    public void findLogradouro(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "uf") final String uf, @PathParam(value = "nome") final String nome) {
        asyncResponse.resume(doFindLogradouro(uf, nome));
    }

    private Response doFindLogradouro(String uf, String nome) {
        return ok().entity(dao.getListaLogradouro(uf, nome)).build();
    }

}
