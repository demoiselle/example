/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import estacionamento.entity.Fabricante;
import java.util.List;

/**
 *
 * @author gladson
 */
@PersistenceController
public class FabricanteDAO extends JPACrud<Fabricante, Long> {

       /**
     *
     * @return
     */
    public Long count() {
        return (Long) getEntityManager().createQuery("select COUNT(u) from " + this.getBeanClass().getSimpleName() + " u").getSingleResult();
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
    public List list(String field, String order, int init, int qtde) {
        return getEntityManager().createQuery("select u from " + this.getBeanClass().getSimpleName() + " u ORDER BY " + field + " " + order).setFirstResult(init).setMaxResults(qtde).getResultList();
    }

    /**
     *
     * @param name
     * @return
     */
    public List<Fabricante> listaModelos(String fabricante) {
        String jpql = "SELECT u FROM Fabricante u WHERE upper(u.make) like upper('%" + fabricante.replaceAll(" ", "%") + "%') order by model";
        return super.findByJPQL(jpql);
    }
    
        /**
     *
     * @param field
     * @return
     */
    @SuppressWarnings("unchecked")
    public List list(String campo, String valor) {
        return getEntityManager().createQuery("select u from " + this.getBeanClass().getSimpleName() + " u " + " where " + campo + " = " + valor + " ORDER BY " + campo).getResultList();
    }

}
