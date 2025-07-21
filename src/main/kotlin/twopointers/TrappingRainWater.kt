package twopointers

/**
 * You are given an array of non-negative integers height which represents an elevation map.
 *
 * Each value within the array represents the height of a bar, which has a width of 1.
 *
 * Return the maximum area of water that can be trapped between the bars.
 */
class TrappingRainWater {
  /**
   * The solution solves the maximum amount of water that can be trapped with a time complexity of O(n) and a constant
   * space complexity.
   *
   * The approach uses a two-pointer algorithm where pointers are initialized for the start and end of the array. The
   * maximum value encountered for each pointer as they are progressed is stored as left and right maximums.
   *
   * If the size of the array is zero, a value of zero is immediately returned.
   *
   * Otherwise, the pointers are progressed until they meet. The pointer with the largest maximum for a given iteration
   * is progressed, and the maximum value between the current pointer's value and the maximum height encountered so far
   * is used to calculate the area that can be accounted for.
   *
   * The area for each iteration is accumulated for each iteration and returned as the total amount of water that
   * can be trapped given a set of heights.
   */
  fun trap(height: IntArray): Int {
    if (height.isEmpty()) return 0

    var p1 = 0
    var p2 = height.size - 1

    var lmax = height[p1]
    var rmax = height[p2]

    var water = 0
    while (p1 < p2) {
      if (lmax <= rmax) {
        p1++
        lmax = maxOf(lmax, height[p1])
        water += lmax - height[p1]
      } else {
        p2--
        rmax = maxOf(rmax, height[p2])
        water += rmax - height[p2]
      }
    }

    return water
  }
}
