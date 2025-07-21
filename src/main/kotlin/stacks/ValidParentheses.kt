package stacks

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input is valid.
 *
 * An input string is valid if:
 * - Open brackets must be closed by the same type of brackets.
 * - Open brackets must be closed in the correct order.
 * - Every close bracket has a corresponding open bracket of the same type.
 *
 * Constraints:
 * - `1 <= s.length <= 104`
 * - `s` consists of parentheses only '()[]{}'.
 */
class ValidParentheses {
    /**
     * This solution determines whether a string contains valid opening and closing characters with a time and space
     * complexity of O(n).
     *
     * The characters are iterated over and added to a stack. For each iteration, if a closing character is
     * encountered, the stack is popped to determine if the appropriate opening character was the last character
     * added to the stack. If this is not the case, false is returned immediately.
     *
     * If the stack is empty after iterating over all members, a value of true is returned. Otherwise, false is
     * returned as not all opening characters were closed.
     */
    fun isValid(s: String): Boolean {
        val stack = ArrayDeque<Char>()
        val closing = mapOf('}' to '{', ']' to '[', ')' to '(')

        for (char in s) {
            when {
                char !in closing -> stack.add(char)
                stack.isEmpty() || stack.removeLast() != closing[char] -> return false
            }
        }

        return stack.isEmpty()
    }
}
