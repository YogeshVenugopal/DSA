class Solution {
    // Here is the simple program to check the word is palindrome or not
    // Using the two pointer concept
    public static boolean isPalindrome(String a) {
        // Here, l = left pointer
        // r = right pointer
        // eg. a = "bab"
        // +-----+-----+-----+
        // |     |     |     |
        // |  b  |  a  |  b  |
        // |     |     |     |
        // +-----+-----+-----+
        //     ^          ^
        //     l          r
    
        int l = 0, r = a.length() - 1;
        // Loop runs until the left and right pointers cross each other
        while (l < r) {
            // Check the characters are equal 
            // If it's not equal then it return false which means not a palindrome
            if (a.charAt(l++) != a.charAt(r--)) return false;
        }

        // If all the characters are equal then it is a palindrome and return true
        return true;
    }

    public String longestPalindrome(String s) {

        // Check the string length if it is zero then return empty string
        if (s.length() == 0) return "";
        // Take the first character in the string as a answer
        String ans = s.substring(0, 1);
        // Create two loops One is for start and another for end strings
        for (int j = 0; j < s.length(); j++) {
            for (int i = j + 1; i <= s.length(); i++) {
                String sub = s.substring(j, i);

                // Check the word is palindrome and that word length is greater than answer word length then set that word as a answer word
                if (isPalindrome(sub) && sub.length() > ans.length()) {
                    ans = sub;
                }
            }
        }
        return ans;
    }
}