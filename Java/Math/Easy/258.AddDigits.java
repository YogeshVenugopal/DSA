
class Solution {
    public int addDigits(int num) {
        // Create a loop and it's run until the number become single digit
        while(num >= 10){
            // Declare the variable for the mathematic opertions
            int sum = 0;
            // Adding another loop for sum the last digit in the number
            while(num > 0){
                sum += num % 10;
                num /= 10;
            }
            // Set the number as the sum value
            // Case - 1: if the number is still two digit then it goes once again
            // Case - 2: if the number is comes to single digit then it exit from the loop
            num = sum;
        }
        // return the answer
        return num;
    }
}