package com.harsain.RPNCalculator;

import com.harsain.RPNCalculator.Exception.RPNCalculatorInsufficientOperands;
import com.harsain.RPNCalculator.Expression.Add;
import com.harsain.RPNCalculator.Expression.Expression;
import com.harsain.RPNCalculator.Expression.Operand;
import junit.framework.TestCase;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

public class AddTest extends TestCase{
    private Add add;
    OutputStream os;

    public void testValidInput() {
        Stack<Expression> values = new Stack<>();
        values.push(new Operand(new BigDecimal(1.0)));
        values.push(new Operand(new BigDecimal(1.1)));
        try {
            add = new Add(values);
            List<BigDecimal> result = add.interpret();

            assertEquals(2.1, result.get(0).doubleValue());

        } catch (RPNCalculatorInsufficientOperands insufficientOperands) {
            insufficientOperands.printStackTrace();
            fail("Failed with insufficent operands exception");
        }
    }


    public void testInValidNoOfOperands() {
        Stack<Expression> values = new Stack<>();
        values.push(new Operand(new BigDecimal(1.0)));
        try {
            add = new Add(values);
            List<BigDecimal> result = add.interpret();

            fail("Should have thrown insufficient number of operands exception");
        } catch (RPNCalculatorInsufficientOperands insufficientOperands) {
            insufficientOperands.printStackTrace();
            assertEquals("ADD requires 2 operands", insufficientOperands.getMessage());
        }
    }
}
