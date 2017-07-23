package com.harsain.RPNCalculator;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Stack;

public class Calculator {
    private Stack<Expression> valueStack = new Stack<Expression>();
    private Stack<Expression> instructionStack = new Stack<Expression>();

    public void eval(String input) {
        DecimalFormat decimalFormat = new DecimalFormat("#.000000000000000");
        String[] expressionArr = input.split(" ");
        for (String expression: expressionArr) {
            Double value = tryParseDouble(expression);
            if (value != null) {
                // it's a digit
                valueStack.push( new Operand(value));
            } else if (ExpressionUtil.isOperator(expression) ) {
                Expression operator = ExpressionUtil.getOperator(expression, valueStack);
                List<Double> results = operator.interpret();
                for (Double result : results) {
                    valueStack.push(new Operand(result));
                }
            }
        }
        this.printValueStack();
    }

    private Double tryParseDouble(String operandString) {
        try {
            return Double.parseDouble(operandString);
        } catch (NumberFormatException numEx) {
            return null;
        }
    }

    private void printValueStack() {
        System.out.println("Stack:");
        for (Expression expression: valueStack) {
            System.out.printf("%.10f ", expression.interpret().get(0));
        }
        System.out.printf("%n");
    }
}
