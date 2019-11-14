/*
759. Employee Free Time

The idea is to just add all the intervals to the priority queue. 

priority queue - sorted by start time, and for same start time sort by either largest end time or smallest (it is not matter).
Everytime you poll from priority queue, just make sure it doesn't intersect with previous interval.
This mean that there is no coomon interval. Everyone is free time.
*/

/*
I assume it is supposed to use the property intervals are sorted for each employee.
using merge sort, the time complexity will be n*lg(k), k is the number of employees.
*/
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        int n=schedule.size();
        List<Interval> time=mergeSort(schedule, 0, n-1);
        List<Interval> free=new ArrayList<>();
        int end=time.get(0).end;
        for(int i=1;i<time.size();i++) {
            if(time.get(i).start>end) {
                free.add(new Interval(end, time.get(i).start));
            }
            end=Math.max(end, time.get(i).end);
        }
        return free;
    }
    
    private List<Interval> mergeSort(List<List<Interval>> schedule, int l, int r) {
        if(l==r) return schedule.get(l);
        int mid=(l+r)/2;
        List<Interval> left=mergeSort(schedule, l, mid);
        List<Interval> right=mergeSort(schedule, mid+1, r);
        return merge(left, right);
    }
    
    private List<Interval> merge (List<Interval> A, List<Interval> B) {
        List<Interval> res=new ArrayList<>();
        int m=A.size(), n=B.size();
        int i=0, j=0;
        while(i<m||j<n) {
            if(i==m) {
                res.add(B.get(j++));
            }
            else if(j==n) {
                res.add(A.get(i++));
            }
            else if(A.get(i).start<B.get(j).start) {
                res.add(A.get(i++));
            }
            else res.add(B.get(j++));
        }
        return res;
    }
}

//N is the total number of intervals in all the lists. K is the number of people.
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> schedule.get(a[0]).get(a[1]).start - schedule.get(b[0]).get(b[1]).start);
        for (int i = 0; i < schedule.size(); i++) {
            pq.add(new int[] {i, 0});
        }
        List<Interval> res = new ArrayList<>();
        int prev = schedule.get(pq.peek()[0]).get(pq.peek()[1]).start;
        while (!pq.isEmpty()) {
            int[] index = pq.poll();
            Interval interval = schedule.get(index[0]).get(index[1]);
            if (interval.start > prev) {
                res.add(new Interval(prev, interval.start));
            }
            prev = Math.max(prev, interval.end);
            if (schedule.get(index[0]).size() > index[1] + 1) {
                pq.add(new int[] {index[0], index[1] + 1});
            }
        }
        return res;
    }
}

//The main idea is after sorting start and end time list, there are gaps when starts.get(i + 1) > ends.get(i).

	public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        int n = schedule.size();
        List<Interval> ret = new ArrayList<>();
        if (n == 0)
            return ret;
        List<Integer> starts = new ArrayList<Integer>();
        List<Integer> ends = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            List<Interval> employee = schedule.get(i);
            for (Interval it : employee) {
                starts.add(it.start);
                ends.add(it.end);
            }
        }
        Collections.sort(starts);
        Collections.sort(ends);
        n = starts.size();
        for (int i = 0; i < n - 1; i++) {
            if (starts.get(i + 1) > ends.get(i)) {
                ret.add(new Interval(ends.get(i), starts.get(i + 1)));
            }
        }
        
        return ret;
    }

    //
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        int size = 0;
        for(List<Interval> list : schedule) size += list.size();
        int[] starts = new int[size];
        int[] ends = new int[size];
        int index = 0;
        for(List<Interval> list : schedule){
            for(Interval inter : list){
                starts[index] = inter.start;
                ends[index++] = inter.end;
            }
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        List<Interval> res = new ArrayList<>();
        for(int i = 1; i < size; i++){
            if(ends[i-1] < starts[i]) {
                res.add(new Interval(ends[i-1], starts[i]));
            }
        }
        return res;
    }