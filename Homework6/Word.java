import java.util.Set;
import java.util.TreeSet;
/**
 * This class represents a word.
 *
 * AndrewID: jiamingx
 * @author Jiaming  Xiao
 *
 */
public class Word implements Comparable<Word> {
    /**
     * the string word.
     */
    private String word;

    /**
     * Index.
     */
    private Set<Integer> index;

    /**
     * Frequency.
     */
    private int frequency;

    /**
     * Constructor.
     * @param initWord initialize the a word
     */
    public Word(String initWord) {
        if (!isWord(initWord)) {
            return;
        }
        word = initWord;
        index = new TreeSet<Integer>();
    }

    // TODO implement methods below.
    /**
     * Set the string value.
     * @param newWord the reset value of word
     */
    public void setWord(String newWord) {
        word = newWord;
    }

    /**
     * Set index.
     * @param newIndex the reset value of index
     */
    public void setIndex(Set<Integer> newIndex) {
        index = newIndex;
    }

    /**
     * Set frequency.
     * @param newFrequency the reset value of frequency
     */
    public void setFrequency(int newFrequency) {
        frequency = newFrequency;
    }

    /**
     * Get the string value.
     * @return string value of word
     */
    public String getWord() {
        return word;
    }

    /**
     * Get index.
     * @return index of the word
     */
    public Set<Integer> getIndex() {
        Set<Integer> copyIndex = new TreeSet<Integer>(index);
        return copyIndex;
    }

    /**
     * Get frequency.
     * @return frequency of a word
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Implement compareTo method.
     * @param otherWord
     * @return 0 if equal, positive num if large, negative if less
     */
    @Override
    public int compareTo(Word otherWord) {
        return word.compareTo(otherWord.word);
    }

    /**
     * To string method.
     * @return a string
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(word);
        result.append(" ");
        result.append(frequency);
        result.append(" ");
        result.append(index);
        return result.toString();
    }

    /**
     * Add new index.
     * @param line the line which contains the word
     */
    public void addToIndex(Integer line) {
        index.add(line);
    }

    /**
     * Check if the word legal.
     * @param newWord the input new word
     * @return true if legal false if not
     */
    private boolean isWord(String newWord) {
        return newWord.matches("[a-zA-Z]+");
    }
}
