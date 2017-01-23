package app.bc;

import app.entity.User;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import org.demoiselle.jee.crud.AbstractBusiness;

/**
 *
 * @author gladson
 */
public class UserBC extends AbstractBusiness<User, String> {

    private static final Logger LOG = getLogger(UserBC.class.getName());

}
