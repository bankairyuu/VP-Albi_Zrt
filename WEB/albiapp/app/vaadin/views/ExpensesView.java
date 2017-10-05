package vaadin.views;

import backend.models.Expense;
import backend.models.FlatUser;
import backend.repositories.ExpenseRepository;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import javax.inject.Inject;

public class ExpensesView extends CustomComponent implements View {
    private final ExpenseRepository expenseRepository;
    private Grid<Expense> expenseGrid;

    @Inject
    public ExpensesView(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
        init();
    }

    private void init() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();

        verticalLayout.setMargin(false);

        verticalLayout.addComponent(createGrid());

        setCompositionRoot(verticalLayout);
    }

    private Grid<Expense> createGrid() {
        expenseGrid = new Grid<>(Expense.class);

        expenseGrid.setSizeFull();

        expenseGrid.setDataProvider(DataProvider.fromCallbacks(query -> expenseRepository.findAll().stream(), query -> expenseRepository.count()));

        expenseGrid.removeAllColumns();

        expenseGrid.addColumn(expense -> expense.from.userName).setCaption("From").setId("from");
        expenseGrid.addColumn(expense -> expense.to.userName).setCaption("To").setId("to");
        expenseGrid.addColumn(expense -> expense.amount).setCaption("Amount").setId("amount");


        return expenseGrid;
    }

    private void editUser(FlatUser flatUser) {

    }
}
