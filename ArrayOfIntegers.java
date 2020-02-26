/*	Omar Janouk
*	CS435-004
*	Proj 1 Part 3
*/

import java.util.*;

public class ArrayOfIntegers {
	
	//Function to outputs an array of random distinct integers of size n
	static ArrayList<Integer> getRandomArray(int n) {
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
	    Random random = new Random();
	    
	    for (int i = 0; i < n; i++) {
	    	int num = random.nextInt(50);
	    	if (!arr.contains(num)) {arr.add(num);}
	    	else {--i;}
	    }
	   return arr;
	}

//---------------------------------------------------------------------
	    
	//Function to outputs an array of sorted integers (n to 1) of size n
	static ArrayList<Integer> getSortedArray(int n) {
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
	    int num = n;
	    for (int i = 0; i < n; i++) {
	    	arr.add(num);
	    	num--;
	    }
	   return arr;
	}
	
//---------------------------------------------------------------------

	//Main function to test the methods
	public static void main(String[] args) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		int n = 20;
		
		array = getRandomArray(n);
		System.out.println("Array of random distinct integers of size " + n);
		System.out.println(array);
		
		System.out.println();
		
		array = getSortedArray(n);
		System.out.println("Array of sorted integers (n to 1) of size " + n);
		System.out.println(array);
		
		System.out.println("\n---------End Of Main------------");
	}
}
