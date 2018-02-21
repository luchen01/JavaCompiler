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

    public void tokenize(String tokenName, String input){
        ArrayList newToken = new ArrayList<String>();
        newToken.add(tokenName);
        newToken.add(input);
        this.parseString.add(newToken);
    }


    public void parser() throws RuntimeException{
        StringBuilder temp = new StringBuilder();
        String status = "";

        for (int i = 0, n = this.stringToParse.length(); i < n; i++) {
            char c = this.stringToParse.charAt(i);

            //letter
            if(Character.isLetter(c)){
                if(status == "letter" || status == ""){
                    temp.append(c);
                    status = "letter";
                    if(this.stringToParse.charAt(i+1) == ' '){
                        String newWord = temp.toString();
                        if(newWord == "if"){
                            tokenize("IF", "if");
                        } else if (newWord == "else"){
                            tokenize("ELSE", "else");
                        } else if (newWord == "int"){
                            tokenize("INT", "int");
                        } else if (newWord == "while"){
                            tokenize("WHILE", "while");
                        } else if (newWord == "return"){
                            tokenize("RETURN", "return");
                        } else {
                            tokenize("ID", newWord);
                        };
                        temp = new StringBuilder();
                        i++;
                    }
                } else {
                    throw new RuntimeException("invalid input");
                }
            }

            //digits
            if(Character.isDigit(c)){
                if(status == "digit" || status == ""){
                    temp.append(c);
                    status = "digit";
                    if(this.stringToParse.charAt(i+1) == ' '){
                        String newNum = temp.toString();
                        tokenize("ID", newNum);
                        temp = new StringBuilder();
                        i++;
                    }
                }else {
                    throw new RuntimeException("invalid input");
                }
            }

            //Left Paren
            if(c == '('){
                tokenize("LPAREN", "(");
            }

            //Right Paren
            if(c == ')'){
                tokenize("RPAREN", ")");
            }

            //Left Brace
            if(c == '{'){
                tokenize("LBRACE", "{");
            }

            //Right Brace
            if(c == '}'){
                tokenize("RBRACE", "}");
            }

            //plus
            if(c == '+'){
                tokenize("PLUS", "+");
            }

            //minus
            if(c == '-'){
                tokenize("MINUS", "-");
            }

            //star
            if(c == '*'){
                tokenize("STAR", "*");
            }

            //slash
            if(c == '/'){
                tokenize("SLASH", "/");
            }

            //PCT
            if(c == '%'){
                tokenize("PCT", "%");
            }

            //comma
            if(c == ','){
                tokenize("COMMA", ",");
            }

            //semi
            if(c == ';'){
                tokenize("SEMI", ";");
            }


            //becomes =, EQ
            if(c == '='){
                if(this.stringToParse.charAt(i+1) == ' '){
                    tokenize("becomes", "=");
                } else if(this.stringToParse.charAt(i+1) == '='){
                    tokenize("EQ", "==");
                    i++;
                }
            }

            //less than <
            if(c == '<' && temp.length() == 0){
                if(this.stringToParse.charAt(i+1) == ' '){
                    tokenize("LT", "<");
                } else if (this.stringToParse.charAt(i+1) == '='){
                    tokenize("LE", "<=");
                }
            }

            //greater than <
            if(c == '>' && temp.length() == 0){
                if(this.stringToParse.charAt(i+1) == ' '){
                    tokenize("GT", ">");
                } else if (this.stringToParse.charAt(i+1) == '='){
                    tokenize("GE", ">=");
                }
            }





        }

    }

}

