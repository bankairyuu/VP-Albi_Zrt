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

        Task task = new Task(userRepository.findById(apiTask.from.id), userRepository.findById(apiTask.to.id),
                apiTask.description, Task.TaskStatus.valueOf(apiTask.taskStatus), apiTask.plannedCompletionDate, apiTask.acceptanceMessage);

        taskRepository.save(task);

        return ok();
    }

    //@Secured
    public Result update() {
        ApiTask apiTask = Json.fromJson(request().body().asJson(), ApiTask.class);

        Task task = new Task( userRepository.findById(apiTask.from.id), userRepository.findById(apiTask.to.id),
                apiTask.description, Task.TaskStatus.valueOf(apiTask.taskStatus), apiTask.plannedCompletionDate, apiTask.acceptanceMessage);

        task.id = apiTask.id;

        taskRepository.save(task);

        return ok();
    }

    //@Secured
    public Result delete(long userId) {
        Task task = taskRepository.findById(userId);
        taskRepository.delete(task);

        return ok();
    }
}
