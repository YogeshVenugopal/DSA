public class SecondLargestElementInArray {
    public static int solution(int[] arr){
        int largest = arr[0];
        int secondLargest = -1;
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > largest){
                secondLargest = largest;
                largest = arr[i];
            }
            else if(secondLargest < arr[i] && arr[i] != largest) secondLargest = arr[i];
        }
        return secondLargest;
    }
    public static void main(String[] args){
        int[] arr = { 5, 5};
        if(solution(arr) == -1) System.out.println("No second largest element in the array");
        else System.out.print("Second largest element in array is "+ solution(arr));
    }
}
