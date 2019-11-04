472. Concatenated Words

//Method 1: dp
public class Solution {
     public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        Set<String> pre = new HashSet<>();
        Arrays.sort(words, (s1, s2)-> s1.length() - s2.length());

        for (int i = 0; i < words.length; i++) {
            if (canForm(words[i], pre)) {
                res.add(words[i]);
            }
            pre.add(words[i]);
        }

        return res;
    }

    //word break
    private static boolean canForm(String word, Set<String> dict) {
        if (dict.isEmpty()) return false;
        //if (word.length() == 0) return false;
        int n = word.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}

//Method 2: trie + dfs
Pseudo-code:
    func findAllConcatenatedWordsInADict():
        build Trie using words
        for word in words
            test isConcatenation(0, word, root])
    
    func b    private static Node root;
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        
        if (words == null || words.length == 0)
            return new ArrayList<>();
        
        List<String> result = new LinkedList<>();
        
        root = new Node();
        buildTrie(words);
        
        for (String word : words) {
            if (isConcatenation(0, word, 0))
                result.add(word);
        }
        
        return result;
    }

    
    private boolean isConcatenation(int index, String word, int count) {
        
        Node ptr = root;
        
        for (int i = index; i < word.length(); i++) {   
            if (ptr.children[word.charAt(i) - 'a'] == null) 
                return false;
            ptr = ptr.children[word.charAt(i) - 'a'];
            if (ptr.isWordEnd) {
                if (i == word.length() - 1) {
                    return count >= 1;
                }
                if (isConcatenation(i + 1, word, count + 1))
                    return true;
            }     
        }
        
        return false;
    }
    
    private void buildTrie(String[] words) {
        Node ptr;
        for (String word : words) {
            ptr = root;
            for (char ch : word.toCharArray()) {
                int order = ch - 'a';
                if (ptr.children[order] == null) {
                    ptr.children[order] = new Node();
                } 
                ptr = ptr.children[order];
            }
            ptr.isWordEnd = true;
        }
    }
    
    class Node {
        Node[] children;
        boolean isWordEnd;
        
        public Node() {
            children = new Node[26];
            isWordEnd = false;
        }
    }oolean isConcatenation(index, word, cnt): // backtrack
        ptr = root
        for (i = index; i < word.length; i++)
            if (ptr.children[word[i] - 'a'] == null)
                return false
            ptr = ptr.children[word[i] - 'a'];
            if (ptr.isEnd) // time to make a decision
                if (i = word.length - 1)
                    return cnt >= 1
                if (isConcatenation(wi + 1, word, cnt + 1))
                    return true;
            
            
        return false;
        
class Solution {
   class TrieNode {
       TrieNode[] children;
       boolean isWordEnd;
       
       public TrieNode() {
           this.children = new TrieNode[26];
           this.isWordEnd = false;
       }
   
   }
    
   public List<String> findAllConcatenatedWordsInADict(String[] words) {
       if (words == null || words.length == 0) return new ArrayList<>();
       List<String> res = new LinkedList<>();
       TrieNode root = new TrieNode();
       buildTrie(words, root);
       
       for (String word : words) {
           if (isConcatenated(0, word, root, 0)) {
               res.add(word);
           }
       }
       return res;
   }    
   
   private boolean isConcatenated(int index, String word, TrieNode root, int count) {
       TrieNode node = root;
       for (int i = index; i < word.length(); i++) {
           if (node.children[word.charAt(i) - 'a'] == null) return false;
           node = node.children[word.charAt(i) - 'a'];
           if (node.isWordEnd) {
               if (i == word.length() - 1) {
                   return count >= 1;
               }
               if (isConcatenated(i + 1, word, root, count + 1)) {
                   return true;
               }
           }
       }
       return false;
   } 
    
   private void buildTrie(String[] words, TrieNode root) {
       TrieNode node;
       for (String word : words) {
           node = root;
           for (char ch : word.toCharArray()) {
               int index = ch - 'a';
               if (node.children[index] == null) {
                   node.children[index] = new TrieNode();
               }
               node = node.children[index];
           }
           node.isWordEnd = true;
       }
   }
    
}
