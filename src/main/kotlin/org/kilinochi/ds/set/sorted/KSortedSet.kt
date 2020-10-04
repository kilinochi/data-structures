package org.kilinochi.ds.set.sorted

import org.kilinochi.ds.set.KSet

interface KSortedSet<V: Comparable<V>>: KSet<V> {

    /**
     * Returns the first (lowest) element currently in this set.
     *
     * @return the first (lowest) element currently in this set
     * @throws java.util.NoSuchElementException if this set is empty
     */
    fun first(): V

    /**
     * Returns the last (highest) element currently in this set.
     *
     * @return the last (highest) element currently in this set
     * @throws java.util.NoSuchElementException if this set is empty
     */
    fun last(): V
}