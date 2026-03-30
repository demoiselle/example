package org.demoiselle.livraria.dao;

import org.demoiselle.livraria.entity.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

public class LivroDAO extends AbstractDAO<Livro, String> {

    @PersistenceContext(unitName = "pu")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
