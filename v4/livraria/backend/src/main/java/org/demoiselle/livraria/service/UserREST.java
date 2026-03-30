package org.demoiselle.livraria.service;

import org.demoiselle.livraria.entity.User;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;
import org.demoiselle.jee.security.annotation.Authenticated;

@Path("v1/users")
//@Authenticated
public class UserREST extends AbstractREST<User, String> {

    @GET
    @Override
    @Transactional
    public Result find() {
        return bc.find();
    }

}
