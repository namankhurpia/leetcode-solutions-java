//BRUTE FORCE SOLUTION
class Solution {
    public List<String> letterCombinations(String digits) {
        int arr[] = new int[]{2,3,4,5,6,7,8,9};
        String key[] = new String[]{"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

        ArrayList<String> res = new ArrayList<String>();

        int first = 0;
        int second = 0;
        int third = 0;
        int fourth = 0;

        if(digits.length()==0)
        {
            
            
        }
        if(digits.length()==1)
        {
            int index =0;
            first = Integer.parseInt(digits.charAt(0)+"");
            for(int i=0;i<arr.length;i++)
            {
                if(arr[i]==first)
                {
                    index = i;
                    break;
                }
            }
            for(int i=0;i<key[index].length();i++)
            {
                res.add(key[index].charAt(i)+"");
            }

        }
        else if(digits.length()==2)
        {
            first = Integer.parseInt(digits.charAt(0)+"");
            second = Integer.parseInt(digits.charAt(1)+"");
            int findex =0, sindex=0;
           for(int i=0;i<arr.length;i++)
            {
                if(arr[i]==first)
                {
                    findex = i;
                    
                }
                if(arr[i]==second)
                {
                    sindex = i;
                }
            }
            //System.out.println(findex +" "+sindex);

            for(int i=0;i<key[findex].length();i++)
            {
                for(int j=0;j<key[sindex].length();j++)
                {
                    res.add(key[findex].charAt(i)+""+key[sindex].charAt(j)+"");
                }
                
            }


        }
        else if(digits.length()==3)
        {
            first = Integer.parseInt(digits.charAt(0)+"");
            second = Integer.parseInt(digits.charAt(1)+"");
            third =  Integer.parseInt(digits.charAt(2)+"");

            int findex =0, sindex=0, tindex =0;
            for(int i=0;i<arr.length;i++)
            {
                if(arr[i]==first)
                {
                    findex = i;
                    
                }

                if(arr[i]==second)
                {
                    sindex = i;
                }

                if(arr[i]==third)
                {
                    tindex = i;
                }
            }
            //System.out.println(findex +" "+sindex+" "+ tindex);

            for(int i=0;i<key[findex].length();i++)
            {
                for(int j=0;j<key[sindex].length();j++)
                {
                    for(int k=0;k<key[tindex].length();k++)
                    {
                        res.add(key[findex].charAt(i)+""+key[sindex].charAt(j)+""+key[tindex].charAt(k));
                    }
                    
                }
                
            }

        }
        else if(digits.length()==4)
        {
            first = Integer.parseInt(digits.charAt(0)+"");
            second = Integer.parseInt(digits.charAt(1)+"");
            third =  Integer.parseInt(digits.charAt(2)+"");
            fourth = Integer.parseInt(digits.charAt(3)+"");

            int findex =0, sindex=0, tindex =0, fourindex = 0;
            for(int i=0;i<arr.length;i++)
            {
                if(arr[i]==first)
                {
                    findex = i;
                }

                if(arr[i]==second)
                {
                    sindex = i;
                }

                if(arr[i]==third)
                {
                    tindex = i;
                }

                if(arr[i]==fourth)
                {
                    fourindex = i;
                }
            }
            System.out.println(first +" "+second+" "+ third+" "+ fourth);
            System.out.println(findex +" "+sindex+" "+ tindex+" "+ fourindex);

            for(int i=0;i<key[findex].length();i++)
            {
                for(int j=0;j<key[sindex].length();j++)
                {
                    for(int k=0;k<key[tindex].length();k++)
                    {
                        for(int l=0;l<key[fourindex].length();l++)
                        {
                            res.add(key[findex].charAt(i)+""+key[sindex].charAt(j)+""+key[tindex].charAt(k)+""+key[fourindex].charAt(l));
                        }
                        
                    }
                    
                }
                
            }


        }
        return res;
        
    }
}


