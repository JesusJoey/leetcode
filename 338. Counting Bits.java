/*
338. Counting Bits
Input: 5
Output: [0,1,1,2,1,2]
*/

Solution 1: brutal force
class Solution {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            res[i] = countBit(i);
        }
        return res;
    }
    
    private int countBit(int x) {
        int count = 0;
        while (x != 0) {
            x &= x - 1;
            count++;
        }
        return count;
    }
}

Solution 2: two poniters
/*
My logic was to copy the previous values (starting at 0) until a power of 2 was hit (new range), 
at which point we just reset the t pointer back to 0 to begin the new range.
*/

0 1 .10 11 .100 101 110 111 .1000

public int[] countBits(int num) {
	int[] res = new int[num + 1];
	int pow = 1;
	for (int i = 1, j = 0; i <= num; i++, j++) {
		if (pow == i) {
			pow *= 2;
			j = 0;
		}
		res[i] = res[j] + 1;
	}
	 return res;
}

public int[] countBits(int num) {
    int result[] = new int[num + 1];
    int offset = 1;
    for (int index = 1; index < num + 1; ++index){
        if (offset * 2 == index){
            offset *= 2;
        }
        result[index] = result[index - offset] + 1;
    }
    return result;
}

public class Solution {
	public int[] countBits(int num) {
		
		int[] ans = new int[num + 1];
		for (int i = 1; i <= num; ++i)
			ans[i] = ans[i & (i - 1)] + 1;
  		return ans;
	}
}