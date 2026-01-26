import heapq

class Solution:
    def maxEvents(self, events: List[List[int]]) -> int:
        events.sort()
        pq = []
        lenevents = len(events)
        maxday = max(e[1] for e in events)
        i=0
        attend = 0
        
        for day in range(1, maxday+1):
            # add all events in a minheap that start today or earlier
            while(i<lenevents and events[i][0]<=day ):
                heapq.heappush(pq, events[i][1])
                i+=1
            
            #remove expired events
            while(pq and pq[0]<day):
                heapq.heappop(pq)
            
            #attend the event at the earliest day
            if pq:
                heapq.heappop(pq)
                attend+=1
        
        return attend
            



