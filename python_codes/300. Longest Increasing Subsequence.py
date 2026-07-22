from bisect import bisect_left
class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        tail = []
        for n in nums:
            x = bisect_left(tail, n)
            if x == len(tail):
                tail.append(n)
            else:
                tail[x] = n
        
        return len(tail)
    

#explaination
2) O(n log n) “Patience sorting” (most common optimal)
Core trick

Maintain an array tails where:

tails[k] = the smallest possible tail value of any increasing subsequence of length k+1.

Key properties:

tails is sorted

Smaller tail is better because it’s easier to extend later

For each number x, we find where it fits in tails using binary search:

If x is bigger than all tails → we can extend LIS: tails.append(x)

Else replace the first tail >= x with x (we keep the same length but improve the tail)

Important: tails is not necessarily the actual subsequence, but its length equals the LIS length.

Step-by-step for [10,9,2,5,3,7,101,18]

Start tails = []

10 → append → [10]

9 → replace first >=9 (10) → [9]

2 → replace first >=2 (9) → [2]

5 → append → [2,5]

3 → replace first >=3 (5) → [2,3]

7 → append → [2,3,7]

101→ append → [2,3,7,101]

18 → replace first >=18 (101)→ [2,3,7,18]

Length of tails = 4 ⇒ LIS length is 4.

Why bisect_left (not bisect_right)?

Because LIS is strictly increasing.

With duplicates like [7,7,7], bisect_left always returns index 0, so tails stays [7] → answer 1 (correct).

Which should you use in interviews?

Start with O(n²) DP if they want clarity.

Then mention the O(n log n) optimization (tails + binary search) as the scalable solution.

If you want, paste the LIS code you have (or the part you don’t get), and I’ll explain it line-by-line in your style.