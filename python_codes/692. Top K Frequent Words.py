from collections import defaultdict

class Solution:
    def topKFrequent(self, words: List[str], k: int) -> List[str]:
        arr = defaultdict(int)
        for i in words:
            arr[i]+=1
        
        sorted_items = sorted(arr.items(), key=lambda x: (-x[1], x[0]))
        
        return [word for word, _ in sorted_items[:k]]
        