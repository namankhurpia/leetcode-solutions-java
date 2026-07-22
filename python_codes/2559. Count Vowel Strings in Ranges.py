
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


## OPtimal - build a prefix array with sum precomputed

class Solution:
    def vowelStrings(self, words: List[str], queries: List[List[int]]) -> List[int]:

        vowels = set('aeiou')
        res = []
        n = 0
        for w in words:
            
            if w[0] in vowels and w[-1] in vowels:
                n+=1
                res.append(n)
            else:
                res.append(n)
        
        print(res)
        finalres = []
        for l,r in queries:
            if l == 0:
                finalres.append(res[r])
            else:
                finalres.append(res[r]-res[l-1])

        return finalres

"""
Key idea: turn words into 0/1 first

Make an array where each word becomes:

1 if it’s a vowel string

0 otherwise

Example:

words = ["aba","bcb","ece","aa","e"]
vowel?   1     0     1     1   1
good  = [1,    0,    1,    1,  1]

Prefix sum = running total

Now build a prefix sum array pref where:

pref[i] = number of vowel strings in the first i words

We usually make pref length n+1 so it’s easy:

good = [1, 0, 1, 1, 1]
pref = [0,
        1,   # 0..0
        1,   # 0..1
        2,   # 0..2
        3,   # 0..3
        4]   # 0..4
index   0  1  2  3  4  5


Meaning:

pref[3] = 2 → in words [0..2], there are 2 vowel strings.

"""