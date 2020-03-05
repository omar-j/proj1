/*	Omar Janouk
 *	CS435-004
 *	Proj 1 Part 4
 */

public class AVLTreeIter {

	private Node root;

	class Node {
		int key, bf, height;
		Node left, right, parent;
		
		Node(int key, Node parent) {
			this.key = key;
			this.parent = parent;
		}
	}
//---------------------------------------------------------------------
	
	//return height of a node
	int height(Node node) {
		if (node == null) {return -1;}
		return node.height;
	}
	
	//set balance factor of a node
	void setBF(Node node) {
		updateHeight(node);
		node.bf = height(node.right) - height(node.left);
	}

	//update height of a node
	void updateHeight(Node node) {
		if (node != null)
			node.height = 1 + Math.max(height(node.left), height(node.right));
	}

//---------------------------------------------------------------------
	//helper function to rotate left
	Node rotateLeft(Node node) {

		Node tmp = node.right;
		tmp.parent = node.parent;
		node.right = tmp.left;
		if (node.right != null) {node.right.parent = node;}
		tmp.left = node;
		return getNode(node, tmp);
	}
//---------------------------------------------------------------------
	//helper function to rotate right
	Node rotateRight(Node node) {

		Node tmp = node.left;
		tmp.parent = node.parent;
		node.left = tmp.right;
		if (node.left != null) {node.left.parent = node;}
		tmp.right = node;
		return getNode(node, tmp);
	}
//---------------------------------------------------------------------
	//helper function to rotate left then right
	Node rotateLeftThenRight(Node node) {
		node.left = rotateLeft(node.left);
		return rotateRight(node);
	}
	
	//helper function to rotate right then left
	Node rotateRightThenLeft(Node node) {
		node.right = rotateRight(node.right);
		return rotateLeft(node);
	}
//---------------------------------------------------------------------
	//helper function to get link two nodes (parent-child relation) and return one node
	Node getNode(Node a, Node b) {
		a.parent = b;
		if (b.parent != null) {
			if (b.parent.right == a) {b.parent.right = b;}
			else {b.parent.left = b;}
		}
		setBF(a);
		setBF(b);
		return b;
	}
//---------------------------------------------------------------------
	//helper function to re-balance the tree
	void rebalance(Node n) {
		while (n != null) {
			setBF(n);
			if (n.bf == -2) {
				if (height(n.left.left) >= height(n.left.right)) {n = rotateRight(n);}
				else {n = rotateLeftThenRight(n);}
			}
			else if (n.bf == 2) {
				if (height(n.right.right) >= height(n.right.left)) {n = rotateLeft(n);}
				else {n = rotateRightThenLeft(n);}
			}
			if (n.parent != null) {n = n.parent;}
			else {break;}
		}
		root = n;
	}
//---------------------------------------------------------------------

	void AVLInsertIter(int value) {
		if (root == null) {
			root = new Node(value, null);
			return;
		}
		Node currNode = root;
		while (true) {
			Node parent = currNode;
			boolean toLeft = currNode.key > value;
			currNode = toLeft ? currNode.left : currNode.right;
			if (currNode == null) {
				if (toLeft) {parent.left = new Node(value, parent);}
				else {parent.right = new Node(value, parent);}
				rebalance(parent);
				break;
			}
		}
	}
//---------------------------------------------------------------------
	void AVLDeleteIter(int value) {
		if (root == null) {return;}
		Node currNode = root;
		while (currNode != null) {
			Node node = currNode;
			boolean y = (value >= node.key);
			currNode = y ? node.right : node.left;
			if (value == node.key) {
				AVLDelete(node);
			}
		}
	}
	
	//helper function to delete node by its value
	void AVLDelete(Node node) {
		if (node.left == null && node.right == null) {
			if (node.parent == null) {root = null;}
			else {
				Node parent = node.parent;
				if (parent.left == node) {parent.left = null;}
				else {parent.right = null;}
				rebalance(parent);
			}
			return;
		}

		if (node.left != null) {
			Node child = node.left;
			while (child.right != null) child = child.right;
			node.key = child.key;
			AVLDelete(child);
		}
		else {
			Node child = node.right;
			while (child.left != null) child = child.left;
			node.key = child.key;
			AVLDelete(child);
		}
	}

//---------------------------------------------------------------------
	//---------------------------------------------------------------------
    Node findMinIter(Node root) {
    	while (root.left != null) {root = root.left;}
    	return root;
    }

    Node findMaxIter(Node root) {
    	while (root.right != null) {root = root.right;}
    	return root;
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
				if (root.left!= null) {prec = findMaxIter(root.left);}
				break;
			}
		}
		return prec;
    }
		
//---------------------------------------------------------------------


    //THIS FUNCTION IS FOR TESTING PURPOSES ONLY.. NOT PART OF THE ASSIGNMENT
    //Helper function to insert full array into the tree
	public void treeInsert(int[] items) {
		for (int item : items) {
			AVLInsertIter(item);
		}
	}
	
    //THIS FUNCTION IS FOR TESTING PURPOSES ONLY.. NOT PART OF THE ASSIGNMENT
    //Helper function to call InorderRec() 
    void inorder()  {
       inorderRec(root);
    }
    //THIS FUNCTION IS FOR TESTING PURPOSES ONLY.. NOT PART OF THE ASSIGNMENT
    //Function to do in-order traversal of BST 
    void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left); 
            System.out.print(node.key + " "); 
            inorderRec(node.right); 
        }
    }

    //THIS FUNCTION IS FOR TESTING PURPOSES ONLY.. NOT PART OF THE ASSIGNMENT
    //Function to do preorder traversal of BBST 
    void preOrderRec(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrderRec(node.left);
            preOrderRec(node.right);
        }
    }
    
//---------------------------------------------------------------------
    //THIS FUNCTION IS FOR TESTING PURPOSES ONLY.. NOT PART OF THE ASSIGNMENT
    //Main function to test the methods
    public static void main(String[] args) { 
    	AVLTreeIter tree = new AVLTreeIter();
        int arr[] = {10, 20, 30, 40, 50, 25, 12, 14, 18, 17, 33, 34};

        tree.treeInsert(arr);
        
        System.out.println("Inorder traversal of the given tree:"); 
        tree.preOrderRec(tree.root); 
        System.out.println("\n------------------------------------");

        tree.AVLDeleteIter(17);
        tree.preOrderRec(tree.root); 
        System.out.println("\n------------------------------------");
    }
}
