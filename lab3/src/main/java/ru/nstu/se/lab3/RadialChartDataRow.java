package ru.nstu.se.lab3;


import java.awt.*;
import java.util.List;

public class RadialChartDataRow {
    private final Color color;
    private final List<Double> data;
    private final double norm;

    public RadialChartDataRow(Color color, List<Double> data, double norm) {
        this.color = color;
        this.data = data;
        this.norm = norm;
    }

    public Color getColor() {
        return color;
    }

    public List<Double> getData() {
        return data;
    }

    public double getNorm() {
        return norm;
    }
}
