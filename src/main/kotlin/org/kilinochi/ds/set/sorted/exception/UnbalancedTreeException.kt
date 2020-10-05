package org.kilinochi.ds.set.sorted.exception

class UnbalancedTreeException(override val message: String): RuntimeException(message) {

    companion object {

        private const val serialVersionUID: Long = 7869829465216879L

        fun create(
            message: String,
            leftHeight: Int,
            rightHeight: Int,
            nodeInfo: String
        ): UnbalancedTreeException {

            return UnbalancedTreeException(
                message + "\n"
                        + "leftHeight = " + leftHeight + ","
                        + "rightHeight = " + rightHeight + "\n"
                        + "nodeInfo = " + nodeInfo
            )
        }
    }
}
