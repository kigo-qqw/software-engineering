package ru.nstu.se.lab1.state;

import ru.nstu.se.lab1.state.dto.GlobalStateDto;
import ru.nstu.se.lab1.state.dto.MoveToBlockStateDto;
import ru.nstu.se.lab1.view.SortingProcessView;

public class AllValuesMovedToLeftBlockSPS<T extends Comparable<T>> extends SortingProcessState<T> {
    private final GlobalStateDto<T> globalStateDto;

    public AllValuesMovedToLeftBlockSPS(SortingProcessView<T> sortingProcessView, GlobalStateDto<T> globalStateDto) {
        super(sortingProcessView);
        this.globalStateDto = globalStateDto;
    }

    @Override
    public SortingProcessState<T> next() {
        var moveToBlockStateDto = new MoveToBlockStateDto<>(globalStateDto, 0);
        return new MoveToRightBlockSPS<>(getSortingProcessView(), moveToBlockStateDto);
    }
}
