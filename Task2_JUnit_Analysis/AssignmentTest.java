package task2;

import static org.junit.Assert.*;
import org.junit.Test;

public class AssignmentTest {

    @Test
    public void testProgram1() {
        // test: x=[2, 3, 5]; y=2; Expected = 0
        int[] x = {2, 3, 5};
        assertEquals("Program 1 failed to find element at index 0", 0, Program1.findLast(x, 2));
    }

    @Test
    public void testProgram2() {
        // test: x=[0, 1, 0]; Expected = 2
        int[] x = {0, 1, 0};
        assertEquals("Program 2 failed to find the LAST zero", 2, Program2.lastZero(x));
    }

    @Test
    public void testProgram3() {
        // test: x=[-4, 2, 0, 2]; Expected = 2
        int[] x = {-4, 2, 0, 2};
        assertEquals("Program 3 should only count positive numbers (>0)", 2, Program3.countPositive(x));
    }

    @Test
    public void testProgram4() {
        // test: x=[-3, -2, 0, 1, 4]; Expected = 3
        int[] x = {-3, -2, 0, 1, 4};
        assertEquals("Program 4 failed to count negative odd numbers", 3, Program4.oddOrPos(x));
    }
}