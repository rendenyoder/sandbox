package twopointers

/**
 * You are given an integer array representing the heights of a set of bars.
 *
 * You may choose any two bars to form a container. Return the maximum amount of water a container can store.
 */
class ContainerWithMostWater {
    /**
     * This solution solves for the maximum area with a time complexity of O(n) and a constant space complexity.
     *
     * The solution uses a two-pointer algorithm with the two pointers starting at the start and end of the array. The
     * pointers are progressed depending on the greater height located at each of the pointers. If the left pointer
     * value is larger, the right is progressed and vice versa. At each progression, the area is calculated and
     * stored if it is the new maximum.
     *
     * Finally, the maximum is returned as the maximum area for a container given the set of heights.
     */
    fun maxArea(height: IntArray): Int {
        var p1 = 0
        var p2 = height.size - 1
        var area = 0

        while (p1 < p2) {
            area = maxOf(
                a = area,
                b = (p2 - p1) * minOf(height[p1], height[p2])
            )

            if (height[p1] < height[p2]) p1++ else p2--
        }

        return area
    }
}
