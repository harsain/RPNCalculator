package com.harsain.RPNCalculator;

import com.harsain.RPNCalculator.Exception.ExitException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Reverse Polish Notation Application
 */
public class App 
{
    private static Logger log = Logger.getLogger(App.class.getName());

    /**
     * the main entry point for the application
     * @param args
     */
    public static void main( String[] args )
    {
        try {
            runCalculator(new Scanner(System.in));
        } catch (Exception exp) {
            log.warning(exp.getMessage());
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
        System.out.println("-----------------------------------------------------");
        System.out.printf("Available commands: %s\n", Arrays.asList(OperatorEnum.getOperators()));
        expressionInput(scanner);
    }

    /**
     * this method handles the user input and keeps the program running till user exist's
     */
    private static void expressionInput(Scanner scanner) {
        Calculator calculator = new Calculator();
        String userInput = scanner.nextLine();
        while (!userInput.equalsIgnoreCase("EXIT")) {
            System.out.println("Enter next input: ");

            log.info("user entered: " + userInput);
            try {
                // only process if not empty
                if (!userInput.trim().isEmpty()) {
                    calculator.eval(userInput);
                }
                userInput = scanner.nextLine();
            } catch (ExitException e) {
                System.exit(1);
            } catch (NoSuchElementException noSuchElemExcep) {
                log.warning(noSuchElemExcep.getMessage());
            }
        }
    }
}

