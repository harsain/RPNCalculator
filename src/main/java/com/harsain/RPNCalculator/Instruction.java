package com.harsain.RPNCalculator;

import com.harsain.RPNCalculator.Expression.Expression;

public class Instruction {

    private Expression expression;
    private OperatorEnum operand;

    /**
     * Creates a collection of the expression & the operand, which is used for the undo action
     * @param expression
     * @param operand
     */
    public Instruction(Expression expression, OperatorEnum operand) {
        this.expression = expression;
        this.operand = operand;
    }

    public Expression getExpression() {
        return expression;
    }

    public OperatorEnum getOperand() {
        return operand;
    }
}
