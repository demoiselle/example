package app.bc;

import app.entity.Todo;
import java.util.logging.Logger;
import org.demoiselle.jee.crud.AbstractBusiness;

/**
 *
 * @author gladson
 */
public class TodoBC extends AbstractBusiness<Todo, String> {

    private static final Logger LOG = Logger.getLogger(TodoBC.class.getName());

}
