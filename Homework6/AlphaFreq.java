import java.util.Comparator;
/**
 * Comparator based on alphabetic and frequency.
 * AndrewID: jiamingx
 * @author Jiaming Xiao
 */
public class AlphaFreq implements Comparator<Word> {
     /**
      *  Constructor.
      */
    public AlphaFreq() {

    };

    @Override
    /* Compare
     * @param thisWord
     * @param otherWord
     * @return negative, zero and positive
     */
    public int compare(Word thisWord, Word otherWord) {

        int alphaRank = thisWord.getWord().compareTo(otherWord.getWord());
        if (alphaRank != 0) {
            return alphaRank;
        }
        return thisWord.getFrequency() - otherWord.getFrequency();
    }
}
