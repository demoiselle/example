package org.demoiselle.forum.dao;

import org.demoiselle.forum.entity.Categoria;
import java.util.UUID;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

/**
 *
 * @author 70744416353
 */
public class CategoriaDAO extends AbstractDAO<Categoria, UUID> {

    @PersistenceContext(unitName = "forumPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
