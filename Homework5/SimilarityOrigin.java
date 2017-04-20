import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.math.BigInteger;
import java.io.FileNotFoundException;

/**
 * This class is created to calcualte similarity between strings and files.
 * AndrewID: jiamingx
 * @author Jiaming Xiao
 */
public class Similarity {
    /**
     * Hash map is the data structure behind this class.
     * It will contain all the words as keys of map. 
     * And frequency of each word will be the values.
     */
    private Map<String, Integer> dictionary = new HashMap<String, Integer>();

    /**
     * Number of lines.
     */
    private int numOfLines;

    /**
     * Number of words.
     */
    private BigInteger numOfWords = new BigInteger("0");

    /**
     * Constructor 1.
     * @param string input string
     */
    public Similarity(String string) {
        String[] words = string.split("\\W");
        for (String word : words) {
            if (!isWord(word)) {
                return;
            }

            BigInteger one = new BigInteger("1");
            numOfWords = numOfWords.add(one);
            word = word.toLowerCase();
            Integer temp = dictionary.get(word);
            if (temp == null) {
                temp = new Integer(1);
                dictionary.put(word, temp);
            } else {
                temp = new Integer(temp.intValue() + 1);
                dictionary.put(word, temp);
            }
        }
    }

    /**
     * Constructor 2.
     * @param file input file
     */
    public Similarity(File file) {
        Scanner scanner = null;
        try {
            scanner = new Scanner (file, "latin1");
            numOfLines = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\W");
                for (String word : words) {
                    if (!isWord(word)) {
                        continue;
                    }

                    BigInteger one = new BigInteger("1");
                    numOfWords = numOfWords.add(one);
                    word = word.toLowerCase();
                    Integer temp = dictionary.get(word);
                    if (temp == null) {
                        temp = new Integer(1);
                        dictionary.put(word, temp);
                    } else {
                        temp = new Integer(temp.intValue() + 1);
                        dictionary.put(word, temp);
                    }
                }
                numOfLines++;
            }
        } catch(FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * Check if the word is legal
     * @param input  input string
     * @return true if the word only contains alphebatics false if not
     */ 
    private boolean isWord(String input) {
        return input.matches("[a-zA-Z]+");
    }

    /**
     * Get number of lines in the input file.
     * @return the number of lines 
     */
    public int numOfLines() {
        return numOfLines;
    }

    /**
     * Get number of words in the input file or string.
     * @return the number of words
     */
    public BigInteger numOfWords() {
        return numOfWords;
    }

    /**
     * Get number of words without duplicates.
     * @return the number of words without duplicates
     */
    public int numOfWordsNoDups() {  // There is a bug here
        int count = 0;
        for (String key : dictionary.keySet()) {
            if (dictionary.get(key) != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Get the result of euclideanNorm.
     * @return the euclidean norm
     */
    public double euclideanNorm() {
        double result = 0;
        for (String key : dictionary.keySet()) {
            Integer temp = dictionary.get(key);
            if (temp != null) {
                result += Math.pow(temp.intValue(), 2);
            }
        }
        return Math.sqrt(result);
    }

    /**
     * Calcualte dot product between two maps.
     * @param map
     * @result dot product between two maps
     */
    public double dotProduct(Map<String, Integer> map) {
        double result = 0;
        for (String key : dictionary.keySet()) {
            Integer temp = map.get(key);
            if (temp != null) {
                result += temp.intValue() * dictionary.get(key).intValue();
            }
        }
        return result;
    }

    /**
     * Calculate distance between two maps.
     * @param map
     * @result distance between two maps
     */
    public double distance(Map<String, Integer> map) {
        double result = 0;
        double dP = dotProduct(map); // Calculate dot product
        double thisNorm = euclideanNorm();
        // Calculate norm for another map
        double sum = 0;
        for (String key : map.keySet()) {
            Integer temp = map.get(key);
            if (temp != null) {
                sum += Math.pow(temp.intValue(), 2);
            }
        }
        double otherNorm = Math.sqrt(sum);

        return Math.acos(dP / (thisNorm * otherNorm));
    }

    /**
     * Get the map behind the similarity class.
     * @return the map behind similarity calss
     */
    public Map<String, Integer> getMap() {
        return dictionary;
    }
}