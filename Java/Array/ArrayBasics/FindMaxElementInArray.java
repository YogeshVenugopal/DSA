public class FindMaxElementInArray {
    public static void main(String[] args) {
        int[] arr = { 3, 5, 7, 2, 8, 1 };
        int maxElement = findMaxElement(arr);
        System.out.println("Maximum Element in the Array: " + maxElement);
    }

    public static int findMaxElement(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}
