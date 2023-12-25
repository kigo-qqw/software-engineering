package ru.nstu.se.lab1.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nstu.se.lab1.controller.impl.MainControllerImpl;
import ru.nstu.se.lab1.controller.impl.SortingProcessOfIterativeMergeSortControllerImpl;
import ru.nstu.se.lab1.model.ArrayListSortingProcessOfIterativeMergeSortModel;
import ru.nstu.se.lab1.util.IntegerRandomElement;
import ru.nstu.se.lab1.view.javafx.IntegerArrayViewElementSizingStrategy;
import ru.nstu.se.lab1.view.javafx.JavaFXMainView;
import ru.nstu.se.lab1.view.javafx.JavaFXSortingProcessView;


public class Main extends Application {
    @Override
    public void start(Stage stage) {
        var sizingStrategy = new IntegerArrayViewElementSizingStrategy();
        var sortingProcessView = new JavaFXSortingProcessView<>(sizingStrategy);
        var mainView = new JavaFXMainView<>(sortingProcessView);

        var model = new ArrayListSortingProcessOfIterativeMergeSortModel<>(sortingProcessView);

        var sortingProcessOfIterativeMergeSortController = new SortingProcessOfIterativeMergeSortControllerImpl<>(sortingProcessView, model);

        var integerRandomElement = new IntegerRandomElement();
        var mainController = new MainControllerImpl<>(mainView, sortingProcessOfIterativeMergeSortController, integerRandomElement);
        mainView.setMainController(mainController);

        var scene = new Scene(mainView, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
