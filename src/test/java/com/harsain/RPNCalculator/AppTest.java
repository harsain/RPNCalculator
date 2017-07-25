package com.harsain.RPNCalculator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import junit.framework.TestCase;

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
        super.tearDown();
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }

    public void testAllowToEnterInput() {
        try {
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String input = "1 1\nexit\n";
            App.runCalculator(new Scanner(input));
            assertEquals("Welcome to the RPN Calculator Application\n"
                + "Enter your expression or 'EXIT' to quit\n"
                + "-----------------------------------------------------\n"
                + "Available commands: [[undo, sqrt, clear, *, +, -, /]]\n"
                + "Enter next input: \n"
                + "Stack:\n"
                + "1.0000000000 1.0000000000 \n", os.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
