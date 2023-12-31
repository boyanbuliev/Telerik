package com.company.oop;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyListImpl<T> implements MyList<T> {

    private static final int DEFAULT_CAPACITY = 4;

    private T[] data;
    private int size;

    public MyListImpl() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyListImpl(int capacity) {
        this.data = (T[]) new Object[capacity];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return data.length;
    }

    @Override
    public void add(T element) {
        if (size >= data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[size++] = element;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", index, size));
        }
        return data[index];
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        for (int i = size - 1; i >= 0; i--) {
            if (element.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", index, size));
        }
        T[] tempArr = (T[]) new Object[data.length];
        for (int i = 0, j = 0; i < size; i++) {
            if (i != index)
                tempArr[j++] = data[i];
        }
        data = tempArr;
        size--;
    }

    @Override
    public boolean remove(T element) {
        if (indexOf(element) != -1) {
            removeAt(indexOf(element));
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public void swap(int from, int to) {
        T tempEl = data[from];
        data[from] = data[to];
        data[to] = tempEl;
    }

    @Override
    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1)
                sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrIterator();
    }

    private class ArrIterator implements Iterator<T> {
        private int index;

        public ArrIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            return data[index++];
        }
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for (T t : this) {
            action.accept(t);
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return new ArrSpliterator(this);
    }

    private class ArrSpliterator implements Spliterator<T> {
        private final MyList<T> elements;
        private int index;
        private final int fence;

        public ArrSpliterator(MyList<T> elements) {
            this.elements = elements;
            index = 0;
            fence = elements.size();
        }

        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            if (index < fence) {
                action.accept(elements.get(index++));
                return true;
            }
            return false;
        }


        @Override
        public Spliterator<T> trySplit() {
            int currentSize = size - index;
            if (currentSize < 2) {
                return null;
            }
            int splitIndex = index + size / 2;
            MyList<T> tempArr = new MyListImpl<>();
            for (int i = 0; i < splitIndex; i++) {
                tempArr.add(elements.get(i));
            }
            ArrSpliterator newSpliterator = new ArrSpliterator(tempArr);
            index = splitIndex;
            return newSpliterator;
        }

        @Override
        public long estimateSize() {
            return size - index;
        }

        @Override
        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
        }
    }
}
