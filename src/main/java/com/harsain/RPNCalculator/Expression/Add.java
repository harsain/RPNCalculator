package com.harsain.RPNCalculator.Expression;

import com.harsain.RPNCalculator.Exception.RPNCalculatorInsufficientOperands;
import com.harsain.RPNCalculator.OperatorEnum;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Add implements Expression{

    private final Expression leftOperand;
    private final Expression rightOperand;

    public Add(Stack<Expression> valuesStack) throws RPNCalculatorInsufficientOperands {
        if (valuesStack.size() < 2 ) {
            throw new RPNCalculatorInsufficientOperands(OperatorEnum.ADD.name() + " requires at least 2 operands");
        } else {
            this.leftOperand = valuesStack.pop();
            this.rightOperand = valuesStack.pop();
        }
    }

    public List<Double> interpret() {
        return Collections.singletonList(leftOperand.interpret().get(0) + rightOperand.interpret().get(0));
    }

    public List<Expression> getOperands() {
        return Stream.of(this.rightOperand, this.leftOperand).collect(Collectors.toList());
    }

    public OperatorEnum getOperand() {
        return OperatorEnum.ADD;
    }
}
