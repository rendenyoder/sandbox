package slidingwin

/**
 * You are given an array of integers and an integer "k". There is a sliding window of size "k" that starts at the
 * left edge of the array.
 *
 * The window slides one position to the right until it reaches the right edge of the array.
 *
 * Return a list that contains the maximum element in the window at each step.
 *
 * Constraints:
 * - `1 <= k <= nums.length`
 * - `1 <= nums.length <= 1000`
 * - `-1000 <= nums[i] <= 1000`
 */
class SlidingWindowMaximum {
    /**
     * This solution returns the maximum integer for each window of size [k] given an array of integers [nums] with
     * a time and space complexity of O(n).
     *
     * The algorithm uses a sliding window technique coupled with a monotonically decreasing queue. First, a result
     * array, a queue (meant to contain indices of window values), and left and right pointers are initialized.
     *
     * While the right pointer is less than the size of [nums], the values are scanned. For each iteration, the queue
     * has previous values removed from the end, while the right pointer represents a number greater than the number
     * represented by the last value of the queue. This ensures the queue always contains indices pointing to
     * monotonically decreasing values. Afterward, the right pointer is added to the end of the queue and, for every
     * iteration, the right pointer is incremented.
     *
     * If, for any iteration, the pointers represent a valid window size of [k], the value of [nums] at the first
     * value of the queue (i.e., the maximum value for the window) is added to the result array. The left pointer is
     * incremented, and if the queue's first value is smaller than the left pointer (i.e., has left the window), the
     * value is removed from the start of the queue.
     *
     * Finally, the result array is returned as the maximum value at each window of size [k] from [nums].
     */
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val res = mutableListOf<Int>()
        val queue = ArrayDeque<Int>()

        var l = 0
        var r = 0

        while (r < nums.size) {
            while (queue.isNotEmpty() && nums[queue.last()] < nums[r]) {
                queue.removeLast()
            }

            queue.addLast(r)

            if (k == r - l + 1) {
                res.add(nums[queue.first()])

                if (l++ >= queue.first()) {
                    queue.removeFirst()
                }
            }

            r++
        }

        return res.toIntArray()
    }
}
