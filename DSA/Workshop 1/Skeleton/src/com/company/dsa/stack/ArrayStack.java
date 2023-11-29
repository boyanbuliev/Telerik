package com.company.dsa.stack;

import java.util.NoSuchElementException;

public class ArrayStack<E> implements Stack<E> {
    private E[] items;
    private int top;

    @Override
    public void push(E element) {
        if (items == null) {
            items = (E[]) new Object[10];
            top = 0;
        }
        if (top == items.length - 1) {
            resize();
        }
        items[top] = element;
        top++;
    }


    @Override
    public E pop() {
        checkSize();
        top--;
        return items[top];
    }

    @Override
    public E peek() {
        checkSize();
        return items[top - 1];
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    private void checkSize() {
        if (isEmpty())
            throw new NoSuchElementException();
    }

    private void resize() {
        E[] curr = (E[]) new Object[items.length * 2];
        for (int i = 0; i < items.length; i++) {
            curr[i] = items[i];
        }
        items = curr;
    }
}
