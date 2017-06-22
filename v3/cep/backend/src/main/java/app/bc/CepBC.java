/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.bc;

import app.dao.CepDAO;
import app.entity.Cep;
import java.util.List;
import org.demoiselle.jee.crud.AbstractBusiness;

/**
 *
 * @author 70744416353
 */
public class CepBC extends AbstractBusiness<Cep, Integer> {

    public List getEndereco(String id) {
        return (((CepDAO) dao).getEndereco(id));
    }

    public List getListaLogradouro(String nome) {
        return (((CepDAO) dao).getListaLogradouro(nome));
    }
}
