package com.dagomiliano.progettoesame.model;

/**
 * Classe modellante per i metadata
 */
public class MetaData {

    private String alias;
    private String sourceField;
    private String type;


    //Getters and setters

    public String getAlias() {
        return alias;
    }

    //getters and setters
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSourceField() {
        return sourceField;
    }

    public void setSourceField(String sourceField) {
        this.sourceField = sourceField;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
