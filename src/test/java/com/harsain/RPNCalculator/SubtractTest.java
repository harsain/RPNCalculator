package com.harsain.RPNCalculator;

import com.harsain.RPNCalculator.Exception.RPNCalculatorInsufficientOperands;
import com.harsain.RPNCalculator.Expression.Expression;
import com.harsain.RPNCalculator.Expression.Operand;
import com.harsain.RPNCalculator.Expression.Substract;
import junit.framework.TestCase;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

public class SubtractTest extends TestCase {
    private Substract subtract;
    OutputStream os;

    public void testValidInput() {
        Stack<Expression> values = new Stack<>();
        values.push(new Operand(new BigDecimal(5)));
        values.push(new Operand(new BigDecimal(2)));
        try {
            subtract = new Substract(values);
            List<BigDecimal> result = subtract.interpret();

            assertEquals(3, result.get(0).intValue());
        } catch (RPNCalculatorInsufficientOperands insufficientOperands) {
            insufficientOperands.printStackTrace();
            fail("Failed with insufficient operands exception");
        }
    }

    public void testInvalidNoOfOperands() {
        Stack<Expression> values = new Stack<>();
        values.push(new Operand(new BigDecimal(5)));
        try {
            subtract = new Substract(values);
            List<BigDecimal> result = subtract.interpret();

            fail("Should have thrown insufficient number of operands exception");
        } catch (RPNCalculatorInsufficientOperands insufficientOperands) {
            insufficientOperands.printStackTrace();
            assertEquals("SUBTRACT requires at least 2 operands", insufficientOperands.getMessage());
        }
    }

}
