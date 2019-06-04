package com.dagomiliano.progettoesame.model;

import net.minidev.json.JSONValue;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.json.BasicJsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;


/**
 *Classe utilizzata per effettuare il parsing remoto dei dati
 */

public class CsvParser {


    public void parse(String Url) {

        String url = Url;
        try {

            URLConnection openConnection = new URL(url).openConnection();
            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            InputStream in = openConnection.getInputStream();

            String data = "";
            String line;

            try {
                InputStreamReader inR = new InputStreamReader(in);
                BufferedReader buf = new BufferedReader(inR);

                while ((line = buf.readLine()) != null) {
                    data += line;
                    //System.out.println( line );
                }
            } finally {
                in.close();
            }

            Map<String, Object> map = new BasicJsonParser().parseMap(data);

            map = (Map<String, Object>) map.get("result");
            map = (Map) ((List) map.get("resources")).get(0);
            String zipUrl = (String) map.get("url");
            String format = (String) map.get("format");

            System.out.println(zipUrl);
            System.out.println(format);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //end class
}
