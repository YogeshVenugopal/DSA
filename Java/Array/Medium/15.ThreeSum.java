import java.util.*;

public class MainClass {

    public List<List<Integer>> threeSum(int[] nums) {

        // This list will store all unique triplets
        List<List<Integer>> result = new ArrayList<>();

        // Step 1: Sort the array
        // Sorting helps in using two pointers and avoiding duplicates
        Arrays.sort(nums);

        // Step 2: Fix one element at a time
        // We stop at length - 2 because we need at least 3 numbers
        for (int i = 0; i < nums.length - 2; i++) {

            // Step 3: Skip duplicate fixed elements
            // If current element is same as previous, skip it
            // This prevents duplicate triplets
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            // Step 4: Initialize two pointers
            int left = i + 1;               // next element after i
            int right = nums.length - 1;    // last element

            // Step 5: Use two pointer approach
            while (left < right) {

                // Calculate sum of three numbers
                int sum = nums[i] + nums[left] + nums[right];

                // Case 1: Found valid triplet
                if (sum == 0) {

                    // Add triplet to result list
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Step 6: Skip duplicate values for left pointer
                    // Avoid repeating same second number
                    while (left < right && nums[left] == nums[left + 1])
                        left++;

                    // Skip duplicate values for right pointer
                    // Avoid repeating same third number
                    while (left < right && nums[right] == nums[right - 1])
                        right--;

                    // Move both pointers inward
                    left++;
                    right--;
                }

                // Case 2: Sum is too small
                // Move left pointer to increase sum
                else if (sum < 0) {
                    left++;
                }

                // Case 3: Sum is too large
                // Move right pointer to decrease sum
                else {
                    right--;
                }
            }
        }

        // Return final list of unique triplets
        return result;
    }
}