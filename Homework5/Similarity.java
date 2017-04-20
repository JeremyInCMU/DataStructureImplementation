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
    private Map<String, BigInteger> dictionary = null;

    /**
     * Number of lines.
     */
    private int numOfLines;

    /**
     * Number of words.
     */
    private BigInteger numOfWords = new BigInteger("0");

    /**
     * Define default error to check if a double value is zero.
     */
    private static final double EPSILON = 0.00001;

    /**
     * Constructor 1.
     * @param string input string
     */
    public Similarity(String string) {
        // Edge case: check if input string is null
        if (string == null || string.isEmpty()) {
            return;
        }

        dictionary = new HashMap<String, BigInteger>();
        String[] words = string.split("\\W");
        for (String word : words) {
            if (!isWord(word)) {
                continue;
            }

            BigInteger one = new BigInteger("1");
            numOfWords = numOfWords.add(one);
            word = word.toLowerCase();
            BigInteger temp = dictionary.get(word);
            if (temp == null) {
                temp = new BigInteger("1");
                dictionary.put(word, temp);
            } else {
                one = new BigInteger("1");
                temp = temp.add(one);
                dictionary.put(word, temp);
            }
        }
    }

    /**
     * Constructor 2.
     * @param file input file
     */
    public Similarity(File file) {
        if (file  == null || file.length() == 0) {  // Edge case null and empty;
            return;
        }

        Scanner scanner = null;
        try {
            dictionary = new HashMap<String, BigInteger>();
            scanner = new Scanner(file, "latin1");
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
                    BigInteger temp = dictionary.get(word);
                    if (temp == null) {
                        temp = new BigInteger("1");
                        dictionary.put(word, temp);
                    } else {
                        one = new BigInteger("1");
                        temp = temp.add(one);
                        dictionary.put(word, temp);
                    }
                }
                numOfLines++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * Check if the word is legal.
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
    public int numOfWordsNoDups() {
        int count = 0;

        if (dictionary == null) {  // Edge case null;
            return count;
        }

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

        if (dictionary == null) {
            return result;
        }

        for (String key : dictionary.keySet()) {
            BigInteger temp = dictionary.get(key);
            if (temp != null) {
                result += Math.pow(temp.intValue(), 2);
            }
        }
        return Math.sqrt(result);
    }

    /**
     * Calcualte dot product between two maps.
     * @param map the map in another similairty instance
     * @return dot product between two maps
     */
    public double dotProduct(Map<String, BigInteger> map) {
        double result = 0;

        if (dictionary == null || map == null) {
            return result;
        }

        for (String key : dictionary.keySet()) {
            BigInteger tempMap = map.get(key);
            BigInteger tempDict = dictionary.get(key);
            if (tempMap != null && tempDict != null) {
                result += tempMap.intValue() * tempDict.intValue();
            }
        }
        return result;
    }

    /**
     * Calculate distance between two maps.
     * @param map the map in another similarity instance
     * @return distance between two maps
     */
    public double distance(Map<String, BigInteger> map) {
        double result = Math.PI / 2;

        if (map == null) {
            return result;
        }

        double dP = dotProduct(map); // Calculate dot product
        double thisNorm = euclideanNorm();
        // Calculate norm for another map
        double sum = 0;
        for (String key : map.keySet()) {
            BigInteger temp = map.get(key);
            if (temp != null) {
                sum += Math.pow(temp.intValue(), 2);
            }
        }
        double otherNorm = Math.sqrt(sum);

        if (isZero(dP)) {  // Edge case 1: completely different
            return Math.PI / 2;
        }


        if (dP / (thisNorm * otherNorm) > 1.00) { // Edge case 2: completely the same
            result = Math.acos(1.00);
        } else {
            result = Math.acos(dP / (thisNorm * otherNorm));
        }

        if (isZero(result)) { // Edge case 3: completely the same
            return 0.00;
        }

        return result;
    }

    /**
     * Get the map behind the similarity class.
     * @return the map behind similarity calss
     */
    public Map<String, BigInteger> getMap() {
        if (dictionary == null) {
            return dictionary;
        }
        return new HashMap<String, BigInteger>(dictionary);
    }

    /**
     * Helper function: check if a double value is close enough to zero.
     * @param num given double value
     * @return true if it appraoches to zero false if not.
     */
    private boolean isZero(double num) {
        return Math.abs(num) < EPSILON;
    }
}
