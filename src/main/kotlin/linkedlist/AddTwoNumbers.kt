package linkedlist

/**
 * You are given two non-empty linked lists, where each represents a non-negative integer.
 *
 * The digits are stored in reverse order. For example, the number "123" is represented as `3 → 2 → 1`.
 *
 * Each of the nodes contains a single digit. You may assume the two numbers do not contain any leading zero, except
 * the number 0 itself.
 *
 * Return the sum of the two numbers as a linked list.
 *
 * Constraints:
 *
 * - `0 <= Node.val <= 9`
 * - `1 <= l1.length, l2.length <= 100.`
 */
class AddTwoNumbers {

    class ListNode(val value: Int, var next: ListNode? = null)

    /**
     * This solution returns the sum of [l1] and [l2] as a reverse order linked list with a time complexity of O(n)
     * and a constant space complexity.
     *
     * The solution generally follows a two-pointer technique; one pointer from each list. First, a carry flag and a
     * sentinel node are initialized, the latter serving as the initial node to build on. Next, pointers from each list
     * are initialized along with a "power of ten" pointer, initially starting at the sentinel node.
     *
     * While either the [l1] pointer is not null, the [l2] pointer is not null, or the carry flag is true, a variable
     * is declared to track the current sum. The sum begins at 1 if the carry flag has been set to true from a previous
     * operation, otherwise 0. The values from the [l1] and [l2] pointers are added to the sum, if not null. The value
     * of the sum modulo 10 is then used to create and assign the current "power of ten" pointer's next node. Next,
     * the [l1], [l2], and "power of ten" pointers are progressed and the carry flag is set depending on whether the
     * current sum is greater than or equal to 10.
     *
     * After all iterations, the sentinel node's next value is returned as the head of the list representing the sum
     * of the two lists, [l1] and [l2].
     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var carry = false
        val dummy = ListNode(-1)

        var n1: ListNode? = l1
        var n2: ListNode? = l2
        var pow: ListNode? = dummy

        while (n1 != null || n2 != null || carry) {
            var sum = if (carry) 1 else 0
            sum += n1?.value ?: 0
            sum += n2?.value ?: 0

            pow?.next = ListNode(sum % 10)

            n1 = n1?.next
            n2 = n2?.next
            pow = pow?.next
            carry = sum >= 10
        }

        return dummy.next
    }
}
