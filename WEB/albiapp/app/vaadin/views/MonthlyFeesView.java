package vaadin.views;

import backend.models.MonthlyFee;
import backend.repositories.MonthlyFeeRepository;
import backend.services.MonthlyFeeService;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import vaadin.components.EditorInterface;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Arrays;
import java.util.function.Consumer;

public class MonthlyFeesView extends CustomComponent implements View {
    private final MonthlyFeeRepository monthlyFeeRepository;
    private final MonthlyFeeService monthlyFeeService;
    private final Provider<MonthlyFeeEditor> monthlyFeeEditorProvider;

    private Grid<MonthlyFee> monthlyFeeGrid;


    //kukacinject
    @Inject
    public MonthlyFeesView(MonthlyFeeRepository monthlyFeeRepository, MonthlyFeeService monthlyFeeService, Provider<MonthlyFeeEditor> monthlyFeeEditorProvider) {
        this.monthlyFeeRepository = monthlyFeeRepository;
        this.monthlyFeeService = monthlyFeeService;
        this.monthlyFeeEditorProvider = monthlyFeeEditorProvider;

        init();
    }

    private void init() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();

        verticalLayout.setMargin(false);

        verticalLayout.addComponent(createAddMonthlyFeeButton());
        verticalLayout.addComponent(createGrid());

        setCompositionRoot(verticalLayout);
    }

    private Button createAddMonthlyFeeButton() {
        return new Button("Create monthly fee", event -> editMonthlyFee(new MonthlyFee()));
    }

    private Grid<MonthlyFee> createGrid() {
        monthlyFeeGrid = new Grid<>(MonthlyFee.class);

        monthlyFeeGrid.setSizeFull();

        monthlyFeeGrid.setDataProvider(DataProvider.fromCallbacks(query -> monthlyFeeRepository.findAll().stream(),
                query -> monthlyFeeRepository.count()));

        monthlyFeeGrid.removeAllColumns();

        monthlyFeeGrid.setColumns("creationDate", "amount");

        monthlyFeeGrid.addColumn(monthlyFee -> monthlyFee.feeType.getName())
                .setCaption("Fee type").setId("feeType.name");

        monthlyFeeGrid.addColumn(monthlyFee -> "Edit",
                new ButtonRenderer<>(event -> editMonthlyFee(event.getItem()))).setId("edit");

        monthlyFeeGrid.addColumn(expense -> "Delete",
                new ButtonRenderer<>(event -> {
                    monthlyFeeRepository.delete(event.getItem());
                    monthlyFeeGrid.getDataProvider().refreshAll();
                })).setId("delete");

        monthlyFeeGrid.setColumnOrder("creationDate", "feeType.name", "amount");

        return monthlyFeeGrid;
    }


    private void editMonthlyFee(MonthlyFee monthlyFee) {
        Window window = new Window("Edit monthly fee");

        MonthlyFeeEditor monthlyFeeEditor = monthlyFeeEditorProvider.get();

        monthlyFeeEditor.init(monthlyFee);
        window.setContent(monthlyFeeEditor);

        monthlyFeeEditor.setSaveCallback(editedMonthlyFee-> {
            monthlyFeeService.createOrUpdateMonthlyFee(editedMonthlyFee);
            monthlyFeeGrid.getDataProvider().refreshAll();
            window.close();
        });

        getUI().addWindow(window);
        window.setModal(true);
        window.setWidth("500px");
        window.focus();
        window.setModal(true);
        window.setClosable(true);
    }



    private static class MonthlyFeeEditor extends CustomComponent implements EditorInterface<MonthlyFee> {
        Consumer<MonthlyFee> saveCallback;
        TextField amount = new TextField("Amount");
        ComboBox<MonthlyFee.FeeType> feeType = new ComboBox<>("Fee type");

        Binder<MonthlyFee> binder;
        private MonthlyFee monthlyFee;

        //inject me now
        @Inject
        private MonthlyFeeEditor() {
            initBinder();
            initFields();
            initLayout();
        }

        private void initBinder() {
            binder = new Binder<>(MonthlyFee.class);

            binder.forField(amount).withConverter(
                    new StringToIntegerConverter("Invalid value"))
                    .bind("amount");

            binder.bind(feeType, "feeType");
        }

        public void init(MonthlyFee toEdit) {
            this.monthlyFee = toEdit;
            binder.readBean(toEdit);
        }

        private void initLayout() {
            FormLayout formLayout = new FormLayout();

            Arrays.asList(feeType, amount)
                    .forEach(formLayout::addComponent);

            formLayout.addComponent(new Button("Save", event -> {
                try {
                    binder.writeBean(monthlyFee);
                    saveCallback.accept(monthlyFee);
                } catch (ValidationException e) {

                }
            }));
            VerticalLayout verticalLayout = new VerticalLayout(formLayout);
            setCompositionRoot(verticalLayout);
        }

        private void initFields() {
            feeType.setItemCaptionGenerator(MonthlyFee.FeeType::getName);
            feeType.setItems(MonthlyFee.FeeType.values());
        }

        @Override
        public void setSaveCallback(Consumer<MonthlyFee> saveCallback) {
            this.saveCallback = saveCallback;
        }
    }
}
