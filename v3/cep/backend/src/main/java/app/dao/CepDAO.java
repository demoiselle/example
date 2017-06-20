/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.dao;

import app.entity.Cep;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

/**
 *
 * @author PauloGladson
 */
public class CepDAO extends AbstractDAO<Cep, Integer> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List getEndereco(String cep) {
        return getEntityManager().createQuery("Select c from Cep c where c.cep = :cep order by c.cep").setParameter("cep", cep).getResultList();
    }

    public List getListaCidade(String uf) {
        return getEntityManager().createQuery("Select c.cidade from Cep c where c.uf = :uf group by c.cidade order by c.cidade").setParameter("uf", uf).getResultList();
    }

    public List getListaLogradouro(String nome) {
        return getEntityManager().createQuery("Select c.cidade from Cep c where c.logradouro like :nome order by c.cep").setParameter("nome", "%" + nome).getResultList();
    }

    public List getListaUF() {
        return getEntityManager().createQuery("Select c.uf from Cep c group by c.uf order by c.uf").getResultList();
    }
}
