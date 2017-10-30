package org.demoiselle.forum.service;

import org.demoiselle.forum.entity.User;
import java.util.UUID;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractREST;

//@Api("v1/Users")
//@Path("v1/users")
//@Authenticated
//@ApiImplicitParams({
//    @ApiImplicitParam(name = "Authorization", value = "JWT token",
//            required = true, dataType = "string", paramType = "header")
//})

/**
 *
 * @author PauloGladson
 */
public class UserREST extends AbstractREST<User, UUID> {

    /**
     *
     * @return
     */
    @GET
    @Override
    @Transactional
    public Result find() {
        return bc.find();
    }
    private static final Logger LOG = Logger.getLogger(UserREST.class.getName());

}
