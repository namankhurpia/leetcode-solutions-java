/**
 * // This is the ImmutableListNode's API interface.
 * // You should not implement it, or speculate about its implementation.
 * interface ImmutableListNode {
 *     public void printValue(); // print the value of this node.
 *     public ImmutableListNode getNext(); // return the next node.
 * };
 */

 //BRUTE FORCE SOLUTION - O(n) space and time
 class Solution {
    public void printLinkedListInReverse(ImmutableListNode head) {
        ArrayList<ImmutableListNode> arr = new ArrayList<ImmutableListNode>();
        while(head!=null)
        {
            arr.add(head);
            head = head.getNext();
        }
        
        for(int i=arr.size()-1;i>=0;i--)
        {
            arr.get(i).printValue();
        }
    }
}

//OPTIMISED SOLUTION - constant space and time complexity 
