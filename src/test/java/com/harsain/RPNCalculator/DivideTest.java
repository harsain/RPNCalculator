package com.harsain.RPNCalculator;

import com.harsain.RPNCalculator.Exception.RPNCalculatorInsufficientOperands;
import com.harsain.RPNCalculator.Expression.Divide;
import com.harsain.RPNCalculator.Expression.Expression;
import com.harsain.RPNCalculator.Expression.Operand;
import junit.framework.TestCase;

import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

public class DivideTest extends TestCase {
    private Divide divide;

    public void testValidInput() {
        Stack<Expression> values = new Stack<>();
        values.push(new Operand(new BigDecimal(4)));
        values.push(new Operand(new BigDecimal(2)));
        try {
            divide = new Divide(values);
            List<BigDecimal> result = divide.interpret();

            assertEquals(2.0, result.get(0).doubleValue());
        } catch (RPNCalculatorInsufficientOperands insufficientOperands) {
            insufficientOperands.printStackTrace();
            fail("Failed with insufficient operands exception");
        }
    }

    public void testInvalidNoOfOperands() {
        Stack<Expression> values = new Stack<>();
        values.push(new Operand(new BigDecimal(4)));
        try {
            divide = new Divide(values);
            List<BigDecimal> result = divide.interpret();

            fail("Should have caught the exception");
        } catch (RPNCalculatorInsufficientOperands insufficientOperands) {
            insufficientOperands.printStackTrace();
            assertEquals("DIVIDE requires at least 2 operands", insufficientOperands.getMessage());
        }
    }

}
