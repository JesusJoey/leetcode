public List<Integer> preorderTraversal(TreeNode root) {
  List<Integer> result = new ArrayList<>();
  Deque<TreeNode> stack = new ArrayDeque<>();
  TreeNode p = root;
  while(!stack.isEmpty() || p != null) {
      if(p != null) {
          stack.push(p);
          result.add(p.val);  // Add before going to children
          p = p.left;
      } else {
          TreeNode node = stack.pop();
          p = node.right;   
      }
  }
  return result;
}

class Solution {
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> pre = new LinkedList<Integer>();
		preHelper(root,pre);
		return pre;
	}
	public void preHelper(TreeNode root, List<Integer> pre) {
		if(root==null) return;
		pre.add(root.val);
		preHelper(root.left,pre);
		preHelper(root.right,pre);
	}
    
    public List<Integer> preorderTraversal(TreeNode node) {
        List<Integer> list = new LinkedList<Integer>();
        Stack<TreeNode> rights = new Stack<TreeNode>();
        while(node != null) {
            list.add(node.val);
            if (node.right != null) {
                rights.push(node.right);
            }
            node = node.left;
            if (node == null && !rights.isEmpty()) {
                node = rights.pop();
            }
        }
        return list;
    }
}

public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    // method 1: recursion

    helper(root, res);
    return res;

    //helper function for method 1
    private void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            if (root.left != null) {
                helper(root.left, res);
            }
            res.add(root.val);
            if (root.right != null) {
                helper(root.right, res);
           }
       }
   }
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<Integer>();
    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode cur = root;

    while(cur!=null || !stack.empty()){
        while(cur!=null){
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        list.add(cur.val);
        cur = cur.right;
    }
    return list;
}

public List<Integer> postorderTraversal(TreeNode root) {
  LinkedList<Integer> result = new LinkedList<>();
  Deque<TreeNode> stack = new ArrayDeque<>();
  TreeNode p = root;
  while(!stack.isEmpty() || p != null) {
      if(p != null) {
          stack.push(p);
          // Reverse the process of preorder
          result.addFirst(p.val); 
          // Reverse the process of preorder 
          p = p.right;             
      } else {
          TreeNode node = stack.pop();
           // Reverse the process of preorder
          p = node.left;          
      }
  }
  return result;
}

//100. same tree
public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) return true;
    else if (p == null || q == null) return false;
    else if (p.val == q.val) {
        return isSameTree(p.left,q.left) && isSameTree(p.right, q.right);
    }
    return false;
}

/*
103. Zig Zag level order
bfs
*/
public List<List<Integer>> zigzagLevelOrder(TreeNode root) 
{
    List<List<Integer>> sol = new ArrayList<>();
    travel(root, sol, 0);
    return sol;
}

private void travel(TreeNode curr, List<List<Integer>> sol, int level)
{
    if(curr == null) return;
    
    if(sol.size() <= level)
    {
        List<Integer> newLevel = new LinkedList<>();
        sol.add(newLevel);
    }
    
    List<Integer> collection  = sol.get(level);
    if(level % 2 == 0) collection.add(curr.val);
    else collection.add(0, curr.val);
    
    travel(curr.left, sol, level + 1);
    travel(curr.right, sol, level + 1);
}

public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root==null) return res;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    boolean order = true;
    while (!queue.isEmpty()) {
        List<Integer> tmp = new ArrayList<>();
        //number of nodes in one level
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            if (order) tmp.add(node.val);
            else tmp.add(0,node.val);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);

        }
        //after traverse one level change the order
        res.add(tmp);
        order = !order;
    }
    return res;
}

/*
104. Maximum Depth of Binary Tree
*/
//可以非递归bfs遍历
public int maxDepth(TreeNode root) {
    if(root==null){
        return 0;
    }
    return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
}

/*
105. Construct Binary Tree from Preorder and Inorder Traversal

The basic idea is here:
Say we have 2 arrays, PRE and IN.
Preorder traversing implies that PRE[0] is the root node.
Then we can find this PRE[0] in IN, say it's IN[5].
Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the left side, 
IN[6] to the end is on the right side.
Recursively doing this on subarrays, we can build a tree out of it :)
*/

public TreeNode buildTree(int[] preorder, int[] inorder) {
  return helper(0,0,inorder.length - 1, preorder, inorder);  
}

public TreeNode helper(int preStart, int inStart, int inEnd,
  int[] preorder, int[] inorder) {
  if (preStart > preorder.length - 1 || inStart > inEnd) return null;
  TreeNode root = new TreeNode(preorder[preStart]);
  int index = 0;
  for (int i = inStart; i <= inEnd; i++) {
    if (inorder[i] == root.val) {
      index = i;
    } 
  }
  root.left = helper(preStart + 1, inStart, index - 1, preorder, inorder);
  root.right = helper(preStart+index-inStart+1, index + 1,inEnd, preorder, inorder);
  return root;
}

/*
106. Construct Binary Tree from Inorder and Postorder Traversal
*/
public TreeNode buildTree(int[] inorder, int[] postorder) {
    return buildTree(inorder, inorder.length-1, 0, postorder, postorder.length-1);
}

private TreeNode buildTree(int[] inorder, int inStart, int inEnd,
        int[] postorder,int postStart) {
    if (postStart < 0 || inStart < inEnd)
        return null;

    //The last element in postorder is the root.
    TreeNode root = new TreeNode(postorder[postStart]);

    //find the index of the root from inorder. Iterating from the end.
    int rIndex = inStart;
    for (int i = inStart; i >= inEnd; i--) {
        if (inorder[i] == postorder[postStart]) {
            rIndex = i;
            break;
        }
    }
    //build right and left subtrees. Again, scanning from the end to find the sections.
    root.right = buildTree(inorder,inStart,rIndex + 1,postorder,postStart-1);
    root.left = buildTree(inorder, rIndex - 1, inEnd, postorder,
                          postStart - (inStart - rIndex) -1);
    return root;
}

/*
199. Binary tree right side view

*/
//recursive
//1.Each depth of the tree only select one node.
//2. View depth is current size of result list.
public List<Integer> rightSideView(TreeNode root) {
    List<Integer> result = new ArrayList<Integer>();
    rightView(root, result, 0);
    return result;
}

public void rightView(TreeNode curr, List<Integer> result, int currDepth){
    if(curr == null){
        return;
    }
    if(currDepth == result.size()){
        result.add(curr.val);
    }
    
    rightView(curr.right, result, currDepth + 1);
    rightView(curr.left, result, currDepth + 1); 
}

//BFS iterative
public List<Integer> rightSideView(TreeNode root) {
    if (root == null)
        return new ArrayList();
    List<Integer> res = new ArrayList();
    Queue<TreeNode> queue = new LinkedList();
    queue.offer(root);
    
    while(!queue.isEmpty()){
        int size = queue.size();
        TreeNode cur = null;
        while (size -- > 0){
            cur = queue.poll();
            if (cur.left != null)
                queue.offer(cur.left);
            if (cur.right != null)
                queue.offer(cur.right);
        }
        res.add(cur.val);
    }       
    return res;
}

//Lowest coomon ancestor of Binary Tree

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      if(root.val > p.val && root.val > q.val){
          return lowestCommonAncestor(root.left, p, q);
      }else if(root.val < p.val && root.val < q.val){
          return lowestCommonAncestor(root.right, p, q);
      }else{
          return root;
      }
  }
/*
230. Kth Smallest Element in a BST
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 // DFS in-order recursive

private static int number = 0;
private static int count = 0;

public int kthSmallest(TreeNode root, int k) {
  helper(root);
  return number;
}

public void helper(TreeNode n) {
  if (n.left != null) helper(n.left);
  count--;
  if (count == 0) {
      number = n.val;
      return;
  }
  if (n.right != null) helper(n.right);
}

//DFS in-order iterative
public int kthSmallest(TreeNode root, int k) {
  Stack<TreeNode> stack = new Stack<>();

  while (root != null) {
    stack.push(root);
    root = root.left;
  }
  //inorder traversal of left child
  while (k != 0) {
    TreeNode node = stack.pop();
    k--;
    if (k == 0) return node.value;
    //start from the bottom to check its right child
    TreeNode right = node.right;
    while (right != null) {
      stack.push(right);
      right = right.left;
    }
  }
  return -1;
}

//270. Closest Binary Search Tree Value
public int closestValue(TreeNode root, double target) {
    int ret = root.val;   
    while(root != null){
        //update closet value if current is closer to target
        if(Math.abs(target - root.val) < Math.abs(target - ret)){
            ret = root.val;
        }      
        if (ret == target) return ret;
        root = root.val > target? root.left: root.right;
    }     
    return ret;
}

/*
314. Binary tree verticle order traveral
*/

public List<List<Integer>> verticalOrder(TreeNode root) {
  List<List<Integer>> res = new ArrayList<>();
  if (root == null) return res;

  Map<Integer, ArrayList<Integer>> map = new HashMap<>();
  Queue<TreeNode> queue = new LinkedList<>();
  Queue<Integer> cols = new LinkedList<>();

  queue.add(root);
  cols.add(0);
  int min = 0, max = 0;
  while (!queue.isEmpty()) {
  	//put node and its col into the queue
    TreeNode node = queue.poll();
    int col = cols.poll();
    if (!map.containsKey(col)) {
        map.put(col, new ArrayList<Integer>());
    }
    map.get(col).add(node.val);
    //get col boundary, stored in map
    if (node.left != null) {
        queue.add(node.left);
        cols.add(col - 1);
        min = Math.min(min, col - 1);
    }
    if (node.right != null) {
        queue.add(node.right);
        cols.add(col+1);
        max = Math.max(max, col+1)
    }
  }
  for (int i = min; i <= max; i++) {
    res.add(map.get(i));
  }
  return res;
}

//701. insert into a binary search tree
public TreeNode insertIntoBST(TreeNode root, int val) {
  if(root == null) return new TreeNode(val);
  TreeNode cur = root;
  while(true) {
      if(cur.val <= val) {
          if(cur.right != null) cur = cur.right;
          else {
              cur.right = new TreeNode(val);
              break;
          }
      } else {
          if(cur.left != null) cur = cur.left;
          else {
              cur.left = new TreeNode(val);
              break;
          }
      }
  }
  return root;
}