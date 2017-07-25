package com.harsain.RPNCalculator;

import com.harsain.RPNCalculator.Exception.CalculatorException;
import com.harsain.RPNCalculator.Exception.ExitException;
import com.harsain.RPNCalculator.Exception.RPNCalculatorInsufficientOperands;
import com.harsain.RPNCalculator.Expression.Expression;
import com.harsain.RPNCalculator.Expression.Operand;
import com.sun.javafx.binding.StringFormatter;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

public class Calculator {

    private static Logger log = Logger.getLogger(Calculator.class.getName());

    private Stack<Expression> valueStack = new Stack<Expression>();
    private Stack<Instruction> instructionStack = new Stack<Instruction>();

    /**
     * this method evaluates the user input
     * @param input
     */
    public void eval(String input) throws ExitException {

        // defines the decimal format for printing the result
        DecimalFormat decimalFormat = new DecimalFormat("#.000000000000000");

        String[] expressionArr = input.split(" ");

        // this maintains the index at user input
        Integer lastIndex = -1;

        log.info("expressions: " + expressionArr.toString());

        // process each expression
        for (String expression: expressionArr) {

            // update the last index pointer
            lastIndex = indexOfOperator(input, expression, lastIndex);
            BigDecimal value = tryParseString(expression);

            if (value != null) {
                // it's a digit
                valueStack.push( new Operand(value));
                instructionStack.push(null);
                ExpressionUtil.clearLastInteraction();
            } else if (isExistExpression(expression)) {
                throw new ExitException("Exit");
            } else if (ExpressionUtil.isOperator(expression) ) {
                // it's an operator
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
                    log.warning(e.getMessage());
                    System.out.println(e.getMessage());
                } catch (RPNCalculatorInsufficientOperands insufficientOperands) {
                    log.warning(insufficientOperands.getMessage());
                    System.out.printf("operator %s (position: %d): insufficient parameters", expression, lastIndex);
                    break;
                } catch (Exception exp){
                    log.warning(exp.getMessage());
                    System.out.println(exp.getMessage());
                }
            }
        }
        // print the stack at the end
        this.printValueStack();
    }

    private boolean isExistExpression(String expression) {
        return "exit".equalsIgnoreCase(expression.trim());
    }

    /**
     * calculates the pointer / index of the operator in the user input
     * @param input
     * @param elem
     * @param lastIndex
     * @return
     */
    private Integer indexOfOperator(String input, String elem, Integer lastIndex) {
        return input.indexOf(elem, ++lastIndex);
    }

    /**
     * this method parses the user input to a number
     * @param operandString
     * @return
     */
    private BigDecimal tryParseString(String operandString) {
        try {
            return new BigDecimal(Double.parseDouble(operandString));
        } catch (NumberFormatException numEx) {
            log.warning("Error parsing expression: " + numEx.getMessage());
            return null;
        }
    }

    /**
     * this method prints the stack
     */
    private void printValueStack() {
        System.out.println("Stack:");
        for (Expression expression: valueStack) {
            System.out.printf("%.10f ", expression.interpret().get(0));
        }
        System.out.printf("%n");
    }
}
