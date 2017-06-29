package org.demoiselle.livraria.service;

import org.demoiselle.livraria.entity.Livro;
import io.swagger.annotations.Api;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.crud.Search;
import org.demoiselle.jee.security.annotation.Authenticated;

@Api("v1/Livros")
@Path("v1/livros")
@Authenticated
public class LivroREST extends AbstractREST< Livro, String> {

    @GET
    @Override
    @Transactional
    @Search(fields = {"id", "descricao"})
    public Result find() {
        return bc.find();
    }

}
