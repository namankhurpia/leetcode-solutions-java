//optimal O(n)

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        
        ListNode mynode = new ListNode(-1);
        ListNode prev = mynode;
        while(list1 != null && list2 != null)
        {
            
            if(list1.val < list2.val)
            {
                prev.next = list1;
                list1 = list1.next;
            }
            else
            {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;

        }
        prev.next = list1 == null ? list2:list1;
        return mynode.next;

    }
}