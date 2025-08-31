package linkedlist

/**
 * Given the head of a singly linked list, reverse the list, and return the new beginning of the list.
 *
 * Constraints:
 * - `-1000 <= Node.val <= 1000`
 * - `0 <= The length of the list <= 1000`
 */
class ReverseLinkedList {

    class ListNode(val value: Int, var next: ListNode? = null)

    /**
     * This solution returns the new head of the reversed, singly linked list with a time complexity of O(n) and a
     * constant space complexity.
     *
     * The algorithm follows an iterative approach involving two node variables for the previous node and the current
     * node that is being reversed. First, the previous node value is set to null and the current node value is set to
     * the initial [head] node.
     *
     * While there exists a current node to reverse, the current node's next node is stored. After this, the current
     * node's next node is reversed by setting it to the previous node. The previous node is assigned to the current
     * node, and the current node to the original next node the current node was pointing to before reassignment.
     *
     * After all nodes have been reversed, the previous node value is returned as the new head of the linked list.
     *
     * It should be noted a recursive solution exists, one that can be optimized at compile time by defining the
     * function as a tail-recursive function. This implementation follows a similar pattern whereby the current
     * head's next node is stored, the head's next node is reassigned to the previous node, and, if there is no next
     * node, the head is returned as the new head of the linked list; otherwise a recursive call is made with the
     * previously stored node as the iteration's head and the current head as the next iteration's previous node.
     *
     * ```kotlin
     * tailrec fun reverseList(head: ListNode?, prev: ListNode? = null): ListNode? {
     *     val next = head?.next
     *     head?.next = prev
     *     return if (next == null) head else reverseList(next, head)
     * }
     * ```
     */
    fun reverseList(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var curr: ListNode? = head

        while (curr != null) {
            val next = curr.next
            curr.next = prev

            prev = curr
            curr = next
        }

        return prev
    }
}
