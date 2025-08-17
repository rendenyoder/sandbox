package binsearch

/**
 * You are given two integer arrays of size "m" and "n" respectively, where each is sorted in ascending order.
 *
 * Return the median value among all elements of both arrays. The solution must run with in O(log m + n) time.
 *
 * Constraints:
 *
 * - `nums1.length == m`
 * - `nums2.length == n`
 * - `0 <= m <= 1000`
 * - `0 <= n <= 1000`
 * - `-10⁶ <= nums1[i], nums2[i] <= 10⁶`
 */
class MedianOfTwoSortedArrays {
    /**
     * This solution returns the median value between [nums1] and [nums2] with a time complexity of O(log min(m, n))
     * and a constant space complexity.
     *
     * Given both arrays are sorted, the median can be computed by selecting a subset of values from both arrays, such
     * that the total number of selected elements is half the size of both arrays combined. From both of these subsets,
     * the last value and the last value + 1, if present, can help us determine which values to consider for computing
     * the median value.
     *
     * The general approach follows a binary search algorithm. First, left, right, and middle pointers are initialized.
     * It should be noted the right pointer is not the typical one less than the size of the search array. This is due
     * to the search being performed on the values between the middle pointer since values on both sides of the middle
     * pointer need to be considered (e.g., a search array of [0, 1] yields [[-inf, 0], [0, 1], [1, inf]]).
     *
     * While the pointers have not met, the values to the left and right of the middle pointer, for array "a", are
     * determined. If either value is out of bounds, a [Int.MIN_VALUE] or [Int.MAX_VALUE] is assigned, respectively.
     *
     * For array "b", the number of elements that can be selected from it given the elements selected from array "a"
     * for the given iteration is determined (e.g., a half of 6 with a middle pointer of 3 means we can only select
     * 3 elements from "b"). The number of elements serves as the middle index for array "b", of which the left and
     * right values are determined.
     *
     * If the leftmost value from "a" is greater than the rightmost value from "b", we know the middle pointer for "a"
     * is too large as a value from the left partition cannot be greater than a value from the right. In this case,
     * the right pointer is progressed to search left.
     *
     * Similarly, if the leftmost value from "b" is greater than the rightmost value from "a", we progress the left
     * pointer as values from the left partition cannot be greater than values from the right.
     *
     * If neither of the above conditions is true, the subsets from both arrays are valid. If the size of the combined
     * arrays is odd, the maximum of the left values, from both "a" and "b", is the median value and is returned.
     *
     * Otherwise, the mean is computed between the left and right values, from both "a" and "b", and is returned as
     * the median value.
     *
     * A base case of -1 is returned otherwise, mostly to make the compiler happy is this is outside the constraints
     * specified by the problem.
     */
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val (a, b) = if (nums1.size <= nums2.size) nums1 to nums2 else nums2 to nums1

        var p1 = 0
        var p2 = a.size

        val size = nums1.size + nums2.size
        val half = (size + 1) / 2

        while (p1 <= p2) {
            val mid = p1 + (p2 - p1) / 2

            val aLeft = a.getOrNull(mid - 1) ?: Int.MIN_VALUE
            val aRight = a.getOrNull(mid) ?: Int.MAX_VALUE

            val bLeft = b.getOrNull(half - mid - 1) ?: Int.MIN_VALUE
            val bRight = b.getOrNull(half - mid) ?: Int.MAX_VALUE

            when {
                aLeft > bRight -> p2 = mid - 1
                bLeft > aRight -> p1 = mid + 1

                else -> return when {
                    size % 2 == 0 -> (maxOf(aLeft, bLeft) + minOf(aRight, bRight)) / 2.0
                    else -> maxOf(aLeft, bLeft).toDouble()
                }
            }
        }

        return -1.0
    }
}
