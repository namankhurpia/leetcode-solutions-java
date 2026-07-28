# Definition for singly-linked list.

from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

    def __str__(self):
        result = []
        current = self
        while current:
            result.append(str(current.val))
            current = current.next
        return " -> ".join(result)


class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        
        prev = None
        curr = head

        while(curr):
            nxt = curr.next
            curr.next = prev
            prev = curr
            curr = nxt
        
        return prev


if __name__ == "__main__":
    sol = Solution()
    mynode = ListNode(1)
    mynode.next = ListNode(2)
    mynode.next.next = ListNode(3)
    print(mynode)
    print(sol.reverseList(mynode))



