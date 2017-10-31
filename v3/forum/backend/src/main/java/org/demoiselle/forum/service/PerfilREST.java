package org.demoiselle.forum.service;

import org.demoiselle.forum.entity.Perfil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.crud.Search;
import org.demoiselle.jee.security.annotation.Authenticated;

@Api("v1/Perfils")
@ApiImplicitParams({
    @ApiImplicitParam(name = "Authorization", value = "JWT token",
            required = true, dataType = "string", paramType = "header")
})
@Path("v1/perfils")
@Authenticated
public class PerfilREST extends AbstractREST< Perfil, String> {

    @GET
    @Override
    @Transactional
    @Search(fields = {"id", "description"}) // Escolha quais campos serão passados para o frontend
    public Result find() {
        return bc.find();
    }

}
