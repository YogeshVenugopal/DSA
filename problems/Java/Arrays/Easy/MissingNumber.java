package problems.Java.Arrays.Easy;

class MissingNumber {
    public static int missingNumber(int[] nums) {
        int n = nums.length;
        int totalSum = (n * (n + 1)) / 2;
        int actualSum = Arrays.stream(nums).sum();
        return totalSum - actualSum;
    }

    public static void main(String[] args) {
        int[] nums = {3, 0, 1};
        System.out.println("Missing number: " + missingNumber(nums)); // Output: 2
    }
}