package com.company.dsa.tests;

import com.company.dsa.stack.ArrayStack;
import com.company.dsa.stack.Stack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class StackBaseTests {
    public Stack<Integer> stack;

    @BeforeEach
    public void before(){
        stack = initializeStack();
    }

    protected abstract Stack<Integer> initializeStack();

    @Test
    public void initialization_Should_CreateEmptyStack(){
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void push_Should_AddElementToTheTop_When_StackEmpty() {
        // Act
        stack.push(1);
        // Assert
        Assertions.assertEquals(1, stack.peek());
    }

    @Test
    public void push_Should_AddElementToTheTop_When_StackNotEmpty() {
        // Arrange
        stack.push(1);
        // Act
        stack.push(2);
        // Assert
        Assertions.assertEquals(2, stack.peek());
    }

    @Test
    public void pop_Should_ThrowException_When_StackEmpty() {
        // Act, Assert
        Assertions.assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    @Test
    public void pop_Should_ReturnTop_When_StackNotEmpty() {
        // Arrange
        stack.push(1);
        stack.push(2);
        stack.push(3);
        // Act, Assert
        Assertions.assertEquals(3, stack.pop());
    }

    @Test
    public void peek_Should_ThrowException_When_StackEmpty() {
        // Act
        Assertions.assertThrows(NoSuchElementException.class, () -> stack.peek());
    }

    @Test
    public void peek_Should_ReturnTop_When_StackNotEmpty() {
        // Arrange
        stack.push(1);
        stack.push(2);
        stack.push(3);
        // Act&&Assert
        Assertions.assertEquals(3, stack.peek());
    }

    @Test
    public void size_Should_ReturnZero_When_StackEmpty() {
        // Act&&Assert
        Assertions.assertEquals(0, stack.size());
    }

    @Test
    public void size_Should_ReturnProperValue_When_StackNotEmpty() {
        // Arrange
        stack.push(1);
        stack.push(2);
        stack.push(3);
        // Act&&Assert
        Assertions.assertEquals(3, stack.size());
    }

    @Test
    public void isEmpty_Should_ReturnTrue_When_StackEmpty() {
        // Assert
        Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    public void isEmpty_Should_ReturnFalse_When_StackNotEmpty() {
        // Act
        stack.push(1);
        // Assert
        Assertions.assertFalse(stack.isEmpty());
    }

    @Test
    public void addLast_Should_MaintainCorrectOrder() {
        // Arrange & Act
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        stack = new ArrayStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        // Assert
        Assertions.assertTrue(testListMatchesExpected(expected));
    }

    private boolean testListMatchesExpected(List<Integer> values) {
        if (stack.size() != values.size()) {
            return false;
        }
        int index = stack.size() - 1;

        for (int i = 0; i < stack.size() - 1; i++) {
            if (stack.pop().intValue() != values.get(index)) {
                return false;
            }
            index--;
        }
        return true;
    }
}
