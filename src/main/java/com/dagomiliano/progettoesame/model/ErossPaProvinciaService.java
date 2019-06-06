package com.dagomiliano.progettoesame.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ErossPaProvinciaService {

    private static List<ErossPaProvincia> provincias = new ArrayList<>();
    static
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

   // END SERVICE
}
