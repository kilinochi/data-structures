package org.kilinochi.ds.set.sorted

import org.kilinochi.ds.set.sorted.exception.UnbalancedTreeException
import java.util.Comparator
import java.util.NoSuchElementException

open class AVLTree<V : Comparable<V>>: KSelfBalancingSortedTreeSet<V> {
    private val comparator: Comparator<V>

    private var root: Node<V>? = null
    private var length: Int = 0

    constructor(comparator: Comparator<V>) {
        this.comparator = comparator
    }

    constructor() : this(Comparator.naturalOrder<V>())

    override fun add(value: V): Boolean {

        val current = find(root, value)

        if (current == null) {
            insert(value)
            length++
            return true
        }

        return false
    }

    override fun first(): V {
        if (root == null) {
            throw NoSuchElementException("This collection must not be empty")
        }

        var current = root!!

        while (current.right != null) {
            current = current.right!!
        }

        return current.data
    }

    override fun last(): V {
        if (root == null) {
            throw NoSuchElementException("This collection must not be empty")
        }

        var current = root!!

        while (current.left != null) {
            current = current.left!!
        }

        return current.data
    }

    override fun remove(value: V): Boolean {

        find(root, value) ?: return false

        delete(value)
        length--
        return true
    }

    override fun contains(value: V): Boolean {
        val current: Node<V>? = find(root, value)
        return current?.data != null
    }

    override fun checkBalance() {
        traverseTreeAndCheckBalanced(root);
    }

    override fun size(): Int {
        return length
    }

    override fun isEmpty(): Boolean {
        return root == null && length == 0
    }

    override fun clear() {
        this.root = null
        length = 0
    }

    private fun find(current: Node<V>?, data: V): Node<V>? {

        if (current == null) {
            return current
        }

        val compare = comparator.compare(current.data, data)

        when {
            compare > 0 -> return find(current.left, data)

            compare < 0 -> return find(current.right, data)
        }

        return current
    }

    private fun insert(value: V) {
        this.root = insert(this.root, value)
    }

    private fun insert(current: Node<V>?, data: V): Node<V>? {

        if (current == null) {
            return Node(data)
        }

        val compare = comparator.compare(current.data, data)

        when {
            compare > 0 -> {
                current.left = insert(current.left, data)
            }
            compare < 0 -> {
                current.right = insert(current.right, data)
            }
        }

        current.height = Math.max(getHeight(current.left), getHeight(current.right)) + 1

        val balanceFactor = balanceFactor(current)

        /*if balanceFactor not 0 or 1, we have to do balance our Tree*/

        //LL Case
        if (balanceFactor > 1 && comparator.compare(data, current.left!!.data) < 0) {
            return rightRotate(current);
        }

        //RR Case
        if (balanceFactor < -1 && comparator.compare(data, current.right!!.data) > 0) {
            return leftRotate(current);
        }

        // LR Case
        if (balanceFactor > 1 && comparator.compare(data, current.left!!.data) > 0) {
            current.left = leftRotate(current.left!!)
            return rightRotate(current)
        }
        // RL Case
        if (balanceFactor < -1 && comparator.compare(data, current.right!!.data) < 0) {
            current.right = rightRotate(current.right!!)
            return leftRotate(current)
        }
        return current;

    }

    private fun balanceFactor(node: Node<V>?): Int {
        if (node == null) {
            return 0
        }

        return getHeight(node.left) - getHeight(node.right)
    }

    private fun getHeight(node: Node<V>?): Int {
        if (node == null) {
            return 0
        }

        return node.height
    }

    private fun rightRotate(current: Node<V>): Node<V>? {

        val b = current.left
        current.left = b?.right

        //rotate
        b?.right = current

        //upd
        current.height = Math.max(getHeight(current.left), getHeight(current.right)) + 1
        b?.height = Math.max(getHeight(b?.left), getHeight(b?.right)) + 1
        return b
    }


    private fun leftRotate(current: Node<V>): Node<V>? {

        val b = current.right
        current.right = b?.left

        //rotate
        b?.left = current

        //upd
        current.height = Math.max(getHeight(current.left), getHeight(current.right)) + 1
        b?.height = Math.max(getHeight(b?.left), getHeight(b?.right)) + 1
        return b
    }

    private fun delete(value: V) {
        this.root = root?.let { delete(it, value) }
    }

    private fun delete(current: Node<V>?, data: V): Node<V>? {

        if (current == null) {
            throw NoSuchElementException()
        }

        if (comparator.compare(data, current.data) < 0) {
            current.left = delete(current.left, data)
        } else if (comparator.compare(data, current.data) > 0) {
            current.right = delete(current.right, data)
        } else {
            val currentLeft: Node<V>? = current.left
            val currentRight: Node<V> = current.right ?: return currentLeft
            val min: Node<V> = treeMin(currentRight)

            min.right = removeMin(currentRight)
            min.left = currentLeft
            return fixRemoveBalance(min)
        }
        return fixRemoveBalance(current)
    }

    private fun treeMin(current: Node<V>): Node<V> {

        var curr = current
        while (curr.left != null) {
            curr = curr.left!!
        }
        return curr
    }

    private fun removeMin(current: Node<V>?): Node<V>? {
        if (current?.left == null) {
            return current?.right
        }
        current.left = removeMin(current.left)
        return fixRemoveBalance(current)
    }

    private fun fixRemoveBalance(current: Node<V>): Node<V>? {
        current.height = Math.max(getHeight(current.left), getHeight(current.right)) + 1
        if (getHeight(current.right) - getHeight(current.left) == 2) {
            if (getHeight(current.right?.left) > getHeight(current.right?.right)) {
                current.right = current.right?.let {
                    rightRotate(it)
                }
            }
            return leftRotate(current)
        }
        if (getHeight(current.left) - getHeight(current.right) == 2) {
            if (getHeight(current.left?.right) > getHeight(current.left?.left)) {
                current.left = current.left?.let {
                    leftRotate(it)
                }
            }
            return rightRotate(current)
        }
        return current
    }

    private fun traverseTreeAndCheckBalanced(curr: Node<V>?): Int {
        if (curr == null) {
            return 0
        }

        val leftHeight = traverseTreeAndCheckBalanced(curr.left)
        val rightHeight = traverseTreeAndCheckBalanced(curr.right)
        if (Math.abs(leftHeight - rightHeight) > 1) {
            throw UnbalancedTreeException.create(
                "The heights of the two child subtrees of any node must be differ by at most one",
                leftHeight, rightHeight, curr.toString()
            )
        }
        return Math.max(leftHeight, rightHeight) + 1
    }

    private class Node<V: Comparable<V>>(val data: V) {

        var right: Node<V>? = null
        var left: Node<V>? = null
        var height: Int = 0

    }
}