package com.horizons.lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Lexer {
    private ArrayList<ArrayList> parseString;
    private String stringToParse;

    public Lexer(){
        this.parseString = new ArrayList<>();
        this.stringToParse = "";
    }

    public static String readFile(String file) throws IOException {
        List<String> lines = readFileLineByLine(file);
        StringBuilder sb = new StringBuilder();
        for (String l : lines) {
            sb.append(l).append("\n");
        }
        return sb.toString().trim();
    }

    public static ArrayList<String> readFileLineByLine(String file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            ArrayList<String> linesList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                linesList.add(line);
            }
            return linesList;
        }
    }

    public void parseFile() throws IOException{
        String file = "src/main/java/data/Poem.txt";
        File fileObj = new File(file);
        ArrayList<String> lineList = readFileLineByLine(fileObj.getAbsolutePath());
//        this.parseString = lineList;
        System.out.println(parseString);
    }

    static Scanner scanner = new Scanner(System.in);

    static String promptForInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void readInputFromConsole() {
        // Example usage of promptForInput();
        this.stringToParse = promptForInput("Please enter a string to parse:");

    }






}

