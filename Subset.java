//prints all subsets of a given array using PowerSet
//https://www.youtube.com/watch?v=b7AYbpM5YrE

import java.util.ArrayList;

class Subset
{
    public static void main(String[] args) {
        String arr[] = new String[]{"a","b","c"};
        generateSubsets(arr);
    }

    public static void generateSubsets(String []arr)
    {
        ArrayList<String> list = new ArrayList<>();
        int n = arr.length;
        for(int i=0;i<(1<<n);i++)
        {
            String substring = "";
            for(int j=0;j<n;j++)
            {
                if((i & (1<<j))!=0)
                    substring= substring+arr[j];
            }
            list.add(substring);
        }
        System.out.println(list);

    }
}