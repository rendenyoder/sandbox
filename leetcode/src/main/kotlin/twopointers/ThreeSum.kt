package twopointers

/**
 * Given an integer array, return all triplets such that the sum of all three values is equal to 0.
 *
 * The output should not contain any duplicate triplets. You may return the output and triplets in any order.
 */
class ThreeSum {
    /**
     * This solution finds all triplets where the sum of all three values is equal to zero with a time complexity of
     * O(n²) and space complexity of O(1), barring any additional memory consumed by the sorting algorithm.
     *
     * The integer array is sorted and iterated over. Each value that is less than zero and not equal to an
     * immediately preceding value is used as the fixed starting point for a two-pointer algorithm.
     *
     * Both start and end pointers are initialized. The starting pointer points to the index after the current
     * number. The two pointers and incremented and decremented respectively until they cross or of the current number
     * and the values at the two-pointers sum to zero.
     *
     * If the values sum to zero, the values are added to the result list and both pointers progress, with the left
     * pointer progressing until a different value is encountered or until it meets the right pointer.
     *
     * Finally, the resulting triplets are returned as the solution.
     */
    fun threeSum(nums: IntArray): List<List<Int>> {
        val results = mutableListOf<List<Int>>()

        nums.sort()
        for (i in nums.indices) {
            val num = nums[i]

            if (num > 0) break
            if (i > 0 && num == nums[i - 1]) continue

            var p1 = i + 1
            var p2 = nums.size - 1
            while (p1 < p2) {
                val sum = num + nums[p1] + nums[p2]
                when {
                    sum < 0 -> p1++
                    sum > 0 -> p2--
                    else -> {
                        results.add(listOf(num, nums[p1], nums[p2]))

                        p1++
                        p2--

                        while (p1 < p2 && nums[p1] == nums[p1 - 1]) p1++
                    }
                }
            }
        }

        return results
    }
}
