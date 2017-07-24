package com.harsain.RPNCalculator;

import com.harsain.RPNCalculator.Exception.CalculatorException;
import com.harsain.RPNCalculator.Exception.RPNCalculatorInsufficientOperands;
import com.harsain.RPNCalculator.Expression.*;

import java.util.Stack;
import java.util.logging.Logger;

public class ExpressionUtil {

    private static Logger log = Logger.getLogger(ExpressionUtil.class.getName());

    private static Stack<Expression> lastOperands = new Stack<Expression>();
    private static OperatorEnum lastInstruction;

    /**
     * checks if the string is an operator
     * @param s
     * @return
     */
    public static boolean isOperator(String s) {
        return OperatorEnum.containsOperator(s);
    }

    /**
     * based on the operator passed, it returns an expression for calculation
     * @param operator
     * @param valuesStack
     * @param instructions
     * @return
     * @throws CalculatorException
     * @throws RPNCalculatorInsufficientOperands
     */
    public static Expression getOperator(String operator, Stack<Expression> valuesStack, Stack<Instruction> instructions) throws CalculatorException, RPNCalculatorInsufficientOperands {
        try {
            // switch on the operator
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
                        log.warning("No operation to perform undo action");
                        throw new CalculatorException("no operation to undo");
                    }
                    Instruction lastInstruction = instructions.pop();
                    Expression undoExp = new Undo(valuesStack, lastInstruction);

                    return undoExp;
                case CLEAR:
                    // in case of clear, it just clears all stacks
                    valuesStack.clear();
                    lastInstruction = null;
                    lastOperands.clear();
                    return null;
                default:
                    // in default case it just returns null
                    return null;
            }
        } catch (RPNCalculatorInsufficientOperands insufficientOperandsExp) {
            log.warning(insufficientOperandsExp.getMessage());
            throw insufficientOperandsExp;
        }
    }

    /**
     * this just clears the last interaction, which is used for the undo operation
     */
    public static void clearLastInteraction() {
        lastInstruction = null;
        lastOperands.empty();
    }
}
