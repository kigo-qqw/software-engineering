package ru.nstu.se.lab1.state;

import ru.nstu.se.lab1.state.dto.GlobalStateDto;
import ru.nstu.se.lab1.state.dto.MainLoopStateDto;
import ru.nstu.se.lab1.view.SortingProcessView;

public class StartSPS<T extends Comparable<T>> extends SortingProcessState<T> {
    private final GlobalStateDto<T> globalStateDto;

    public StartSPS(SortingProcessView<T> sortingProcessView, GlobalStateDto<T> globalStateDto) {
        super(sortingProcessView);
        this.globalStateDto = globalStateDto;
    }

    @Override
    public SortingProcessState<T> next() {
        var mainLoopStateDto = new MainLoopStateDto<>(globalStateDto);
        return new MainLoopSPS<>(getSortingProcessView(), mainLoopStateDto);
    }
}
