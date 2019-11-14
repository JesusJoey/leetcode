/*
394. Decode String

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
*/

class Solution {
    public String decodeString(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int num = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            } else if ( ch == '[') {
                intStack.push(num);
                strStack.push(cur);
                cur = new StringBuilder();
                num = 0;
            } else if (ch == ']') {
                StringBuilder tmp = cur;
                cur = strStack.pop();
                for (num = intStack.pop(); num > 0; num--) 
                	cur.append(tmp);
            } else {
            	cur.append(ch);
            }
        }
        return cur.toString();
    }
}