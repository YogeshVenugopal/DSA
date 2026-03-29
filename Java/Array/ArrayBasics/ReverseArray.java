public class ReverseArray {
    public static void solution(int[] arr){
        int start = 0;
        int end = arr.length - 1;
        int temp;
        while(start < end){
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        for(int j : arr) System.out.print(j + " ");
    }
    public static void main(String[] args){
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Original Array:");
        for(int j : arr) System.out.print(j + " ");
        System.out.println();
        System.out.println("Reversed Array:");
        solution(arr);
    }
}
