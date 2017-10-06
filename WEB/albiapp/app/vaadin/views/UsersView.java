package vaadin.views;


import backend.models.FlatUser;
import backend.repositories.UserRepository;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import javax.inject.Inject;

public class UsersView extends CustomComponent implements View {
    private final UserRepository userRepository;
    private Grid<FlatUser> userGrid;

    @Inject
    public UsersView(UserRepository userRepository) {
        this.userRepository = userRepository;

        init();
    }

    private void init() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();

        verticalLayout.setMargin(false);

        verticalLayout.addComponent(createGrid());

        setCompositionRoot(verticalLayout);
    }

    private Grid<FlatUser> createGrid() {
        userGrid = new Grid<>(FlatUser.class);

        userGrid.setSizeFull();

        userGrid.setDataProvider(DataProvider.fromCallbacks(query -> userRepository.findAll().stream(), query -> userRepository.count()));

        userGrid.setColumns("userName", "nickname");


        return userGrid;
    }

    private void editUser(FlatUser flatUser) {

    }

    private static class UserEditor {

    }
}
