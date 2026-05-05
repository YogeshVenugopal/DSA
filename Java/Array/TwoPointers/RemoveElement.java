package Java.Array.TwoPointers;

public class RemoveElement {

    public static int[] ShiftElement(int[] arr, int val) {
        int first = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != val) {
                arr[first] = arr[i];
                first++;
            }
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;

        int[] result = ShiftElement(arr, val);

        for (int n : result) {
            System.out.print(n + " ");
        }
    }
}
