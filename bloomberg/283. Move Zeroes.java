/*
283. Move Zeroes
*/

class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null||nums.length == 0)
            return;
        int idx = 0;
        for (int num:nums){
            if (num != 0)
                nums[idx++] = num;
        }
        while (idx < nums.length){
            nums[idx++] = 0;
        }
    }
}