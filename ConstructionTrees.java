/*	Omar Janouk
 *	CS435-004
 *	Proj 1 Part 5
 */

import java.util.*;

public class ConstructionTrees {

	static ArrayList<Integer> getRandomArray(int n) {
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
	    Random random = new Random();
	    
	    for (int i = 0; i < n; i++) {
	    	int num = random.nextInt(20000000);
	    	if (!arr.contains(num)) {arr.add(num);}
	    	else {--i;}
	    }
	   return arr;
	}

    public static void main(String[] args) {
    	
		ArrayList<Integer> array = new ArrayList<Integer>();
		BinarySearchTreeRec BSTrec = new BinarySearchTreeRec(); 
		BinarySearchTreeIter BSTiter = new BinarySearchTreeIter(); 
		AVLTreeIter AVLiter = new AVLTreeIter();
		
		array = getRandomArray(10000);
		int[] arr = new int[array.size()];
        for (int i=0; i< array.size(); i++) {
        	arr[i] = array.get(i);
        }

        System.out.println("Inserting into BinarySearchTreeRec...");
        long startTimeBSTrec = System.nanoTime();
        BSTrec.treeInsert(arr);
        long endTimeBSTrec = System.nanoTime();
        System.out.println("Constructiong BinarySearchTreeRec is completed.");
        long BSTrecDuration = (endTimeBSTrec - startTimeBSTrec)/1000000;
        System.out.println("duration in ms: " + BSTrecDuration);        
        System.out.println("----------------------------------------------");
        
        System.out.println("Inserting into AVLTreeIter...");
        long startTimeAVLiter = System.nanoTime();
        AVLiter.treeInsert(arr);
        long endTimeAVLiter = System.nanoTime();
        System.out.println("Constructiong AVLTreeIter is completed.");
        long AVLiterDuration = (endTimeAVLiter - startTimeAVLiter)/1000000;
        System.out.println("duration in ms: " + AVLiterDuration);        
        System.out.println("----------------------------------------------");
        
        System.out.println("Inserting into BinarySearchTreeIter...");
        long startTimeBSTiter = System.nanoTime();
        BSTiter.treeInsert(arr);
        long endTimeBSTiter = System.nanoTime();
        System.out.println("Constructiong BinarySearchTreeIter is completed.");
        long BSTiterDuration = (endTimeBSTiter - startTimeBSTiter)/1000000;
        System.out.println("duration in ms: " + BSTiterDuration);
        System.out.println("----------------------------------------------");
	}
}
