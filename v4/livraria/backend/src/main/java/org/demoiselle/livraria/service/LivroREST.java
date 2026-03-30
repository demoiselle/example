package org.demoiselle.livraria.service;

import org.demoiselle.livraria.entity.Livro;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.crud.Search;
import org.demoiselle.jee.security.annotation.Authenticated;

@Path("v1/livros")
@Authenticated
public class LivroREST extends AbstractREST<Livro, String> {

    @GET
    @Override
    @Transactional
    @Search(fields = {"id", "descricao"})
    public Result find() {
        return bc.find();
    }

}
