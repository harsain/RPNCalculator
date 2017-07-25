package com.harsain.RPNCalculator.Expression;

import com.harsain.RPNCalculator.OperatorEnum;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Operand implements Expression {
    private final BigDecimal num;

    public Operand(BigDecimal num) {
        this.num = num;
    }

    public List<BigDecimal> interpret() {
        return Collections.singletonList(this.num);
    }

    @Override
    public Collection<? extends Expression> getOperands() {
        return Collections.singletonList(new Operand(this.num));
    }

    @Override
    public OperatorEnum getOperator() {
        return null;
    }
}
