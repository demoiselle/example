package org.demoiselle.forum.dao;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.forum.entity.Guest;
import org.demoiselle.jee.crud.AbstractDAO;

/**
 *
 * @author PauloGladson
 */
public class GuestDAO extends AbstractDAO< Guest, String> {

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
    private static final Logger LOG = Logger.getLogger(GuestDAO.class.getName());

}
