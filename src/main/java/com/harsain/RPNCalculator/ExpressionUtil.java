package com.harsain.RPNCalculator;

import com.harsain.RPNCalculator.Exception.CalculatorException;
import com.harsain.RPNCalculator.Exception.RPNCalculatorInsufficientOperands;
import com.harsain.RPNCalculator.Expression.*;

import java.util.Stack;

public class ExpressionUtil {

    private static Stack<Expression> lastOperands = new Stack<Expression>();
    private static OperatorEnum lastInstruction;

    public static boolean isOperator(String s) {
        return OperatorEnum.containsOperator(s);
    }

    public static Expression getOperator(String operator, Stack<Expression> valuesStack, Stack<Instruction> instructions) throws CalculatorException, RPNCalculatorInsufficientOperands {
        try {
            switch (OperatorEnum.get(operator)) {
                case ADD:
                    Expression add = new Add(valuesStack);
                    clearLastInteraction();

                    lastInstruction = OperatorEnum.ADD;
                    lastOperands.addAll(add.getOperands());
                    instructions.push(new Instruction(add, OperatorEnum.ADD));
                    return add;
                case SUBTRACT:
                    Expression substract = new Substract(valuesStack);
                    clearLastInteraction();

                    lastInstruction = OperatorEnum.SUBTRACT;
                    lastOperands.addAll(substract.getOperands());
                    instructions.push(new Instruction(substract, OperatorEnum.SUBTRACT));

                    return substract;
                case MULTIPLY:
                    Expression multiply = new Multiply(valuesStack);
                    clearLastInteraction();

                    lastInstruction = OperatorEnum.MULTIPLY;
                    lastOperands.addAll(multiply.getOperands());
                    instructions.push(new Instruction(multiply, OperatorEnum.MULTIPLY));

                    return multiply;
                case DIVIDE:
                    Expression divide = new Divide(valuesStack);
                    clearLastInteraction();

                    lastInstruction = OperatorEnum.DIVIDE;
                    lastOperands.addAll(divide.getOperands());
                    instructions.push(new Instruction(divide, OperatorEnum.DIVIDE));

                    return divide;
                case SQRT:
                    Expression sqrt = new Sqrt(valuesStack);
                    clearLastInteraction();

                    lastInstruction = OperatorEnum.SQRT;
                    lastOperands.addAll(sqrt.getOperands());
                    instructions.push(new Instruction(sqrt, OperatorEnum.SQRT));

                    return sqrt;
                case UNDO:
                    if (instructions.empty()) {
                        throw new CalculatorException("no operation to undo");
                    }
                    Instruction lastInstruction = instructions.pop();

                    Expression undoExp = new Undo(valuesStack, lastInstruction);
                    return undoExp;

                case CLEAR:
                    valuesStack.clear();
                    lastInstruction = null;
                    lastOperands.clear();
                    return null;
                default:
                    return null;
            }
        } catch (RPNCalculatorInsufficientOperands insufficientOperandsExp) {
            throw insufficientOperandsExp;
        }
    }

    public static void clearLastInteraction() {
        lastInstruction = null;
        lastOperands.empty();
    }
}
