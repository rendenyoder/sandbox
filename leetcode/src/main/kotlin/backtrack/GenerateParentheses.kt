package backtrack

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * Constraints:
 * - `1 <= n <= 7`
 */
class GenerateParentheses {
    /**
     * This solution generates all valid parentheses combinations with a time complexity O(4ⁿ÷√n) and a space
     * complexity O(n).
     *
     * A parentheses list is initialized, and a backtracking function is declared that tracks the current parentheses
     * expression and the count of both the open and closed parentheses.
     *
     * The base-case of one open parenthesis is called to start the recursive generation. For each recursive call where
     * the count of open and closed parentheses is less than [n], up to two additional calls are made for generating
     * a group with one additional parenthesis of a given type. Once a recursive call has [n] number of closed
     * and open parentheses, the string is added to the list of well-formed parentheses.
     *
     * After all calls complete, the list of all parentheses is returned.
     */
    fun generateParenthesis(n: Int): List<String> {
        val parentheses = mutableListOf<String>()

        fun backtrack(str: String, open: Int = 0, closed: Int = 0) {
            if (open == n && closed == n) {
                parentheses.add(str)
                return
            }

            if (open < n) backtrack(str = "$str(", open = open + 1, closed = closed)
            if (open > closed) backtrack(str = "$str)", open = open, closed = closed + 1)
        }

        backtrack(str = "(", open = 1, closed = 0)
        return parentheses
    }
}
