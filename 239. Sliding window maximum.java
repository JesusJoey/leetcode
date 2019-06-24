/**
239. Sliding Window Maximum

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3 (size of the window)
Output: [3,3,5,5,6,7] 
*/

class Solution {
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || k <= 0) 
			return new int[0];
		int n = nums.length;
		int[] res = new int[n - k + 1];
		int index = 0;
		Deque<Integer> dq = new ArrayDeque<>( );

		for (int i = 0; i < nums.length; i++) {
			//remove numbers of out of range k
			while (!dq.isEmpty() && dq.peek() < i - k + 1) {
				dq.poll();
			}
			//remove samaller numbers in k range as they are useless
			while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
				dq.pollLast();
			}
			dq.offer(i);
			if (i >= k-1) {
				res[index++] = nums[dq.peek()];
			}
		}
		return res;
	}
}