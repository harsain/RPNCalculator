package com.harsain.RPNCalculator.Expression;

import com.harsain.RPNCalculator.Instruction;
import com.harsain.RPNCalculator.OperatorEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class Undo implements Expression {

    private List<BigDecimal> previousOperands = new ArrayList<BigDecimal>();

    public Undo(Stack<Expression> valuesStack, Instruction lastExpression) {
        if (lastExpression != null) {
            if (lastExpression.getOperand().equals(OperatorEnum.UNDO)) {
                this.previousOperands.clear();
            } else {
                // if last expression was not undo then just clear 1 last operator from the stack
                // & push last expression's operators
                valuesStack.pop();
                for (Expression operand : lastExpression.getExpression().getOperands()) {
                    this.previousOperands.add(operand.interpret().get(0));
                }
            }
        } else {
            // if the lastExpression was null,
            // that means last operation was push operator to the stack
            valuesStack.pop();
        }

    }

    /**
     * Perform the actual undo action
     * @return
     */
    public List<BigDecimal> interpret() {
        return this.previousOperands;
    }

    @Override
    public Collection<? extends Expression> getOperands() {
        return null;
    }

    @Override
    public OperatorEnum getOperator() {
        return OperatorEnum.UNDO;
    }
}
