package com.boc.datastructure.queue;

import com.atguigu.datastructure.queue.CircleArrayQueue;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组队列
 * @author boc
 */
public class ArrayQueue<T> {

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(2);
        System.out.println(queue.add(1));
        System.out.println(queue.add(1));
        System.out.println(queue.add(1));

    }

    /** 数据 */
    private T[] data;

    /** 队列头部，先进的放头，头部先出 */
    private int head;

    /** 队列尾部 */
    private int tail;

    /**
     * @param capacity 容量
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        data = (T[]) new Object[capacity];
    }

    public int maxSize() {
        return data.length;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        int oldHead = head;
        head = (head + 1) % maxSize();
        return data[oldHead];
    }

    public boolean add(T t) {
        if (isFull()) {
            return false;
        }
        data[tail = (tail + 1) % maxSize()] = t;
        return true;
    }

    /**
     * @return 返回队列头部数据
     */
    public T get() {
        return isEmpty() ? null : data[head];
    }

    /**
     * @return 是否为空
     */
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * @return 是否已满
     */
    public boolean isFull() {
        return (tail + 1) % maxSize() == head;
    }

}
