/*
1105. Filling Bookcase Shelves
Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
Output: 6
Explanation:
The sum of the heights of the 3 shelves are 1 + 3 + 2 = 6.
Notice that book number 2 does not have to be on the first shelf.


dp[i]: the min height for placing first books i - 1 on shelves
For dp[i+1],
either place book i on a new shelve => dp[i] + height[i],
or grab previous books together with book i and move to next level together,
 utlitzing the sub problem dp[j] => min(dp[j] + max(height[j+1] .. height[i])),
  where sum(width[j+1] + ... + sum(width[i]) <= shelve_width
*/
class Solution {
    public int minHeightShelves(int[][] books, int shelf_width) {
        int[] dp = new int[books.length + 1];
        
        dp[0] = 0;
        
        for (int i = 1; i <= books.length; ++i) {
            int width = books[i-1][0];
            int height = books[i-1][1];
            dp[i] = dp[i-1] + height;
            for (int j = i - 1; j > 0 && width + books[j-1][0] <= shelf_width; --j) {
                height = Math.max(height, books[j-1][1]);
                width += books[j-1][0];
                dp[i] = Math.min(dp[i], dp[j-1] + height);
            }
        }
        return dp[books.length];
    }
}