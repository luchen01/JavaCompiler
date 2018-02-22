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
    private ArrayList<Token> tokens;

    public Lexer(){
        this.parseString = new ArrayList<>();
        this.stringToParse = "";
        this.tokens = new ArrayList<>();
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

    private static ArrayList<Token> maximalMunchScan(String input) {
        DFA dfa = new DFA();
        State backtrackState = dfa.currentState;
        int backtrackI = 0;
        int tokenStart = 0;
        int failedI = 0;
        ArrayList<Token> tokens = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (dfa.transition(currentChar)) {
                if (dfa.currentStateAccepting()) {
                    backtrackState = dfa.currentState;
                    backtrackI = i;
                } else {
                    if (i == failedI) {
                        System.out.println("Could not tokenize whole input, stuck at " + dfa.currentState.toString());
                        System.exit(1);
                    } else {
                        failedI = i;
                    }
                }
            } else {
                tokens.add(new Token(backtrackState.toString(), input.substring(tokenStart, backtrackI + 1)));
                dfa.currentState = State.start;
                i = backtrackI;
                tokenStart = backtrackI + 1;
            }
        }
        if (dfa.currentStateAccepting()) {
            tokens.add(new Token(dfa.currentState.toString(), input.substring(tokenStart, backtrackI + 1)));
        } else {
            System.out.println("Could not tokenize whole input");
            System.exit(1);
        }
        return tokens;
    }
    private static void sanitize(ArrayList<Token> tokens) {
        for (Token token: tokens) {
            if (token.kind == State.zero.toString()) {
                token.kind = State.NUM.toString();
            } else if (DFA.alsoID.contains(token.kind)) {
                token.kind = State.ID.toString();
            }
        }
        for (int i = 0; i < tokens.size() - 1; i++) {
            Token first = tokens.get(i);
            Token second = tokens.get(i + 1);
            if (DFA.whitespaceBetween1.contains(first.kind) &&
                    DFA.whitespaceBetween1.contains(second.kind) ||
                    DFA.whitespaceBetween2.contains(first.kind) &&
                            DFA.whitespaceBetween2.contains(second.kind)) {
                System.out.println("Expected whitespace between " + first + " and " + second);
                System.exit(1);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        String input = readFile(args[0]);
        ArrayList<Token> tokens = maximalMunchScan(input);
        sanitize(tokens);
        for (Token token : tokens) {
            System.out.println(token.toString());
        }
    }




}

