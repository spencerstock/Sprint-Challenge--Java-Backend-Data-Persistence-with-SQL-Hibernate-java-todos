package local.sgs.javatodos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.domain.Auditable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

@Entity
@Table(name = "todos")
public class Todo implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;

    @Column(nullable = false)
    private String description;

    private Timestamp datestarted;

    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",
                nullable = false)
    @JsonIgnoreProperties({"todos", "hibernateLazyInitializer"})
    private User user;

    public Todo()
    {
    }

    public Todo(String description, String datestarted, User user)
    {
        this.description = description;
        this.datestarted = Timestamp.valueOf(datestarted);
        this.completed = false;
        this.user = user;
    }

    public long getTodoid()
    {
        return todoid;
    }

    public void setTodoid(long todoid)
    {
        this.todoid = todoid;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDatestarted()
    {
        return datestarted.toString();
    }

    public void setDatestarted(String datestarted)
    {
        this.datestarted = Timestamp.valueOf(datestarted);
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public Optional getCreatedBy() {
        return Optional.empty();
    }

    @Override
    public void setCreatedBy(Object o) {

    }

    @Override
    public Optional getCreatedDate() {
        return Optional.empty();
    }

    @Override
    public void setCreatedDate(TemporalAccessor temporalAccessor) {

    }

    @Override
    public Optional getLastModifiedBy() {
        return Optional.empty();
    }

    @Override
    public void setLastModifiedBy(Object o) {

    }

    @Override
    public Optional getLastModifiedDate() {
        return Optional.empty();
    }

    @Override
    public void setLastModifiedDate(TemporalAccessor temporalAccessor) {

    }

    @Override
    public Object getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}