class Solution:
    def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:
        arr.sort()
        min_val = float('inf')
        for i in range (len(arr)-1):
            diff = abs(arr[i] - arr[i+1])
            
            min_val = min(diff, min_val)
        
        res = [] 
        for i in range (len(arr)-1):
            if(abs(arr[i]-arr[i+1]) == min_val):
                res.append((arr[i], arr[i+1]))
        
        return res
                

"""Most optimal - no issues with previous - Not required, but you can avoid scanning twice by resetting res when you find a smaller diff."""

class Solution:
    def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:
        arr.sort()
        res = []
        min_val = float('inf')

        for i in range(len(arr) - 1):
            diff = arr[i+1] - arr[i]
            if diff < min_val:
                min_val = diff
                res = [[arr[i], arr[i+1]]]
            elif diff == min_val:
                res.append([arr[i], arr[i+1]])

        return res
