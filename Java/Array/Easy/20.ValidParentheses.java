class Solution {
    public boolean isValid(String s) {
        // Create a stack to store the open parenthesis
        Stack<Character> ch_stack = new Stack<>();

        // Create a loop for each values
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            // If the string is open then add to the stack
            if(ch == '(' || ch == '{' || ch == '['){
                ch_stack.push(ch);
            }

            // Else check the string
            else{

                if(ch_stack.isEmpty()) return false;
                // Get the top value
                char top = ch_stack.pop();
                // Check the top value with last value in the stack
                if(ch == ')' && top != '(' ||
                   ch == '}' && top != '{' ||
                   ch == ']' && top != '['){
                    return false;
                }
            }
        }
        return ch_stack.isEmpty();
    }
}