package com.dagomiliano.progettoesame.model;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.dagomiliano.progettoesame.utils.CsvParser;
import org.springframework.web.server.ResponseStatusException;

/**
 * La classe ErossPaProvinciaService contiene tutti i metodi che faranno ritornare i dati filtrati
 * all'utente e contiene le istruzioni per l'avvio corretto del programma (verrà richiamato il metodo checkSER di
 * CsvParser)
 */

@Component
public class ErossPaProvinciaService {
    //dichiarazione della variabile meta di tipo array di stringhe
    private static String[] meta;
    //dichiarazione della variabile provincias di tipo Lista di ErossPaProvincia
    private static List<ErossPaProvincia> provincias = new ArrayList<>();

    {
        // Inizializza i dati
        CsvParser p = new CsvParser();
        //richiamo metodo checkSER
        p.checkSER();
        //copia in provincias il contenuto di tutta la lista ottenuta, cioè i dati del .csv parsato
        provincias = p.getList();
        //copia in meta il contenuto di tutto l'array ottenuto, cioè i metadati del file .csv parsato
        meta = p.getMetaData();
    }

    /**
     * metodo che consente di ottenenere la lista provincias
     *
     * @return tutta la lista
     */
    public static List<ErossPaProvincia> getDatas() {
        return provincias;
    }

    /**
     * Metodo che consente di ottenere, fornendo al metodo come parametro un id, tutte le informazioni riguardanti
     * un record specifico.
     *
     * @param id id di ricerca
     * @return oggetto di tipo ErossPaProvincia contenente tutte le informazioni riguardanti il record filtrato
     */
    public ErossPaProvincia getDatoById(int id) {
        //il for fa ciclare la lista contenente i dati elemento per elemento
        for (ErossPaProvincia obj : provincias) {
            //se l'id combacia
            if (obj.getIdTerritorio() == id) {
                //ritorno il record desiderato
                return obj;
            }
        }
        //se non è stato trovato niente, allora verrà stampato un messaggio di errore all'utente.
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id inesistente");
    }

    /**
     * Metodo che consente di ottenere, fornendo al metodo come parametro le iniziali della provincia, tutte le informazioni
     * riguardanti quella provincia
     *
     * @param prov contenente la sigla della provincia
     * @return oggetto contenente le informazioni desiderate
     */
    public ErossPaProvincia getDatoByProvincia(String prov) {
        String provTemp = "terr.prov. di " + prov;
        for (ErossPaProvincia obj : provincias) {
            if (obj.getTerritorio().equals(provTemp)){
                return obj;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Errore nei dati inseriti");
    }

    /**
     * Metodo che consente di ottenere tutti i dati statistici richiesti su un determinato campo di interesse
     *
     * @param field contiene il nome del campo di ricerca specificato
     * @param lista contiene la lista intera o filtrata
     * @return variabile di tipo Stats contenente il risultato delle statistiche
     */
    public Stats getStats(String field, List<ErossPaProvincia> lista) {
        try {
            //controllo se la lista data è vuota
            if(lista.isEmpty())
            {
                //la lista è vuota, quindi non è stato trovato nessun record che soddisfaceva il filtro assegnato
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nessun valore trovato per questo parametro!");
            }
            //creo la variabile di tipo metodo, contenente il nome del metodo da richiamare in ErossPaProvincia
            Method code = ErossPaProvincia.class.getMethod("get" + field.substring(0, 1).toUpperCase() + field.substring(1));
            //controllo se il metodo selezionato non sia di tipo double o int
            if(!((code.getReturnType()==Integer.TYPE)||(code.getReturnType()==Double.TYPE)))
            {
                /*se non lo è, non si possono effettuare dati statistici, poiché nel nostro dataset l'unico campo non
                 di tipo double o int è di tipo stringa. Quindi il programma lancia un messaggio di errore*/
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Attenzione! Non posso effettuare questa operazione perchè "+field+" non è un dato di tipo Double o Int, ma di tipo "+code.getReturnType().getSimpleName()+"!");
            }
            //inizializzazione variabili
            double sum = 0;
            double avg = 0;
            double devStd = 0;
            double max;
            double min;
            max = ((Number) code.invoke(lista.get(0))).doubleValue();
            min = ((Number) code.invoke(lista.get(0))).doubleValue();
            //calcolo sommatoria di tutti gli elementi selezionati presenti
            for (ErossPaProvincia obj : lista) {
                double temp = ((Number) code.invoke(obj)).doubleValue();
                sum += temp;
            }
            //calcolo media
            avg=sum/lista.size();
            //calcolo della sommatoria quadratica della deviazione standard, trova massimo e minimo
            for (ErossPaProvincia obj : lista) {
                double temp = ((Number) code.invoke(obj)).doubleValue();
                devStd += (temp - avg) * (temp - avg);
                if (temp > max) max = temp;
                if (temp < min) min = temp;
            }
            //calcola deviazione standard
            devStd = Math.sqrt(devStd)/lista.size();
            //creo variabile di tipo Stats, passando i parametri statistici di interesse
            Stats ret = new Stats(field, avg, devStd, max, min, sum, lista.size());
            //ritorno il risultato
            return ret;
        } catch (NoSuchMethodException e)  {
            e.printStackTrace();
            //viene lansciata questa eccezione nel caso il campo immesso non esiste
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Errore nei dati inseriti! "+field+" non esiste!");
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * Metodo che consente di ottenere come risultato il conteggio di quante volte sono presenti delle province
     * nella lista ottenuta.
     *
     * @return una lista contenente i nomi delle province e quante volte si ripetono all'interno di essa.
     */
    public List<StringCount> stringCounter() {
        List<StringCount> ret = new ArrayList<>();
        List<String> check = new ArrayList<>();
        /*creazione e inizializzazione della variabile counter di tipo ad int ad 1 poiché un elemento può ripetersi
        almeno una volta*/
        int counter;
        boolean flag = true;
        while(flag) {
            //se si è al primo ciclo si procede all'inserimento del primo elemento in lista
//            if (ret.size() == 0) {
//
//                StringCount n = new StringCount(this.provincias.get(0).getTerritorio());
//                for (int i = 1; i < provincias.size(); i++) {
//                    if (n.getField().equals(this.provincias.get(i).getTerritorio())) {
//                        counter = counter + 1;
//                    }
//                }
//                counter=1;
//                n.setCount(counter);
//                ret.add(n);
//                check.add(n.getField());
//            } else {
                /*controllando se il campo è già stato usato per il conteggio, incremento il valore count
                  per quante volte esso sia presente in lista. Se il campo è già stato usato, significa che il
                  conteggio è stato effettuato, quindi procedo con il for passando al campo successivo*/
                for (int i = 0; i < this.provincias.size(); i++) {
                    if (!(check.contains(this.provincias.get(i).getTerritorio()))) {
                        StringCount n = new StringCount(this.provincias.get(i).getTerritorio());
                        counter = 1;
                        for (int j = i + 1; j < this.provincias.size(); j++) {
                            if (n.getField().equals(this.provincias.get(j).getTerritorio())) {
                                counter = counter + 1;
                            }
                        }
                        n.setCount(counter);
                        ret.add(n);
                        check.add(n.getField());
                    }
                }
                flag = false;
            }
        //}
        return ret;
    }

    /**
     * Metodo che consente di ottenere in risultato per ogni campo l'alias, il sourceField e il tipo
     *
     * @return collection che contiene tutte le informazioni riguardanti i metadati.
     */
    public Collection getMetadata() {
        int i=0;
        List<MetaData> metaRet = new ArrayList<>();
        Field[] field = ErossPaProvincia.class.getDeclaredFields();
        for (Field o : field) {
            MetaData newMeta = new MetaData();
            newMeta.setType(o.getType().getSimpleName());
            newMeta.setAlias(o.getName());
            newMeta.setSourceField(meta[i]);
            metaRet.add(newMeta);
            i+=1;
        }
        return metaRet;
    }
   // END SERVICE
}
