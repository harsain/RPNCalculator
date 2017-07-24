package com.harsain.RPNCalculator.Expression;

import com.harsain.RPNCalculator.Instruction;
import com.harsain.RPNCalculator.OperatorEnum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class Undo implements Expression {

    private List<Double> previousOperands = new ArrayList<Double>();

    public Undo(Stack<Expression> valuesStack, Instruction lastExpression) {
        if (lastExpression != null) {
            if (lastExpression.getOperand().equals(OperatorEnum.UNDO)) {
                this.previousOperands.clear();
            } else {
                valuesStack.pop();
                for (Expression operand : lastExpression.getExpression().getOperands()) {
                    this.previousOperands.add(operand.interpret().get(0));
                }
            }
        } else {
            valuesStack.pop();
        }

    }

    public List<Double> interpret() {
        return this.previousOperands;
    }

    @Override
    public Collection<? extends Expression> getOperands() {
        return null;
    }

    @Override
    public OperatorEnum getOperand() {
        return OperatorEnum.UNDO;
    }
}
