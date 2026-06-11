package problems.Java.Arrays;

public class MoveAllZero {

    public static int[] Solution(int[] arr){
        int slow = 0;
        for(int fast = 0; fast < arr.length; fast++){
            if(arr[fast] != 0){
                int temp = arr[fast];
                arr[fast] = arr[slow];
                arr[slow] = temp;
                slow++;
            }
        }
        return arr;
    }

    public static void main(String[] args){
        int[] arr = {1, 0, 2, 3, 0, 0, 4, 5, 6, 0};
        int[] sol = Solution(arr);
        for(int i : sol) System.out.print(i + " ");
    }
}