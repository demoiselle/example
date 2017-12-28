/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.biblia.constants;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author paulo
 */
public class ResponseFTS implements Serializable {

    private static final long serialVersionUID = -1769589533175831560L;

    private Integer idOrigem;
    private String origem;
    private String nome;
    private float ocorrencias;
    private String texto;

    /**
     *
     * @return
     */
    public Integer getIdOrigem() {
        return idOrigem;
    }

    /**
     *
     * @param idOrigem
     */
    public void setIdOrigem(Integer idOrigem) {
        this.idOrigem = idOrigem;
    }

    /**
     *
     * @return
     */
    public String getOrigem() {
        return origem;
    }

    /**
     *
     * @param origem
     */
    public void setOrigem(String origem) {
        this.origem = origem;
    }

    /**
     *
     * @return
     */
    public float getOcorrencias() {
        return ocorrencias;
    }

    /**
     *
     * @param ocorrencias
     */
    public void setOcorrencias(float ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    /**
     *
     * @return
     */
    public String getTexto() {
        return texto;
    }

    /**
     *
     * @param texto
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    private static final Logger LOG = Logger.getLogger(ResponseFTS.class.getName());

}
