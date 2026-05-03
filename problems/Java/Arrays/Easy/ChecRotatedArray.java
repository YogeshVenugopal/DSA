package problems.Java.Arrays.Easy;

class CheckRotatedArray {
    public static boolean check(int[] nums) {
        int b = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] > nums[(i+1) % nums.length]) b++;
        }

        return b <= 1;
    }


    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2};
        System.out.println("Is the array rotated sorted? " + check(nums)); // Output: true

        int[] nums2 = {2, 1, 3, 4};
        System.out.println("Is the array rotated sorted? " + check(nums2)); // Output: false
    }
}