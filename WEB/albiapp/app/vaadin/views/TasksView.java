package vaadin.views;

import backend.models.FlatUser;
import backend.models.Task;
import backend.repositories.TaskRepository;
import backend.repositories.UserRepository;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import vaadin.components.EditorInterface;
import vaadin.components.EntityEditor;
import vaadin.components.EntityGrid;

import javax.inject.Inject;
import javax.inject.Provider;

public class TasksView extends EntityGrid<Task> implements View {

    private final Provider<TaskEditor> taskEditorProvider;

    @Inject
    public TasksView(TaskRepository taskRepository, Provider<TaskEditor> taskEditorProvider) {
        super(taskRepository, Task.class);
        this.taskEditorProvider = taskEditorProvider;
    }

    @Override
    protected void addColumns(Grid<Task> grid) {
        grid.addColumn(task -> task.creationDate).setCaption("Creadion date");
        grid.addColumn(task -> task.name).setCaption("Name");
        grid.addColumn(task -> task.from.name).setCaption("From");
        grid.addColumn(task -> task.to.name).setCaption("To");
    }

    @Override
    protected EditorInterface<Task> createEditor() {
        TaskEditor taskEditor = taskEditorProvider.get();

        taskEditor.init();

        return taskEditor;
    }

    private static class TaskEditor extends EntityEditor<Task> {

        ComboBox<FlatUser> from = new ComboBox<>("From");
        ComboBox<FlatUser> to = new ComboBox<>("To");
        TextField name = new TextField("Name");
        TextField description = new TextField("Description");
        ComboBox<Task.TaskStatus> taskStatus = new ComboBox<>("Task status");
        DateField requestedCompletionDate = new DateField();
        DateField plannedCompletionDate = new DateField();
        ComboBox<Task.AcceptanceStatus> acceptanceStatus = new ComboBox<>("Acceptance status");
        TextField acceptanceMessage = new TextField("Acceptance message");

        final UserRepository userRepository;

        @Inject
        public TaskEditor(UserRepository userRepository) {
            super(Task.class);
            this.userRepository = userRepository;
        }

        @Override
        protected void bindFields(Binder<Task> binder) {
            binder.bind(from, "from");
            binder.bind(to, "to");
            binder.bind(name, "name");
            binder.bind(description, "description");
            binder.bind(requestedCompletionDate, "requestedCompletionDate");
            binder.bind(plannedCompletionDate, "plannedCompletionDate");
            binder.bind(taskStatus, "taskStatus");
            binder.bind(acceptanceStatus, "acceptanceStatus");
            binder.bind(acceptanceMessage, "acceptanceMessage");
        }

        @Override
        protected void displayFields(FormLayout formLayout) {
            from.setItems(userRepository.findAll());
            from.setItemCaptionGenerator(item -> item.name);
            to.setItems(userRepository.findAll());
            to.setItemCaptionGenerator(item -> item.name);
            acceptanceStatus.setItemCaptionGenerator(Task.AcceptanceStatus::getName);
            acceptanceStatus.setItems(Task.AcceptanceStatus.values());

            taskStatus.setItems(Task.TaskStatus.values());
            taskStatus.setItemCaptionGenerator(Task.TaskStatus::getName);

            formLayout.addComponents(from, to, name, description, requestedCompletionDate,
                    acceptanceStatus, acceptanceMessage,
                    taskStatus, plannedCompletionDate);
        }
    }
}
