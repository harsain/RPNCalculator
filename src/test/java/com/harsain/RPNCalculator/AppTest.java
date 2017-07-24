package com.harsain.RPNCalculator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.*;
import java.util.Scanner;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    private App app;
    private OutputStream os;

    public void setUp() throws Exception {
        super.setUp();

        app = new App();

        os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
    }

    public void tearDown() throws Exception {
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }

    public void testAllowToEnterInput() {
        try {
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        App tempApp = new App();

        try {
            String input = "1 1\nexit\n";
            tempApp.runCalculator(new Scanner(input));
            assertEquals("Welcome to the RPN Calculator Application\n" +
                    "Enter your expression or 'EXIT' to quit\n" +
                    "-------------------------------------------------------------------------\n" +
                    "Available commands: [[undo, sqrt, clear, *, +, -, /]]\n" +
                    "Enter next input: \n" +
                    "Stack:\n" +
                    "1.0000000000 1.0000000000 \n" +
                    "Enter next input: \n" +
                    "Stack:\n" +
                    "1.0000000000 1.0000000000 \n", os.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
