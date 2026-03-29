public class MoveAllZero {
    public static void ShiftedArray(int[] arr){
        int prevPos = 0;
        int temp;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != 0){
                temp = arr[i];
                arr[i] = arr[prevPos];
                arr[prevPos] = temp;
                prevPos++;
            }
        }
    }
    public static void main(String[] args){
        int[] arr = {0, 0, 0, 0, 0, 1, 0};
        ShiftedArray(arr);
        for(int i: arr) System.out.print(i + " ");
    }
}
