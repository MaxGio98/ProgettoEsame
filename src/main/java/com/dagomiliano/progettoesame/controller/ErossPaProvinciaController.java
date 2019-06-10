package com.dagomiliano.progettoesame.controller;

import com.dagomiliano.progettoesame.model.ErossPaProvincia;
import com.dagomiliano.progettoesame.model.ErossPaProvinciaService;
import com.dagomiliano.progettoesame.model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ErossPaProvinciaController {

    @Autowired
    private ErossPaProvinciaService ePP;

    @GetMapping("/id/{id}")
    public ErossPaProvincia searchProvincia(@PathVariable int id)
    {
        return ePP.getDatoById(id);
    }

    @GetMapping("/territorio/{territorio}")
    public ErossPaProvincia searchProvincia(@PathVariable String territorio)
    {
        return ePP.getDatoByProvincia(territorio);
    }

    @GetMapping("/all")
    public List<ErossPaProvincia> searchAll() {
        return ePP.getAll();
    }

//    @GetMapping("/floss/{stat}/{campo}")
//    public Map getAvg(@PathVariable String stat, @PathVariable String campo) {
//        return ePP.getStats(stat, campo);
//    }

    @GetMapping("stats/{field}")
    public Stats getStats(@PathVariable String field) {
        return ePP.getStats(field);
    }

}
