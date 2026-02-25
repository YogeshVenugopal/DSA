class Solution {
    public String convert(String s, int numRows) {
        // Check the string length and number of rows to determine if conversion is necessary
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }
        // Initialize an array of StringBuilder to hold the characters for each row
        StringBuilder[] rows = new StringBuilder[numRows];

        // Create a StringBuilder for each row
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        // currentRow keeps track of the current row index, and goingDown indicates the direction of traversal
        int currentRow = 0;
        boolean goingDown = false;

        // Iterate through each character in the input string and append it to the appropriate row
        for (char c : s.toCharArray()) {

            // Append the current character to the current row
            rows[currentRow].append(c);

            // Change direction when we reach the top or bottom row
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }

            // Move to the next row based on the current direction
            currentRow += goingDown ? 1 : -1;
        }

        // Combine all rows into a single string
        StringBuilder result = new StringBuilder();

        // Append each row's StringBuilder to the result
        for (StringBuilder row : rows) {
            result.append(row);
        }
        // Convert the StringBuilder to a String and return it
        return result.toString();
    }
}