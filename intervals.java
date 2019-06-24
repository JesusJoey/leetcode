/**
56. Merge intervals

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
*/

//with interface
public class Interval {
	int start;
	int end;
}
public class Solution {
	public List<Interval> merge(List<Interval> intervals) {
	    if (intervals.size() <= 1)
	        return intervals;
	    // Sort by ascending starting point using an anonymous Comparator
	    intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));
	    
	    List<Interval> result = new LinkedList<Interval>();
	    int start = intervals.get(0).start;
	    int end = intervals.get(0).end;
	    
	    for (Interval interval : intervals) {
	        if (interval.start <= end) // Overlapping intervals, move the end if needed
	            end = Math.max(end, interval.end);
	        else {                     // Disjoint intervals, add the previous one and reset bounds
	            result.add(new Interval(start, end));
	            start = interval.start;
	            end = interval.end;
	        }
	    }
	    
	    // Add the last interval
	    result.add(new Interval(start, end));
	    return result;
	}

	//without interface
	public int[][] merge(int[][] intervals) {
		int n = intervals.length;
		int[] starts = new int[n];
		int[] ends = new int[n];
		for (int i = 0; i < length; i++) {
			starts[i] = intervals[i][0];
			ends[i] = intervals[i][1];
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
		ArrayList<int[]> res = new ArrayList<>();
		int curr = 0;
		for (int i = 0; i < n; i++) {
			if ( i == n - 1 || starts[i + 1] > ends[i]) {
				res.add(new int[] {starts[curr], ends[i]});
				curr = i + 1;
			}
		}
		return res.toArray(new int[res.size()][]);
	}

	public int[][] merge(int[][] intervals) {
		if (intervals.length <= 1) return intervals;
	}

/**
57. Insert Interval

Given a set of non-overlapping intervals, 
insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted
according to their start times.

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
*/
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> res = new ArrayList<Interval>();
		for (Interval curr : intervals) {
			if (newInterval == null || curr.end < newInterval.start)
				res.add(curr);
			else if (curr.start > newInterval.end) {
				res.add(newInterval);
				res.add(curr);
				newInterval == null;
			} else {    //overlap
				newInterval.start = Math.min(newInterval.start, curr.start);
				newInterval.end = Math.max(newInterval.end, curr.end);
			}
		}

		if (newInterval != null) 
			res.add(newInterval);
		return res;
	}

}
