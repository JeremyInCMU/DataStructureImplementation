/**
 * This class is built to stimulate array manipulation inside java source code.
 * 
 * Andrew ID: jiamingx
 * @author Jeremy
 */
public class MyArray {
    /**
     * Instance variables
     */
    private int capacity;
    private int size = 0;
    private String[] contents;

    /**
     * Constructor.
     */
    public MyArray(int initialCapacity) {
        capacity = initialCapacity;
        contents = new String[capacity];
    }

    /**
     * Add elements to array.
     * @param text
     */
    public void add(String text) {
        // Handle the invalid intput string
        if (text != null && text.isEmpty()) return;

        // Check if the input word is valid
        if (text !=null && !isWord(text)) return;

        // Add word to array
        if (size < capacity) { // Not full
            contents[size ++] = text;
        } 
        else {// Full
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
            contents[size ++] = text;
        }
    }

    /**
     * Helper function to check validity of word.
     * @param word
     */
    public boolean isWord(String word) {
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
     * @param temp
     */
    private void copyTempBack(String temp) {
        int prevIndex = 0;
        int recordIndex = 0;
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) == ',') {
                for ( int j = recordIndex; j < size; j++) {
                    String tempSubstring = temp.substring(prevIndex, i);
                    if (contents[j] == null) {
                        if (!tempSubstring.equals("null")) {
                            contents[j] = tempSubstring;
                        } else {
                            contents[j] = null;
                        }
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
     * @param key
     * @return wether the key is found or not
     */
    public boolean search(String key) {
        for (int i = 0; i < size; i++) {
            if (key == null) {
                if (contents[i] == null) {
                    return true;
                }
            }
            else if (key.equals(contents[i])) {
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
        for (int i = 0; i < size - 1; i++) {
            if (contents[i] != null) {
                System.out.print(contents[i] + " ");
            }
        }
        if (contents[size - 1] == null){
            System.out.println("");
        } else {
            System.out.println(contents[size - 1]);
        }
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
                }
                else if (contents[i].equals(contents[j])) { // Check duplate
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
     * @param index
     */
    private void remove (int index) {
        for (int i = index; i < size - 1; i++) {
            contents[i] = contents[i + 1];
        }
        contents[size - 1] = null;
        size -= 1;
    }

    /**
     * Helper function: to free memory space of empty part in the array.
     */
    private void releaseMemory() {
        if (size <= capacity/2) {
            String[] temp = new String[capacity/2];
            temp = contents.clone();
            contents = new String[capacity/2];
            contents = temp.clone();
            temp = null;
            capacity = capacity/2;
        }
    }
}
