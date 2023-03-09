package com.astondevs.yanizamovramil.lessons.lesson_000.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyArrayListTest {

    private MyArrayList<Integer> elementArray;
    Comparator<Integer> comparator;

    @BeforeEach
    void init() {
        elementArray = new MyArrayList<>();
        comparator = Integer::compare;
    }

    @Test
    void add() {
        boolean isAdded = elementArray.add(1);
        assertEquals(1, elementArray.size());
        assertTrue(isAdded);
    }

    @Test
    void addByIndex() {
        boolean isAdded = elementArray.add(0, 1);
        assertEquals(1, elementArray.size());
        assertTrue(isAdded);
    }

    @Test
    void get() {
        elementArray.add(1);
        Integer one = elementArray.get(0);
        assertEquals(1, one);
    }

    @Test
    void remove() {
        elementArray.add(1);
        Integer one = elementArray.remove(0);
        assertEquals(0, elementArray.size());
        assertEquals(1, one);
    }

    @Test
    void removeAll() {
        elementArray.add(1);
        elementArray.add(2);
        elementArray.removeAll();
        assertEquals(0, elementArray.size());
    }

    @Test
    void sort() {
        elementArray.add(3);
        elementArray.add(1);
        elementArray.add(5);
        elementArray.add(4);
        elementArray.add(2);
        elementArray.sort(comparator);
        assertEquals(1, elementArray.get(0));
        assertEquals(5, elementArray.get(4));
    }

    @Test
    void size() {
        assertEquals(0, elementArray.size());
    }
}