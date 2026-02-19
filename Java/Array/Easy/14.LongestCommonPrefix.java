class Solution {
    public String longestCommonPrefix(String[] strs) {
        // Checks the strs Array is NULL or the length of the strs Array is equal to zero..
        // If the strs is NULL or length is zero then return empty String.
        if(strs == null || strs.length == 0){
            return "";
        }

        // Get the first word from the Array
        String word = strs[0];

        // Create the loop based on the length of the first word
        for(int i=0; i<word.length(); i++){
            // Get the character from the taken word
            char ch = word.charAt(i);

            // Create the loop for the each string in the array
            for(int j=1; j<strs.length; j++){

                // First check the length of the substring is greater then actual word
                // Second check the current character is not equal to the next word character
                if(i >= strs[j].length() || ch != strs[j].charAt(i)){

                    // Slice the word and return the value
                    return word.substring(0,i);
                }
            }
        }
        // Return the whole word if the all the conditions are satisfied
        return word;
    }
}