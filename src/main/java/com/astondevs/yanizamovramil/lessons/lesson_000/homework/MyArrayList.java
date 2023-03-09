package com.astondevs.yanizamovramil.lessons.lesson_000.homework;

import java.util.Comparator;

/**
 * The type My array list - is the collection implementation instead of standard ArrayList.
 * Provides collection methods for adding, getting removing and sorting operations on its elements.
 * Max size of collection elements provided is appointed as Integer.MAX_VALUE - 8.
 *
 * @param <E> the type parameter.
 * @author Ramil Nizamov
 */
public class MyArrayList<E> implements MyList<E> {

    /**
     * the index of the last element in collection.
     * Default value empty collection is -1.
     */
    private int biggestIndex = -1;

    /**
     * array, used as elements' storage.
     */
    private Object[] elementArray;

    /**
     * The default value for setting of collection element capacity.
     */
    private static final int DEFAULT_ARRAY_CAPACITY = 100;

    /**
     * Instantiates a new My array list instance with default elements capacity.
     */
    public MyArrayList() {
        elementArray = new Object[DEFAULT_ARRAY_CAPACITY];
    }

    /**
     * Adding element to collection by order.
     *
     * @param e element to be added to collection.
     * @return true - if element e added, false - if element e is null, so element e could not be added.
     * @throws ArrayIndexOutOfBoundsException if max size of elements in MyArrayList instance has been reached,
     *                                        so element e could not be added.
     */
    @Override
    public boolean add(E e) {
        if (e == null) {
            return false;
        }
        if (biggestIndex + 1 >= (Integer.MAX_VALUE - 8)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int min = Integer.MAX_VALUE / 27 * 10;
        int max = Integer.MAX_VALUE - 8;
        Object[] extendedElementArray;
        if (elementArray.length == (biggestIndex + 1) && (biggestIndex + 1) <= min) {
            int extended = (elementArray.length / 10) * 27 - 11;
            extendedElementArray = new Object[extended];
            System.arraycopy(elementArray, 0, extendedElementArray, 0, (biggestIndex + 1));
            elementArray = extendedElementArray;
        } else if (elementArray.length == (biggestIndex + 1) && elementArray.length >= min) {
            extendedElementArray = new Object[max];
            System.arraycopy(elementArray, 0, extendedElementArray, 0, (biggestIndex + 1));
            elementArray = extendedElementArray;
        }
        elementArray[biggestIndex + 1] = e;
        biggestIndex++;
        return true;
    }

    /**
     * Adding element to collection at index number, saving order of other elements.
     *
     * @param index the index number, where element e assumed to add.
     * @param e     element to be added to collection.
     * @return true - if element e added, false - if element e is null, so element e could not be added.
     * @throws ArrayIndexOutOfBoundsException if instance storage elements max capacity has been reached,
     *                                        so element e could not be added.
     * @throws IndexOutOfBoundsException      if index number is negative or bigger by 2 than last element index.
     **/
    @Override
    public boolean add(int index, E e) {
        if (index < 0 || index > (biggestIndex + 2)) {
            throw new IndexOutOfBoundsException();
        }
        if (e == null) {
            return false;
        }
        if (biggestIndex + 1 >= (Integer.MAX_VALUE - 8)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int min = Integer.MAX_VALUE / 27 * 10;
        int max = Integer.MAX_VALUE - 8;
        Object[] extendedElementArray = new Object[elementArray.length];
        if (elementArray.length == (biggestIndex + 1) && (biggestIndex + 1) <= min) {
            int extended = (elementArray.length / 10) * 27 - 11;
            extendedElementArray = new Object[extended];
        } else if (elementArray.length == (biggestIndex + 1) && elementArray.length >= min) {
            extendedElementArray = new Object[max];
        }
        if (index == 0) {
            System.arraycopy(elementArray, 0, extendedElementArray, 1, (biggestIndex + 1));
        } else if (index == biggestIndex) {
            System.arraycopy(elementArray, 0, extendedElementArray, 0, (biggestIndex));
            extendedElementArray[biggestIndex + 1] = elementArray[biggestIndex];
        } else if (index == biggestIndex + 1) {
            System.arraycopy(elementArray, 0, extendedElementArray, 0, (biggestIndex + 1));
        } else {
            System.arraycopy(elementArray, 0, extendedElementArray, 0, (index + 1));
            System.arraycopy(elementArray, (index + 1), extendedElementArray, (index + 1), (biggestIndex - index));
        }
        elementArray = extendedElementArray;
        elementArray[index] = e;
        biggestIndex++;
        return true;
    }

    /**
     * Getting of element e stored at index number.
     *
     * @param index the index number, whose element e assumed to get.
     * @return element e stored at index number
     * @throws IndexOutOfBoundsException if index is negative number or bigger than the index of last element
     *                                   in collection
     */
    @Override
    public E get(int index) {
        if (index < 0 || index > (biggestIndex)) {
            throw new IndexOutOfBoundsException();
        }
        return (E) elementArray[index];
    }

    /**
     * Removes element e at index number.
     *
     * @param index the index number, whose element e assumed to remove.
     * @return removed element e at index number
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index > (biggestIndex + 1)) {
            throw new IndexOutOfBoundsException();
        }
        E e = (E) elementArray[index];
        if (index == (biggestIndex + 1)) {
            elementArray[index] = null;
            biggestIndex--;
            return e;
        }
        Object[] bufferElementArray = new Object[elementArray.length];
        if (index == 0) {
            System.arraycopy(elementArray, 1, bufferElementArray, 0, biggestIndex + 1);
        } else {
            System.arraycopy(elementArray, 0, bufferElementArray, 0, index);
            System.arraycopy(elementArray, index + 1, bufferElementArray, index, (biggestIndex - index));
        }
        biggestIndex--;
        elementArray = bufferElementArray;
        return e;
    }

    /**
     * Clears all data in collection and sets all its properties to default values.
     */
    @Override
    public void removeAll() {
        elementArray = new Object[100];
        biggestIndex = -1;
    }

    /**
     * Sorts all elements in collection by comparator rules.
     *
     * @param comparator Comparator of type E or extended one from E, used for comparing and sorting elements.
     * @throws NullPointerException if comparator is null.
     */
    @Override
    public void sort(Comparator<? super E> comparator) {
        if (comparator == null) {
            throw new NullPointerException();
        }
        MyArrayList.quickSort(elementArray, 0, biggestIndex, comparator);
    }

    private static <E> void quickSort(Object[] elementArray, int left, int right, Comparator<? super E> comparator) {
        if (left >= right) {
            return;
        }
        int partIndex = partition(elementArray, left, right, comparator);
        quickSort(elementArray, left, partIndex - 1, comparator);
        quickSort(elementArray, partIndex + 1, right, comparator);
    }

    private static <E> int partition(Object[] elementArray, int left, int right, Comparator<? super E> comparator) {
        E pivot = (E) elementArray[right];
        int wall = left - 1;

        for (int i = left; i < right; i++) {
            E indexElement = (E) elementArray[i];
            if (comparator.compare(indexElement, pivot) < 0) {
                wall++;
                swap(elementArray, wall, i);
            }
        }
        swap(elementArray, wall + 1, right);
        return wall + 1;
    }

    private static void swap(Object[] elementArray, int wall, int i) {
        Object swapElement = elementArray[wall];
        elementArray[wall] = elementArray[i];
        elementArray[i] = swapElement;
    }

    /**
     * Getting the size of collection.
     *
     * @return Number of elements stored in collection.
     */
    @Override
    public int size() {
        return biggestIndex + 1;
    }
}