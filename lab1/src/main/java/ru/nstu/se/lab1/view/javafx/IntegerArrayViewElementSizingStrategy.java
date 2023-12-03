package ru.nstu.se.lab1.view.javafx;

public class IntegerArrayViewElementSizingStrategy implements ArrayViewElementSizingStrategy<Integer> {
    @Override
    public double getSize(Integer value) {
        return value;
    }
}
