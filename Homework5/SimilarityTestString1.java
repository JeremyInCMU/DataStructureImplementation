import java.io.File;
/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework Assignment 5
 * Document Similarity Acceptance Test (case 1)
 *
 * No need to worry about number of lines for this case.
 *
 * DO NOT MODIFY THIS CLASS!
 * @author Terry Lee
 */
public class SimilarityTestString1 {

    /**
     * Test program to check Similarity.
     * @param args arguments
     */
    public static void main(String[] args) {
        String input_1 = "I am pretty good";
        Similarity text1 = new Similarity(input_1);
        printOutput(text1);

        File input_2 = null;
        Similarity text2 = new Similarity(input_2);
        printOutput(text2);

        System.out.println(text1.dotProduct(text2.getMap()) + " dot product.");
        System.out.println(text1.distance(text2.getMap()) + " distance.");
    }

    /**
     * Prints some values of Similarity object.
     * @param sim Similarity object to deal with
     */
    private static void printOutput(Similarity sim) {
        System.out.println(sim.numOfWords() + " words.");
        System.out.println(sim.numOfWordsNoDups() + " distinct words.");
        System.out.println(sim.euclideanNorm() + " Euclidean norm.\n");
    }
}

/*
 * <EXPECTED OUTPUT>
 *
 * 9 words.
 * 7 distinct words.
 * 3.605551275463989 Euclidean norm.
 *
 * 10 words.
 * 10 distinct words.
 * 3.1622776601683795 Euclidean norm.
 *
 * 7.0 dot product.
 * 0.9097531579442097 distance.
 */
