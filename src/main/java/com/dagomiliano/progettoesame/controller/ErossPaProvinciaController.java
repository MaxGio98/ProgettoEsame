package com.dagomiliano.progettoesame.controller;
import com.dagomiliano.progettoesame.model.ErossPaProvincia;
import com.dagomiliano.progettoesame.model.ErossPaProvinciaService;
import com.dagomiliano.progettoesame.model.Stats;
import com.dagomiliano.progettoesame.model.StringCount;
import com.dagomiliano.progettoesame.utils.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;

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

    @GetMapping("stats/{field}")
    public Stats getStats(@PathVariable String field) {
        return ePP.getStats(field,ePP.getDatas());
    }
    public Stats getStats(@PathVariable String field,List<ErossPaProvincia> lista) {
        return ePP.getStats(field,lista);
    }

    @GetMapping("/count")
    public List<StringCount> getCount() {
        return ePP.stringCounter();
    }

    @GetMapping("/get/filter")
    public List<ErossPaProvincia> filter(@RequestParam("field") String field, @RequestParam("filter") String filter, @RequestParam("value") int value) {
        Filter f = new Filter(filter);
        return f.filtering(field, value);
    }

    @GetMapping("/get/filter/stats")
    public Stats statsfilter(@RequestParam("field") String field, @RequestParam("filter") String filter, @RequestParam("value") int value)
    {
        List<ErossPaProvincia> lista= this.filter(field, filter, value);
        return getStats(field,lista);
    }

    @GetMapping("/get/metadata")
    public Collection getMetadata() {
        return ePP.getMetadata();
    }
}
