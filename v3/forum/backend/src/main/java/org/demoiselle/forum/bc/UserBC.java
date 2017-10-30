package org.demoiselle.forum.bc;

import java.util.UUID;
import java.util.logging.Logger;
import org.demoiselle.forum.entity.User;
import org.demoiselle.jee.crud.AbstractBusiness;

/**
 *
 * @author PauloGladson
 */
public class UserBC extends AbstractBusiness<User, UUID> {

    private static final Logger LOG = Logger.getLogger(UserBC.class.getName());

}
