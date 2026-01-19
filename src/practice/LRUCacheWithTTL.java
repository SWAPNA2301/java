package practice;
import java.util.*;
import java.util.concurrent.locks.*;

public class LRUCacheWithTTL<K, V> {

    private class Node {
        K key;
        V value;
        long expiryTime;
        Node prev, next;

        Node(K key, V value, long ttl) {
            this.key = key;
            this.value = value;
            this.expiryTime = System.currentTimeMillis() + ttl;
        }
    }

    private final int capacity;
    private final Map<K, Node> cache;
    private final Node head, tail;
    private final ReentrantLock lock = new ReentrantLock();

    public LRUCacheWithTTL(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(null, null, Long.MAX_VALUE);
        this.tail = new Node(null, null, Long.MAX_VALUE);
        head.next = tail;
        tail.prev = head;

        startCleanupThread();
    }

    public V get(K key) {
        lock.lock();
        try {
            Node node = cache.get(key);
            if (node == null || isExpired(node)) {
                removeNode(node);
                cache.remove(key);
                return null;
            }
            moveToFront(node);
            return node.value;
        } finally {
            lock.unlock();
        }
    }

    public void put(K key, V value, long ttlMillis) {
        lock.lock();
        try {
            if (cache.containsKey(key)) {
                Node node = cache.get(key);
                node.value = value;
                node.expiryTime = System.currentTimeMillis() + ttlMillis;
                moveToFront(node);
                return;
            }

            if (cache.size() >= capacity) {
                Node lru = tail.prev;
                removeNode(lru);
                cache.remove(lru.key);
            }

            Node newNode = new Node(key, value, ttlMillis);
            cache.put(key, newNode);
            addToFront(newNode);

        } finally {
            lock.unlock();
        }
    }

    private boolean isExpired(Node node) {
        return System.currentTimeMillis() > node.expiryTime;
    }

    private void moveToFront(Node node) {
        removeNode(node);
        addToFront(node);
    }

    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        if (node == null) return;
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void startCleanupThread() {
        Thread cleaner = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    Iterator<Map.Entry<K, Node>> it = cache.entrySet().iterator();
                    while (it.hasNext()) {
                        Node node = it.next().getValue();
                        if (isExpired(node)) {
                            removeNode(node);
                            it.remove();
                        }
                    }
                } finally {
                    lock.unlock();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        });
        cleaner.setDaemon(true);
        cleaner.start();
    }

    // Testing
    public static void main(String[] args) throws InterruptedException {
        LRUCacheWithTTL<Integer, String> cache = new LRUCacheWithTTL<>(2);

        cache.put(1, "A", 2000);
        cache.put(2, "B", 2000);

        System.out.println(cache.get(1)); // A
        Thread.sleep(2100);
        System.out.println(cache.get(1)); // null (expired)

        cache.put(3, "C", 2000);
        System.out.println(cache.get(2)); // B
        System.out.println(cache.get(3)); // C
    }
}
