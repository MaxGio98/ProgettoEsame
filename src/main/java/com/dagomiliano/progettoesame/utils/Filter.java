package com.dagomiliano.progettoesame.utils;

import com.dagomiliano.progettoesame.model.ErossPaProvincia;
import com.dagomiliano.progettoesame.model.ErossPaProvinciaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Filter {

    private String filter;

    public Filter(String filter) {
        this.filter = filter;
    }

    public List<ErossPaProvincia> filtering(String field, int param) {
        try {
            Method code = ErossPaProvincia.class.getMethod("get" + field.substring(0, 1).toUpperCase() + field.substring(1));
            if(!((code.getReturnType()==Integer.TYPE)||(code.getReturnType()==Double.TYPE)))
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Errore! Non posso effettuare questa operazione perchè "+field+" non è un dato di tipo Double o Int, ma di tipo "+code.getReturnType().getSimpleName()+"!");
            }
            switch (this.filter) {
                case "$gt": return this.gr(param,code);
                case "$lt": return this.lt(param,code);
                case "$not": return this.not(param,code);
                case "$in" : return this.in(param,code);
                default:
                    return null;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Errore nei dati inseriti! "+field+" non esiste!");
        }
    }

    public List<ErossPaProvincia> gr(int param,Method code) {
        try {
            List<ErossPaProvincia> list = new ArrayList<>();
            for (ErossPaProvincia obj : ErossPaProvinciaService.getDatas()) {
                if (((Number) code.invoke(obj)).doubleValue() > param) list.add(obj);
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

    public List<ErossPaProvincia> lt(int param,Method code) {
        try {
            List<ErossPaProvincia> list = new ArrayList<>();
            for (ErossPaProvincia obj : ErossPaProvinciaService.getDatas()) {
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

    public List<ErossPaProvincia> not(int param,Method code) {
        try {
            List<ErossPaProvincia> list = new ArrayList<>();
            for (ErossPaProvincia obj : ErossPaProvinciaService.getDatas()) {
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

    public List<ErossPaProvincia> in(int param,Method code) {
        try {
            List<ErossPaProvincia> list = new ArrayList<>();
            for (ErossPaProvincia obj : ErossPaProvinciaService.getDatas()) {
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