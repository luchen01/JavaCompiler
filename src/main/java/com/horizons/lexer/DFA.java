package com.horizons.lexer;

import java.util.ArrayList;

public class DFA {
    private ArrayList<ArrayList> parseString;
    private String stringToParse;
    public String currState;

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
                if(status == "comment"){
                    temp.append(c);
                } else if(status == "letter" || status == ""){
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
                        status = "";
                        i++;
                    }
                } else {
                    throw new RuntimeException("invalid input");
                }
            }

            //digits
            if(Character.isDigit(c)){
                if( status == "comment"){
                    temp.append(c);
                } else if(status == "digit" || status == "") {
                    temp.append(c);
                    status = "digit";
                    if(this.stringToParse.charAt(i+1) == ' '){
                        String newNum = temp.toString();
                        tokenize("NUM", newNum);
                        temp = new StringBuilder();
                        status = "";
                        i++;
                    }
                } else if (status == "letter"){
                    temp.append(c);
                    if(this.stringToParse.charAt(i+1) == ' '){
                        String newWord = temp.toString();
                        tokenize("ID", newWord);
                        temp = new StringBuilder();
                        status = "";
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
                if(this.stringToParse.charAt(i+1) == ' '){
                    tokenize("SLASH", "/");
                } else if (this.stringToParse.charAt(i+1) == '/'){
                    status = "comment";
                }
            }

            //newLine

            if( c == '\n'){
                if(status == "comment"){
                    String newComment = temp.toString();
                    tokenize("COMMENT", newComment);
                } else {
                    tokenize("NEWLINE", " \n");
                }
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
                    i++;
                }
            }

            //greater than <
            if(c == '>' && temp.length() == 0){
                if(this.stringToParse.charAt(i+1) == ' '){
                    tokenize("GT", ">");
                } else if (this.stringToParse.charAt(i+1) == '='){
                    tokenize("GE", ">=");
                    i++;
                }
            }

        }

    }

    public static void main(){

    }
}
