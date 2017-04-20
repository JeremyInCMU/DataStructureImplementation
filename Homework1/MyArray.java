/**
 * This class is built to stimulate array manipulation inside java source code.
 * Andrew ID: jiamingx
 * @author Jiaming Xiao
 */
public class MyArray {
    /**
     * Capacity of the array.
     */
    private int capacity;

    /**
     * Size of the array.
     */
    private int size = 0;

    /**
     * The array.
     */
    private String[] contents;

    /**
     * Constructor 1.
     */
    public MyArray() {
        capacity = 0;
        contents = new String[capacity];
    }

    /**
     * Constructor 2.
     * @param initialCapacity // Initial capacity of the array
     */
    public MyArray(int initialCapacity) {
        capacity = initialCapacity;
        contents = new String[capacity];
    }

    /**
     * Add elements to array.
     * @param text // A word add to the array
     */
    public void add(String text) {
        // Check validity of the input word
        if (!isWord(text)) {
            return;
        }

        // Add word to array
        if (size < capacity) { // Not full
            contents[size++] = text;
        } else { // Full
            String temp = tempStore();
            // Update capacity
            if (capacity == 0) {
                capacity = capacity + 2;
            } else {
                capacity *= 2;
            }
            contents = new String[capacity];
            copyTempBack(temp);
            temp = null; // Free memory space for temp
            contents[size++] = text;
        }
    }

    /**
     * Helper function to check validity of word.
     * @param word //a word needs to be checked
     * @return if the input word is valid
     */
    public boolean isWord(String word) {
        // Handle edge case
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
     * Helper function to store elements in the previous contents array into a string.
     * @return the string stores those elements
     */
    private String tempStore() {
        String result = "";
        for (int i = 0; i <  size; i++) {
            result += contents[i];
            result += ",";
        }
        return result;
    }

    /**
     * Helper function to copy elements in temp string back to the expanded array.
     * @param temp // A string to store elements in the previous array
     */
    private void copyTempBack(String temp) {
        int prevIndex = 0;
        int recordIndex = 0;
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) == ',') {
                for (int j = recordIndex; j < size; j++) {
                    if (contents[j] == null) {
                        contents[j] = temp.substring(prevIndex, i);
                        prevIndex = i + 1;
                        recordIndex = j + 1;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Search specific word in contents array.
     * @param key // the keyword for search words in the array
     * @return wether the key is found or not
     */
    public boolean search(String key) {
        // handle edge case
        if (key == null || key.isEmpty()
            || !isWord(key)) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (key.equals(contents[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get size of the contents array.
     * @return number of elements in the contents array
     */
    public int size() {
        return size;
    }

    /**
     * Get length of the contents array.
     * @return length of the contents array
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Display the words in the contents array.
     */
    public void display() {
        if (size == 0) { // Handle edge case
            System.out.println("");
            return;
        }

        for (int i = 0; i < size - 1; i++) {
            System.out.print(contents[i] + " ");
        }
        System.out.println(contents[size - 1]);
    }

    /**
     * Remove duplicates in the contents array.
     */
    public void removeDups() {
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (contents[i] == null) { // Handle null edge case
                    if (contents[j] == null) {
                        contents[j] = "1";
                    }
                } else if (contents[i].equals(contents[j])) { // Check duplate
                    contents[j] = "1"; // Mark the dup elements
                }
            }
        }
        // Begin to remove elements
        for (int k = size - 1; k >= 0; k--) {
            if (contents[k] != null && contents[k].equals("1")) {
                remove(k);
            }
        }
    }

    /**
     * Helper function: to remove specific element in the contents array.
     * @param index // the index to position dup element in the array
     */
    private void remove(int index) {
        for (int i = index; i < size - 1; i++) {
            contents[i] = contents[i + 1];
        }
        contents[size - 1] = null;
        size -= 1;
    }
}
