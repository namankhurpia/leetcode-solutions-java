from typing import List

class Solution:
    def maxProfit(self, prices: List[int]) -> int:

        lowest_yet =  float('inf')
        highest_yet = 0
        maxdiff = 0

        for p in prices:
            if p < lowest_yet:
                lowest_yet = min(p,lowest_yet)
            else:
                diff = p-lowest_yet

                maxdiff = max(diff, maxdiff)
        
        return maxdiff


if __name__ == "__main__":
    sol = Solution()

    test_cases = [
        ([7, 1, 5, 3, 6, 4], 5),
        ([7, 6, 4, 3, 1], 0),
        ([1, 2, 3, 4, 5], 4),
        ([3, 3, 3, 3], 0),
        ([2], 0),
        ([], 0),
        ([1, 7, 2, 10, 4, 8], 9),
        ([5, 1, 5, 3, 6, 4], 5),
    ]

    for prices, expected in test_cases:
        result = sol.maxProfit(prices)
        assert result == expected, f"maxProfit({prices}) == {result}, expected {expected}"
        print(f"Passed: maxProfit({prices}) == {expected}")

    print("All tests passed!")