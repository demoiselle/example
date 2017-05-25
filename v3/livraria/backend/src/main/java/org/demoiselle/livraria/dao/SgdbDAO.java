package org.demoiselle.livraria.dao;

import org.demoiselle.livraria.tenant.Sgdb;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

/**
 *
 * @author 70744416353
 */
public class SgdbDAO extends AbstractDAO<Sgdb, Long> {

    @PersistenceContext(unitName = "MasterPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em.getEntityManagerFactory().createEntityManager();
    }

}
