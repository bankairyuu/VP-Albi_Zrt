package backend.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Task {
    public enum TaskStatus {
        NEW,
        IN_PROGRESS,
        COMPLETED;
    }

    @Id
    @GeneratedValue
    public Long id;

    public Date creationDate = new Date();

    @ManyToOne
    public FlatUser from;

    @ManyToOne
    public FlatUser to;

    public String name;

    public String description;

    public TaskStatus taskStatus;

    public Date plannedCompletionDate;

    public String acceptanceMessage;

    public Task(FlatUser from, FlatUser to, String description, TaskStatus taskStatus,
                Date plannedCompletionDate, String acceptanceMessage) {
        this.from = from;
        this.to = to;
        this.description = description;
        this.taskStatus = taskStatus;
        this.plannedCompletionDate = plannedCompletionDate;
        this.acceptanceMessage = acceptanceMessage;
    }

    public Task() {
    }
}