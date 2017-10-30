package org.demoiselle.forum.service;

import org.demoiselle.forum.entity.Categoria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.crud.Search;

/**
 *
 * @author PauloGladson
 */
@Api("v1/Categorias")
@Path("v1/categorias")
//@Authenticated
@ApiImplicitParams({
    @ApiImplicitParam(name = "Authorization", value = "JWT token",
            required = true, dataType = "string", paramType = "header")
})
public class CategoriaREST extends AbstractREST< Categoria, String> {

    /**
     *
     * @return
     */
    @GET
    @Override
    @Transactional
    @Search(fields = {"*"}) // Escolha quais campos serão passados para o frontend
    public Result find() {
        return bc.find();
    }
    private static final Logger LOG = Logger.getLogger(CategoriaREST.class.getName());

}
