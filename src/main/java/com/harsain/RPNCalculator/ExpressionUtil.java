package com.harsain.RPNCalculator;

import java.util.Stack;

public class ExpressionUtil {

    private static Stack<Expression> lastOperands = new Stack<Expression>();
    private static OperatorEnum lastInstruction;

    public static boolean isOperator(String s) {
        return OperatorEnum.containsOperator(s);
    }

    public static Expression getOperator(String operator, Stack<Expression> valuesStack) {
        switch (OperatorEnum.get(operator)) {
            case ADD:
                Expression rightExpression = valuesStack.pop();
                Expression leftExpression = valuesStack.pop();
                lastOperands.push(rightExpression);
                lastOperands.push(leftExpression);
                lastInstruction = OperatorEnum.ADD;
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
                Expression undoExp = new Undo(lastOperands, lastInstruction);
                valuesStack.pop();
                return undoExp;
            case CLEAR:
                valuesStack.empty();
                break;
            default:
                return null;
        }
        return null;
    }

    private void clearLastInteraction() {
        lastInstruction = null;
        lastOperands.empty();
    }
}
