package ru.nstu.se.lab1.view.javafx;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import ru.nstu.se.lab1.controller.MainController;
import ru.nstu.se.lab1.view.MainView;

public class JavaFXMainView<T extends Comparable<T>> extends BorderPane implements MainView {
    private MainController<T> mainController;
    private final JavaFXSortingProcessView<T> sortingProcessView;

    public JavaFXMainView(JavaFXSortingProcessView<T> sortingProcessView) {
        this.sortingProcessView = sortingProcessView;

        setCenter(this.sortingProcessView);

        // FIXME: delete after check
        var start = new Button("start");
        start.setOnAction(event -> {
            this.mainController.sortStep();
        });
        setLeft(start);
    }

    public void setMainController(MainController<T> mainController) {
        this.mainController = mainController;
        this.mainController.startSort();
    }
}
