/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento.entity;

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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gladson
 */
@Entity
@Table(catalog = "estacionamento", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"year", "make", "model"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VehicleModelYear.findAll", query = "SELECT v FROM VehicleModelYear v"),
    @NamedQuery(name = "VehicleModelYear.findById", query = "SELECT v FROM VehicleModelYear v WHERE v.id = :id"),
    @NamedQuery(name = "VehicleModelYear.findByYear", query = "SELECT v FROM VehicleModelYear v WHERE v.year = :year"),
    @NamedQuery(name = "VehicleModelYear.findByMake", query = "SELECT v FROM VehicleModelYear v WHERE v.make = :make"),
    @NamedQuery(name = "VehicleModelYear.findByModel", query = "SELECT v FROM VehicleModelYear v WHERE v.model = :model")})
public class VehicleModelYear implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int year;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String make;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String model;

    public VehicleModelYear() {
    }

    public VehicleModelYear(Integer id) {
        this.id = id;
    }

    public VehicleModelYear(Integer id, int year, String make, String model) {
        this.id = id;
        this.year = year;
        this.make = make;
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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
        if (!(object instanceof VehicleModelYear)) {
            return false;
        }
        VehicleModelYear other = (VehicleModelYear) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "estacionamento.entity.VehicleModelYear[ id=" + id + " ]";
    }
    
}
