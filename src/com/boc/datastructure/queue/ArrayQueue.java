package com.boc.datastructure.queue;

import com.atguigu.datastructure.queue.CircleArrayQueue;

/**
 * 数组队列
 * @author boc
 */
public class ArrayQueue<T> {

    public static void main(String[] args) {

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
        // 实际容量会少一个，因为判断下一个能不能添加需要用尾部+1，实际上在上一次添加的时候尾部就+过1了，因此如果再判断是否为空和是否满的时候就会计算到两个+1，比如总容量是3，当前是2，判断的时候会(2+1)%3，发现已经到了头部，无法添加，
        // 实际上2是可添加的，这会导致可用容量少一个，又不能在每次要add的时候再计算，因为一开始是0，add的时候要放在0，默认值就算是计算好的，如果设置为-1又会导致其它问题，比如判断是否为空和是否满的时候怎么处理
        // 泛型在运行时会擦除，因此还是Object对象，不会出现转换异常
        data = (T[]) new Object[capacity + 1];
    }

    /**
     * @return 用户指定的容量
     */
    public int getCapacity() {
        return data.length - 1;
    }

    public int size() {
        // 尾减去头，尾在前面正好取到它们之间的数量，加上最大值再取余还是它们相差的数量
        // 如果头在前面，取到的就是中间为空的数量，这时只需要前面的某部分和后面的某部分，这时候用总容量减去中间这一块，剩下的就是前后的容量了，这种情况下取余不会生效
        return (tail - head + maxSize()) % maxSize();
    }

    /**
     * @return 实际容量，实际会多出一个
     */
    private int maxSize() {
        return data.length;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T tmp = data[head];
        head = (head + 1) % maxSize();
        return tmp;
    }

    public boolean add(T t) {
        if (isFull()) {
            return false;
        }
        data[tail] = t;
        tail = (tail + 1) % maxSize();
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
