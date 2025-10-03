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
 * Given the rotated sorted array and a target, return the index of the target, or -1 if it is not present. You may
 * assume all elements in the sorted rotated array are unique. The solution must run in O(log n) time.
 *
 * Constraints:
 * - `1 <= nums.length <= 5000`
 * - `-10000 <= nums[i] <= 10000`
 * - `-10000 <= target <= 10000`
 */
class SearchRotatedSortedArray {
    /**
     * This solution returns the index of the [target] integer with a time complexity of O(log n) and a constant
     * space complexity.
     *
     * The general approach follows a binary search algorithm. Left, right, and middle pointers are initialized. While
     * the two pointers have not met, if the middle value is equal to the [target], the middle index is returned.
     *
     * Otherwise, the middle value is checked to see if it is greater than or equal to the leftmost value. If this is
     * the case, the middle pointer lies within the left segment, or the rotated section, of the [nums] array. If our
     * target value is greater than the middle value or smaller than the leftmost value, the target does not appear in
     * the left segment. As such, the right segment is searched, otherwise the left.
     *
     * If the middle pointer is smaller than the leftmost value, the middle pointer lies within the right segment. If
     * the target value is smaller than the middle value or greater than the rightmost value, the target does not
     * appear in the right segment. As such, the left segment is searched, otherwise the right.
     *
     * If the pointers meet before finding an index, the target does not exist in the array and -1 is returned.
     */
    fun search(nums: IntArray, target: Int): Int {
        var p1 = 0
        var p2 = nums.size - 1

        while (p1 <= p2) {
            val mid = p1 + (p2 - p1) / 2

            if (nums[mid] == target) return mid

            when {
                nums[p1] <= nums[mid] -> when {
                    target > nums[mid] || target < nums[p1] -> p1 = mid + 1
                    else -> p2 = mid - 1
                }
                else -> when {
                    target < nums[mid] || target > nums[p2] -> p2 = mid - 1
                    else -> p1 = mid + 1
                }
            }
        }

        return -1
    }
}
