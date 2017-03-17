package org.demoiselle.forum.dao;

import org.demoiselle.forum.entity.Guest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

public class GuestDAO extends AbstractDAO< Guest, String> {

    @PersistenceContext(unitName = "forumPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
