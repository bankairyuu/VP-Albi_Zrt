package vaadin.components;

import backend.repositories.GenericRepository;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.ui.*;

import javax.inject.Inject;

public abstract class EntityGrid<T> extends CustomComponent {
    private final GenericRepository<T> entityRepository;
    private final Class<T> entityClass;

    private Grid<T> entityGrid;

    @Inject
    public EntityGrid(GenericRepository<T> entityRepository, Class<T> entityClass) {
        this.entityRepository = entityRepository;
        this.entityClass = entityClass;

        init();
    }

    private void init() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();

        verticalLayout.setMargin(false);

        verticalLayout.addComponent(createAddEntityButton());
        verticalLayout.addComponent(createGrid());

        setCompositionRoot(verticalLayout);
    }

    private Button createAddEntityButton() {
        return new Button("Create", event -> {
            try {
                editEntity(entityClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Grid<T> getEntityGrid() {
        return entityGrid;
    }

    private Grid<T> createGrid() {
        entityGrid = new Grid<>(entityClass);

        entityGrid.setSizeFull();

        entityGrid.setDataProvider(DataProvider.fromCallbacks(query -> entityRepository.findAll().stream(), query -> entityRepository.count()));

        entityGrid.removeAllColumns();

        addColumns(entityGrid);

        entityGrid.addComponentColumn(entity -> createActionColumn(entity));

        return entityGrid;
    }

    protected HorizontalLayout createActionColumn(T entity) {
        HorizontalLayout actionsLayout = new HorizontalLayout();
        actionsLayout.addComponent(createEditButton(entity));
        actionsLayout.addComponent(createDeleteButton(entity));
        actionsLayout.setSpacing(true);

        return actionsLayout;
    }

    protected Button createEditButton(T entity) {
        return new Button("Edit", event -> editEntity(entity));
    }

    protected Button createDeleteButton(T entity) {
        return new Button("Delete", event -> deleteEntity(entity));
    }

    protected abstract void addColumns(Grid<T> grid);
    protected abstract EditorInterface<T> createEditor();

    private void editEntity(T entity) {
        Window window = new Window("Edit");

        EditorInterface<T> entityEditor = createEditor();

        entityEditor.load(entity);
        window.setContent(entityEditor);

        entityEditor.setSaveCallback(savedEntity-> {
            saveEntity(savedEntity);
            entityGrid.getDataProvider().refreshAll();
            window.close();
        });

        getUI().addWindow(window);
        window.setModal(true);
        window.setWidth("500px");
        window.focus();
        window.setModal(true);
        window.setClosable(true);
    }

    protected void saveEntity(T entity) {
        entityRepository.save(entity);
    }

    private void deleteEntity(T entity) {
        entityRepository.delete(entity);
        entityGrid.getDataProvider().refreshAll();
    }
}
