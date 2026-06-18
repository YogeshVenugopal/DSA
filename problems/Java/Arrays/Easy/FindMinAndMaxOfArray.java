package problems.Java.Arrays;

public class FindMinAndMaxOfArray{

    public static int maximum(int[] arr){
        
        if(arr.length == 0) return -1;
        else if(arr.length == 1) return arr[0];
        
        int max = arr[0];

        for(int i = 1; i < arr.length; i++) if(arr[i] > max) max = arr[i];

        return max;
    }

    public static int minimum(int[] arr){
        
        if(arr.length == 0) return -1;
        else if(arr.length == 1) return arr[0];

        int min = arr[0];
        
        for(int i = 1; i < arr.length; i++) if(arr[i] < min) min = arr[i];

        return min;
    }

    public static void main(String[] args){
        int[] arr = {1, 2, 3, 4, 5, 6 , 7};

        System.out.println(maximum(arr));
        System.out.println(minimum(arr));
    }
}