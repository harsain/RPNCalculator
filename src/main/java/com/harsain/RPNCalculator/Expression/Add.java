package com.harsain.RPNCalculator.Expression;

import com.harsain.RPNCalculator.Exception.RPNCalculatorInsufficientOperands;
import com.harsain.RPNCalculator.OperatorEnum;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Add implements Expression{

    private final Expression leftOperand;
    private final Expression rightOperand;

    public Add(Stack<Expression> valuesStack) throws RPNCalculatorInsufficientOperands {
        // check for the no of operators, if not ok return exception
        if (valuesStack.size() < OperatorEnum.ADD.getNoOfOperands() ) {
            throw new RPNCalculatorInsufficientOperands(OperatorEnum.ADD.name() + " requires " + OperatorEnum.ADD.getNoOfOperands() + " operands");
        } else {
            // assign the operands from the stack
            this.leftOperand = valuesStack.pop();
            this.rightOperand = valuesStack.pop();
        }
    }

    /**
     * perform the addition
     * @return
     */
    public List<BigDecimal> interpret() {
        return Collections.singletonList(leftOperand.interpret().get(0).add(rightOperand.interpret().get(0)));
    }

    /**
     * returns all the operands used to perform the action
     * @return
     */
    public List<Expression> getOperands() {
        return Stream.of(this.rightOperand, this.leftOperand).collect(Collectors.toList());
    }

    public OperatorEnum getOperator() {
        return OperatorEnum.ADD;
    }
}
