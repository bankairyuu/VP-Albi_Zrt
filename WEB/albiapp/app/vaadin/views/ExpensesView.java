package vaadin.views;

import backend.models.Expense;
import backend.models.FlatUser;
import backend.repositories.ExpenseRepository;
import backend.repositories.UserRepository;
import backend.services.ExpenseService;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import vaadin.components.EditorInterface;
import vaadin.components.EntityEditor;
import vaadin.components.EntityGrid;

import javax.inject.Inject;
import javax.inject.Provider;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Consumer;

public class ExpensesView extends EntityGrid<Expense> implements View {
    private final ExpenseService expenseService;

    private final Provider<ExpenseEditor> expenseEditorProvider;

    @Inject
    public ExpensesView(ExpenseRepository expenseRepository, ExpenseService expenseService, Provider<ExpenseEditor> expenseEditorProvider) {
        super(expenseRepository, Expense.class);
        this.expenseService = expenseService;
        this.expenseEditorProvider = expenseEditorProvider;
    }

    @Override
    protected void addColumns(Grid<Expense> grid) {
        grid.addColumn(expense -> expense.from.userName).setCaption("From").setId("from");
        grid.addColumn(expense -> expense.to.userName).setCaption("To").setId("to");
        grid.addColumn(expense -> expense.amount).setCaption("Amount").setId("amount");
        grid.addColumn(expense -> expense.paid ? "Yes" : "No").setCaption("Paid");
    }

    @Override
    protected EditorInterface<Expense> createEditor() {
        ExpenseEditor expenseEditor = expenseEditorProvider.get();

        expenseEditor.init();
        return expenseEditor;
    }

    @Override
    protected HorizontalLayout createActionColumn(Expense entity) {
        HorizontalLayout actionsLayout = new HorizontalLayout();

        if(!entity.paid) {
            //if(expense.to == userProvider.get)
            actionsLayout.addComponent(createPayButton(entity));

            actionsLayout.addComponent(createEditButton(entity));
            actionsLayout.addComponent(createDeleteButton(entity));
        }

        return actionsLayout;
    }

    private Button createPayButton(Expense entity) {
        return new Button("Pay", event -> payExpense(entity));
    }

    private void payExpense(Expense expense) {
        expenseService.markAsPaid(expense);
        getEntityGrid().getDataProvider().refreshAll();
    }

    private static class ExpenseEditor extends EntityEditor<Expense> {
        ComboBox<FlatUser> from = new ComboBox<>("From");
        ComboBox<FlatUser> to = new ComboBox<>("To");
        TextField name = new TextField("Name");
        TextField description = new TextField("Description");
        ComboBox<Expense.SharingType> sharingType = new ComboBox<>("Sharing type");
        TextField amount = new TextField("Amount");
        CheckBox apartmentCost = new CheckBox("Is apartment cost");
        DateField expenseMonth = new DateField("Expense month");
        final UserRepository userRepository;

        //kukacinject
        @Inject
        private ExpenseEditor(UserRepository userRepository) {
            super(Expense.class);

            this.userRepository = userRepository;
        }

        @Override
        protected void bindFields(Binder<Expense> binder) {
            binder.bind(from, "from");
            binder.bind(to, "to");
            binder.bind(name, "name");
            binder.bind(description, "description");
            binder.forField(amount).withConverter(new StringToIntegerConverter("Invalid value"))
                    .bind("amount");
            binder.bind(sharingType, "sharingType");
            binder.bind(apartmentCost, "apartmentCost");
            binder.forField(expenseMonth).withConverter((LocalDate localDate) ->
                            Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    date -> LocalDate.from(date.toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDate())).bind(expense1 -> expense1.expenseMonth,
                    (expense1, fieldvalue) -> expense1.expenseMonth = fieldvalue);
        }

        @Override
        protected void displayFields(FormLayout formLayout) {
            Arrays.asList(from, to, name, description, sharingType, amount, apartmentCost, expenseMonth)
                    .forEach(formLayout::addComponent);

            from.setItems(userRepository.findAll());
            from.setItemCaptionGenerator(item -> item.name);
            to.setItems(userRepository.findAll());
            to.setItemCaptionGenerator(item -> item.name);

            sharingType.setItems(Expense.SharingType.values());
            sharingType.setItemCaptionGenerator(item -> item.getName());
        }
    }
}
