package linkedlist

/**
 * Design a data structure that follows the constraints of a "Least Recently Used" (LRU) cache.
 *
 * Each method must run in O(1) average time complexity.
 *
 * Constraints:
 *
 * - `0 <= key <= 104`
 * - `0 <= value <= 105`
 * - `1 <= capacity <= 3000`
 */
class LRUCache(private val capacity: Int) {
    private var head = Node(-1, -1)
    private var tail = Node(-1, -1)
    private val cache = hashMapOf<Int, Node>()
    private data class Node(val key: Int, var value: Int, var prev: Node? = null, var next: Node? = null)

    init {
        head.next = tail
        tail.prev = head
    }

    /**
     * Returns the value for the [key] with a constant time complexity.
     *
     * A double-linked list is being used in combination with a hashmap for constant lookups. First, the node at the
     * given [key] is retrieved. If not null, the node is removed from its position in the linked list and re-linked
     * at the start of the list to correspond with it being the most recently used node.
     *
     * Otherwise, if the node at the given [key] is null, -1 is returned.
     */
    fun get(key: Int): Int {
        val node = cache[key] ?: return -1

        unlink(node)
        link(node)

        return node.value
    }

    /**
     * Sets or updates the [value] in the cache for the given [key] with a constant time complexity.
     *
     * A double-linked list is being used in combination with a hashmap for constant lookups. As such, the cache is
     * first checked to see if there is a value for the [key]. If there is, the value for that [key] is removed.
     *
     * Afterward, the cache size is checked to see if it exceeds the capacity, and if so, the last value is evicted.
     *
     * Finally, a newly created node will be inserted for the [key] and [value].
     */
    fun put(key: Int, value: Int) {
        val node = cache[key]
        if (node != null) remove(node)

        val size = cache.size
        if (capacity <= size) evict()

        insert(Node(key, value))
    }

    /**
     * A supporting operation to remove the least used node (i.e., the preceding node of the [tail]).
     */
    private fun evict() {
        remove(tail.prev!!)
    }

    /**
     * A supporting operation to link the [node] and add it to the [cache].
     */
    private fun insert(node: Node) {
        link(node)
        cache[node.key] = node
    }

    /**
     * A supporting operation to unlink the [node] and remove it from the [cache].
     */
    private fun remove(node: Node) {
        unlink(node)
        cache.remove(node.key)
    }

    /**
     * A supporting operation to link a given [node] to the [head] of the linked list.
     *
     * The node's previous node is set to the head, the node's next node is set to the head's next node, and the head's
     * next node and next previous node are set to the node.
     */
    private fun link(node: Node) {
        node.prev = head
        node.next = head.next

        head.next?.prev = node
        head.next = node
    }

    /**
     * A supporting operation to unlink a given [node] from the linked list.
     *
     * The node's next previous node is set to the node's previous node, and the node's previous next node is set to
     * the node's next node.
     */
    private fun unlink(node: Node) {
        node.next?.prev = node.prev
        node.prev?.next = node.next
    }
}
