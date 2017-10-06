package controllers.api.models;

import backend.models.Task;

import java.util.Date;

public class ApiTask {
    public long ID;

    public Date CreationDate = new Date();

    public ApiUser From;

    public ApiUser To;

    public String Name;

    public String Description;

    public String TaskStatus;

    public Date PlannedCompletionDate;

    public String AcceptanceMessage;

    public ApiTask(Task task) {
        this.ID = task.id;
        this.CreationDate = task.creationDate;
        this.From = new ApiUser(task.from);
        this.To = new ApiUser(task.to);
        this.Name = task.name;
        this.Description = task.description;
        this.TaskStatus = task.taskStatus.name();
        this.PlannedCompletionDate = task.plannedCompletionDate;
        this.AcceptanceMessage = task.acceptanceMessage;
    }

    public ApiTask() {
    }
}
