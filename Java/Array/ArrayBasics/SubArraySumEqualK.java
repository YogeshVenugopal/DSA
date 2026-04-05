
import java.util.HashMap;

public class SubArraySumEqualK {

    public static int countSubarraysWithSumK(int[] arr, int k) {
        HashMap<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);
        int count = 0;
        int currentSum = 0;

        for (int num : arr) {
            currentSum += num;

            if (prefixSum.containsKey(currentSum - k)) {
                count += prefixSum.get(currentSum - k);
            }

            prefixSum.put(currentSum, prefixSum.getOrDefault(currentSum, 0) + 1);
        }

        return count;

    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1};
        int k = 2;

        System.out.println("Number of subarrays with sum equal to " + k + " is: " + countSubarraysWithSumK(arr, k));
    }
}
