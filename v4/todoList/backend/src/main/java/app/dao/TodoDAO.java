package app.dao;

import app.entity.Todo;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

public class TodoDAO extends AbstractDAO<Todo, String> {

    private static final Logger LOG = getLogger(TodoDAO.class.getName());

    @PersistenceContext(unitName = "TodoPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
