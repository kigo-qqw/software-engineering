package ru.nstu.se.lab2.main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import ru.nstu.se.lab2.CONST;
import ru.nstu.se.lab2.CONSTFieldDescriptor;
import ru.nstu.se.lab2.ClassWithCONSTAnnotatedFieldsToArrayListOfCONSTFieldDescriptors;
import ru.nstu.se.lab2.components.LabelWithText_A;
import ru.nstu.se.lab2.components.LabelWithText_B;
import ru.nstu.se.lab2.components.LabelWithText_C;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;

public class Main extends Application {
    class ClassWithCONSTAnnotationsOnField {
        @CONST(title = "title-for-A", group = "group-for-A", clazz = LabelWithText_A.class)
        private static final int constantForClassA = 1;
        @CONST(title = "title-for-B", group = "group-for-B", clazz = LabelWithText_B.class)
        private static final int constantForClassB = 2;
        @CONST(title = "title-for-C", group = "group-for-C", clazz = LabelWithText_C.class)
        private static final int constantForClassC = 3;

    }

    @Override
    public void start(Stage stage) {
        var object = new ClassWithCONSTAnnotationsOnField();
        var descriptors = ClassWithCONSTAnnotatedFieldsToArrayListOfCONSTFieldDescriptors.convert(object);

        var componentWrapper = new HBox();
        var comboBox = new ComboBox<>(FXCollections.observableList(descriptors.stream().map(CONSTFieldDescriptor::title).toList()));
        comboBox.setOnAction(event -> {
            var title = comboBox.getValue();
            var optionalComponentClass = descriptors.stream()
                    .filter(constFieldDescriptor -> constFieldDescriptor.title().equals(title))
                    .map(CONSTFieldDescriptor::clazz)
                    .findFirst();
            if (optionalComponentClass.isPresent()) {
                try {
                    componentWrapper.getChildren().setAll(optionalComponentClass.get().getDeclaredConstructor().newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        var view = new VBox(comboBox, componentWrapper);
        var scene = new Scene(view, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}