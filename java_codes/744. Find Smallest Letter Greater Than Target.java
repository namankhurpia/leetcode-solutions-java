//Brute force solution - O(n)

class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        
        for(int i=0;i<letters.length;i++)
        {
            if(letters[i]>target)
            {
                return letters[i];
            }
        }
        return letters[0];

    }
}

//Binary search solution - O(log n)

class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        
        int l = 0;
        int r = letters.length-1;
        int mid = (l+r)/2;

        while(l<=r)
        {
            mid = (l+r)/2;

            if(letters[mid]<=target)
            {
                l = mid +1;
            }
            else
            {
                r = mid -1;
            }


        }
        return l == letters.length ? letters[0] : letters[l];

        

    }
}