package vaadin.menu;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class Menu extends CustomComponent {

    private HorizontalLayout horizontalLayout;

    public Menu() {
        horizontalLayout = new HorizontalLayout();

        horizontalLayout.setSizeFull();
        horizontalLayout.setSpacing(true);

        horizontalLayout.setSpacing(false);
        horizontalLayout.setMargin(false);

        setSizeUndefined();
        setCompositionRoot(horizontalLayout);
    }

    public void addButton(String caption, String view) {
        Button button = new Button(caption, (Button.ClickListener)
                evet -> getUI().getNavigator().navigateTo(view));

        horizontalLayout.addComponent(button);
    }

    private String getLogoutPath() {
        return getUI().getPage().getLocation().getPath();
    }

}