package BST;

import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {
    private class Node {
        T data;
        Node left, right;

        Node(T data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    private Node root;
    private ArrayList<T> traversalList;
    private int current; 
    
    public BinarySearchTree() {
        root = null;
        traversalList = new ArrayList<>();
        current = -1;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean isFull() {
        return false;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    public void clear() {
        root = null;
    }

    public T add(T element) {
        //int currentHeight = getHeight(); 
        root = addRecursive(root, element);
        //updateHeight(); 
        return element; 
    }

    private Node addRecursive(Node current, T element) {
        if (current == null) {
            return new Node(element);
        }

        if (element.compareTo(current.data) < 0) {
            current.left = addRecursive(current.left, element);
        } else if (element.compareTo(current.data) > 0) {
            current.right = addRecursive(current.right, element);
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    public int findHeight() {
        return findHeight(root); // call the recursive method starting from the root
    }

    private int findHeight(Node root) {
        if (root == null) {
            return 0; // of the node is null, height is 0
        } else {
            int leftHeight = findHeight(root.left); 	// calculate the height of the left subtree
            int rightHeight = findHeight(root.right); 	// calculate the height of the right subtree

            // compare the heights of the left and right subtrees, then return the maximum height
            return Math.max(leftHeight, rightHeight) + 1; // add 1 to account for the current node
        }
    }

    public boolean contains(T element) {
        return containsRecursive(root, element);
    }

    private boolean containsRecursive(Node current, T element) {
        if (current == null) {
            return false;
        }

        if (element.compareTo(current.data) == 0) {
            return true;
        }

        return element.compareTo(current.data) < 0
            ? containsRecursive(current.left, element)
            : containsRecursive(current.right, element);
    }

    public void remove(T element) {
        root = removeRecursive(root, element);
    }

    private Node removeRecursive(Node current, T element) {
        if (current == null) {
            return null;
        }

        if (element.compareTo(current.data) == 0) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            T smallestValue = findSmallestValue(current.right);
            current.data = smallestValue;
            current.right = removeRecursive(current.right, smallestValue);
            return current;
        }

        if (element.compareTo(current.data) < 0) {
            current.left = removeRecursive(current.left, element);
        } else {
            current.right = removeRecursive(current.right, element);
        }
        return current;
	    }
	    
	    public void removeOddNodesRecursive() {
	        root = removeOddNodes(root);
	    }
	
	    private Node removeOddNodes(Node node) {
	        if (node == null) {
	            return null;
	        }
	        
	        node.left = removeOddNodes(node.left);
	        node.right = removeOddNodes(node.right);
	
	        if (((Integer) node.data) % 2 != 0) {
	            return removeRecursive(node, node.data);
	        }
	
	        return node;
	    }
	    
	    // method to remove odd nodes from the original tree and create a separate tree with even values
	    public BinarySearchTree<T> removeOddAndCreateEvenBST() {
	        BinarySearchTree<T> evenBST = new BinarySearchTree<>();
	        removeOddAndAddEvenValues(root, evenBST);
	        return evenBST;
	    }

	    // recursive method to remove odd nodes and add even values to the new tree
	    private void removeOddAndAddEvenValues(Node node, BinarySearchTree<T> evenBST) {
	        if (node != null) {
	            if (((Integer) node.data) % 2 == 0) {
	                evenBST.add(node.data); // add even value to the new tree
	            }
	            removeOddAndAddEvenValues(node.left, evenBST);
	            removeOddAndAddEvenValues(node.right, evenBST);
	            if (((Integer) node.data) % 2 != 0) {
	                remove(node.data); // remove odd node from the original tree
	            }
	        }
	    }

    private T findSmallestValue(Node root) {
        return root.left == null ? root.data : findSmallestValue(root.left);
    }

    public void reset(int order) {
        traversalList.clear();
        current = 0;
        switch (order) {
            case 0: // Inorder
                inorderRecursive(root);
                break;
            case 1: // Preorder
                preorderRecursive(root);
                break;
            case 2: // Postorder
                postorderRecursive(root);
                break;
            default:
                throw new IllegalArgumentException("Invalid order: " + order);
        }
    }

    public T getNext(int order) {
        if (current >= 0 && current < traversalList.size()) {
            return traversalList.get(current++);
        }
        return null;
    }

    public void inorderTraversal() {
        inorderRecursive(root);
        System.out.println();
    }

    private void inorderRecursive(Node node) {
        if (node != null) {
            inorderRecursive(node.left);
            System.out.print(node.data + " ");
            inorderRecursive(node.right);
        }
    }

    public void postorderTraversal() {
        postorderRecursive(root);
        System.out.println();
    }

    private void postorderRecursive(Node node) {
        if (node != null) {
            postorderRecursive(node.left);
            postorderRecursive(node.right);
            System.out.print(node.data + " ");
        }
    }

    public void preorderTraversal() {
        preorderRecursive(root);
        System.out.println();
    }

    private void preorderRecursive(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderRecursive(node.left);
            preorderRecursive(node.right);
        }
    }

    public T findMax() {
        if (isEmpty()) {
            return null; 
        }
        Node current = root;
        while (current.right != null) {
            current = current.right; 
        }
        return current.data;
    }

    public T findMin() {
        if (isEmpty()) {
            return null; 
        }
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }
}
