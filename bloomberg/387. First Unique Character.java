/*
387. First Unique Character in a String
*/
public class Solution {
    public int firstUniqChar(String s) {
        int freq [] = new int[26];
        for(int i = 0; i < s.length(); i ++)
            freq [s.charAt(i) - 'a'] ++;
        for(int i = 0; i < s.length(); i ++)
            if(freq [s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }
}


class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer> ();
        int n=s.length();
        for (int i=0;i<n;i++){
            char c = s.charAt(i);
                map.put(c,map.getOrDefault(c,0)+1);
        }
        for (int i=0;i<n;i++){
            if (map.get(s.charAt(i))==1)
                return i;
        }
        return -1;
    }
}