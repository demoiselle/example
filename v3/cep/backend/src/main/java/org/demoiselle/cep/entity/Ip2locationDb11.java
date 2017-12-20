/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.cep.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paulo
 */
@Entity
@Table(name = "ip2location_db11")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ip2locationDb11.findAll", query = "SELECT i FROM Ip2locationDb11 i")
    , @NamedQuery(name = "Ip2locationDb11.findByIpFrom", query = "SELECT i FROM Ip2locationDb11 i WHERE i.ip2locationDb11PK.ipFrom = :ipFrom")
    , @NamedQuery(name = "Ip2locationDb11.findByIpTo", query = "SELECT i FROM Ip2locationDb11 i WHERE i.ip2locationDb11PK.ipTo = :ipTo")
    , @NamedQuery(name = "Ip2locationDb11.findByCountryCode", query = "SELECT i FROM Ip2locationDb11 i WHERE i.countryCode = :countryCode")
    , @NamedQuery(name = "Ip2locationDb11.findByCountryName", query = "SELECT i FROM Ip2locationDb11 i WHERE i.countryName = :countryName")
    , @NamedQuery(name = "Ip2locationDb11.findByRegionName", query = "SELECT i FROM Ip2locationDb11 i WHERE i.regionName = :regionName")
    , @NamedQuery(name = "Ip2locationDb11.findByCityName", query = "SELECT i FROM Ip2locationDb11 i WHERE i.cityName = :cityName")
    , @NamedQuery(name = "Ip2locationDb11.findByLatitude", query = "SELECT i FROM Ip2locationDb11 i WHERE i.latitude = :latitude")
    , @NamedQuery(name = "Ip2locationDb11.findByLongitude", query = "SELECT i FROM Ip2locationDb11 i WHERE i.longitude = :longitude")
    , @NamedQuery(name = "Ip2locationDb11.findByZipCode", query = "SELECT i FROM Ip2locationDb11 i WHERE i.zipCode = :zipCode")
    , @NamedQuery(name = "Ip2locationDb11.findByTimeZone", query = "SELECT i FROM Ip2locationDb11 i WHERE i.timeZone = :timeZone")})
public class Ip2locationDb11 implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Ip2locationDb11PK ip2locationDb11PK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "country_code", nullable = false, length = 2)
    private String countryCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "country_name", nullable = false, length = 64)
    private String countryName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "region_name", nullable = false, length = 128)
    private String regionName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "city_name", nullable = false, length = 128)
    private String cityName;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private float latitude;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private float longitude;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "zip_code", nullable = false, length = 30)
    private String zipCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "time_zone", nullable = false, length = 8)
    private String timeZone;

    public Ip2locationDb11() {
    }

    public Ip2locationDb11(Ip2locationDb11PK ip2locationDb11PK) {
        this.ip2locationDb11PK = ip2locationDb11PK;
    }

    public Ip2locationDb11(Ip2locationDb11PK ip2locationDb11PK, String countryCode, String countryName, String regionName, String cityName, float latitude, float longitude, String zipCode, String timeZone) {
        this.ip2locationDb11PK = ip2locationDb11PK;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.regionName = regionName;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zipCode = zipCode;
        this.timeZone = timeZone;
    }

    public Ip2locationDb11(long ipFrom, long ipTo) {
        this.ip2locationDb11PK = new Ip2locationDb11PK(ipFrom, ipTo);
    }

    public Ip2locationDb11PK getIp2locationDb11PK() {
        return ip2locationDb11PK;
    }

    public void setIp2locationDb11PK(Ip2locationDb11PK ip2locationDb11PK) {
        this.ip2locationDb11PK = ip2locationDb11PK;
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

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ip2locationDb11PK != null ? ip2locationDb11PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ip2locationDb11)) {
            return false;
        }
        Ip2locationDb11 other = (Ip2locationDb11) object;
        if ((this.ip2locationDb11PK == null && other.ip2locationDb11PK != null) || (this.ip2locationDb11PK != null && !this.ip2locationDb11PK.equals(other.ip2locationDb11PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.demoiselle.cep.entity.Ip2locationDb11[ ip2locationDb11PK=" + ip2locationDb11PK + " ]";
    }
    
}
