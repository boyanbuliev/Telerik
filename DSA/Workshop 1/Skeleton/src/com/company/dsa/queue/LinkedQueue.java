package com.company.dsa.queue;

import com.company.dsa.Node;

import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E> {
    private Node<E> head, tail;
    private int size;

    public LinkedQueue() {
        size = 0;
    }

    @Override
    public void enqueue(E element) {
        Node<E> curr = new Node<>(element);
        if (head == null) {
            head = curr;
        } else {
            tail.next = curr;
        }
        tail = curr;
        size++;
    }

    @Override
    public E dequeue() {
        checkSize();
        Node<E> curr = head;
        head = head.next;
        size--;
        return curr.data;
    }

    @Override
    public E peek() {
        checkSize();
        return head.data;
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
}
