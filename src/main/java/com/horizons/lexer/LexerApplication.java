package com.horizons.lexer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LexerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LexerApplication.class, args);
	}
    public static String readFile(String file) throws IOException {
        List<String> lines = readFileLineByLine(file);
        StringBuilder sb = new StringBuilder();
        for (String l : lines) {
            sb.append(l).append("\n");
        }
        return sb.toString().trim();
    }

    public static List<String> readFileLineByLine(String file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<String> linesList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                linesList.add(line);
            }
            return linesList;
        }
    }
}
