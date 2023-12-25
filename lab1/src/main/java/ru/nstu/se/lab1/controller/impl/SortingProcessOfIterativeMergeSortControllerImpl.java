package ru.nstu.se.lab1.controller.impl;

import ru.nstu.se.lab1.controller.SortingProcessOfIterativeMergeSortController;
import ru.nstu.se.lab1.model.SortingProcessOfIterativeMergeSortModel;
import ru.nstu.se.lab1.state.FinishSPS;
import ru.nstu.se.lab1.state.SortingProcessState;
import ru.nstu.se.lab1.view.SortingProcessView;

import java.util.ArrayList;

public class SortingProcessOfIterativeMergeSortControllerImpl<T extends Comparable<T>> implements SortingProcessOfIterativeMergeSortController<T> {
    private final SortingProcessView<T> sortingProcessView;
    private final SortingProcessOfIterativeMergeSortModel<T> model;

    public SortingProcessOfIterativeMergeSortControllerImpl(
            SortingProcessView<T> sortingProcessView,
            SortingProcessOfIterativeMergeSortModel<T> model
    ) {
        this.sortingProcessView = sortingProcessView;
        this.model = model;
    }

    @Override
    public void setData(ArrayList<T> data) {
        this.model.setData(data);
        this.sortingProcessView.setData(data);
    }

    @Override
    public void autoSort() {
        SortingProcessState<T> state = this.model.nextStep();
        while (!(state instanceof FinishSPS<T>)) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            state = this.model.nextStep();
        }
    }

    @Override
    public void nextStep() {
        var ignore = this.model.nextStep();
    }
}
