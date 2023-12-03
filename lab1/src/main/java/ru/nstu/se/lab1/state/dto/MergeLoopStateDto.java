package ru.nstu.se.lab1.state.dto;

public class MergeLoopStateDto<T extends Comparable<T>> {
    private GlobalStateDto<T> globalStateDto;
    private int iterator;
    private int leftBlockIterator;
    private int rightBlockIterator;
    private int k;

    public MergeLoopStateDto(GlobalStateDto<T> globalStateDto, int iterator, int leftBlockIterator, int rightBlockIterator, int k) {
        this.globalStateDto = globalStateDto;
        this.iterator = iterator;
        this.leftBlockIterator = leftBlockIterator;
        this.rightBlockIterator = rightBlockIterator;
        this.k = k;
    }

    public GlobalStateDto<T> getGlobalStateDto() {
        return globalStateDto;
    }

    public void setGlobalStateDto(GlobalStateDto<T> globalStateDto) {
        this.globalStateDto = globalStateDto;
    }

    public int getIterator() {
        return iterator;
    }

    public void setIterator(int iterator) {
        this.iterator = iterator;
    }

    public int getLeftBlockIterator() {
        return leftBlockIterator;
    }

    public void setLeftBlockIterator(int leftBlockIterator) {
        this.leftBlockIterator = leftBlockIterator;
    }

    public int getRightBlockIterator() {
        return rightBlockIterator;
    }

    public void setRightBlockIterator(int rightBlockIterator) {
        this.rightBlockIterator = rightBlockIterator;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }
}
