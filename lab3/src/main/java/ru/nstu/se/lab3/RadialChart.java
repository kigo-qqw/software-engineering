package ru.nstu.se.lab3;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RadialChart extends JPanel {
    private final int width;
    private final int height;
    private final List<RadialChartDataRow> radialChartData;
    private int N;
    private final double length;
    private final Point center;


    public RadialChart(int width, int height, List<RadialChartDataRow> radialChartData) {
        this.width = width;
        this.height = height;
        this.radialChartData = radialChartData;

        updateData();

        this.length = Math.min(this.width, this.height) / 2f * 0.85;
        this.center = new Point(this.width / 2, this.height / 2);

        setBounds(0, 0, this.width, this.height);
        setBackground(Color.lightGray);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        synchronized (this.radialChartData) {
            // draw grid
            for (int i = 0; i < this.N; i++) {
                var lineDoubleNormEnd = calculatePoint(i, this.length);
                var nextLineDoubleNormEnd = calculatePoint(i + 1, this.length);
                var lineEnd = calculatePoint(i, this.length / 2);
                var nextLineEnd = calculatePoint(i + 1, this.length / 2);
                var label = calculatePoint(i, this.length * 1.07);
                String labelText = String.valueOf(i + 1);
                int labelTextWidth = g.getFontMetrics().stringWidth(labelText);

                // radial lines
                g.drawLine(this.center.x, this.center.y, lineDoubleNormEnd.x, lineDoubleNormEnd.y);
                g.drawString(labelText, label.x - labelTextWidth / 2, label.y);
                // big circle
                g.drawLine(lineDoubleNormEnd.x, lineDoubleNormEnd.y, nextLineDoubleNormEnd.x, nextLineDoubleNormEnd.y);
                // small circle
                g.drawLine(lineEnd.x, lineEnd.y, nextLineEnd.x, nextLineEnd.y);
            }

            // draw lines
            for (int dataRowIndex = 0; dataRowIndex < this.radialChartData.size(); dataRowIndex++) {
                var prevPoint = calculatePoint(this.radialChartData.get(dataRowIndex).getData().size() - 1, dataRowIndex);
                for (int i = 0; i < this.N; i++) {
                    Point currentPoint;
                    if (i < this.radialChartData.get(dataRowIndex).getData().size()) {
                        currentPoint = calculatePoint(i, dataRowIndex);
                        var g2 = (Graphics2D) g;
                        g2.setStroke(new BasicStroke(2));
                        g.setColor(this.radialChartData.get(dataRowIndex).getColor());
                        g.drawLine(prevPoint.x, prevPoint.y, currentPoint.x, currentPoint.y);
                        prevPoint = currentPoint;
                    } else {
                        currentPoint = calculatePoint(this.radialChartData.get(dataRowIndex).getData().size() - 1, dataRowIndex);
                        g.drawLine(prevPoint.x, prevPoint.y, currentPoint.x, currentPoint.y);
                    }
                }
            }

            // draw marks
            for (int dataRowIndex = 0; dataRowIndex < this.radialChartData.size(); dataRowIndex++) {
                for (int i = 0; i < this.N; i++) {
                    if (i < this.radialChartData.get(dataRowIndex).getData().size()) {
                        var g2 = (Graphics2D) g;
                        Point currentPoint = calculatePoint(i, dataRowIndex);
                        if (this.radialChartData.get(dataRowIndex).getData().get(i) > 2 * this.radialChartData.get(dataRowIndex).getNorm()) {
                            drawTriangle(g2, currentPoint, this.radialChartData.get(dataRowIndex).getColor());
                        } else {
                            drawCircle(g2, currentPoint, this.radialChartData.get(dataRowIndex).getColor());
                        }
                    }
                }
            }
        }
    }

    private Point calculatePoint(int index, int dataRowIndex) {
        synchronized (this.radialChartData) {
            double value = this.radialChartData.get(dataRowIndex).getData().get(index);
            double norm = this.radialChartData.get(dataRowIndex).getNorm();
            if (value > 2 * norm)
                value = 2 * norm;
            return calculatePoint(index, this.length / 2 * value / norm);
        }
    }

    private Point calculatePoint(int index, double value) {
        double angleInRadians = Math.toRadians(360f / this.N * index);
        int x = this.center.x + (int) (Math.sin(angleInRadians) * value);
        int y = this.center.x - (int) (Math.cos(angleInRadians) * value);
        return new Point(x, y);
    }

    private void update() {
        updateData();
        repaint();
    }


    private void updateData() {
        synchronized (this.radialChartData) {
            this.N = this.radialChartData.stream()
                    .map(radialChartDataRow -> radialChartDataRow.getData().size())
                    .max(Integer::compare)
                    .orElse(0);
        }
    }

    private void drawCircle(Graphics2D g, Point point, Color color) {
        g.setColor(color);
        g.fillOval(point.x - 4, point.y - 4, 8, 8);
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(1));
        g.drawOval(point.x - 4, point.y - 4, 8, 8);
    }

    private void drawTriangle(Graphics2D g, Point point, Color color) {
        int size = 14;
        double a = size / 3.f * Math.sin(Math.toRadians(60));
        int[] x = {point.x - size / 2, point.x, point.x + size / 2};
        int[] y = {(int) (point.y + a), (int) (point.y - 2 * a), (int) (point.y + a)};
        g.setColor(color);
        g.fillPolygon(x, y, 3);
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(1));
        g.drawPolygon(x, y, 3);
    }
}
