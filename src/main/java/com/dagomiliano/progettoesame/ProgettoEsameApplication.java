package com.dagomiliano.progettoesame;

import com.dagomiliano.progettoesame.model.CsvParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

@SpringBootApplication
public class ProgettoEsameApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ProgettoEsameApplication.class, args);

		CsvParser p = new CsvParser();
		String url=p.JSONparse("https://www.dati.gov.it/api/3/action/package_show?id=42063df0-49ed-438a-91d4-fca8074166c4");
		File zipFile;
		zipFile = p.ZIPdownload(url, "EROSS_PA_PROVINCIA.zip");
		InputStream in = p.ZIPfinder(zipFile, "EROSS_PA/EROSS_PA_PROVINCIA.csv");
	}

}
