package com.harsain.RPNCalculator;

import com.harsain.RPNCalculator.Exception.CalculatorException;
import com.harsain.RPNCalculator.Exception.RPNCalculatorInsufficientOperands;
import com.harsain.RPNCalculator.Expression.Expression;
import com.harsain.RPNCalculator.Expression.Operand;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Stack;

public class Calculator {
    private Stack<Expression> valueStack = new Stack<Expression>();
    private Stack<Instruction> instructionStack = new Stack<Instruction>();

    /**
     *
     * @param input
     */
    public void eval(String input) {
        DecimalFormat decimalFormat = new DecimalFormat("#.000000000000000");
        String[] expressionArr = input.split(" ");
        Integer lastIndex = -1;
        for (String expression: expressionArr) {
            lastIndex = indexOfOperator(input, expression, lastIndex);
            BigDecimal value = tryParseString(expression);
            if (value != null) {
                // it's a digit
                valueStack.push( new Operand(value));
                instructionStack.push(null);
                ExpressionUtil.clearLastInteraction();
            } else if (ExpressionUtil.isOperator(expression) ) {
                Expression operator = null;
                try {
                    operator = ExpressionUtil.getOperator(expression, valueStack, instructionStack);
                    if (operator != null) {
                        List<BigDecimal> results = operator.interpret();
                        for (BigDecimal result : results) {
                            valueStack.push(new Operand(result));
                        }
                    }
                } catch (CalculatorException e) {
                    System.out.println(e.getMessage());
                } catch (RPNCalculatorInsufficientOperands insufficientOperands) {
                    System.out.printf("operator %s (position: %d): insufficient parameters", expression, lastIndex);
                    break;
                }
            }
        }
        this.printValueStack();
    }

    private Integer indexOfOperator(String input, String elem, Integer lastIndex) {
        return input.indexOf(elem, ++lastIndex);
    }

    private BigDecimal tryParseString(String operandString) {
        try {
            return new BigDecimal(Double.parseDouble(operandString));
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
