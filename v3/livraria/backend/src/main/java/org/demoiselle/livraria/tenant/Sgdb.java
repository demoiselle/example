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
    @NamedQuery(name = "Sgdb.findAll", query = "SELECT s FROM Sgdb s")
    , @NamedQuery(name = "Sgdb.findById", query = "SELECT s FROM Sgdb s WHERE s.id = :id")
    , @NamedQuery(name = "Sgdb.findByDia", query = "SELECT s FROM Sgdb s WHERE s.dia = :dia")
    , @NamedQuery(name = "Sgdb.findByComando", query = "SELECT s FROM Sgdb s WHERE s.comando = :comando")})
public class Sgdb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dia;
    @Size(max = 10240)
    @Column(length = 10240)
    private String comando;

    public Sgdb() {
    }

    public Sgdb(Integer id) {
        this.id = id;
    }

    public Sgdb(Integer id, Date dia) {
        this.id = id;
        this.dia = dia;
    }

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
        this.dia = dia;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
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
        if (!(object instanceof Sgdb)) {
            return false;
        }
        Sgdb other = (Sgdb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.demoiselle.livraria.tenant.Sgdb[ id=" + id + " ]";
    }
    
}
