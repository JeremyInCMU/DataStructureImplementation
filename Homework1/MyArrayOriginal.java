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
        if (text.isEmpty()) return;

        // Check if the input word is valid
        if (!isWord(text)) return;

        // Add word to array
        if (size < capacity) { // Not full
            contents[size ++] = text;
        } 
        else {// Full
            String temp = tempStore();
            // Update capacity
            if (capacity == 0) {
                capacity = (capacity + 1) * 2;
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
        if (word == null) return true;
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
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) == ',') {
                for ( int j = 0; j < size; j++) {
                    if (contents[j] == null) {
                        contents[j] = temp.substring(prevIndex, i);
                        prevIndex = i + 1;
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
            if (key == null && contents[i] == null) {
                return true;
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
        for (int i = 0; i < size; i++) {
            System.out.print(contents[i] + " ");
        }
    }

    /**
     * Remove duplicates in the contents array.
     */
    public void removeDups() {
        int[] dupIndex = new int[size];
        int counter = 0;
        boolean exist;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                exist = false;
                if (contents[i].equals(contents[j])) { // Check duplate
                    // Check if j in dupIndex array
                    for(int k = 0; k < size; k++) {
                        if (dupIndex[k] == 0) {
                            break;
                        }
                        else if (dupIndex[k] == j) {
                            exist = true;
                        }
                    }
                    if (!exist) {
                        for(int k = 0; k < size; k++) {
                            if (dupIndex[k] == 0) {
                                dupIndex[k] = j;
                                counter++;
                                break;
                            }
                            else if (j > dupIndex[k]) { // Sort the array when insert elements
                                // right shift
                                for (int p = counter; p > k; p--) {
                                    if(p >= size || p <= 0) {
                                       System.out.println(p);
                                    }
                                    dupIndex[p] = dupIndex[p - 1];
                                }
                                dupIndex[k] = j;
                                counter++;
                                break;
                            }
                        }
                    }
                }
            }
        }
        // Begin to remove elements
        for (int q = 0; q < counter; q++) {
            remove(dupIndex[q]);
        }
        dupIndex = null; //release memory of dupIndex array 
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
