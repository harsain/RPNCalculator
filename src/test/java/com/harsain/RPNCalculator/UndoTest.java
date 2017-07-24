package com.harsain.RPNCalculator;

import com.harsain.RPNCalculator.Exception.RPNCalculatorInsufficientOperands;
import com.harsain.RPNCalculator.Expression.Add;
import com.harsain.RPNCalculator.Expression.Expression;
import com.harsain.RPNCalculator.Expression.Operand;
import com.harsain.RPNCalculator.Expression.Undo;
import junit.framework.TestCase;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

public class UndoTest extends TestCase {
    private Undo undo;
    OutputStream os;

    public void testToRemoveLastItemInStack() {
        Stack<Expression> values = new Stack<>();
        values.push(new Operand(new BigDecimal(1.0)));
        values.push(new Operand(new BigDecimal(2.0)));
        values.push(new Operand(new BigDecimal(3.0)));

        undo = new Undo(values, null);
        List<BigDecimal> result = undo.interpret();

        assertEquals(2, values.size());
    }

    public void testUndoAddOperation() {
        Stack<Expression> values = new Stack<>();
        values.push(new Operand(new BigDecimal(5.0)));
        values.push(new Operand(new BigDecimal(2.0)));
        values.push(new Operand(new BigDecimal(3.0)));

        try {
            Expression add = new Add(values);
            Instruction lastInstruction = new Instruction(add, OperatorEnum.ADD);
            undo = new Undo(values, lastInstruction);
            List<BigDecimal> result = undo.interpret();

            assertEquals(2, result.size());
        } catch (RPNCalculatorInsufficientOperands insufficientOperands) {
            insufficientOperands.printStackTrace();
            fail("Should not throw insufficient operands exception");
        }
    }
}
