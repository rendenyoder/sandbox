package linkedlist

/**
 * You are given the beginning of a linked list, and an integer "n".
 *
 * Remove the "nth" node from the end of the list and return the beginning of the list.
 *
 * Constraints:
 *
 * - `1 <= n <= nodes`
 * - `1 <= nodes <= 30`
 * - `0 <= Node.val <= 100`
 */
class RemoveFromEndOfLinkedList {

    class ListNode(val value: Int, var next: ListNode? = null)

    /**
     * This solution returns the head of a linked list after removing the "nth" node from the end of the list with a
     * time complexity of O(n) and a constant space complexity.
     *
     * The solution follows a two-pointer technique where the pointers are spaced [n] nodes a part. First, a sentinel
     * node is initialized to serve as the temporary head of the list. Next, the two pointers are initialized, with
     * the left set to the temporary node and the right set to the "true" head node.
     *
     * The right node is then progressed so that it is [n] nodes away from the "true" head node. While the right node
     * is not null (i.e., has not moved past the end of the linked list), both the left and right nodes progress by
     * one position.
     *
     * After the loop has exited, the left node is pointing to the "nth" node from the end due to the distance between
     * the left and right nodes being of size [n] - 1. The left node is then set to point to the node coming after it's
     * next node, thereby removing the target node from the list.
     *
     * The sentinel node's next node is then returned as the head, after having had the target node removed.
     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val temp = ListNode(-1)
        temp.next = head

        var left: ListNode? = temp
        var right: ListNode? = head

        for (i in 0 until n) {
            right = right?.next
        }

        while (right != null) {
            right = right.next
            left = left?.next
        }

        left?.next = left.next?.next
        return temp.next
    }
}
