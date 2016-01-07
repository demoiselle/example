/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import estacionamento.entity.User;
import estacionamento.entity.VehicleModelYear;
import java.util.List;

/**
 *
 * @author gladson
 */
@PersistenceController
public class VehicleModelYearDAO extends JPACrud<VehicleModelYear, Integer> {

    /**
     *
     * @return
     */
    public Long count() {
        return (Long) getEntityManager().createQuery("select COUNT(u) from VehicleModelYear u").getSingleResult();
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
        return getEntityManager().createQuery("select u from VehicleModelYear u ORDER BY " + field + " " + order).setFirstResult(init).setMaxResults(qtde).getResultList();
    }
}
