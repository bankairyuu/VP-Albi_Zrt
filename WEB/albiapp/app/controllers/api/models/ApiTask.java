package controllers.api.models;

import backend.models.Task;

import java.util.Date;

public class ApiTask {
    public long id;

    public Date creationDate = new Date();

    public ApiUser from;

    public ApiUser to;

    public String description;

    public String taskStatus;

    public Date plannedCompletionDate;

    public String acceptanceMessage;

    public ApiTask(Task task) {
        this.id = task.id;
        this.creationDate = task.creationDate;
        this.from = new ApiUser(task.from);
        this.to = new ApiUser(task.to);
        this.description = task.description;
        this.taskStatus = task.taskStatus.name();
        this.plannedCompletionDate = task.plannedCompletionDate;
        this.acceptanceMessage = task.acceptanceMessage;
    }

    public ApiTask() {
    }
}
