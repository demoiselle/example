/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gladson
 */
@Entity
@Table
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cep.findAll", query = "SELECT c FROM Cep c")
    , @NamedQuery(name = "Cep.findById", query = "SELECT c FROM Cep c WHERE c.id = :id")
    , @NamedQuery(name = "Cep.findByLogradouro", query = "SELECT c FROM Cep c WHERE c.logradouro = :logradouro")
    , @NamedQuery(name = "Cep.findByCep", query = "SELECT c FROM Cep c WHERE c.cep = :cep")
    , @NamedQuery(name = "Cep.findByUf", query = "SELECT c FROM Cep c WHERE c.uf = :uf")
    , @NamedQuery(name = "Cep.findByCidade", query = "SELECT c FROM Cep c WHERE c.cidade = :cidade")
    , @NamedQuery(name = "Cep.findByBairroIni", query = "SELECT c FROM Cep c WHERE c.bairroIni = :bairroIni")
    , @NamedQuery(name = "Cep.findByBairroFim", query = "SELECT c FROM Cep c WHERE c.bairroFim = :bairroFim")})
public class Cep implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Size(max = 128)
    @Column(length = 128)
    private String logradouro;
    @Size(max = 8)
    @Column(length = 8)
    private String cep;
    @Size(max = 2)
    @Column(length = 2)
    private String uf;
    @Size(max = 128)
    @Column(length = 128)
    private String cidade;
    @Size(max = 128)
    @Column(name = "bairro_ini", length = 128)
    private String bairroIni;
    @Size(max = 128)
    @Column(name = "bairro_fim", length = 128)
    private String bairroFim;

    public Cep() {
    }

    public Cep(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairroIni() {
        return bairroIni;
    }

    public void setBairroIni(String bairroIni) {
        this.bairroIni = bairroIni;
    }

    public String getBairroFim() {
        return bairroFim;
    }

    public void setBairroFim(String bairroFim) {
        this.bairroFim = bairroFim;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cep)) {
            return false;
        }
        Cep other = (Cep) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.Cep[ id=" + id + " ]";
    }

}
