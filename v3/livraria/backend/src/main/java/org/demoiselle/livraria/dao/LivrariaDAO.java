package org.demoiselle.livraria.dao;

import org.demoiselle.livraria.tenant.Livraria;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

public class LivrariaDAO extends AbstractDAO< Livraria, String> {

    @PersistenceContext(unitName = "pu")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Boolean nomeExists(String nome) {
        return em.createQuery("Select l from Livraria l where l.description = :nome").setParameter("nome", nome).getResultList().size() > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

}
