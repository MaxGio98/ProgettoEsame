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
        int utilizzoFloss = 0;
        int utilizzoFlossClient = 0;
        int utilizzoFlossServer = 0;
        int nonUtilizzatoriFloss = 0;
        int utilizzatoriConsapevoliFloss = 0;
        int utilizzatoriInconsapevoliFloss = 0;
        int utilizzoFlossBrowser = 0;
        int utilizzoOfficeAutomation = 0;
        int utilizzoFlossPostaElettronica = 0;
        int utilizzoFlossSitGis = 0;
        int utilizzoFlossSO = 0;
        int utilizzoFlossAppServer = 0;
        int utilizzoFlossDBMS = 0;
        int utilizzoFlossFileServer = 0;
        int utilizzoFlossMailServer = 0;
        int utilizzoFlossPrinterServer = 0;
        int utilizzoFlossServerDesktopRemoto = 0;
        int utilizzoFlossWebServer = 0;
        double spesaMediaSoftwarePerAddetto = 0;
        int mediaFornitoriICT = 0;
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
        avg.put("Media utilizzo floss", utilizzoFloss + "%");
        avg.put("Media utilizzo floss client", utilizzoFlossClient + "%");
        avg.put("Media utilizzo floss server", utilizzoFlossServer + "%");
        avg.put("Media non utilizzatori floss", nonUtilizzatoriFloss + "%");
        avg.put("Media utilizzatori consapevoli floss", utilizzatoriConsapevoliFloss + "%");
        avg.put("Media utilizzatori inconsapevoli floss", utilizzatoriInconsapevoliFloss + "%");
        avg.put("Media utilizzo floss browser", utilizzoFlossBrowser + "%");
        avg.put("Media utilizzo office automation", utilizzoOfficeAutomation + "%");
        avg.put("Media utilizzo floss posta elettronica", utilizzoFlossPostaElettronica + "%");
        avg.put("Media utilizzo floss SitGis", utilizzoFlossSitGis + "%");
        avg.put("Media utilizzo floss SO", utilizzoFlossSO + "%");
        avg.put("Media utilizzo floss App Server", utilizzoFlossAppServer + "%");
        avg.put("Media utilizzo floss DBMS", utilizzoFlossDBMS + "%");
        avg.put("Media utilizzo floss File Server", utilizzoFlossFileServer + "%");
        avg.put("Media utilizzo floss mail Server", utilizzoFlossMailServer + "%");
        avg.put("Media utilizzo floss Printer Server", utilizzoFlossPrinterServer + "%");
        avg.put("Media utilizzo floss Server Desktop Remoto", utilizzoFlossServerDesktopRemoto +"%");
        avg.put("Media utilizzo floss Web Server", utilizzoFlossWebServer + "%");
        avg.put("Media spesa media software per addetto", spesaMediaSoftwarePerAddetto + "%");
        avg.put("Media media fornitori ICT", mediaFornitoriICT + "%");

        return avg;
    }
   // END SERVICE
}
