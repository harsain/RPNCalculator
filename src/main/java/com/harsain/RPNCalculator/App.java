package com.harsain.RPNCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * App
 */
public class App 
{
    private static Scanner input = new Scanner(System.in);

    /**
     * the main entry point for the application
     * @param args
     */
    public static void main( String[] args )
    {
        try {
            runCalculator();
        } catch (Exception exp) {
            System.out.println("Oops, an error occurred, please try re-run the application");
        }
    }

    /**
     * Starts the calculator
     * @throws Exception
     */
    private static void runCalculator() throws Exception {
        System.out.println("Welcome to the RPN Calculator Application");
        System.out.println("Enter your expression or 'EXIT' to quit");
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("Available commands: %s\n", Arrays.asList(OperatorEnum.values()));
        expressionInput();
    }

    /**
     *
     */
    private static void expressionInput() {
        Calculator calculator = new Calculator();
        String userInput = "";
        while (!userInput.equalsIgnoreCase("EXIT")) {
            System.out.println("Enter next input: ");

            userInput = input.nextLine();
            calculator.eval(userInput);
        }
    }
}

