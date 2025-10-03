package twopointers

/**
 * Given an array of integers sorted in non-decreasing order, return the indices (1-indexed) of the two numbers that
 * add up to a given target number where the first index is less than the second index.
 *
 * Note that the indices cannot be equal, therefore you may not use the same element twice.
 *
 * There will always be exactly one valid solution.
 *
 * Constraints:
 * - Your solution must use only constant extra space.
 */
class TwoSumII {
    /**
     * This solution finds the two, 1-indexed, indices of the two numbers that add to the [target] number with a time
     * and space complexity of O(n) and O(1), respectively.
     *
     * First, two pointers are initialized for the start and end of the array. If the sum of the two numbers at the
     * two indices is less than the target, the first pointer is incremented. However, if the sum is greater than
     * the target value, the second pointer is decremented.
     *
     * If that sum equals the target value, the indices are returned incremented by 1.
     */
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var p1 = 0
        var p2 = numbers.size - 1

        while (p1 < p2) {
            val sum = numbers[p1] + numbers[p2]

            when {
                sum < target -> p1++
                sum > target -> p2--
                else -> return intArrayOf(p1 + 1, p2 + 1)
            }
        }

        return intArrayOf()
    }
}
