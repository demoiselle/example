package app.dao;

import app.entity.Cep;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.demoiselle.jee.crud.AbstractDAO;

/**
 *
 * @author PauloGladson
 */
public class CepDAO extends AbstractDAO<Cep, String> {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Cep> getCep(String cep) {
        return em.createQuery("From Cep c Where c.cep = :cep", Cep.class).setParameter("cep", cep).getResultList();
    }

    public Object getCidades(String uf) {
        return em.createQuery("SELECT c.cidade From Cep c Where c.uf = :uf Group by c.cidade Order by c.cidade").setParameter("uf", uf).getResultList();
    }

    public List<Cep> getLogradouro(String uf, String nome) {
        return em.createQuery("From Cep c Where c.uf = :uf and c.logradouro like :nome").setParameter("uf", uf).setParameter("nome", "%" + nome).getResultList();
    }

    public Object getUfs() {
        return em.createQuery("SELECT c.uf From Cep c Group by c.uf Order by c.uf").getResultList();
    }

}
