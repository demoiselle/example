package org.demoiselle.forum.service;

import org.demoiselle.forum.entity.Topico;
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
@Path("v1/topicos")
@Authenticated
public class TopicoREST extends AbstractREST<Topico, UUID> {

    @GET
    @Override
    @Transactional
    @Search(fields = {"*"})
    public Result find() {
        return bc.find();
    }
}
