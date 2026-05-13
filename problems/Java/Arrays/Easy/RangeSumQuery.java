package problems.Java.Arrays.Easy;


class RangeSumQuery {

    private int[] prevArray;
    public static RangeSumQuery(int[] nums) {
        prevArray = new int[nums.length];

        if(nums.length == 0) return;
        prevArray[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            prevArray[i] = prevArray[i-1] + nums[i];
        }
    }
    
    public static int sumRange(int left, int right) {
        if(left == 0){
            return prevArray[right];
        }
        return prevArray[right] - prevArray[left-1];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        RangeSumQuery rangeSumQuery = new RangeSumQuery(nums);
        System.out.println("Sum of range (0, 2): " + rangeSumQuery.sumRange(0, 2)); // Output: 1
        System.out.println("Sum of range (2, 5): " + rangeSumQuery.sumRange(2, 5)); // Output: -1
        System.out.println("Sum of range (0, 5): " + rangeSumQuery.sumRange(0, 5)); // Output: -3
    }
}
