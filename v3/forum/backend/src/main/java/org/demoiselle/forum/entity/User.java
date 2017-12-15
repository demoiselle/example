package org.demoiselle.forum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.demoiselle.forum.constants.Perfil;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author 70744416353
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
@XmlRootElement
public class User implements Serializable {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @NotNull
    @Basic(optional = false)
    @Size(min = 3, max = 128)
    @Column(nullable = false, length = 128)
    private String description;

    @Email
    @NotNull
    @Basic(optional = false)
    @Size(min = 5, max = 128)
    @Column(name = "email", nullable = false, length = 128, unique = true)
    private String email;

    @NotNull
    @Basic(optional = false)
    @Size(min = 8, max = 128)
    @Column(length = 128)
    private String pass;

    @Size(max = 2048)
    @Column(length = 2048)
    private String foto;

    @Column
    private Perfil perfil;

    /**
     *
     * @return
     */
    public UUID getId() {
        return id;
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
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String getPass() {
        return pass;
    }

    /**
     *
     * @param pass
     */
    @JsonIgnore
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     *
     * @return
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     *
     * @param perfil
     */
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    /**
     *
     * @return
     */
    public String getFoto() {
        return foto;
    }

    /**
     *
     * @param foto
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", description=" + description + ", email=" + email + ", pass=" + pass + ", perfil=" + perfil + '}';
    }

}
