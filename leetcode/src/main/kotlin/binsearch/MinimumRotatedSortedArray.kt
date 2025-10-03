package binsearch

/**
 * Suppose an array of length "n", sorted in ascending order, is rotated between 1 and "n" times.
 *
 * For example, the array [0,1,2,4,5,6,7] might become:
 * - [4,5,6,7,0,1,2] if it was rotated 4 times.
 * - [0,1,2,4,5,6,7] if it was rotated 7 times.
 *
 * Notice that rotating the array 4 times moves the last four elements of the array to the beginning. Rotating the
 * array 7 times produces the original array.
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array. The solution
 * must run in O(log n) time.
 *
 * Constraints:
 * - `n == nums.length`
 * - `1 <= n <= 5000`
 * - `-5000 <= nums[i] <= 5000`
 */
class MinimumRotatedSortedArray {
    /**
     * This solution returns the minimum value of a rotated, sorted array with a time complexity of O(log n) and a
     * constant space complexity.
     *
     * The general approach is to use a binary search to find the transition point between the rotated portion and
     * the original start of the array.
     *
     * First, left, right, and middle pointers are initialized. Given the rotation moves values from the end of the
     * array to the beginning, if the middle pointer value is greater than the right, the minimum value appears to
     * the right of the middle pointer. Otherwise, the minimum value is either the value at the middle pointer or
     * to the left of the middle pointer.
     *
     * Finally, the value at the left pointer, or lower bound, is returned as the minimum value.
     */
    fun findMin(nums: IntArray): Int {
        var p1 = 0
        var p2 = nums.size - 1

        while (p1 < p2) {
            val index = p1 + (p2 - p1) / 2

            when {
                nums[index] > nums[p2] -> p1 = index + 1
                else -> p2 = index
            }
        }

        return nums[p1]
    }
}
