/*	Omar Janouk
 *	CS435-004
 *	Proj 1 Part 2
 */

import java.util.*;

public class BinarySearchTreeSort {
	
	class Node {
        int key;
        Node left, right;
  
        public Node(int element) {
            key = element;
            left = right = null;
        }
    }
	
    Node root;
    
	//Define an array of integers to store the sorted elements
	static ArrayList<Integer> sortedArr = new ArrayList<Integer>();

    BinarySearchTreeSort() {
        root = null;
    }
    
//---------------------------------------------------------------------
    //Helper method to call insertRec()
    void insert(int key) {
    	root = insertRec(root, key);
    }
    
    //Function to insert an element into the tree
    Node insertRec(Node root, int value) {
    	if (root == null) {
    		root = new Node(value);
            return root;
    	}
    	if (value < root.key) {root.left = insertRec(root.left, value);}
        else if (value > root.key) {root.right = insertRec(root.right, value);}
    	return root;
    }
//---------------------------------------------------------------------

    //Helper function to insert full array into the tree
    void treeInsert(int arr[]) {
        for(int i = 0; i < arr.length; i++) {insert(arr[i]);}
    }

//---------------------------------------------------------------------
    
    //Function to do in-order traversal of BST
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            sortedArr.add(root.key);
            inorderRec(root.right);
        }
    }
//---------------------------------------------------------------------
    
    //Main function to test the methods
	public static void main(String[] args) {
		BinarySearchTreeSort tree = new BinarySearchTreeSort();
		
        int unsortedArr[] = {50, 40, 70, 20, 80, 100};
        
        tree.treeInsert(unsortedArr);
        
        tree.inorderRec(tree.root);
        
        System.out.println("Inorder traversal of the given tree");
        System.out.println(sortedArr);
        System.out.println("\n---------End Of Main------------");
	}

}
