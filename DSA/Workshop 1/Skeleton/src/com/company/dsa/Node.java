package com.company.dsa;

// You can skip encapsulation here for convenience.
public class Node<E> {
    public E data;
    public Node<E> next;

    public Node(E data) {
        this.data = data;
    }
}
