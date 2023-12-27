package ru.nstu.se.lab3.main;

import ru.nstu.se.lab3.RadialChart;
import ru.nstu.se.lab3.RadialChartDataRow;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var frame = new JFrame();

        var chartDataRow1 = new RadialChartDataRow(Color.red, List.of(1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d), 4);
        var chartDataRow2 = new RadialChartDataRow(Color.green, List.of(1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d), 7);
        var radialChart = new RadialChart(500, 500, List.of(chartDataRow1, chartDataRow2));
        frame.add(radialChart);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
