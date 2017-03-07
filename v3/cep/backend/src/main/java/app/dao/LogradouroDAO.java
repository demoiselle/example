/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.dao;

import app.entity.LogLogradouro;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;

/**
 *
 * @author PauloGladson
 */
public class LogradouroDAO extends AbstractDAO<LogLogradouro, Integer> {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LogLogradouro getLogradouroCep(String cep) {
        return (LogLogradouro) getEntityManager().createNamedQuery("LogLogradouro.findByCep").setParameter("cep", cep).getSingleResult();
    }
}
