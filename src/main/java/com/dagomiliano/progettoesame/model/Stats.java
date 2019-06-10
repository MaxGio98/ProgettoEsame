package com.dagomiliano.progettoesame.model;

public class Stats {

    private String field;
    private double avg;
    private double devStd;
    private int max;
    private int min;
    private int sum;
    private int count;

    public Stats(String field, double avg, double devStd, int max, int min, int sum, int count) {
        this.field = field;
        this.avg = avg;
        this.devStd = devStd;
        this.max = max;
        this.min = min;
        this.sum = sum;
        this.count = count;
    }

    public String getField() {
        return field;
    }

    public double getAvg() {
        return avg;
    }

    public double getDevStd() {
        return devStd;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public int getSum() {
        return sum;
    }

    public int getCount() {
        return count;
    }
}
