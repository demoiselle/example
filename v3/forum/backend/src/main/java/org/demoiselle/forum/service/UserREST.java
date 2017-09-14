package org.demoiselle.forum.service;

import org.demoiselle.forum.entity.User;
import java.util.UUID;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;

//@Api("v1/Users")
//@Path("v1/users")
//@Authenticated
public class UserREST extends AbstractREST<User, UUID> {

    @GET
    @Override
    @Transactional
    public Result find() {
        return bc.find();
    }

}
