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

/**
45.Jump game II

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
*/
public int jump(int[] nums) {
    int maxReach = nums[0];
    int edge = 0 , step = 0;

    for (int i = 1; i < nums.length; i++) {
        if (i > edge) {
            step++;
            edge = maxReach;
            if (edge > num.length - 1)
                return step;
        }
        maxReach = Math.max(maxReach, nums[i] + i);
        if (maxReach < i)
            return -1;
    }
    return step;
}