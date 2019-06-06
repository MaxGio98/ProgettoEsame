package com.dagomiliano.progettoesame.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ErossPaProvinciaService {

    private static List<ErossPaProvincia> provincias = new ArrayList<>();

    {
        // Inizializza i dati
        CsvParser p = new CsvParser();
        p.checkSER();
        provincias = p.getList();
    }

    public List<ErossPaProvincia> getDatas() {
        return provincias;
    }

    public ErossPaProvincia getDatoById(int id) {
        for (ErossPaProvincia obj : provincias) {
            if (obj.getIdTerritorio() == id) {
                return obj;
            }
        }
        ErossPaProvincia err=new ErossPaProvincia(0,"ERRORE ID INSERITO",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        return err;
    }

    public ErossPaProvincia getDatoByProvincia(String prov) {
        if (prov.equals("ER")) {
            return provincias.get(provincias.size()-1);
        } else {
            String provTemp = "terr.prov. di " + prov;
            for (ErossPaProvincia obj : provincias) {
                if (obj.getTerritorio().equals(provTemp)){
                    return obj;
                }
            }
            System.out.println("Parametro non corretto");
            ErossPaProvincia err=new ErossPaProvincia(0,"ERRORE PROVINCIA INSERITA",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
            return err;
        }
    }

    public List<ErossPaProvincia> getAll() {
        return provincias;
    }

    public Map avg() {
        float utilizzoFloss = 0;
        float utilizzoFlossClient = 0;
        float utilizzoFlossServer = 0;
        float nonUtilizzatoriFloss = 0;
        float utilizzatoriConsapevoliFloss = 0;
        float utilizzatoriInconsapevoliFloss = 0;
        float utilizzoFlossBrowser = 0;
        float utilizzoOfficeAutomation = 0;
        float utilizzoFlossPostaElettronica = 0;
        float utilizzoFlossSitGis = 0;
        float utilizzoFlossSO = 0;
        float utilizzoFlossAppServer = 0;
        float utilizzoFlossDBMS = 0;
        float utilizzoFlossFileServer = 0;
        float utilizzoFlossMailServer = 0;
        float utilizzoFlossPrinterServer = 0;
        float utilizzoFlossServerDesktopRemoto = 0;
        float utilizzoFlossWebServer = 0;
        float spesaMediaSoftwarePerAddetto = 0;
        float mediaFornitoriICT = 0;
        for (ErossPaProvincia obj : provincias) {
           // if (obj.getIdTerritorio() == 448) continue;
            utilizzoFloss += obj.getUtilizzoFloss() / 9;
            utilizzoFlossClient += obj.getUtilizzoFlossClient() / 9;
            utilizzoFlossServer += obj.getUtilizzoFlossServer() / 9;
            nonUtilizzatoriFloss += obj.getNonUtilizzatoriFloss() / 9;
            utilizzatoriConsapevoliFloss += obj.getUtilizzatoriConsapevoliFloss() / 9;
            utilizzatoriInconsapevoliFloss += obj.getUtilizzatoriInconsapevoliFloss() / 9;
            utilizzoFlossBrowser += obj.getUtilizzoFlossBrowser() / 9;
            utilizzoOfficeAutomation += obj.getUtilizzoOfficeAutomation() / 9;
            utilizzoFlossPostaElettronica += obj.getUtilizzoFlossPostaElettronica() / 9;
            utilizzoFlossSitGis += obj.getUtilizzoFlossSitGis() / 9;
            utilizzoFlossSO += obj.getUtilizzoFlossSO() / 9;
            utilizzoFlossAppServer += obj.getUtilizzoFlossAppServer() / 9;
            utilizzoFlossDBMS += obj.getUtilizzoFlossDBMS() / 9;
            utilizzoFlossFileServer += obj.getUtilizzoFlossFileServer() / 9;
            utilizzoFlossMailServer += obj.getUtilizzoFlossMailServer() / 9;
            utilizzoFlossPrinterServer += obj.getUtilizzoFlossPrinterServer() / 9;
            utilizzoFlossServerDesktopRemoto += obj.getUtilizzoFlossServerDesktopRemoto() / 9;
            utilizzoFlossWebServer += obj.getUtilizzoFlossWebServer() / 9;
            spesaMediaSoftwarePerAddetto += obj.getSpesaMediaSoftwarePerAddetto() / 9;
            mediaFornitoriICT += obj.getMediaFornitoriICT() / 9;
        }

        Map<String,String> avg = new HashMap<String, String>();
        avg.put("Media utilizzo floss", Float.toString(utilizzoFloss) + "%");
        avg.put("Media utilizzo floss client", Float.toString(utilizzoFlossClient) + "%");
        avg.put("Media utilizzo floss server", Float.toString(utilizzoFlossServer) + "%");
        avg.put("Media non utilizzatori floss", Float.toString(nonUtilizzatoriFloss) + "%");
        avg.put("Media utilizzatori consapevoli floss", Float.toString(utilizzatoriConsapevoliFloss) + "%");
        avg.put("Media utilizzatori inconsapevoli floss", Float.toString(utilizzatoriInconsapevoliFloss) + "%");
        avg.put("Media utilizzo floss browser", Float.toString(utilizzoFlossBrowser) + "%");
        avg.put("Media utilizzo office automation", Float.toString(utilizzoOfficeAutomation) + "%");
        avg.put("Media utilizzo floss posta elettronica", Float.toString(utilizzoFlossPostaElettronica) + "%");
        avg.put("Media utilizzo floss SitGis", Float.toString(utilizzoFlossSitGis) + "%");
        avg.put("Media utilizzo floss SO", Float.toString(utilizzoFlossSO) + "%");
        avg.put("Media utilizzo floss App Server", Float.toString(utilizzoFlossAppServer) + "%");
        avg.put("Media utilizzo floss DBMS", Float.toString(utilizzoFlossDBMS) + "%");
        avg.put("Media utilizzo floss File Server", Float.toString(utilizzoFlossFileServer) + "%");
        avg.put("Media utilizzo floss mail Server", Float.toString(utilizzoFlossMailServer) + "%");
        avg.put("Media utilizzo floss Printer Server", Float.toString(utilizzoFlossPrinterServer) + "%");
        avg.put("Media utilizzo floss Server Desktop Remoto", Float.toString(utilizzoFlossServerDesktopRemoto) +"%");
        avg.put("Media utilizzo floss Web Server", Float.toString(utilizzoFlossWebServer) + "%");
        avg.put("Media spesa media software per addetto", Float.toString(spesaMediaSoftwarePerAddetto) + "%");
        avg.put("Media media fornitori ICT", Float.toString(mediaFornitoriICT) + "%");

        return avg;
    }
   // END SERVICE
}
