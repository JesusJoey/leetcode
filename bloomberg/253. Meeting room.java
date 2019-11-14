/*
253. Meeting Rooms II

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
*/

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            int endTime = pq.peek();
            if (intervals[i][0] < endTime) {
                pq.add(intervals[i][1]);
            }
            else {
                pq.poll();
                pq.add(intervals[i][1]);
            }
        }
        return pq.size();
    }
}