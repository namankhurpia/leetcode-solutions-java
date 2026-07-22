//create min and max heap 

class MedianFinder {
    
    PriorityQueue<Integer> low ;
    PriorityQueue<Integer> high; 
    boolean even = true;

    public MedianFinder() {
        high = new PriorityQueue<>();
        low = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    public void addNum(int num) {
        if(even)
        {
            high.offer(num);
            low.offer(high.poll());
        }
        else
        {
            low.offer(num);
            high.offer(low.poll());
            
        }
       even = !even;
    }
    
    public double findMedian() {
        if(even)
        {
            return (high.peek() + low.peek())/2.0;
        }
        else
        {
            return low.peek();
        }
        
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */