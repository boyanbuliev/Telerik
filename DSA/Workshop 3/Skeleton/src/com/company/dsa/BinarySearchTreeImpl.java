package com.company.dsa;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeImpl<E extends Comparable<E>> implements BinarySearchTree<E> {
    private E value;
    private BinarySearchTreeImpl<E> left;
    private BinarySearchTreeImpl<E> right;

    public BinarySearchTreeImpl(E value) {
        this.value = value;
        left = null;
        right = null;
    }

    @Override
    public E getRootValue() {
        return value;
    }

    @Override
    public BinarySearchTree<E> getLeftTree() {
        return left;
    }

    @Override
    public BinarySearchTree<E> getRightTree() {
        return right;
    }

    @Override
    public void insert(E value) {
        BinarySearchTreeImpl<E> newVal = new BinarySearchTreeImpl<>(value);

        if (value.compareTo(this.value) < 0) {
            if (this.left == null) {
                this.left = newVal;
                return;
            }
            this.left.insert(value);
        } else {
            if (this.right == null) {
                this.right = newVal;
                return;
            }
            this.right.insert(value);
        }
    }

    @Override
    public boolean search(E value) {
        if (this.value == value) {
            return true;
        }

        if (this.left != null && this.left.search(value)) {
            return true;
        }
        if (this.right != null && this.right.search(value)) {
            return true;
        }
        return false;
    }

    @Override
    public List<E> inOrder() {
        List<E> items = new ArrayList<>();

        if (this.left != null) {
            items.addAll(this.left.inOrder());
        }
        items.add(this.value);
        if (this.right != null) {
            items.addAll(this.right.inOrder());
        }
        return items;
    }


    @Override
    public List<E> postOrder() {
        List<E> items = new ArrayList<>();

        if (this.left != null) {
            items.addAll(this.left.postOrder());
        }
        if (this.right != null) {
            items.addAll(this.right.postOrder());
        }
        items.add(this.value);
        return items;
    }

    @Override
    public List<E> preOrder() {
        List<E> items = new ArrayList<>();
        items.add(this.value);
        if (this.left != null) {
            items.addAll(this.left.preOrder());
        }
        if (this.right != null) {
            items.addAll(this.right.preOrder());
        }
        return items;
    }

    @Override
    public List<E> bfs() {
        ArrayDeque<BinarySearchTreeImpl<E>> queue = new ArrayDeque<>();
        List<E> values = new ArrayList<>();
        queue.offer(this);

        while (!queue.isEmpty()) {
            BinarySearchTreeImpl<E> next = queue.pop();
            values.add(next.value);
            if (next.left != null) {
                queue.offer(next.left);
            }
            if (next.right != null) {
                queue.offer(next.right);
            }
        }
        return values;
    }

    @Override
    public int height() {
        return Math.max(this.left != null ? this.left.height() + 1 : 0, this.right != null ? this.right.height() + 1 : 0);
    }

//    @Override
//    public boolean remove(E value) {
//        return false;
//    }


}
