//OPTIMISED SOLUTION

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {

        ListNode res = head;
        
        while(res!=null && res.next!=null)
        {
            if(res.next.val == res.val)
            {
                res.next = res.next.next;
            }
            else
            {
                res = res.next;
            }
            
        }
        
            
        return head;
        
    }
}