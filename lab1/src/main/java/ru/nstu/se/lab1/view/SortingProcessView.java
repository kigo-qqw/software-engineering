package ru.nstu.se.lab1.view;

import java.util.ArrayList;

public interface SortingProcessView<T extends Comparable<T>> extends View {
    void setData(ArrayList<T> data);

    void setLeftBlock(ArrayList<T> leftBlock);

    void setRightBlock(ArrayList<T> rightBlock);

    void compare(int leftBlockIterator, int rightBlockIterator);

    void moveFromArrayToLeftBlock(ArrayList<T> array, int arrayIterator, ArrayList<T> leftBlock, int leftBlockIterator);

    void moveFromArrayToRightBlock(ArrayList<T> array, int arrayIterator, ArrayList<T> rightBlock, int rightBlockIterator);

    void moveFromLeftBlockToArray(ArrayList<T> leftBlock, int leftBlockIterator, ArrayList<T> array, int arrayIterator);

    void moveFromRightBlockToArray(ArrayList<T> rightBlock, int rightBlockIterator, ArrayList<T> array, int arrayIterator);
}