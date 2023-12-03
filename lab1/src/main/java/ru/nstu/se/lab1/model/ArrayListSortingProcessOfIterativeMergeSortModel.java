package ru.nstu.se.lab1.model;

import ru.nstu.se.lab1.state.SortingProcessState;
import ru.nstu.se.lab1.state.StartSPS;
import ru.nstu.se.lab1.state.dto.GlobalStateDto;
import ru.nstu.se.lab1.view.SortingProcessView;

import java.util.ArrayList;

public class ArrayListSortingProcessOfIterativeMergeSortModel<T extends Comparable<T>>
        implements SortingProcessOfIterativeMergeSortModel<T> {
    private final SortingProcessView<T> sortingProcessView;
    private GlobalStateDto<T> globalStateDto;
    private SortingProcessState<T> currentState;

    public ArrayListSortingProcessOfIterativeMergeSortModel(SortingProcessView<T> sortingProcessView) {
        this.sortingProcessView = sortingProcessView;
    }

    public ArrayListSortingProcessOfIterativeMergeSortModel(SortingProcessView<T> sortingProcessView, ArrayList<T> data) {
        this.sortingProcessView = sortingProcessView;
        setData(data);
    }

    @Override
    public SortingProcessState<T> nextStep() {
        if (this.currentState == null)
            this.currentState = new StartSPS<>(this.sortingProcessView, this.globalStateDto);
        this.currentState = this.currentState.next();
        return this.currentState;
    }

    public void setData(ArrayList<T> data) {
        this.globalStateDto = new GlobalStateDto<>(
                data,
                new ArrayList<>(),
                new ArrayList<>(),
                1,
                0,
                0
        );
        this.currentState = null;  // reset state
        System.out.println(this.globalStateDto.getArray());
    }
}
