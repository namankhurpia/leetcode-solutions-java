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
//BRUTE FORCE SOLUTION


class Solution {
    public boolean isPalindrome(ListNode head) {

            ArrayList<Integer> arr = new ArrayList<>();
            while(head!=null)
            {
                arr.add(head.val);
                head = head.next;
            }
            ArrayList<Integer> arr_rev = new ArrayList<>(arr);
            Collections.reverse(arr_rev);
            if(arr.equals(arr_rev))
            
            {
                return true;
            }
            else
            {
                return false;
            }
            
        
    }

}

//Most Optimal approach - o(n)

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
    public boolean isPalindrome(ListNode head) {

            ListNode slow = head;
            ListNode fast = head;

            while(fast!=null && fast.next!=null &&  fast.next.next!=null)
            {
                slow = slow.next;
                fast = fast.next.next;
            }

            slow.next = reverselist(slow.next);
            slow = slow.next;

            while(slow!=null)
            {
                if(slow.val!=head.val)
                {
                    return false;
                }
               
                head = head.next;
                slow = slow.next;
            }
            return true;
 
        
    }

    public ListNode reverselist(ListNode head)
    {
        ListNode curr = head;
        ListNode prev = null;
        ListNode nxt = null;

        while(curr!=null)
        {
            nxt = curr.next;
            curr.next = prev;

            prev = curr;
            curr = nxt;
        }
        return prev;
    }
}