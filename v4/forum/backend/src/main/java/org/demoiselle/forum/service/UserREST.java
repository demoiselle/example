package org.demoiselle.forum.service;

import org.demoiselle.forum.entity.User;
import java.util.UUID;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.security.annotation.Authenticated;

/**
 *
 * @author 70744416353
 */
@Path("v1/users")
@Authenticated
public class UserREST extends AbstractREST<User, UUID> {

    @GET
    @Override
    @Transactional
    public Result find() {
        return bc.find();
    }
}
