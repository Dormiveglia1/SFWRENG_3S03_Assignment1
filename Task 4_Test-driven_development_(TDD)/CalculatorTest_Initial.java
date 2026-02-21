package task4;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest_Initial {
    @Test
    public void testStandardDivision() {
        Calculator_Initial calc = new Calculator_Initial();
        // Test 10 / 2 = 5.0
        assertEquals(5.0, calc.divide(10.0, 2.0), 0.001);
    }

    @Test
    public void testNegativeDivision() {
        Calculator_Initial calc = new Calculator_Initial();
        // Test -6 / 3 = -2.0
        assertEquals(-2.0, calc.divide(-6.0, 3.0), 0.001);
    }
}
