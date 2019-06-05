/*
449. Serialize and Deserialize BST
Note: Do not use class member/global/static variables to store states.
 Your serialize and deserialize algorithms should be stateless.
*/
 public class Codec {
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();
    }
    private void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val + ",");
        dfs(root.left, sb);
        dfs(root.right, sb);
        return;
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        TreeNode root = null;
        for (String s : arr) {
            if (s.length() > 0) {
                root = buildBST(root, Integer.parseInt(s));
            }
        }
        return root;
    }
    public TreeNode buildBST(TreeNode root, int v) {
        if (root == null) return new TreeNode(v);
        if (v < root.val) {
            root.left = buildBST(root.left, v);
        } else {
            root.right = buildBST(root.right, v);
        }
        return root;
    }
}


/*
297. Serialize and Deserialize Binary Tree
You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
*/
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                sb.append("null ");
                continue;
            }
            sb.append(node.val + " ");
            q.add(node.left);
            q.add(node.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") return null;
        Queue<TreeNode> q = new LinkedList<>();
        String[] values = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        q.add(root);
        for (int i = 1; i < values.length; i++) {
            TreeNode cur = q.poll();
            if  (!values[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                cur.left = left;
                q.add(left);
            }
            if (!values[++i].equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                cur.right = right;
                q.add(right);
            }
        }
        return root;
    }
}