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
		p.checkSER();
	}

}
