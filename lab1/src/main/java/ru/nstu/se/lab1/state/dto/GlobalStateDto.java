package ru.nstu.se.lab1.state.dto;

import java.util.ArrayList;

public class GlobalStateDto<T extends Comparable<T>> {
    private ArrayList<T> array;
    private ArrayList<T> leftBlock;
    private ArrayList<T> rightBlock;
    private int groupSize;
    private int leftBlockSize;
    private int rightBlockSize;

    public GlobalStateDto(ArrayList<T> array, ArrayList<T> leftBlock, ArrayList<T> rightBlock, int groupSize, int leftBlockSize, int rightBlockSize) {
        this.array = array;
        this.leftBlock = leftBlock;
        this.rightBlock = rightBlock;
        this.groupSize = groupSize;
        this.leftBlockSize = leftBlockSize;
        this.rightBlockSize = rightBlockSize;
    }

    public ArrayList<T> getArray() {
        return array;
    }

    public void setArray(ArrayList<T> array) {
        this.array = array;
    }

    public ArrayList<T> getLeftBlock() {
        return leftBlock;
    }

    public void setLeftBlock(ArrayList<T> leftBlock) {
        this.leftBlock = leftBlock;
    }

    public ArrayList<T> getRightBlock() {
        return rightBlock;
    }

    public void setRightBlock(ArrayList<T> rightBlock) {
        this.rightBlock = rightBlock;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public int getLeftBlockSize() {
        return leftBlockSize;
    }

    public void setLeftBlockSize(int leftBlockSize) {
        this.leftBlockSize = leftBlockSize;
    }

    public int getRightBlockSize() {
        return rightBlockSize;
    }

    public void setRightBlockSize(int rightBlockSize) {
        this.rightBlockSize = rightBlockSize;
    }
}
