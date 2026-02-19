class Solution {
    public int maxArea(int[] height) {

        // Step 1: Initialize two pointers
        // 'left' starts from the beginning of the array
        // 'right' starts from the end of the array
        int left = 0;
        int right = height.length - 1;

        // Step 2: Variable to store the maximum area found so far
        int maxArea = 0;

        // Step 3: Continue until the two pointers meet
        while (left < right) {

            // Step 4: Width of the container is the distance between pointers
            int width = right - left;

            // Step 5: Height of the container is the smaller of the two heights
            int minHeight = Math.min(height[left], height[right]);

            // Step 6: Calculate current area
            int area = width * minHeight;

            // Step 7: Update maximum area if current area is larger
            maxArea = Math.max(maxArea, area);

            // Step 8: Move the pointer pointing to the smaller height
            // because moving the larger one will not increase the area
            if (height[left] < height[right]) {
                left++;   // Move left pointer inward
            } else {
                right--;  // Move right pointer inward
            }
        }

        // Step 9: Return the maximum area found
        return maxArea;
    }
}
