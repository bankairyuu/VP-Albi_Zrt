package vaadin;

import backend.repositories.UserRepository;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import play.Play;

import javax.inject.Inject;

public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        // add Grid to the layout
        layout.addComponents(new Label("It works"));

        UserRepository userRepository = Play.application().injector().instanceOf(UserRepository.class);



        setContent(layout);
    }
}
