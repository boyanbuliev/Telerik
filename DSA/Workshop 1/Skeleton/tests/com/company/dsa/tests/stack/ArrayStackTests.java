package com.company.dsa.tests.stack;

import com.company.dsa.stack.ArrayStack;
import com.company.dsa.stack.Stack;
import com.company.dsa.tests.StackBaseTests;

public class ArrayStackTests extends StackBaseTests {
    @Override
    protected Stack<Integer> initializeStack() {
        return new ArrayStack<>();
    }
}