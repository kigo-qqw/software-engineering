package ru.nstu.se.lab1.view.javafx;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import ru.nstu.se.lab1.controller.MainController;
import ru.nstu.se.lab1.view.MainView;

public class JavaFXMainView<T extends Comparable<T>> extends BorderPane implements MainView {
    private MainController<T> mainController;
    private final JavaFXSortingProcessView<T> sortingProcessView;

    public JavaFXMainView(JavaFXSortingProcessView<T> sortingProcessView) {
        this.sortingProcessView = sortingProcessView;

        setCenter(this.sortingProcessView);

        final var generateData = new Button("generate data");
        generateData.setOnAction(event -> {
            this.mainController.generateRandomData();
        });

        final var step = new Button("step");
        step.setOnAction(event -> {
            this.mainController.sortStep();
        });

        final var container=new HBox(generateData, step);
        setTop(container);
    }

    public void setMainController(MainController<T> mainController) {
        this.mainController = mainController;
        this.mainController.startSort();
    }
}
