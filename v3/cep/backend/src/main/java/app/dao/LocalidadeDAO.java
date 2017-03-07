/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.dao;

import app.entity.LogLocalidade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

/**
 *
 * @author PauloGladson
 */
public class LocalidadeDAO extends AbstractDAO<LogLocalidade, Integer> {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<LogLocalidade> findByUf(String uf) {
        return em.createQuery("SELECT l FROM LogLocalidade l WHERE l.ufeSg.ufeSg = :uf and l.locInTipoLocalidade = 'M' order by l.locNo").setParameter("uf", uf).getResultList();
    }

}
