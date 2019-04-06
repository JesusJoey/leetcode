public class Solution {

	//priority queue
	//Retain a minheap of k elements, the first one is k largest
	public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int num:nums) {
			pq.offer(num);

			if (pq.size() > k) {
				pq.poll();
			}
		}
		return pq.peek();
	}



//quick sort
/*
    利用快速排序的思想，从数组S中随机找出一个元素X，把数组分为两部分Sa和Sb。
    Sa中的元素大于等于X，Sb中元素小于X。这时有两种情况：

        1. Sa中元素的个数小于k，则Sb中的第k-|Sa|个元素即为第k大数；
        2. Sa中元素的个数大于等于k，则返回Sa中的第k大数。
        利用快排的partion思想 T(n) = 2T(n/2) + O(1)   时间复杂度为O(n)   
*/
	public int findKthLargest(int[] nums, int k){
		int n = nums.length;
		int position = quickSelect(nums, 0 ,n-1, n-k+1);
		return nums[position];
	}

	//return the index of the kth smallest number
	int quickSelect(int[] nums, int lo, int hi, int k) {
		//put num <= pivot to the left
		int i = lo, j = hi, pivot = nums[hi];
		while (i < j) {
			if (nums[i++] > pivot)
				swap(nums,--i,--j);
		}
		swap(nums, i, hi);

		//count the nums that <= pivot from lo
		int count = i - lo + 1;
		if (count== k) return i;
		//pivot is too big ,it should be on the left
		else if (count > k)
			return quickSelect(nums, lo , i-1, k);
		else 
			return quickSelect(nums, i+1, hi, k-count);
	}

	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}

