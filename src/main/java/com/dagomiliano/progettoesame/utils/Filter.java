package com.dagomiliano.progettoesame.utils;

import com.dagomiliano.progettoesame.model.ErossPaProvincia;
import com.dagomiliano.progettoesame.model.ErossPaProvinciaService;

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
        switch (this.filter) {
            case "$gt": return this.gr(field, param);
            case "$lt": return this.lt(field, param);
            case "$not": return this.not(field, param);
            case "$in" : return this.in(field, param);
            //case "$bt": return this.bt(field, param);

            default:
                return null;
        }
    }

//    private List<ErossPaProvincia> bt(String field, int[] param) {
//        try {
//            Method code = ErossPaProvincia.class.getMethod("get" + field.substring(0, 1).toUpperCase() + field.substring(1));
//            List<ErossPaProvincia> list = new ArrayList<>();
//            for (ErossPaProvincia obj : ErossPaProvinciaService.getDatas()) {
//                int temp = (int) code.invoke(obj);
//                if (temp > param[0] && temp < param[1]) list.add(obj);
//            }
//            return list;
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//            return null;
//        } catch (SecurityException e) {
//            e.printStackTrace();
//            return null;
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//            return null;
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public List<ErossPaProvincia> gr(String field, int param) {
        try {
            Method code = ErossPaProvincia.class.getMethod("get" + field.substring(0, 1).toUpperCase() + field.substring(1));
            List<ErossPaProvincia> list = new ArrayList<>();
            for (ErossPaProvincia obj : ErossPaProvinciaService.getDatas()) {
                if ((int) code.invoke(obj) > param) list.add(obj);
            }
            return list;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
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

    public List<ErossPaProvincia> lt(String field, int param) {
        try {
            Method code = ErossPaProvincia.class.getMethod("get" + field.substring(0, 1).toUpperCase() + field.substring(1));
            List<ErossPaProvincia> list = new ArrayList<>();
            for (ErossPaProvincia obj : ErossPaProvinciaService.getDatas()) {
                if ((int) code.invoke(obj) < param) list.add(obj);
            }
            return list;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
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

    public List<ErossPaProvincia> not(String field, int param) {
        try {
            Method code = ErossPaProvincia.class.getMethod("get" + field.substring(0, 1).toUpperCase() + field.substring(1));
            List<ErossPaProvincia> list = new ArrayList<>();
            for (ErossPaProvincia obj : ErossPaProvinciaService.getDatas()) {
                if ((int) code.invoke(obj) != param) list.add(obj);
            }
            return list;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
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

    public List<ErossPaProvincia> in(String field, int param) {
        try {
            Method code = ErossPaProvincia.class.getMethod("get" + field.substring(0, 1).toUpperCase() + field.substring(1));
            List<ErossPaProvincia> list = new ArrayList<>();
            for (ErossPaProvincia obj : ErossPaProvinciaService.getDatas()) {
                if ((int) code.invoke(obj) == param) list.add(obj);
            }
            return list;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
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