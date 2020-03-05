/*	Omar Janouk
 *	CS435-004
 *	Proj 1 Part 6
 */

public class ModifiedBinarySearchTreeIter {
	
	static int traverseCounter=0;

	class Node {
        int key;
        Node left, right;
        public Node(int element) {
            key = element;
            left = right = null;
        }
    }
	
    Node root;
    ModifiedBinarySearchTreeIter() {
        root = null;
    }
    
    void insert(int key) {
    	root = insertIter(root, key);
    }
    
    Node insertIter(Node root, int value) {
    	Node curr = root;
       	Node parent = null;
    	if (root == null) {return new Node(value);}
    	while (curr != null) {
    		traverseCounter++;
    		parent = curr;
    		if (value < curr.key) {curr = curr.left;}
    		else {curr = curr.right;}
    	}
    	if (value < parent.key) {parent.left = new Node(value);}
    	else {parent.right = new Node(value);}
    	return root;
    }
    
    void inorder()  {
       inorderRec(root);
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left); 
            System.out.print(root.key + " "); 
            inorderRec(root.right); 
        }
    }
    void treeInsert(int arr[]) {
        for(int i = 0; i < arr.length; i++) {insert(arr[i]);}
    }
    
    int getTraverseCounter() {
    	return traverseCounter;
    }

}
