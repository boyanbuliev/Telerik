package com.company.dsa;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
    private Node head;
    private Node tail;
    private int size = 0;

    public LinkedList() {
    }

    public LinkedList(Iterable<T> iterable) {
        iterable.forEach(this::addLast);
    }

    @Override
    public void addFirst(T value) {
        Node curr = new Node(value);
        if (size == 0) {
            head = curr;
            tail = curr;
        } else {
            curr.next = head;
            head = curr;
        }
        size++;
    }

    @Override
    public void addLast(T value) {
        Node curr = new Node(value);
        if (size == 0) {
            head = curr;
        } else {
            tail.next = curr;
            curr.prev = tail;
        }
        tail = curr;
        size++;
    }

    @Override
    public void add(int index, T value) {
        if (index == size) {
            addLast(value);
            return;
        }
        checkIndex(index);
        Node prevNode = head;
        for (int i = 0; i < index - 1; i++) {
            prevNode = prevNode.next;
        }
        Node newNode = new Node(value);
        Node nextNode = prevNode.next;
        prevNode.next = newNode;
        nextNode.prev = newNode;
        newNode.prev = prevNode;
        newNode.next = nextNode;
        size++;
    }

    @Override
    public T getFirst() {
        checkIfEmpty();
        return head.value;
    }

    @Override
    public T getLast() {
        checkIfEmpty();
        return tail.value;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        checkIfEmpty();
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.value;
    }

    @Override
    public int indexOf(T value) {
        int index = 0;
        for (Node curr = head; curr != null; curr = curr.next) {
            if (curr.value == value)
                return index;
            index++;
        }
        return -1;
    }

    @Override
    public T removeFirst() {
        checkIfEmpty();
        Node curr = head;
        if (size == 1) {
            head = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return curr.value;
    }

    @Override
    public T removeLast() {
        checkIfEmpty();
        Node curr = tail;
        if (size == 1) {
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return curr.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListItr();
    }

    private class ListItr implements Iterator<T> {
        private Node curr;

        ListItr() {
            curr = head;
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public T next() {
            T value = curr.value;
            curr = curr.next;
            return value;
        }
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new NoSuchElementException();
        }
    }

    private void checkIfEmpty() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
    }

    private class Node {
        T value;
        Node prev;
        Node next;

        Node(T value) {
            this.value = value;
        }
    }
}
