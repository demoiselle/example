/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.livraria.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.jee.crud.AbstractDAO;
import org.demoiselle.livraria.tenant.Mensagem;

/**
 *
 * @author 70744416353
 */
public class MensagemDAO extends AbstractDAO< Mensagem, String> {

    @PersistenceContext(unitName = "pu")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Mensagem> listaPorTipo(String tipo){
        return getEntityManager().createNamedQuery("Mensagem.findByTipoAtivo").setParameter("tipo", tipo).getResultList();
    }

}
