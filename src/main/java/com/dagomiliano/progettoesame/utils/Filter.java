package com.dagomiliano.progettoesame.utils;

import com.dagomiliano.progettoesame.model.ErossPaProvincia;
import com.dagomiliano.progettoesame.model.ErossPaProvinciaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Filter è stata usata per la gestione dei filtri disponibili necessarie in ErossPaProvinciaController.
 *  */
public class Filter {

    private String filter;
    private List<ErossPaProvincia> list = new ArrayList<>();

    /**
     * Costruttore della classe Filter
     * @param filter riceve in ingeresso il filter
     */
    public Filter(String filter) {
        this.filter = filter;
    }

    /**
     * Gestisce la chiamata dei metodi che si occupano del filtraggio dei dati forniti in partenza.
     *
     * @throws ResponseStatusException restituisce un messaggio di errore personalizzato a seconda dell'inesattezza avvenuta
     * @param field contiene il campo di nostro interesse sul quale vogliamo applicare un filtro
     * @param param contiene il valore di riferimento per il filtraggio
     * @return Lista contenente i dati filtrati di nostro interesse
     */
    public List<ErossPaProvincia> filtering(String field, int param) {
        try {
            //La variabile code di tipo Method contiene il metodo che richiama la get della variabile che contiene il campo di nostro interesse
            Method code = ErossPaProvincia.class.getMethod("get" + field.substring(0, 1).toUpperCase() + field.substring(1));
            //controllo se il metodo contenuto in code non ritorna un parametro di tipo Double o Int. In caso positivo (non è né double o int) viene generato un messaggio di errore
            if(!((code.getReturnType()==Integer.TYPE)||(code.getReturnType()==Double.TYPE)))
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Errore! Non posso effettuare questa operazione perchè "+field+" non è un dato di tipo Double o Int, ma di tipo "+code.getReturnType().getSimpleName()+"!");
            }
            //switch che si occupa della ricerca del filtro corretto (e metodo) da applicare. In caso di inserimento da parte dell'utente di un filtro errato, viene generato un messaggio di errore
            switch (this.filter.toLowerCase()) {
                case "$gt": return this.gr(param,code);
                case "$lt": return this.lt(param,code);
                case "$not": return this.not(param,code);
                case "$in" : return this.in(param,code);
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Filtro inesistente!");
            }
        } catch (NoSuchMethodException e) {
            //nel caso di inserimento errato del campo di interesse, viene generato un messaggio di errore
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Errore nei dati inseriti! "+field+" non esiste!");
        }
    }

    /**
     * Restituisce in una lista di tipo ErossPaProvincia tutti i dati che sono maggiori del valore indicato nella
     * variabile param.
     *
     * @param param contiene il valore di riferimento per il filtro
     * @param code contenente il tipo di metodo che ottiene i valori da filtrare
     * @return una lista contenente i dati filtrati
     */
    public List<ErossPaProvincia> gr(int param,Method code) {
        try {
            //immetto in obj un record della lista alla volta, per tutti gli elementi presenti in lista
            for (ErossPaProvincia obj : ErossPaProvinciaService.getDatas()) {
                //se, richiamando il get del campo di nostro interessse, il valore è maggiore del valore di riferimento, allora questo campo verrà aggiunto alla lista filtrata
                if (((Number) code.invoke(obj)).doubleValue() > param) list.add(obj);
            }
            //ritorno lista filtrata
            return list;
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Restituisce in una lista di tipo ErossPaProvincia tutti i dati che sono minori del valore indicato nella
     * variabile param.
     *
     * @param param contiene il valore di riferimento per il filtro
     * @param code contenente il tipo di metodo che ottiene i valori da filtrare
     * @return una lista contenente i dati filtrati
     */
    public List<ErossPaProvincia> lt(int param,Method code) {
        try {
            //immetto in obj un record della lista alla volta, per tutti gli elementi presenti in lista
            for (ErossPaProvincia obj : ErossPaProvinciaService.getDatas()) {
                //se, richiamando il get del campo di nostro interessse, il valore è minore del valore di riferimento, allora questo campo verrà aggiunto alla lista filtrata

                if (((Number) code.invoke(obj)).doubleValue() < param) list.add(obj);
            }

            return list;
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Restituisce in una lista di tipo ErossPaProvincia tutti i dati che sono diversi dal valore indicato nella
     * variabile param.
     *
     * @param param contiene il valore di riferimento per il filtro
     * @param code contenente il tipo di metodo che ottiene i valori da filtrare
     * @return una lista contenente i dati filtrati
     */
    public List<ErossPaProvincia> not(int param,Method code) {
        try {
            //immetto in obj un record della lista alla volta, per tutti gli elementi presenti in lista
            for (ErossPaProvincia obj : ErossPaProvinciaService.getDatas()) {
                //se, richiamando il get del campo di nostro interessse, il valore è diverso dal valore di riferimento, allora questo campo verrà aggiunto alla lista filtrata
                if (((Number) code.invoke(obj)).doubleValue() != param) list.add(obj);
            }
            return list;
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Restituisce in una lista di tipo ErossPaProvincia tutti i dati che sono uguali valore indicato nella
     * variabile param.
     *
     * @param param contiene il valore di riferimento per il filtro
     * @param code contenente il tipo di metodo che ottiene i valori da filtrare
     * @return una lista contenente i dati filtrati
     */
    public List<ErossPaProvincia> in(int param,Method code) {
        try {
            //immetto in obj un record della lista alla volta, per tutti gli elementi presenti in lista
            List<ErossPaProvincia> list = new ArrayList<>();
            for (ErossPaProvincia obj : ErossPaProvinciaService.getDatas()) {
                //se, richiamando il get del campo di nostro interessse, il valore è uguale al valore di riferimento, allora questo campo verrà aggiunto alla lista filtrata

                if (((Number) code.invoke(obj)).doubleValue() == param) list.add(obj);
            }
            return list;
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}