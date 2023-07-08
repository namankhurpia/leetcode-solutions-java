//brute force solution - o(n)^2

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

           while(headA!=null)
           {
               ListNode newhead = headB;
               while(newhead!=null)
               {
                   if(newhead==headA) return newhead;

                   newhead = newhead.next;
               }
               headA = headA.next;
           }
           return null;
        
        
    }
}


//optimal solution O(n+m)
//using hashsets


public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

           HashSet<ListNode> set= new HashSet<ListNode>();
           ListNode B = headB;
           while(B!=null)
           {
               set.add(B);
               B = B.next;
           }

           while(headA!=null)
           {
               if(set.contains(headA)) return headA;
               headA = headA.next;
           }
           return null;
        
        
    }
}

//using 2 pointer approach

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

           ListNode A = headA;
           ListNode B = headB;

           while(A!=B)
           {
               if(A==null && B==null)
               {
                   return null;
               }
               
               if(A==null)
               {
                   A=headB;
               }
               else
               {
                   A = A.next;
               }


               if(B==null)
               {
                   B=headA;
               }
               else
               {
                   B = B.next;
               }
           }

           return A;
        
        
    }
}
