
public class SquareOfSortedArray {

    public static int[] SquareSorted(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {-4, -1, 0, 5, 10};

        nums = SquareSorted(nums);

        for (int num : nums) {
            System.out.println(num + "");
        }
    }
}
