package hashing

import kotlin.collections.iterator

/**
 * Given an integer array nums and an integer k, return the k most frequent elements within the array.
 *
 * You may return the output in any order.
 */
class TopKFrequentElements {
    /**
     * This solution builds a map of frequencies for the provided array. Afterward, a modified version of bucket sort
     * is used to achieve a top-k result set selection with a time and space complexity of O(n).
     *
     * A list of 'buckets' is initialized, and the frequency number is added to each bucket at the frequency value
     * index. The buckets are then iterated over in reverse order, and the result is returned once the result
     * set reaches the desired size of [k].
     *
     * There also exists an alternate O(n•log•n) approach which uses a max-heap. This approach is similar except the
     * frequencies are added to a max-heap, ensuring it never grows past size [k], and is returned as an array:
     *
     * ```kotlin
     * ...
     *
     * val byFrequency = compareBy<Map.Entry<Int, Int>> { it.value }
     * val priorityQueue = PriorityQueue(k, byFrequency)
     *
     * for (entry in frequencies) {
     *     if (priorityQueue.size < k) {
     *         priorityQueue.offer(entry)
     *     } else if (entry.value > priorityQueue.peek().value) {
     *         priorityQueue.poll()
     *         priorityQueue.offer(entry)
     *     }
     * }
     *
     * return IntArray(k) { priorityQueue.poll().key }
     * ```
     */
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val frequencies = mutableMapOf<Int, Int>()
        for (n in nums) {
            frequencies[n] = frequencies.getOrDefault(n, 0) + 1
        }

        val buckets = List(nums.size + 1) { mutableListOf<Int>() }
        for (entry in frequencies) {
            buckets[entry.value].add(entry.key)
        }

        val results = mutableListOf<Int>()
        for(i in buckets.size - 1 downTo 1) {
            for (item in buckets[i]) {
                results.add(item)

                if (results.size >= k) {
                    return results.toIntArray()
                }
            }
        }

        return results.toIntArray()
    }
}
