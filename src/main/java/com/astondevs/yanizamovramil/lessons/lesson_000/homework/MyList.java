package com.astondevs.yanizamovramil.lessons.lesson_000.homework;

import java.util.Comparator;

/**
 * The interface My list is ordered element storage collection instead of standard List interface.
 * Provides collection methods for adding, getting removing and sorting operations on its elements.
 * @param <E> the type parameter
 * @author Ramil Nizamov
 */
public interface MyList<E> {

    /**
     * Adding element to collection by order.
     * @param e element to be added to collection.
     * @return true - if element e added.
     */
    boolean add(E e);

    /**
     * Adding element to collection at index number, saving order of other elements.
     * @param index the index number, where element e assumed to add.
     * @param e element to be added to collection.
     * @return true - if element e added.
     */
    boolean add(int index, E e);

    /**
     * Getting of element e stored at index number.
     * @param index the index number, whose element e assumed to get.
     * @return element e stored at index number
     */
    E get(int index);

    /**
     * Removes element e at index number.
     * @param index the index number, whose element e assumed to remove.
     * @return removed element e at index number
     */
    E remove(int index);

    /**
     * Clears all data in collection.
     */
    void removeAll();

    /**
     * Sorts all elements in collection by comparator rules.
     * @param comparator Comparator of type E or extended one from E, used for comparing and sorting elements.
     */
    void sort(Comparator<? super E> comparator);

    /**
     * Getting the size of collection.
     * @return Number of elements stored in collection.
     */
    int size();
}
