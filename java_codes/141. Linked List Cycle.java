//OPTIMISED SOLUTION - BEATS 100

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        
        ListNode head1 = head;
        ListNode head2 = head;
        if(head == null)
        {
            return false;
        }
        
        while( head2!=null && head2.next!=null)
        {
            head1 = head1.next;
            head2 = head2.next.next;
            if(head1 == head2)
            {
                return true;
            }
        }
        return false;
    }
}