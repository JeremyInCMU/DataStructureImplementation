import java.util.Arrays;
/**
 * 08722 Data Structures for Application Programmers.
 * Hw3 sorted linked list
 * AndrewID: jiamingx
 * @author Jiaming Xiao
 */
public class SortedLinkedList implements MyListInterface {
    private Node head;

    /**
     * Constructor.
     * @param unsortted this is an unsorrted array with string elements
     */
    public SortedLinkedList(String[] unsortted) {
        initLLD (unsortted, unsortted.length);
    }


    /**
     * Parse the input string array into a linked list.
     * @param array
     * @param cur
     * @param depth
     */
    private void initLLD(String[] array, int depth) {
        if (depth == 0) { // Base case
            return;
        } else { // Recursive case
            if (!contains(array[array.length - depth])) {
                System.out.println(depth);
                add(array[array.length - depth]);
            }
            initLLD(array, depth - 1);
        }
    }
 
    /**
     * Class for node which contains val and next node.
     * @author Jiaming Xiao
     */
    private class Node implements Comparable<Node> {
        public String val;
        Node next;
        
        /**
         * Constructor.
         * @param newVal
         */
        public Node(String newVal) {
            val =newVal;
        }

        @Override
        /**
         * Make comparison between strings.
         * @param other
         * @return an integer (-1 means less than, 0 means equal, 1 means larger than)
         */
        public int compareTo(Node other) {
            int len = other.val.length();
            if (this.val.length() < other.val.length()) {
                len = this.val.length();
            }
            for (int i = 0; i < len; i++) {
                if (this.val.charAt(i) - other.val.charAt(i) > 0) {
                    return 1;
                } else if (this.val.charAt(i) - other.val.charAt(i) < 0) {
                    return -1;
                }
            }
            if (this.val.length() > len) {
                return 1;
            } else if (other.val.length() > len) {
                return -1;
            }
            return 0;
        }
    }


    @Override
    /**
     * Insert a new string into the linked list.
     * @param value
     */
    public void add(String value) {
        Node addedNode = new Node(value);
        if (head == null) {  // Special case
            head = addedNode;
            return;
        }
        Node prevNode = searchForPosition(head, head, addedNode);
        if (prevNode == head) {  // Special case
            Node temp = head;
            head = addedNode;
            head.next = temp;
        } else if (prevNode != null) {  // Implement insert process
            Node temp = prevNode.next;
            prevNode.next = addedNode;
            addedNode.next = temp;
        }
    }

    /**
     * Helper function for the add function.
     * @param prev
     * @param cur
     * @param addedNode
     * @return the node whom the added node will be inserted behind
     */
    public Node searchForPosition(Node prev, Node cur, Node addedNode) {
        
        if (cur == null) { // Base case
            return prev;
        } else if (cur.val.equals(addedNode.val)) { // Second base case
            return null;
        } else if (cur.compareTo(addedNode) >= 0) { // Third base case
            return prev;
        } else {
            return searchForPosition(cur, cur.next, addedNode);
        }
    }

    @Override
    /**
     * Get size of the linked list.
     * @return size of the linked list
     */
    public int size() {
        return getLLDSize(head);
    }

    /**
     * Helper function to get linked list size.
     * @param cur
     * @return size of linkedlist
     */
    private int getLLDSize(Node cur) {
        if (cur == null) { // Base case
            return 0;
        } else {
            return 1 + getLLDSize(cur.next);
        }
    }

    @Override
    /**
     * Display contents of nodes in the linked list.
     */
    public void display() {
        StringBuilder result = new StringBuilder();
        System.out.println(printLLD(head, result));
    }

    /**
     * Helper function to get contents of the linked list.
     */
    private StringBuilder printLLD(Node cur, StringBuilder result) {
        if (cur == null) {
            return result;
        } else {
            return printLLD(cur.next, result).append(cur.val + " ");
        }
    }

    @Override
    /**
     * Check if nodes in the linked list contain the key
     * @param key
     * @return return true if it is found false if not
     */
    public boolean contains(String key) {
        return searchForContent(head, key);
    }

    /**
     * Helper function to check if the key is contained in the linked list.
     * @param cur
     * @param key
     * @return return true if it is found false if not
     */
    private boolean searchForContent(Node cur, String key) {
        if (cur == null) { // Base case 1
            return false;
        } else if (cur.val.equals(key)) {// Base case 2
            return true;
        } else { // Recursive case
            return searchForContent(cur.next, key);
        }
    }

    @Override
    /**
     * Check if the linked list is empty.
     * @return return true if it is empty, false if it is not
     */
    public boolean isEmpty() {
        if (head == null || head.next == null) {
            return true;
        } else {
            return checkLLDEmpty(head);
        }
    }

    /**
     * Helper function to check if the linked list is empty.
     * @param cur
     * @return true if the linked list is empty, false if it is not
     */
    private boolean checkLLDEmpty(Node cur) {
        if (cur == null) {
            return true;
        } else if (cur.val.isEmpty()) {
            return false;
        } else {
            return checkLLDEmpty(cur.next);
        }
    }

    @Override
    /**
     * Remove the first node in the linked list.
     * @ return string contained in the removed node
     */
    public String removeFirst() {
        if (head == null) {
            return null;
        }
        String result = head.val;
        head = head.next;
        return result;
    }

    @Override
    /**
     * Remove a node in the linked list through its index
     * @param index
     * @return string contained in the removed node
     */
    public String removeAt(int index) {
        if (index == 0) {
           return removeFirst();
        } else {
           return removeThroughIndex(head, index);
        }
    }

    /**
     * Helper function to remove node through its index.
     * @param cur
     * @param depth
     * @return string contained in the removed node
     */
    private String removeThroughIndex(Node cur, int depth) {
        if (depth == 1) {  // Base case
            String result = cur.next.val;
            cur.next = cur.next.next;
            return result;
        } else {
            return removeThroughIndex(cur.next, depth - 1);
        }
    }
}
