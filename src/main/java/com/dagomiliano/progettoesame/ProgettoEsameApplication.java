package com.dagomiliano.progettoesame;

import com.dagomiliano.progettoesame.model.CsvParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProgettoEsameApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoEsameApplication.class, args);

		CsvParser p = new CsvParser("")
	}

}
