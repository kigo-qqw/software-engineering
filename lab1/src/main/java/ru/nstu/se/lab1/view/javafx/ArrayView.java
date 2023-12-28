package ru.nstu.se.lab1.view.javafx;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ArrayView<T extends Comparable<T>> extends HBox {
    private ObservableList<T> data;
    private final ArrayViewElementSizingStrategy<T> sizingStrategy;

    public ArrayView(ObservableList<T> data, ArrayViewElementSizingStrategy<T> sizingStrategy) {
        this.sizingStrategy = sizingStrategy;

        setData(data);
    }

    void setData(ObservableList<T> data) {
        this.data = data;
        this.data.addListener((ListChangeListener<T>) c -> update());
        Platform.runLater(this::update);
    }

    void update() {
        getChildren().clear();
        getChildren().addAll(this.data.stream()
                .map(t -> {
                    TextField textField = new TextField(t != null ? t.toString() : "");
                    if (t == null) textField.setVisible(false);
                    textField.setMaxWidth(40);
                    textField.setEditable(false);
                    return textField;
                })
                .toList());

    }

    public void highlightElementYellow(int index) {
        getChildren().get(index).setStyle("-fx-background-color: yellow;");
    }

    public void highlightElementGreen(int index) {
        getChildren().get(index).setStyle("-fx-background-color: green;");
    }

    public void highlightElementBlue(int index) {
        getChildren().get(index).setStyle("-fx-background-color: blue;");
    }
}
