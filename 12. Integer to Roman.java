class Solution {
    public String intToRoman(int num) {

        int arr[] = new int[]{1,4,5,9,10,40,50,90,100,400,500,900,1000};
        String logs[] = new String[]{"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};

        int quo = 0;
        String res = "";
        while(num!=0)
        {
            for(int i=arr.length-1;i>=0;i--)
            {
                if(arr[i]<=num)
                {
                    
                    quo = num /arr[i];
                    if(quo==1)
                    {
                        res = res+logs[i];
                        num = num - arr[i];
                    }
                    else if(quo==2)
                    {
                        res = res+logs[i]+logs[i];
                        num = num - arr[i]-arr[i];
                    }
                    else if(quo==3)
                    {
                        res = res+logs[i]+logs[i]+logs[i];
                        num = num - arr[i]-arr[i]-arr[i];
                    }
                    else
                    {
                        //System.out.println("IMPOSSIBLE");
                    }
                    //System.out.println(res);
                }
            }
        }
        return res;





        
    }
}