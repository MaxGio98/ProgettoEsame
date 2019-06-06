package com.dagomiliano.progettoesame.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class erossPaProvinciaService {

    private static List<erossPaProvincia> provincias = new ArrayList<>();

    {
        // Inizializza i dati
        CsvParser p = new CsvParser();
        p.checkSER();
        provincias = p.getList();
    }

    public List<erossPaProvincia> getDatas() {
        return provincias;
    }

    public erossPaProvincia getDatoById(int id) {
        for (erossPaProvincia obj : provincias) {
            if (obj.getIdTerritorio() == id) {
                return obj;
            }
        }
        return null;
    }

    public erossPaProvincia getDatoByProvincia(String prov) {
        if (prov.equals("ER")) {
            return provincias.get(provincias.size()-1);
        } else {
            String provTemp = "prov. di " + prov;
            for (erossPaProvincia obj : provincias) {
                if (obj.getTerritorio().equals(provTemp)){
                    return obj;
                }
            }
            System.out.println("Parametro non corretto");
            return null;
        }
    }

   // END SERVICE
}
