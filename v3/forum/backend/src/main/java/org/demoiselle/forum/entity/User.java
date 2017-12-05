package org.demoiselle.forum.entity;

import org.demoiselle.forum.constants.Perfil;
import java.io.Serializable;
import java.util.Objects;
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
import org.hibernate.validator.constraints.Email;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
@XmlRootElement
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private String id;

    @NotNull
    @Basic(optional = false)
    @Size(min = 3, max = 128)
    @Column(unique = true, nullable = false, length = 128)
    private String firstName;

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

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
        return "User{" + "id=" + id + ", firstName=" + firstName + ", email=" + email + ", pass=" + pass + ", perfil=" + perfil + '}';
    }

}
