/*	Omar Janouk
 *	CS435-004
 *	Proj 1 Part 6
 */

public class ModifiedAVLTreeIter {
	Node root;
	static int traverseCounter = 0;
	
	class Node {
		int key, bf, height;
		Node left, right, parent;
		
		Node(int key, Node parent) {
			this.key = key;
			this.parent = parent;
		}
	}

	int height(Node node) {
		if (node == null) {return -1;}
		return node.height;
	}
	
	void setBF(Node node) {
		updateHeight(node);
		node.bf = height(node.right) - height(node.left);
	}

	void updateHeight(Node node) {
		if (node != null)
			node.height = 1 + Math.max(height(node.left), height(node.right));
	}

	Node rotateLeft(Node node) {

		Node tmp = node.right;
		tmp.parent = node.parent;
		node.right = tmp.left;
		if (node.right != null) {node.right.parent = node;}
		tmp.left = node;
		return getNode(node, tmp);
	}

	Node rotateRight(Node node) {

		Node tmp = node.left;
		tmp.parent = node.parent;
		node.left = tmp.right;
		if (node.left != null) {node.left.parent = node;}
		tmp.right = node;
		return getNode(node, tmp);
	}

	Node rotateLeftThenRight(Node node) {
		node.left = rotateLeft(node.left);
		return rotateRight(node);
	}
	
	Node rotateRightThenLeft(Node node) {
		node.right = rotateRight(node.right);
		return rotateLeft(node);
	}

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

	void AVLInsertIter(int value) {
		if (root == null) {
			root = new Node(value, null);
			return;
		}
		Node currNode = root;
		while (true) {
			Node parent = currNode;
			traverseCounter++;
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

	public void treeInsert(int[] items) {
		for (int item : items) {
			AVLInsertIter(item);
		}
	}
	
    void inorder()  {
       inorderRec(root);
    }

    void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left); 
            System.out.print(node.key + " "); 
        inorderRec(node.right); 
        }
    }
    
    int getTraverseCounter() {
    	return traverseCounter;
    }
}
