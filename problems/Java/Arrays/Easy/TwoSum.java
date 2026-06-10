package problems.Java.Arrays;

import java.util.HashMap;

public class TwoSum {

    public static int[] twosums(int[] nums, int target) {
        HashMap<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];

            if (seen.containsKey(need)) {
                return new int[]{seen.get(need), i};
            }
            seen.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] ans = twosums(nums, target);
        for (int i : ans) {
            System.out.println(i + " ");
        }
    }
}
