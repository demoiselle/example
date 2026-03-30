package app.service;

import app.entity.User;
import app.message.TodoMessage;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
import org.demoiselle.jee.rest.exception.DemoiselleRestException;
import org.demoiselle.jee.security.annotation.Authenticated;
import org.demoiselle.jee.security.annotation.RequiredRole;

@Authenticated
@Path("v1/user")
public class UserREST extends AbstractREST<User, String> {

    private static final Logger LOG = getLogger(UserREST.class.getName());

    @Inject
    private DemoiselleUser dml;

    @Inject
    private TodoMessage message;

    @POST
    @Override
    @Transactional
    @Authenticated(enable = false)
    public User persist(User entity) {
        return bc.persist(entity);
    }

    @PUT
    @Override
    @Transactional
    public User mergeFull(User entity) {
        if (entity.getId().equalsIgnoreCase(dml.getIdentity())) {
            return bc.mergeFull(entity);
        } else {
            throw new DemoiselleRestException(message.onlyOwner(), 403);
        }
    }

    @GET
    @Override
    @Path("{id}")
    @Transactional
    public User find(@PathParam("id") final String id) {
        if (id.equalsIgnoreCase(dml.getIdentity())) {
            return bc.find(id);
        } else {
            throw new DemoiselleRestException(message.onlyOwner(), 403);
        }
    }

    @GET
    @Override
    @Transactional
    @Search(fields = {"*"})
    @RequiredRole(value = {"Administrador", "Gerente"})
    public Result find() {
        return bc.find();
    }

    @DELETE
    @Override
    @Path("{id}")
    @Transactional
    public void remove(@PathParam("id") final String id) {
        if (id.equalsIgnoreCase(dml.getIdentity())) {
            bc.remove(id);
        } else {
            throw new DemoiselleRestException(message.onlyOwner(), 403);
        }
    }
}
