package org.demoiselle.forum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.demoiselle.forum.constants.Perfil;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author 70744416353
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
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

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @JsonIgnore
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

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

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", description=" + description + ", email=" + email + ", pass=" + pass + ", perfil=" + perfil + '}';
    }
}
