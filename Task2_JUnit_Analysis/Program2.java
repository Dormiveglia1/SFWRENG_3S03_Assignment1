package task2;

public class Program2 {
	/**
	 * Finds last index of zero
	 * 
	 * @param x array to search
	 *
	 * @return last index of 0 in x; -1 if there is no zero
	 * @throws NullPointerException if x is null
	 */
	 public static int lastZero (int[] x)
	 {
		 for (int i = 0; i < x.length; i++)
		 {
			 if (x[i] == 0)
			 {
				 return i;
			 }
		 }
		 return -1;
	 }
	// test: x=[0,1,0]; Expected = 2
}
