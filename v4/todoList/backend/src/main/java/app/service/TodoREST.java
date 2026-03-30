package app.service;

import app.entity.Todo;
import app.message.TodoMessage;
import java.util.List;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.core.api.security.DemoiselleUser;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.crud.Search;
import org.demoiselle.jee.crud.pagination.ResultSet;
import org.demoiselle.jee.rest.exception.DemoiselleRestException;
import org.demoiselle.jee.security.annotation.Authenticated;

@Path("v1/todo")
@Authenticated
public class TodoREST extends AbstractREST<Todo, String> {

    @Inject
    private DemoiselleUser dml;

    @Inject
    private TodoMessage message;

    @POST
    @Override
    @Transactional
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
    public Todo mergeFull(@Valid Todo entity) {
        if (entity.getUser().getId().equalsIgnoreCase(dml.getIdentity())) {
            return bc.mergeFull(entity);
        } else {
            throw new DemoiselleRestException(message.onlyOwner(), 403);
        }
    }

    @DELETE
    @Override
    @Path("{id}")
    @Transactional
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
    @Search(fields = {"*"})
    public Result find() {
        Result res = new ResultSet();
        res.setContent((List<?>) bc.find(dml.getIdentity()).getUser().getTodos());
        return res;
    }

    @GET
    @Override
    @Path("{id}")
    @Transactional
    public Todo find(@PathParam("id") final String id) {
        Todo todo = bc.find(id);
        if (todo.getUser().getId().equalsIgnoreCase(dml.getIdentity())) {
            return bc.find(id);
        } else {
            throw new DemoiselleRestException(message.onlyOwner(), 403);
        }
    }
}
