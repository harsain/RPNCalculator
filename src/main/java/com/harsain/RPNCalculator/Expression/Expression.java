package com.harsain.RPNCalculator.Expression;

import com.harsain.RPNCalculator.OperatorEnum;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface Expression {
    List<BigDecimal> interpret();

    Collection<? extends Expression> getOperands();

    OperatorEnum getOperator();
}
