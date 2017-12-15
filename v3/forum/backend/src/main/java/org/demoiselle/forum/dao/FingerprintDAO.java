package org.demoiselle.forum.dao;

import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import  java.util.List;
import org.demoiselle.forum.entity.Fingerprint;
import org.demoiselle.jee.crud.AbstractDAO;


/**
 *
 * @author SERPRO
 */
public class FingerprintDAO extends AbstractDAO< Fingerprint, Long> {

    private static final Logger LOG = getLogger(FingerprintDAO.class.getName());

    /**
     *
     */
    @PersistenceContext(unitName = "forumPU")
    protected EntityManager em;

    /**
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     * @param id
     * @return
     */
    public List<Fingerprint> findByUsuario(Long id) {
        return em.createQuery("SELECT f FROM Fingerprint f WHERE f.usuarioId = :id").setParameter("id", id).getResultList();
    }

    /**
     *
     * @param cod
     * @return
     */
    public List<Fingerprint> findByCodigo(String cod) {
        return em.createQuery("SELECT f FROM Fingerprint f WHERE f.codigo = :codigo").setParameter("codigo", cod).getResultList();
    }
}
