package vaadin.components;

import java.util.function.Consumer;

public interface EditorInterface<T> {
    void setSaveCallback(Consumer<T> saveCallback);
}
