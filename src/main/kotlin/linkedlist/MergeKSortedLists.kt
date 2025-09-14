package linkedlist

import java.util.PriorityQueue

/**
 * You are given an array of linked-lists lists, each sorted in ascending order.
 *
 * Merge and return all linked-lists into one sorted linked-list.
 */
class MergeKSortedLists {

    class ListNode(var value: Int, var next: ListNode? = null)

    /**
     * This solution returns the ordered [lists] as a merged linked-list with a time complexity of O(n log k) and a
     * space complexity of O(k), where n represents all nodes provided and k is the number of lists.
     *
     * This solution uses a sentinel node to build upon and a heap to maintain the node with the smallest value when
     * considering one node from each linked-list. First, the sentinel node and min-heap are constructed. Next, every
     * non-null node from [lists] is added to the heap. While the heap is not empty, the top of the heap is polled and
     * is added as the next node in the merged linked-list. The tail of the merged linked-list is then progressed, and
     * the top of the heap's next node, if it is not null, is added to the heap. This iteratively adds the currently
     * in-scope node from each of the lists that has the smallest value.
     *
     * Finally, the sentinel node's next node is then returned as the head of the merged linked-list.
     */
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val head = ListNode(-1)
        val heap = PriorityQueue<ListNode>(compareBy { it.value })

        for (node in lists) {
            if (node != null) heap.offer(node)
        }

        var tail: ListNode? = head
        while (heap.isNotEmpty()) {
            val min = heap.poll()

            tail?.next = min
            tail = tail?.next

            if (min?.next != null) {
                heap.offer(min.next)
            }
        }

        return head.next
    }
}
