package org.demoiselle.forum.dao;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.forum.entity.Moderator;
import org.demoiselle.jee.crud.AbstractDAO;

/**
 *
 * @author PauloGladson
 */
public class ModeratorDAO extends AbstractDAO< Moderator, String> {

    /**
     *
     */
    @PersistenceContext(unitName = "forumPU")
    protected EntityManager em;

    /**
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    private static final Logger LOG = Logger.getLogger(ModeratorDAO.class.getName());

}
