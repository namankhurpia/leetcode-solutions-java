import heapq
class Solution:
    def lastStoneWeight(self, stones: List[int]) -> int:
        pq = []
        for s in stones:
            heapq.heappush(pq, -s)

        if len(stones)<=1:
            return stones[0]

        diff = 0
        while(len(pq)>=2):
        
            largest = -heapq.heappop(pq)
            secondlar = -heapq.heappop(pq)
            
            #print("diff b/w" ,largest, " and ", secondlar)
            diff = largest - secondlar
            heapq.heappush(pq,-diff)
        

        return diff
            