//987. Vertical Order Traversal of a Binary Tree

class Solution {
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap();
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, 0, 0, map);
        for (TreeMap<Integer, PriorityQueue<Integer>> yMap : map.values()) {
            List<Integer> tmp = new ArrayList<>();
            for (PriorityQueue<Integer> nodes : yMap.values()) {
                while (!nodes.isEmpty()) {
                    tmp.add(nodes.poll());
                }
            }
            res.add(tmp);
        }
        return res;
    }
    
    private void dfs(TreeNode root, int x, int y, TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map) {
        if (root == null) return;
        if (!map.containsKey(x)) {
            map.put(x, new TreeMap<>());
        }
        if (!map.get(x).containsKey(y)) {
            map.get(x).put(y, new PriorityQueue<>());
        }
        map.get(x).get(y).offer(root.val);
        dfs(root.left, x - 1, y + 1, map);
        dfs(root.right, x + 1, y + 1, map);
    }
}

class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if (root == null) return res;
        
        PriorityQueue<Point> pq = new PriorityQueue(new Comparator<Point>(){
            public int compare(Point p1, Point p2){
                if (p1.x != p2.x)
                    return p1.x - p2.x;
                else if (p1.y != p2.y)
                    return p2.y - p1.y;
                else 
                    return p1.val - p2.val;
            }
        });
        
        dfs(root, 0, 0, pq);
        int prev_x = Integer.MIN_VALUE;
        while (!pq.isEmpty()){
            Point p = pq.poll();
            if (p.x > prev_x){
                List<Integer> list = new ArrayList();
                list.add(p.val);
                res.add(list);
            }
            else{
                List<Integer> list = res.get(res.size()-1);
                list.add(p.val);
            }
            prev_x = p.x;
        }
        return res;
    }
    
    private void dfs(TreeNode root, int x, int y, PriorityQueue<Point> pq){
        if (root == null) return;
        pq.offer(new Point(x, y, root.val));
        dfs(root.left, x-1, y-1, pq);
        dfs(root.right, x+1, y-1, pq);
    }
    
    class Point{
        int x,y,val;
        Point(int x,int y,int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
}