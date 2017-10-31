package org.demoiselle.forum.dao;

import org.demoiselle.forum.entity.Permissao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

public class PermissaoDAO extends AbstractDAO< Permissao, String> {

    @PersistenceContext(unitName = "forumPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
