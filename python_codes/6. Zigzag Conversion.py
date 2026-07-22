class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if numRows >= len(s) or len(s)<=1 or numRows==1:
            return s
        
        rows = [""]*numRows
        row = 0
        step = 1

        for c in s:
            rows[row] += c
            if row == 0:
                step = 1
            elif row == numRows - 1:
                step = -1
            row +=step

        return "".join(rows)