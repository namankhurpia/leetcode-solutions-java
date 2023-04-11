// Optimal solution -  O(n)
class Solution {
    public static int lengthOfLongestSubstring(String s) {

      ArrayList<Character> set = new ArrayList<Character>();
      int left = 0;
      int max =0;
      for(int i=0;i<s.length();i++)
      {
          while(set.contains(Character.valueOf(s.charAt(i))))
          {
              set.remove(left);
          }

          set.add(s.charAt(i));
          max = Math.max(max, set.size());
      }
      return max;

  }

      
}
