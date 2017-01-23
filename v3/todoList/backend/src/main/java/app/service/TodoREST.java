package app.service;

import app.entity.Todo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.security.annotation.Authenticated;
import org.demoiselle.jee.security.annotation.RequiredRole;

/**
 *
 * @author gladson
 */
@Api("Todo")
@Path("todo")
@Authenticated
public class TodoREST extends AbstractREST<Todo, String> {

    @POST
    @Override
    @Transactional
    @ApiOperation(value = "persist entity")
    public Todo persist(@Valid Todo entity) {
        return bc.persist(entity);
    }

    @PUT
    @Override
    @Transactional
    @ApiOperation(value = "full update entity")
    public Todo merge(@Valid Todo entity) {
        return bc.merge(entity);
    }

    @DELETE
    @Override
    @Path("{id}")
    @Transactional
    @ApiOperation(value = "remove entity")
    public void remove(@PathParam("id") final String id) {
        bc.remove(id);
    }

    @GET
    @Override
    @Transactional
    @RequiredRole("Administrador")
    public Result find() {
        return bc.find();
    }

    @GET
    @Override
    @Path("{id}")
    @Transactional
    @ApiOperation(value = "find by ID")
    public Todo find(@PathParam("id") final String id) {
        return bc.find(id);
    }
    private static final Logger LOG = Logger.getLogger(TodoREST.class.getName());
}
