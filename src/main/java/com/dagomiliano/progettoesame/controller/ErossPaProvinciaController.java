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

    private String field;

    @Autowired
    private ErossPaProvinciaService ePP;

    @GetMapping("/id/{id}")
    public ErossPaProvincia searchProvincia(@PathVariable int id)
    {
        return ePP.getDatoById(id);
    }

    @GetMapping("/all")
    public List<ErossPaProvincia> searchAll() {
        return ePP.getDatas();
    }

    //getmapping con metodi in overload
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

    @GetMapping("/get/metadata")
    public Collection getMetadata() {
        return ePP.getMetadata();
    }

    @PostMapping("/post/filter/")
    public List<ErossPaProvincia> filterpost(@RequestBody String body)
    {
        Filter f=new Filter();
        List l=f.parseBody(body);
        this.field=l.get(0).toString();
        return f.filtering1(this.field,l.get(1).toString(),l.get(2));
    }

    @PostMapping("/post/filter/stats")
    public Stats filterpoststats(@RequestBody String body)
    {
       List<ErossPaProvincia>lista=this.filterpost(body);
       return getStats(this.field,lista);
    }

}
