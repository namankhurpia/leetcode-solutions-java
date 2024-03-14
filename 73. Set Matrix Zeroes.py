#O(n)

class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        
        arr = []
        for i in range(0, len(matrix)):
            for j in range(0, len(matrix[0])):
                if matrix[i][j] == 0:
                    arr.append([i,j])
        
        print(arr)

        for i,j in arr:
            for row in range(len(matrix[0])):
                matrix[i][row] = 0
            for column in range(len(matrix)):
                matrix[column][j] = 0

        