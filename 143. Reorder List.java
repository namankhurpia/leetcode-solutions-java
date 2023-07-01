//time  - O(n)and space -O(n)

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
    public void reorderList(ListNode head) {
        
        ListNode slow = head;
        ListNode fast = head;

        while(fast!=null && fast.next!=null)
        {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode second = slow.next;
        //reversing the second second_half
        
        ListNode prev = null;
        slow.next = null;

        while(second!=null)
        {
            ListNode temp  = second.next;
            second.next = prev;
            prev = second;
            second = temp;

        }

        //merging the lists - prev and head
        ListNode first = head;
        second = prev;
        while(second!=null)
        {
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;
            first.next = second;
            second.next = temp1;
            first = temp1;
            second = temp2;

        }



    }
}