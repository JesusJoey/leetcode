public boolean verifyPreorder(int[] preorder) {
    int low = Integer.MIN_VALUE;
    Stack<Integer> path = new Stack();
    for (int p : preorder) {
        if (p < low)
            return false;
        while (!path.empty() && p > path.peek())
            low = path.pop();
        path.push(p);
    }
    return true;
}

public boolean verifyPreorder(int[] preorder) {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> inorder = new Stack<>();
    
    for(int v : preorder){
        if(!inorder.isEmpty() && v < inorder.peek())
            return false;
        while(!stack.isEmpty() && v > stack.peek()){
            inorder.push(stack.pop());
        }
        stack.push(v);
    }
    return true;
}