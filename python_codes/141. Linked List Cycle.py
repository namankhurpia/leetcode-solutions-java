# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

from typing import Optional

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

    def __str__(self):
        result = []
        curr = self
        while curr:
            result.append(str(curr.val))
            curr = curr.next

        return "->".join(result)

class Solution:
    def hasCycle(self, head: Optional[ListNode]) -> bool:

        slow , fast = head, head

        while(fast and fast.next):
            slow = slow.next
            fast = fast.next.next
            if slow is fast:
                return True
        
        return False

if __name__ == "__main__":
    sol = Solution()
    mynode = ListNode(1)
    mynode.next = ListNode(2)
    mynode.next.next = ListNode(3)
    print(mynode)
    print(sol.hasCycle(mynode))

    mynode2 = ListNode(1)
    mynode2.next = ListNode(2)
    mynode2.next.next = ListNode(3)
    mynode2.next.next.next = mynode2.next  # Creating a cycle
    print(sol.hasCycle(mynode2))  # Should return True