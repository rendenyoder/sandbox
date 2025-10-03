package binsearch

/**
 * You are given an m x n integer matrix with the following two properties:
 * - Each row is sorted in non-decreasing order.
 * - The first integer of each row is greater than the last integer of the previous row.
 *
 * Given an integer target, return true if the target is in the matrix or false otherwise.
 *
 * You must write a solution in O(log m⋅n) time complexity.
 *
 * Constraints:
 * - `m == matrix.length`
 * - `n == matrix[i].length`
 * - `1 <= m, n <= 100`
 * - `-104 <= matrix[i][j], target <= 104`
 */
class Search2DMatrix {
    /**
     * This solution returns whether the target value exists in the 2D matrix with a time complexity of O(log m⋅n) and
     * constant space complexity.
     *
     * The general approach of the algorithm is a two-phase binary search. First, a row index, start and end pointer
     * are initialized. A binary search is performed on each row, using the last value of a row to determine whether
     * the left pointer should be moved and the first value, the right. If the target value happens to be between the
     * values, the loop exists as the index of the row has been determined.
     *
     * Next, a classic binary search algorithm is run on the row possibly containing the target value. If the value
     * is found, true is returned, otherwise false.
     */
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val m = matrix.size - 1
        val n = matrix[0].size - 1

        var i = 0
        var p1 = 0
        var p2 = m

        while (p1 <= p2) {
            i = p1 + (p2 - p1) / 2

            when {
                target > matrix[i][n] -> p1 = i + 1
                target < matrix[i][0] -> p2 = i - 1
                else -> break
            }
        }

        if (p1 > p2) return false

        p1 = 0
        p2 = n

        while (p1 <= p2) {
            val j = p1 + (p2 - p1) / 2

            when {
                matrix[i][j] < target -> p1 = j + 1
                matrix[i][j] > target -> p2 = j - 1
                else -> return true
            }
        }

        return false
    }
}
