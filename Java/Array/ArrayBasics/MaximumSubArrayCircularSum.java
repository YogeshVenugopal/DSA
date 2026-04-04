
public class MaximumSubArrayCircularSum {

    public static int findMaxCircularSum(int[] arr) {

        int totalSum = 0;

        int maxSum = arr[0];
        int currentMax = arr[0];

        int minSum = arr[0];
        int currentMin = arr[0];

        for (int i = 1; i < arr.length; i++) {

            currentMax = Math.max(arr[i], currentMax + arr[i]);
            maxSum = Math.max(maxSum, currentMax);

            currentMin = Math.min(arr[i], currentMin + arr[i]);
            minSum = Math.min(minSum, currentMin);

            totalSum += arr[i];
        }

        totalSum += arr[0];

        if (maxSum < 0) {
            return maxSum;
        }
        return Math.max(maxSum, totalSum - minSum);
    }

    public static void main(String[] args) {
        int[] arr = {5, -2, 5};
        int maxCircularSum = findMaxCircularSum(arr);
        System.out.println("Maximum circular subarray sum is: " + maxCircularSum);
    }
}
