package vaadin.views;

import backend.models.Expense;
import backend.models.FlatUser;
import backend.repositories.ExpenseRepository;
import backend.repositories.UserRepository;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToBigIntegerConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import vaadin.components.EditorInterface;

import javax.inject.Inject;
import javax.inject.Provider;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Consumer;

public class ExpensesView extends CustomComponent implements View {
    private final ExpenseRepository expenseRepository;
    private Grid<Expense> expenseGrid;

    private final Provider<ExpenseEditor> expenseEditorProvider;

    @Inject
    public ExpensesView(ExpenseRepository expenseRepository, Provider<ExpenseEditor> expenseEditorProvider) {
        this.expenseRepository = expenseRepository;
        this.expenseEditorProvider = expenseEditorProvider;
        init();
    }

    private void init() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();

        verticalLayout.setMargin(false);

        verticalLayout.addComponent(createAddExpenseButton());
        verticalLayout.addComponent(createGrid());

        setCompositionRoot(verticalLayout);
    }

    private Button createAddExpenseButton() {
        return new Button("Create expense",event -> editExpense(new Expense()));
    }

    private Grid<Expense> createGrid() {
        expenseGrid = new Grid<>(Expense.class);

        expenseGrid.setSizeFull();

        expenseGrid.setDataProvider(DataProvider.fromCallbacks(query -> expenseRepository.findAll().stream(), query -> expenseRepository.count()));

        expenseGrid.removeAllColumns();

        expenseGrid.addColumn(expense -> expense.from.userName).setCaption("From").setId("from");
        expenseGrid.addColumn(expense -> expense.to.userName).setCaption("To").setId("to");
        expenseGrid.addColumn(expense -> expense.amount).setCaption("Amount").setId("amount");

        expenseGrid.addColumn(expense -> "Edit",
                new ButtonRenderer<>(event -> editExpense(event.getItem()))).setId("edit");

        expenseGrid.addColumn(expense -> "Delete",
                new ButtonRenderer<>(event -> {
                    expenseRepository.delete(event.getItem());
                    expenseGrid.getDataProvider().refreshAll();
                })).setId("delete");

        return expenseGrid;
    }

    private void editExpense(Expense expense) {
        Window window = new Window("Edit expense");

        ExpenseEditor expenseEditor = expenseEditorProvider.get();

        expenseEditor.init(expense);
        window.setContent(expenseEditor);

        expenseEditor.setSaveCallback(savedUser-> {
            expenseRepository.save(savedUser);
            expenseGrid.getDataProvider().refreshAll();
            window.close();
        });

        getUI().addWindow(window);
        window.setModal(true);
        window.setWidth("500px");
        window.focus();
        window.setModal(true);
        window.setClosable(true);
    }

    private static class ExpenseEditor extends CustomComponent implements EditorInterface<Expense> {
        Consumer<Expense> saveCallback;

        ComboBox<FlatUser> from = new ComboBox<>("From");
        ComboBox<FlatUser> to = new ComboBox<>("To");
        TextField name = new TextField("Name");
        TextField description = new TextField("Description");
        ComboBox<Expense.SharingType> sharingType = new ComboBox<>("Sharing type");
        TextField amount = new TextField("Amount");
        CheckBox apartmentCost = new CheckBox("Is apartment cost");
        DateField expenseMonth = new DateField("Expense month");

        Binder<Expense> binder;

        Expense expense;

        final UserRepository userRepository;

        //kukacinject
        @Inject
        private ExpenseEditor(UserRepository userRepository) {
            this.userRepository = userRepository;
            binder = new Binder<>(Expense.class);
            binder.bind(from, "from");
            binder.bind(to, "to");
            binder.bind(name, "name");
            binder.bind(description, "description");
            binder.forField(amount).withConverter(new StringToIntegerConverter("Invalid value"))
                    .bind("amount");
            binder.bind(sharingType, "sharingType");
            binder.bind(apartmentCost, "apartmentCost");
            binder.forField(expenseMonth).withConverter((LocalDate localDate) -> Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    date -> LocalDate.from(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())).bind(expense1 -> expense1.expenseMonth, (expense1, fieldvalue) -> expense1.expenseMonth = fieldvalue);

            initFields();
            initLayout();
        }

        public void init(Expense toEdit) {
            this.expense = toEdit;
            binder.readBean(toEdit);
        }

        private void initLayout() {
            FormLayout formLayout = new FormLayout();

            Arrays.asList(from, to, name, description, sharingType, amount, apartmentCost, expenseMonth)
                    .forEach(formLayout::addComponent);

            formLayout.addComponent(new Button("Save", event -> {
                try {
                    binder.writeBean(expense);
                    saveCallback.accept(expense);
                } catch (ValidationException e) {

                }
            }));
            VerticalLayout verticalLayout = new VerticalLayout(formLayout);
            setCompositionRoot(verticalLayout);
        }

        private void initFields() {
            from.setItems(userRepository.findAll());
            from.setItemCaptionGenerator(item -> item.name);
            to.setItems(userRepository.findAll());
            to.setItemCaptionGenerator(item -> item.name);

            sharingType.setItems(Expense.SharingType.values());
            sharingType.setItemCaptionGenerator(item -> item.getName());
        }

        @Override
        public void setSaveCallback(Consumer<Expense> saveCallback) {
            this.saveCallback = saveCallback;
        }
    }
}
