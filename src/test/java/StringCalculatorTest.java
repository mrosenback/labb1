import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StringCalculatorTest {

    StringCalculator calculator;

    @Mock
    StringCalculator mockCalculator;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    void setUp() {
        calculator = new StringCalculator();
        mockCalculator = new StringCalculator();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddString() {
        Assertions.assertEquals(0, calculator.Add(""));
        Assertions.assertEquals(1, calculator.Add("1"));
        Assertions.assertEquals(3, calculator.Add("1,2"));
    }

    @Test
    public void testAddStringUnknownNumber() {
        Assertions.assertEquals(150, calculator.Add("110,10,20,10"));
    }

    @Test
    public void testAddHandlesNewlineSeparator() {
        Assertions.assertEquals(6, calculator.Add("1\n2,3"));
    }

    @Test
    public void testAddHandlesDelimiters() {
        Assertions.assertEquals(3, calculator.Add("//;\n1;2"));
    }

    @Test
    public void testNegativeNumberException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.Add("-5,2"));
    }

    @Test
    public void testBigNumberLog() {
        Mockito.when(mockCalculator.Add("1100,400")).thenReturn(1500);
        Assertions.assertEquals(1500,mockCalculator.Add("1100,400"));
        Mockito.verify(mockCalculator).Add("1100,400");
    }

    @Test
    public void testWelcomeMessage() {
        System.setOut(new PrintStream(outputStreamCaptor));
        StringCalculator.WelcomeMessage("Welcome to String Calculator!\nInput some numbers with delimiters to calculate the sum.");

        assertEquals("Welcome to String Calculator!\nInput some numbers with delimiters to calculate the sum.", outputStreamCaptor.toString().trim());

        System.setOut(standardOut);
    }

    @Test
    public void testUserInput() {
        String input = "1,2,3";

        Assertions.assertEquals("1,2,3\n", calculator.UserInput(new Scanner(input), System.out));
    }

    @Test
    public void testMultipleInputs() {
        String input1 = "1,2,3";
        String input2 = "2,3,4";
        String sumInput = calculator.UserInput(new Scanner(input1), System.out) + calculator.UserInput(new Scanner(input2), System.out);
        int result;

        result = calculator.Add(sumInput);

        assertEquals(15, result);
    }

    @Test
    public void testComplexInput() {
        String input = "//[***][%%%]\n1***2%%%4";
        int result;

        result = calculator.Add(input);

        assertEquals(7, result);
    }
}
