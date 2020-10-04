package org.kilinochi.ds

interface KCollection<V> {


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
}