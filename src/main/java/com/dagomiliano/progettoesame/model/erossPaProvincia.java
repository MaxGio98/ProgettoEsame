package com.dagomiliano.progettoesame.model;

/**
 * Classe rappresentante le statistiche inerenti l'utilizzo di software con licenze di tipo Floss
 */

public class erossPaProvincia {

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
}
