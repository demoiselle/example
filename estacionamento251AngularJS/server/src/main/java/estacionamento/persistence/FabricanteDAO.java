/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import estacionamento.entity.Fabricante;
import estacionamento.entity.User;
import java.util.List;

/**
 *
 * @author gladson
 */
@PersistenceController
public class FabricanteDAO extends JPACrud<Fabricante, Integer> {

    /**
     *
     * @return
     */
    public Long count() {
        return (Long) getEntityManager().createQuery("select COUNT(u) from Fabricante u").getSingleResult();
    }

    /**
     *
     * @param field
     * @param order
     * @param init
     * @param qtde
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<User> list(String field, String order, int init, int qtde) {
        return getEntityManager().createQuery("select u from Fabricante u ORDER BY " + field + " " + order).setFirstResult(init).setMaxResults(qtde).getResultList();
    }
}
