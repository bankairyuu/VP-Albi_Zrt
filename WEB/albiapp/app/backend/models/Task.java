package backend.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Task {
    public enum TaskStatus implements NamedEnum {
        OPEN("Open"),
        ACCEPTANCE("Acceptance"),
        IN_WORK("In work"),
        DONE("Done");

        private final String name;

        TaskStatus(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    public enum AcceptanceStatus implements NamedEnum {
        ACCEPTED("Accepted"),
        ACCEPTED_WITH_CONDITIONS("Accepted with conditions"),
        DENIED("Denied"),
        WAITING_FOR_REPLY("Waiting for reply");

        private final String name;

        AcceptanceStatus(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    @Id
    @GeneratedValue
    public Long id;

    public Date creationDate = new Date();

    @ManyToOne
    @NotNull
    public FlatUser from;

    @ManyToOne
    @NotNull
    public FlatUser to;

    @NotNull
    public String name;

    public String description;

    @NotNull
    public TaskStatus taskStatus;

    public Date requestedCompletionDate;

    public Date plannedCompletionDate;

    @NotNull
    public AcceptanceStatus acceptanceStatus;

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