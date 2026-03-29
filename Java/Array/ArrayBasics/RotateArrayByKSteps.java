public class RotateArrayByKSteps {
    public static int[] solution(int[] arr, int k){
        int len = arr.length;
        int[] ans = new int[len];
        for(int i = 0;i < len; i++){
            ans[(i+k)%len] = arr[i];
        }
        return ans;
    }
    public static void main(String[] args){
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k = 4;
        int[] newArr = solution(arr, k);
        for(int j : newArr) System.out.print(j + " ");
    }
}
