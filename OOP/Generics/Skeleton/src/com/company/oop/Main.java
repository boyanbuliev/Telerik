package com.company.oop;

import java.util.Spliterator;

public class Main {
    public static void main(String[] args) {

        MyList<String> list = new MyListImpl<>();
//
        list.add("Cat");
        list.add("Dog");
        list.add("Dog");
        list.add("Mouse");
        list.add("Cat");

        Spliterator<String> spliterator = list.spliterator().trySplit();
        spliterator.forEachRemaining(System.out::println);
    }

}