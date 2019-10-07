/*
1209. Remove All Adjacent Duplicates in String II
Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made.
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
            Pair peek = stack.pop();
            for (int i = 0; i < peek.freq; i++) {
                sb.append(peek.c);
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

1