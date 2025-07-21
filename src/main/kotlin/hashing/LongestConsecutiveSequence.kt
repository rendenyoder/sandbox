package hashing

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * A consecutive sequence is a sequence of elements in which each element is exactly 1 greater than the previous
 * element. The elements do not have to be consecutive in the original array.
 *
 * You must write an algorithm that runs in O(n) time.
 */
class LongestConsecutiveSequence {
    /**
     * This solution returns the longest sequence that can be made from a given input array with a time and space
     * complexity of O(n).
     *
     * The solution first determines converts the input array into a set. The set is iterated over and each value
     * is determined to be the start of a sequence if one less than the value is not present in the set.
     *
     * If the value is the start of a sequence, a counter is incremented while one greater than the incremented
     * sequence start value is contained within the set. Afterward, if the counter is greater than the current
     * maximum, it is assigned as the new maximum.
     *
     * Finally, the maximum value is returned as the longest sequence length.
     */
    fun longestConsecutive(nums: IntArray): Int {
        var maximum = 0
        val hashset = nums.toSet()

        for (num in hashset) {
            if (num - 1 in hashset) continue

            var counter = 1
            while ((num + counter) in hashset) {
                counter++
            }

            if (counter > maximum) {
                maximum = counter
            }
        }

        return maximum
    }
}
