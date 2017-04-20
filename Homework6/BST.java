import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents a binary search tree.
 * AndrewID: jiamingx
 * @author Jiaming Xiao
 * @param <T> anytype
 */
public class BST<T extends Comparable<T>> implements Iterable<T>, BSTInterface<T> {
    /**
     * Root of the binary tree.
     */
    private Node<T> root;

    /**
     * Comparator deployed to compare elements of the tree.
     */
    private Comparator<T> comparator;

    /**
     * No-arg constructor.
     */
    public BST() {
        this(null);
    }

    /**
     * Constructor with arguments.
     * @param comp the comparator
     */
    public BST(Comparator<T> comp) {
        comparator = comp;
        root = null;
    }

    /**
     * Get comparator.
     * @return the comparator deployed in the BST
     */
    public Comparator<T> comparator() {
        return comparator;
    }

    /**
     * Get root of the BST.
     * @return root of the BST
     */
    public T getRoot() {
        if (root == null) {
            return null;
        }
        Node<T> temp = new Node<T>(root.data, null, null);
        return temp.data;
    }

    /**
     * Get height of the BST.
     * @return height of the BST
     */
    public int getHeight() {
        if (root == null) {
            return 0;
        }
        return getHeightHelper(root) - 1;
    }

    /**
     * Get height helper.
     * @param current current node
     * @return height of subtrees
     */
    private int getHeightHelper(Node<T> current) {
        int leftSubTree = 0; // height of left subtree
        int rightSubTree = 0; // height of right subtree

        if (current == null) { // Base case
            return 0;
        }
        leftSubTree = getHeightHelper(current.left); // Recursive case 1
        rightSubTree = getHeightHelper(current.right); // Recursive case 2
        return Math.max(leftSubTree, rightSubTree) + 1;
    }

    /**
     * Get number of nodes in the binary search tree.
     * @return the num of nodes in the binary search tree
     */
    public int getNumberOfNodes() {
        if (root == null) {
            return 0;
        }
        return getNumberOfNodesHelper(root);
    }

    /**
     * Get number of nodes helper.
     * @param current current node
     * @return num of nodes in the subtree
     */
    private int getNumberOfNodesHelper(Node<T> current) {
        int leftSubTree = 0; // node number of left subtree
        int rightSubTree = 0; // node number of right subtree

        if (current.left == null && current.right == null) { // Base case
            return 1;
        }

        if (current.left != null) {  // Recursive case 1
            leftSubTree = getNumberOfNodesHelper(current.left);
        }

        if (current.right != null) { // Recusive case 2
            rightSubTree = getNumberOfNodesHelper(current.right);
        }

        return leftSubTree + rightSubTree + 1;

    }

    /**
     * Search a specific word in the BST.
     * @param toSearch the key to search in the BST
     */
    @Override
    public T search(T toSearch) {
        if (toSearch == null) { // Edge case
            return null;
        }

        Node<T> result = searchHelper(root, toSearch);
        if (result == null) {
            return null;
        }
        return result.data;
    }

    /**
     * Search Helper.
     * @param current current node
     * @param target target word
     * @return the node find in search process
     */
    private Node<T> searchHelper(Node<T> current, T target) {

        if (comparator != null) { // When passing defined comparator
            if (current == null || comparator.compare(current.data, target) == 0) { // Base case
                return current;
            }

            if (comparator.compare(current.data, target) < 0) { // Recursive case 1
                return searchHelper(current.right, target);
            } else {                                 // Recursive case 2
                return searchHelper(current.left, target);
            }
        } else { // Natural order
            if (current == null || current.data.compareTo(target) == 0) { // Base case
                return current;
            }

            if (current.data.compareTo(target) < 0) { // Recursive case 1
                return searchHelper(current.right, target);
            } else {                                 // Recursive case 2
                return searchHelper(current.left, target);
            }
        }
    }

    /**
     * Insert a word into the BST.
     * @param toInsert the word to insert
     */
    @Override
    public void insert(T toInsert) {
        if (toInsert == null) { // Edge case 1
            return;
        }

        if (root == null) { // Edge case 2
            root = new Node<T>(toInsert);
            return;
        }
        insertHelper(root, root, toInsert, false);
        return;
    }

    /**
     * Insert helper.
     * @param parent parent node
     * @param current current node
     * @param insertVal the word needs to insert
     * @param isLeftChild flag to indicate left or right child
     */
    private void insertHelper(Node<T> parent, Node<T> current, T insertVal, boolean isLeftChild) {
        if (comparator != null) { // When defined comparator is deployed
            if (current != null && parent.data.equals(insertVal)) { // Base case 1
                return;
            } else if (current == null && isLeftChild) {             // Base case 2
                parent.left = new Node<T>(insertVal);
                return;
            } else if (current == null && !isLeftChild) {     // Base case 3
                parent.right = new Node<T>(insertVal);
                return;
            }

            if (comparator.compare(current.data, insertVal) <= 0) {  // Recursive case
                insertHelper(current, current.right, insertVal, false);
            } else {
                insertHelper(current, current.left, insertVal, true);
            }
        } else { // Natural order
            if (parent != null && parent.data.equals(insertVal)) { // Base case 1
                return;
            } else if (current == null && isLeftChild) {             // Base case 2
                parent.left = new Node<T>(insertVal);
                return;
            } else if (current == null && !isLeftChild) {     // Base case 3

                parent.right = new Node<T>(insertVal);
                return;
            }

            if (current.data.compareTo(insertVal) <= 0) {  // Recursive case
                insertHelper(current, current.right, insertVal, false);
            } else {
                insertHelper(current, current.left, insertVal, true);
            }
        }
    }

    /**
     * Implement iterator.
     * @return iterator
     */
    @Override
    public Iterator<T> iterator() {
        // Strategy: the iterator belongs to an arraylist.
        //           the arraylist is mapped from the BST.
        MyIterator it = new MyIterator(root, root);
        return it;
    }

    /**
     * Self-defined iterator.
     * AndrewID: jiamingx
     * @author Jiaming Xiao
     */
    private class MyIterator implements Iterator<T> {
        /**
         * Current node in the BST.
         */
        private Node<T> current;
        /**
         * A predecessor to connect nodes.
         */
        private Node<T> predecessor;
        /**
         * Index of array list which is used to trace its elements.
         */
        private int count = 0;
        /**
         * An array list to contain elements in BST according to in-order.
         */
        private List<Node<T>> itList = new ArrayList<Node<T>>();

        /**
         * Constructor.
         * @param newCurrent current node
         * @param newPredecessor predecessor node
         */
        private MyIterator(Node<T> newCurrent, Node<T> newPredecessor) {
            if (root == null) {
                return;
            }
            current = newCurrent;
            predecessor = newPredecessor;
            mapTreetoList();
        }

        /**
         * Check if hits the boundary.
         * @return false if hits the end, true if not.
         */
        @Override
        public boolean hasNext() {
             return !(count == itList.size()); // check if it hits end
        }

        /**
         * Get next element in a iteration.
         * @return the next element
         */
        @Override
        public T next() { // Get next value in the array list
            T result = itList.get(count).data;
            count++;
            return result;
        }

        /**
         * Map tree to an Array List.
         */
        private void mapTreetoList() {
            while (current != null) { // Keep traveersing in the BST.
                if (current.left == null) { // Hit the most left node
                    itList.add(current);    // add to array list
                    current = current.right; // Go to right
                } else {
                    // Strategy: connect most right node of a subtree to its
                    //           parent.
                    predecessor = current.left;
                    while (predecessor.right != null
                          && predecessor.right != current) {
                          predecessor = predecessor.right;
                    }
                    if (predecessor.right == null) { // hit the bottom
                        predecessor.right = current; // connect to parent of subtree
                        current = current.left;      // move to left
                    } else {
                        predecessor.right = null;
                        itList.add(current);         // add to array list
                        current = current.right;     // goes to right
                    }
                }
            }
        }
    }

    /**
     * Nodes of the BST.
     * AndrewID: jiamingx
     * @author Jiaming Xiao
     * @param <T> anytype
     */
    private static class Node<T> {
        /**
         * data in node.
         */
        private T data;
        /**
         * left child.
         */
        private Node<T> left;
        /**
         * right child.
         */
        private Node<T> right;

        /**
         * First constructor of node.
         * @param newData string of a word
         */
        Node(T newData) {
            this(newData, null, null);
        }

        /**
         * Second constructor of node.
         * @param d data of the node.
         * @param l left child of the node.
         * @param r right child of the node.
         */
        Node(T d, Node<T> l, Node<T> r) {
            this.data = d;
            this.left = l;
            this.right = r;
        }

        /**
         * Output string.
         * @return string of the word
         */
        @Override
        public String toString() {
            return data.toString();
        }
    }
}
