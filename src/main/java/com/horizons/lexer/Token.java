package com.horizons.lexer;

public class Token {
    public String kind;
    public String lexeme;
    public Token(String kind, String lexeme) {
        this.kind = kind;
        this.lexeme = lexeme;
    }

}
