package vaadin.views;


import backend.models.FlatUser;
import backend.repositories.UserRepository;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import vaadin.components.EditorInterface;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Arrays;
import java.util.function.Consumer;

public class UsersView extends CustomComponent implements View {
    private final UserRepository userRepository;
    private final Provider<UserEditor> userEditorProvider;
    private Grid<FlatUser> userGrid;

    @Inject
    public UsersView(UserRepository userRepository, Provider<UserEditor> userEditorProvider) {
        this.userRepository = userRepository;
        this.userEditorProvider = userEditorProvider;

        init();
    }

    private void init() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();

        verticalLayout.setMargin(false);

        verticalLayout.addComponent(createAddUserButton());
        verticalLayout.addComponent(createGrid());

        setCompositionRoot(verticalLayout);
    }

    private Button createAddUserButton() {
        return new Button("Create user",event -> editUser(new FlatUser()));
    }

    private Grid<FlatUser> createGrid() {
        userGrid = new Grid<>(FlatUser.class);

        userGrid.setSizeFull();

        userGrid.setDataProvider(DataProvider.fromCallbacks(query -> userRepository.findAll().stream(), query -> userRepository.count()));

        userGrid.addColumn(flatUser -> "Edit",
                new ButtonRenderer<>(event -> editUser(event.getItem()))).setId("edit");

        userGrid.addColumn(flatUser -> "Delete",
                new ButtonRenderer<>(event -> {
                    userRepository.delete(event.getItem());
                    userGrid.getDataProvider().refreshAll();
                })).setId("delete");

        userGrid.setColumns("userName", "name", "nickname","edit", "delete");

        return userGrid;
    }

    private void editUser(FlatUser flatUser) {
        Window window = new Window("Edit user");

        UserEditor userEditor = userEditorProvider.get();

        userEditor.init(flatUser);
        window.setContent(userEditor);

        userEditor.setSaveCallback(savedUser-> {
            userRepository.save(savedUser);
            userGrid.getDataProvider().refreshAll();
            window.close();
        });

        getUI().addWindow(window);
        window.setModal(true);
        window.focus();
        window.setWidth("500px");
        window.setModal(true);
        window.setClosable(true);
    }

    private static class UserEditor extends CustomComponent implements EditorInterface<FlatUser> {
        Consumer<FlatUser> saveCallback;


        TextField userName = new TextField("User name");
        TextField name = new TextField("Name");
        TextField bankAccountNumber= new TextField("Bank account number");
        TextField phoneNumber= new TextField("Phone number");
        TextField email= new TextField("E-mail");
        TextField nickname= new TextField("Nick name");

        private Binder<FlatUser> binder;

        private FlatUser flatUser;

        private UserEditor() {
            binder = new Binder<>(FlatUser.class);

            binder.bind(userName, "userName");
            binder.bind(name, "name");
            binder.bind(bankAccountNumber, "bankAccountNumber");
            binder.bind(phoneNumber, "phoneNumber");
            binder.bind(email, "email");
            binder.bind(nickname, "nickname");

            initLayout();
        }

        public void init(FlatUser toEdit) {
            this.flatUser = toEdit;
            binder.readBean(toEdit);
        }

        private void initLayout() {
            FormLayout formLayout = new FormLayout();

            Arrays.asList(userName, name, email, nickname, phoneNumber, bankAccountNumber)
                    .forEach(formLayout::addComponent);

            formLayout.addComponent(new Button("Save", event -> {
                try {
                    binder.writeBean(flatUser);
                    saveCallback.accept(flatUser);
                } catch (ValidationException e) {

                }
            }));
            VerticalLayout verticalLayout = new VerticalLayout(formLayout);
            setCompositionRoot(verticalLayout);
        }

        @Override
        public void setSaveCallback(Consumer<FlatUser> saveCallback) {
            this.saveCallback = saveCallback;
        }
    }
}
