package com.dagomiliano.progettoesame.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 *Classe utilizzata per effettuare il parsing remoto dei dati
 */

public class CsvParser{

    private String ftpUrl;
    URL url;

    CsvParser(String url) {
        this.ftpUrl = url;
    }

    public void Parse() throws MalformedURLException {
        try {
            this.url = new URL(this.ftpUrl);
            //File zipFile = new File(this.url.toString());
            //ZipFile zip = new ZipFile(zipFile);
            InputStream in = this.url.openStream();
            ZipInputStream zis = new ZipInputStream(in);
            ZipEntry entry = zis.getNextEntry();
            while (entry != null) {
                System.out.println(entry.getName());
                entry = zis.getNextEntry();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}
