/*	Omar Janouk
 *	CS435-004
 *	Proj 1 Part 1
 */

public class BinarySearchTreeIter {
	class Node {
        int key;
        Node left, right;
  
        public Node(int element) {
            key = element;
            left = right = null;
        }
    }
	
    Node root;
    
    BinarySearchTreeIter() {
        root = null;
    }
    
//---------------------------------------------------------------------
    //Helper function to call insertIter() 
    void insert(int key) {
    	root = insertIter(root, key);
    }
    
    //---------------------------------------------
    Node insertIter(Node root, int value) {
    	Node curr = root;
       	Node parent = null;
    	if (root == null) {return new Node(value);}
    	while (curr != null) {
    		parent = curr;
    		if (value < curr.key) {curr = curr.left;}
    		else {curr = curr.right;}
    	}
    	if (value < parent.key) {parent.left = new Node(value);}
    	else {parent.right = new Node(value);}
    	return root;
    }
    
//---------------------------------------------------------------------
    //Helper function to call deleteIter() 
    void delete(int key) {
    	root = deleteIter(root, key); 
    }
    
    //----------------------------------------------
    Node deleteIter(Node root, int value) {
    	
    	Node parent = null;
    	Node current = root;
    	boolean hasLeft = false;
    	if (root == null) {return root;}
    	
    	while (current != null) {
    		if (current.key == value) {break;}
    		parent = current;
    		if (value < current.key) {
    			hasLeft = true;
    			current = current.left;	
    		}
    		else {
    			hasLeft = false;
    			current = current.right;
    		}
    	}

    	if (parent == null) {return deleteNode(current);}
    	if (hasLeft) {parent.left = deleteNode(current);}
    	else {parent.right = deleteNode(current);}
    	return root;
    }
    //Helper function to delete a node
    Node deleteNode(Node node) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                return null;
            }

            if (node.left != null && node.right != null) {
                Node inOrderSuccessor = deleteInOrderSucc(node);
                node.key = inOrderSuccessor.key;
            }
            else if (node.left != null) {node = node.left;}
            else {node = node.right;}
        }
        return node;
    }
    //Helper function to delete In Order Successor
    Node deleteInOrderSucc(Node node) {
        Node parent = node;
        node = node.right;
        boolean rightChild = node.left == null;

        while (node.left != null) {
            parent = node;
            node = node.left;
        }
        if (rightChild) {parent.right = node.right;}
        else {parent.left = node.right;}
        node.right = null;
        return node;
    }
    
//---------------------------------------------------------------------
    Node findMinIter(Node root) {
    	while (root.left != null) {root = root.left;}
    	return root;
    }

    Node findMaxRec(Node root) {
        if (root.right == null) {return root;}
        return findMaxRec(root.right);
    }

//---------------------------------------------------------------------
    //Helper function to call findNextIter()
    int nextIter(int key) {
    	Node next = findNextIter(root, key);
    	return next.key;
    }

    //----------------------------------------------
    Node findNextIter(Node root, int value) {
		if (root == null) {return null;}
		Node next = null;
		Node curr = root;
		while (curr != null && curr.key !=value) {
			if(curr.key > value) {
				next = curr;
				curr = curr.left;
			}
			else {curr = curr.right;}
		}
		if (curr == null) {return null;}
		if (curr.right==null) {return next;}
		curr = curr.right;
		while(curr.left!=null) {curr = curr.left;}
		return curr;
	}
    
//---------------------------------------------------------------------
    //Helper function to call findPrevIter()
    int prevIter(int key) {
    	Node prev = findPrevIter(root, key);
    	return prev.key;
    }

    //---------------------------------------------------
    Node findPrevIter(Node root, int value) {
		if (root == null) {return null;}
		Node prec = null;
		while (true) {
			if (value < root.key) {root = root.left;}
			else if (value > root.key) {
				prec = root;
				root = root.right;
			}
			else {
				if (root.left!= null) {prec = findMaxRec(root.left);}
				break;
			}
		}
		return prec;
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
		BinarySearchTreeIter tree = new BinarySearchTreeIter(); 

        int arr[] = {60, 30, 50, 40, 70, 90, 20, 80, 100};
        
        tree.treeInsert(arr);
        
        System.out.println("Inorder traversal of the given tree"); 
        tree.inorder();
        
        System.out.println("Next of 70 is: "+tree.nextIter(70));
        System.out.println("Prev of 70 is: "+tree.prevIter(70));

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
        
        System.out.println("Next of 90 is: "+tree.nextIter(90));
        System.out.println("Prev of 90 is: "+tree.prevIter(90));

        System.out.println("\n---------End Of Main------------");
	}
}
