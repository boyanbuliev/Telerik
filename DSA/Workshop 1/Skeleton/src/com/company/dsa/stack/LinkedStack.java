package com.company.dsa.stack;

import com.company.dsa.Node;

import java.util.NoSuchElementException;

public class LinkedStack<E> implements Stack<E> {
    private Node<E> top;
    private int size;

    @Override
    public void push(E element) {
        Node<E> curr = new Node<>(element);
        if (top == null) {
            top = curr;
            size = 0;
        } else {
            top.next = curr;
        }
        top = curr;
        size++;
    }

    @Override
    public E pop() {
        checkSize();
        Node<E> temp = top;
        top = top.next;
        size--;
        return temp.data;
    }

    @Override
    public E peek() {
        checkSize();
        return top.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    private void checkSize() {
        if (isEmpty())
            throw new NoSuchElementException();
    }
}
