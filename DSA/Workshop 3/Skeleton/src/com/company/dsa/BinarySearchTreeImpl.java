package com.company.dsa;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
        if (value.compareTo(this.value) < 0) {
            if (left == null) {
                left = new BinarySearchTreeImpl<>(value);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new BinarySearchTreeImpl<>(value);
            } else {
                right.insert(value);
            }
        }
    }

    @Override
    public boolean search(E value) {
        return value.compareTo(this.value) == 0 || (value.compareTo(this.value) < 0 ? left != null && left.search(value) : (right != null && right.search(value)));
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
        Queue<BinarySearchTreeImpl<E>> queue = new ArrayDeque<>();
        List<E> values = new ArrayList<>();
        queue.offer(this);

        while (!queue.isEmpty()) {
            BinarySearchTreeImpl<E> next = queue.poll();
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

    @Override
    public boolean remove(E value) {
        if (!search(value)) {
            return false;
        }

        return removeNodeRecursively(value, this);
    }

    private boolean removeNodeRecursively(E value, BinarySearchTreeImpl<E> root) {
        BinarySearchTreeImpl<E> node = getNode(value);
        BinarySearchTreeImpl<E> parent = getParent(node, root);

        if (node.left == null && node.right == null) {
            removeLeaf(node, parent);
        } else if (node.left == null) {
            removeSingleChildNode(node, parent, node.right);
        } else if (node.right == null) {
            removeSingleChildNode(node, parent, node.left);
        } else {
            BinarySearchTreeImpl<E> maxNodeInLeftSubtree = getMaxValueNode(node.left);
            node.value = maxNodeInLeftSubtree.value;
            node.left.removeNodeRecursively(maxNodeInLeftSubtree.value, root);
        }
        return true;
    }

    private BinarySearchTreeImpl<E> getMaxValueNode(BinarySearchTreeImpl<E> node) {
        if (node.right == null) {
            return node;
        }
        return right.getMaxValueNode(node.right);
    }

    private void removeSingleChildNode(BinarySearchTreeImpl<E> nodeToRemove, BinarySearchTreeImpl<E> parent, BinarySearchTreeImpl<E> nodeToStay) {
        if (parent.left == nodeToRemove) {
            parent.left = nodeToStay;
        }
        if (parent.right == nodeToRemove) {
            parent.right = nodeToStay;
        }
    }

    private void removeLeaf(BinarySearchTreeImpl<E> leaf, BinarySearchTreeImpl<E> parent) {
        if (parent.left == leaf) {
            parent.left = null;
        }
        if (parent.right == leaf) {
            parent.right = null;
        }
    }

    private BinarySearchTreeImpl<E> getParent(BinarySearchTreeImpl<E> node, BinarySearchTreeImpl<E> root) {
        if (node == root) {
            return root;
        }
        if (root.left == node || root.right == node) {
            return root;
        }
        if (node.value.compareTo(root.value) <= 0) {
            return getParent(node, root.left);
        }
        return getParent(node, root.right);
    }

    private BinarySearchTreeImpl<E> getNode(E value) {
        if (value.equals(this.value)) {
            return this;
        } else if (value.compareTo(this.value) < 0) {
            return this.left.getNode(value);
        }
        return this.right.getNode(value);
    }
}