/**
 * This class is created to compress string.
 * @author Jeremy
 */
public class StringManipulation {
    /**
     * Constructor.
     */
    public StringManipulation() {


    }


    /**
     * Main function.
     */
    public static void main(String[] args) {
        StringManipulation strMan = new StringManipulation();
        System.out.println(strMan.StrManipulate(args[0]).toString());
    }

    /**
     * String manipulation function.
     * @param string
     * @return compressed string
     */
    private StringBuilder StrManipulate (String s) {
        int counter = 0;
        String currentLetter = new String();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            String temp = s.substring(i,i+1);
            if(currentLetter.isEmpty()) {
               currentLetter = temp;
               counter+=1;
            } else if (currentLetter.equals(temp)) {
               counter+=1;
            } else if (!currentLetter.equals(temp)) {
               result.append(currentLetter + counter);
               currentLetter = temp;
               counter=1;
            }
        }
        result.append(currentLetter + counter);
        return result;
    }
}
