/**
75. Sort Colors

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
*/

//Method1: bucket sort
class Solution {
    public void sortColors(int[] nums) {
        int[] buckets = new int[3];
        for (int num : nums) buckets[num]++;
        for (int i = 0, val = 0; val < 3; val++) {
            int count = 0;
            while (count < buckets[val]) {
                nums[i++] = val;
                count++;
            }
        }
    }
}

   public void sortColors(int[] A) {
       if(A==null || A.length<2) return;
       int low = 0; 
       int high = A.length-1;
       for(int i = low; i<=high;) {
           if(A[i]==0) {
              // swap A[i] and A[low] and i,low both ++
              int temp = A[i];
              A[i] = A[low];
              A[low]=temp;
              i++;low++;
           }else if(A[i]==2) {
               //swap A[i] and A[high] and high--;
              int temp = A[i];
              A[i] = A[high];
              A[high]=temp;
              high--;
           }else {
               i++;
           }
       }
   }

	void sortColors(int A[], int n) {
	    int j = 0, k = n - 1;
	    for (int i = 0; i <= k; ++i){
	        if (A[i] == 0 && i != j)
	            swap(A[i--], A[j++]);
	        else if (A[i] == 2 && i != k)
	            swap(A[i--], A[k--]);
	    }
	}