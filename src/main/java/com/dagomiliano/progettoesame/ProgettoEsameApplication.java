package com.dagomiliano.progettoesame;

import com.dagomiliano.progettoesame.model.CsvParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;

@SpringBootApplication
public class ProgettoEsameApplication {

	public static void main(String[] args) throws MalformedURLException {
		SpringApplication.run(ProgettoEsameApplication.class, args);

		CsvParser p = new CsvParser("http://dati.emilia-romagna.it/dataset/42063df0-49ed-438a-91d4-fca8074166c4/resource/f1c93219-63fa-4106-b397-987cc96db8cf/download/erosspa.zip");
		p.Parse();

	}

}
