package app.entity;

import app.constants.Perfil;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import static java.util.Collections.unmodifiableSet;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author gladson
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    @Column
    private Perfil perfil;

    @OneToMany(mappedBy = "user", targetEntity = Todo.class, fetch = EAGER, orphanRemoval = true)
    private Set<Todo> todos = new HashSet<>();

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

    public Set<Todo> getTodos() {
        return unmodifiableSet(todos);
    }

    public void setTodos(Set<Todo> todos) {
        this.todos = todos;
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
