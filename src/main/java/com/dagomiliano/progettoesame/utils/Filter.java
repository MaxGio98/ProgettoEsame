package com.dagomiliano.progettoesame.utils;

import com.dagomiliano.progettoesame.model.ErossPaProvincia;
import com.dagomiliano.progettoesame.model.ErossPaProvinciaService;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * La classe Filter è stata usata per la gestione dei filtri disponibili necessarie in ErossPaProvinciaController.
 *  */
public class Filter {


    private List<ErossPaProvincia> list = new ArrayList<>();

    /**
     * Consente di parsare la stringa contenente il body, ottenendo il nome del campo, tipo di filtro e valore/i di
     * filtro
     * @param body contiene il body
     * @return lista contenente al primo elemento il campo, al secondo il filtr, al terzo il valore/i di filtro
     */
    public List parseBody(String body)
    {
        List elem=new ArrayList();
        Map<String,Object> mappa=new BasicJsonParser().parseMap(body);
        String field=mappa.keySet().toArray(new String[0])[0];
        elem.add(field);
        mappa= (Map<String, Object>) mappa.get(field);
        String filter=mappa.keySet().toArray(new String[0])[0];
        elem.add(filter);
        Object values=mappa.get(filter);
        elem.add(values);
        return elem;
    }

    /**
     * Gestisce la chiamata dei metodi che si occupano del filtraggio dei dati forniti in partenza.
     *
     * @throws ResponseStatusException restituisce un messaggio di errore personalizzato a seconda dell'inesattezza avvenuta
     * @param field contiene il campo di nostro interesse sul quale vogliamo applicare un filtro
     * @param filter contiene il filtro specificato dall'utente
     * @param param contiene il/i valore/i di riferimento per il filtraggio
     * @return Lista contenente i dati filtrati di nostro interesse
     */
    public List<ErossPaProvincia> filtering(String field,String filter, Object param) {
        try {
            Method code = ErossPaProvincia.class.getMethod("get" + field.substring(0, 1).toUpperCase() + field.substring(1));
            //se il metodo che richiamiamo non è un numero intero o decimale, il programma lancerà un errore
            if (!((code.getReturnType() == Integer.TYPE) || (code.getReturnType() == Double.TYPE))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Errore!!! Non posso effettuare questa operazione perchè " + field + " non è un dato di tipo Double o Int, ma di tipo " + code.getReturnType().getSimpleName() + "!");
            }
            //se il parametro fornito è di tipo lista
            if (param instanceof List) {
                List<Double> values = new ArrayList<>();
                //ciclo tutta la lista
                for (int i = 0; i < ((List) param).size(); i++) {
                    //controllo se tutti i parametri inseriti siano numeri, altrimenti il programma lancerà un errore
                    if (((List) param).get(i) instanceof Number) {
                        values.add(((Number) ((List) param).get(i)).doubleValue());
                    } else {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Errore! L'elemento inserito numero " +(i+1)  + " non è un numero");
                    }
                }
                //switch sulla stringa filtro passata, in minuscolo
                switch (filter.toLowerCase()) {
                    case "$in":
                        return this.in(values, code);
                        //se il filtro inserito è sbagliato verrà visualizzato un messaggio di errore
                    default:
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Filtro non valido!");
                }
                //se il parametro fornito è un numero
            } else if (param instanceof Number) {
                double value = ((Number) param).doubleValue();
                switch (filter.toLowerCase()) {
                    case "$gt":
                        return this.gr(value, code);
                    case "$lt":
                        return this.lt(value, code);
                    case "$not":
                        return this.not(value, code);
                        //se il filtro inserito è sbagliato, verrà visualizzato un messaggio di errore
                    default:
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Filtro non valido!");
                }
            }
            //se il dato non è né una lista né un numero il programma lancia un errore
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Errore!");
            }
        } catch (NoSuchMethodException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Errore nei dati inseriti! " + field + " non esiste!");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
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
    public List<ErossPaProvincia> gr(double param,Method code) {
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
    public List<ErossPaProvincia> lt(double param,Method code) {
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
    public List<ErossPaProvincia> not(double param,Method code) {
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
    public List<ErossPaProvincia> in(double param,Method code) {
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

    /**
     * Restituisce in una lista di tipo ErossPaProvincia tutti i dati che sono uguali ai valori indicati nella
     * variabile param.
     *
     * @param param contiene i valori di riferimento per il filtro
     * @param code contenente il tipo di metodo che ottiene i valori da filtrare
     * @return una lista contenente i dati filtrati
     */
    public List<ErossPaProvincia> in(List<Double> param,Method code) throws InvocationTargetException, IllegalAccessException {
        List<ErossPaProvincia> list=new ArrayList<>();
        //ciclo tutti gli elementi della lista
        for(ErossPaProvincia obj:ErossPaProvinciaService.getDatas())
        {
            //ciclo tutti i parametri
            for(double temp:param)
            {
                //se, richiamando il get del campo di nostro interessse, il valore è uguale al valore di riferimento, allora questo campo verrà aggiunto alla lista filtrata
                if (((Number) code.invoke(obj)).doubleValue() == temp) list.add(obj);

            }
        }
        return list;
    }
}