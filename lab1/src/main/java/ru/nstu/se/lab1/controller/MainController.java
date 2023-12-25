package ru.nstu.se.lab1.controller;

public interface MainController<T extends Comparable<T>> extends Controller {
    void startSort();

    void sortStep();

    void generateRandomData();
}
