package task4;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest_Improved {
	@Test(expected = ArithmeticException.class)
	public void testDivideByZero() {
	    Calculator_Improved calc = new Calculator_Improved();
	    calc.divide(10.0, 0.0); 
	}

	@Test
	public void testPrecision() {
		Calculator_Improved calc = new Calculator_Improved();
	    // Verification of precision with delta
	    assertEquals(3.3333, calc.divide(10.0, 3.0), 0.0001);
	}
}