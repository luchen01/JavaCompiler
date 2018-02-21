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

}
