package org.demoiselle.cep.dao;

import org.demoiselle.cep.entity.Cep;
import java.util.List;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.ejb.Asynchronous;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

/**
 *
 * @author PauloGladson
 */
public class CepDAO extends AbstractDAO<Cep, Integer> {

    private static final Logger LOG = getLogger(CepDAO.class.getName());

    @PersistenceContext
    private EntityManager em;

    /**
     *
     * @return
     */
    @Override
    @Asynchronous
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     * @param cep
     * @return
     */
    public List getEndereco(String cep) {
        return getEntityManager().createQuery("Select c from Cep c where c.cep = :cep order by c.cep").setParameter("cep", cep).getResultList();
    }

    /**
     *
     * @param uf
     * @return
     */
    public List getListaCidade(String uf) {
        return getEntityManager().createQuery("Select c.cidade from Cep c where c.uf = :uf group by c.cidade order by c.cidade").setParameter("uf", uf).getResultList();
    }

    /**
     *
     * @param uf
     * @param logradouro
     * @return
     */
    public List getListaLogradouro(String uf, String logradouro) {
        return getEntityManager().createQuery("Select c from Cep c where c.uf = :uf and c.logradouro like :logra order by c.cep").setParameter("uf", uf).setParameter("logra", "%" + logradouro + "%").getResultList();
    }

    /**
     *
     * @return
     */
    public List getListaUF() {
        return getEntityManager().createQuery("Select c.uf from Cep c group by c.uf order by c.uf").getResultList();
    }
}
