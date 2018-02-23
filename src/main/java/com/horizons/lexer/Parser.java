package com.horizons.lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Parser {
    private Grammar grammar;
    private ArrayList<Token> input;
    private HashMap<MemoKey, LinkedList<Tree>> memo;
    public ParseCYK(ArrayList<Token> input) {
        this.grammar = new Grammar();
        this.input = input;
    }
    public Tree exec() {
        memo = new HashMap<>();
        return parse(new LinkedList<GS>(Arrays.asList(Grammar.start)), 0, input.size()).peekFirst();
    }

    private LinkedList<Tree> parse(LinkedList<GS> lhs, int from, int length) {

    }
}
