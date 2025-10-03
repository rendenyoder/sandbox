package stacks

/**
 * You are given an array of integer temperatures where each temperature represents the daily temperatures on a day.
 *
 * Return an array result where each result is the number of days after a given day before a warmer temperature appears
 * on a future day.
 *
 * If there is no day in the future where a warmer temperature appears for a day, set the result for that day to zeo.
 */
class DailyTemperatures {
    /**
     * This solution returns the number of days until a warmer day appears for each day with a time and space
     * complexity of O(n).
     *
     * A stack of integers, meant to contain previous indices, and an integer array, meant to contain the result for
     * each day, are initialized.
     *
     * For each temperature, the stack is checked until it is empty or the top corresponds to a warmer temperature. For
     * each value of the stack that is cooler than the current temperature, the top is removed and the popped value
     * is used to set the number of days since a warmer temperature was encountered. At the end of each iteration, the
     * current index will be pushed to the stack.
     *
     * Finally, the resulting array of days will be returned as the solution.
     */
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val stack = ArrayDeque<Int>()
        val result = IntArray(temperatures.size) { 0 }

        for ((i, t) in temperatures.withIndex()) {
            while (stack.isNotEmpty() && t > temperatures[stack.last()]) {
                val stackIndex = stack.removeLast()

                result[stackIndex] = i - stackIndex
            }

            stack.add(i)
        }

        return result
    }
}
