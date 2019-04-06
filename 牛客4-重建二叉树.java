//输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
//假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        TreeNode root = reConstruct(pre, 0, pre.length -1, in, 0, in.length -1);
        return root;
    }
    
    public TreeNode reConstruct(int [] pre, int preStart, int preEnd, 
                                int[] in, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);//存入节点
        for (int i = inStart; i <= inEnd; i++) {//从中序遍历开始，寻找和根节点相同的元素       
            if (in[i] == pre[preStart]) {//找到根后分为左右子树,递归查找
                root.left = reConstruct(pre, preStart+1, preStart + i - inStart, in, inStart, i-1);
                root.right = reConstruct(pre,preStart +i - inStart +1, preEnd, in, i+1 , inEnd);
            }
        }
        return root;
    }
}