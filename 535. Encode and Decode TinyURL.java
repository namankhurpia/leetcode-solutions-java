public class Codec {
    HashMap<String,String> map = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String chars = "0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";

        String res = "";
        int rd = (int)Math.random();
        rd = rd % 62;
        for(int i=0;i<6;i++)
        {
            res+= chars.charAt(rd);
        }
        map.put("http://tinyurl.com/"+res, longUrl);
        return "http://tinyurl.com/"+res;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if(map.containsKey(shortUrl))
        {
            return map.get(shortUrl);
        }
        else
        {
            return null;
        }
        
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));