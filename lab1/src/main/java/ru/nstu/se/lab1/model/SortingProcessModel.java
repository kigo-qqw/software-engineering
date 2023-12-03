package ru.nstu.se.lab1.model;

import ru.nstu.se.lab1.state.SortingProcessState;

import java.util.ArrayList;

public interface SortingProcessModel<T extends Comparable<T>> extends Model {
    void setData(ArrayList<T> data);
    SortingProcessState<T> nextStep();
}
