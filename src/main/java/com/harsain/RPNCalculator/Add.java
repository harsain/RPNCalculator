package com.harsain.RPNCalculator;

import java.util.Collections;
import java.util.List;

public class Add implements Expression{

    private final Expression leftOperand;
    private final Expression rightOperand;

    public Add(Expression leftOperand, Expression rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    public List<Double> interpret() {
        return Collections.singletonList(leftOperand.interpret().get(0) + rightOperand.interpret().get(0));
    }
}
