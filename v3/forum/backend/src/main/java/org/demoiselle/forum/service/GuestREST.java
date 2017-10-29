package org.demoiselle.forum.service;

import org.demoiselle.forum.entity.Guest;
import io.swagger.annotations.Api;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.crud.Search;
import org.demoiselle.jee.security.annotation.Authenticated;

@Api("v1/Guests")
@Path("v1/guests")
//@Authenticated
//@ApiImplicitParams({
//    @ApiImplicitParam(name = "Authorization", value = "JWT token",
//            required = true, dataType = "string", paramType = "header")
//})
public class GuestREST extends AbstractREST< Guest, String> {

    @GET
    @Override
    @Transactional
    @Search(fields = {"*"}) // Escolha quais campos serão passados para o frontend
    public Result find() {
        return bc.find();
    }

}
