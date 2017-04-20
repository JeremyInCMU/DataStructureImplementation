import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.ListIterator;
/**
 * 08722 Data Structures for Application Programmers.
 * Homework Assignment 2
 * Solve Josephus problem with different data structures
 * and different algorithms and compare running times
 *
 * Andrew ID: jiamingx
 * @author: Jiaming Xiao
 */
public class Josephus {

    /**
     * Uses ArrayDeque class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithAD(int size, int rotation) {
        // TODO your implementation here
        ArrayDeque<Integer> queue = new ArrayDeque<Integer>(size);
        // Add  elements into queue
        if (rotation > 0) {
            for (int i = 1; i <= size; i++) {
                queue.addLast(i);
            }
        }

        int counter = 0;
        // Search for the survive element
        while (queue.size() > 1) {
            if (rotation > queue.size() && rotation % queue.size() == 0) {
                counter = queue.size() - 1;
            } else if (rotation > queue.size() && rotation % queue.size() != 0) {
                counter = rotation % queue.size() - 1;
            } else {
                counter = rotation - 1;
            }

            while (counter > 0) {
                int temp = queue.removeFirst();
                queue.addLast(temp);
                counter--;
            }
            queue.removeFirst();
        }
        return queue.getFirst();
    }

    /**
     * Uses LinkedList class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLL(int size, int rotation) {
        // TODO your implementation here
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Add elements into queue
        if (rotation > 0) {
            for (int i = 1; i <= size; i++) {
                queue.addLast(i);
            }
        }

        int counter = 0;
        // Search for the survive element
        while (queue.size() > 1) {
           if (rotation > queue.size() && rotation % queue.size() == 0) {
               counter = queue.size() - 1;
           } else if (rotation > queue.size() && rotation % queue.size() != 0) {
               counter = rotation % queue.size() - 1;
           } else {
               counter = rotation - 1;
           }

           while (counter > 0) {
               int temp = queue.removeFirst();
               queue.addLast(temp);
               counter--;
           }
           queue.removeFirst();
        }
        return queue.getFirst();
    }

    /**
     * Uses LinkedList class to find the survivor's position.
     * However, do NOT use the LinkedList as Queue/Deque
     * Instead, use the LinkedList as "List"
     * That means, it uses index value to find and remove a person to be executed in the circle
     *
     * Note: Think carefully about this method!!
     * When in doubt, please visit one of the office hours!!
     *
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLLAt(int size, int rotation) {
        // TODO your implementation here
        LinkedList<Integer> list = new LinkedList<Integer>();
        // Add elements into list
        if (rotation > 0) {
            for (int i = 1; i <= size; i++) {
                list.add(i);
            }
        }

        // Search for the survivor
        int counter = 0;
        ListIterator iteration = list.listIterator(0);
        while (list.size() > 1) {
            if (rotation > list.size() && rotation % list.size() == 0) {
                counter = list.size() - 1;
            } else if (rotation > list.size() && rotation % list.size() != 0) {
                counter = rotation % list.size() - 1;
            } else {
                counter = rotation - 1;
            }

            while (counter > 0) {
                if (!iteration.hasNext()) {
                    iteration = list.listIterator(0);
                }
                iteration.next();
                counter--;
            }
            int tempIndex = 0;
            if (iteration.hasNext()) {
                tempIndex = iteration.nextIndex();
            }
            list.remove(tempIndex);
            iteration = list.listIterator(tempIndex);
        }
        return list.getFirst();
    }
}
