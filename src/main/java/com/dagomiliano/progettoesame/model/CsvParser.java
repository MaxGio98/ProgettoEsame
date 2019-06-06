package com.dagomiliano.progettoesame.model;

import org.springframework.boot.json.BasicJsonParser;
import java.lang.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 *Classe utilizzata per effettuare il parsing remoto dei dati
 */

public class CsvParser {

    private List<erossPaProvincia> lista=new ArrayList<>();
    
    private String nomeZIP="EROSS_PA_PROVINCIA.zip";
    
    public void checkSER()
    {
        String data;

        File findSer=new File("lista.ser");
        if(findSer.exists())
        {
            try {
                FileInputStream fileIn = new FileInputStream("lista.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                lista = (List<erossPaProvincia>) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
                return;
            } catch (ClassNotFoundException c) {
                System.out.println("Non trovato");
                c.printStackTrace();
                return;
            }
        }
        else
        {
            String url=parseJSON("https://www.dati.gov.it/api/3/action/package_show?id=42063df0-49ed-438a-91d4-fca8074166c4");
            File zipFile = downloadZIP(url, this.nomeZIP);
            data = finderInZIP(zipFile, "EROSS_PA/EROSS_PA_PROVINCIA.csv");
            parseCSV(data);
        }
    }

    public String parseJSON(String Url) {

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

    public File downloadZIP(String inUrl, String ZIPname) {
        File newFile = new File(ZIPname);
        try {

            URL url = new URL(inUrl);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            BufferedInputStream in = new BufferedInputStream((http.getInputStream()));
            FileOutputStream fout = new FileOutputStream(newFile);
            BufferedOutputStream bout = new BufferedOutputStream(fout, 1024);
            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = in.read(buffer, 0, 1024)) >= 0) {
                bout.write(buffer, 0, read);
            }
            bout.close();
            in.close();
            return newFile;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String finderInZIP(File zipFileIn, String searchFile) {
        try {
            ZipFile zipFile = new ZipFile(zipFileIn);
            ZipEntry entry = zipFile.getEntry(searchFile);
            InputStream input = zipFile.getInputStream(entry);
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String data="";
            String line;
            while((line = br.readLine()) != null) {
                data += line + ";" +"\n";
            }
            zipFile.close();
            return data;
        } catch(ZipException e) {
            e.printStackTrace();
            return null;
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void parseCSV(String dati) {
        String meta, data="";
        Scanner scanner = new Scanner(dati);
        scanner.useDelimiter("\n");
        meta = scanner.next();
        while(scanner.hasNext()) {
            data += scanner.next();
        }
        data = data.replaceAll("%", "");
        data=data.replaceAll(",",".");
        data = data.replace("2.008","2008");
        String[] metaSplitted = meta.split(";");
        String[] dataSplitted = data.split(";");
        for(int i=0; i<dataSplitted.length;i+=23)
        {
            erossPaProvincia ePP=new erossPaProvincia(Integer.parseInt(dataSplitted[i]),dataSplitted[i+1],Integer.parseInt(dataSplitted[i+2]),Float.parseFloat(dataSplitted[i+3]),Float.parseFloat(dataSplitted[i+4]),Float.parseFloat(dataSplitted[i+5]),Float.parseFloat(dataSplitted[i+6]),Float.parseFloat(dataSplitted[i+7]),Float.parseFloat(dataSplitted[i+8]),Float.parseFloat(dataSplitted[i+9]),Float.parseFloat(dataSplitted[i+10]),Float.parseFloat(dataSplitted[i+11]),Float.parseFloat(dataSplitted[i+12]),Float.parseFloat(dataSplitted[i+13]),Float.parseFloat(dataSplitted[i+14]),Float.parseFloat(dataSplitted[i+15]),Float.parseFloat(dataSplitted[i+16]),Float.parseFloat(dataSplitted[i+17]),Float.parseFloat(dataSplitted[i+18]),Float.parseFloat(dataSplitted[i+19]),Float.parseFloat(dataSplitted[i+20]),Float.parseFloat(dataSplitted[i+21]),Float.parseFloat(dataSplitted[i+22]));
            lista.add(ePP);
        }
        try {
            FileOutputStream fileOut = new FileOutputStream("lista.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(lista);
            out.close();
            fileOut.close();

        } catch (IOException i) {
            i.printStackTrace();
        }
        File zipFile=new File(this.nomeZIP);
        zipFile.delete();
    }
    //end class
}
