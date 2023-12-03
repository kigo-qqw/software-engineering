package ru.nstu.se.lab1.state;

import ru.nstu.se.lab1.state.dto.GlobalStateDto;
import ru.nstu.se.lab1.state.dto.MergeLoopStateDto;
import ru.nstu.se.lab1.view.SortingProcessView;

public class AllValuesMovedToRightBlockSPS<T extends Comparable<T>> extends SortingProcessState<T> {
    private final GlobalStateDto<T> globalStateDto;

    public AllValuesMovedToRightBlockSPS(SortingProcessView<T> sortingProcessView, GlobalStateDto<T> globalStateDto) {
        super(sortingProcessView);
        this.globalStateDto = globalStateDto;
    }

    @Override
    public SortingProcessState<T> next() {
        var mergeLoopStateDto = new MergeLoopStateDto<>(globalStateDto, 0, 0, 0, 0);
        return new MergeLoopSPS<>(getSortingProcessView(), mergeLoopStateDto);
    }
}
