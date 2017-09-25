package org.demoiselle.forum.service;

import org.demoiselle.forum.entity.Categoria;
import io.swagger.annotations.Api;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.crud.Search;

@Api("v1/Categorias")
@Path("v1/categorias")
//@Authenticated
public class CategoriaREST extends AbstractREST< Categoria, String> {

    @GET
    @Override
    @Transactional
    @Search(fields = {"*"}) // Escolha quais campos serão passados para o frontend
    public Result find() {
        return bc.find();
    }

}
