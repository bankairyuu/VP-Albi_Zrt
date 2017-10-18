package vaadin.components;

import com.vaadin.data.*;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.inject.Inject;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
        errorLabel.setContentMode(ContentMode.HTML);
        bindFields(binder);

        BinderValidationStatusHandler defaultHandler = binder.getValidationStatusHandler();

        binder.setValidationStatusHandler(status -> {
            // create an error message on failed bean level validations
            List<ValidationResult> errors = status.getBeanValidationErrors();

            // collect all bean level error messages into a single string,
            // separating each message with a <br> tag
            String errorMessage = errors.stream().map(ValidationResult::getErrorMessage)
                    // sanitize the individual error strings to avoid code injection
                    // since we are displaying the resulting string as HTML
                    .map(errorString -> Jsoup.clean(errorString, Whitelist.simpleText()))
                    .collect(Collectors.joining("<br>"));

            // finally, display all bean level validation errors in a single label
            errorLabel.setValue(errorMessage);
            errorLabel.setVisible(!errorMessage.isEmpty());

            // Let the default handler show messages for each field
            defaultHandler.statusChange(status);
        });
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
