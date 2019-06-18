package com.dagomiliano.progettoesame.utils;

import com.dagomiliano.progettoesame.model.ErossPaProvincia;
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
    private List<ErossPaProvincia> lista=new ArrayList<>();

    /**
     * Stringa contenente il nome del file .zip da visitare
     */
    private String nomeZIP="EROSS_PA_PROVINCIA.zip";
    /**
     * Vettore di stringhe contenente i metadati derivati dal csv
     */
    private String[] metaData;
    //get del vettore di stringhe "metaData"
    public String[] getMetaData() {
        return this.metaData;
    }
    //get della lista di tipo ErossPaProvincia "lista"
    public List<ErossPaProvincia> getList() {
        return this.lista;
    }

    /**
     * Il metodo checkSER controlla che i file .ser, contenente gli Oggetti ottenuti dal parsing,
     * esistano all'interno della cartella principale del progetto.
     * In tal caso vengono caricati i dati di lista.ser in lista e di mata.ser in metaData.
     * Altrimenti l'applicazione si trova al suo primo avvio, per cui procede al download dei file e al parsing.
     */
    public void checkSER()
    {
        File findSer=new File("lista.ser");
        File findMetaSer=new File("meta.ser");
        //controllo se lista.ser e meta.ser esistono nel progetto
        if(findSer.exists()&&findMetaSer.exists())
        {
            try {
                FileInputStream fileIn = new FileInputStream("lista.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                //copia nella lista di tipo ErossPaProvincia di tutti gli oggetti contenuti nel file selezionato
                lista = (List<ErossPaProvincia>) in.readObject();
                //invocazione metodo per chiudere lo stream di ObjectInputStream
                in.close();
                //invocazione metodo per chiudere lo stream di FileInputStream
                fileIn.close();
                FileInputStream metaInFile=new FileInputStream("meta.ser");
                ObjectInputStream metaInObj=new ObjectInputStream(metaInFile);
                metaData=(String[]) metaInObj.readObject();
                //invocazione metodo per chiudere lo stream di ObjectInputStream
                metaInObj.close();
                //invocazione metodo per chiudere lo stream di FileInputStream
                metaInFile.close();
            } catch (IOException i) {
                i.printStackTrace();
                return;
            } catch (ClassNotFoundException c) {
                System.out.println("Non trovato");
                c.printStackTrace();
                return;
            }
        }
        //si entra nell'else nel caso in cui manchi almeno uno dei due file .ser
        else
        {
            //richiamo metodo parseJSON, passando come parametro l'URL del JSON contenente il dataset con valore di ritorno contenuto nella stringa url
            String url=parseJSON("https://www.dati.gov.it/api/3/action/package_show?id=42063df0-49ed-438a-91d4-fca8074166c4");
            /*richiamo metodo downloadZIP, passando la stringa url ottenuta precedentemente e del nome del file .zip di nostro interesse.
            Il metodo ritornerà il file interessato, che sarà a sua volta contenuto nella variabile zipFile di tipo file*/
            File zipFile = downloadZIP(url, this.nomeZIP);
            /*richiamo del metodo finderInZip, passando come parametro il file ottento in precedenza e il nome del file .csv
            di nostro interesse. Il risultato del metodo verrà salvato nella lista di liste di stringhe record*/
            List<List<String>> record = finderInZIP(zipFile, "EROSS_PA/EROSS_PA_PROVINCIA.csv");
            //richiamo del metodo parseCSV, passando come parametro record
            parseCSV(record);
        }
    }

    /**
     * Il metodo parseJSON si occupa del parsing del json ottenuto dall'url che lo contiene.
     * Il parsing del file è assegnato alla classe BasicJsonParser contenuta nelle librerie Spring.
     * La visita della struttura dati ottenuta dal parsing è svolta attraverso i metodi della classe Map.
     *
     * @param url   url contenente il file json da parsare
     * @return Stringa contenete l'url contenente il file .zip
     */
    public String parseJSON(String url) {
        try {
            //apertura connessione
            URLConnection openConnection = new URL(url).openConnection();
            //impostazione User-Agent
            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            //apertura stream di input
            InputStream in = openConnection.getInputStream();
            String data = "";
            String line;
            try {
                //apertura lettura dello stream di input
                InputStreamReader inR = new InputStreamReader(in);
                //dichiarazione buffer di lettura
                BufferedReader buf = new BufferedReader(inR);
                //copia del json in data
                while ((line = buf.readLine()) != null) {
                    data += line;
                }
            } finally {
                in.close();
            }
            //Navigazione nella stringa contenente il json per poter ottenere l'url del file .zip
            Map<String, Object> map = new BasicJsonParser().parseMap(data);
            map = (Map<String, Object>) map.get("result");
            map = (Map) ((List) map.get("resources")).get(0);
            //copia dell'url nella stringa zipUrl
            String zipUrl = (String) map.get("url");
            //chiusura stream
            in.close();
            //ritorno della stringa
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
     * Il metodo downloadZIP si occupa del download del file .zip a partire dall'url che lo contiene
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
            //scrittura in newFile
            while ((read = in.read(buffer, 0, 1024)) >= 0) {
                bout.write(buffer, 0, read);
            }
            //chiusura stream
            bout.close();
            in.close();
            return newFile;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Il metodo finderInZIP si occupa della visita del file .zip scaricato precedentemente
     * restituendo una lista contenente tutti i dati contenuti nel file .csv di interesse.
     *
     * @param zipFileIn     File .zip da visitare
     * @param searchFile    nome del File .csv di interesse
     * @return      Stringa contenente i dati contenuti nel file .csv
     */


    public List<List<String>> finderInZIP(File zipFileIn, String searchFile) {
        try {
            ZipFile zipFile = new ZipFile(zipFileIn);
            ZipEntry entry = zipFile.getEntry(searchFile);
            InputStream input = zipFile.getInputStream(entry);
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            List<List<String>> records = new ArrayList<>();
            String line;
            //nel while viene copiato il contenuto del file .csv una riga alla volta, fino a termine documento
            while ((line = br.readLine()) != null) {
                /*si divide la riga con il metodo split(), passando come parametro il separatore del nostro file .csv (;),
                * salvando il risultato in in array di stringhe (values)*/
                String[] values = line.split(";");
                //viene aggiunto l'array alla lista di liste di stringhe records
                records.add(Arrays.asList(values));
            }
            /*viene avviato un ciclo for che inizia dalla seconda riga, poiché nel csv nella prima riga
            * sono presente i metadati e non i valori. Il ciclo finisce con l'ultimo elemento presente in lista*/
            for(int i=1;i<records.size();i++)
            {
                //viene avviato un secondo ciclo for per ciclare tutte le colonne
                for(int j=0;j<records.get(i).size();j++)
                {
                    //se la stringa termina con il %
                    if(records.get(i).get(j).endsWith("%"))
                    {
                        //% viene rimosso
                        records.get(i).set(j,records.get(i).get(j).replace("%",""));

                    }
                    //se la stringa presenta una virgola
                    if(records.get(i).get(j).contains(","))
                    {
                        /*viene cambiata la "," con un ".". Ciò è stato fatto poichè quando si vuole inserire un
                        * dato di tipo Double, egli dovrà presentare (se il dato da inserire presenta numeri decimali)
                        * il simbolo ".", altrimenti il programma genererebbe errore*/
                        records.get(i).set(j,records.get(i).get(j).replace(",","."));
                    }
                }
            }
            //chiusura stream
            zipFile.close();
            return records;
        } catch(ZipException e) {
            e.printStackTrace();
            return null;
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Il metodo parseCSV si occupa del parsing dei dati contenuti in una lista (ottenuti dal csv di interesse), della
     * creazione casuale di 90 elementi, come richiesto dal prof. Mancini, poiché il dataset a noi assegnato contiene
     * solo 10 osservazioni.
     * Inoltre il metodo parseCSV si occupa della creazione del file .ser contenente gli oggetti ,ottenuti dal parsing
     * e dalla generazione randomica.
     * Dopo aver generato il file .ser il metodo effettua la cancellazione dalla memoria il file .zip di partenza.
     *
     * @param records      Lista di lista di stringhe contenente i dati di partenza per la creazione degli oggetti
     *                     che modellano i dati di interesse.
     */

    public void parseCSV(List<List<String>> records) {
        //dichiarazione vettore di stringhe meta, di dimensione massima pari al numero di colonne della lista records
        String[] meta = new String[records.get(0).size()];
        for(int i=0;i<records.get(0).size()-1;i++) {
            //copia dei dati nel vettore di stringhe, elemento per elemento
            meta[i] = records.get(0).get(i);
        }
        //rimozione dalla lista di liste di stringhe record dei metadati, ormai contenuti nel vettore di stringhe meta
        records.remove(0);
        //copia in metaData del contenuto di meta
        this.metaData = meta;
        //copia della lista di liste di stringhe records in una lista di tipo ErossPaProvincia, elemento per elemento
        for(int i=0;i<records.size()-1;i++)
        {
                ErossPaProvincia ePP=new ErossPaProvincia(Integer.parseInt(records.get(i).get(0)),records.get(i).get(1),Integer.parseInt(records.get(i).get(2)),Integer.parseInt(records.get(i).get(3)),Integer.parseInt(records.get(i).get(4)),Integer.parseInt(records.get(i).get(5)),Integer.parseInt(records.get(i).get(6)),Integer.parseInt(records.get(i).get(7)),Integer.parseInt(records.get(i).get(8)),Integer.parseInt(records.get(i).get(9)),Integer.parseInt(records.get(i).get(10)),Integer.parseInt(records.get(i).get(11)),Integer.parseInt(records.get(i).get(12)),Integer.parseInt(records.get(i).get(13)),Integer.parseInt(records.get(i).get(14)),Integer.parseInt(records.get(i).get(15)),Integer.parseInt(records.get(i).get(16)),Integer.parseInt(records.get(i).get(17)),Integer.parseInt(records.get(i).get(18)),Integer.parseInt(records.get(i).get(19)),Integer.parseInt(records.get(i).get(20)),Double.parseDouble(records.get(i).get(21)),Integer.parseInt(records.get(i).get(22).trim()));
                lista.add(ePP);
        }
        //copia del valore dell'ultimo id presente in lista
        int lastNotRandomId=lista.get(lista.size()-1).getIdTerritorio();
        Random r=new Random();
        //generazione e aggiunta in "lista" di 90 elementi generati randomicamente, come richiesto dal prof. Mancini
        for(int i=1;i<=91;i++)
        {
            String randomPro= "terr.prov. di "+(char)(r.nextInt(90-65)+65)+(char)(r.nextInt(90-65)+65);
            ErossPaProvincia ePP=new ErossPaProvincia(lastNotRandomId+i,randomPro,2008,r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),Math.floor((r.nextDouble()*(500))*Math.pow(10,7))/Math.pow(10,7),r.nextInt(10));
            lista.add(ePP);
        }
        try {
            FileOutputStream fileOut = new FileOutputStream("lista.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            //scrittura in lista.ser di "lista"
            out.writeObject(lista);
            out.close();
            fileOut.close();
            FileOutputStream fileOutMeta=new FileOutputStream("meta.ser");
            ObjectOutputStream outMeta=new ObjectOutputStream(fileOutMeta);
            //scrittura in meta.ser di "metaData"
            outMeta.writeObject(this.metaData);
            outMeta.close();
            fileOutMeta.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
        File zipFile=new File(this.nomeZIP);
        //eliminazione del file .zip
        zipFile.delete();
    }
    //end class
}
