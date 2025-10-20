package trees

/**
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the
 * farthest leaf node.
 *
 * Constraints:
 *
 * - `-100 <= Node.val <= 100`
 * - The number of nodes in the tree is in the range [0, 104].
 */
class MaximumBinaryTreeDepth {

    class TreeNode(var value: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * This solution returns the maximum depth of a binary tree given the [root] node of the tree with a time and
     * space complexity of O(n).
     *
     * The solution follows a depth-first search approach. First, if the root node is null, a value of zero is returned.
     *
     * Otherwise, the left and right nodes are traversed via a recursive call. The maximum value between each left and
     * right node, incremented by one, is returned at each level and aggregate to return the maximum depth of the tree.
     */
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0

        val left = maxDepth(root.left)
        val right = maxDepth(root.right)

        return maxOf(left, right) + 1
    }
}
