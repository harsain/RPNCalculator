package com.harsain.RPNCalculator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public enum OperatorEnum {
    ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/"), SQRT("sqrt"), UNDO("undo"), CLEAR("clear");

    private final static Set<String> values = new HashSet<String>(OperatorEnum.values().length);
    private final static Set<String> valuesExp = new HashSet<String>(OperatorEnum.values().length);
    private final static HashMap<String, OperatorEnum> lookup = new HashMap<String, OperatorEnum>();

    static {
        for (OperatorEnum operatorEnum: OperatorEnum.values()) {
            values.add(operatorEnum.name());
            valuesExp.add(operatorEnum.operator);
            lookup.put(operatorEnum.operator, operatorEnum);
        }
    }

    private String operator;

    OperatorEnum(String operator) { this.operator = operator; }

    public static OperatorEnum getOperator(String stringOperand) {
        return OperatorEnum.valueOf(stringOperand);
    }

    public static boolean containsOperator(String operator) {
        return valuesExp.contains(operator);
    }

    public static OperatorEnum get(String abbreviation) {
        return lookup.get(abbreviation);
    }
    public static boolean contains (String value){
        return values.contains(value);
    }
}
