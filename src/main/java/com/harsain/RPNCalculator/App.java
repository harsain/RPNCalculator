package com.harsain.RPNCalculator;

import java.util.Arrays;
import java.util.Scanner;

/**
 * App
 */
public class App 
{
    private static Scanner input = new Scanner(System.in);

    public static void main( String[] args )
    {
        try {
            calculator();
        } catch (Exception exp) {
            System.out.println("Oops, an error occured, please try re-run the application");
        }
    }

    public static void calculator() throws Exception {
        System.out.println("Welcome to the RPN Calculator Application (enter EXIT to stop the application)");
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("Available commands: %s\n", Arrays.asList(OperatorEnum.values()));
        takeInput();
    }

    private static void takeInput() {
        String userInput = "";
        while (!userInput.equalsIgnoreCase("EXIT")) {
            System.out.println("Enter next input: ");

            userInput = input.nextLine();

            System.out.println(userInput);
        }
    }
}

