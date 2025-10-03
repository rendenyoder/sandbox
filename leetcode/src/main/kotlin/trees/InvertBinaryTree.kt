package trees

/**
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 * Constraints:
 *
 * - `0 <= Nodes <= 100`
 * - `-100 <= Node.val <= 100`
 */
class InvertBinaryTree {

    class TreeNode(var value: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * This solution returns the root node of an inverted binary tree given the [root] node of a binary tree with a
     * time and space complexity of O(n).
     *
     * The algorithm uses a depth-first search approach. First, the given root node is checked to see if it is null,
     * and if so, null is immediately returned. Otherwise, the left and right nodes of the root are stored in temporary
     * variables; after which they are swapped. The left and right subtrees are then recursively inverted by calling
     * this method with the left and right nodes.
     *
     * Finally, after all recursive calls are complete, the root node is returned as the root of the inverted tree.
     */
    fun invertTree(root: TreeNode?): TreeNode? {
        if (root == null) return null

        val left = root.left
        val right = root.right

        root.left = right
        root.right = left

        invertTree(left)
        invertTree(right)

        return root
    }
}
