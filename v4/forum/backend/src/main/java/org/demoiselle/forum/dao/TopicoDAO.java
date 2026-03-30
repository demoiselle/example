package org.demoiselle.forum.dao;

import org.demoiselle.forum.entity.Topico;
import java.util.UUID;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

/**
 *
 * @author 70744416353
 */
public class TopicoDAO extends AbstractDAO<Topico, UUID> {

    @PersistenceContext(unitName = "forumPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
