/**
118. Pascal's Triangle
Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/
class Solution {
    public List<List<Integer>> generate(int numRows) {
        
        List<List<Integer>> result = new ArrayList<>();
        
        for(int i = 0 ; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            
            for(int j = 0; j < i + 1 ; j++) {
                if(j == 0 || j == i) {
                    list.add(1);
                }
                else {
                    int a = result.get(i - 1).get(j - 1);
                    int b = result.get(i - 1).get(j);
                    list.add(a + b);
                }
            }
            result.add(list);
         }
        return result;
      }
}
/**
119. Pascal's Triangle II
Input: 3
Output: [1,3,3,1]
*/

public List<Integer> getRow(int rowIndex) {
	List<Integer> res = new ArrayList<Integer>();
	for (int i = 0; i <= rowIndex; i++) {
		res.add(1);
		for (int j = i - 1; j > 0; j--) {
			res.set(j, res.get(j) + res.get(j - 1));
		}
	}
	return res;
}

public class Solution {
    public List<Integer> getRow(int k) {
        Integer[] arr = new Integer[k + 1];
        Arrays.fill(arr, 0);
        arr[0] = 1;
        
        for (int i = 1; i <= k; i++) 
            for (int j = i; j > 0; j--) 
                arr[j] = arr[j] + arr[j - 1];
        
        return Arrays.asList(arr);
    }
}

/**
120. Triangle

Given a triangle, find the minimum path sum from top to bottom. 
Each step you may move to adjacent numbers on the row below.
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 
(i.e., 2 + 3 + 5 + 1 = 11).
*/

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) return 0;
        //从倒数第二行开始计算
     	for (int i = triangle.size() - 2; i >=0; i--) {
     		List<Integer> row = triangle.get(i + 1);
     		for (int j = 0; j <= i; j++) {
     			int sum = Math.min(row.get(j), row.get(j+1)) + triangle.get(i).get(j);
     			triangle.get(i).set(j, sum);
     		}
     	}
     	return triangle.get(0).get(0);
    }
}