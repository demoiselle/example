package app.entity;

import app.constants.Perfil;
import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author gladson
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = getLogger(User.class.getName());

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

    @Column
    private Perfil perfil;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        return Objects.equals(this.id, other.id);
    }

}
