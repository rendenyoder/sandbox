package binsearch

import kotlin.math.ceil

/**
 * Koko loves to eat bananas. There are "n" piles of bananas. The guards have gone and will come back in "h" hours.
 *
 * Koko can decide her bananas-per-hour eating speed of "k". Each hour, she chooses some pile of bananas and eats "k"
 * bananas from that pile. If the pile has less than "k" bananas, she eats all of them instead and will not eat any
 * more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum bananas-per-hour eating speed such that she can eat all the bananas within "h" hours.
 *
 * Constraints:
 * - `1 <= piles.length <= 1_000`
 * - `piles.length <= h <= 1_000_000`
 * - `1 <= piles[i] <= 1_000_000_000`
 */
class KokoEatingBananas {
    /**
     * This solution returns the minimum bananas-per-hour eating speed with a time complexity of O(n⋅log m) and a
     * constant space complexity.
     *
     * The approach follows an optimization pattern where the maximum bananas-per-hour eating speed is determined
     * by looking for the maximum number of bananas in the [piles]. It follows that if you could eat the largest pile
     * in a single hour, with "h" always being greater than or equal to the number of piles, you could eat each pile
     * before the guards arrive.
     *
     * This maximum bananas-per-hour speed is used as an upper boundary for a binary search approach, with the initial
     * minimum value set to 1. While the minimum and maximum pointers have not met, we take our speed and determine
     * how long it would take to eat all bananas. If the number of hours taken is smaller than or equal to the allotted
     * time, we move the maximum bananas-per-hour pointer, otherwise the minimum.
     *
     * Notice we preferentially move the maximum pointer when the allotted time is equal to the number of hours taken
     * as we will be returning the minimum pointer since we are optimizing for the smallest value.
     *
     * After the two pointers have met, the value for the minimum pointer is returned.
     */
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        var p1 = 1
        var p2 = piles.max()

        while (p1 <= p2) {
            val k = p1 + (p2 - p1) / 2
            val hours = piles.sumOf { ceil(it / k.toDouble()) }

            when {
                hours > h -> p1 = k + 1
                hours <= h -> p2 = k - 1
            }
        }

        return p1
    }
}
