package app.service;

import app.entity.Cep;
import io.swagger.annotations.Api;
import javax.ejb.Asynchronous;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.crud.Search;
import org.demoiselle.jee.rest.annotation.CacheControl;

/**
 *
 * @author PauloGladson
 */
@Api("Cep")
@Path("v1/ceps")
@Produces(value = {MediaType.APPLICATION_JSON})
public class CepREST extends AbstractREST<Cep, Integer> {

    @GET
    @Transactional
    @Asynchronous
    @CacheControl(value = "max-age=3600, must-revalidate, public")
    @Search(fields = {"id", "cep", "logradouro", "cidade", "uf", "bairroIni", "bairroFim"})
    public void find(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(doFind());
    }

    private Result doFind() {
        return bc.find();
    }
}
