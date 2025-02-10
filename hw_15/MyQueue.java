package hw_15;

public class MyQueue {
    private final int[] queue;
    private int front = 0;
    private int offset = 0;
    private int size = 0;

    public MyQueue(int capacity) {
        this.queue = new int[capacity];
    }

    public void enqueue(int value) {
        this.queue[offset++] = value;
        size++;
    }

    public int dequeue() {
        size--;
        return this.queue[front++];
    }

    public boolean isNotEmpty() {
        return size != 0;
    }

    public int size() {
        return size;
    }
}
