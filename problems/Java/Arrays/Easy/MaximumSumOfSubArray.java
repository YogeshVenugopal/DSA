package problems.Java.Arrays;

public class MaximumSumOfSubArray {

    public  static int Solution(int[] nums){
        int currentSum = nums[0];
        int maxSum = nums[0];

        for(int i = 1; i < nums.length; i++){
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args){
        int[] arr = {1};
        System.out.print("Maximum Sum Of Arrays : "+ Solution(arr));
    }
}
