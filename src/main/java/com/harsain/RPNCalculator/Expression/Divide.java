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

public class Divide implements Expression {

    private final Expression leftOperand;
    private final Expression rightOperand;

    public Divide(Stack<Expression> valuesStack) throws RPNCalculatorInsufficientOperands {
        // check for the no of operators, if not ok return exception
        if (valuesStack.size() < OperatorEnum.DIVIDE.getNoOfOperands()) {
            throw new RPNCalculatorInsufficientOperands(OperatorEnum.DIVIDE.name() + " requires " + OperatorEnum.DIVIDE.getNoOfOperands() + " operands");
        } else {
            this.rightOperand = valuesStack.pop();
            this.leftOperand = valuesStack.pop();
        }
    }

    @Override
    public List<BigDecimal> interpret() {
        return Collections.singletonList(this.leftOperand.interpret().get(0).divide(this.rightOperand.interpret().get(0)));
    }

    @Override
    public Collection<? extends Expression> getOperands() {
        return Stream.of(this.leftOperand, this.rightOperand).collect(Collectors.toList());
    }

    public OperatorEnum getOperator() {
        return OperatorEnum.DIVIDE;
    }
}
