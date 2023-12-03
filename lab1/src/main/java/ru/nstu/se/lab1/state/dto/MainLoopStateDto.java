package ru.nstu.se.lab1.state.dto;

public class MainLoopStateDto<T extends Comparable<T>> {
    private GlobalStateDto<T> globalStateDto;

    public MainLoopStateDto(GlobalStateDto<T> globalStateDto) {
        this.globalStateDto = globalStateDto;
    }

    public GlobalStateDto<T> getGlobalStateDto() {
        return globalStateDto;
    }

    public void setGlobalStateDto(GlobalStateDto<T> globalStateDto) {
        this.globalStateDto = globalStateDto;
    }
}
