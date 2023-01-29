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