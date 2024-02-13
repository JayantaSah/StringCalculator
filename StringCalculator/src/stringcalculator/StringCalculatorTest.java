package stringcalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    @Test
    public void emptyStringReturnsZero() {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    public void singleNumberReturnsNumber() {
        assertEquals(1, StringCalculator.add("1"));
    }

    @Test
    public void twoNumbersCommaDelimitedReturnsSum() {
        assertEquals(3, StringCalculator.add("1,2"));
    }

    @Test
    public void multipleNumbersCommaDelimitedReturnsSum() {
        assertEquals(6, StringCalculator.add("1,2,3"));
    }

    @Test
    public void newLineAsDelimiterReturnsSum() {
        assertEquals(6, StringCalculator.add("1\n2,3"));
    }

    @Test
    public void customDelimiterSpecifiedReturnsSum() {
        assertEquals(3, StringCalculator.add("//;\n1;2"));
    }

    @Test
    public void negativeNumberThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> StringCalculator.add("-1,2"));
        assertEquals("negative numbers not allowed: -1", exception.getMessage());
    }

    @Test
    public void multipleNegativeNumbersThrowExceptionWithAllNumbers() {
        Exception exception = assertThrows(RuntimeException.class, () -> StringCalculator.add("-1,2,-3"));
        assertEquals("negative numbers not allowed: -1, -3", exception.getMessage());
    }

    @Test
    public void numbersGreaterThan1000AreIgnored() {
        assertEquals(2, StringCalculator.add("1001,2"));
    }
}
