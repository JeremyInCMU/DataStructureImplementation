/**
 * 08722 Data Structures for Application Programmers.
 * Lab 3 Simple Sorting and Stability
 *
 * Selection Sort Implementation
 *
 * Andrew ID:
 * @author
 */
public class SelectionSortApp {

    /**
     * test selection sort and its stability.
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

        System.out.println("Before Selection Sorting: ");
        for (Employee e : list) {
            System.out.println(e);
        }
        System.out.println("");

        selectionSort(list, "last");

        System.out.println("After Selection Sorting by last name: ");
        for (Employee e : list) {
            System.out.println(e);
        }
        System.out.println("");

        selectionSort(list, "zip");

        System.out.println("After Selection Sorting by zip code: ");
        for (Employee e : list) {
            System.out.println(e);
        }
    }

    /**
     * Sorts employees either by last name or zip using Selection Sort.
     * @param list list of employee objects
     * @param key key param value should be either "last" or "zip"
     */
    public static void selectionSort(Employee[] list, String key) {
        // TODO implement selection sort here
        for (int i = 0; i < list.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.length; j++) {
                if (key.equals("last") && 
                    compareByLastName(list[minIndex], list[j]) > 0) {
                    minIndex = j;
                } else if (key.equals("zip") && 
                    list[minIndex].getZipCode() - list[j].getZipCode() > 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                swap(list, i, minIndex);
            }
        }
    }

    /**
     * private helper method to swap elements in an array.
     * @param list list of employee objects
     * @param one first index position
     * @param two second index position
     */
    private static void swap(Employee[] list, int one, int two) {
        Employee temp = list[one];
        list[one] = list[two];
        list[two] = temp;
    }

    // Comparator or comparable can be used here to simplify the process
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

        if (minLength < lastSecond.length()) { // Separate string into two parts
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
