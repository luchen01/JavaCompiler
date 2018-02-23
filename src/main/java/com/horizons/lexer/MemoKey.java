package com.horizons.lexer;

import java.util.LinkedList;

public class MemoKey {

    private LinkedList<GS> lhs;
    private int from;
    private int length;
    public MemoKey(LinkedList<GS> lhs, int from, int length) {
        this.lhs = lhs;
        this.from = from;
        this.length = length;
    }

    @Override
    public int hashCode() {
        return (lhs.toString() + from + length).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof MemoKey)) {
            return false;
        }
        MemoKey a = (MemoKey) o;

        return  a.lhs.equals(lhs) &&
                a.from == from &&
                a.length == length;
    }

}
