/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        
        HashMap<Node,Node> map = new HashMap<>();

        Node newlist = head;
        
        while(newlist!=null)
        {
            map.put(newlist, new Node(newlist.val));
            newlist = newlist.next;
            
        }
        
        newlist = head;
        while(newlist!=null)
        {
            map.get(newlist).next = map.get(newlist.next);
            map.get(newlist).random = map.get(newlist.random);
            newlist = newlist.next;
        }

        return map.get(head);
    }
}