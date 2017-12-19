/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.cep.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paulo
 */
@Entity
@Table(catalog = "cep", schema = "public", uniqueConstraints = {
 @UniqueConstraint(columnNames = {"cnpj"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cnpj.findAll", query = "SELECT c FROM Cnpj c")
    , @NamedQuery(name = "Cnpj.findByCnpj", query = "SELECT c FROM Cnpj c WHERE c.cnpj = :cnpj")
    , @NamedQuery(name = "Cnpj.findByNome", query = "SELECT c FROM Cnpj c WHERE c.nome = :nome")
    , @NamedQuery(name = "Cnpj.findByAtividadePrincipal", query = "SELECT c FROM Cnpj c WHERE c.atividadePrincipal = :atividadePrincipal")
    , @NamedQuery(name = "Cnpj.findByDataSituacao", query = "SELECT c FROM Cnpj c WHERE c.dataSituacao = :dataSituacao")
    , @NamedQuery(name = "Cnpj.findByComplemento", query = "SELECT c FROM Cnpj c WHERE c.complemento = :complemento")
    , @NamedQuery(name = "Cnpj.findByEfr", query = "SELECT c FROM Cnpj c WHERE c.efr = :efr")
    , @NamedQuery(name = "Cnpj.findByUf", query = "SELECT c FROM Cnpj c WHERE c.uf = :uf")
    , @NamedQuery(name = "Cnpj.findByTelefone", query = "SELECT c FROM Cnpj c WHERE c.telefone = :telefone")
    , @NamedQuery(name = "Cnpj.findByQsa1", query = "SELECT c FROM Cnpj c WHERE c.qsa1 = :qsa1")
    , @NamedQuery(name = "Cnpj.findByQsa2", query = "SELECT c FROM Cnpj c WHERE c.qsa2 = :qsa2")
    , @NamedQuery(name = "Cnpj.findBySituacao", query = "SELECT c FROM Cnpj c WHERE c.situacao = :situacao")
    , @NamedQuery(name = "Cnpj.findByBairro", query = "SELECT c FROM Cnpj c WHERE c.bairro = :bairro")
    , @NamedQuery(name = "Cnpj.findByLogradouro", query = "SELECT c FROM Cnpj c WHERE c.logradouro = :logradouro")
    , @NamedQuery(name = "Cnpj.findByNumero", query = "SELECT c FROM Cnpj c WHERE c.numero = :numero")
    , @NamedQuery(name = "Cnpj.findByCep", query = "SELECT c FROM Cnpj c WHERE c.cep = :cep")
    , @NamedQuery(name = "Cnpj.findByMunicipio", query = "SELECT c FROM Cnpj c WHERE c.municipio = :municipio")
    , @NamedQuery(name = "Cnpj.findByAbertura", query = "SELECT c FROM Cnpj c WHERE c.abertura = :abertura")
    , @NamedQuery(name = "Cnpj.findByNaturezaJuridica", query = "SELECT c FROM Cnpj c WHERE c.naturezaJuridica = :naturezaJuridica")
    , @NamedQuery(name = "Cnpj.findByFantasia", query = "SELECT c FROM Cnpj c WHERE c.fantasia = :fantasia")
    , @NamedQuery(name = "Cnpj.findByTipo", query = "SELECT c FROM Cnpj c WHERE c.tipo = :tipo")
    , @NamedQuery(name = "Cnpj.findByEmail", query = "SELECT c FROM Cnpj c WHERE c.email = :email")})
public class Cnpj implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(nullable = false)
    private Object id;
    @Size(max = 32)
    @Column(length = 32)
    private String cnpj;
    @Size(max = 128)
    @Column(length = 128)
    private String nome;
    @Size(max = 128)
    @Column(name = "atividade_principal", length = 128)
    private String atividadePrincipal;
    @Column(name = "data_situacao")
    @Temporal(TemporalType.DATE)
    private Date dataSituacao;
    @Size(max = 128)
    @Column(length = 128)
    private String complemento;
    @Size(max = 128)
    @Column(length = 128)
    private String efr;
    @Size(max = 32)
    @Column(length = 32)
    private String uf;
    @Size(max = 32)
    @Column(length = 32)
    private String telefone;
    @Size(max = 128)
    @Column(length = 128)
    private String qsa1;
    @Size(max = 128)
    @Column(length = 128)
    private String qsa2;
    @Size(max = 32)
    @Column(length = 32)
    private String situacao;
    @Size(max = 64)
    @Column(length = 64)
    private String bairro;
    @Size(max = 128)
    @Column(length = 128)
    private String logradouro;
    @Size(max = 8)
    @Column(length = 8)
    private String numero;
    @Size(max = 16)
    @Column(length = 16)
    private String cep;
    @Size(max = 32)
    @Column(length = 32)
    private String municipio;
    @Size(max = 32)
    @Column(length = 32)
    private String abertura;
    @Size(max = 32)
    @Column(name = "natureza_juridica", length = 32)
    private String naturezaJuridica;
    @Size(max = 128)
    @Column(length = 128)
    private String fantasia;
    @Size(max = 32)
    @Column(length = 32)
    private String tipo;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 128)
    @Column(length = 128)
    private String email;

    public Cnpj() {
    }

    public Cnpj(Object id) {
        this.id = id;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAtividadePrincipal() {
        return atividadePrincipal;
    }

    public void setAtividadePrincipal(String atividadePrincipal) {
        this.atividadePrincipal = atividadePrincipal;
    }

    public Date getDataSituacao() {
        return dataSituacao;
    }

    public void setDataSituacao(Date dataSituacao) {
        this.dataSituacao = dataSituacao;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEfr() {
        return efr;
    }

    public void setEfr(String efr) {
        this.efr = efr;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getQsa1() {
        return qsa1;
    }

    public void setQsa1(String qsa1) {
        this.qsa1 = qsa1;
    }

    public String getQsa2() {
        return qsa2;
    }

    public void setQsa2(String qsa2) {
        this.qsa2 = qsa2;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getAbertura() {
        return abertura;
    }

    public void setAbertura(String abertura) {
        this.abertura = abertura;
    }

    public String getNaturezaJuridica() {
        return naturezaJuridica;
    }

    public void setNaturezaJuridica(String naturezaJuridica) {
        this.naturezaJuridica = naturezaJuridica;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(object instanceof Cnpj)) {
            return false;
        }
        Cnpj other = (Cnpj) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.Cnpj[ id=" + id + " ]";
    }
    
}
