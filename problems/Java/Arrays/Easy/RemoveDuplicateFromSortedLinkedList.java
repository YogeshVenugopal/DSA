public class RemoveDuplicateFromSortedLinkedList{
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    void add(int data) {
        ListNode newNode = new ListNode(data);
        if (head == null) {
            head = newNode;
        } else {
            ListNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    ListNode head;

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode current = head;

        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next; 
            } else {
                current = current.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        RemoveDuplicateFromSortedLinkedList list = new RemoveDuplicateFromSortedLinkedList();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);

        ListNode result = list.deleteDuplicates(list.head);

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}