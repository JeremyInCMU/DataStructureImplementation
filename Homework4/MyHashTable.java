/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework Assignment 4
 * HashTable Implementation with linear probing
 *
 * Andrew ID: jiamingx
 * @author: Jiaming Xiao
 */
public class MyHashTable implements MyHTInterface {
    /**
     * Capacity of the hashtable.
     */
    private int capacity = 10;

    /**
     * Initial load factor.
     */
    private double loadFactor = 0.5;

    /**
     * Size of hash table: number of items.
     */
    private int size;

    /**
     * Number of collisions.
     */
    private int collisionNum;

    /**
     * Array of data items.
     */
    private DataItem[] hashTable;

    /**
     * Base for hash processes.
     */
    private final int base = 27;

    // TODO implement constructor with no initial capacity
    /**
     * Constructor without passing parameters.
     */
    public MyHashTable() {
        initHashTable(capacity);
    }

    // TODO implement constructor with initial capacity
    /**
     * Constructor with initial capacity.
     * @param newCapacity user defined capacity
     */
    public MyHashTable(int newCapacity) {
        capacity = newCapacity;
        initHashTable(capacity);
    }

    // TODO implement required methods
    /**
     * Helper function: init the hash table array.
     * @param cap capacity of the hash table.
     */
    private void initHashTable(int cap) {
        hashTable = new DataItem[cap];
        collisionNum = 0;
        size = 0;
    }

    /**
     * Inserts a new String value.
     * @param value String value to add
     */
    public void insert(String value) {

        if (!isWord(value)) { // Check if the input value is a word
            return;
        }

        int hashValue = hashValue(value);
        int i = hashValue(value);
        while (i <= capacity) {
            i = i % capacity;

            if (hashTable[i] == null
                || hashTable[i].value.equals("#DEL#")) {
                hashTable[i] = new DataItem(value, 1);
                size += 1;
                break;
            } else if (hashTable[i].value.equals(value)) {
                hashTable[i].frequency += 1;
                break;
            } else if (i == hashValue && !contains(value)
                       && i == hashValue(hashTable[i].value)) {
                collisionNum++;
            }
            i++;
        }
        if ((double) size / capacity > loadFactor) { // Check if it needs to rehash
            rehash();
        }
    }


    /**
     * Helper function to check validity of word.
     * @param word //a word needs to be checked
     * @return if the input word is valid
     */
    public boolean isWord(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }

        for (int i = 0; i < word.length(); i++) {
            int asciiCode = (int) word.charAt(i);
            if (asciiCode < 65 || (asciiCode > 90
                && asciiCode < 96) || asciiCode > 122) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get size: number of items in the table.
     * @return the number of items in the table
     */
    public int size() {
        return size;
    }

    /**
     * Displays the values of the table.
     * If an index is empty, it shows **
     * If an data item has been deleted, it shows #DEL#
     */
    public void display() {
        String result = "";

        for (int i = 0; i < capacity - 1; i++) {

            if (hashTable[i] == null) {
                result += "** ";
            } else if (hashTable[i].value == "#DEL#") {
                result += hashTable[i].value + " ";
            } else {
                result += "[" + hashTable[i].value + ", "
                          + hashTable[i].frequency + "] ";
            }
        }
        if (hashTable[capacity - 1] == null) {
            result += "**\n";
        } else if (hashTable[capacity - 1].value == "#DEL#") {
            result += hashTable[capacity - 1].value + "\n";
        } else {
            result += "[" + hashTable[capacity - 1].value + ", "
                      + hashTable[capacity - 1].frequency + "]\n";
        }
        System.out.println(result);
    }

    /**
     * Returns true if value is contained in the table.
     * @param key string key value to search
     * @return true if found, false if not
     */
    public boolean contains(String key) {
        int hashVal = hashValue(key);

        for (int i = 0; i < capacity; i++) {
            if (hashTable[i] != null
                && hashTable[i].value.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get number of collision during insert and rehash.
     * @return number of collisions
     */
    public int numOfCollisions() {
        return collisionNum;
    }

    /**
     * Returns the hash value of a string.
     * @param value whose hash value needs to be calcualted
     * @return int has value of a string
     */
    public int hashValue(String value) {
        return hashFunc(value);
    }

    /**
     * Returns the frequency of a key string.
     * @param key string value to find its frequency
     * @return frequency value if found. If not found, return 0
     */
    public int showFrequency(String key) {
        for (int i = 0; i < capacity; i++) {
            if (hashTable[i] != null
                && hashTable[i].value.equals(key)) {
                return hashTable[i].frequency;
            }
        }
        return 0;
    }

    /**
     * Removes and returns removed value.
     * @param key string to remove
     * @return value that is removed
     */
    public String remove(String key) {
        for (int i = 0; i < capacity; i++) {
            if (hashTable[i] != null
                && hashTable[i].value.equals(key)) {
                String result = hashTable[i].value;
                hashTable[i].value = "#DEL#";
                hashTable[i].frequency = 0;
                size--;
                return result;
            }
        }
        return null;
    }

    /**
     * Instead of using String's hashCode, you are to implement your own here.
     * You need to take the table length into your account in this method.
     *
     * In other words, you are to combine the following two steps into one step.
     * 1. converting Object into integer value
     * 2. compress into the table using modular hashing (division method)
     *
     * Helper method to hash a string for English lowercase alphabet and blank,
     * we have 27 total. But, you can assume that blank will not be added into
     * your table. Refer to the instructions for the definition of words.
     *
     * For example, "cats" : 3*27^3 + 1*27^2 + 20*27^1 + 19*27^0 = 60,337
     *
     * But, to make the hash process faster, Horner's method should be applied as follows;
     *
     * var4*n^4 + var3*n^3 + var2*n^2 + var1*n^1 + var0*n^0 can be rewritten as
     * (((var4*n + var3)*n + var2)*n + var1)*n + var0
     *
     * Note: You must use 27 for this homework.
     *
     * However, if you have time, I would encourage you to try with other
     * constant values than 27 and compare the results but it is not required.
     * @param input input string for which the hash value needs to be calculated
     * @return int hash value of the input string
     */
    private int hashFunc(String input) {
        // TODO implement this
        int len = input.length();
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum = (sum * base) % capacity + (input.charAt(i) - 96) % capacity;
        }
        return sum % capacity;
    }

    /**
     * doubles array length and rehash items whenever the load factor is reached.
     */
    private void rehash() {
        // TODO implement this
        DataItem[] oldHashTable = hashTable;
        int oldCapacity = capacity;
        capacity = capacity * 2;
        if (isPrime(capacity)) {
            initHashTable(capacity);
        } else {
            capacity = findNextPrime(capacity);
            initHashTable(capacity);
        }
        for (int i = 0; i < oldCapacity; i++) {
            if  (oldHashTable[i] != null
                 && !oldHashTable[i].value.equals("#DEL#")) {
                int tempFreq = oldHashTable[i].frequency;
                while (tempFreq > 0) {
                    insert(oldHashTable[i].value);
                    tempFreq--;
                }
            }
        }
    }


    /**
     * Helper function: is prime number.
     * @param num a number that will be checked if it is a prime number.
     * @return true if the number is a prime, false if not.
     */
    private boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper function: find next prime number.
     * @param currentCapacity current capacity
     * @return the nearest prime number
     */
    private int findNextPrime(int currentCapacity) {
        int updateCapacity = currentCapacity + 1;
        while (true) {
            if (isPrime(updateCapacity)) {
                return updateCapacity;
            }
            updateCapacity++;
        }
    }

    /**
     * private static data item nested class.
     */
    private static class DataItem {
        /**
         * String value.
         */
        private String value;
        /**
         * String value's frequency.
         */
        private int frequency;

        // TODO implement constructor and methods
        /**
         * Constructor.
         * @param newValue the insert value of a string
         * @param newFreq the frequency of the insert value
         */
        private DataItem(String newValue, int newFreq) {
            value = newValue;
            frequency = newFreq;
        }
    }
}
