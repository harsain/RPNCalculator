package com.harsain.RPNCalculator;

public class Add implements Expression{

    private final Expression leftOperand;
    private final Expression rightOperand;

    public Add(Expression leftOperand, Expression rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    public Double interpret() {
        return leftOperand.interpret() + rightOperand.interpret();
    }
}
