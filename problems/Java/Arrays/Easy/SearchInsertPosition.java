package problems.Java.Arrays.Easy;

class SearchInsertPosition {
    public static int searchInsert(int[] nums, int target) {
        int idx = 0;

        while(idx < nums.length){
            if(nums[idx] == target) return idx;
            else if(nums[idx] > target) return idx;
            idx++;
        }

        return idx;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int target = 5;
        System.out.println(searchInsert(nums, target)); // Output: 2

        target = 2;
        System.out.println(searchInsert(nums, target)); // Output: 1

        target = 7;
        System.out.println(searchInsert(nums, target)); // Output: 4

        target = 0;
        System.out.println(searchInsert(nums, target)); // Output: 0
    }
}