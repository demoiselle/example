/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.bc;

import app.dao.LogradouroDAO;
import app.entity.LogLogradouro;
import java.util.List;
import org.demoiselle.jee.crud.AbstractBusiness;

/**
 *
 * @author 70744416353
 */
public class LogradouroBC extends AbstractBusiness<LogLogradouro, Integer> {

    public List getLogradouroNome(String nome) {
        return ((LogradouroDAO) dao).getLogradouroNome(nome);
    }

    public LogLogradouro getLogradouroCep(String nome) {
        return ((LogradouroDAO) dao).getLogradouroCep(nome);
    }
}
