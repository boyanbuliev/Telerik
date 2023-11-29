package com.company.dsa.tests.stack;

import com.company.dsa.stack.LinkedStack;
import com.company.dsa.stack.Stack;
import com.company.dsa.tests.StackBaseTests;

public class LinkedStackTests extends StackBaseTests {
    @Override
    protected Stack<Integer> initializeStack() {
        return new LinkedStack<>();
    }
}