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
    public List<Fabricante> list(String field, String order, int init, int qtde) {
        return getEntityManager().createQuery("select u from Fabricante u ORDER BY " + field + " " + order).setFirstResult(init).setMaxResults(qtde).getResultList();
    }

    /**
     *
     * @param name
     * @return
     */
    public List<Fabricante> listaModelos(String fabricante) {
        String jpql = "SELECT u FROM Fabricante u WHERE upper(u.make) like upper('%" + fabricante.replaceAll(" ", "%") + "%')";
        return super.findByJPQL(jpql);
    }

}
