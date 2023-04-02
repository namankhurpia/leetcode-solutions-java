//BINARY SEARCH APPROACH - OPtimal 

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        

        Arrays.sort(potions);
        int []res = new int[spells.length];
        for(int i=0;i<spells.length;i++)
        {
          int spell = spells[i];
          int left = 0;
          int right = potions.length-1;
          while(left<=right)
          {
              int mid = (left + right)/2;
              long mul = (long)spell * potions[mid];
              if(mul>=success)
              {
                right = mid-1;
              }
              else
              {
                left = mid+1;
              }
          }
          res[i] = potions.length - left;

        }
        return res;

    }
}
