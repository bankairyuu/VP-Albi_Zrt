package vaadin.views;


import backend.models.FlatUser;
import backend.repositories.UserRepository;
import com.vaadin.data.Binder;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import vaadin.components.EditorInterface;
import vaadin.components.EntityEditor;
import vaadin.components.EntityGrid;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Arrays;

public class UsersView extends EntityGrid<FlatUser> implements View {
    private final Provider<UserEditor> userEditorProvider;

    @Inject
    public UsersView(UserRepository userRepository, Provider<UserEditor> userEditorProvider) {
        super(userRepository, FlatUser.class);
        this.userEditorProvider = userEditorProvider;
    }

    @Override
    protected void addColumns(Grid<FlatUser> grid) {
        grid.addColumn("userName");
        grid.addColumn("nickname");
        grid.addColumn("email");
        grid.addColumn("phoneNumber");
    }

    @Override
    protected EditorInterface<FlatUser> createEditor() {
        UserEditor userEditor = userEditorProvider.get();

        userEditor.init();
        return userEditor;
    }

    private static class UserEditor extends EntityEditor<FlatUser> {
        TextField userName = new TextField("User name");
        TextField name = new TextField("Name");
        TextField bankAccountNumber = new TextField("Bank account number");
        TextField phoneNumber = new TextField("Phone number");
        TextField email = new TextField("E-mail");
        TextField nickname = new TextField("Nick name");

        @Inject
        private UserEditor() {
            super(FlatUser.class);
        }

        @Override
        protected void bindFields(Binder<FlatUser> binder) {
            binder.bind(userName, "userName");
            binder.bind(name, "name");
            binder.bind(bankAccountNumber, "bankAccountNumber");
            binder.bind(phoneNumber, "phoneNumber");
            binder.bind(email, "email");
            binder.bind(nickname, "nickname");
        }

        @Override
        protected void displayFields(FormLayout formLayout) {
            Arrays.asList(userName, name, email, nickname, phoneNumber, bankAccountNumber)
                    .forEach(formLayout::addComponent);
        }
    }
}
