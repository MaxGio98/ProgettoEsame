package com.dagomiliano.progettoesame.model;

/**
 * Classe modellante per il conteggio di stringhe StringCount
 */
public class StringCount {

    private String field;
    private int count;

    /**
     * Costruttore della classe
     *
     * @param field
     */
    public StringCount(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
