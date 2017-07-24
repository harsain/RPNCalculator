package com.harsain.RPNCalculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * App
 */
public class App 
{
//    private static Scanner input = new Scanner(System.in);

    /**
     * the main entry point for the application
     * @param args
     */
    public static void main( String[] args )
    {
        try {
            runCalculator(new Scanner(System.in));
        } catch (Exception exp) {
            System.out.println("Oops, an error occurred, please try re-run the application");
        }
    }

    /**
     * Starts the calculator
     * @throws Exception
     */
    public static void runCalculator(Scanner scanner) throws Exception {
        System.out.println("Welcome to the RPN Calculator Application");
        System.out.println("Enter your expression or 'EXIT' to quit");
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("Available commands: %s\n", Arrays.asList(OperatorEnum.getOperators()));
        expressionInput(scanner);
    }

    /**
     *
     */
    private static void expressionInput(Scanner scanner) {
        Calculator calculator = new Calculator();
        String userInput = "";
        while (!userInput.equalsIgnoreCase("EXIT")) {
            System.out.println("Enter next input: ");

            userInput = scanner.nextLine();
            calculator.eval(userInput);
        }
    }
}

