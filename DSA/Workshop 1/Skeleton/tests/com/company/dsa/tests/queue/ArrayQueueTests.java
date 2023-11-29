package com.company.dsa.tests.queue;

import com.company.dsa.queue.ArrayQueue;
import com.company.dsa.queue.Queue;
import com.company.dsa.tests.QueueBaseTests;

public class ArrayQueueTests extends QueueBaseTests {
    @Override
    protected Queue<Integer> initializeQueue() {
        return new ArrayQueue<>();
    }
}