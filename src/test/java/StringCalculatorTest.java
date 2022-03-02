import org.junit.jupiter.api.Assertions;
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
    public void testAddStringBigNumber() {
        assertEquals(150, calculator.Add("10,20,30,40,50"));
    }

    @Test
    public void testSplitNewLine() {
        assertEquals(6, calculator.Add("1\n2,3"));
    }

    @Test
    public void testSplitDelimiters() {
        assertEquals(3, calculator.Add("//;\n1;2"));
    }

    @Test
    public void testNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> calculator.Add("-5,2"));
        assertEquals("Negatives not allowed -3", exception.getMessage());
    }
}
