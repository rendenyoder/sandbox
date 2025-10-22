package trees

/**
 * The diameter of a binary tree is defined as the length of the longest path between any two nodes. The path does not
 * necessarily have to pass through the root.
 *
 * The length of a path between two nodes in a binary tree is the number of edges between the nodes. Note that the path
 * cannot include the same node twice.
 *
 * Given the root of a binary tree root, return the diameter of the tree.
 *
 * Constraints:
 *
 * - `-100 <= Node.val <= 100`
 * - The number of nodes in the tree is in the range [1, 104].
 */
class DiameterOfBinaryTree {

    class TreeNode(var value: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * This solution returns the diameter of a binary tree with a time complexity of O(n) and a space complexity
     * of O(h), where "h" is the height of the tree.
     *
     * The solution follows a depth-first search approach. First, a local variable is declared to store the largest
     * diameter encountered. Next, a local recursive function is invoked to return the max depth of each subtree and,
     * within each recursive call, the diameter for the subtree is computed. If the diameter of a given subtree is
     * larger than the outer diameter result, it replaces the outer diameter result.
     *
     * After all recursive calls, the diameter result is returned as the diameter of the binary tree.
     */
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        var result = 0

        fun maxDepth(root: TreeNode?): Int {
            if (root == null) return 0

            val left = maxDepth(root.left)
            val right = maxDepth(root.right)

            result = maxOf(left + right, result)
            return maxOf(left, right) + 1
        }

        maxDepth(root)
        return result
    }
}
