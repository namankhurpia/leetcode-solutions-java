//OPTIMAL SOLUTION - Algorithm
public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        
        String encoded = "";
        for(int i=0;i<strs.size();i++)
        {
            encoded = encoded + "#"+strs.get(i).length() +"?"+strs.get(i);   
        }
        System.out.println("out"+encoded);
        return encoded;
        
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {

        List<String> res= new ArrayList<String>();
        for(int i=0;i<s.length();i++)
        {
            String temp = "";
            if(s.charAt(i)=='#')
            {
                String temp2 = "";
                int j=i+1;
                while(s.charAt(j)!='?')
                {
                    temp2 = temp2+s.charAt(j);
                    j++;
                }
                int num = Integer.parseInt(temp2);

                temp = s.substring(j+1,j+1+num);
                System.out.println("got:"+temp);
                res.add(temp);
                i=j+num;
            }
        }

        return res;
        
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));