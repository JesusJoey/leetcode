/*
1209. Remove All Adjacent Duplicates in String II

Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
*/
class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<Pair> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().c == ch) {
                stack.peek().freq++;
            } else {
                stack.push(new Pair(ch, 1));
            }
            if (stack.peek().freq == k) {
                stack.pop();
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair cur = stack.pop();
            for (int i = 0; i < cur.freq; i++) {
                sb.append(cur.c);
            }
        }
        return sb.reverse().toString();
    }
    
    class Pair {
        char c;
        int freq;
        
        public Pair(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }
}