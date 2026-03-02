import java.util.Arrays;

class Solution {
    public int[] plusOne(int[] digits) {
        // Get the length of the input array
        int n = digits.length;

        // Iterate through the array from the end to the beginning
        for (int i = n - 1; i >= 0; i--) {
            // If the current digit is less than 9, increment it and return the array
            if (digits[i] < 9) {
                // Increment the current digit
                digits[i]++;
                return digits;
            }
            // If the current digit is 9, set it to 0 and continue to the next iteration
            digits[i] = 0;
        }
        
        // If we have reached this point, it means all digits were 9, so we need to create a new array
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }
}