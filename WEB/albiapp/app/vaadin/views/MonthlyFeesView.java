package vaadin.views;

import backend.models.MonthlyFee;
import backend.repositories.MonthlyFeeRepository;
import backend.services.MonthlyFeeService;
import com.vaadin.data.Binder;
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
import java.util.Arrays;

public class MonthlyFeesView extends EntityGrid<MonthlyFee> implements View {
    private final MonthlyFeeService monthlyFeeService;
    private final Provider<MonthlyFeeEditor> monthlyFeeEditorProvider;


    //kukacinject
    @Inject
    public MonthlyFeesView(MonthlyFeeRepository monthlyFeeRepository, MonthlyFeeService monthlyFeeService, Provider<MonthlyFeeEditor> monthlyFeeEditorProvider) {
        super(monthlyFeeRepository, MonthlyFee.class);

        this.monthlyFeeService = monthlyFeeService;
        this.monthlyFeeEditorProvider = monthlyFeeEditorProvider;
    }

    @Override
    protected void addColumns(Grid<MonthlyFee> grid) {
        grid.setColumns("creationDate", "amount");

        grid.addColumn(monthlyFee -> monthlyFee.feeType.getName())
                .setCaption("Fee type").setId("feeType.name");

        grid.setColumnOrder("creationDate", "feeType.name", "amount");
    }

    @Override
    protected void saveEntity(MonthlyFee entity) {
        monthlyFeeService.createOrUpdateMonthlyFee(entity);
    }

    @Override
    protected EditorInterface<MonthlyFee> createEditor() {
        MonthlyFeeEditor monthlyFeeEditor = monthlyFeeEditorProvider.get();
        monthlyFeeEditor.init();

        return monthlyFeeEditor;
    }

    private static class MonthlyFeeEditor extends EntityEditor<MonthlyFee> {
        TextField amount = new TextField("Amount");
        ComboBox<MonthlyFee.FeeType> feeType = new ComboBox<>("Fee type");

        //inject me now
        @Inject
        private MonthlyFeeEditor() {
            super(MonthlyFee.class);
        }

        @Override
        protected void bindFields(Binder<MonthlyFee> binder) {
            binder.forField(amount).withConverter(
                    new StringToIntegerConverter("Invalid value"))
                    .bind("amount");

            binder.bind(feeType, "feeType");
        }

        @Override
        protected void displayFields(FormLayout formLayout) {
            Arrays.asList(feeType, amount)
                    .forEach(formLayout::addComponent);

            feeType.setItemCaptionGenerator(MonthlyFee.FeeType::getName);
            feeType.setItems(MonthlyFee.FeeType.values());
        }
    }
}
