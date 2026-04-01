public class KadaneAlgorithm {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int currentSum = 0;
        int maxSum = arr[0];

        for(int i = 0; i < arr.length; i++) {
            currentSum += arr[i];

            if(currentSum > maxSum) {
                maxSum = currentSum;
            }

            if(currentSum < 0) {
                currentSum = 0;
            }
        }

        System.out.println("Maximum subarray sum is: " + maxSum);
    }
}
