package com.dagomiliano.progettoesame.model;

public class Stats {

    private String field;
    private double avg;
    private double devStd;
    private double max;
    private double min;
    private double sum;
    private int count;

    public Stats(String field, double avg, double devStd, double max, double min, double sum, int count) {
        this.field = field;
        this.avg = avg;
        this.devStd = devStd;
        this.max = max;
        this.min = min;
        this.sum = sum;
        this.count = count;
    }

    public Stats(String elem)
    {
        this.field=elem;
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

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getSum() {
        return sum;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
