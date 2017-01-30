/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gladson
 */
@Entity
@Cacheable
@Table(name = "log_logradouro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogLogradouro.findAll", query = "SELECT l FROM LogLogradouro l")
    ,
    @NamedQuery(name = "LogLogradouro.findByLogNuSequencial", query = "SELECT l FROM LogLogradouro l WHERE l.logNuSequencial = :logNuSequencial")
    ,
    @NamedQuery(name = "LogLogradouro.findByUfeSg", query = "SELECT l FROM LogLogradouro l WHERE l.ufeSg = :ufeSg")
    ,
    @NamedQuery(name = "LogLogradouro.findByLogNo", query = "SELECT l FROM LogLogradouro l WHERE l.logNo like :logNo")
    ,
    @NamedQuery(name = "LogLogradouro.findByLogNome", query = "SELECT l FROM LogLogradouro l WHERE l.logNome = :logNome")
    ,
    @NamedQuery(name = "LogLogradouro.findByBaiNuSequencialFim", query = "SELECT l FROM LogLogradouro l WHERE l.baiNuSequencialFim = :baiNuSequencialFim")
    ,
    @NamedQuery(name = "LogLogradouro.findByCep", query = "SELECT l FROM LogLogradouro l WHERE l.cep = :cep")
    ,
    @NamedQuery(name = "LogLogradouro.findByLogComplemento", query = "SELECT l FROM LogLogradouro l WHERE l.logComplemento = :logComplemento")
    ,
    @NamedQuery(name = "LogLogradouro.findByLogTipoLogradouro", query = "SELECT l FROM LogLogradouro l WHERE l.logTipoLogradouro = :logTipoLogradouro")
    ,
    @NamedQuery(name = "LogLogradouro.findByLogStatusTipoLog", query = "SELECT l FROM LogLogradouro l WHERE l.logStatusTipoLog = :logStatusTipoLog")
    ,
    @NamedQuery(name = "LogLogradouro.findByLogNoSemAcento", query = "SELECT l FROM LogLogradouro l WHERE l.logNoSemAcento = :logNoSemAcento")
    ,
    @NamedQuery(name = "LogLogradouro.findByIndUop", query = "SELECT l FROM LogLogradouro l WHERE l.indUop = :indUop")
    ,
    @NamedQuery(name = "LogLogradouro.findByIndGru", query = "SELECT l FROM LogLogradouro l WHERE l.indGru = :indGru")
    ,
    @NamedQuery(name = "LogLogradouro.findByTemp", query = "SELECT l FROM LogLogradouro l WHERE l.temp = :temp")})
public class LogLogradouro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "log_nu_sequencial", nullable = false)
    private Integer logNuSequencial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "ufe_sg", nullable = false, length = 2)
    private String ufeSg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "log_no", nullable = false, length = 70)
    private String logNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 125)
    @Column(name = "log_nome", nullable = false, length = 125)
    private String logNome;
    @Column(name = "bai_nu_sequencial_fim")
    private Integer baiNuSequencialFim;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(nullable = false, length = 16)
    private String cep;
    @Size(max = 100)
    @Column(name = "log_complemento", length = 100)
    private String logComplemento;
    @Size(max = 72)
    @Column(name = "log_tipo_logradouro", length = 72)
    private String logTipoLogradouro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "log_status_tipo_log", nullable = false, length = 1)
    private String logStatusTipoLog;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "log_no_sem_acento", nullable = false, length = 70)
    private String logNoSemAcento;
    @Size(max = 1)
    @Column(name = "ind_uop", length = 1)
    private String indUop;
    @Size(max = 1)
    @Column(name = "ind_gru", length = 1)
    private String indGru;
    @Size(max = 8)
    @Column(length = 8)
    private String temp;
//    @XmlTransient
    @JoinColumn(name = "bai_nu_sequencial_ini", referencedColumnName = "bai_nu_sequencial", nullable = false)
    @ManyToOne(optional = false)
    private LogBairro baiNuSequencialIni;
//    @XmlTransient
//    @JoinColumn(name = "loc_nu_sequencial", referencedColumnName = "loc_nu_sequencial", nullable = false)
//    @ManyToOne(optional = false)
//    private LogLocalidade locNuSequencial;

    public LogLogradouro() {
    }

    public LogLogradouro(Integer logNuSequencial) {
        this.logNuSequencial = logNuSequencial;
    }

    public LogLogradouro(Integer logNuSequencial, String ufeSg, String logNo, String logNome, String cep, String logStatusTipoLog, String logNoSemAcento) {
        this.logNuSequencial = logNuSequencial;
        this.ufeSg = ufeSg;
        this.logNo = logNo;
        this.logNome = logNome;
        this.cep = cep;
        this.logStatusTipoLog = logStatusTipoLog;
        this.logNoSemAcento = logNoSemAcento;
    }

    public Integer getLogNuSequencial() {
        return logNuSequencial;
    }

    public void setLogNuSequencial(Integer logNuSequencial) {
        this.logNuSequencial = logNuSequencial;
    }

    public String getUfeSg() {
        return ufeSg;
    }

    public void setUfeSg(String ufeSg) {
        this.ufeSg = ufeSg;
    }

    public String getLogNo() {
        return logNo;
    }

    public void setLogNo(String logNo) {
        this.logNo = logNo;
    }

    public String getLogNome() {
        return logNome;
    }

    public void setLogNome(String logNome) {
        this.logNome = logNome;
    }

    public Integer getBaiNuSequencialFim() {
        return baiNuSequencialFim;
    }

    public void setBaiNuSequencialFim(Integer baiNuSequencialFim) {
        this.baiNuSequencialFim = baiNuSequencialFim;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogComplemento() {
        return logComplemento;
    }

    public void setLogComplemento(String logComplemento) {
        this.logComplemento = logComplemento;
    }

    public String getLogTipoLogradouro() {
        return logTipoLogradouro;
    }

    public void setLogTipoLogradouro(String logTipoLogradouro) {
        this.logTipoLogradouro = logTipoLogradouro;
    }

    public String getLogStatusTipoLog() {
        return logStatusTipoLog;
    }

    public void setLogStatusTipoLog(String logStatusTipoLog) {
        this.logStatusTipoLog = logStatusTipoLog;
    }

    public String getLogNoSemAcento() {
        return logNoSemAcento;
    }

    public void setLogNoSemAcento(String logNoSemAcento) {
        this.logNoSemAcento = logNoSemAcento;
    }

    public String getIndUop() {
        return indUop;
    }

    public void setIndUop(String indUop) {
        this.indUop = indUop;
    }

    public String getIndGru() {
        return indGru;
    }

    public void setIndGru(String indGru) {
        this.indGru = indGru;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public LogBairro getBaiNuSequencialIni() {
        return baiNuSequencialIni;
    }

    public void setBaiNuSequencialIni(LogBairro baiNuSequencialIni) {
        this.baiNuSequencialIni = baiNuSequencialIni;
    }
//
//    public LogLocalidade getLocNuSequencial() {
//        return locNuSequencial;
//    }
//
//    public void setLocNuSequencial(LogLocalidade locNuSequencial) {
//        this.locNuSequencial = locNuSequencial;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logNuSequencial != null ? logNuSequencial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogLogradouro)) {
            return false;
        }
        LogLogradouro other = (LogLogradouro) object;
        if ((this.logNuSequencial == null && other.logNuSequencial != null) || (this.logNuSequencial != null && !this.logNuSequencial.equals(other.logNuSequencial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.LogLogradouro[ logNuSequencial=" + logNuSequencial + " ]";
    }

}
