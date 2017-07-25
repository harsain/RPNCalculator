package com.harsain.RPNCalculator.Expression;

import com.harsain.RPNCalculator.Exception.RPNCalculatorInsufficientOperands;
import com.harsain.RPNCalculator.OperatorEnum;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sqrt implements Expression {

    private final Expression operand;

    public Sqrt(Stack<Expression> valuesStack) throws RPNCalculatorInsufficientOperands {
        // check for the no of operators, if not ok return exception
        if (valuesStack.size() < OperatorEnum.SQRT.getNoOfOperands()) {
            throw new RPNCalculatorInsufficientOperands(OperatorEnum.SQRT.name() + " requires " + OperatorEnum.SQRT.getNoOfOperands() + " operands");
        } else {
            // assign the operand from the stack
            this.operand = valuesStack.pop();
        }
    }

    /**
     * Perform the actual Sqrt action
     * @return
     */
    public List<BigDecimal> interpret() {
        return Collections.singletonList(new BigDecimal(Math.sqrt(this.operand.interpret().get(0).doubleValue())).setScale(15));
    }

    @Override
    public Collection<? extends Expression> getOperands() {
        return Stream.of(this.operand).collect(Collectors.toList());
    }

    @Override
    public OperatorEnum getOperator() {
        return OperatorEnum.SQRT;
    }
}
