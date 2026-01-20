
## TLE -  time limit exceeded in brute force approach
class Solution:
    def vowelStrings(self, words: List[str], queries: List[List[int]]) -> List[int]:

        vowels = set('aeiou')
        res = []

        for w in words:
            if w[0] in vowels and w[-1] in vowels:
                res.append(1)
            else:
                res.append(0)
        
        #print(res)
        finalres = []
        for q in queries:
            q1 = q[0]
            q2 = q[1]
            c = 0
            for j in range(q1,q2+1):
                c+=res[j]
            finalres.append(c)
        return finalres


