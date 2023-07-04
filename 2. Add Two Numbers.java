//Beats 100%  ==== O(n)

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        ListNode list = new ListNode(-1);
        ListNode curr = list;

        int carry = 0;
        while(l1!=null || l2!=null || carry!=0)
        {
            int f = (l1!=null)?l1.val:0;
            int s = (l2!=null)?l2.val:0;
            int sum = f+s+carry;
            carry = sum/10;
            curr.next = new ListNode(sum%10);
            curr = curr.next;
            if(l1!=null)
                l1 = l1.next;
            if(l2!=null)
                l2 = l2.next;

        }
        return list.next;

    }
}