//BRUTE FORCE SOLUTION
class Solution {
    public String removeVowels(String s) {
        String res = s.replaceAll("a","");
        res = res.replaceAll("e","");
        res = res.replaceAll("i","");
        res = res.replaceAll("o","");
        res = res.replaceAll("u","");
        return res;


    }
}

//OPTIMISED APPROACH
class Solution {
    public String removeVowels(String s) {
        /*String res = s.replaceAll("a","");
        res = res.replaceAll("e","");
        res = res.replaceAll("i","");
        res = res.replaceAll("o","");
        res = res.replaceAll("u","");
        return res;
*/

        char[] result = new char[s.length()];
        int index = 0;
        
        for(char c : s.toCharArray()) {
            if (c != 'a' && c != 'e' && c != 'i' && c !='o' && c != 'u') {
                result[index++] = c;
            }
        }
        
        return new String(result, 0, index);

    }
}