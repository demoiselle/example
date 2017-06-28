/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.livraria.tenant;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 70744416353
 */
@Entity
@Table(catalog = "tenant", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensagem.findAll", query = "SELECT s FROM Mensagem s")
    , @NamedQuery(name = "Mensagem.findById", query = "SELECT s FROM Mensagem s WHERE s.id = :id")
    , @NamedQuery(name = "Mensagem.findByDia", query = "SELECT s FROM Mensagem s WHERE s.dia = :dia")
    , @NamedQuery(name = "Mensagem.findByTipoAtivo", query = "SELECT s FROM Mensagem s WHERE s.tipo = :tipo and s.ativo is true")
    , @NamedQuery(name = "Mensagem.findByComando", query = "SELECT s FROM Mensagem s WHERE s.comando = :comando")})
public class Mensagem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dia;

    @Size(max = 64)
    @Column(length = 64)
    private String comando;

    @Size(max = 8)
    @Column(length = 8)
    private String tipo;

    private boolean ativo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = new Date();
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

}
