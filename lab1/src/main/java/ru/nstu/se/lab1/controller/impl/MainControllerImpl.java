package ru.nstu.se.lab1.controller.impl;

import ru.nstu.se.lab1.controller.MainController;
import ru.nstu.se.lab1.controller.SortingProcessOfIterativeMergeSortController;
import ru.nstu.se.lab1.view.MainView;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainControllerImpl<T extends Comparable<T>> implements MainController<T> {
    private final MainView mainView;
    private final SortingProcessOfIterativeMergeSortController<T> sortingProcessOfIterativeMergeSortController;

    public MainControllerImpl(MainView mainView,
                              SortingProcessOfIterativeMergeSortController<T> sortingProcessOfIterativeMergeSortController) {
        this.mainView = mainView;
        this.sortingProcessOfIterativeMergeSortController = sortingProcessOfIterativeMergeSortController;
    }

    @Override
    public void startSort() {
        System.out.println("void startSort();");
        // TODO: get data somehow
        sortingProcessOfIterativeMergeSortController.setData(
                (ArrayList<T>) IntStream.rangeClosed(1, 10).boxed()
                        .collect(Collectors.toCollection(ArrayList::new))
        );
//        this.sortingProcessOfIterativeMergeSortController.autoSort();
    }

    @Override
    public void sortStep() {
        this.sortingProcessOfIterativeMergeSortController.nextStep();
    }
}
