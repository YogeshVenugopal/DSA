public class RemoveDuplicateInSortedArray {
    public static void removeDuplicates(int[] nums) {
        int slow = 0;
        for(int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
    }
    public static void main(String[] args){
        int[] arr = {1, 2 , 3, 3, 4, 5};
        removeDuplicates(arr);
        for(int i : arr) System.out.print(i+" ");
    }
}
