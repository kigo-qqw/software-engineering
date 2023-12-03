package ru.nstu.se.lab1.controller;

import ru.nstu.se.lab1.model.SortingProcessOfIterativeMergeSortModel;
import ru.nstu.se.lab1.view.MainView;
import ru.nstu.se.lab1.view.SortingProcessView;

import java.util.ArrayList;

public interface MainController<T extends Comparable<T>> extends Controller {
    void startSort();
    void sortStep();
}
