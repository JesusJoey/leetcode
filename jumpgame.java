/**
55. Jump game
Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. 
Its maximum jump length is 0
*/

class Solution {
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (max < i) 
                return false;
            max = Math.max(nums[i] + i,max);
        }
        return true;
    }
}

//辣鸡方法
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] jump = new boolean[n];
        jump[n-1] = true;
        
        for (int i = n-2; i >= 0; i--) {
            for (int j = 0; j <= nums[i] && i + j < n; j++) {
                if (jump[i + j] == true) {
                    jump[i] = true;
                    break;
                }
            }
        }
        return jump[0];
    }
}