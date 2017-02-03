package app.service;

import app.entity.Todo;
import app.message.TodoMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.core.api.security.DemoiselleUser;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.rest.exception.DemoiselleRestException;
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

    @Inject
    private DemoiselleUser dml;

    @Inject
    private TodoMessage message;

    @POST
    @Override
    @Transactional
    @ApiOperation(value = "persist entity")
    public Todo persist(@Valid Todo entity) {
        if (entity.getUser().getId().equalsIgnoreCase(dml.getIdentity())) {
            return bc.persist(entity);
        } else {
            throw new DemoiselleRestException(message.onlyOwner(), 403);
        }
    }

    @PUT
    @Override
    @Transactional
    @ApiOperation(value = "full update entity")
    public Todo merge(@Valid Todo entity) {
        if (entity.getUser().getId().equalsIgnoreCase(dml.getIdentity())) {
            return bc.merge(entity);
        } else {
            throw new DemoiselleRestException(message.onlyOwner(), 403);
        }
    }

    @DELETE
    @Override
    @Path("{id}")
    @Transactional
    @ApiOperation(value = "remove entity")
    public void remove(@PathParam("id") final String id) {
        Todo todo = bc.find(id);
        if (todo.getUser().getId().equalsIgnoreCase(dml.getIdentity())) {
            bc.remove(id);
        } else {
            throw new DemoiselleRestException(message.onlyOwner(), 403);
        }
    }

    @GET
    @Override
    @Transactional
    @RequiredRole("Administrador, Gerente")
    public Result find() {
        return bc.find();
    }

    @GET
    @Override
    @Path("{id}")
    @Transactional
    @ApiOperation(value = "find by ID")
    public Todo find(@PathParam("id") final String id) {
        Todo todo = bc.find(id);
        if (todo.getUser().getId().equalsIgnoreCase(dml.getIdentity())) {
            return bc.persist(todo);
        } else {
            throw new DemoiselleRestException(message.onlyOwner(), 403);
        }
    }

    private static final Logger LOG = Logger.getLogger(TodoREST.class.getName());
}
