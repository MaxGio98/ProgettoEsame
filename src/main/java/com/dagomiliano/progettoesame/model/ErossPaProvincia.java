package com.dagomiliano.progettoesame.model;

import java.io.Serializable;

/**
 *  Classe modellante dei dati contenente gli attributi presenti nel file .csv
 *
 *  Il file proposto contiene dati inerenti l'utilizzo di software con licenza FLOSS nelle province
 *  dell' Emilia Romagno nel periodo dicembre 2007 - marzo 2008.
 */

public class ErossPaProvincia implements Serializable {

    private int idTerritorio;
    private String territorio;
    private int anno;
    private int utilizzoFloss;
    private int utilizzoFlossClient;
    private int utilizzoFlossServer;
    private int nonUtilizzatoriFloss;
    private int utilizzatoriConsapevoliFloss;
    private int utilizzatoriInconsapevoliFloss;
    private int utilizzoFlossBrowser;
    private int utilizzoOfficeAutomation;
    private int utilizzoFlossPostaElettronica;
    private int utilizzoFlossSitGis;
    private int utilizzoFlossSO;
    private int utilizzoFlossAppServer;
    private int utilizzoFlossDBMS;
    private int utilizzoFlossFileServer;
    private int utilizzoFlossMailServer;
    private int utilizzoFlossPrinterServer;
    private int utilizzoFlossServerDesktopRemoto;
    private int utilizzoFlossWebServer;
    private double spesaMediaSoftwarePerAddetto;
    private int mediaFornitoriICT;

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
    public ErossPaProvincia(int idTerritorio, String territorio, int anno, int utilizzoFloss, int utilizzoFlossClient, int utilizzoFlossServer, int nonUtilizzatoriFloss, int utilizzatoriConsapevoliFloss, int utilizzatoriInconsapevoliFloss, int utilizzoFlossBrowser, int utilizzoOfficeAutomation, int utilizzoFlossPostaElettronica, int utilizzoFlossSitGis, int utilizzoFlossSO, int utilizzoFlossAppServer, int utilizzoFlossDBMS, int utilizzoFlossFileServer, int utilizzoFlossMailServer, int utilizzoFlossPrinterServer, int utilizzoFlossServerDesktopRemoto, int utilizzoFlossWebServer, double spesaMediaSoftwarePerAddetto, int mediaFornitoriICT) {
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

    //Getters

    public int getAnno() {
        return anno;
    }

    public int getUtilizzoFloss() {
        return utilizzoFloss;
    }

    public int getUtilizzoFlossClient() {
        return utilizzoFlossClient;
    }

    public int getUtilizzoFlossServer() {
        return utilizzoFlossServer;
    }

    public int getNonUtilizzatoriFloss() {
        return nonUtilizzatoriFloss;
    }

    public int getUtilizzatoriConsapevoliFloss() {
        return utilizzatoriConsapevoliFloss;
    }

    public int getUtilizzatoriInconsapevoliFloss() {
        return utilizzatoriInconsapevoliFloss;
    }

    public int getUtilizzoFlossBrowser() {
        return utilizzoFlossBrowser;
    }

    public int getUtilizzoOfficeAutomation() {
        return utilizzoOfficeAutomation;
    }

    public int getUtilizzoFlossPostaElettronica() {
        return utilizzoFlossPostaElettronica;
    }

    public int getUtilizzoFlossSitGis() {
        return utilizzoFlossSitGis;
    }

    public int getUtilizzoFlossSO() {
        return utilizzoFlossSO;
    }

    public int getUtilizzoFlossAppServer() {
        return utilizzoFlossAppServer;
    }

    public int getUtilizzoFlossDBMS() {
        return utilizzoFlossDBMS;
    }

    public int getUtilizzoFlossFileServer() {
        return utilizzoFlossFileServer;
    }

    public int getUtilizzoFlossMailServer() {
        return utilizzoFlossMailServer;
    }

    public int getUtilizzoFlossPrinterServer() {
        return utilizzoFlossPrinterServer;
    }

    public int getUtilizzoFlossServerDesktopRemoto() {
        return utilizzoFlossServerDesktopRemoto;
    }

    public int getUtilizzoFlossWebServer() {
        return utilizzoFlossWebServer;
    }

    public double getSpesaMediaSoftwarePerAddetto() {
        return spesaMediaSoftwarePerAddetto;
    }

    public int getMediaFornitoriICT() {
        return mediaFornitoriICT;
    }

    @Override
    public String toString() {
        return "ErossPaProvincia{" +
                "idTerritorio=" + idTerritorio +
                ", territorio='" + territorio + '\'' +
                ", anno=" + anno +
                ", utilizzoFloss=" + utilizzoFloss +
                ", utilizzoFlossClient=" + utilizzoFlossClient +
                ", utilizzoFlossServer=" + utilizzoFlossServer +
                ", nonUtilizzatoriFloss=" + nonUtilizzatoriFloss +
                ", utilizzatoriConsapevoliFloss=" + utilizzatoriConsapevoliFloss +
                ", utilizzatoriInconsapevoliFloss=" + utilizzatoriInconsapevoliFloss +
                ", utilizzoFlossBrowser=" + utilizzoFlossBrowser +
                ", utilizzoOfficeAutomation=" + utilizzoOfficeAutomation +
                ", utilizzoFlossPostaElettronica=" + utilizzoFlossPostaElettronica +
                ", utilizzoFlossSitGis=" + utilizzoFlossSitGis +
                ", utilizzoFlossSO=" + utilizzoFlossSO +
                ", utilizzoFlossAppServer=" + utilizzoFlossAppServer +
                ", utilizzoFlossDBMS=" + utilizzoFlossDBMS +
                ", utilizzoFlossFileServer=" + utilizzoFlossFileServer +
                ", utilizzoFlossMailServer=" + utilizzoFlossMailServer +
                ", utilizzoFlossPrinterServer=" + utilizzoFlossPrinterServer +
                ", utilizzoFlossServerDesktopRemoto=" + utilizzoFlossServerDesktopRemoto +
                ", utilizzoFlossWebServer=" + utilizzoFlossWebServer +
                ", spesaMediaSoftwarePerAddetto=" + spesaMediaSoftwarePerAddetto +
                ", mediaFornitoriICT=" + mediaFornitoriICT +
                '}';
    }
}