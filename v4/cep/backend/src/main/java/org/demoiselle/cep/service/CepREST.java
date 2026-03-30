package org.demoiselle.cep.service;

import org.demoiselle.cep.entity.Cep;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.crud.Search;
import org.demoiselle.jee.rest.annotation.CacheControl;

/**
 *
 * @author PauloGladson
 */
@Path("v1/ceps")
@Produces(value = {APPLICATION_JSON})
public class CepREST extends AbstractREST<Cep, Integer> {

    private static final Logger LOG = getLogger(CepREST.class.getName());

    /**
     *
     * @return
     */
    @GET
    @Transactional
    @CacheControl(value = "max-age=3600, must-revalidate, public")
    @Search(fields = {"id", "cep", "logradouro", "cidade", "uf", "bairroIni", "bairroFim"})
    public Result find() {
        return bc.find();
    }
}
