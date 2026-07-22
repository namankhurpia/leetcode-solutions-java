//BRUTE FORCE APPROACH
class Solution {
    public int minDeletionSize(String[] strs) {
        String tempone = strs[0];

        int count = 0;
        for(int i=0;i<tempone.length();i++)
        {
            char arr[] = new char[strs.length];
            for(int j=0;j<strs.length;j++)
            {
                arr[j] = strs[j].charAt(i);
            }
            if(!issorted(arr))
            {
                count = count+1;
            }

        }
        return count;
    }

    public static boolean issorted(char []arr)
    {
        char arrdup[] =Arrays.copyOf( arr, arr.length);

        Arrays.sort(arr);

        if(Arrays.equals(arrdup,arr))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}