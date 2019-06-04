package com.dagomiliano.progettoesame.model;


import org.springframework.boot.json.BasicJsonParser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


/**
 *Classe utilizzata per effettuare il parsing remoto dei dati
 */

public class CsvParser {


    public String JSONparse(String Url) {

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
            //String format = (String) map.get("format");

            //System.out.println(zipUrl);
            //System.out.println(format);
            return zipUrl;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void ZIPfinder(String url, String name)
    {

        try {
            URL urlOBJ=new URL(url);
            File zipFile = new File(url);
            ZipFile zip = new ZipFile(zipFile);
            InputStream in = new BufferedInputStream(urlOBJ.openStream(), 1024);
            ZipInputStream zis = new ZipInputStream(in);
            ZipEntry entry;
            System.out.println("ciao");
        }
        catch(MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch(ZipException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }



    }

    //end class
}
