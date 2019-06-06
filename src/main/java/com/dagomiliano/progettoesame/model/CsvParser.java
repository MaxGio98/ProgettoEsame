package com.dagomiliano.progettoesame.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
 * Classe per la gestione dei dati che vengono ricevuti al primo avvio partendo dal file json assegnato:
 *
 * dal json ci si riconduce all'url contenente il file .zip a sua volta contenente il file .csv d' interesse.
 */

public class CsvParser {

    /**
     * Lista contenete gli oggetti ottenuti dal parsing del file .csv
     */
    private List<erossPaProvincia> lista=new ArrayList<>();

    /**
     * Stringa contenente il nome del file .zip da visitare
     */
    private String nomeZIP="EROSS_PA_PROVINCIA.zip";
    /**
     * Stringa contenente i metadati derivati dal csv
     */
    private String metaData;
    /**
     * Stringa contenente i dati derivati dal csv
     */
    private String data;

    // END PARAMETERS

    public String getData() {
        return this.data;
    }

    public String getMetaData() {
        return this.metaData;
    }

    public List<erossPaProvincia> getList() {
        return this.lista;
    }

    /**
     * Il metodo checkSER controlla che il file .ser contenente gli Oggetti ottenuti dal parsing
     * esista all'interno della cartella principale del progetto.
     * In tal caso carica gli oggetti contenuti nel file .ser all'interno della lista.
     * Altrimenti l'applicazione si trova al suo primo avvio per cui procede al download dei file e al parsing.
     */
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

    /**
     * Il metodo parseJson si occupa del parsing del file json dall'url che lo contiene.
     * Il parsing del file è assegnato alla classe BasicJsonParser contenuta nelle librerie Spring.
     * La visita della struttura dati ottenuta dal parsing è svolta attraverso i metodi della calsse Map.
     *
     * @param Url   url contenente il file json da parsare
     * @return Stringa contenete l'url contenente il file .zip
     */
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

    /**
     * Il metodo downloadZip si occupa del download del file .zip a partire dall'url che lo contiene
     *
     * @param inUrl     url contenente il file .zip
     * @param ZIPname   nome con cui il file scaricato verrà salvato in memoria
     * @return  File di estensione .zip scaricato dall'url dato in ingresso
     */

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

    /**
     * Il metodo finderInZip si occupa della visita del file .zip scaricato precedentemente
     * restituendo una Stringa contenente tutti i dati contenuti nel file .csv di interesse.
     *
     * @param zipFileIn     File .zip da visitare
     * @param searchFile    nome del File .csv di interesse
     * @return      Stringa contenente i dati contenuti nel file .csv
     */
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

    /**
     * Il metodo parseCSV si occupa del parsing dei dati contenuti in unsa stringa (ottenuti dal csv di interesse),
     * della creazione del file .ser contenente gli oggetti ,ottenuti dal parsing, e della cancellazione dalla memoria
     * del file .zip di partenza
     *
     * @param dati      Stringa contenente i dati di partenza per la creazione degli oggetti che modellano
     *                  i dati di interesse.
     */

    public void parseCSV(String dati) {
        String meta, data="";
        Scanner scanner = new Scanner(dati);
        scanner.useDelimiter("\n");
        meta = scanner.next();
        this.metaData = meta;
        while(scanner.hasNext()) {
            data += scanner.next();
        }
        this.data = data;
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
