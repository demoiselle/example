package org.demoiselle.forum.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author PauloGladson
 */
@Entity
@XmlRootElement
@Table(name = "mensagem")
public class Mensagem implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private UUID id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User usuario;

    @JoinColumn(name = "topico_id")
    @ManyToOne
    private Topico topico;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(nullable = false, length = 128)
    private String description;

    @Temporal(TIMESTAMP)
    private Date datahora;

    /**
     *
     * @return
     */
    public UUID getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public User getUsuario() {
        return usuario;
    }

    /**
     *
     * @param usuario
     */
    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    /**
     *
     * @return
     */
    public Topico getTopico() {
        return topico;
    }

    /**
     *
     * @param topico
     */
    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    /**
     *
     * @return
     */
    public Date getDatahora() {
        return datahora;
    }

    /**
     *
     * @param datahora
     */
    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mensagem other = (Mensagem) obj;
        return Objects.equals(this.id, other.id);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Mensagem{" + "id=" + id + ", description=" + description + '}';
    }
    private static final Logger LOG = Logger.getLogger(Mensagem.class.getName());
}
