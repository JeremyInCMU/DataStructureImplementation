import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * This class is built for creating index tree.
 * Andrew ID: jiamingx
 * @author Jiaming Xiao
 */
public class Index {
    /**
     * Build a BST based on a file.
     * @param fileName name of input file
     * @return binary search tree
     */
    public BST<Word> buildIndex(String fileName) {
        File file = new File(fileName);
        BST<Word> tree = new BST<Word>();

        if (file == null || file.length() == 0) { // Edge case
            return null;
        }

        Scanner scanner = null;

        try { // Read words from file and insert into binary seaerch tree
            scanner = new Scanner(file, "latin1");
            Integer count = new Integer(1); // Count variable to record line number
            while (scanner.hasNextLine()) { // Scan lines in file
                String line = scanner.nextLine();
                String[] strings = line.split("\\W");
                for (String string: strings) {
                    Word word = new Word(string);
                    if (word != null && word.getWord() != null) { // Edge case
                        Word result = tree.search(word);
                        if (result == null) { // Add word into BST
                            word.setFrequency(1);
                            word.addToIndex(count);
                            tree.insert(word);
                        } else {     // Add frequency
                            result.setFrequency(result.getFrequency() + 1);
                            result.addToIndex(count);
                        }
                    }
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return tree;
    }

    /**
     * Build a BST based on a file and defined comparator.
     * @param fileName name of input file
     * @param comparator defined comparator
     * @return binary search tree
     */
    public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
        File file = new File(fileName);
        BST<Word> tree = new BST<Word>(comparator);

        if (file == null || file.length() == 0) { // Edge case
            return null;
        }

        Scanner scanner = null;

        try { // Read words from the file
            scanner = new Scanner(file, "latin1");
            Integer count = new Integer(1);  // Count variable to record line number
            while (scanner.hasNextLine()) {    // Scan lines in file
                String line = scanner.nextLine();
                String[] strings = line.split("\\W");
                for (String string: strings) {
                    Word word = new Word(string);
                    if (word != null && word.getWord() != null) { // Edge case
                        Word result = tree.search(word);
                        if (result != null) { // Add frequency
                            int currentFreq = result.getFrequency();
                            result.setFrequency(currentFreq + 1);
                            result.addToIndex(count);
                        } else { // Add word in BST
                            word.setFrequency(1);
                            word.addToIndex(count);
                            tree.insert(word);
                        }
                    }
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return tree;
    }

    /**
     * Build a BST based on an arraylist and a defined comparator.
     * @param list an array list
     * @param comparator defined comparator
     * @return binary search tree
     */
    public BST<Word> buildIndex(ArrayList<Word> list, Comparator<Word> comparator) {
        Iterator<Word> it = list.iterator();   // Iterator of the array list
        BST<Word> tree = new BST<Word>(comparator);  // Build a binary tree
        while (it.hasNext()) { // Go through elements in the BST
            Word insert = it.next();
            // Insert elements in a new tree based on defined comparator
            tree.insert(insert);
        }
        return tree;
    }

    /**
     * Sort BST by Alpha.
     * @param tree the BST is to be sorted
     * @return arraylist with BST elements
     */
    public ArrayList<Word> sortByAlpha(BST<Word> tree) {
        ArrayList<Word> result = new ArrayList<Word>();
        Iterator<Word> it = tree.iterator(); // Iterator of BST
        while (it.hasNext()) { // Parse the BST to an arraylist
            Word temp = it.next();
            result.add(temp);
        }

        // Create a new BST whose elements are sorted by alphabetics.
        BST<Word> updateTree = buildIndex(result, null);
        Iterator<Word> itUpdate = updateTree.iterator();
        // Create an arraylist to contain sorted words
        result = new ArrayList<Word>();
        while (itUpdate.hasNext()) { // Parse elements in BST to an arraylist
            Word temp = itUpdate.next();
            result.add(temp);
        }
        return result;
    }

    /**
     * Sort BST by Frequency.
     * @param tree the BST is to be sroted
     * @return araylist with BST elements
     */
    public ArrayList<Word> sortByFrequency(BST<Word> tree) {
        ArrayList<Word> result = new ArrayList<Word>();
        Iterator<Word> it = tree.iterator();
        while (it.hasNext()) { // Parse the BST to an arraylist
            Word temp = it.next();
            result.add(temp);
        }
        Comparator<Word> comparator = new Frequency();

        // Create a new BST whose elements are sorted by frequency
        BST<Word> updateTree = buildIndex(result, comparator);
        Iterator<Word> itUpdate = updateTree.iterator();
        // Create an arraylist to contain sorted words
        result = new ArrayList<Word>();
        while (itUpdate.hasNext()) { // Parse elements in BST to an arraylist
            Word temp = itUpdate.next();
            result.add(temp);
        }
        return result;
    }

    /**
     * Get higest frequency elements in BST.
     * @param tree the BST
     * @return arraylist with BST elements
     */
    public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
        ArrayList<Word> result = new ArrayList<Word>();
        ArrayList<Word> hFreqResult = new ArrayList<Word>();
        Iterator<Word> it = tree.iterator();
        int highestFreq = 0;
        while (it.hasNext()) { // Search for the higest frequency
            Word temp = it.next();
            result.add(temp);
            if (highestFreq == 0 || highestFreq < temp.getFrequency()) {
               highestFreq = temp.getFrequency();
            }
        }

        it = result.iterator();
        // Includes the nodes with highest frequency into an arraylist
        while (it.hasNext()) {
            Word temp = it.next();
            if (temp.getFrequency() == highestFreq) {
                hFreqResult.add(temp);
            }
        }
        return hFreqResult;
    }

}
