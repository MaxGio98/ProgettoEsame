package com.dagomiliano.progettoesame;

import com.dagomiliano.progettoesame.model.CsvParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;
import java.net.URL;

@SpringBootApplication
public class ProgettoEsameApplication {

	public static void main(String[] args) throws MalformedURLException {
		SpringApplication.run(ProgettoEsameApplication.class, args);

		CsvParser p = new CsvParser();
		String url=p.JSONparse("https://www.dati.gov.it/api/3/action/package_show?id=42063df0-49ed-438a-91d4-fca8074166c4");
		p.ZIPfinder(url, "EROSS_PA/EROSS_PA_PROVINCIA.csv");

	}

}
