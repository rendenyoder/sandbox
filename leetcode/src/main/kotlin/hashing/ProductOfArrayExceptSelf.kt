package hashing

/**
 * Given an integer array nums, return an array such that the answer at a given index is equal to the product of all
 * elements within the array except the value at that index.
 *
 * The product of any prefix or suffix of the array is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Constraints:
 *
 * - 2 <= nums.length <= 105
 * - -30 <= nums[i] <= 30
 */
class ProductOfArrayExceptSelf {
    /**
     * The proposed solution uses a two-pass approach in O(n) time complexity and O(1) space complexity.
     *
     * A first pass is performed, iterating left-to-right, whereby each value in the result array is assigned a value
     * equal to the product of the computed prefix (initially set to 1) and the value of [nums] at the current index.
     *
     * A second pass is performed whereby, iterating right-to-left, each value in the result array is assigned
     * a value equal to the product of a postfix (initially set to 1) and the value of the result array at the
     * current index.
     *
     * An alternative approach, which is not as memory efficient, also exists which illustrates the solution better by
     * computing the prefix and suffix arrays. A result array is then computed where each value is the product of the
     * left (prefix) and right (suffix) value at a given index.
     *
     * ```kotlin
     * fun productExceptSelf(nums: IntArray): IntArray {
     *     val prefix = nums.clone()
     *     val suffix = nums.clone()
     *
     *     for (i in 1 until nums.size) {
     *         prefix[i] *= prefix[i - 1]
     *     }
     *
     *     for (i in nums.size - 2 downTo 0) {
     *         suffix[i] *= suffix[i + 1]
     *     }
     *
     *     return IntArray(nums.size) { i ->
     *         val prefix = prefix.getOrElse(i - 1) { 1 }
     *         val suffix = suffix.getOrElse(i + 1) { 1 }
     *
     *         prefix * suffix
     *     }
     * }
     * ```
     */
    fun productExceptSelf(nums: IntArray): IntArray {
        val result = IntArray(nums.size) { 1 }

        var prefix = 1
        for (i in nums.indices) {
            result[i] = prefix
            prefix *= nums[i]
        }

        var postfix = 1
        for (i in nums.size - 1 downTo 0) {
            result[i] *= postfix
            postfix *= nums[i]
        }

        return result
    }
}
