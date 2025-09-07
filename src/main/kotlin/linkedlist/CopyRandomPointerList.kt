package linkedlist

/**
 * A linked list of length "n" is given such that each node contains an additional random pointer, which could point
 * to any node in the list, or null.
 *
 * Construct a deep copy of the list. The deep copy should consist of exactly "n" new nodes, where each new node has
 * its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes
 * should point to new nodes in the copied list such that the pointers in the original list and copied list represent
 * the same list state. None of the pointers in the new list should point to nodes in the original list.
 *
 * Return the head of the copied linked list.
 *
 * Constrains:
 * - `0 <= n <= 100`
 * - `-100 <= Node.val <= 100`
 */
class CopyRandomPointerList {

    class Node(var value: Int, var next: Node? = null, var random: Node? = null)

    /**
     * This solution returns a copy of a linked list containing additional random pointers given the head [node] of
     * the list with a time and space complexity of O(n).
     *
     * The solution roughly follows a two-pointer technique, using a hashmap to associate original nodes with their
     * copies. First, a sentinel head node and a hashmap, to track copies, are instantiated. Next, pointers for the
     * previous and current node are initialized as the sentinel node and the true head node, respectively. A pointer
     * for the current random node is also declared for clarity and null-safety.
     *
     * While the current node is not null, a copy of the node is either retrieved or assigned and retrieved from the
     * hashmap. The copy is then set as the value the previous node is pointing to, building a new list off the
     * sentinel node. The same is done for the random node if it is not null, with the copied value being set as the
     * random value for the node the previous node is pointing to.
     *
     * After the end of the list has been reached, the node the sentinel node is pointing to is returned as the head
     * of the copied linked list.
     *
     * It should be noted, a solution also exists that uses constant extra space, aside from the copied list, that
     * interleaves copied nodes into the original linked list. However, this solution involves mutating the input
     * linked list and optionally setting the input linked list back to its original state.
     */
    fun copyRandomList(node: Node?): Node? {
        val dummy = Node(-1)
        val copies = hashMapOf<Node?, Node?>()

        var prev: Node? = dummy
        var curr: Node? = node
        var rand: Node? = curr?.random

        while (curr != null) {
            prev?.next = copies.getOrPut(curr) { Node(curr.value) }

            if (rand != null) {
                prev?.next?.random = copies.getOrPut(rand) { Node(rand.value) }
            }

            prev = prev?.next
            curr = curr.next
            rand = curr?.random
        }

        return dummy.next
    }
}
