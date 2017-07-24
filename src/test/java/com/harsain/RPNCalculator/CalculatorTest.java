package com.harsain.RPNCalculator;

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

    public void testNumberInput() {
        cal.eval("1 1");
        String expectedString = "Stack:\n" +
                "1.0000000000 1.0000000000 \n";

        assertEquals(expectedString, os.toString());
    }

    public void testWithInvalidInput(){
        cal.eval("1.09875984 tesing");
        System.out.println(os.toString());
        assertEquals("Stack:\n"
            + "1.0987598400 \n"
            + "Stack:\n"
            + "1.0987598400 \n"
            + "\n", os.toString());
    }
}
