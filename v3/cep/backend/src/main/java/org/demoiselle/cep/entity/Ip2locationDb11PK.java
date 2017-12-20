/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.cep.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author paulo
 */
@Embeddable
public class Ip2locationDb11PK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ip_from", nullable = false)
    private long ipFrom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ip_to", nullable = false)
    private long ipTo;

    public Ip2locationDb11PK() {
    }

    public Ip2locationDb11PK(long ipFrom, long ipTo) {
        this.ipFrom = ipFrom;
        this.ipTo = ipTo;
    }

    public long getIpFrom() {
        return ipFrom;
    }

    public void setIpFrom(long ipFrom) {
        this.ipFrom = ipFrom;
    }

    public long getIpTo() {
        return ipTo;
    }

    public void setIpTo(long ipTo) {
        this.ipTo = ipTo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ipFrom;
        hash += (int) ipTo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ip2locationDb11PK)) {
            return false;
        }
        Ip2locationDb11PK other = (Ip2locationDb11PK) object;
        if (this.ipFrom != other.ipFrom) {
            return false;
        }
        if (this.ipTo != other.ipTo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.demoiselle.cep.entity.Ip2locationDb11PK[ ipFrom=" + ipFrom + ", ipTo=" + ipTo + " ]";
    }
    
}
