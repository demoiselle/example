package org.demoiselle.forum.service.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.demoiselle.forum.bc.TopicoBC;
import org.demoiselle.forum.entity.Topico;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.crud.Search;

/**
 *
 * @author PauloGladson
 */
@Api("v1/Topicos")
@Path("v1/topicos")
//@Authenticated
@ApiImplicitParams({
    @ApiImplicitParam(name = "Authorization", value = "JWT token",
            required = true, dataType = "string", paramType = "header")
})
public class TopicoREST extends AbstractREST< Topico, String> {

    /**
     *
     * @return
     */
    @GET
    @Override
    @Transactional
    @Search(fields = {"id", "description", "categoria"})
    public Result find() {
        return bc.find();
    }

    /**
     *
     * @return
     */
    @GET
    @Path("categoria")
    @Transactional
    @Search(fields = {"*"})
    public Result findHandler() {
        return ((TopicoBC) bc).findHandler();
    }
    private static final Logger LOG = Logger.getLogger(TopicoREST.class.getName());

}
