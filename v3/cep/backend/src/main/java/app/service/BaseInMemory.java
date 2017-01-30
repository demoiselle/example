/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.bc.LocalidadeBC;
import app.bc.LogradouroBC;
import app.bc.UfBC;
import app.entity.LogFaixaUf;
import app.entity.LogLocalidade;
import app.entity.LogLogradouro;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author 70744416353
 */
@Stateless
public class BaseInMemory {

    @Inject
    private LocalidadeBC localidadeBC;

    @Inject
    private LogradouroBC logradouroBC;

    @Inject
    private UfBC ufBC;

    private static final ConcurrentHashMap<String, LogLogradouro> repoLogra = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, LogLocalidade> repoLoca = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, LogFaixaUf> repoUf = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {

        if (repoUf.isEmpty()) {
            ufBC.find().getContent().parallelStream().forEach((object) -> {
                repoUf.putIfAbsent(((LogFaixaUf) object).getUfeSg().toLowerCase(), (LogFaixaUf) object);
            });
        }

        if (repoLoca.isEmpty()) {
            localidadeBC.find().getContent().parallelStream().forEach((object) -> {
                repoLoca.putIfAbsent(((LogLocalidade) object).getLocNo().toLowerCase(), (LogLocalidade) object);
            });
        }

    }

    public ConcurrentHashMap getRepoLoca() {
        return repoLoca;
    }

    public ConcurrentHashMap getRepoUf() {
        return repoUf;
    }

    public List<LogLogradouro> getLogradouro(String nome) {

        if (nome.length() >= 3) {
            try {
                List<LogLogradouro> lista = logradouroBC.getLogradouroNome(nome);
                lista.stream().parallel().forEach((logLogradouro) -> {
                    LogLogradouro log = repoLogra.get(logLogradouro.getCep());
                    if (log == null) {
                        repoLogra.putIfAbsent(logLogradouro.getCep(), logLogradouro);
                    }
                });
                return lista;
            } catch (Exception e) {
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }

    public LogLogradouro getCep(String cep) {
        LogLogradouro log = repoLogra.get(cep);
        try {
            if (log == null) {
                log = logradouroBC.getLogradouroCep(cep);
                if (log != null) {
                    repoLogra.putIfAbsent(cep, log);
                } else {
                    log = new LogLogradouro();
                }
            }
            return log;
        } catch (Exception e) {
            return log;
        }

    }

    public LogFaixaUf getUf(String nome) {
        return repoUf.get(nome.toLowerCase());
    }

    public LogLocalidade getLocalidade(String nome) {
        return repoLoca.get(nome.toLowerCase());
    }

    public List<LogLocalidade> getLocalidadesFromUF(String nome) {
        List<LogLocalidade> lista = new ArrayList<>();
        repoLoca.entrySet().stream().parallel().map((entry) -> entry.getValue()).filter((value) -> (value.getUfeSg().getUfeSg().equalsIgnoreCase(nome))).forEach((value) -> {
            lista.add(value);
        });
        return lista;
    }
}
