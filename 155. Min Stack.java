//optimal - minstack implemented in O(1)
class MinStack {
    Stack<Integer> stack ;
    Stack<Integer> minstack;

    public MinStack() {
        stack = new Stack<Integer>();
        minstack = new Stack<Integer>();
    }
    
    public void push(int val) {
        stack.push(val);
        if(minstack.isEmpty())
        {
            minstack.push(val);
        }
        else
        {
            if(minstack.peek()>val)
            {
                minstack.push(val);
            }
            else
            {
                minstack.push(minstack.peek());
            }
        }
    }
    
    public void pop() {
        stack.pop();
        minstack.pop();
        
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minstack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */