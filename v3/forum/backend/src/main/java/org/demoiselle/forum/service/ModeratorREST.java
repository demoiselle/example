package org.demoiselle.forum.service;

import org.demoiselle.forum.entity.Moderator;
import io.swagger.annotations.Api;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.crud.Search;
import org.demoiselle.jee.security.annotation.Authenticated;

@Api("v1/Moderators")
@Path("v1/moderators")
public class ModeratorREST extends AbstractREST< Moderator, String> {

    @GET
    @Override
    @Transactional
//    @Search(fields = {"id", "firstName", "email", "cor", "ordem"}) // Escolha quais campos serão passados para o frontend
    @Search(fields = {"*"})
    public Result find() {
        return bc.find();
    }

}
