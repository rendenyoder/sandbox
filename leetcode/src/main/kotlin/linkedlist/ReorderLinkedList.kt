package linkedlist

/**
 * You are given the head of a singly linked list.
 *
 * The positions of a linked list with a length of 7, for example, must be reordered in the following manner:
 *
 * - Input:  `[0, 1, 2, 3, 4, 5, 6]`
 * - Output: `[0, 6, 1, 5, 2, 4, 3]`
 *
 * Notice that in the general case for a list of length "n", the nodes are as `[0, n-1, 1, n-2, 2, n-3, ...]`.
 *
 * You may not modify the values in the list's nodes, but instead you must reorder the nodes themselves.
 *
 * Constraints:
 *
 * - `1 <= Node.val <= 1000`
 * - `1 <= Length of the list <= 1000`
 */
class ReorderLinkedList {

    class ListNode(val value: Int, var next: ListNode? = null)

    /**
     * This solution reorders a singly linked list, whereby nodes are ordered as `[0, n-1, 1, n-2, 2, n-3, ...]`,
     * with a time complexity of O(n) and a constant space complexity.
     *
     * If the provided [head] is null or has no next value, the function returns early. Otherwise, the solution
     * follows three main steps.
     *
     * First, the middle node is determined using fast and slow nodes. While the fast node is not null and has a next
     * value, it is progressed forward two nodes while the slow pointer progresses one. When the loop exits, the slow
     * node represents the middle node of the linked list.
     *
     * Second, the nodes from the middle node onward are reversed. Previous and current nodes are initialized and,
     * while the current node is not null, the current node is updated to point to the previous node. The previous
     * node is then set the current, and the current to the original value the current node pointed to.
     *
     * Finally, the first segment of the list and the latter reversed segment of the list merge. Head and tail nodes
     * are initialized, representing the start and end of the original list. While the head value is not null, the
     * head node is updated to point to the tail and the tail to the head's original next node. The head and tail are
     * then updated to be the original value they individually pointed to.
     *
     * No value is returned as the algorithm reorders the linked list inplace.
     */
    fun reorderList(head: ListNode?) {
        if (head == null || head.next == null) return

        var fast: ListNode? = head
        var slow: ListNode? = head
        while (fast != null && fast.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        var prev: ListNode? = null
        var curr: ListNode? = slow
        while (curr != null) {
            val next = curr.next
            curr.next = prev

            prev = curr
            curr = next
        }

        var head: ListNode? = head
        var tail: ListNode? = prev
        while (head != null) {
            val headNext = head.next
            val tailNext = tail?.next

            head.next = tail
            tail?.next = headNext

            head = headNext
            tail = tailNext
        }
    }
}
