1.The key to solve this problem is using a double linked list which enables us to quickly move nodes.
2.The LRU cache is a hash table of keys and double linked nodes. The hash table makes the time of get() to be O(1). 
The list of double linked nodes make the nodes adding/removal operations O(1).

class Node {
    int key;
    int value;
    Node prev;
    Node next;
    
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
class LRUCache {
    
    HashMap<Integer, Node> map;
    int capacity, count;
    Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
        
    }
    
    public int get(int key) {
        if (map.get(key) != null) {
            Node node = map.get(key);
            int res = node.value;
            delete(node);
            addToHead(node);
            return res;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (map.get(key) != null) {
            Node node = map.get(key);
            node.value = value;
            delete(node);
            addToHead(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            if (count < capacity) {
                count++;
                addToHead(node);
            } else {
                Node toDel = tail.prev;
                map.remove(toDel.key);
                delete(toDel);
                addToHead(node);
            }
        }
    }
    
    private void addToHead(Node node) {
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;
    }
    
    private void delete(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
}
