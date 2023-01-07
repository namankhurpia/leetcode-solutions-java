class Solution {
    public static int lengthOfLongestSubstring(String s) {

      int streak =0;
      int maxstreak = 0;

      ArrayList<Character> arr = new ArrayList<Character>();
      for(int i=0;i<s.length();i++)
      {
          if(arr.contains(s.charAt(i)))
          {
              //System.out.println("clearing array");
              arr.clear();
              arr.add(s.charAt(i));
              //System.out.println("streak add :"+s.charAt(i));
              streak = 1;
          }
          else
          {
              //System.out.println("streak add :"+s.charAt(i));
              arr.add(s.charAt(i));
              streak +=1;
              maxstreak = Math.max(streak,maxstreak);
          }

      }

      return maxstreak;

  }

      
}