package stacks

/**
 * You are given an array of heights where each height represents the height of a bar. The width of each bar is 1.
 *
 * Return the area of the largest rectangle that can be formed between the bars.
 *
 * Constraints:
 * - `1 <= heights.length <= 105`
 * - `0 <= heights[i] <= 104`
 */
class LargestRectangleInHistogram {
    /**
     * The solution returns the maximum area of a rectangle able to be formed within the histogram with a time and
     * space complexity of O(n).
     *
     * The core rationale behind this approach is that, as the heights are iterated through, shorter heights prevent
     * previously encountered heights from contributing their full height to the area for future bars. They can
     * only contribute as much height as a given shorter height, encountered later in a sequence.
     *
     * To start, a max area and stack are initialized. The stack is to contain a monotonically decreasing pairing of a
     * starting index to the height of a bar when the pair is added to the stack.
     *
     * For each height, the stack is checked to see if it is not empty and the height at the top of the stack is
     * greater than the current height. While these conditions are true, the top of the stack is popped. The area
     * that can be accounted for by the popped pairing is calculated and set as the new max area if it is larger than
     * the current max area. The formula for the area is the height multiplied by the difference between the current
     * index and the index of the popped pair. After this, either the current index or the index of the last popped
     * pair is added to the stack, paired with the current height.
     *
     * Finally, the max area of any remaining elements in the stack is evaluated, and the max area encountered is
     * returned as the max area of a rectangle under the histogram.
     */
    fun largestRectangleArea(heights: IntArray): Int {
        var area = 0
        val stack = ArrayDeque<Pair<Int, Int>>()

        for ((i, h) in heights.withIndex()) {
            var start = i

            while (stack.isNotEmpty() && h < stack.last().second) {
                val (ix, hx) = stack.removeLast()

                area = maxOf(area, (i - ix) * hx)
                start = ix
            }

            stack.add(start to h)
        }

        for ((i, h) in stack) {
            area = maxOf(area, h * (heights.size - i))
        }

        return area
    }
}
