package ru.nstu.se.lab3.main;

import ru.nstu.se.lab3.PeriodicDataProvider;
import ru.nstu.se.lab3.RadialChart;
import ru.nstu.se.lab3.RadialChartDataRow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {
        double norm1 = 5;
        double norm2 = 7;

        var chartDataRow1 = generateRadialChartDataRow(Color.red, 5, norm1);
        var chartDataRow2 = generateRadialChartDataRow(Color.green, 7, norm2);

        PeriodicDataProvider dataProvider1 = () -> generateValue(norm1);
        PeriodicDataProvider dataProvider2 = () -> generateValue(norm2);

        var radialChart = new RadialChart(500, 500,
                List.of(chartDataRow1, chartDataRow2),
                List.of(dataProvider1, dataProvider2),
                1000);

        var frame = new JFrame();
        frame.add(radialChart);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

        radialChart.startPeriodicDataAcquisition();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        radialChart.cancelPeriodicDataAcquisition();
    }

    private static double generateValue(double norm) {
        return random.nextDouble() * 2.5 * norm;
    }

    private static RadialChartDataRow generateRadialChartDataRow(Color color, int length, double norm) {
        return new RadialChartDataRow(color,
                Stream.generate(() -> generateValue(norm))
                        .limit(length)
                        .collect(Collectors.toCollection(ArrayList::new)),
                norm);
    }
}
