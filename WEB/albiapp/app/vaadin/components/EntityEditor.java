package vaadin.components;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.*;

import javax.inject.Inject;
import java.util.function.Consumer;

public abstract class EntityEditor<T> extends CustomComponent implements EditorInterface<T>{

    protected Consumer<T> saveCallback;

    protected Binder<T> binder;
    protected T editedEntity;

    protected final Class<T> entityClass;

    private Label errorLabel = new Label();

    //inject me now
    @Inject
    public EntityEditor(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void init() {
        initBinder();
        initLayout();
    }

    protected abstract void bindFields(Binder<T> binder);
    protected abstract void displayFields(FormLayout formLayout);

    private void initBinder() {
        binder = new BeanValidationBinder<>(entityClass);
        binder.setStatusLabel(errorLabel);
        bindFields(binder);
    }

    public void load(T entityToEdit) {
        this.editedEntity = entityToEdit;
        binder.readBean(entityToEdit);
    }

    private void initLayout() {
        FormLayout formLayout = new FormLayout();

        displayFields(formLayout);

        formLayout.addComponent(new Button("Save", event -> {
            try {
                binder.writeBean(editedEntity);
                saveCallback.accept(editedEntity);
            } catch (ValidationException e) {

            }
        }));
        VerticalLayout verticalLayout = new VerticalLayout(formLayout);
        verticalLayout.addComponent(errorLabel);
        setCompositionRoot(verticalLayout);
    }

    @Override
    public void setSaveCallback(Consumer<T> saveCallback) {
        this.saveCallback = saveCallback;
    }
}
