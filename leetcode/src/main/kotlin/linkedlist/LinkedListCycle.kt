package linkedlist

/**
 * Given the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
 * following the next pointer.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 * Constraints:
 *
 * - `0 <= nodes <= 10⁴`
 * - `-105 <= Node.val <= 105`
 */
class LinkedListCycle {

    class ListNode(val value: Int, var next: ListNode? = null)

    /**
     * This solution returns whether the linked list, given the [head] node, contains a cycle with a time complexity
     * of O(n) and constant space complexity.
     *
     * The approach follows "Floyd's Tortoise and Hare" algorithm. First, slow and fast pointers are initialized at
     * the head of the linked list. While the fast pointer is not null and has a next node, the slow pointer moves to
     * its next node while the fast pointer moves to its next node's next node. This ensures, if there is a cycle, the
     * fast pointer gains on the slow pointer by one position for every iteration thereby guaranteeing they will meet.
     *
     * If, for any iteration, the slow and fast pointers are equal to one another, a cycle exists and true is returned.
     *
     * Otherwise, if the loop has exited, false is returned (i.e., a cycle cannot exist if the loop exits on its own).
     *
     * It should be noted that an intuitive solution exists with a space complexity of O(n) where a set is used to
     * determine if a node is encountered more than once, confirming that a cycle exists.
     *
     * ```kotlin
     * fun hasCycle(head: ListNode?): Boolean {
     *     var node = head
     *     val hashset = hashSetOf<ListNode>()
     *
     *     while (node != null) {
     *         if (node in hashset) return true
     *
     *         hashset.add(node)
     *         node = node.next
     *     }
     *
     *     return false
     * }
     * ```
     */
    fun hasCycle(head: ListNode?): Boolean {
        var slow = head
        var fast = head

        while (fast != null && fast.next != null) {
            slow = slow?.next
            fast = fast.next?.next

            if (slow == fast) return true
        }

        return false
    }
}
