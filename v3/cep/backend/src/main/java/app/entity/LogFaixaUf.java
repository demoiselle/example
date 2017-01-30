/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gladson
 */
@Entity
@Table(name = "log_faixa_uf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogFaixaUf.findAll", query = "SELECT l FROM LogFaixaUf l")
    , @NamedQuery(name = "LogFaixaUf.findByUfeSg", query = "SELECT l FROM LogFaixaUf l WHERE l.ufeSg = :ufeSg")
    , @NamedQuery(name = "LogFaixaUf.findByUfeNo", query = "SELECT l FROM LogFaixaUf l WHERE l.ufeNo = :ufeNo")
    , @NamedQuery(name = "LogFaixaUf.findByUfeRad1Ini", query = "SELECT l FROM LogFaixaUf l WHERE l.ufeRad1Ini = :ufeRad1Ini")
    , @NamedQuery(name = "LogFaixaUf.findByUfeSuf1Ini", query = "SELECT l FROM LogFaixaUf l WHERE l.ufeSuf1Ini = :ufeSuf1Ini")
    , @NamedQuery(name = "LogFaixaUf.findByUfeRad1Fim", query = "SELECT l FROM LogFaixaUf l WHERE l.ufeRad1Fim = :ufeRad1Fim")
    , @NamedQuery(name = "LogFaixaUf.findByUfeSuf1Fim", query = "SELECT l FROM LogFaixaUf l WHERE l.ufeSuf1Fim = :ufeSuf1Fim")
    , @NamedQuery(name = "LogFaixaUf.findByUfeRad2Ini", query = "SELECT l FROM LogFaixaUf l WHERE l.ufeRad2Ini = :ufeRad2Ini")
    , @NamedQuery(name = "LogFaixaUf.findByUfeSuf2Ini", query = "SELECT l FROM LogFaixaUf l WHERE l.ufeSuf2Ini = :ufeSuf2Ini")
    , @NamedQuery(name = "LogFaixaUf.findByUfeRad2Fim", query = "SELECT l FROM LogFaixaUf l WHERE l.ufeRad2Fim = :ufeRad2Fim")
    , @NamedQuery(name = "LogFaixaUf.findByUfeSuf2Fim", query = "SELECT l FROM LogFaixaUf l WHERE l.ufeSuf2Fim = :ufeSuf2Fim")})
public class LogFaixaUf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "ufe_sg", nullable = false, length = 2)
    private String ufeSg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 72)
    @Column(name = "ufe_no", nullable = false, length = 72)
    private String ufeNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ufe_rad1_ini", nullable = false, length = 5)
    private String ufeRad1Ini;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "ufe_suf1_ini", nullable = false, length = 3)
    private String ufeSuf1Ini;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ufe_rad1_fim", nullable = false, length = 5)
    private String ufeRad1Fim;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "ufe_suf1_fim", nullable = false, length = 3)
    private String ufeSuf1Fim;
    @Size(max = 5)
    @Column(name = "ufe_rad2_ini", length = 5)
    private String ufeRad2Ini;
    @Size(max = 3)
    @Column(name = "ufe_suf2_ini", length = 3)
    private String ufeSuf2Ini;
    @Size(max = 5)
    @Column(name = "ufe_rad2_fim", length = 5)
    private String ufeRad2Fim;
    @Size(max = 3)
    @Column(name = "ufe_suf2_fim", length = 3)
    private String ufeSuf2Fim;
//    @OneToMany(mappedBy = "ufeSg")
//    private List<LogLocalidade> logLocalidadeList;

    public LogFaixaUf() {
    }

    public LogFaixaUf(String ufeSg) {
        this.ufeSg = ufeSg;
    }

    public LogFaixaUf(String ufeSg, String ufeNo, String ufeRad1Ini, String ufeSuf1Ini, String ufeRad1Fim, String ufeSuf1Fim) {
        this.ufeSg = ufeSg;
        this.ufeNo = ufeNo;
        this.ufeRad1Ini = ufeRad1Ini;
        this.ufeSuf1Ini = ufeSuf1Ini;
        this.ufeRad1Fim = ufeRad1Fim;
        this.ufeSuf1Fim = ufeSuf1Fim;
    }

    public String getUfeSg() {
        return ufeSg;
    }

    public void setUfeSg(String ufeSg) {
        this.ufeSg = ufeSg;
    }

    public String getUfeNo() {
        return ufeNo;
    }

    public void setUfeNo(String ufeNo) {
        this.ufeNo = ufeNo;
    }

    public String getUfeRad1Ini() {
        return ufeRad1Ini;
    }

    public void setUfeRad1Ini(String ufeRad1Ini) {
        this.ufeRad1Ini = ufeRad1Ini;
    }

    public String getUfeSuf1Ini() {
        return ufeSuf1Ini;
    }

    public void setUfeSuf1Ini(String ufeSuf1Ini) {
        this.ufeSuf1Ini = ufeSuf1Ini;
    }

    public String getUfeRad1Fim() {
        return ufeRad1Fim;
    }

    public void setUfeRad1Fim(String ufeRad1Fim) {
        this.ufeRad1Fim = ufeRad1Fim;
    }

    public String getUfeSuf1Fim() {
        return ufeSuf1Fim;
    }

    public void setUfeSuf1Fim(String ufeSuf1Fim) {
        this.ufeSuf1Fim = ufeSuf1Fim;
    }

    public String getUfeRad2Ini() {
        return ufeRad2Ini;
    }

    public void setUfeRad2Ini(String ufeRad2Ini) {
        this.ufeRad2Ini = ufeRad2Ini;
    }

    public String getUfeSuf2Ini() {
        return ufeSuf2Ini;
    }

    public void setUfeSuf2Ini(String ufeSuf2Ini) {
        this.ufeSuf2Ini = ufeSuf2Ini;
    }

    public String getUfeRad2Fim() {
        return ufeRad2Fim;
    }

    public void setUfeRad2Fim(String ufeRad2Fim) {
        this.ufeRad2Fim = ufeRad2Fim;
    }

    public String getUfeSuf2Fim() {
        return ufeSuf2Fim;
    }

    public void setUfeSuf2Fim(String ufeSuf2Fim) {
        this.ufeSuf2Fim = ufeSuf2Fim;
    }

//    public List<LogLocalidade> getLogLocalidadeList() {
//        return logLocalidadeList;
//    }
//
//    public void setLogLocalidadeList(List<LogLocalidade> logLocalidadeList) {
//        this.logLocalidadeList = logLocalidadeList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ufeSg != null ? ufeSg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogFaixaUf)) {
            return false;
        }
        LogFaixaUf other = (LogFaixaUf) object;
        if ((this.ufeSg == null && other.ufeSg != null) || (this.ufeSg != null && !this.ufeSg.equals(other.ufeSg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.LogFaixaUf[ ufeSg=" + ufeSg + " ]";
    }
    
}
