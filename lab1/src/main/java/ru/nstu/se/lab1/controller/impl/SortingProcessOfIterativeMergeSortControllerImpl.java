package ru.nstu.se.lab1.controller.impl;

import ru.nstu.se.lab1.controller.SortingProcessOfIterativeMergeSortController;
import ru.nstu.se.lab1.model.SortingProcessOfIterativeMergeSortModel;
import ru.nstu.se.lab1.state.FinishSPS;
import ru.nstu.se.lab1.state.SortingProcessState;
import ru.nstu.se.lab1.view.SortingProcessView;

import java.util.ArrayList;

public class SortingProcessOfIterativeMergeSortControllerImpl<T extends Comparable<T>> implements SortingProcessOfIterativeMergeSortController<T> {
    private SortingProcessView<T> sortingProcessView;
    private SortingProcessOfIterativeMergeSortModel<T> model;

    public SortingProcessOfIterativeMergeSortControllerImpl(
            SortingProcessView<T> sortingProcessView,
            SortingProcessOfIterativeMergeSortModel<T> model
    ) {
        this.sortingProcessView = sortingProcessView;
        this.model = model;
    }

    @Override
    public void setData(ArrayList<T> data) {
        System.out.println("void setData();");
        this.model.setData(data);
        this.sortingProcessView.setData(data);
    }

    @Override
    public void autoSort() {
        System.out.println("void autoSort();");
        SortingProcessState<T> state = this.model.nextStep();
        while (!(state instanceof FinishSPS<T>)) {
            System.out.println("state = " + state);
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
        SortingProcessState<T> state = this.model.nextStep();
    }
}