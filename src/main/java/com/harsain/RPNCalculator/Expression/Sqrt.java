package com.harsain.RPNCalculator.Expression;

import com.harsain.RPNCalculator.Exception.RPNCalculatorInsufficientOperands;
import com.harsain.RPNCalculator.OperatorEnum;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sqrt implements Expression {

    private final Expression operand;

    public Sqrt(Stack<Expression> valuesStack) throws RPNCalculatorInsufficientOperands {
        if (valuesStack.size() < OperatorEnum.SQRT.getNoOfOperands()) {
            throw new RPNCalculatorInsufficientOperands(OperatorEnum.SQRT.name() + " requires" + OperatorEnum.SQRT.getNoOfOperands() + " operands");
        } else {
            this.operand = valuesStack.pop();
        }
    }

    @Override
    public List<Double> interpret() {
        return Collections.singletonList(Math.sqrt(this.operand.interpret().get(0)));
    }

    @Override
    public Collection<? extends Expression> getOperands() {
        return Stream.of(this.operand).collect(Collectors.toList());
    }

    @Override
    public OperatorEnum getOperand() {
        return OperatorEnum.SQRT;
    }
}