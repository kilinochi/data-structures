package org.kilinochi.ds.set.sorted

import java.util.Comparator

class AVLTree<V : Comparable<V>>: KSelfBalancingSortedTreeSet<V> {

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
        TODO("Not yet implemented")
    }

    override fun last(): V {
        TODO("Not yet implemented")
    }

    override fun remove(value: V): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(value: V): Boolean {
        TODO("Not yet implemented")
    }

    override fun checkBalance() {
        TODO("Not yet implemented")
    }

    override fun size(): Int {
       return length
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun clear() {
        this.root = null
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
        if(balanceFactor > 1 && comparator.compare(data, current.left!!.data) < 0){
            return rightRotate(current);
        }
        //RR Case
        if(balanceFactor < -1 && comparator.compare(data, current.right!!.data) > 0){
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

    private class Node<V : Comparable<V>>(val data: V) {

        var right: Node<V>? = null
        var left: Node<V>? = null
        var height: Int = 0

    }
}