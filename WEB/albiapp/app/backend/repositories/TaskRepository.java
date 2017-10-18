package backend.repositories;

import backend.models.FlatUser;
import backend.models.Task;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import java.util.List;

public class TaskRepository extends GenericRepository<Task> {

    @Inject
    public TaskRepository(JPAApi jpaApi) {
        super(Task.class, jpaApi);
    }

    public List<Task> findByAssignee(FlatUser assignee) {
        return em().createQuery("select t from Task t where t.to = ?1")
                .setParameter(1, assignee).getResultList();
    }
}
