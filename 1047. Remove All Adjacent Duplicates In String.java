/*
1047. Remove All Adjacent Duplicates In String

Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.

We repeatedly make duplicate removals on S until we no longer can.

Return the final string after all such duplicate removals have been made.
*/
Solution 1 : Stack
class Solution {
    public String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        for (char ch : S.toCharArray()) {
            if (!stack.isEmpty() && ch == stack.peek()) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}

Solution : two pointers
	public String removeDuplicates(String S) {
        char[] a = S.toCharArray();
        int end = -1;
        for (char c : a) {
            if (end >= 0 && a[end] == c) { 
                --end; 
            }else { 
                a[++end] = c; 
            }
        }
        return String.valueOf(a, 0, end + 1);
    }

       public String removeDuplicates(String S) {
        String res = "";
        char[] ch = S.toCharArray();
        int i = 0;
        for (int j = 0; j < ch.length; i++, j++) {
            ch[i] = ch[j];
            if (i >= 1 && ch[i] == ch[i - 1]) {
                i -= 2;
            }
        }
        return String.valueOf(ch, 0, i);
    }