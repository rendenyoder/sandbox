package linkedlist

/**
 * You are given the head of a linked list and a positive integer "k".
 *
 * You must reverse the first "k" nodes in the linked list, the next "k" nodes, and so on. If there are fewer than "k"
 * nodes left, leave the nodes as they are.
 *
 * Return the modified list after reversing the nodes in each group of k.
 *
 * Constraints:
 *
 * - `1 <= k <= n <= 100`
 * - `0 <= Node.val <= 100`
 */
class ReverseNodesInKGroup {

    class ListNode(var value: Int, var next: ListNode? = null)

    /**
     * This solution returns the head of a linked list after reversing nodes in groups of size [k] from the beginning
     * of the list with a time complexity of O(1) and a constant space complexity.
     *
     * First, if the head is null or [k] is of size one, the head of the linked list is returned. Otherwise, a sentinel
     * node is initialized to build upon and a tail node is declared to track the end of each node group.
     *
     * While the end of the linked list has not been reached, the kth node, starting from the previous node group
     * tail, is determined by walking the node forward. If the kth node is null prior to completing [k] iterations,
     * the sentinel node's next node is returned as the group-reversed linked list as there were not [k] nodes left.
     *
     * Next, the starting and ending nodes of the group that is to be reversed are determined using the previous group
     * tail and the kth node. Afterward, two nodes are declared to track the previous and current node for a typical
     * linked list reversal. It should be noted that the previous node is initially set to the ending node so that the
     * original group head is assigned to the next group's head.
     *
     * After the reversal of the group, the group-tail node is pointed to the kth node and is reset to the head of the
     * group prior to reversal (i.e., the tail of the reversed node).
     *
     * This continues until the loop exists when progressing the kth node and [k] nodes are not left.
     */
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (head == null || k == 1) return head

        val dummy = ListNode(-1)
        dummy.next = head

        var tail: ListNode? = dummy
        while (true) {
            var kth: ListNode? = tail
            for (i in 0 until k) {
                kth = kth?.next

                if (kth == null) return dummy.next
            }

            val start = tail?.next
            val end = kth?.next

            var prev: ListNode? = end
            var curr: ListNode? = start
            while (curr != end) {
                val next = curr?.next
                curr?.next = prev

                prev = curr
                curr = next
            }

            tail?.next = kth
            tail = start
        }
    }
}
