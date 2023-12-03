package ru.nstu.se.lab1.state;

import ru.nstu.se.lab1.view.SortingProcessView;

public class FinishSPS<T extends Comparable<T>> extends SortingProcessState<T> {
    public FinishSPS(SortingProcessView<T> sortingProcessView) {
        super(sortingProcessView);
    }

    @Override
    public SortingProcessState<T> next() {
        return this;
    }
}
