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

public class Substract implements Expression {

    private final Expression leftOperand;
    private final Expression rightOperand;

    public Substract(Stack<Expression> valuesStack) throws RPNCalculatorInsufficientOperands {
        // check for the no of operators, if not ok return exception
        if (valuesStack.size() < OperatorEnum.SUBTRACT.getNoOfOperands()) {
            throw new RPNCalculatorInsufficientOperands(OperatorEnum.SUBTRACT.name() + " requires " + OperatorEnum.SUBTRACT.getNoOfOperands() + " operands");
        } else {
            // assign the operands from the stack
            this.rightOperand = valuesStack.pop();
            this.leftOperand = valuesStack.pop();
        }
    }

    /**
     * Perform the actual Subtract action
     * @return
     */
    public List<BigDecimal> interpret() {
        return Collections.singletonList(this.leftOperand.interpret().get(0).subtract(this.rightOperand.interpret().get(0)));
    }

    @Override
    public Collection<? extends Expression> getOperands() {
        return Stream.of(this.rightOperand, this.leftOperand).collect(Collectors.toList());
    }

    public OperatorEnum getOperator() {
        return OperatorEnum.SUBTRACT;
    }
}
