package app.entity;

import app.constants.Status;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import static jakarta.persistence.FetchType.EAGER;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import static jakarta.persistence.TemporalType.DATE;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "todo")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = getLogger(Todo.class.getName());

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private String id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(nullable = false, length = 128)
    private String description;

    private Status status;

    @Future
    @Temporal(DATE)
    private Date dateEnd;

    private int changed;

    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = EAGER)
    private User user;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Todo other = (Todo) obj;
        return Objects.equals(this.id, other.id);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public Date getDateEnd() { return dateEnd; }
    public void setDateEnd(Date dateEnd) { this.dateEnd = dateEnd; }
    public int getChanged() { return changed; }
    public void setChanged(int changed) { this.changed = changed; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
