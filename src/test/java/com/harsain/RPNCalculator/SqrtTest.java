package com.harsain.RPNCalculator;

import com.harsain.RPNCalculator.Exception.RPNCalculatorInsufficientOperands;
import com.harsain.RPNCalculator.Expression.Expression;
import com.harsain.RPNCalculator.Expression.Operand;
import com.harsain.RPNCalculator.Expression.Sqrt;
import junit.framework.TestCase;

import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

public class SqrtTest extends TestCase {
    private Sqrt sqrt;

    public void testValidInput() {
        Stack<Expression> values = new Stack<>();
        values.push(new Operand(new BigDecimal(4)));
        try {
            sqrt = new Sqrt(values);
            List<BigDecimal> result = sqrt.interpret();

            assertEquals(2.0, result.get(0).doubleValue());
        } catch (RPNCalculatorInsufficientOperands insufficientOperands) {
            insufficientOperands.printStackTrace();
            fail("Should not have caught exception");
        }
    }

    public void testInvalidNoOfOperands() {
        Stack<Expression> values = new Stack<>();
        try {
            sqrt = new Sqrt(values);
            List<BigDecimal> result = sqrt.interpret();


            fail("Should have caught exception");
        } catch (RPNCalculatorInsufficientOperands insufficientOperands) {
            insufficientOperands.printStackTrace();
            assertEquals("SQRT requires 1 operands", insufficientOperands.getMessage());
        }
    }
}
