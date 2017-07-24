package com.harsain.RPNCalculator;

import java.util.*;

public enum OperatorEnum {
    // all the operators available in the application
    ADD("+", "-", 2),
    SUBTRACT("-", "+", 2),
    MULTIPLY("*", "/", 2),
    DIVIDE("/", "*", 2),
    SQRT("sqrt", null, 1),
    UNDO("undo", null, 0),
    CLEAR("clear", null, 0);

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
    private String reverseOperator;
    private int noOfOperands;

    OperatorEnum(String operator, String reverseOperand, int noOfOperands) {
        this.operator = operator;
        this.reverseOperator = reverseOperand;
        this.noOfOperands = noOfOperands;
    }

    public String getOperator() {
        return operator;
    }

    public String getReverseOperator() {
        return reverseOperator;
    }

    public int getNoOfOperands() {
        return noOfOperands;
    }

    public static OperatorEnum getOperator(String stringOperand) {
        return OperatorEnum.valueOf(stringOperand);
    }

    public static boolean containsOperator(String operator) {
        return valuesExp.contains(operator);
    }

    /**
     * returns all the operators available
     * @return
     */
    public static List<String> getOperators() {
        List<String> operatorArr = new ArrayList<>();
        lookup.values().stream().forEach(val -> operatorArr.add(val.operator));

        return operatorArr;
    }

    /**
     * return operator based on the operator abbreviation
     * @param abbreviation
     * @return
     */
    public static OperatorEnum get(String abbreviation) {
        return lookup.get(abbreviation);
    }
    public static boolean contains (String value){
        return values.contains(value);
    }
}
