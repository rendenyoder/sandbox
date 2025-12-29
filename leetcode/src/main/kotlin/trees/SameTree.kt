package trees

/**
 * Given the roots of two binary trees, write a function to check if they are the same or not.
 *
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 *
 * Constraints:
 * - `-10⁴ <= Node.val <= 10⁴`
 * - The number of nodes in both trees is in the range [0, 100].
 */
class SameTree {

    class TreeNode(var value: Int, var left: TreeNode? = null, var right: TreeNode? = null)

    /**
     * The solution returns whether two trees are equal to one another with a time and space complexity of O(n).
     *
     * The solution follows a depth first search algorithm. First, the two nodes [p] and [q] are checked if they both
     * are null and, if so, a value of true. Otherwise, the result of comparing the values of [p] and [q] and
     * recursively comparing left and right subtrees is returned.
     */
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true

        return p?.value == q?.value &&
            isSameTree(p?.left, q?.left) &&
            isSameTree(p?.right, q?.right)
    }
}
