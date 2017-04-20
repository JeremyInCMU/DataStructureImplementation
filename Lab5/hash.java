public class hash {
    public static void main(String[] args) {
        String[] array = {"I", "am", "a", "cat", "sdjfkldfjldkfjjklkjljkllkkjsdjfks"};
	for(int i = 0; i < array.length; i++) {
            System.out.println(hashCode(array[i]));
        }
    }

    public static int hashCode(String input) {
        int hash = 0;
        int k = Math.max(1, input.length() / 8);
        for(int i = 0; i < input.length(); i += k) {
            hash = (hash * 31) + input.charAt(i);
        }
        return hash;
    }
}
