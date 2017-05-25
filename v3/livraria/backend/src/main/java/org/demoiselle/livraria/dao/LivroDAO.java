package org.demoiselle.livraria.dao;

import org.demoiselle.livraria.entity.Livro;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

public class LivroDAO extends AbstractDAO< Livro, String> {

    @PersistenceContext(unitName = "TenantPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
