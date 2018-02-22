package com.horizons.lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;

//public class DFA {
//    private ArrayList<ArrayList> parseString;
//    private String stringToParse;
//    public String currState;
//
//    public void tokenize(String tokenName, String input){
//        ArrayList newToken = new ArrayList<String>();
//        newToken.add(tokenName);
//        newToken.add(input);
//        this.parseString.add(newToken);
//    }
//
//    public void parser() throws RuntimeException{
//        StringBuilder temp = new StringBuilder();
//        String status = "";
//
//        for (int i = 0, n = this.stringToParse.length(); i < n; i++) {
//            char c = this.stringToParse.charAt(i);
//
//            //letter
//            if(Character.isLetter(c)){
//                if(status == "comment"){
//                    temp.append(c);
//                } else if(status == "letter" || status == ""){
//                    temp.append(c);
//                    status = "letter";
//                    if(this.stringToParse.charAt(i+1) == ' '){
//                        String newWord = temp.toString();
//                        if(newWord == "if"){
//                            tokenize("IF", "if");
//                        } else if (newWord == "else"){
//                            tokenize("ELSE", "else");
//                        } else if (newWord == "int"){
//                            tokenize("INT", "int");
//                        } else if (newWord == "while"){
//                            tokenize("WHILE", "while");
//                        } else if (newWord == "return"){
//                            tokenize("RETURN", "return");
//                        } else {
//                            tokenize("ID", newWord);
//                        };
//                        temp = new StringBuilder();
//                        status = "";
//                        i++;
//                    }
//                } else {
//                    throw new RuntimeException("invalid input");
//                }
//            }
//
//            //digits
//            if(Character.isDigit(c)){
//                if( status == "comment"){
//                    temp.append(c);
//                } else if(status == "digit" || status == "") {
//                    temp.append(c);
//                    status = "digit";
//                    if(this.stringToParse.charAt(i+1) == ' '){
//                        String newNum = temp.toString();
//                        tokenize("NUM", newNum);
//                        temp = new StringBuilder();
//                        status = "";
//                        i++;
//                    }
//                } else if (status == "letter"){
//                    temp.append(c);
//                    if(this.stringToParse.charAt(i+1) == ' '){
//                        String newWord = temp.toString();
//                        tokenize("ID", newWord);
//                        temp = new StringBuilder();
//                        status = "";
//                        i++;
//                    }
//                }else {
//                    throw new RuntimeException("invalid input");
//                }
//            }
//
//            //Left Paren
//            if(c == '('){
//                tokenize("LPAREN", "(");
//            }
//
//            //Right Paren
//            if(c == ')'){
//                tokenize("RPAREN", ")");
//            }
//
//            //Left Brace
//            if(c == '{'){
//                tokenize("LBRACE", "{");
//            }
//
//            //Right Brace
//            if(c == '}'){
//                tokenize("RBRACE", "}");
//            }
//
//            //plus
//            if(c == '+'){
//                tokenize("PLUS", "+");
//            }
//
//            //minus
//            if(c == '-'){
//                tokenize("MINUS", "-");
//            }
//
//            //star
//            if(c == '*'){
//                tokenize("STAR", "*");
//            }
//
//            //slash
//            if(c == '/'){
//                if(this.stringToParse.charAt(i+1) == ' '){
//                    tokenize("SLASH", "/");
//                } else if (this.stringToParse.charAt(i+1) == '/'){
//                    status = "comment";
//                }
//            }
//
//            //newLine
//
//            if( c == '\n'){
//                if(status == "comment"){
//                    String newComment = temp.toString();
//                    tokenize("COMMENT", newComment);
//                } else {
//                    tokenize("NEWLINE", " \n");
//                }
//            }
//
//            //PCT
//            if(c == '%'){
//                tokenize("PCT", "%");
//            }
//
//            //comma
//            if(c == ','){
//                tokenize("COMMA", ",");
//            }
//
//            //semi
//            if(c == ';'){
//                tokenize("SEMI", ";");
//            }
//
//
//            //becomes =, EQ
//            if(c == '='){
//                if(this.stringToParse.charAt(i+1) == ' '){
//                    tokenize("becomes", "=");
//                } else if(this.stringToParse.charAt(i+1) == '='){
//                    tokenize("EQ", "==");
//                    i++;
//                }
//            }
//
//            //less than <
//            if(c == '<' && temp.length() == 0){
//                if(this.stringToParse.charAt(i+1) == ' '){
//                    tokenize("LT", "<");
//                } else if (this.stringToParse.charAt(i+1) == '='){
//                    tokenize("LE", "<=");
//                    i++;
//                }
//            }
//
//            //greater than <
//            if(c == '>' && temp.length() == 0){
//                if(this.stringToParse.charAt(i+1) == ' '){
//                    tokenize("GT", ">");
//                } else if (this.stringToParse.charAt(i+1) == '='){
//                    tokenize("GE", ">=");
//                    i++;
//                }
//            }
//
//        }
//
//    }
//}

enum State {
    start,
    LPAREN,
    RPAREN,
    LBRACE,
    RBRACE,
    BECOMES,
    LT,
    GT,
    bang,
    PLUS,
    MINUS,
    STAR,
    SLASH,
    PCT,
    COMMA,
    SEMI,
    SPACE,
    TAB,
    NEWLINE,
    EQ,
    NE,
    LE,
    GE,
    i,
    in,
    INT,
    IF,
    e,
    el,
    els,
    ELSE,
    w,
    wh,
    whi,
    whil,
    WHILE,
    r,
    re,
    ret,
    retu,
    retur,
    RETURN,
    zero,
    NUM,
    ID,
    inComment,
    COMMENT,
}

public class DFA {
    private static final Character[] alphaValues = new Character[]{
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            '0','1','2','3','4','5','6','7','8','9','0','(',')','{','}','=','<','>','!','+','-','*','/',';',',','%',' ','\t','\n'
    };
    private static final HashSet<Character> alphabet = new HashSet<>(Arrays.asList(alphaValues));
    private static final EnumSet<State> accepting = EnumSet.of(
            State.BECOMES,State.LT,State.GT,State.PLUS,State.MINUS,State.STAR,State.SLASH,State.PCT, State.COMMA,State.SEMI,State.SPACE,
            State.TAB,State.NEWLINE,State.LPAREN,State.RPAREN,State.LBRACE,State.RBRACE,State.EQ,State.NE,State.LE, State.GE,State.i,
            State.in,State.INT,State.IF,State.e,State.el,State.els,State.ELSE,State.w,State.wh, State.whi,State.whil,State.WHILE,
            State.r,State.re,State.ret,State.retu,State.retur,State.RETURN,State.NUM,State.zero,State.ID,State.COMMENT
    );
    public static final EnumSet<State> alsoID = EnumSet.of(
            State.i,State.in,State.e,State.el,State.els,State.w,State.wh,State.whi,State.whil,State.r,State.re,State.ret,State.retu,State.retur
    );
    public static final EnumSet<State> whitespaceBetween1 = EnumSet.of(
            State.ID,State.NUM,State.RETURN,State.IF,State.ELSE,State.WHILE,State.INT
    );
    public static final EnumSet<State> whitespaceBetween2 = EnumSet.of(
            State.EQ,State.NE,State.LT,State.LE,State.GT,State.GE,State.BECOMES
    );
    public State currentState = State.start;

    public boolean transition(char nextChar) {
        if (!alphabet.contains(nextChar)) {
            return false;
        }
        switch (currentState) {
            case start:
                switch (nextChar) {
                    case '(':
                        currentState = State.LPAREN;
                        return true;
                    case ')':
                        currentState = State.RPAREN;
                        return true;
                    case '{':
                        currentState = State.LBRACE;
                        return true;
                    case '}':
                        currentState = State.RBRACE;
                        return true;
                    case '=':
                        currentState = State.BECOMES;
                        return true;
                    case '<':
                        currentState = State.LT;
                        return true;
                    case '>':
                        currentState = State.LT;
                        return true;
                    case '!':
                        currentState = State.bang;
                        return true;
                    case '+':
                        currentState = State.PLUS;
                        return true;
                    case '-':
                        currentState = State.MINUS;
                        return true;
                    case '*':
                        currentState = State.STAR;
                        return true;
                    case '/':
                        currentState = State.SLASH;
                        return true;
                    case ';':
                        currentState = State.SEMI;
                        return true;
                    case ',':
                        currentState = State.COMMA;
                        return true;
                    case '%':
                        currentState = State.PCT;
                        return true;
                    case ' ':
                        currentState = State.SPACE;
                        return true;
                    case '\t':
                        currentState = State.TAB;
                        return true;
                    case '\n':
                        currentState = State.NEWLINE;
                        return true;
                    case 'i':
                        currentState = State.i;
                        return true;
                    case 'e':
                        currentState = State.e;
                        return true;
                    case 'w':
                        currentState = State.w;
                        return true;
                    case 'r':
                        currentState = State.r;
                        return true;
                    case '0':
                        currentState = State.zero;
                        return true;
                    default:
                        if (Character.isDigit(nextChar)) {
                            currentState = State.NUM;
                            return true;
                        } else if (Character.isLetter(nextChar)) {
                            currentState = State.ID;
                            return true;
                        }
                        return false;
                }
            case BECOMES:
                switch (nextChar) {
                    case '=':
                        currentState = State.EQ;
                        return true;
                    default:
                        return false;
                }
            case bang:
                switch (nextChar) {
                    case '=':
                        currentState = State.NE;
                        return true;
                    default:
                        return false;
                }
            case LT:
                switch (nextChar) {
                    case '=':
                        currentState = State.LE;
                        return true;
                    default:
                        return false;
                }
            case GT:
                switch (nextChar) {
                    case '=':
                        currentState = State.GE;
                        return true;
                    default:
                        return false;
                }
            case i:
                switch (nextChar) {
                    case 'f':
                        currentState = State.IF;
                        return true;
                    case 'n':
                        currentState = State.in;
                        return true;
                    default:
                        if (Character.isLetterOrDigit(nextChar)) {
                            currentState = State.ID;
                            return true;
                        }
                        return false;
                }
            case IF:
                if (Character.isLetterOrDigit(nextChar)) {
                    currentState = State.ID;
                    return true;
                }
                return false;
            case in:
                switch (nextChar) {
                    case 't':
                        currentState = State.INT;
                        return true;
                    default:
                        if (Character.isLetterOrDigit(nextChar)) {
                            currentState = State.ID;
                            return true;
                        }
                        return false;
                }
            case INT:
                if (Character.isLetterOrDigit(nextChar)) {
                    currentState = State.ID;
                    return true;
                }
                return false;
            case e:
                switch (nextChar) {
                    case 'l':
                        currentState = State.el;
                        return true;
                    default:
                        if (Character.isLetterOrDigit(nextChar)) {
                            currentState = State.ID;
                            return true;
                        }
                        return false;
                }
            case el:
                switch (nextChar) {
                    case 's':
                        currentState = State.els;
                        return true;
                    default:
                        if (Character.isLetterOrDigit(nextChar)) {
                            currentState = State.ID;
                            return true;
                        }
                        return false;
                }
            case els:
                switch (nextChar) {
                    case 'e':
                        currentState = State.ELSE;
                        return true;
                    default:
                        if (Character.isLetterOrDigit(nextChar)) {
                            currentState = State.ID;
                            return true;
                        }
                        return false;
                }
            case ELSE:
                if (Character.isLetterOrDigit(nextChar)) {
                    currentState = State.ID;
                    return true;
                }
                return false;
            case w:
                switch (nextChar) {
                    case 'h':
                        currentState = State.wh;
                        return true;
                    default:
                        if (Character.isLetterOrDigit(nextChar)) {
                            currentState = State.ID;
                            return true;
                        }
                        return false;
                }
            case wh:
                switch (nextChar) {
                    case 'i':
                        currentState = State.whi;
                        return true;
                    default:
                        if (Character.isLetterOrDigit(nextChar)) {
                            currentState = State.ID;
                            return true;
                        }
                        return false;
                }
            case whi:
                switch (nextChar) {
                    case 'l':
                        currentState = State.whil;
                        return true;
                    default:
                        if (Character.isLetterOrDigit(nextChar)) {
                            currentState = State.ID;
                            return true;
                        }
                        return false;
                }
            case whil:
                switch (nextChar) {
                    case 'e':
                        currentState = State.WHILE;
                        return true;
                    default:
                        if (Character.isLetterOrDigit(nextChar)) {
                            currentState = State.ID;
                            return true;
                        }
                        return false;
                }
            case WHILE:
                if (Character.isLetterOrDigit(nextChar)) {
                    currentState = State.ID;
                    return true;
                }
                return false;
            case r:
                switch (nextChar) {
                    case 'e':
                        currentState = State.re;
                        return true;
                    default:
                        if (Character.isLetterOrDigit(nextChar)) {
                            currentState = State.ID;
                            return true;
                        }
                        return false;
                }
            case re:
                switch (nextChar) {
                    case 't':
                        currentState = State.ret;
                        return true;
                    default:
                        if (Character.isLetterOrDigit(nextChar)) {
                            currentState = State.ID;
                            return true;
                        }
                        return false;
                }
            case ret:
                switch (nextChar) {
                    case 'u':
                        currentState = State.retu;
                        return true;
                    default:
                        if (Character.isLetterOrDigit(nextChar)) {
                            currentState = State.ID;
                            return true;
                        }
                        return false;
                }
            case retu:
                switch (nextChar) {
                    case 'r':
                        currentState = State.retur;
                        return true;
                    default:
                        if (Character.isLetterOrDigit(nextChar)) {
                            currentState = State.ID;
                            return true;
                        }
                        return false;
                }
            case retur:
                switch (nextChar) {
                    case 'n':
                        currentState = State.RETURN;
                        return true;
                    default:
                        if (Character.isLetterOrDigit(nextChar)) {
                            currentState = State.ID;
                            return true;
                        }
                        return false;
                }
            case RETURN:
                if (Character.isLetterOrDigit(nextChar)) {
                    currentState = State.ID;
                    return true;
                }
                return false;
            case NUM:
                return Character.isDigit(nextChar);
            case ID:
                return Character.isLetterOrDigit(nextChar);
            case SLASH:
                switch (nextChar) {
                    case '/':
                        currentState = State.inComment;
                        return true;
                    default:
                        return false;
                }
            case inComment:
                switch (nextChar) {
                    case '\n':
                        currentState = State.COMMENT;
                        return true;
                    default:
                        return true;
                }
            default:
                return false;
        }
    }
    public boolean currentStateAccepting() {
        return accepting.contains(currentState);
    }
}