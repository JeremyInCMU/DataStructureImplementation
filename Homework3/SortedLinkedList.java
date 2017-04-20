/**
 * 08722 Data Structures for Application Programmers.
 * Hw3 sorted linked list
 * AndrewID: jiamingx
 * @author Jiaming Xiao
 */
public class SortedLinkedList implements MyListInterface {
    /**
     * Instance variable giving the head of the linked list.
     */
    private Node head;

    /**
     * Constructor 1.
     * @param unsortted this is an unsorrted array with string elements
     */
    public SortedLinkedList(String[] unsortted) {
        initLLD(unsortted, unsortted.length);
    }

    /**
     * Constructor 2.
     */
    public SortedLinkedList() {

    }

    /**
     * Parse the input string array into a linked list.
     * @param array the input string array
     * @param depth depth of the recursion process
     */
    private void initLLD(String[] array, int depth) {
        if (depth == 0) { // Base case
            return;
        } else { // Recursive case
            add(array[array.length - depth]);
            initLLD(array, depth - 1);
        }
    }

    /**
     * Class for node which contains val and next node.
     * @author Jiaming Xiao
     */
    private class Node {
        /**
         * Value of node.
         */
        private String val;

        /**
         * Next node in linked list.
         */
        private Node next;

        /**
         * Constructor.
         * @param newVal the input string value
         */
        private Node(String newVal) {
            val = newVal;
        }

        /**
         * Get value of a nod.
         * @return the value stored in this node
         */
        public String getValue() {
            return val;
        }

        /**
         * Check if the next node exist.
         * @return true if the next node exists false if not
         */
        public boolean hasNext() {
            return next != null;
        }

        /**
         * Get the next node.
         * @return the next node
         */
        public Node getNext() {
            return next;
        }

        /**
         * Set the next node.
         * @param newNode the new next node
         */
        public void setNext(Node newNode) {
            next = newNode;
        }
    }


    @Override
    /**
     * Insert a new string into the linked list.
     * @param value the string expect to add
     */
    public void add(String value) {
        if (!isWord(value) || contains(value)) {
            return;
        }

        Node addedNode = new Node(value);
        if (head == null) {  // Special case
            head = new Node(value);
            return;
        }
        Node prevNode = searchForPosition(null, head, value);
        if (prevNode == null) {  // Implement insert process special case
            Node temp = head;
            head = new Node(value);
            head.setNext(temp);
        } else if (prevNode != null) {  // Implement insert process
            Node temp = prevNode.getNext();
            prevNode.setNext(new Node(value));
            prevNode.getNext().setNext(temp);
        }
    }

    /**
     * Helper function for the add function.
     * @param prev previous node
     * @param cur  current node
     * @param data the string value prepared to insert into the linked list
     * @return the node whom the added node will be inserted behind
     */
    public Node searchForPosition(Node prev, Node cur, String data) {
        if (cur == null) { // Base case
            return prev;
        } else if (cur.val.compareTo(data) > 0) { // Second base case
            return prev;
        } else {
            return searchForPosition(cur, cur.getNext(), data);
        }
    }

    /**
     * Helper function to check validity of word.
     * @param word a word needs to be checked
     * @return if the input word is valid
     */
    private boolean isWord(String word) {
        // Handle edge case
        if (word == null || word.isEmpty()) { // Deal with special case
            return false;
        }
        return isWordHelper(word);
    }

    /**
     * Helper funcion for is word function.
     * @param word a word needs to be checked
     * @return true if the word is legal false if not
     */
    private boolean isWordHelper(String word) {
        if (word.length() == 1) {  // Base case
            int asciiCode = (int) word.charAt(0);
            if (asciiCode < 65 || (asciiCode > 90
                && asciiCode < 96) || (asciiCode > 122)) {
                return false;
            }
            return true;
        } else { // Recursive case
            return isWordHelper(word.substring(0, 1))
                   && isWordHelper(word.substring(1, word.length()));
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
     * @param cur current node
     * @return size of linkedlist
     */
    private int getLLDSize(Node cur) {
        if (cur == null) { // Base case
            return 0;
        } else {
            return 1 + getLLDSize(cur.getNext());
        }
    }

    @Override
    /**
     * Display contents of nodes in the linked list.
     */
    public void display() {
        if (size() == 0) {
            System.out.println("");
            return;
        }
        StringBuilder result = new StringBuilder();
        System.out.println("[" + printLLD(head, result).toString() + "]");
    }

    /**
     * Helper function to get contents of the linked list.
     * @param cur current node
     * @param result the string of output
     * @return the output string
     */
    private StringBuilder printLLD(Node cur, StringBuilder result) {
        if (!cur.hasNext()) {
            return result.append(cur.getValue());
        } else {
            return printLLD(cur.getNext(), result).insert(0, cur.getValue() + ", ");
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
     * @param cur current node
     * @param key the search key
     * @return return true if it is found false if not
     */
    private boolean searchForContent(Node cur, String key) {
        if (cur == null) { // Base case 1
            return false;
        } else if (cur.getValue().equals(key)) { // Base case 2
            return true;
        } else { // Recursive case
            return searchForContent(cur.getNext(), key);
        }
    }

    @Override
    /**
     * Check if the linked list is empty.
     * @return return true if it is empty, false if it is not
     */
    public boolean isEmpty() {
        if (head == null || !head.hasNext()) {
            return true;
        } else {
            return checkLLDEmpty(head);
        }
    }

    /**
     * Helper function to check if the linked list is empty.
     * @param cur current node
     * @return true if the linked list is empty, false if it is not
     */
    private boolean checkLLDEmpty(Node cur) {
        if (cur == null) {
            return true;
        } else if (cur.getValue().isEmpty()) {
            return false;
        } else {
            return checkLLDEmpty(cur.getNext());
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
        String result = head.getValue();
        head = head.getNext();
        return result;
    }

    @Override
    /**
     * Remove a node in the linked list through its index
     * @param index the index given to search for node
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
     * @param cur current node
     * @param depth depth of recursion
     * @return string contained in the removed node
     */
    private String removeThroughIndex(Node cur, int depth) {
        if (depth == 1) {  // Base case
            String result = cur.getNext().getValue();
            cur.setNext(cur.getNext().getNext());
            return result;
        } else {
            return removeThroughIndex(cur.getNext(), depth - 1);
        }
    }
}
