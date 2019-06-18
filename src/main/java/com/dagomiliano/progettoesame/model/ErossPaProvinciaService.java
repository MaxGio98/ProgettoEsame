package com.dagomiliano.progettoesame.model;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.dagomiliano.progettoesame.utils.CsvParser;
import com.dagomiliano.progettoesame.model.MetaData;
import org.springframework.web.server.ResponseStatusException;


@Component
public class ErossPaProvinciaService {

    private String[] meta;
    private static List<ErossPaProvincia> provincias = new ArrayList<>();

    {
        // Inizializza i dati
        CsvParser p = new CsvParser();
        p.checkSER();
        provincias = p.getList();
        meta = p.getMetaData();
    }

    public static List<ErossPaProvincia> getDatas() {
        return provincias;
    }

    public ErossPaProvincia getDatoById(int id) {
        for (ErossPaProvincia obj : provincias) {
            if (obj.getIdTerritorio() == id) {
                return obj;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Errore nei dati inseriti");
    }

    public ErossPaProvincia getDatoByProvincia(String prov) {
        String provTemp = "terr.prov. di " + prov;
        for (ErossPaProvincia obj : provincias) {
            if (obj.getTerritorio().equals(provTemp)){
                return obj;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Errore nei dati inseriti");
    }

    public List<ErossPaProvincia> getAll() {
        return provincias;
    }

    public double media(String campo, List<ErossPaProvincia>lista) {
        double avg = 0;
        try {
            Method code = ErossPaProvincia.class.getMethod("get" + campo.substring(0, 1).toUpperCase() + campo.substring(1));
            for (ErossPaProvincia obj : lista) {
                avg+=((Number)code.invoke(obj)).doubleValue();
           }
            avg /= lista.size();
            return avg;
        } catch (NoSuchMethodException e)  {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Errore nei dati inseriti");
        } catch (SecurityException e) {
            e.printStackTrace();
            return 0;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Stats getStats(String field, List<ErossPaProvincia> lista) {
        try {
            if(lista.isEmpty())
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nessun valore trovato per questo parametro!");
            }
            Method code = ErossPaProvincia.class.getMethod("get" + field.substring(0, 1).toUpperCase() + field.substring(1));
            if(!((code.getReturnType()==Integer.TYPE)||(code.getReturnType()==Double.TYPE)))
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Attenzione! Non posso effettuare questa operazione perchè "+field+" non è un dato di tipo Double o Int, ma di tipo "+code.getReturnType().getSimpleName()+"!");
            }
            double sum = 0;
            double avg = this.media(field,lista);
            double devStd = 0;
            double max;
            double min;
            max = ((Number) code.invoke(lista.get(0))).doubleValue();
            min = ((Number) code.invoke(lista.get(0))).doubleValue();
            for (ErossPaProvincia obj : lista) {
                double temp = ((Number) code.invoke(obj)).doubleValue();
                sum += temp;
                devStd += (temp - avg) * (temp - avg);
                if (temp > max) max = temp;
                if (temp < min) min = temp;
            }
            devStd = Math.sqrt(devStd)/lista.size();
            Stats ret = new Stats(field, avg, devStd, max, min, sum, lista.size());
            return ret;
        } catch (NoSuchMethodException e)  {
            e.printStackTrace();
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

    public List<StringCount> stringCounter() {
        List<StringCount> ret = new ArrayList<>();
        List<String> check = new ArrayList<>();
        int counter = 1;
        boolean flag = true;

        while(flag) {
            if (ret.size() == 0) {
                StringCount n = new StringCount(this.provincias.get(0).getTerritorio());
                for (int i = 1; i < provincias.size(); i++) {
                    if (n.getField().equals(this.provincias.get(i).getTerritorio())) {
                        counter = counter + 1;
                    }
                }
                n.setCount(counter);
                ret.add(n);
                check.add(n.getField());
            } else {
                for (int i = 1; i < this.provincias.size() - 1; i++) {
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
        }
        return ret;
    }

    public Collection getMetadata() {
        List<MetaData> metaRet = new ArrayList<>();
        Field[] field = ErossPaProvincia.class.getDeclaredFields();
        int i = 0;

        for (Field o : field) {
            MetaData newMeta = new MetaData();
            newMeta.setType(o.getType().getSimpleName());
            newMeta.setAlias(o.getName());
            newMeta.setSourceField(meta[i]);
            metaRet.add(newMeta);
            i += 1;
        }
        return metaRet;
    }
   // END SERVICE
}
