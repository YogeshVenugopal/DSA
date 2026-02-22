class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        // Dummy node to store result
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        int carry = 0;
        
        // Traverse until both lists are null and no carry left
        while (l1 != null || l2 != null || carry != 0) {
            
            int sum = carry;  // Start with previous carry
            
            // Add l1 value if exists
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            
            // Add l2 value if exists
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            
            // Calculate new digit and carry
            carry = sum / 10;        // carry for next iteration
            int digit = sum % 10;    // digit to store in node
            
            // Create new node with digit
            current.next = new ListNode(digit);
            current = current.next;
        }
        
        return dummy.next;  // Return actual head
    }
}