package backend.repositories;

import backend.models.Task;
import play.db.jpa.JPAApi;

import javax.inject.Inject;

public class TaskRepository extends GenericRepository<Task> {

    @Inject
    public TaskRepository(JPAApi jpaApi) {
        super(Task.class, jpaApi);
    }
}
