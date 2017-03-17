package app.dao;

import app.entity.Cep;
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

    public Object getCep(String cep) {
        return em.createQuery("From Cep c Where c.cep = :cep").setParameter("cep", cep).getResultList();
    }

    public Object getCidades(String uf) {
        return em.createNativeQuery("db.cep.aggregate([{$match:{uf:\"" + uf + "\"}}, {$group:{_id:\"$cidade\"}}]);").getResultList();
    }

    public Object getLogradouro(String uf, String nome) {
        return em.createNativeQuery("db.cep.find({\"uf\":\"" + uf + "\"})").getResultList();
    }

    public Object getUfs() {
        String query1 = "db.cep.aggregate({$group : {_id : \"$uf\"})";
        //,{$sort : {total : -1}}
        Query query = em.createNativeQuery(query1);
        return query.getResultList();
    }
}
