/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.livraria.timer;

import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.demoiselle.livraria.dao.MensagemDAO;
import org.demoiselle.livraria.dao.SgdbDAO;
import org.demoiselle.livraria.tenant.Mensagem;

/**
 *
 * @author 70744416353
 */
@Singleton
public class SGDB {

    @Inject
    private SgdbDAO sdao;

    @Inject
    private MensagemDAO mdao;

    @Transactional
    @Schedule(hour = "*", minute = "*/3", second = "3", persistent = false)
    public void criarBase() {
        List<Mensagem> mens = mdao.listaPorTipo("DBADD");
        for (Mensagem men : mens) {
            System.out.println(men.getTipo() + " " + men.getComando());
            sdao.createSgdb(men.getComando());
            men.setAtivo(false);
            mdao.persist(men);
        }
    }

    @Transactional
    @Schedule(hour = "*", minute = "*/3", second = "3", persistent = false)
    public void manutencaoBase() {
        List<Mensagem> mens = mdao.listaPorTipo("DBUPDATE");
        for (Mensagem men : mens) {
            System.out.println(men.getTipo() + " " + men.getComando());
            sdao.updateSgdb(men.getComando());
            men.setAtivo(false);
            mdao.persist(men);
        }
    }
}
