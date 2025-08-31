package linkedlist

/**
 * You are given the heads of two sorted linked lists.
 *
 * Merge the two lists into one sorted linked list and return the head of the new sorted linked list.
 *
 * Constraints:
 * - `-100 <= Node.val <= 100`
 * - `0 <= The length of the each list <= 100`
 */
class MergeTwoSortedLists {

    class ListNode(val value: Int, var next: ListNode? = null)

    /**
     * This solution returns the head of a linked list, formed by merging [list1] and [list2], with a time complexity
     * of O(n + m) and a constant space complexity.
     *
     * The algorithm uses a two-pointer technique, one pointer for each list, with a sentinel head node. First, the
     * head node is initialized as a starting point, along with a node variable to track the node to build on.
     *
     * While the two pointers are not null, if the first node's value is greater than the second's, the current node's
     * next value is assigned to the second node and the second node pointer moves to its next value. Otherwise, the
     * current node's next value is assigned to the first, and the first node pointer moves to its next value. After
     * this, the current node's value progresses to the recently assigned next value.
     *
     * Finally, after all iterations are complete, the current node's next value is assigned to any remaining list
     * nodes from either the first or second list.
     *
     * The sentinel head node's next value is then returned as the head of the newly merged list.
     */
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        var l1 = list1
        var l2 = list2

        val head: ListNode? = ListNode(-1)
        var node: ListNode? = head

        while (l1 != null && l2 != null) {
            if (l1.value > l2.value) {
                node?.next = l2
                l2 = l2.next
            } else {
                node?.next = l1
                l1 = l1.next
            }

            node = node?.next
        }

        node?.next = l1 ?: l2
        return head?.next
    }
}
