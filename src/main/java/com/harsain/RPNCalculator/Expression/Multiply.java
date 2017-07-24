package com.harsain.RPNCalculator.Expression;

import com.harsain.RPNCalculator.Exception.RPNCalculatorInsufficientOperands;
import com.harsain.RPNCalculator.OperatorEnum;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Multiply implements Expression {

    private final Expression leftOperand;
    private final Expression rightOperand;

    public Multiply(Stack<Expression> valuesStack) throws RPNCalculatorInsufficientOperands {
        if (valuesStack.size() < OperatorEnum.MULTIPLY.getNoOfOperands()) {
            throw new RPNCalculatorInsufficientOperands(OperatorEnum.MULTIPLY.name() + " requires" + OperatorEnum.MULTIPLY.getNoOfOperands() + " operands");
        } else {
            this.leftOperand = valuesStack.pop();
            this.rightOperand = valuesStack.pop();
        }
    }

    @Override
    public List<Double> interpret() {
        return Collections.singletonList(this.leftOperand.interpret().get(0) * this.rightOperand.interpret().get(0));
    }

    @Override
    public Collection<? extends Expression> getOperands() {
        return Stream.of(this.rightOperand, this.leftOperand).collect(Collectors.toList());
    }

    public OperatorEnum getOperand() {
        return OperatorEnum.MULTIPLY;
    }
}
