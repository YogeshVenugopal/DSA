class Solution {
    public String longestCommonPrefix(String[] strs) {

        // Step 1: Edge case check
        // If array is empty or null → no prefix exists
        if(strs == null || strs.length == 0){
            return "";
        }

        // Step 2: Take first word as reference
        String word = strs[0];

        // Step 3: Loop through each character of first word
        for(int i = 0; i < word.length(); i++){

            // Current character to compare
            char ch = word.charAt(i);

            // Step 4: Compare with same position in all other words
            for(int j = 1; j < strs.length; j++){

                // If index exceeds length OR characters mismatch
                // → prefix ends here
                if(i >= strs[j].length() || ch != strs[j].charAt(i)){
                    return word.substring(0, i);
                }
            }
        }

        // Step 5: If loop completes → entire word is common prefix
        return word;
    }
}
