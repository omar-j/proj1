/*	Omar Janouk
 *	CS435-004
 *	Proj 1 Part 6
 */

import java.util.ArrayList;
import java.util.Random;

public class CompareImplementations {
	
	static ArrayList<Integer> getRandomArray(int n) {
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
	    Random random = new Random();
	    
	    for (int i = 0; i < n; i++) {
	    	int num = random.nextInt(2000000);
	    	if (!arr.contains(num)) {arr.add(num);}
	    	else {--i;}
	    }
	   return arr;
	}

	static ArrayList<Integer> getSortedArray(int n) {
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
	    int num = n;
	    for (int i = 0; i < n; i++) {
	    	arr.add(num);
	    	num--;
	    }
	   return arr;
	}

	//--------------------------------------------------------------------------------------
    public static void main(String[] args) {
    	
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		ModifiedBinarySearchTreeIter BSTiter = new ModifiedBinarySearchTreeIter(); 
		ModifiedAVLTreeIter AVLiter = new ModifiedAVLTreeIter();
		
		
		array = getRandomArray(10000);
		int[] randArr = new int[array.size()];
        for (int i=0; i< array.size(); i++) {
        	randArr[i] = array.get(i);
        }
        
        System.out.println("Random Array:");
        System.out.println("Inserting into BinarySearchTreeIter...");
        BSTiter.treeInsert(randArr);
		int BSTtraverseCount = BSTiter.getTraverseCounter();
        //BSTiter.inorder();
        System.out.println("Constructiong BinarySearchTreeIter is completed.");
        System.out.println("BST traverse count is: " + BSTtraverseCount);        
        System.out.println("----------------------------------------------");     
        
        System.out.println("Inserting into AVLTreeIter...");
        AVLiter.treeInsert(randArr);
		int AVLtraverseCount = AVLiter.getTraverseCounter();
        //AVLiter.inorder();
        System.out.println("Constructiong AVLTreeIter is completed.");
        System.out.println("AVL traverse count is: " + AVLtraverseCount);        
        System.out.println("----------------------------------------------");     
        System.out.println("----------------------------------------------");     
        
		array = getSortedArray(10000);
		int[] sortedArr = new int[array.size()];
        for (int i=0; i< array.size(); i++) {
        	sortedArr[i] = array.get(i);
        }
        
        System.out.println("Sorted Array:");
        System.out.println("Inserting into BinarySearchTreeIter...");
        BSTiter.treeInsert(sortedArr);
		BSTtraverseCount = BSTiter.getTraverseCounter();
        //BSTiter.inorder();
        System.out.println("Constructiong BinarySearchTreeIter is completed.");
        System.out.println("BST traverse count is: " + BSTtraverseCount);        
        System.out.println("----------------------------------------------");     
        
        System.out.println("Inserting into AVLTreeIter...");
        AVLiter.treeInsert(sortedArr);
		AVLtraverseCount = AVLiter.getTraverseCounter();
        //AVLiter.inorder();
        System.out.println("Constructiong AVLTreeIter is completed.");
        System.out.println("AVL traverse count is: " + AVLtraverseCount);        
        System.out.println("----------------------------------------------");
	}
}
/*

OUTPUT:

Random Array:
Inserting into BinarySearchTreeIter...
Constructiong BinarySearchTreeIter is completed.
BST traverse count is: 150917
----------------------------------------------
Inserting into AVLTreeIter...
Constructiong AVLTreeIter is completed.
AVL traverse count is: 120929
----------------------------------------------
----------------------------------------------
Sorted Array:
Inserting into BinarySearchTreeIter...
Constructiong BinarySearchTreeIter is completed.
BST traverse count is: 2489403
----------------------------------------------
Inserting into AVLTreeIter...
Constructiong AVLTreeIter is completed.
AVL traverse count is: 281661
----------------------------------------------
*/
