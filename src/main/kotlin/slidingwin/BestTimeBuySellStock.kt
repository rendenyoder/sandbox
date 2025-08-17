package slidingwin

/**
 * You are given an array of prices where each element is the price of a given stock on that day.
 *
 * You may choose a single day to buy and a different day in the future to sell.
 *
 * Return the maximum profit you can achieve. You may choose to not make any transactions, in which case the
 * profit would be 0.
 *
 * Constraints:
 * - `0 <= prices[i] <= 104`
 * - `1 <= prices.length <= 105`
 */
class BestTimeBuySellStock {
    /**
     * This solution returns the maximum profit that can be obtained with a time complexity of O(n) and a constant
     * space complexity.
     *
     * The solution follows a two-pointer, sliding window algorithm. In this case, the right pointer is always
     * progressed on each iteration, and the search ends when the right pointer meets the end of the array. As such,
     * a for loop can be used for the right pointer.
     *
     * For each iteration, the difference between the right and left pointer values is determined, as profit. If the
     * value is larger than the running max profit, it replaces it. If the right pointer value is smaller than the left
     * pointer value, the left pointer progresses to the position of the right pointer. In this way, the calculated
     * profit for each iteration uses the smallest buying price that came before it, maximizing the possible profit.
     *
     * After all prices have been considered, the running maximum profit is returned.
     */
    fun maxProfit(prices: IntArray): Int {
        var p1 = 0
        var max = 0

        for (p2 in 1 until prices.size) {
            max = maxOf(max, prices[p2] - prices[p1])

            if (prices[p2] < prices[p1]) p1 = p2
        }

        return max
    }
}
