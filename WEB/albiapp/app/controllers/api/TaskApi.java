package controllers.api;

import backend.models.Task;
import backend.repositories.TaskRepository;
import backend.repositories.UserRepository;
import controllers.api.models.ApiTask;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.stream.Collectors;

@Transactional
public class TaskApi extends Controller {
    @Inject
    private TaskRepository taskRepository;

    @Inject
    private UserRepository userRepository;

    public Result list() {
        return ok(Json.toJson(taskRepository.findAll().stream().map(ApiTask::new).collect(Collectors.toList())));
    }

    //@Secured
    public Result create() {

        ApiTask apiTask = Json.fromJson(request().body().asJson(), ApiTask.class);

        Task task = new Task(userRepository.findById(apiTask.From.ID), userRepository.findById(apiTask.To.ID),
                apiTask.Description, Task.TaskStatus.valueOf(apiTask.TaskStatus), apiTask.PlannedCompletionDate, apiTask.AcceptanceMessage);

        taskRepository.save(task);

        return ok();
    }

    //@Secured
    public Result update() {
        ApiTask apiTask = Json.fromJson(request().body().asJson(), ApiTask.class);

        Task task = new Task( userRepository.findById(apiTask.From.ID), userRepository.findById(apiTask.To.ID),
                apiTask.Description, Task.TaskStatus.valueOf(apiTask.TaskStatus), apiTask.PlannedCompletionDate, apiTask.AcceptanceMessage);

        task.id = apiTask.ID;

        taskRepository.save(task);

        return ok();
    }

    //@Secured
    public Result delete(long taskId) {
        Task task = taskRepository.findById(taskId);
        taskRepository.delete(task);

        return ok();
    }
}
