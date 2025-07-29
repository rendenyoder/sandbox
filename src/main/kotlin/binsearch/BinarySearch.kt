package binsearch

/**
 * Given an array of integers which is sorted in ascending order, and a target integer, write a function to search
 * for the target in the array.
 *
 * If the target exists, then return its index. Otherwise, return -1.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 */
class BinarySearch {
    /**
     * This solution returns the index of the target integer within the input array with a time complexity of O(log n)
     * and a constant space complexity.
     *
     * Two pointers for the start and end of the array are initialized. While the pointers have not met, the element
     * between the two pointers is checked to see if it is the target element. If it is, the index is returned. If the
     * value at the current middle point is greater than the target, the right pointer is set to the middle point,
     * otherwise the left pointer, and the process will be repeated.
     *
     * If the loop exists and the target value was not found, -1 is returned.
     */
    fun search(nums: IntArray, target: Int): Int {
        var p1 = 0
        var p2 = nums.size - 1

        while (p1 <= p2) {
            val index = ((p2 - p1) / 2) + p1

            when {
                nums[index] < target -> p1 = index + 1
                nums[index] > target -> p2 = index - 1
                else -> return index
            }
        }

        return -1
    }
}
