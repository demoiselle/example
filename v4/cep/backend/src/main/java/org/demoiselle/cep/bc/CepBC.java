/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.cep.bc;

import org.demoiselle.cep.dao.CepDAO;
import org.demoiselle.cep.entity.Cep;
import java.util.List;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import org.demoiselle.jee.crud.AbstractBusiness;

/**
 *
 * @author 70744416353
 */
public class CepBC extends AbstractBusiness<Cep, Integer> {

    private static final Logger LOG = getLogger(CepBC.class.getName());

    /**
     *
     * @param id
     * @return
     */
    public List getEndereco(String id) {
        return (((CepDAO) dao).getEndereco(id));
    }

}
