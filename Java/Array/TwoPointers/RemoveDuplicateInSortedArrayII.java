package Java.Array.TwoPointers;

public class RemoveDuplicateInSortedArrayII {

    public static int removeDuplicates(int[] nums) {
        int k = 0;
        for (int num : nums) {
            if (k < 2 || nums[k - 2] != num) {
                nums[k] = num;
                k++;
            }
        }

        return k;

    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 2, 3, 3, 4, 5};
        System.out.println(removeDuplicates(arr));
    }
}
