/**
102. Binary Tree Level Order Traversal

For example:
Given binary tree [3,9,20,null,null,15,7],

[
  [3],
  [9,20],
  [15,7]
]
*/

/**
103. Binary Tree Zigzag Level Order Traversal
*/

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode> ();
        List<List<Integer>> res = new LinkedList<List<Integer>> ();
        if (root==null) return res;
        queue.add(root);
        //boolean order = true;
        while (! queue.isEmpty()) {
            List<Integer> level = new LinkedList<>();
            int size = queue.size();
            while (size-- > 0){
                TreeNode tmp = queue.poll();
                //if (order)
                level.add(tmp.val);
                //else level.add(0, tmp.val);
                if (tmp.left != null) queue.offer(tmp.left);
                if (tmp.right != null) queue.offer(tmp.right);
            }
            res.add(level);
            //order =! order;
        }
        return res;
        
    }
}

