package com.harsain.RPNCalculator;

import java.util.Stack;

public class ExpressionUtil {

    private static Stack<Expression> lastOperands = new Stack<Expression>();
    private static Stack<OperatorEnum> lastInstruction = new Stack<OperatorEnum>();

    public static boolean isOperator(String s) {
        return OperatorEnum.containsOperator(s);
    }

    public static Expression getOperator(String operator, Stack<Expression> valuesStack) {
        lastInstruction.empty();
        lastOperands.empty();
        switch (OperatorEnum.get(operator)) {
            case ADD:
                Expression rightExpression = valuesStack.pop();
                Expression leftExpression = valuesStack.pop();
                lastOperands.push(leftExpression);
                lastOperands.push(rightExpression);
                lastInstruction.push(OperatorEnum.ADD);
                return new Add(leftExpression, rightExpression);
            case SUBTRACT:
                break;
            case MULTIPLY:
                break;
            case DIVIDE:
                break;
            case SQRT:
                break;
            case UNDO:
                break;
            case CLEAR:
                valuesStack.empty();
                break;
            default:
                return null;
        }
        return null;
    }
}
