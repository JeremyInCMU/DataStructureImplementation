import java.util.Comparator;
/**
 * Comparator based on frequency.
 * AndrewID: jiamingx
 * @author Jiaming Xiao
 */
public class Frequency implements Comparator<Word> {
    /**
     * Constructor.
     */
    public Frequency() {

    }

    @Override
    /**
     * Compare by frequency
     * @param thisWord
     * @param otherWord
     */
    public int compare(Word thisWord, Word otherWord) {
        return otherWord.getFrequency() - thisWord.getFrequency();
    }
}
