package com.company.dsa.queue;

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {
    private E[] items;
    private int head, tail, size;

    public ArrayQueue() {
        items = (E[]) new Object[10];
        head = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public void enqueue(E element) {
        if (tail == items.length) {
            resize();
        }
        items[tail] = element;
        size++;
        tail++;
    }

    @Override
    public E dequeue() {
        checkSize();
        head++;
        size--;
        return items[head - 1];
    }

    @Override
    public E peek() {
        checkSize();
        return items[head];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkSize() {
        if (isEmpty())
            throw new NoSuchElementException();
    }

    private void resize() {
        E[] curr = (E[]) new Object[items.length * 2];
        System.arraycopy(items, head, curr, 0, tail);
        head = 0;
        tail = size;
        items = curr;
    }
}
