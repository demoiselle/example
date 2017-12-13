package org.demoiselle.forum.dao;

import org.demoiselle.forum.entity.Topico;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

public class TopicoDAO extends AbstractDAO< Topico, UUID> {

    @PersistenceContext(unitName = "forumPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
