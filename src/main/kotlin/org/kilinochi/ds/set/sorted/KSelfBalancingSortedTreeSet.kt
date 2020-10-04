package org.kilinochi.ds.set.sorted

import org.kilinochi.ds.set.sorted.exception.UnbalancedTreeException

interface KSelfBalancingSortedTreeSet<V : Comparable<V>>: KSortedSet<V> {

    /**
     * Adds the specified element to this set if it is not already present.
     *
     * Complexity = O(log(n))
     *
     * @param value element to be added to this set
     * @return `true` if this set did not already contain the specified
     * element
     */
    override fun add(value: V): Boolean

    /**
     * Removes the specified element from this set if it is present.
     *
     * Complexity = O(log(n))
     *
     * @param value object to be removed from this set, if present
     * @return `true` if this set contained the specified element
     */
    override fun remove(value: V): Boolean

    /**
     * Returns `true` if this collection contains the specified element.
     * aka collection contains element el such that `Objects.equals(el, value) == true`
     *
     * Complexity = O(log(n))
     *
     * @param value element whose presence in this collection is to be tested
     * @return `true` if this collection contains the specified element
     */
    override fun contains(value: V): Boolean

    /**
     * Returns the first (lowest) element currently in this set.
     *
     * Complexity = O(log(n))
     *
     * @return the first (lowest) element currently in this set
     * @throws java.util.NoSuchElementException if this set is empty
     */
    override fun first(): V

    /**
     * Returns the last (highest) element currently in this set.
     *
     * Complexity = O(log(n))
     *
     * @return the last (highest) element currently in this set
     * @throws java.util.NoSuchElementException if this set is empty
     */
    override fun last(): V

    /**
     * Traverse self balanced tree set and check balance correctness.
     *
     * @throws UnbalancedTreeException if the self balanced tree set is unbalanced
     */
    @Throws(UnbalancedTreeException::class)
    fun checkBalance()
}