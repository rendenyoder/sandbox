package hashing

/**
 * Given an integer array nums, return true if any value appears more than once in the array, otherwise return false.
 */
class ContainsDuplicate {
    /**
     * This solution determines if an integer array has at least on duplicate value in O(n) time and space complexity.
     *
     * A set of integers is constructed. Each integer from the input array is iterated over and, if at any point, the
     * current number exists within the set, true is returned.
     *
     * Otherwise, false is returned as there were no duplicates integers.
     */
    fun containsDuplicate(nums: IntArray): Boolean {
        val set = mutableSetOf<Int>()

        for (num in nums) {
            if (num in set) return true

            set.add(num)
        }

        return false
    }
}
