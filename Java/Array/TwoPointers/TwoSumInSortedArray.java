package Java.Array.TwoPointers;

public class TwoSumInSortedArray {

    public static int[] TwoSum(int[] arr, int target) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1};
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int target = 17;
        int[] result = TwoSum(arr, target);

        for (int i : result) {
            System.err.print(i + " ");
        }
    }
}
