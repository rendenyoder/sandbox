package hashing

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to
 * the target value.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 */
class TwoSum {
    /**
     * This solution retains a map of the difference between the [target] value a given value within [nums].
     *
     * The [nums] array is iterated over and the difference between the target and each number is added to map
     * of differences. If the current number exists in the difference map upon each iteration, it implies that we
     * previously encountered a number that, if added to the current number, would be equal to the target number.
     *
     * In this case, we can return the previously stored index and the current index. Otherwise, we return an empty
     * array as no indices exist that meet the requirements (however, the problem states that the solution need not
     * worry about this).
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val differences = mutableMapOf<Int, Int>()

        nums.forEachIndexed { index, num ->
            val previous = differences[num]

            if (previous != null) {
                return intArrayOf(previous, index)
            }

            differences[target - num] = index
        }

        return intArrayOf()
    }
}
