//https://practice.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1#


//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class Job {
    int id, profit, deadline;
    Job(int x, int y, int z){
        this.id = x;
        this.deadline = y;
        this.profit = z; 
    }
}

class GfG {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //testcases
		int t = Integer.parseInt(br.readLine().trim());
		while(t-->0){
            String inputLine[] = br.readLine().trim().split(" ");
            
            //size of array
            int n = Integer.parseInt(inputLine[0]);
            Job[] arr = new Job[n];
            inputLine = br.readLine().trim().split(" ");
            
            //adding id, deadline, profit
            for(int i=0, k=0; i<n; i++){
                arr[i] = new Job(Integer.parseInt(inputLine[k++]), Integer.parseInt(inputLine[k++]), Integer.parseInt(inputLine[k++]));
            }
            
            Solution ob = new Solution();
            
            //function call
            int[] res = ob.JobScheduling(arr, n);
            System.out.println (res[0] + " " + res[1]);
        }
    }
}
// } Driver Code Ends


class Solution
{
    //Function to find the maximum profit and the number of jobs done.
    int[] JobScheduling(Job arr[], int n)
    {
        // Your code here
        Arrays.sort(arr, (a,b)-> (b.profit - a.profit));
        int maxmimum_jobs_done = 0;
        
        for(int i=0;i<arr.length;i++)
        {
            maxmimum_jobs_done = Math.max(arr[i].deadline, maxmimum_jobs_done);
        }
        
        int result [] = new int[maxmimum_jobs_done+1];
        for(int i=1;i<=maxmimum_jobs_done;i++)
        {
            result[i] = -1;
        }
        
        int countjobs = 0;
        int totalprofit = 0;
        
        for(int i=0;i<n;i++)
        {
            for(int j=arr[i].deadline;j>0;j--)
            {
                if(result[j]==-1)
                {
                    result[j] = i;
                    countjobs ++;
                    totalprofit += arr[i].profit;
                    break;
                }
            }
        }
        
        int [] res = new int[2];
        res[0] = countjobs;
        res[1] = totalprofit;
        
        return res;
        
        
        
        
    }
}

/*
class Job {
    int id, profit, deadline;
    Job(int x, int y, int z){
        this.id = x;
        this.deadline = y;
        this.profit = z; 
    }
}
*/