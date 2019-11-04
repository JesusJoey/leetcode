//1181. Before and After Puzzle

class Solution {
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        Map<String, List<Integer>> map = new HashMap<>();
        int i = 0;
        for(String str: phrases){
            String first = str.split(" ")[0];
            if(map.containsKey(first)==false){
                map.put(first, new ArrayList<>());
            }
            
            map.get(first).add(i);
            i++;
        }
        
        i = 0;
        Set<String> res = new HashSet<>();
        for(String str: phrases){
            int ind = str.lastIndexOf(" ");
            String last = ind>=0?str.substring(ind+1):str;
            if(map.containsKey(last)){
                for(int index: map.get(last)){
                    if(index == i){
                        continue;
                    }
                    
                    res.add(str + phrases[index].substring(last.length()));
                }
            }
            
            i++;
        }
        
        List<String> ans = new ArrayList<>(res);
        
        Collections.sort(ans);
        
        return ans;
    }
}