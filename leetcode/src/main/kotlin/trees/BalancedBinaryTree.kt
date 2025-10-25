package trees

import kotlin.math.abs

/**
 * Given a binary tree, return true if it is height-balanced and false otherwise.
 *
 * A height-balanced binary tree is defined as a binary tree in which the left and right subtrees of every node differ
 * in height by no more than 1.
 *
 * Constraints:
 *
 * - `-1000 <= Node.val <= 1000`
 * - The number of nodes in the tree is in the range [0, 1000].
 */
class BalancedBinaryTree {

    class TreeNode(var value: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * This solution returns whether a binary tree is height-balanced given a [root] node with a time complexity of
     * O(n) and a space complexity of O(h), where "h" is the height of the tree.
     *
     * This solution follows a depth-first search approach. A local recursive function is declared to perform the
     * depth-first search.
     *
     * Within the local function, first, the node is checked to see if it is null. If it is null, the function returns
     * a value of zero. Otherwise, the left and right subtrees are recursively searched for heights. If the difference
     * between the two heights is greater than one, the function returns a negative value. If either subtree returns a
     * negative value, the function also returns a negative value. Otherwise, the function returns the maximum of the
     * left and right subtrees, plus one to represent the height added by the current node.
     *
     * Finally, the outer function calls the local function and returns whether the binary tree is height-balanced
     * based on whether the local function returns a non-negative value.
     */
    fun isBalanced(root: TreeNode?): Boolean {
        fun dfs(node: TreeNode?): Int {
            if (node == null) return 0

            val left = dfs(node.left)
            if (left < 0) return -1

            val right = dfs(node.right)
            if (right < 0) return -1

            if (abs(left - right) > 1) return -1
            return maxOf(left, right) + 1
        }

        return dfs(root) >= 0
    }
}
