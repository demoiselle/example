package org.demoiselle.biblia.service;

import org.demoiselle.biblia.entity.Versiculo;
import io.swagger.annotations.Api;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.demoiselle.biblia.bc.VersiculoBC;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.crud.Search;

@Api("v1/Versiculos")
@Path("v1/versiculos")
public class VersiculoREST extends AbstractREST< Versiculo, Integer> {

    @GET
    @Override
    @Transactional
    @Search(fields = {"*"}) // Escolha quais campos vão para o frontend Ex: {"id", "description"}
    public Result find() {
        return bc.find();
    }

    @GET
    @Transactional
    @Path("fulltextsearch/{texto}")
    public Response find(@PathParam("texto") final String texto) {
        return Response.ok().entity(((VersiculoBC) bc).listarVersiculosFTS(texto)).build();
    }

}
