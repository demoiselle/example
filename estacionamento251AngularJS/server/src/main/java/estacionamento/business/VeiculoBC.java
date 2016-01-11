/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import estacionamento.entity.Fabricante;
import estacionamento.entity.Veiculo;
import estacionamento.persistence.VeiculoDAO;
import java.util.List;

/**
 *
 * @author 70744416353
 */
@BusinessController
public class VeiculoBC extends DelegateCrud<Veiculo, Long, VeiculoDAO> {

    /**
     *
     * @return
     */
    public Long count() {
        return getDelegate().count();
    }

    /**
     *
     * @param field
     * @param order
     * @param init
     * @param qtde
     * @return
     */
    public List list(String field, String order, int init, int qtde) {
        return getDelegate().list(field, order, init, qtde);
    }

}
