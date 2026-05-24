package trees

/**
 * Given the roots of two binary trees, return true if there is a subtree within the first with the same structure
 * and node values of the second.
 *
 * A subtree of a binary tree is a tree that consists of a node in a root tree and all of its descendants. A tree
 * could also be considered as a subtree of itself.
 *
 * Constraints:
 * - `-10⁴ <= Node.val <= 10⁴`
 * - The number of nodes in both trees is in the range [1, 100].
 */
class SubtreeOfAnotherTree {

    class TreeNode(var value: Int, var left: TreeNode?, var right: TreeNode?)

    /**
     * This solution returns whether [subRoot] is a subtree of [root] with a time and space complexity of O(n•k),
     * where `n` is the number of nodes in [root] and `k` is the number of nodes in [subRoot], using a depth-first
     * search approach where each node in [root] is compared with [subRoot].
     *
     * First, [subRoot] is checked if it is null. If it is, `true` is returned as a null tree is considered to be
     * a subtree of both a null and non-null tree.
     *
     * Next, [root] is checked if it is null. If it is, `false` is returned as a null root tree cannot contain a
     * subtree of either a null or non-null subroot.
     *
     * Otherwise, whether [root] and [subRoot] are equal or whether [subRoot] is a subtree of either the left
     * or right subtrees of [root] is recursively returned.
     */
    fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (subRoot == null) return true
        if (root == null) return false

        return isSameTree(root, subRoot)
                || isSubtree(root.left, subRoot)
                || isSubtree(root.right, subRoot)
    }

    /**
     * A helper method for determining the equality of two trees following a depth-first search approach.
     *
     * First, both [a] and [b] are checked if they are null. If so, a value of `true` is returned as two null trees
     * are considered equal.
     *
     * Otheriwse, whether the values of [a] and [b] are equal and whether the values of the left and right children
     * are equal is recursively returned.
     */
    fun isSameTree(a: TreeNode?, b: TreeNode?): Boolean {
        if (a == null && b == null) return true

        return a?.value == b?.value
                && isSameTree(a?.left, b?.left)
                && isSameTree(a?.right, b?.right)
    }
}
