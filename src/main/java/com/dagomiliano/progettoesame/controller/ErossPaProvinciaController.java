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

/**
 * Classe controller del pattern MVC che gestische le richieste tramite api get/post.
 * Al suo interno è presente un oggetto di {@link ErossPaProvinciaService}
 */

@RestController
public class ErossPaProvinciaController {


    @Autowired
    private ErossPaProvinciaService ePP;

    /**
     * metodo GetMapping che consente di visualizzare un record in base al suo id
     * @param id id specificato dall'utente di un record da voler visualizzare
     * @return ogni campo del record con l'id specificato
     */
    @GetMapping("/id/{id}")
    public ErossPaProvincia searchProvincia(@PathVariable int id)
    {
        return ePP.getDatoById(id);
    }

    /**
     * metodo GetMapping che consente di visualizzare tutti i record contenuti nella lista
     * @return tutti i record presenti
     */
    @GetMapping("/all")
    public List<ErossPaProvincia> searchAll() {
        return ePP.getDatas();
    }

    /**
     * metodo GetMapping che consente di visualizzare i dati statistici di un determinato campo
     *
     * @param field stringa che contiene il nome del campo sul quale si vuole fare delle statistiche
     * @return statistiche richieste
     */
    @GetMapping("stats/{field}")
    /*
    * Overloading per questi due metodi.
    * Il primo ritorna i campi di tutta la lista.
    * Il secondo ritorna i campi della lista filtrata
    * */
    public Stats getStats(@PathVariable String field) {
        return ePP.getStats(field,ePP.getDatas());
    }
    public Stats getStats(@PathVariable String field,List<ErossPaProvincia> lista) {
        return ePP.getStats(field,lista);
    }

    /**
     * Metodo GetMapping che consente di visualizzare il conteggio di stringhe di elementi unici
     * @return conteggio elementi unici
     */
    @GetMapping("/count")
    public List<StringCount> getCount() {
        return ePP.stringCounter();
    }

    /**
     * Metodo GetMapping che consente di viusualizzare tutte le informazioni sui metadata
     * @return tutte le informazioni riguardanti i metadata
     */
    @GetMapping("/get/metadata")
    public Collection getMetadata() {
        return ePP.getMetadata();
    }
    private String field;

    /**
     * metodo PostMapping che consente di visualizzare dei dati filtrati
     * @param body contiene il body inserito dall'utente
     * @return record filtrati
     */
    @PostMapping("/post/filter")
    public List<ErossPaProvincia> filterpost(@RequestBody String body)
    {
        Filter f=new Filter();
        List l=f.parseBody(body);
        this.field=l.get(0).toString();
        return f.filtering(this.field,l.get(1).toString(),l.get(2));
    }

    /**
     * Metodo PostMapping che consente di visualizzare i dati statistici di dati filtrati
     * @param body contiene il body inserito dall'utente
     * @return statistiche record filtrati
     */
    @PostMapping("/post/filter/stats")
    public Stats filterpoststats(@RequestBody String body)
    {
       List<ErossPaProvincia>lista=this.filterpost(body);
       return getStats(this.field,lista);
    }

}
