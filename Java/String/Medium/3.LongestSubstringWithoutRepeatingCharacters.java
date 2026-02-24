class Solution {
    public int lengthOfLongestSubstring(String s) {

        // left pointer → start of sliding window
        int left = 0;

        // stores the maximum length found so far
        int maxLength = 0;

        // HashSet to store unique characters inside current window
        HashSet<Character> set = new HashSet<>();

        // i → right pointer of sliding window
        for(int i = 0; i < s.length() ; i++){

            // If current character already exists in window
            // shrink the window from left side
            while(set.contains(s.charAt(i))){
                
                // Remove character at left pointer
                set.remove(s.charAt(left));
                
                // Move left pointer forward
                left++;
            }

            // Add current character to window
            set.add(s.charAt(i));

            // Update max length
            maxLength = Math.max(maxLength, i - left + 1);
        }

        return maxLength;
    }
}