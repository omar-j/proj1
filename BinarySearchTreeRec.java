/*	Omar Janouk
 *	CS435-004
 *	Proj 1 Part 1
 */

public class BinarySearchTreeRec {
	class Node {
        int key;
        Node left, right;
  
        public Node(int element) {
            key = element;
            left = right = null;
        }
    }
	
    Node root;
    
    BinarySearchTreeRec() {
        root = null;
    }
    
//---------------------------------------------------------------------
    //Helper function to call insertRec() 
    void insert(int key) {
    	root = insertRec(root, key);
    }
    
    //----------------------------------------
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
    //Helper function to call deleteRec() 
    void delete(int key) {
        root = deleteRec(root, key); 
    }
    
    //--------------------------------------------
    Node deleteRec(Node root, int value) {
    	if (root == null) {
    		return root; 
    	}
    	
    	if (value < root.key) {root.left = deleteRec(root.left, value);}
        else if (value > root.key) {root.right = deleteRec(root.right, value);}
  
        //If key is same as root's key, then this is the node to be deleted
        else {
        	// node with only one child or no child 
            if (root.left == null) {return root.right;}
            else if (root.right == null) {return root.left;}
            
            // node with two children: Get smallest in the right subtree
            root.key = findMinRec(root.right).key; 
  
            // Delete the in order successor 
            root.right = deleteRec(root.right, root.key); 
        }
        return root;
    }
    
//---------------------------------------------------------------------
    Node findMinRec(Node root) {
    	if (root.left == null) {return root;}
    	return findMinRec(root.left);
    }

    Node findMaxRec(Node root) {
        if (root.right == null) {return root;}
        return findMaxRec(root.right);
    }

//---------------------------------------------------------------------
    //Helper function to call findNextRec() 
    int nextRec(int key) {
    	Node next = findNextRec(root, null, key);
    	return next.key;
    }
    
    //-----------------------------------------------------
    Node findNextRec(Node root, Node succ, int key) {
		if (root == null) {
			return null;
		}

		if (root.key == key) {
			if (root.right != null) {return findMinRec(root.right);}
		}
		
		// if given key is less than the root node, recur for left subtree
		else if (key < root.key) {
			succ = root;
			return findNextRec(root.left, succ, key);
		}

		// if given key is more than the root node, recur for right subtree
		else {return findNextRec(root.right, succ, key);}
		
		return succ;
	}
    
//---------------------------------------------------------------------
    //Helper function to call findPrevRec() 
    int prevRec(int key) {
    	Node prev = findPrevRec(root, null, key);
    	return prev.key;
    }
    
    //---------------------------------------------------
    Node findPrevRec(Node root, Node succ, int key) {
		if (root == null) {
			return null;
		}

		if (root.key == key) {
			if (root.left != null) {return findMaxRec(root.left);}
		}
		
		// if given key is less than the root node, recur for left subtree
		else if (key < root.key) {
			return findPrevRec(root.left, succ, key);
		}

		// if given key is more than the root node, recur for right subtree
		else {
			succ = root;
			return findPrevRec(root.right, succ, key);}
		
		return succ;
	}

//---------------------------------------------------------------------
    //THIS FUNCTION IS FOR TESTING PURPOSES ONLY.. NOT PART OF THE ASSIGNMENT
    //Helper function to call InorderRec() 
    void inorder()  {
       inorderRec(root);
    }
    //THIS FUNCTION IS FOR TESTING PURPOSES ONLY.. NOT PART OF THE ASSIGNMENT
    //Function to do in-order traversal of BST 
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left); 
            System.out.println(root.key); 
            inorderRec(root.right); 
        }
    }
    //THIS FUNCTION IS FOR TESTING PURPOSES ONLY.. NOT PART OF THE ASSIGNMENT
    //Helper function to insert full array into the tree
    void treeInsert(int arr[]) {
        for(int i = 0; i < arr.length; i++) {insert(arr[i]);}
    }
    
//---------------------------------------------------------------------
    //THIS FUNCTION IS FOR TESTING PURPOSES ONLY.. NOT PART OF THE ASSIGNMENT
    //Main function to test the methods
	public static void main(String[] args) {
		BinarySearchTreeRec tree = new BinarySearchTreeRec(); 

        int arr[] = {60, 30, 50, 40, 70, 90, 20, 80, 100};
        
        tree.treeInsert(arr);
        
        System.out.println("Inorder traversal of the given tree"); 
        tree.inorder();
        
        System.out.println("Next of 70 is: "+tree.nextRec(70));
        System.out.println("Prev of 70 is: "+tree.prevRec(70));

        System.out.println("\nDelete 20"); 
        tree.delete(20); 
        System.out.println("Inorder traversal of the modified tree"); 
        tree.inorder(); 
  
        System.out.println("\nDelete 30"); 
        tree.delete(30); 
        System.out.println("Inorder traversal of the modified tree"); 
        tree.inorder(); 
  
        System.out.println("\nDelete 50"); 
        tree.delete(50);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();
        
        System.out.println("Next of 90 is: "+tree.nextRec(90));
        System.out.println("Prev of 90 is: "+tree.prevRec(90));

        System.out.println("\n---------End Of Main------------");
	}

}
