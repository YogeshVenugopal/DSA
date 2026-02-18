class Solution {
    public int getSum(int a, int b) {
        int carry;
        while (b != 0) {
            // Calculate the carry bits
            carry = a & b;
            // Calculate the sum without carry, stores in 'a'
            a = a ^ b;
            // Shift the carry left for the next iteration, stores in 'b'
            b = carry << 1;
        }
        return a;
    }
}