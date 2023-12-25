package ru.nstu.se.lab1.view.javafx;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
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

//        setGridLinesVisible(true); // DEBUG

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
    public void compare(int leftBlockIterator, int rightBlockIterator, boolean isLeft) {
        resetStyle();
        if (isLeft) {
            leftBlock.highlightElementGreen(leftBlockIterator);
            rightBlock.highlightElementYellow(rightBlockIterator);
        } else {
            leftBlock.highlightElementYellow(leftBlockIterator);
            rightBlock.highlightElementGreen(rightBlockIterator);
        }
    }

    @Override
    public void moveFromArrayToLeftBlock(ArrayList<T> array, int arrayIterator, ArrayList<T> leftBlock, int leftBlockIterator) {
        resetStyle();
        this.leftBlockList.set(leftBlockIterator, array.get(arrayIterator));
        moveElementToElement(this.data, arrayIterator, this.leftBlock, leftBlockIterator);
    }

    @Override
    public void moveFromArrayToRightBlock(ArrayList<T> array, int arrayIterator, ArrayList<T> rightBlock, int rightBlockIterator) {
        resetStyle();
        this.rightBlockList.set(rightBlockIterator, array.get(arrayIterator));
        moveElementToElement(this.data, arrayIterator, this.rightBlock, rightBlockIterator);
    }

    @Override
    public void moveFromLeftBlockToArray(ArrayList<T> leftBlock, int leftBlockIterator, ArrayList<T> array, int arrayIterator) {
        resetStyle();
        this.dataList.set(arrayIterator, leftBlock.get(leftBlockIterator));
        moveElementToElement(this.leftBlock, leftBlockIterator, this.data, arrayIterator);
    }

    @Override
    public void moveFromRightBlockToArray(ArrayList<T> rightBlock, int rightBlockIterator, ArrayList<T> array, int arrayIterator) {
        resetStyle();
        this.dataList.set(arrayIterator, rightBlock.get(rightBlockIterator));
        moveElementToElement(this.rightBlock, rightBlockIterator, this.data, arrayIterator);
    }

    private void resetStyle() {
        this.data.update();
        this.leftBlock.update();
        this.rightBlock.update();
    }

    private void moveElementToElement(ArrayView<T> fromContainer, int fromIterator, ArrayView<T> toContainer, int toIterator) {
        fromContainer.highlightElementBlue(fromIterator);
        toContainer.highlightElementBlue(toIterator);

        final var fromContainerBounds = fromContainer.localToScene(fromContainer.getBoundsInLocal());
        final var toContainerBounds = toContainer.localToScene(toContainer.getBoundsInLocal());

        final var from = (TextField) fromContainer.getChildren().get(fromIterator);
        final var to = (TextField) toContainer.getChildren().get(toIterator);

        final var fromX = fromContainerBounds.getMinX();
        final var fromY = fromContainerBounds.getMinY() + fromIterator * (fromContainerBounds.getHeight() / fromContainer.getChildren().size());
        final var toX = toContainerBounds.getMinX();
        final var toY = toContainerBounds.getMinY() + toIterator * (toContainerBounds.getHeight() / toContainer.getChildren().size());

        to.maxWidthProperty().bind(from.widthProperty());

        final var translateTransition = new TranslateTransition();

        translateTransition.setDuration(Duration.seconds(0.3));
        translateTransition.setNode(to);

        translateTransition.setFromX(fromX - toX);
        translateTransition.setFromY(fromY - toY);
        translateTransition.setToX(0);
        translateTransition.setToY(0);

        translateTransition.play();
    }
}
