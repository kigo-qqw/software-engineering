package ru.nstu.se.lab1.view.javafx;

import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Objects;
import java.util.Optional;

public class ArrayView<T extends Comparable<T>> extends VBox {
    private ObservableList<T> data;
    private final ArrayViewElementSizingStrategy<T> sizingStrategy;

    public ArrayView(ObservableList<T> data, ArrayViewElementSizingStrategy<T> sizingStrategy) {
        this.sizingStrategy = sizingStrategy;

        setData(data);
    }

    void setData(ObservableList<T> data) {
        System.out.println("setData");
        this.data = data;

        this.data.addListener((ListChangeListener<T>) c -> update());

        Platform.runLater(this::update);
        System.out.println("after update");
        System.out.println(data);
    }

    void update() {
        System.out.println("void update();");

        getChildren().clear();

        Optional<T> maxElementOptional = this.data.stream().filter(Objects::nonNull).max(T::compareTo);
        Optional<T> minElementOptional = this.data.stream().filter(Objects::nonNull).min(T::compareTo);

        if (maxElementOptional.isPresent() && minElementOptional.isPresent()) {
            T maxElement = maxElementOptional.get();
            T minElement = minElementOptional.get();

            double maxElementSize = sizingStrategy.getSize(maxElement);
            double minElementSize = sizingStrategy.getSize(minElement);
            double sizeRange = maxElementSize - minElementSize;

            DoubleBinding minimalSize = widthProperty().divide(10);

            getChildren().addAll(this.data.stream()
                    .map(t -> {
                        System.out.println(t);

                        String text = "";
                        if (t != null)
                            text = t.toString();

                        TextField textField = new TextField(text);

                        textField.maxWidthProperty().bind(
                                widthProperty()
                                        .subtract(minimalSize)
                                        .divide(sizeRange)
                                        .multiply((t != null ? sizingStrategy.getSize(t) : 0) - minElementSize)
                                        .add(minimalSize)
                        );
                        textField.setEditable(false);
                        return textField;
                    })
                    .toList());
        }
    }
    public void highlightElement(int index) {
        ((TextField) getChildren().get(index)).setStyle("-fx-background-color: green;");
    }
}
