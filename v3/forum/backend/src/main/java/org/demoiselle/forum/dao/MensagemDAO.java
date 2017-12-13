package org.demoiselle.forum.dao;

import org.demoiselle.forum.entity.Mensagem;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

public class MensagemDAO extends AbstractDAO< Mensagem, UUID> {

    @PersistenceContext(unitName = "forumPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
