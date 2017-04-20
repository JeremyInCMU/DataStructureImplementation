import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 08722 Data Structures for Application Programmers.
 *
 * Lab 7 Heaps and Java PriorityQueue class
 * Find median of integers using both maxHeap and minHeap
 *
 * Andrew ID: jiamingx
 * @author
 */
public class FindMedian {
    /**
     * max heap data structure using PQ.
     */
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(20, Collections.reverseOrder());
    /**
     * min heap data structure using PQ.
     */
    private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(20);

    /**
     * Adds int values into two heaps.
     * It should maintain a condition that maxHeap.size() >= minHeap.size().
     * @param value int value to add
     */
    public void addNumber(int value) {
        // TODO implement this method
    	if (maxHeap.size() == 0) { // Edge case 1
    		maxHeap.add(new Integer(value));
    		return;
    	}
    	
    	if (minHeap.size() == 0) {
    		minHeap.add(new Integer(value));
    		return;
    	}
    	
    	
    	int maxHeapMax = maxHeap.peek().intValue();
    	int minHeapMin = minHeap.peek().intValue();
    	
    	if (maxHeap.size() > minHeap.size() && value >= maxHeapMax) {
    		minHeap.add(new Integer(value));
    	} else if (maxHeap.size() > minHeap.size() && value < maxHeapMax) {
    		minHeap.add(new Integer(maxHeapMax));
    		maxHeap.remove(maxHeap.peek());
    		maxHeap.add(new Integer(value));
    	} else if (maxHeap.size() == minHeap.size() && value >= maxHeapMax) {
    		maxHeap.add(new Integer(minHeapMin));
    		minHeap.remove(minHeap.peek());
    		minHeap.add(new Integer(value));
    	} else if (maxHeap.size() == minHeap.size() && value < maxHeapMax) {
            maxHeap.add(new Integer(value));   		
    	}
    }

    /**
     * Returns the median value of the added values.
     * If maxHeap and minHeap are of different sizes, then maxHeap must have one extra element.
     * @return median value
     */
    public double getMedian() {
        // TODO implement this method
    	if (maxHeap.size() == minHeap.size()) {
    		return ((double) maxHeap.peek().intValue() + minHeap.peek().intValue())/2;
    	} else {
    		return (double) (maxHeap.peek().intValue());
    	}
    }

    /**
     * Test program to add int values and find median of those.
     * @param args arguments
     */
    public static void main(String[] args) {
        FindMedian tester = new FindMedian();
        tester.addNumber(6);
        tester.addNumber(4);
        tester.addNumber(3);
        tester.addNumber(10);
        tester.addNumber(12);

        // 6.0
        System.out.println(tester.getMedian());

        tester.addNumber(5);

        // 5.5
        System.out.println(tester.getMedian());

        tester.addNumber(7);
        tester.addNumber(8);

        // 6.5
        System.out.println(tester.getMedian());
    }

}
