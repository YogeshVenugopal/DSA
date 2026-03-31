import java.util.ArrayList;
import java.util.HashSet;

public class FindAllDuplicates {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 2, 3, 4};
        HashSet<Integer> table = new HashSet<>();
        ArrayList<Integer> duplicates = new ArrayList<>();

        for (int num : arr) {
            if (table.contains(num)) {
                duplicates.add(num);
            } else {
                table.add(num);
            }
        }
        System.out.println("Duplicate elements: " + duplicates);
    }
}
