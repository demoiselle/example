package org.demoiselle.forum.service;

import org.demoiselle.forum.entity.Categoria;
import java.util.UUID;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.crud.Search;
import org.demoiselle.jee.security.annotation.Authenticated;

/**
 *
 * @author 70744416353
 */
@Path("v1/categorias")
@Authenticated
public class CategoriaREST extends AbstractREST<Categoria, UUID> {

    @GET
    @Override
    @Transactional
    @Search(fields = {"*"})
    public Result find() {
        return bc.find();
    }
}
