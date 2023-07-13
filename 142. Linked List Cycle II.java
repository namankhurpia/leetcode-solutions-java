//brute force solution -

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
    public ListNode detectCycle(ListNode head) {

        HashSet<ListNode> set = new HashSet<ListNode>();
        while(head!=null)
        {
            if(set.contains(head))
            {
                return head;
            }
            else
            {
                set.add(head);
            }
            head = head.next;
        }

        

        return null;

    }
}

//optimal approach time - O(n);  space - O(1)
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
    public ListNode detectCycle(ListNode head) {

        if(head==null || head.next==null )
            return null;

        ListNode ref = head;
        ListNode slow = head;
        ListNode fast = head;

        

        while(fast.next!=null && fast.next.next!=null  )
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast)
            {
                    while(slow!=ref)
                    {
                        ref = ref.next;
                        slow = slow.next;
                    }
                    return ref;
            }
            
        }
        return null;

    }
}