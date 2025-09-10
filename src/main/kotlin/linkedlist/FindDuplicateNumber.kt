package linkedlist

/**
 * You are given an array of integers containing "n + 1" values, where "n" is an integer and each value in the array
 * is within the range [1, n] inclusively.
 *
 * Every integer appears exactly once, except for one integer which appears two or more times.
 *
 * Return the integer that appears more than once without modifying the array or using additional space.
 *
 * Constraints:
 *
 * - `1 <= n <= 10000`
 * - `1 <= nums[i] <= n`
 * - `nums.length == n + 1`
 */
class FindDuplicateNumber {
    /**
     * This solution returns the duplicate integer contained within the given array [nums], where each value in the
     * array is in the range [1, n], with a time complexity of O(n) and a constant space complexity.
     *
     * The approach follows Floyd's Tortoise and Hare Algorithm. Although [nums] is an integer array, the problem
     * can be approached as one would a linked list problem by considering each value in the array as an index pointing
     * to another value within the array. Due to the constraint of every value in the array being within the range
     * [1, n] and the size of the array being "n + 1", every value within the array will always point to another value.
     *
     * In addition, due to the array being of size "n + 1", the array must necessarily contain at least one duplicate.
     * If the values are treated as pointers, the duplicate values form a cycle. As such, it is possible to use Floyd's
     * cycle detection algorithm. It should be noted that since the values are in the range [1, n], the first value
     * cannot be a part of the cycle as no value can point to the 0th index.
     *
     * The algorithm has two phases. The first phase starts with a fast and slow pointer where the slow pointer moves
     * ahead one value for every two values the fast pointer moves. The intersection of these two pointers will always
     * occur at a value that is equidistant from that value to the start of the cycle as the first value of the array
     * to the start of the cycle. This is a mathematical feature inherent to the algorithm as applied to functional
     * graphs.
     *
     * Once there is an intersection, the second phase begins by using the fast pointer as a second slow pointer and
     * starting it at the first index. Since the distance between both pointers to the start of the cycle is the same,
     * and the pointers move the same distance per iteration, the pointers can be progressed until they meet.
     *
     * When the pointers have meet, we are guaranteed to be at the start of the cycle, and the value at that index is
     * returned as the duplicate integer.
     */
    fun findDuplicate(nums: IntArray): Int {
        var p1 = 0
        var p2 = 0

        while (true) {
            p1 = nums[p1]
            p2 = nums[nums[p2]]

            if (p1 == p2) break
        }

        p2 = 0
        while (true) {
            p1 = nums[p1]
            p2 = nums[p2]

            if (p1 == p2) return p1
        }
    }
}
