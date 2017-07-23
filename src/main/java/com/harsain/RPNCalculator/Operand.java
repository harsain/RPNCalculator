package com.harsain.RPNCalculator;

import java.util.Collections;
import java.util.List;

public class Operand implements Expression {
    private final Double num;

    public Operand(Double num) {
        this.num = num;
    }

    public List<Double> interpret() {
        return Collections.singletonList(this.num);
    }
}
