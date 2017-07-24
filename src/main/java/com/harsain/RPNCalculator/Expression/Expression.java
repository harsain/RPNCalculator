package com.harsain.RPNCalculator.Expression;

import com.harsain.RPNCalculator.OperatorEnum;

import java.util.Collection;
import java.util.List;

public interface Expression {
    List<Double> interpret();

    Collection<? extends Expression> getOperands();

    OperatorEnum getOperand();
}
