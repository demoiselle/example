package org.demoiselle.forum.service;

import org.demoiselle.forum.entity.Topico;
import io.swagger.annotations.Api;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.demoiselle.forum.bc.TopicoBC;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.crud.Search;

@Api("v1/Topicos")
@Path("v1/topicos")
//@Authenticated
public class TopicoREST extends AbstractREST< Topico, String> {

    @GET
    @Override
    @Transactional
    @Search(fields = {"id", "description", "categoria"})
    public Result find() {
        return bc.find();
    }

    @GET
    @Path("categoria")
    @Transactional
    @Search(fields = {"*"})
    public Result findHandler() {
        return ((TopicoBC) bc).findHandler();
    }

}
