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



//optimal again - jun 15 2024
// approach - consider 2 pointers left and right for the string s, now create a set which contains character.
// this set will contains unique characters of string s, keep moving from left to right and everytime you cencounter a unique character add it to set, but if it exists remove it using a loop to constantly update the set
class Solution {
    public int lengthOfLongestSubstring(String s) {
        
        int left = 0;
        int right = s.length();
        int max = 0;
        ArrayList<Character> set = new ArrayList<>();

        for(left=0;left<s.length();left++)
        {
            while(set.contains(Character.valueOf(s.charAt(left))))
            {
                set.remove(0);
            }
            
                set.add(Character.valueOf(s.charAt(left)));
                max = Math.max(set.size(),max);
            
        }
        return max;

    }
}