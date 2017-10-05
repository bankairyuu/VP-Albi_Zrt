package vaadin;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import play.Play;
import play.inject.Injector;
import vaadin.views.ExpensesView;
import vaadin.views.UsersView;

public class MainUI extends UI {

    public static final String USERSVIEW = "users";
    public static final String EXPENSESVIEW = "expenses";

    public Navigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        MenuBar menu = new MenuBar();

        menu.addItem("Users", (menuItem) -> navigator.navigateTo(USERSVIEW));
        menu.addItem("Expenses", (menuItem) -> navigator.navigateTo(EXPENSESVIEW));

        VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.setSizeFull();

        Navigator.ComponentContainerViewDisplay viewDisplay = new Navigator.ComponentContainerViewDisplay(contentLayout);
        navigator = new Navigator(UI.getCurrent(), viewDisplay);

        Injector injector = Play.application().injector();

        navigator.addProvider(new ViewProvider() {
            @Override
            public String getViewName(String viewAndParameters) {
                return viewAndParameters;
            }

            @Override
            public View getView(String viewName) {
                if(viewName.equals(USERSVIEW)) {
                    return injector.instanceOf(UsersView.class);
                } else if(viewName.equals(EXPENSESVIEW)) {
                    return injector.instanceOf(ExpensesView.class);
                } else {
                    return new Navigator.EmptyView();
                }
            }
        });


        layout.addComponent(menu);
        layout.addComponent(contentLayout);

        setContent(layout);
    }
}
