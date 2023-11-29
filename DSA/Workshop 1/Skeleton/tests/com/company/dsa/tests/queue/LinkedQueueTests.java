package com.company.dsa.tests.queue;

import com.company.dsa.queue.LinkedQueue;
import com.company.dsa.queue.Queue;
import com.company.dsa.tests.QueueBaseTests;

public class LinkedQueueTests extends QueueBaseTests {
    @Override
    protected Queue<Integer> initializeQueue() {
        return new LinkedQueue<>();
    }
}