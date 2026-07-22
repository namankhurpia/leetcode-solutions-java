//prints all subsets of a given array using PowerSet
//https://www.youtube.com/watch?v=b7AYbpM5YrE

import java.util.ArrayList;

class Subset
{
    public static void main(String[] args) {
        String arr[] = new String[]{"a","b","c"};

        //this generates all including "", formula - 2^n
        generateSubsets(arr);


        //for recursive approach - this generates all except "", formula - (2^n)-1
        recursiveapproach(arr, 0, arr.length-1, "");
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

    public static void recursiveapproach(String arr[], int l, int r, String sum)
    {
        if(l>r)
        {
            System.out.println(sum);
            return;
        }

        recursiveapproach(arr, l+1, r, sum + arr[l].toString());

        recursiveapproach(arr, l+1, r, sum);
           
    }
}