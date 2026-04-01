
public class MaximunProductSubarray {

    public static void main(String[] args) {
        int[] arr = {-2, -3, -2, -4};

        int currentMax = arr[0];
        int currentMin = arr[0];
        int maxProduct = arr[0];

        for (int i = 1; i < arr.length; i++) {
            int num = arr[i];

            int temp1 = currentMax * num;
            int temp2 = currentMin * num;

            currentMax = Math.max(num, Math.max(temp1, temp2));
            currentMin = Math.min(num, Math.min(temp1, temp2));

            maxProduct = Math.max(maxProduct, currentMax);
        }

        System.out.println("Maximum product subarray is: " + maxProduct);
    }
}
