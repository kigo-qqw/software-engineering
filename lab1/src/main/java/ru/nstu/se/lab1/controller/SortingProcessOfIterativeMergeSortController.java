package ru.nstu.se.lab1.controller;


import java.util.ArrayList;

public interface SortingProcessOfIterativeMergeSortController<T extends Comparable<T>> extends Controller {
    void setData(ArrayList<T> data);
    void autoSort();
    void nextStep();
}
