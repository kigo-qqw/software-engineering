package ru.nstu.se.lab1.controller.impl;

import ru.nstu.se.lab1.controller.MainController;
import ru.nstu.se.lab1.controller.SortingProcessOfIterativeMergeSortController;
import ru.nstu.se.lab1.util.RandomElement;
import ru.nstu.se.lab1.view.MainView;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainControllerImpl<T extends Comparable<T>> implements MainController<T> {
    private final MainView mainView;
    private final SortingProcessOfIterativeMergeSortController<T> sortingProcessOfIterativeMergeSortController;
    private final RandomElement<T> randomElement;

    public MainControllerImpl(MainView mainView,
                              SortingProcessOfIterativeMergeSortController<T> sortingProcessOfIterativeMergeSortController,
                              RandomElement<T> randomElement) {
        this.mainView = mainView;
        this.sortingProcessOfIterativeMergeSortController = sortingProcessOfIterativeMergeSortController;
        this.randomElement = randomElement;

        generateRandomData();
    }

    @Override
    public void startSort() {
    }

    @Override
    public void sortStep() {
        this.sortingProcessOfIterativeMergeSortController.nextStep();
    }

    @Override
    public void generateRandomData() {
        sortingProcessOfIterativeMergeSortController.setData(
                Stream.generate(this.randomElement::makeRandom)
                        .limit(10)
                        .collect(Collectors.toCollection(ArrayList::new))
        );
    }
}
