package ru.nstu.se.lab1.state.dto;

public class MoveToBlockStateDto<T extends Comparable<T>> {
    private GlobalStateDto<T> globalStateDto;
    private int iterator;

    public MoveToBlockStateDto(GlobalStateDto<T> globalStateDto, int iterator) {
        this.globalStateDto = globalStateDto;
        this.iterator = iterator;
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
}
