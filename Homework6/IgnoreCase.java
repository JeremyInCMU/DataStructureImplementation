import java.util.Comparator;
/**
 * Ignore case comparator.
 * AndrewID: jiamingx
 * @author Jiaming Xiao
 */
public class IgnoreCase implements Comparator<Word> {
  /**
   * Constructor.
   */
  public IgnoreCase() {

  }

  @Override
  /**
   * compare different word.
   */
  public int compare(Word wd1, Word wd2) {
    wd1.setWord(wd1.getWord().toLowerCase());
    wd2.setWord(wd2.getWord().toLowerCase());
    return wd1.getWord().compareToIgnoreCase(wd2.getWord());
  }
}
