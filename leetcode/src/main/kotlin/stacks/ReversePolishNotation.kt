package stacks

/**
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 *
 * Return an integer that represents the value of the expression.
 *
 * Note that:
 * - The valid operators are '+', '-', '*', and '/'.
 * - Each operand may be an integer or another expression.
 * - The division between two integers always truncates toward zero.
 * - There will not be any division by zero.
 * - The input represents a valid arithmetic expression in a reverse polish notation.
 * - The answer and all the intermediate calculations can be represented in a 32-bit integer.
 *
 * Constraints:
 * - `1 <= tokens.length <= 104`
 * - Each token is either an operator ("+", "-", "*", "/") or an integer in the range [-200, 200].
 */
class ReversePolishNotation {
  /**
   * This solution evaluates a Reverse Polish Notation expression with a time and space complexity of O(n).
   *
   * Each token is evaluated and, if it is not an operator, is pushed to a stack. When an operator is encountered, the
   * last two values are popped from the stack and evaluated based on the operator. The resulting value is re-added
   * to the stack.
   *
   * Given that the problem guarantees only valid RPN expressions, we can be certain after processing all tokens the
   * last value in the stack represents the value of the expression.
   */
  fun evalRPN(tokens: Array<String>): Int {
    val stack = ArrayDeque<Int>()
    val operators = setOf("+", "-", "*", "/")

    for (token in tokens) {
      when (token) {
        in operators -> {
          val a = stack.removeLast()
          val b = stack.removeLast()

          when (token) {
            "+" -> stack.add(b + a)
            "-" -> stack.add(b - a)
            "*" -> stack.add(b * a)
            "/" -> stack.add(b / a)
          }
        }
        else -> stack.add(token.toInt())
      }
    }

    return stack.last()
  }
}
