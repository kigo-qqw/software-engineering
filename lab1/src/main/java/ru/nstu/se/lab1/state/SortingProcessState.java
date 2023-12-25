package ru.nstu.se.lab1.state;

import ru.nstu.se.lab1.view.SortingProcessView;

public abstract class SortingProcessState<T extends Comparable<T>> {
    private final SortingProcessView<T> sortingProcessView;

    public SortingProcessState(SortingProcessView<T> sortingProcessView) {
        this.sortingProcessView = sortingProcessView;
    }

    public abstract SortingProcessState<T> next();

    public SortingProcessView<T> getSortingProcessView() {
        return sortingProcessView;
    }
}
