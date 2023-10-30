package com.company.oop;

public class Main {
    public static void main(String[] args) {

        MyList<String> list = new MyListImpl<>();
//
        list.add("Cat");
        list.add("Dog");
        list.add("Dog");
        list.add("Mouse");
        list.add("Cat");

        list.forEach(System.out::println);
    }

}