package vaadin.components;

import com.vaadin.ui.Component;

import java.util.function.Consumer;

public interface EditorInterface<T> extends Component {
    void setSaveCallback(Consumer<T> saveCallback);
    void load(T entity);
}
