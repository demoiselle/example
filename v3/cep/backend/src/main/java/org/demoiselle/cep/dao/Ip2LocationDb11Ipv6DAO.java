package org.demoiselle.cep.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.cep.entity.Ip2locationDb11Ipv6;
import org.demoiselle.jee.crud.AbstractDAO;

public class Ip2LocationDb11Ipv6DAO extends AbstractDAO< Ip2locationDb11Ipv6, Integer> {

    @PersistenceContext
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
