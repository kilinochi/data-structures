package org.kilinochi.ds.set.sorted.exception

class UnbalancedTreeException(override val message: String): RuntimeException(message) {

    companion object {

        fun create(message: String,
                   leftHeight: Int,
                   rightHeight: Int,
                   nodeInfo: String): UnbalancedTreeException {

            return UnbalancedTreeException(
                message + "\n"
                        + "leftHeight = " + leftHeight + ","
                        + "rightHeight = " + rightHeight + "\n"
                        + "nodeInfo = " + nodeInfo
            )
        }
    }
}
