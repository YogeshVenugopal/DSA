class Solution {
    public String intToRoman(int num) {
        // Store Roman numeral values
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        // Store matching Roman symbols
        String[] symbols = {"M", "CM", "D", "CD","C", "XC", "L", "XL","X", "IX", "V", "IV","I"};

        // Create a result container
        StringBuilder answer = new StringBuilder();

        //Run a loop for each value of Roman numeral values
        for(int i=0; i< values.length; i++){
            // Check if the value fits in the number
            // Repeat until number becomes zero
            while(num >= values[i]){
                answer.append(symbols[i]);
                num -= values[i];
            }
        }
        // Return the answer in string format
        return answer.toString();
    }
}