/*
98. Validate Binary Search Tree
*/

class Solution {
    public boolean isValidBST (TreeNode root){
	   Stack<TreeNode> stack = new Stack<TreeNode> ();
	   TreeNode cur = root ;
	   TreeNode pre = null ;		   
	   while (!stack.isEmpty() || cur != null) {			   
		   while (cur != null) {
			   stack.push(cur);
			   cur = cur.left ;
		   } 				   
		   cur = stack.pop() ;
		   if (pre != null && cur.val <= pre.val) {					   
			   return false ;
		   }				   
		   pre = cur ;					   
		   cur = cur.right;
	   }
	   return true; 
   }
}

    public boolean isValidBST(TreeNode root) {        
        return helper(root, null, null);   
    }
    
    private boolean helper(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) 
            return true;
        if ((min != null && root.val <= min.val) || (max != null && root.val >= max.val))
            return false;

        return helper(root.left, min, root) && helper(root.right, root, max);
    }