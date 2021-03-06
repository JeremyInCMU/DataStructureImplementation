import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * 08722 Data Structures for Application Programmers.
 * Homework Assignment 4
 * HashTable Implementation with linear probing
 *
 * This is only to help you perform a few simple test cases.
 *
 * THERE IS A LOT MORE TO THINK ABOUT!
 * Please test extensively on your own!
 *
 * @author Terry Lee
 */
public class HTTestNew {

    /**
     * Simple hashtable test program.
     * @param args arguments
     * @throws IOException IOException might be thrown
     */
    public static void main(String[] args) throws IOException {
        int size;
        System.out.print("Enter size of hash table (bigger than 0): ");
        size = getInt();
        MyHashTable theHashTable;
        if (size == 0) {
            theHashTable = new MyHashTable();
        } else {
            theHashTable = new MyHashTable(size);
        }
        int count = 6426;
        while(count > 0) {
            char[] repeat = new char[6427 - count];
            Arrays.fill(repeat, 'c');
            if (count == 4095) {
                theHashTable.insert(null);
            };
            theHashTable.insert(new String(repeat));
            count --;
        }
    }

    /**
     * Returns String value from the console.
     * @return String value from the console input
     * @throws IOException IOException might be thrown
     */
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    /**
     * Returns int value from the console.
     * @return int value form the console input
     * @throws IOException IOException might be thrown
     */
    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }

}

/*
 * Expected result (with the initial size of 0)
 *
 * Rehashing 6 items, new size is 23
 *
 * Number of collisions: 3
 * Number of items: 9
 *
 * Table: ** ** ** ** ** ** ** ** ** [increase, 1] [creeping, 2] ** ** ** [seventh, 1] [everything, 1] [ourselves, 1] [learned, 2] [himself, 1] [finished, 1] [receive, 1] ** **
 * Found: 'finished'
 *
 * Hash value of 'increase': 9
 * Hash value of 'creeping': 9
 * Hash value of 'everything': 15
 * Hash value of 'ourselves': 16
 * Hash value of 'himself': 18
 * Hash value of 'finished': 19
 * Hash value of 'seventh': 14
 * Hash value of 'learned': 15
 * Hash value of 'receive': 18
 * Number of items: 8
 * Frequency of 'learned': 2
 * Frequency of 'Learned': 0
 *
 * Table: ** ** ** ** ** ** ** ** ** [increase, 1] [creeping, 2] ** ** ** #DEL# [everything, 1] [ourselves, 1] [learned, 2] [himself, 1] [finished, 1] [receive, 1] ** **
 */

/*
 * Expected result (with the initial size of 3)
 *
 * Rehashing 2 items, new size is 7
 * Rehashing 4 items, new size is 17
 * Rehashing 9 items, new size is 37
 *
 * Number of collisions: 1
 * Number of items: 9
 *
 * Table: ** ** ** [finished, 1] ** ** [receive, 1] [learned, 2] ** ** [increase, 1] ** [himself, 1] ** ** ** ** [seventh, 1] ** ** ** ** ** ** [ourselves, 1] ** ** ** ** ** [creeping, 2] [everything, 1] ** ** ** ** **
 * Found: 'finished'
 *
 * Hash value of 'increase': 10
 * Hash value of 'creeping': 30
 * Hash value of 'everything': 30
 * Hash value of 'ourselves': 24
 * Hash value of 'himself': 12
 * Hash value of 'finished': 3
 * Hash value of 'seventh': 17
 * Hash value of 'learned': 7
 * Hash value of 'receive': 6
 * Number of items: 8
 * Frequency of 'learned': 2
 * Frequency of 'Learned': 0
 *
 * Table: ** ** ** [finished, 1] ** ** [receive, 1] [learned, 2] ** ** [increase, 1] ** [himself, 1] ** ** ** ** #DEL# ** ** ** ** ** ** [ourselves, 1] ** ** ** ** ** [creeping, 2] [everything, 1] ** ** ** ** **
 */

/*
 * Expected result (with the initial size of 40)
 *
 * Number of collisions: 1
 * Number of items: 9
 *
 * Table: [himself, 1] ** ** [learned, 2] ** ** ** ** ** ** ** ** ** ** ** ** ** [seventh, 1] ** ** ** ** ** ** ** [creeping, 2] [ourselves, 1] ** ** ** ** ** ** ** [increase, 1] [finished, 1] [receive, 1] ** ** [everything, 1]
 * Found: 'finished'
 *
 * Hash value of 'increase': 34
 * Hash value of 'creeping': 25
 * Hash value of 'everything': 39
 * Hash value of 'ourselves': 26
 * Hash value of 'himself': 0
 * Hash value of 'finished': 34
 * Hash value of 'seventh': 17
 * Hash value of 'learned': 3
 * Hash value of 'receive': 35
 * Number of items: 8
 * Frequency of 'learned': 2
 * Frequency of 'Learned': 0
 *
 * Table: [himself, 1] ** ** [learned, 2] ** ** ** ** ** ** ** ** ** ** ** ** ** #DEL# ** ** ** ** ** ** ** [creeping, 2] [ourselves, 1] ** ** ** ** ** ** ** [increase, 1] [finished, 1] [receive, 1] ** ** [everything, 1]
 */
