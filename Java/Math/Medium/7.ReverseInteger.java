class Solution {
    public int reverse(int x) {

        // Step 1: Initialize variable to store the reversed number
        int rev = 0;

        // Step 2: Loop until all digits of x are processed
        while (x != 0) {

            // Step 3: Extract the last digit of x
            int digit = x % 10;

            // Step 4: Remove the last digit from x
            x /= 10;

            // Step 5: Check for overflow (positive side)
            // If rev * 10 exceeds Integer.MAX_VALUE, return 0
            if (rev > Integer.MAX_VALUE / 10 ||
               (rev == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }

            // Step 6: Check for underflow (negative side)
            // If rev * 10 goes below Integer.MIN_VALUE, return 0
            if (rev < Integer.MIN_VALUE / 10 ||
               (rev == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }

            // Step 7: Append the extracted digit to the reversed number
            rev = rev * 10 + digit;
        }

        // Step 8: Return the final reversed integer
        return rev;
    }
}