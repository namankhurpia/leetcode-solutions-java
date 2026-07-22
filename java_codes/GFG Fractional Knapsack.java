//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class Item {
    int value, weight;
    Item(int x, int y){
        this.value = x;
        this.weight = y;
    }
}

class GfG {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		while(t-->0){
            String inputLine[] = br.readLine().trim().split(" ");
            int n = Integer.parseInt(inputLine[0]);
            int w = Integer.parseInt(inputLine[1]);
            Item[] arr = new Item[n];
            inputLine = br.readLine().trim().split(" ");
            for(int i=0, k=0; i<n; i++){
                arr[i] = new Item(Integer.parseInt(inputLine[k++]), Integer.parseInt(inputLine[k++]));
            }
            System.out.println(String.format("%.2f", new Solution().fractionalKnapsack(w, arr, n)));
        }
    }
}
// } Driver Code Ends


/*
class Item {
    int value, weight;
    Item(int x, int y){
        this.value = x;
        this.weight = y;
    }
}
*/

class Solution
{
    //Function to get the maximum total value in the knapsack.
    double fractionalKnapsack(int W, Item arr[], int n) 
    {
        // Your code here
        itemcompare comp = new itemcompare();
        Arrays.sort(arr, comp);
        double profit = 0;
        int currentweight = 0;
        for(int i=0;i<n;i++)
        {
            if(currentweight+arr[i].weight<=W)
            {
                //System.out.println(arr[i].value + "reduce"+ arr[i].weight);
                profit += arr[i].value;
                W = W-arr[i].weight;
                //System.out.println("weight updated:"+W);
            }
            else
            {
                
                double fractionalweight = (double)arr[i].value/ (double)arr[i].weight;
                double newprofit = (double)W * fractionalweight;
                //System.out.println("frac"+fractionalweight + " new pro"+newprofit);
                profit +=newprofit;
                break;
            }
        }
        return profit;
        
    }
}

class itemcompare implements Comparator<Item>
{
    @Override
    public int compare(Item i1, Item i2)
    {
        double a1 = (double)i1.value/ (double)i1.weight;
        double a2 = (double)i2.value/(double)i2.weight;
        if(a1<a2) return 1;
        else if (a1>a2) return -1;
        else return 0;
    }
}