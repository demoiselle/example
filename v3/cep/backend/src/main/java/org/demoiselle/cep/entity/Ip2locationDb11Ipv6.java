/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.cep.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 70744416353
 */
@Entity
@Table(name = "ip2location_db11_ipv6")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ip2locationDb11Ipv6.findAll", query = "SELECT i FROM Ip2locationDb11Ipv6 i")
    , @NamedQuery(name = "Ip2locationDb11Ipv6.findByIpFrom", query = "SELECT i FROM Ip2locationDb11Ipv6 i WHERE i.ipFrom = :ipFrom")
    , @NamedQuery(name = "Ip2locationDb11Ipv6.findByIpTo", query = "SELECT i FROM Ip2locationDb11Ipv6 i WHERE i.ipTo = :ipTo")
    , @NamedQuery(name = "Ip2locationDb11Ipv6.findByCountryCode", query = "SELECT i FROM Ip2locationDb11Ipv6 i WHERE i.countryCode = :countryCode")
    , @NamedQuery(name = "Ip2locationDb11Ipv6.findByCountryName", query = "SELECT i FROM Ip2locationDb11Ipv6 i WHERE i.countryName = :countryName")
    , @NamedQuery(name = "Ip2locationDb11Ipv6.findByRegionName", query = "SELECT i FROM Ip2locationDb11Ipv6 i WHERE i.regionName = :regionName")
    , @NamedQuery(name = "Ip2locationDb11Ipv6.findByCityName", query = "SELECT i FROM Ip2locationDb11Ipv6 i WHERE i.cityName = :cityName")
    , @NamedQuery(name = "Ip2locationDb11Ipv6.findByLatitude", query = "SELECT i FROM Ip2locationDb11Ipv6 i WHERE i.latitude = :latitude")
    , @NamedQuery(name = "Ip2locationDb11Ipv6.findByLongitude", query = "SELECT i FROM Ip2locationDb11Ipv6 i WHERE i.longitude = :longitude")
    , @NamedQuery(name = "Ip2locationDb11Ipv6.findByZipCode", query = "SELECT i FROM Ip2locationDb11Ipv6 i WHERE i.zipCode = :zipCode")
    , @NamedQuery(name = "Ip2locationDb11Ipv6.findByTimeZone", query = "SELECT i FROM Ip2locationDb11Ipv6 i WHERE i.timeZone = :timeZone")
    , @NamedQuery(name = "Ip2locationDb11Ipv6.findById", query = "SELECT i FROM Ip2locationDb11Ipv6 i WHERE i.id = :id")})
public class Ip2locationDb11Ipv6 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "ip_from")
    private BigInteger ipFrom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ip_to", nullable = false)
    private BigInteger ipTo;
    @Size(max = 2)
    @Column(name = "country_code", length = 2)
    private String countryCode;
    @Size(max = 64)
    @Column(name = "country_name", length = 64)
    private String countryName;
    @Size(max = 128)
    @Column(name = "region_name", length = 128)
    private String regionName;
    @Size(max = 128)
    @Column(name = "city_name", length = 128)
    private String cityName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double latitude;
    @Column(precision = 22)
    private Double longitude;
    @Size(max = 30)
    @Column(name = "zip_code", length = 30)
    private String zipCode;
    @Size(max = 8)
    @Column(name = "time_zone", length = 8)
    private String timeZone;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;

    public Ip2locationDb11Ipv6() {
    }

    public Ip2locationDb11Ipv6(Integer id) {
        this.id = id;
    }

    public Ip2locationDb11Ipv6(Integer id, BigInteger ipTo) {
        this.id = id;
        this.ipTo = ipTo;
    }

    public BigInteger getIpFrom() {
        return ipFrom;
    }

    public void setIpFrom(BigInteger ipFrom) {
        this.ipFrom = ipFrom;
    }

    public BigInteger getIpTo() {
        return ipTo;
    }

    public void setIpTo(BigInteger ipTo) {
        this.ipTo = ipTo;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        if (!(object instanceof Ip2locationDb11Ipv6)) {
            return false;
        }
        Ip2locationDb11Ipv6 other = (Ip2locationDb11Ipv6) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.Ip2locationDb11Ipv6[ id=" + id + " ]";
    }

}
