import java.util.Stack;

public class StringTrans {
    public String transBase(String num, int fromBase, int toBase) {
        int number = toDecimal(num, fromBase);
        return toOtherBase(number, toBase);
    }
    static  char[] digits = new char[36];
    static  {
        for (int i = 0; i < 10; i++) {
            digits[i] = (char)('0' + i);
        }
        for (int i = 10; i < 36; i++) {
            digits[i] = (char)('A' + (i - 10));
        }
    }

    public static String toOtherBase(int number, int base) {
        int rest = number;
        Stack<Character> stack = new Stack<Character>();
        StringBuilder result = new StringBuilder(0);
        rest = number;
        while (rest != 0) {
            int temp = rest % base;
            stack.push(digits[temp]);
            rest = rest / base;
        }
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    public static int toDecimal(String str, int base) {
        char[] ch = str.toCharArray();
        int res = 0;
        if (base == 10) {
            return Integer.parseInt(str);
        }
        long weight = 1;
        for (int i = ch.length - 1; i >= 0; i--) {
            int index = getIndex(ch[i]);
            res += index * weight;
            weight *= base;
        }
        return res;
    }

    private static int getIndex(char c) {
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == c) return i;
        }
        return -1;
    }
}

// Build a array of list to be buckets with length 1 to sort
public List<Integer> topKFrequent(int[] nums, int k) {

    List<Integer>[] bucket = new List[nums.length + 1];
    Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

    for (int n : nums) {
        frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
    }

    for (int key : frequencyMap.keySet()) {
        int frequency = frequencyMap.get(key);
        if (bucket[frequency] == null) {
            bucket[frequency] = new ArrayList<>();
        }
        bucket[frequency].add(key);
    }

    List<Integer> res = new ArrayList<>();
    for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
        if (bucket[pos] != null) {
            res.addAll(bucket[pos]);
        }
    }
    return res;
}
    
    for (int i = buckets.length-1; i>=0; i--) {
        int j = 0;
        //如果不考虑duplicate则不需要sort
        if (buckets[i] != null) {
            Collections.sort(buckets[i]);
        }
        //else continue;
        while (buckets[i] != null && k>0 && j<buckets[i].size()) {
            res.add(buckets[i].get(j));
            j++;
            k--;
        }
        if (k == 0) break;
    }

// use maxHeap. Put entry into maxHeap so we can always poll a mnuber with largest frequency
public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }
           
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = 
                         new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            maxHeap.add(entry);
            if (maxHeap.size() > k) maxHeap.poll();
        }
        
        List<Integer> res = new ArrayList<>();
        while(res.size() < k){
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            res.add(entry.getKey());
        }
        return res;
    }
}

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = 
                         new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
        Map<Integer, Integer> entry : map.entrySet() {
            minHeap.add(entry);
            if (minHeap.size() > k) minHeap.poll();
        }

        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {
            Map.Entry<Integer, Integer> entry = minHeap.poll();
            res.add(0, entry.getKey());
        }
        return res;
    }

public class Solution {
    private PriorityQueue<Integer> minheap;

    public Solution(int k) {
        minheap = new PriorityQueue<Integer>();
    }

    public void add(int num) {
        if (minheap.size() < k) {
            minheap.offer(num);
        } else {
            if (num > minheap.peek()) {
                minheap.poll();
                minheap.offer(num);
            }
        }

    }

    public List<Integer> topk() {
        Iterator iter = minheap.iterator();
        List<Integer> result = new ArrayList<Integer>();
        while (iter.hasNext()) {
            result.add((Integer) iter.next());
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }
};