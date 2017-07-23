package com.harsain.RPNCalculator;

import java.util.HashSet;
import java.util.Set;

public enum OperatorEnum {
    ADD, SUBTRACT, MULTIPLY, DIVIDE, SQRT, UNDO, CLEAR;

    private final static Set<String> values = new HashSet<String>(OperatorEnum.values().length);

    static {
        for (OperatorEnum operatorEnum: OperatorEnum.values()) {
            values.add(operatorEnum.name());
        }
    }

    public static boolean contains (String value){
        return values.contains(value);
    }
}
