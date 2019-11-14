/*
716. Max Stack

MaxStack stack = new MaxStack();
stack.push(5); 
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5

*/
class MaxStack {

    Stack<Integer> s1;
    Stack<Integer> s2;
    int max;
    /** initialize your data structure here. */
    public MaxStack() {
        s1 = new Stack<>();
        s2 = new Stack<>();
        max = Integer.MIN_VALUE;
    }
    
    public void push(int x) {
        if (x >= max) {
            s1.push(max);
            max = x;
        }
        s1.push(x);
    }
    
    public int pop() {
        int res = s1.pop();
        if (res == max) {
           max = s1.pop();
        }
        return res;
    }
    
    public int top() {
        return s1.peek();
    }
    
    public int peekMax() {
        return max;
    }
    
    public int popMax() {
        while (s1.peek() != max) {
            s2.push(s1.pop());
        }
        int res = s1.pop();
        max = s1.pop();
        while (!s2.isEmpty()) {
            int val = s2.pop();
            if (val >= max) {
                s1.push(max);
                max = val;
            }
            s1.push(val);
        }
        return res;
    }
}