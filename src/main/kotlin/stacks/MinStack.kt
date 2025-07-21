package stacks

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * You must implement a solution with O(1) time complexity for each function.
 *
 * Constraints:
 * - `-231 <= val <= 231 - 1`
 * - Methods pop, top and getMin operations will always be called on non-empty stacks
 * - At most `3 * 10⁴` calls will be made to push, pop, top, and getMin
 */
class MinStack {

    private val stack = ArrayDeque<Int>()
    private val minimum = ArrayDeque<Int>()

    /**
     * Pushes the element [value] onto the stack.
     *
     * When a value is added, it is added to the primary [stack] and conditionally added to the secondary [minimum]
     * stack if it is less than or equal to than the current top of the [minimum] stack.
     */
    fun push(value: Int) {
        stack.add(value)

        if (minimum.isEmpty() || value <= minimum.last()) {
            minimum.add(value)
        }
    }

    /**
     * Removes the element on the top of the stack.
     *
     * When a value is removed, the top of the [minimum] is compared to the removed element. If they are equal,
     * the top of the minimum stack is removed as that minimum value is no longer in the primary [stack].
     */
    fun pop() {
        val removed = stack.removeLast()

        if (removed == minimum.last()) {
            minimum.removeLast()
        }
    }

    /**
     * Returns the top element from the primary [stack].
     */
    fun top(): Int {
        return stack.last()
    }

    /**
     * Retrieves the minimum element from the secondary [minimum] stack.
     */
    fun getMin(): Int {
        return minimum.last()
    }
}
