/**
716. Max Stack

Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. 
If you find more than one maximum elements, only remove the top-most one.
*/

class MaxStack {
	Stack<Integer> s1;
	Stack<Integer> s2;
	int max;
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
		if (res == max) 
			max = s1.pop();
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
