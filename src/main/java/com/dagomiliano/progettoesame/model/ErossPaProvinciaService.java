package com.dagomiliano.progettoesame.model;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

//import com.dagomiliano.progettoesame.utils.CsvParser;


@Component
public class ErossPaProvinciaService {

    private static List<ErossPaProvincia> provincias = new ArrayList<>();

    {
        // Inizializza i dati
        com.dagomiliano.progettoesame.model.CsvParser p = new com.dagomiliano.progettoesame.model.CsvParser();
        p.checkSER();
        provincias = p.getList();
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
        ErossPaProvincia err=new ErossPaProvincia(0,"ERRORE ID INSERITO",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        return err;
    }

    public ErossPaProvincia getDatoByProvincia(String prov) {
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

    public List<ErossPaProvincia> getAll() {
        return provincias;
    }


    public double media(String campo) {
        int avg = 0;
        try {
            Method code = ErossPaProvincia.class.getMethod("get" + campo.substring(0, 1).toUpperCase() + campo.substring(1));
            for (ErossPaProvincia obj : provincias) {
                avg += (int) code.invoke(obj);
            }
            avg /= this.provincias.size();
            return avg;
        } catch (NoSuchMethodException e)  {
            e.printStackTrace();
            return 0;
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

    public Stats getStats(String field) {

        if(field.equals("territorio")||field.equals("Territorio"))
        {
            return this.counterStringheUguali().get(0);
        }
        else
        {
            int sum = 0;
            double avg = this.media(field);
            double devStd = 0;
            int max;
            int min;
            try {
                Method code = ErossPaProvincia.class.getMethod("get" + field.substring(0, 1).toUpperCase() + field.substring(1));
                max = (int) code.invoke(provincias.get(0));
                min = (int) code.invoke(provincias.get(0));

                for (ErossPaProvincia obj : provincias) {
                    int temp = (int) code.invoke(obj);
                    sum += temp;
                    devStd += (temp - avg) * (temp - avg);
                    if (temp > max) max = temp;
                    if (temp < min) min = temp;
                }
                devStd = Math.sqrt(devStd)/this.provincias.size();

                Stats ret = new Stats(field, avg, devStd, max, min, sum, provincias.size());
                return ret;

            } catch (NoSuchMethodException e)  {
                e.printStackTrace();
                return null;
            } catch (SecurityException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }

        }
        return null;
    }

    public List<Stats> counterStringheUguali()
    {
        List<Stats> trovato=new ArrayList<>();
        List<String> check=new ArrayList<>();
        boolean flag=false;
        int count=0;
        do {
            if(trovato.size()!=0)
            {
                for(int i=1;i<provincias.size()-2;i++)
                {
                    count=1;
                    if(!(check.contains(provincias.get(i).getTerritorio())))
                    {
                        Stats obj=new Stats(provincias.get(i).getTerritorio());
                        for(int j=i+1;j<provincias.size()-1;j++)
                        {
                            if(obj.getField().equals(provincias.get(j).getTerritorio()))
                            {
                                count+=1;
                            }
                        }
                        obj.setCount(count);
                        check.add(obj.getField());
                        trovato.add(obj);
                    }
                }
                flag=true;
            }
            else
            {
                Stats obj=new Stats(provincias.get(0).getTerritorio());
                for(int i=0;i<provincias.size()-1;i++)
                {
                    if(obj.getField().equals(provincias.get(i).getTerritorio()))
                    {
                        count++;
                    }
                }
                obj.setCount(count);
                check.add(obj.getField());
                trovato.add(obj);
            }
        }while(flag==false);
        return trovato;
    }

   // END SERVICE
}
