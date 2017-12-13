package org.demoiselle.forum.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paulo
 */
@Entity
@Table
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fingerprint.findAll", query = "SELECT f FROM Fingerprint f")
    , @NamedQuery(name = "Fingerprint.findByCodigo", query = "SELECT f FROM Fingerprint f WHERE f.codigo = :codigo")
    , @NamedQuery(name = "Fingerprint.findByUsuario", query = "SELECT f FROM Fingerprint f WHERE f.usuario = :usuario")
    , @NamedQuery(name = "Fingerprint.findById", query = "SELECT f FROM Fingerprint f WHERE f.id = :id")})
public class Fingerprint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 8196)
    @Column(length = 8196)
    private String codigo;

    @Size(max = 8196)
    @Column(length = 8196)
    private String usuario;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;

    public Fingerprint() {
    }

    public Fingerprint(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Fingerprint)) {
            return false;
        }
        Fingerprint other = (Fingerprint) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pgxp.app.entity.Fingerprint[ id=" + id + " ]";
    }

}
