package app.service;

import app.bc.CepBC;
import app.entity.Cep;
import io.swagger.annotations.Api;
import javax.ejb.Asynchronous;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    @Override
    @Transactional
    @Search(fields = {"id", "cep", "logradouro", "cidade", "uf", "bairroIni", "bairroFim"})
    public Result find() {
        return bc.find();
    }

}
