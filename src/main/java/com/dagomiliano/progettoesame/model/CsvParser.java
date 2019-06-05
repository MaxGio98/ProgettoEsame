package com.dagomiliano.progettoesame.model;


import org.springframework.boot.json.BasicJsonParser;

import java.lang.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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
                }
            } finally {
                in.close();
            }
            Map<String, Object> map = new BasicJsonParser().parseMap(data);
            map = (Map<String, Object>) map.get("result");
            map = (Map) ((List) map.get("resources")).get(0);
            String zipUrl = (String) map.get("url");
            in.close();
            return zipUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public File ZIPdownload(String inUrl, String ZIPname) {

        File newFile = new File(ZIPname);

        try {

            URL url = new URL(inUrl);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            double fileSize = (double) http.getContentLengthLong();
            BufferedInputStream in = new BufferedInputStream((http.getInputStream()));
            FileOutputStream fout = new FileOutputStream(newFile);
            BufferedOutputStream bout = new BufferedOutputStream(fout, 1024);
            byte[] buffer = new byte[1024];
            double downloaded = 0.00;
            int read = 0;
            double percentDownloaded = 0.00;
            while ((read = in.read(buffer, 0, 1024)) >= 0) {
                bout.write(buffer, 0, read);
                downloaded += read;
                percentDownloaded = (downloaded*100)/fileSize;
                String percent = String.format("%.4f", percentDownloaded);
                //System.out.println("Download: " + percent + "%");
            }
            bout.close();
            in.close();
            return newFile;

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public InputStream ZIPfinder(File zipFileIn, String searchFile) {

        try {
            ZipFile zipFile = new ZipFile(zipFileIn);
            ZipEntry entry = zipFile.getEntry(searchFile);
            InputStream input = zipFile.getInputStream(entry);

            String meta;
            String data="";

            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            meta = br.readLine();

            System.out.println("ciao");
            System.out.println(meta);

            String line;
            while((line = br.readLine()) != null) {
                data += line + "\n";

            }

//            for(data=br.readLine();)

            System.out.println(data);



            zipFile.close();

            System.out.println(entry.getName());

            return input;

        } catch(ZipException e) {
            e.printStackTrace();
            return null;
        } catch(IOException e) {
            e.printStackTrace();
            return null;

        }

    }



    public void CSVparse(InputStream input) {

        String meta;
        String data;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            meta = br.readLine();
            System.out.println("ciao");
            System.out.println(meta);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    //end class
}
