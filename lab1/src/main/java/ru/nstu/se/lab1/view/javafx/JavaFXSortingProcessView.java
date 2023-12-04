package ru.nstu.se.lab1.view.javafx;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import ru.nstu.se.lab1.view.SortingProcessView;

import java.util.ArrayList;

public class JavaFXSortingProcessView<T extends Comparable<T>> extends GridPane implements SortingProcessView<T> {
    private final ArrayViewElementSizingStrategy<T> sizingStrategy;
    private final ObservableList<T> dataList;
    private final ObservableList<T> leftBlockList;
    private final ObservableList<T> rightBlockList;
    private final ArrayView<T> data;
    private final ArrayView<T> leftBlock;
    private final ArrayView<T> rightBlock;

    public JavaFXSortingProcessView(ArrayViewElementSizingStrategy<T> sizingStrategy) {
        this.sizingStrategy = sizingStrategy;

        setHgap(10);
        setVgap(10);

        var column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        getColumnConstraints().add(column1);

        var column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        getColumnConstraints().add(column2);

        setAlignment(Pos.CENTER);

        setGridLinesVisible(true); // DEBUG

        this.dataList = FXCollections.observableArrayList();
        this.leftBlockList = FXCollections.observableArrayList();
        this.rightBlockList = FXCollections.observableArrayList();

        this.data = new ArrayView<>(this.dataList, sizingStrategy);
        this.leftBlock = new ArrayView<>(this.leftBlockList, sizingStrategy);
        this.rightBlock = new ArrayView<>(this.rightBlockList, sizingStrategy);

        add(this.data, 0, 0, 1, 2);
        add(this.leftBlock, 1, 0);
        add(this.rightBlock, 1, 1);
    }

    @Override
    public void setData(ArrayList<T> data) {
        this.dataList.setAll(data);
    }

    @Override
    public void setLeftBlock(ArrayList<T> leftBlock) {
        this.leftBlockList.setAll(leftBlock);
    }

    @Override
    public void setRightBlock(ArrayList<T> rightBlock) {
        this.rightBlockList.setAll(rightBlock);
    }

    @Override
    public void compare(int leftBlockIterator, int rightBlockIterator) {
        System.out.format("void compare(int leftBlockIterator = %d, int rightBlockIterator = %d);\n", leftBlockIterator, rightBlockIterator);
        resetStyle();
        leftBlock.highlightElement(leftBlockIterator);
        rightBlock.highlightElement(rightBlockIterator);
    }

    @Override
    public void moveFromArrayToLeftBlock(ArrayList<T> array, int arrayIterator, ArrayList<T> leftBlock, int leftBlockIterator) {
        System.out.format("void moveFromArrayToLeftBlock(ArrayList<T> array = %s, int arrayIterator = %d, ArrayList<T> leftBlock = %s, int leftBlockIterator = %d);\n",
                array.toString(),
                arrayIterator,
                leftBlock.toString(),
                leftBlockIterator
        );
        resetStyle();

        this.leftBlockList.set(leftBlockIterator, array.get(arrayIterator));

        var translateTransition = new TranslateTransition(Duration.seconds(0.3), this.data.getChildren().get(arrayIterator));
        var leftBlockElement = this.leftBlock.getChildren().get(leftBlockIterator);
        var leftBlockElementBounds = leftBlockElement.localToScene(leftBlockElement.getBoundsInLocal());

        var arrayElement = this.data.getChildren().get(arrayIterator);
        var arrayElementBounds = arrayElement.localToScene(arrayElement.getBoundsInLocal());

        translateTransition.setToX(leftBlockElementBounds.getCenterX() - arrayElementBounds.getCenterX());
        translateTransition.setToY(leftBlockElementBounds.getCenterY() - arrayElementBounds.getCenterY());
        System.out.println("X=" + (leftBlockElementBounds.getCenterX() - arrayElementBounds.getCenterX()));
        System.out.println("Y=" + (leftBlockElementBounds.getCenterY() - arrayElementBounds.getCenterY()));
        translateTransition.play();

//        this.leftBlockList.set(leftBlockIterator, array.get(arrayIterator));
    }

    @Override
    public void moveFromArrayToRightBlock(ArrayList<T> array, int arrayIterator, ArrayList<T> rightBlock, int rightBlockIterator) {
        System.out.format("void moveFromArrayToRightBlock(ArrayList<T> array = %s, int arrayIterator = %d, ArrayList<T> rightBlock = %s, int rightBlockIterator = %d);\n",
                array.toString(),
                arrayIterator,
                rightBlock.toString(),
                rightBlockIterator
        );
        resetStyle();
        this.rightBlockList.set(rightBlockIterator, array.get(arrayIterator));
    }

    @Override
    public void moveFromLeftBlockToArray(ArrayList<T> leftBlock, int leftBlockIterator, ArrayList<T> array, int arrayIterator) {
        System.out.format("void moveFromLeftBlockToArray(ArrayList<T> leftBlock = %s, int leftBlockIterator = %d, ArrayList<T> array = %s, int arrayIterator = %d);\n",
                leftBlock.toString(),
                leftBlockIterator,
                array.toString(),
                arrayIterator
        );
        resetStyle();
        this.dataList.set(arrayIterator, leftBlock.get(leftBlockIterator));
    }

    @Override
    public void moveFromRightBlockToArray(ArrayList<T> rightBlock, int rightBlockIterator, ArrayList<T> array, int arrayIterator) {
        System.out.format("void moveFromRightBlockToArray(ArrayList<T> rightBlock = %s, int rightBlockIterator = %d, ArrayList<T> array = %s, int arrayIterator = %d);\n",
                rightBlock.toString(),
                rightBlockIterator,
                array.toString(),
                arrayIterator
        );
        resetStyle();
        this.dataList.set(arrayIterator, rightBlock.get(rightBlockIterator));
    }

    private void resetStyle() {
        this.data.update();
        this.leftBlock.update();
        this.rightBlock.update();
    }
}
