package com.company.dsa;

import com.company.dsa.queue.ArrayQueue;

public class Main {
    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(3);


        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.peek());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());


    }
}
