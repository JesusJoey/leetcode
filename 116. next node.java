/**
116. Populating Next Right Pointers in Each Node

You are given a perfect binary tree 
Populate each next pointer to point to its next right node. 
If there is no next right node, the next pointer should be set to NULL.
*/

class Solution {
    public Node connect(Node root) {
        if(root!=null)
            helper(root.left, root.right);
        return root;
    }
    
    private void helper(Node n1, Node n2){
        if(n1 == null || n2 == null || n1.next != null && n2.next != null)
            return;
        n1.next = n2;
        helper(n2.left, n2.right);
        helper(n1.right, n2.left);
        helper(n1.left, n1.right);
    }
}

class Solution {
    public Node connect(Node root) {
        if(root==null) return root;
        if(root.left!=null) root.left.next = root.right;
        if(root.right!=null) root.right.next = root.next!=null ? root.next.left : null;
        connect(root.left);
        connect(root.right);
        return root;
    }
}

//117. You are given a binary tree
class Solution {
    public Node connect(Node root) {
        if(root==null)       //corner case
            return null;
        
        Queue<Node> q = new LinkedList<Node>();
        q.add(root); //add root to queue
        q.add(null);  //add a null value to indicate level is complete. We do the same for every level.
        while(!q.isEmpty())
        {
            Node node = q.poll();  // take first element out from queue
            if(node==null) //if it is null , we reached end of level. Add another marker for next level end.
            {
                if(!q.isEmpty())
                    q.add(null);
                continue;
            }
            node.next=q.peek(); //point current nodes next to next element in queue.
            if(node.left!=null) //add left and right children, if exist.
                q.add(node.left);
            if(node.right!=null)
                q.add(node.right);
        }
        
        return root;
    }
}