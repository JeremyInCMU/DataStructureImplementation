import java.util.Comparator;
/**
 * 08722 Data Structures for Application Programmers.
 * Lab 3 Simple Sorting and Stability
 *
 * Insertion Sort Implementation
 *
 * Andrew ID: jiamingx
 * @author Jiaming Xiao
 */
public class InsertionSortApp {

    /**
     * test insertion sort and its stability.
     * @param args arguments
     */
    public static void main(String[] args) {
        Employee[] list = new Employee[10];

        // employee data : first name, last name, zip
        list[0] = new Employee("Patty", "Evans", 15213);
        list[1] = new Employee("Doc", "Smith", 15214);
        list[2] = new Employee("Lorraine", "Smith", 15216);
        list[3] = new Employee("Paul", "Smith", 15216);
        list[4] = new Employee("Tom", "Yee", 15216);
        list[5] = new Employee("Sato", "Hashimoto", 15218);
        list[6] = new Employee("Henry", "Stimson", 15215);
        list[7] = new Employee("Jose", "Vela", 15211);
        list[8] = new Employee("Minh", "Vela", 15211);
        list[9] = new Employee("Lucinda", "Craswell", 15210);

        System.out.println("Before Insertion Sorting: ");
        for (Employee e : list) {
            System.out.println(e);
        }
        System.out.println("");

        insertionSort(list, "last");

        System.out.println("After Insertion Sorting by last name: ");
        for (Employee e : list) {
            System.out.println(e);
        }
        System.out.println("");

        insertionSort(list, "zip");

        System.out.println("After Insertion Sorting by zip code: ");
        for (Employee e : list) {
            System.out.println(e);
        }

    }

    /**
     * Sorts employees either by last name or zip using Insertion Sort.
     * @param list list of employee objects
     * @param key key param value should be either "last" or "zip"
     */
    public static void insertionSort(Employee[] list, String key) {
        // TODO implement insertion sort here
        for (int i = 1; i < list.length; i++) {
            int j = i;
            while (j > 0) {
                if (key.equals("last")) { 
                    if (compareByLastName(list[i],list[j - 1]) >= 0) {
                        break;
                    }
		} else if (key.equals("zip")) {
                    if (list[i].getZipCode() - list[j - 1].getZipCode() >= 0) {
                        break;
                    } 
                }
                j--;
            }
            Employee temp = list[i];
            System.arraycopy(list, j, list, j + 1, i - j);
            list[j] = temp;
        }
    }

    /**
     * compare by last name.
     * @param first the first employee
     * @param second the second employee
     * @return 1 if the first is larger, 0 equality and -1 if the first is smaller
     */
    private static int compareByLastName (Employee first, Employee second) {
        String lastFirst = first.getLastName();
        String lastSecond = second.getLastName();
        int minLength = lastFirst.length();

        if (minLength > lastSecond.length()) { // Separate string into two parts
           minLength = lastSecond.length();
        }

        for (int i = 0; i < minLength; i++) { // Check common part of two strings
           if (lastFirst.charAt(i) - lastSecond.charAt(i) > 0) {
               return 1;
           } else if (lastFirst.charAt(i) - lastSecond.charAt(i) < 0) {
               return -1;
           }
        }
            
        if (lastFirst.length() > lastSecond.length()) { // Check extended part
            return 1;
        } else if (lastFirst.length() == lastSecond.length()){
            return 0;
        } else {
            return -1;
        }
     }
}
