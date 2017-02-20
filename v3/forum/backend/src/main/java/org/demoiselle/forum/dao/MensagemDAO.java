package org.demoiselle.forum.dao;

import org.demoiselle.forum.entity.Mensagem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

public class MensagemDAO extends AbstractDAO< Mensagem, String> {

    @PersistenceContext(unitName = "forumPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
