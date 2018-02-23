package com.horizons.lexer;

enum GS /*Grammar Symbol*/ {
    s,
    LPAREN,
    RPAREN,
}

public class Grammar {
    private static class Rule {
        private GS lhs;
        private GS[] rhs;
        private Rule(GS lhs, GS[] rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
        }
    }
    public static GS start = GS.s;
    public static final Rule[] rules = {
            new Rule(start, new GS[]{GS.s, GS.s}),
            new Rule(start, new GS[]{GS.LPAREN, GS.s, GS.RPAREN}),
            new Rule(start, new GS[]{}),
    };

}
