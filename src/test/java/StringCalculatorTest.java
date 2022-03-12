package test.java;

import main.java.StringCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    StringCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new StringCalculator();
    }

    @Test
    public void testAddString() {
        assertEquals(0, calculator.Add(""));
        assertEquals(1, calculator.Add("1"));
        assertEquals(3, calculator.Add("1,2"));
    }

    @Test
    public void testAddStringUnknownNumber() {
        assertEquals(150, calculator.Add("110,10,20,10"));
    }

    @Test
    public void testAddHandlesNewlineSeparator() {
        assertEquals(6, calculator.Add("1\n2,3"));
    }

    @Test
    public void testAddHandlesDelimiters() {
        assertEquals(3, calculator.Add("//;\n1;2"));
    }

    @Test
    public void testNegativeNumberException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.Add("-5,2"));
    }
}
