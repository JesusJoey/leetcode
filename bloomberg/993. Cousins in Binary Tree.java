//993. Cousins in Binary Tree

/*
Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.
*/

class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return false;
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            boolean foundX = false, foundY= false, isSibling = false;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    if (node.left.val ==  x) foundX = true;
                    if (node.left.val == y) foundY = true;
                }
                if (node.right != null) {
                    if (node.right.val ==  x) foundX = true;
                    if (node.right.val == y) foundY = true;
                }
                

                if (node.left != null && node.right != null) {
                    if ((node.left.val == x && node.right.val == y) || node.left.val == y && node.right.val == x)
                        isSibling = true;
                }
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            // end of each level
            if (foundX && foundY && !isSibling) return true;
            
        }
        return false;
    }
}

//dfs
class Solution {
    TreeNode xParent = null;
    TreeNode yParent = null;
    int xDepth = -1, yDepth = -1;
    
    public boolean isCousins(TreeNode root, int x, int y) {
        getDepthAndParent(root, x, y, 0, null);
        return xDepth == yDepth && xParent != yParent? true: false;
    }
    //get both the depth and parent for x and y
    public void getDepthAndParent(TreeNode root, int x, int y, int depth, TreeNode parent){
        if(root == null){
            return;
        }
        if(root.val == x){
            xParent = parent;
            xDepth = depth;
        }else if(root.val == y){
            yParent = parent;
            yDepth = depth;
        }       
        getDepthAndParent(root.left, x, y, depth + 1, root);
        getDepthAndParent(root.right, x, y, depth + 1, root);
    }
}