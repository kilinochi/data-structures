package org.kilinochi.ds.set

import org.kilinochi.ds.KCollection

interface KSet<V>: KCollection<V> {

    /**
     * Adds the specified element to this set if it is not already present.
     *
     * @param value element to be added to this set
     * @return `true` if this set did not already contain the specified
     * element
     */
    override fun add(value: V): Boolean

    /**
     * Removes the specified element from this set if it is present.
     *
     * @param value object to be removed from this set, if present
     * @return `true` if this set contained the specified element
     */
    override fun remove(value: V): Boolean
}