/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.bc;

import app.dao.LocalidadeDAO;
import app.entity.LogLocalidade;
import java.util.List;
import org.demoiselle.jee.crud.AbstractBusiness;

/**
 *
 * @author PauloGladson
 */
public class LocalidadeBC extends AbstractBusiness<LogLocalidade, Integer> {
    
    public List<LogLocalidade> findByUf(String uf){
        return ((LocalidadeDAO) dao).findByUf(uf);
    }

}
