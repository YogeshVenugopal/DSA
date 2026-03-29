public class FindMinimumElementInArray {

    public static int solution(int[] arr){
        int min = arr[0];
        for(int j : arr) if(j < min) min = j;
        return min;
    }

    public static void main(String[] args){
        int[] arr = {8, 2, 1, 0, 6, 3};
        System.out.print("Minimum element in the array is: " + solution(arr));
    }
}
