package com.harsain.RPNCalculator;

import com.harsain.RPNCalculator.Exception.ExitException;
import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class CalculatorTest extends TestCase {
    private Calculator cal;
    OutputStream os;

    public void setUp() throws Exception {
        super.setUp();

        cal = new Calculator();

        os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
    }

    public void tearDown() throws Exception {
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }

    public void testNumberInput() {
        try {
            cal.eval("1 1");
        } catch (ExitException e) {
            e.printStackTrace();
        }
        String expectedString = "Stack:\n" +
                "1.0000000000 1.0000000000 \n";

        assertEquals(expectedString, os.toString());
    }

    public void testWithInvalidInput(){
        try {
            cal.eval("1.09875984 tesing");
        } catch (ExitException e) {
            e.printStackTrace();
        }
        assertEquals("Stack:\n"
            + "1.0987598400 \n", os.toString());
    }

    public void testMultipleExpressions() {
        try {
            cal.eval("1.01 2.00 3.00 + +");
        } catch (ExitException e) {
            e.printStackTrace();
        }
        System.out.println(os.toString());
        assertEquals("Stack:\n"
            + "6.0100000000 \n"
            + "Stack:\n"
            + "6.0100000000 \n"
            + "\n", os.toString());
    }

    public void testInSufficientOperands() {
        try {
            cal.eval("1.01 2.00 3.00 * * 1 + +");
        } catch (ExitException e) {
            e.printStackTrace();
        }

        assertEquals("operator + (position: 23): insufficient parametersStack:\n"
            + "7.0600000000 \n", os.toString());
    }
}
