package com.harsain.RPNCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Undo implements Expression {

    private List<Double> previousOperands = new ArrayList<Double>();

    public Undo(Stack<Expression> lastOperands, OperatorEnum lastOperator) {
        if (lastOperator.equals(OperatorEnum.UNDO)) {
            this.previousOperands.clear();
        } else if (lastOperator.equals(OperatorEnum.SQRT)) {
            lastOperands.pop();
            this.previousOperands.add(lastOperands.get(lastOperands.size() - 1).interpret().get(0));
        } else {
            this.previousOperands.add(lastOperands.get(lastOperands.size() - 1).interpret().get(0));
            this.previousOperands.add(lastOperands.get(lastOperands.size() - 2).interpret().get(0));
        }
    }

    public List<Double> interpret() {
        return this.previousOperands;
    }
}
