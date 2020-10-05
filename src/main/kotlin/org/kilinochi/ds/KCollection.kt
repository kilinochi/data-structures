package org.kilinochi.ds

import java.util.stream.Stream

interface KCollection<V> {

    /**
     * Adds the specified element to this set if it is not already present.
     *
     * @param value element to be added to this set
     * @return `true` if this set did not already contain the specified
     * element
     */
    fun add(value: V): Boolean

    /**
     * Removes the specified element from this set if it is present.
     *
     * @param value object to be removed from this set, if present
     * @return `true` if this set contained the specified element
     */
    fun remove(value: V): Boolean

    /**
     * Returns the number of elements in this collection.
     *
     * @return the number of elements in this collection
     */
    fun size(): Int

    /**
     * Returns `true` if this collection contains no elements.
     *
     * @return `true` if this collection contains no elements
     */
    fun isEmpty(): Boolean

    /**
     * Returns `true` if this collection contains the specified element.
     * aka collection contains element el such that `Objects.equals(el, value) == true`
     *
     * @param value element whose presence in this collection is to be tested
     * @return `true` if this collection contains the specified element
     * @throws NullPointerException if the specified element is null
     */
    fun contains(value: V): Boolean

    /**
     * Removes all of the elements from this collection.
     * The collection will be empty after this method returns.
     */
    fun clear()

    /**
     * return stream of elements from this collection.
     *
     * @return stream of elements from this collection.
     */
    @JvmDefault
    fun stream(): Stream<V> {
        return Stream.empty()
    }
}