package com.harsain.RPNCalculator;

public class Operand implements Expression {
    private final Double num;

    public Operand(Double num) {
        this.num = num;
    }

    public Double interpret() {
        return this.num;
    }
}
