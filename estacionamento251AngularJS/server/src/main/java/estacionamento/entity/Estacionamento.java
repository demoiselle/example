/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gladson
 */
@Entity
@Table(name = "estacionamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estacionamento.findAll", query = "SELECT e FROM Estacionamento e"),
    @NamedQuery(name = "Estacionamento.findById", query = "SELECT e FROM Estacionamento e WHERE e.id = :id"),
    @NamedQuery(name = "Estacionamento.findByVeiculo", query = "SELECT e FROM Estacionamento e WHERE e.veiculo = :veiculo"),
    @NamedQuery(name = "Estacionamento.findByEntrada", query = "SELECT e FROM Estacionamento e WHERE e.entrada = :entrada"),
    @NamedQuery(name = "Estacionamento.findBySaida", query = "SELECT e FROM Estacionamento e WHERE e.saida = :saida"),
    @NamedQuery(name = "Estacionamento.findByValor", query = "SELECT e FROM Estacionamento e WHERE e.valor = :valor"),
    @NamedQuery(name = "Estacionamento.findByRecebido", query = "SELECT e FROM Estacionamento e WHERE e.recebido = :recebido"),
    @NamedQuery(name = "Estacionamento.findByVaga", query = "SELECT e FROM Estacionamento e WHERE e.vaga = :vaga")})
public class Estacionamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @ManyToOne
    private Veiculo veiculo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date entrada;
    @Temporal(TemporalType.TIMESTAMP)
    private Date saida;
    private Long valor;
    private Long recebido;
    @Size(max = 45)
    @Column(length = 45)
    private String vaga;

    /**
     *
     */
    public Estacionamento() {
    }

    /**
     *
     * @param id
     */
    public Estacionamento(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     *
     * @return
     */
    public Date getEntrada() {
        return entrada;
    }

    /**
     *
     * @param entrada
     */
    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    /**
     *
     * @return
     */
    public Date getSaida() {
        return saida;
    }

    /**
     *
     * @param saida
     */
    public void setSaida(Date saida) {
        this.saida = saida;
    }

    /**
     *
     * @return
     */
    public Long getValor() {
        return valor;
    }

    /**
     *
     * @param valor
     */
    public void setValor(Long valor) {
        this.valor = valor;
    }

    /**
     *
     * @return
     */
    public Long getRecebido() {
        return recebido;
    }

    /**
     *
     * @param recebido
     */
    public void setRecebido(Long recebido) {
        this.recebido = recebido;
    }

    /**
     *
     * @return
     */
    public String getVaga() {
        return vaga;
    }

    /**
     *
     * @param vaga
     */
    public void setVaga(String vaga) {
        this.vaga = vaga;
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
        if (!(object instanceof Estacionamento)) {
            return false;
        }
        Estacionamento other = (Estacionamento) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "estacionamento.entity.Estacionamento[ id=" + id + " ]";
    }

}
