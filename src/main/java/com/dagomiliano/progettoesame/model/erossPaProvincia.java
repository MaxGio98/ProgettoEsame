package com.dagomiliano.progettoesame.model;

import java.io.Serializable;

/**
 *  Classe modellante dei dati contenente gli attributi presenti nel file .csv
 *
 *  Il file proposto contiene dati inerenti l'utilizzo di software con licenza FLOSS nelle province
 *  dell' Emilia Romagno nel periodo dicembre 2007 - marzo 2008.
 */

public class erossPaProvincia implements Serializable {

    private int idTerritorio;
    private String territorio;
    private int anno;
    private float utilizzoFloss;
    private float utilizzoFlossClient;
    private float utilizzoFlossServer;
    private float nonUtilizzatoriFloss;
    private float utilizzatoriConsapevoliFloss;
    private float utilizzatoriInconsapevoliFloss;
    private float utilizzoFlossBrowser;
    private float utilizzoOfficeAutomation;
    private float utilizzoFlossPostaElettronica;
    private float utilizzoFlossSitGis;
    private float utilizzoFlossSO;
    private float utilizzoFlossAppServer;
    private float utilizzoFlossDBMS;
    private float utilizzoFlossFileServer;
    private float utilizzoFlossMailServer;
    private float utilizzoFlossPrinterServer;
    private float utilizzoFlossServerDesktopRemoto;
    private float utilizzoFlossWebServer;
    private float spesaMediaSoftwarePerAddetto;
    private float mediaFornitoriICT;

    /**
     * Costruttore della classe
     *
     * @param idTerritorio
     * @param territorio
     * @param anno
     * @param utilizzoFloss
     * @param utilizzoFlossClient
     * @param utilizzoFlossServer
     * @param nonUtilizzatoriFloss
     * @param utilizzatoriConsapevoliFloss
     * @param utilizzatoriInconsapevoliFloss
     * @param utilizzoFlossBrowser
     * @param utilizzoOfficeAutomation
     * @param utilizzoFlossPostaElettronica
     * @param utilizzoFlossSitGis
     * @param utilizzoFlossSO
     * @param utilizzoFlossAppServer
     * @param utilizzoFlossDBMS
     * @param utilizzoFlossFileServer
     * @param utilizzoFlossMailServer
     * @param utilizzoFlossPrinterServer
     * @param utilizzoFlossServerDesktopRemoto
     * @param utilizzoFlossWebServer
     * @param spesaMediaSoftwarePerAddetto
     * @param mediaFornitoriICT
     */
    public erossPaProvincia(int idTerritorio, String territorio, int anno, float utilizzoFloss, float utilizzoFlossClient, float utilizzoFlossServer, float nonUtilizzatoriFloss, float utilizzatoriConsapevoliFloss, float utilizzatoriInconsapevoliFloss, float utilizzoFlossBrowser, float utilizzoOfficeAutomation, float utilizzoFlossPostaElettronica, float utilizzoFlossSitGis, float utilizzoFlossSO, float utilizzoFlossAppServer, float utilizzoFlossDBMS, float utilizzoFlossFileServer, float utilizzoFlossMailServer, float utilizzoFlossPrinterServer, float utilizzoFlossServerDesktopRemoto, float utilizzoFlossWebServer, float spesaMediaSoftwarePerAddetto, float mediaFornitoriICT) {
        this.idTerritorio = idTerritorio;
        this.territorio = territorio;
        this.anno = anno;
        this.utilizzoFloss = utilizzoFloss;
        this.utilizzoFlossClient = utilizzoFlossClient;
        this.utilizzoFlossServer = utilizzoFlossServer;
        this.nonUtilizzatoriFloss = nonUtilizzatoriFloss;
        this.utilizzatoriConsapevoliFloss = utilizzatoriConsapevoliFloss;
        this.utilizzatoriInconsapevoliFloss = utilizzatoriInconsapevoliFloss;
        this.utilizzoFlossBrowser = utilizzoFlossBrowser;
        this.utilizzoOfficeAutomation = utilizzoOfficeAutomation;
        this.utilizzoFlossPostaElettronica = utilizzoFlossPostaElettronica;
        this.utilizzoFlossSitGis = utilizzoFlossSitGis;
        this.utilizzoFlossSO = utilizzoFlossSO;
        this.utilizzoFlossAppServer = utilizzoFlossAppServer;
        this.utilizzoFlossDBMS = utilizzoFlossDBMS;
        this.utilizzoFlossFileServer = utilizzoFlossFileServer;
        this.utilizzoFlossMailServer = utilizzoFlossMailServer;
        this.utilizzoFlossPrinterServer = utilizzoFlossPrinterServer;
        this.utilizzoFlossServerDesktopRemoto = utilizzoFlossServerDesktopRemoto;
        this.utilizzoFlossWebServer = utilizzoFlossWebServer;
        this.spesaMediaSoftwarePerAddetto = spesaMediaSoftwarePerAddetto;
        this.mediaFornitoriICT = mediaFornitoriICT;
    }

    public int getIdTerritorio() {
        return idTerritorio;
    }

    public String getTerritorio() {
        return territorio;
    }

    public int getAnno() {
        return anno;
    }


    public float getUtilizzoFloss() {
        return utilizzoFloss;
    }

    public float getUtilizzoFlossClient() {
        return utilizzoFlossClient;
    }

    public float getUtilizzoFlossServer() {
        return utilizzoFlossServer;
    }

    public float getNonUtilizzatoriFloss() {
        return nonUtilizzatoriFloss;
    }

    public float getUtilizzatoriConsapevoliFloss() {
        return utilizzatoriConsapevoliFloss;
    }

    public float getUtilizzatoriInconsapevoliFloss() {
        return utilizzatoriInconsapevoliFloss;
    }

    public float getUtilizzoFlossBrowser() {
        return utilizzoFlossBrowser;
    }

    public float getUtilizzoOfficeAutomation() {
        return utilizzoOfficeAutomation;
    }

    public float getUtilizzoFlossPostaElettronica() {
        return utilizzoFlossPostaElettronica;
    }

    public float getUtilizzoFlossSitGis() {
        return utilizzoFlossSitGis;
    }

    public float getUtilizzoFlossSO() {
        return utilizzoFlossSO;
    }

    public float getUtilizzoFlossAppServer() {
        return utilizzoFlossAppServer;
    }

    public float getUtilizzoFlossDBMS() {
        return utilizzoFlossDBMS;
    }

    public float getUtilizzoFlossFileServer() {
        return utilizzoFlossFileServer;
    }

    public float getUtilizzoFlossMailServer() {
        return utilizzoFlossMailServer;
    }

    public float getUtilizzoFlossPrinterServer() {
        return utilizzoFlossPrinterServer;
    }

    public float getUtilizzoFlossServerDesktopRemoto() {
        return utilizzoFlossServerDesktopRemoto;
    }

    public float getUtilizzoFlossWebServer() {
        return utilizzoFlossWebServer;
    }

    public float getSpesaMediaSoftwarePerAddetto() {
        return spesaMediaSoftwarePerAddetto;
    }

    public float getMediaFornitoriICT() {
        return mediaFornitoriICT;
    }
}
