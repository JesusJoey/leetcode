 /*
	31. Next Permutation
	1,2,3 -> 1,3,2
	3,2,1 -> 1,2,3
	1,1,5 -> 1,5,1

 */

 public class solution {
 	public void nextPermutation(int[] nums) {
 		if (nums == null || nums.length == 0) return;

 		int firstSmall = -1;
 		//从最后两位开始比较,找到第一次升序排列的位置
 		for (int i =nums.length - 2; i >= 0; i--) {
 			if (nums[i] < nums[i+1]) {
 				firstSmall = i;
 				break;
 			}
 		}
 		//如果每个前面的数都比后面的数大,则翻转得到最小数
 		if (firstSmall == -1) {
 			reverse(nums, 0 ,nums.length - 1);
 			return;
 		}
 		//比最小数大一位的数,因为后面的数字都是降序排列，找到第一个比最小数大的返回
 		int firstLarge = -1;
 		for (int i = nums.length - 1; i > firstSmall; i--) {
 			if (nums[i] > nums[firstSmall]) {
 				firstLarge = i;
 				break;
 			}
 		}
 		//剩下的数字翻转
 		swap(nums,firstSmall,firstLarge);
 		reverse(nums, firstSmall + 1, nums.length - 1);
 	}


 	public void reverse(int[] nums, int i, int j) {
 		while (i<j) {
 			swap(nums, i++, j--);
 		}
 	}

 	public void swap(int[] nums, int i, int j) {
 		int tmp = nums[i];
 		nums[i] = nums[j];
 		nums[j] = tmp;
 	}
}